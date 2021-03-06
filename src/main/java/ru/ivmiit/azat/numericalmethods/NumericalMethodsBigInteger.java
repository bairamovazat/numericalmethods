package ru.ivmiit.azat.numericalmethods;

import ru.ivmiit.azat.numericalmethods.function.Function;
import ru.ivmiit.azat.numericalmethods.function.FunctionBigDecimalImpl;
import ru.ivmiit.azat.numericalmethods.graph.LineGraph;
import ru.ivmiit.azat.numericalmethods.graph.SimpleLine;
import ru.ivmiit.azat.numericalmethods.methods.*;
import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentBigDecimalImpl;
import ru.ivmiit.azat.numericalmethods.model.Row;
import ru.ivmiit.azat.numericalmethods.utils.ErrorUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumericalMethodsBigInteger implements NumericalMethods {
    //Нарисовать график для тестирования
    public void testTaskCalculate(double start, double end, int iterationCount) {

        ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra = getDefaultModel(start / 3.0, -start / 3.0);

        modelTraysVolterra.calculate(start, iterationCount, (end - start) / iterationCount);

        List<SimpleLine> simpleLines = new ArrayList<>();
        simpleLines.add(
                new SimpleLine("Жертвы",
                        Color.BLUE,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getValues()
                                .stream()
                                .map(e -> e.getX().doubleValue())
                                .collect(Collectors.toList())
                )
        );

        simpleLines.add(
                new SimpleLine("Хищники",
                        Color.RED,
                        modelTraysVolterra.getTime(),
                        modelTraysVolterra.getValues()
                                .stream()
                                .map(e -> e.getY().doubleValue())
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
        LineGraph lineGraph = new LineGraph(simpleLines, "Время", "Численность", "Зависимость численности от времени для тестовой задачи", true);
        lineGraph.setVisible(true);
    }

    public ModelTraysVolterra<Row<BigDecimal>> getDefaultModel(double defaultX, double defaultY) {
        Argument<Row<BigDecimal>> zeroState = new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(BigDecimal.valueOf(defaultX), BigDecimal.valueOf(defaultY))
        );
        Function<Row<BigDecimal>> rowFunction = new FunctionBigDecimalImpl();
        CauchysProblemMethod<Row<BigDecimal>> chekinskyMethod = new CheskinoMethod<>(rowFunction);
        return new ModelTraysVolterra<>(chekinskyMethod, zeroState);
    }

    //startStep > endStep
    public void errorsE(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep) {

        List<Double> stepList = new ArrayList<>();
        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra = getDefaultModel(start / 3.0, -start / 3.0);
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(ErrorUtils.getMaxXErrorBigDecimal(modelTraysVolterra, (t) -> t / 3.0));
            maxErrorY.add(ErrorUtils.getMaxYErrorBigDecimal(modelTraysVolterra, (t) -> -t / 3.0));
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

        LineGraph lineGraph = new LineGraph(simpleLines, "Шаг h", "Ошибка: e", "Зависимость ошибки от шага h", true);
        lineGraph.setVisible(true);
    }

    public void errorsEh4(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep) {

        List<Double> stepList = new ArrayList<>();
        List<Double> maxErrorX = new ArrayList<>();
        List<Double> maxErrorY = new ArrayList<>();

        double currentStep = startStep;
        while ((currentStep = getNextStep.apply(currentStep)) > endStep) {
            ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra = getDefaultModel(start / 3.0, -start / 3.0);
            modelTraysVolterra.calculate(start, end, currentStep);
            stepList.add(currentStep);
            maxErrorX.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxXErrorBigDecimal(modelTraysVolterra, (t) -> t / 3.0))
                            .divide(BigDecimal.valueOf(currentStep).pow(4), RoundingMode.HALF_UP)
                            .doubleValue()
            );
            maxErrorY.add(
                    BigDecimal.valueOf(ErrorUtils.getMaxYErrorBigDecimal(modelTraysVolterra, (t) -> -t / 3.0))
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

        LineGraph lineGraph = new LineGraph(simpleLines, "Шаг h", "Ошибка: e/h^4", "Зависимость ошибки от шага h^4", true);
        lineGraph.setVisible(true);
    }

    @Override
    public void studySchedule(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step) {

    }
}
