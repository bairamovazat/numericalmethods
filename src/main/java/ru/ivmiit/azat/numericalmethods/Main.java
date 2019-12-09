package ru.ivmiit.azat.numericalmethods;

import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) {
        //Реализация через double
        NumericalMethods numericalMethods = new NumericalMethodsDouble();
        //Реализация через BigInteger
//        NumericalMethods numericalMethods = new NumericalMethodsBigInteger();

        double start = -10;
        double end = -1;
        int iterationCount = 100;

        numericalMethods.testTaskCalculate(start, end, iterationCount);

        numericalMethods.errorsE(start, end, 1, 0.001, (step) -> step - 0.05);

        numericalMethods.errorsEh4(start, end, 1, 0.001, (step) -> step - 0.05);

        double a = 1;
        double b = 1;
        double c = 1;
        double d = 0.1;
        //c > lastC
        double lastC = 0.1;
        double cStep = 0.05;
        double xStart = 3;
        double yStart = 1;
        double startTime = 1;
        double endTime = 60;
        double step = 0.05;

        numericalMethods.studySchedule(a, b, c, d, lastC, cStep, xStart, yStart, startTime, endTime, step);
    }
}
