package ru.ivmiit.azat.numericalmethods.methods;

public class CheskinoMethod<T> implements CauchysProblemMethod<T> {

    private Function<T> function;

    public CheskinoMethod(Function<T> function) {
        this.function = function;
    }

    public Argument<T> next(double time, Argument<T> current, double step, int iterationNumber) {
        Argument<T> k1 = function.get(time, current, iterationNumber);
        Argument<T> k2Argument = current.addition(k1.multiplication(step / 4));
        Argument<T> k2 = function.get(time + step / 4.0, k2Argument, iterationNumber);
        Argument<T> k3Argument = current.addition(k2.multiplication(step / 2));
        Argument<T> k3 = function.get(time + step / 2.0, k3Argument, iterationNumber);
        Argument<T> k4Argument = current.addition(k1.multiplication(step))
                .subtraction(k2.multiplication(2 * step))
                .addition(k3.multiplication(2 * step));
        Argument<T> k4 = function.get(time + step, k4Argument, iterationNumber);

        return current.addition(
                k1.addition(k3.multiplication(4.0))
                        .addition(k4)
                        .multiplication(step)
                        .division(6.0)
        );
    }
}
