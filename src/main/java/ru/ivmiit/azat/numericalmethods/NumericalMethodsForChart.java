package ru.ivmiit.azat.numericalmethods;

import ru.ivmiit.azat.numericalmethods.model.SolverType;
import ru.ivmiit.azat.numericalmethods.model.TaskType;

import javax.swing.*;

public interface NumericalMethodsForChart {

    void testTaskCalculateAndShowDialog(double start, double end, int iterationCount, JFrame jFrame);

    void errorsEAndShowDialog(double start, double end, double startStep, double endStep, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame);

    void errorsEh4AndShowDialog(double start, double end, double startStep, double endStep, int pow, java.util.function.Function<Double, Double> getNextStep, JFrame jFrame);

    void studyScheduleAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);

    void studyScheduleXAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);

    void studyScheduleYAndShowDialog(double a, double lastA, double aStep, double xStart, double yStart, double startTime, double endTime, double step, JFrame jFrame);

    TaskType getTaskType();
    void setTaskType(TaskType taskType);
    SolverType getSolverType();
    void setSolverType(SolverType solverType);
}
