package ru.ivmiit.azat.numericalmethods.methods;

import ru.ivmiit.azat.numericalmethods.function.Function;
import ru.ivmiit.azat.numericalmethods.model.Argument;

public class CheskinoMethod<T> implements CauchysProblemMethod<T> {

    private Function<T> function;

    public CheskinoMethod(Function<T> function) {
        this.function = function;
    }

    public Argument<T> next(double time, Argument<T> current, double step, int iterationNumber) {
        Argument<T> k1 = function.get(time, current, iterationNumber);
        Argument<T> k2Argument = current.addition(k1.multiplication(step).division(4D));
        Argument<T> k2 = function.get((4D * time + step) / 4D, k2Argument, iterationNumber);
        Argument<T> k3Argument = current.addition(k2.multiplication(step).division(2D));
        Argument<T> k3 = function.get((2D* time + step) / 2D, k3Argument, iterationNumber);
        Argument<T> k4Argument  = current.addition(k1.multiplication(step))
                .subtraction(k2.multiplication(2D * step))
                .addition(k3.multiplication(2D * step));
        Argument<T> k4 = function.get(time + step, k4Argument, iterationNumber);

        return current.addition(
                k1.addition(k3.multiplication(4D))
                        .addition(k4)
                        .multiplication(step)
                        .division(6D)
        );
    }
}
