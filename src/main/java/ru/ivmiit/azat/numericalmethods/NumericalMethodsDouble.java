package ru.ivmiit.azat.numericalmethods;

import ru.ivmiit.azat.numericalmethods.graph.LineGraph;
import ru.ivmiit.azat.numericalmethods.graph.SimpleLine;
import ru.ivmiit.azat.numericalmethods.methods.*;
import ru.ivmiit.azat.numericalmethods.model.Sheep;
import ru.ivmiit.azat.numericalmethods.model.Wolf;
import ru.ivmiit.azat.numericalmethods.utils.ErrorUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumericalMethodsDouble implements NumericalMethods {
    //Нарисовать график для тестирования
    public void testTaskCalculate(double start, double end, int iterationCount) {
        Argument<Row<Double>> zeroState = new ArgumentImpl(
                new Row<Double>(start / 3.0, -start / 3.0)
        );
        Function<Row<Double>> rowFunction = new FunctionImpl();
        CauchysProblemMethod<Row<Double>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
        ModelTraysVolterra<Row<Double>> modelTraysVolterra = new ModelTraysVolterra<>(chekinskyMethod, zeroState);

        modelTraysVolterra.calculate(start, iterationCount, (end - start) / iterationCount);

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы",
                        Color.BLUE,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getValues()
                                .stream().map(Row::getX)
                                .collect(Collectors.toList())
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники",
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
                                .map(e -> e / 3.0)
                                .collect(Collectors.toList())
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники (Правильное решение)",
                        Color.ORANGE,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getTime().stream()
                                .map(e -> e / -3.0)
                                .collect(Collectors.toList())
                )
        );
        LineGraph lineGraph = new LineGraph(simpleLines, "Время", "Численность", "Зависимость численности от времени для тестовой задачи");
        lineGraph.setVisible(true);
    }

    public ModelTraysVolterra<Row<Double>> getDefaultModel(double defaultX, double defaultY) {
        Argument<Row<Double>> zeroState = new ArgumentImpl(
                new Row<Double>(defaultX, defaultY)
        );
        Function<Row<Double>> rowFunction = new FunctionImpl();
        CauchysProblemMethod<Row<Double>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
        return new ModelTraysVolterra<>(chekinskyMethod, zeroState);
    }

    //startStep > endStep
    public void errorsE(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep) {

        List<Double> stepList = new ArrayList<>();
        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = getDefaultModel(start / 3.0, -start / 3.0);
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(ErrorUtils.getMaxXError(modelTraysVolterra, (t) -> t / 3.0));
            maxErrorY.add(ErrorUtils.getMaxYError(modelTraysVolterra, (t) -> -t / 3.0));
        }

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы",
                        Color.BLUE,
                        stepList,
                        maxErrorX
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники",
                        Color.RED,
                        stepList,
                        maxErrorY
                )
        );

        LineGraph lineGraph = new LineGraph(simpleLines, "Шаг h", "Ошибка: e", "Зависимость ошибки от шага h");
        lineGraph.setVisible(true);
    }

    public void errorsEh4(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep) {

        List<Double> stepList = new ArrayList<>();
        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = getDefaultModel(start / 3.0, -start / 3.0);
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxXError(modelTraysVolterra, (t) -> t / 3.0))
                            .divide(BigDecimal.valueOf(currentStep).pow(4), RoundingMode.HALF_UP)
                            .doubleValue()
            );
            maxErrorY.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxYError(modelTraysVolterra, (t) -> -t / 3.0))
                            .divide(BigDecimal.valueOf(currentStep).pow(4), RoundingMode.HALF_UP)
                            .doubleValue()
            );
        }

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы",
                        Color.BLUE,
                        stepList,
                        maxErrorX
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники",
                        Color.RED,
                        stepList,
                        maxErrorY
                )
        );

        LineGraph lineGraph = new LineGraph(simpleLines, "Шаг h", "Ошибка: e/h^4", "Зависимость ошибки от шага h^4");
        lineGraph.setVisible(true);
    }

    public void studySchedule(double a, double b, double c, double d, double lastC, double cStep, double xStart, double yStart, double startTime, double endTime, double step) {



        for (double newC = c; newC > lastC; newC -= cStep) {
            Argument<Row<Double>> zeroState = new ArgumentImpl(
                    new Row<Double>(xStart, yStart)
            );
            Sheep<Double> sheep = new Sheep<>(a, b);
            Wolf<Double> wolf = new Wolf<>(newC, d);

            Function<Row<Double>> rowFunction = new AnimalFunction(wolf, sheep);
            CauchysProblemMethod<Row<Double>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
            ModelTraysVolterra<Row<Double>> modelTraysVolterra = new ModelTraysVolterra<>(chekinskyMethod, zeroState);

            modelTraysVolterra.calculate(startTime, endTime, step);

            List<SimpleLine> simpleLines = new ArrayList<>();
            simpleLines.add(
                    new SimpleLine("X/Y",
                            Color.BLUE,
                            modelTraysVolterra.getValues()
                                    .stream().map(e -> e.getX() * d / a)
                                    .collect(Collectors.toList()) ,
                            modelTraysVolterra.getValues()
                                    .stream().map(e -> e.getY() * a / b)
                                    .collect(Collectors.toList())
                    )
            );


            LineGraph lineGraph = new LineGraph(simpleLines, "X", "Y", "Зависимость X/Y");
            lineGraph.setVisible(true);
        }
    }
}