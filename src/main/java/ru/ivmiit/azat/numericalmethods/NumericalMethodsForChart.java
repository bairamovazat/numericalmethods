package ru.ivmiit.azat.numericalmethods;

import ru.ivmiit.azat.numericalmethods.ui.MainFrame;

import javax.swing.*;

public interface NumericalMethodsForChart {

    public void testTaskCalculateAndShowDialog(double start, double end, int iterationCount, JFrame jFrame);

    public void errorsEAndShowDialog(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame);

    public void errorsEh4AndShowDialog(double start, double end, double startStep, double endStep, int pow, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame);

    public void studyScheduleAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);

    void studyScheduleXAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);

    void studyScheduleYAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);
}
