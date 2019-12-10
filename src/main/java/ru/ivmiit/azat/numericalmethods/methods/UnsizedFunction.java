package ru.ivmiit.azat.numericalmethods.methods;

public class UnsizedFunction implements Function<Row<Double>> {

    private double a_;

    public UnsizedFunction(double a_) {
        this.a_ = a_;

    }

    @Override
    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {

        Double X = value.getValue().getX();
        Double Y = value.getValue().getY();
        Double x = X - X * Y;
        Double y = -a_ * Y + X * Y;
        return new ArgumentImpl(
                new Row<>(x, y)
        );
    }
}