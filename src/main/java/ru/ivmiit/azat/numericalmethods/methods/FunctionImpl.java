package ru.ivmiit.azat.numericalmethods.methods;

public class FunctionImpl implements Function<Row<Double>> {

    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {
        return new ArgumentImpl(
                new Row<Double>(
                        //-y/t
                        -1 * value.getValue().getY() / time,
                        //-x/t
                        -1 * value.getValue().getX() / time
                )
        );
    }
}
