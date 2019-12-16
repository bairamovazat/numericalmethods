package ru.ivmiit.azat.numericalmethods.methods;

import ru.ivmiit.azat.numericalmethods.model.Argument;

public interface CauchysProblemMethod<T> {
    Argument<T> next(double time, Argument<T> current, double step, int iterationNumber);
}
