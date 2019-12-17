package ru.ivmiit.azat.numericalmethods;


public interface NumericalMethods{

    public void testTaskCalculate(double start, double end, int iterationCount);

    public void errorsE(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep);

    public void errorsEh4(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep);

    public void studySchedule(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step);
}