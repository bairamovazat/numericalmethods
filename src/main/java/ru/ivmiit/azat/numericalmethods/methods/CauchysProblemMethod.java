package ru.ivmiit.azat.numericalmethods.methods;

public interface CauchysProblemMethod<T> {
    Argument<T> next(double time, Argument<T> current, double step, int iterationNumber);
}
