package ru.ivmiit.azat.numericalmethods;

import lombok.Getter;
import lombok.Setter;
import ru.ivmiit.azat.numericalmethods.function.Function;
import ru.ivmiit.azat.numericalmethods.function.MyFunctionImpl;
import ru.ivmiit.azat.numericalmethods.function.NikitaFunctionImpl;
import ru.ivmiit.azat.numericalmethods.function.UnsizedFunction;
import ru.ivmiit.azat.numericalmethods.graph.SimpleLine;
import ru.ivmiit.azat.numericalmethods.methods.CauchysProblemMethod;
import ru.ivmiit.azat.numericalmethods.methods.CheskinoMethod;
import ru.ivmiit.azat.numericalmethods.methods.RungeKuttaMethod;
import ru.ivmiit.azat.numericalmethods.model.*;
import ru.ivmiit.azat.numericalmethods.ui.LineAnimatedGraphChart;
import ru.ivmiit.azat.numericalmethods.ui.LineGraphChart;
import ru.ivmiit.azat.numericalmethods.utils.ErrorUtils;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NumericalMethodsDoubleForChart implements NumericalMethodsForChart {

    private java.util.function.Function<Double, Double> azatTrueXFunction = (x) -> (x / 3.0);

    private java.util.function.Function<Double, Double> azatTrueYFunction = (y) -> (y / -3.0);

    private java.util.function.Function<Double, Double> nikitaTrueXFunction = (t) -> (Math.cos(t * t) * Math.sqrt(1 + t));

    private java.util.function.Function<Double, Double> nikitaTrueYFunction = (t) -> (Math.sin(t * t) * Math.sqrt(1 + t));

    private java.util.function.Function<Double, CauchysProblemMethod<Row<Double>>> unsizedCheskinoProblemMethod =
            (aParam) -> new CheskinoMethod<>(new UnsizedFunction(aParam));

    private java.util.function.Function<Double, CauchysProblemMethod<Row<Double>>> unsizedRungeKuttaProblemMethod =
            (aParam) -> new RungeKuttaMethod<>(getRowFunction());

    @Setter
    @Getter
    //0 - мой вариант, 1 - Никиты
    private TaskType taskType = TaskType.AZAT;
    //0 - Ческино, 1 - Рунге Кутты
    @Setter
    @Getter
    private SolverType solverType = SolverType.CHESKINO;

    public CauchysProblemMethod<Row<Double>> getCheskinoMethod() {
        return new CheskinoMethod<>(getRowFunction());
    }

    public CauchysProblemMethod<Row<Double>> getRungeKuttaMethod() {
        return new RungeKuttaMethod<>(getRowFunction());
    }

    public Function<Row<Double>> getRowFunction() {
        return taskType == TaskType.AZAT ? new MyFunctionImpl() : new NikitaFunctionImpl();
    }

    public CauchysProblemMethod<Row<Double>> getProblemMethod() {
        return solverType == SolverType.CHESKINO ? getCheskinoMethod() : getRungeKuttaMethod();
    }

    public java.util.function.Function<Double, CauchysProblemMethod<Row<Double>>> getUnsizedProblemMethod() {
        return solverType == SolverType.CHESKINO ? unsizedCheskinoProblemMethod : unsizedRungeKuttaProblemMethod;
    }

    public java.util.function.Function<Double, Double> getTrueXFunction() {
        return taskType == TaskType.AZAT ? azatTrueXFunction : nikitaTrueXFunction;
    }

    public java.util.function.Function<Double, Double> getTrueYFunction() {
        return taskType == TaskType.AZAT ? azatTrueYFunction : nikitaTrueYFunction;
    }

    //Нарисовать график для тестирования
    public void testTaskCalculateAndShowDialog(double start, double end, int iterationCount, JFrame jFrame) {

        ModelTraysVolterra<Row<Double>> modelTraysVolterra = getDefaultModel(getTrueXFunction().apply(start), getTrueYFunction().apply(start));

        modelTraysVolterra.calculate(start, iterationCount, (end - start) / iterationCount);

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы(X)",
                        Color.BLUE,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getValues()
                                .stream().map(Row::getX)
                                .collect(Collectors.toList())
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники(Y)",
                        Color.RED,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getValues()
                                .stream().map(Row::getY)
                                .collect(Collectors.toList())

                )
        );

        simpleLines.add(
                new SimpleLine("Жертвы (Правильное решение)",
                        Color.CYAN,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getTime().stream()
                                .map(t -> getTrueXFunction().apply(t))
                                .collect(Collectors.toList())
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники (Правильное решение)",
                        Color.ORANGE,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getTime().stream()
                                .map(t -> getTrueYFunction().apply(t))
                                .collect(Collectors.toList())
                )
        );

        System.out.println("Время\tПолученное решение x\tПолученное решение y\tТочное решение x\tТочное решение x\tОшибка x\tОшибка y");
        for (int i = 0; i < modelTraysVolterra.getTime().size(); i++) {
            Double time = modelTraysVolterra.getTime().get(i);
            Double x = modelTraysVolterra.getValues().get(i).getX();
            Double y = modelTraysVolterra.getValues().get(i).getY();
            Double trueX = (getTrueXFunction().apply(modelTraysVolterra.getTime().get(i)));
            Double trueY = (getTrueYFunction().apply(modelTraysVolterra.getTime().get(i)));

            String format = "%.6f";
            System.out.println(
                    String.format(format, time)  + "\t" +
                            String.format(format, x) + "\t" +
                            String.format(format, y) + "\t" +
                            String.format(format, trueX) + "\t" +
                            String.format(format, trueY )+ "\t" +
                            Math.abs(x - trueX) + "\t" +
                            Math.abs(y - trueY)
            );
        }

        LineGraphChart lineGraph = new LineGraphChart(simpleLines, "Время", "Численность", "Зависимость численности от времени для тестовой задачи", true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }

    public ModelTraysVolterra<Row<Double>> getDefaultModel(double defaultX, double defaultY) {
        Argument<Row<Double>> zeroState = new ArgumentImpl(
                new Row<Double>(defaultX, defaultY)
        );
        return new ModelTraysVolterra<>(getProblemMethod(), zeroState);
    }

    public ModelTraysVolterra<Row<Double>> getDefaultUnsizedModel(double defaultX, double defaultY, double a) {
        Argument<Row<Double>> zeroState = new ArgumentImpl(
                new Row<Double>(defaultX, defaultY)
        );
        CauchysProblemMethod<Row<Double>> chekinskyMethod = getUnsizedProblemMethod().apply(a);
        return new ModelTraysVolterra<>(chekinskyMethod, zeroState);
    }

    //startStep > endStep
    public void errorsEAndShowDialog(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame) {

        List<Double> stepList = new ArrayList<>();
        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = getDefaultModel(getTrueXFunction().apply(start), getTrueYFunction().apply(start));
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(ErrorUtils.getMaxXError(modelTraysVolterra, (t) -> getTrueXFunction().apply(t)));
            maxErrorY.add(ErrorUtils.getMaxYError(modelTraysVolterra, (t) -> getTrueYFunction().apply(t)));
        }

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы(X)",
                        Color.BLUE,
                        stepList,
                        maxErrorX
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники(Y)",
                        Color.RED,
                        stepList,
                        maxErrorY
                )
        );

        LineGraphChart lineGraph = new LineGraphChart(simpleLines, "Шаг h", "Ошибка: e", "Зависимость ошибки от шага h", true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }

    public void errorsEh4AndShowDialog(double start, double end, double startStep, double endStep, int pow, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame) {

        List<Double> stepList = new ArrayList<>();
        List<Double> step4powValueList = new ArrayList<>();

        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = getDefaultModel(getTrueXFunction().apply(start), getTrueYFunction().apply(start));
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxXError(modelTraysVolterra, (t) -> getTrueXFunction().apply(t)))
                            .divide(BigDecimal.valueOf(currentStep).pow(pow), RoundingMode.HALF_UP)
                            .doubleValue()
            );
            maxErrorY.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxYError(modelTraysVolterra, (t) -> getTrueYFunction().apply(t)))
                            .divide(BigDecimal.valueOf(currentStep).pow(pow), RoundingMode.HALF_UP)
                            .doubleValue()
            );
        }

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы(X)",
                        Color.BLUE,
                        stepList,
                        maxErrorX
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники(Y)",
                        Color.RED,
                        stepList,
                        maxErrorY
                )
        );

//        simpleLines.add(
//                new SimpleLine("График h^4",
//                        Color.RED,
//                        stepList,
//                        step4powValueList
//                )
//        );


        LineGraphChart lineGraph = new LineGraphChart(simpleLines, "Шаг h", "Ошибка: e/h^4", "Зависимость ошибки от шага h^4", true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }

    public void studyScheduleAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame) {

        LinkedHashMap<Double, List<SimpleLine>> simpleLinesOfA = new LinkedHashMap<>();

        for (double currentA = a; currentA > lastA; currentA += aStep) {
            Argument<Row<Double>> zeroState = new ArgumentImpl(
                    new Row<Double>(xStart, yStart)
            );
            CauchysProblemMethod<Row<Double>> chekinskyMethod = getUnsizedProblemMethod().apply(currentA);
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = new ModelTraysVolterra<>(chekinskyMethod, zeroState);

            modelTraysVolterra.calculate(startTime, endTime, step);

            List<SimpleLine> simpleLines = new ArrayList<>();
            simpleLines.add(
                    new SimpleLine("X/Y",
                            Color.BLUE,
                            modelTraysVolterra.getValues()
                                    .stream().map(Row::getX)
                                    .collect(Collectors.toList()),
                            modelTraysVolterra.getValues()
                                    .stream().map(Row::getY)
                                    .collect(Collectors.toList()),
                            modelTraysVolterra.getTime()
                    )
            );
            simpleLinesOfA.put(currentA, simpleLines);
        }

        LineAnimatedGraphChart lineGraph = new LineAnimatedGraphChart(simpleLinesOfA, "Жертв(X)", "Хищников(Y)", "Зависимость X/Y", false);
        lineGraph.setLine(true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }


    @Override
    public void studyScheduleXAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame) {

        LinkedHashMap<Double, List<SimpleLine>> simpleLinesOfA = new LinkedHashMap<>();

        for (double currentA = a; currentA > lastA; currentA += aStep) {
            Argument<Row<Double>> zeroState = new ArgumentImpl(
                    new Row<Double>(xStart, yStart)
            );

            Function<Row<Double>> rowFunction = new UnsizedFunction(currentA);
            CauchysProblemMethod<Row<Double>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = new ModelTraysVolterra<>(chekinskyMethod, zeroState);

            modelTraysVolterra.calculate(startTime, endTime, step);

            List<SimpleLine> simpleLines = new ArrayList<>();
            simpleLines.add(
                    new SimpleLine("X/t",
                            Color.BLUE,
                            modelTraysVolterra.getTime(),
                            modelTraysVolterra.getValues()
                                    .stream().map(Row::getX)
                                    .collect(Collectors.toList()),
                            modelTraysVolterra.getTime()
                    )
            );
            simpleLinesOfA.put(currentA, simpleLines);
        }

        LineAnimatedGraphChart lineGraph = new LineAnimatedGraphChart(simpleLinesOfA, "Время(t)", "Жертв(X)", "Зависимость X/t", false);
        lineGraph.setLine(true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }

    @Override
    public void studyScheduleYAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame) {

        LinkedHashMap<Double, List<SimpleLine>> simpleLinesOfA = new LinkedHashMap<>();

        for (double currentA = a; currentA > lastA; currentA += aStep) {
            Argument<Row<Double>> zeroState = new ArgumentImpl(
                    new Row<Double>(xStart, yStart)
            );

            Function<Row<Double>> rowFunction = new UnsizedFunction(currentA);
            CauchysProblemMethod<Row<Double>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = new ModelTraysVolterra<>(chekinskyMethod, zeroState);

            modelTraysVolterra.calculate(startTime, endTime, step);

            List<SimpleLine> simpleLines = new ArrayList<>();
            simpleLines.add(
                    new SimpleLine("Y/t",
                            Color.BLUE,
                            modelTraysVolterra.getTime(),
                            modelTraysVolterra.getValues()
                                    .stream().map(Row::getY)
                                    .collect(Collectors.toList()),
                            modelTraysVolterra.getTime()
                    )
            );
            simpleLinesOfA.put(currentA, simpleLines);
        }

        LineAnimatedGraphChart lineGraph = new LineAnimatedGraphChart(simpleLinesOfA, "Время(t)", "Хищников(Y)", "Зависимость Y/t", false);
        lineGraph.setLine(true);
        lineGraph.pack();
        lineGraph.setVisible(true);
    }
}
