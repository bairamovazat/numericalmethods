package ru.ivmiit.azat.numericalmethods;

import ru.ivmiit.azat.numericalmethods.methods.Row;

import java.math.BigDecimal;

public interface NumericalMethods {

    public void testTaskCalculate(double start, double end, int iterationCount);

    public void errorsE(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep);

    public void errorsEh4(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep);

    public void studySchedule(double a, double b, double c, double d, double lastC, double cStep, double xStart, double yStart, double startTime, double endTime, double step);

    }