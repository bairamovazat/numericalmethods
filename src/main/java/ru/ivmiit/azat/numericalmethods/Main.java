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
        //c > lastC
        double lastA = 0.1;
        double aStep = 0.05;
        double xStart = 1;
        double yStart = 1;
        double startTime = 1;
        double endTime = 60;
        double step = 0.05;

        numericalMethods.studySchedule(a, lastA, aStep, xStart, yStart, startTime, endTime, step);
    }
}
