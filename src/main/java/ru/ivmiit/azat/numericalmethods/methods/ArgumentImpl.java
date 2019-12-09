package ru.ivmiit.azat.numericalmethods.methods;

import lombok.Getter;

public class ArgumentImpl implements Argument<Row<Double>> {
    @Getter
    public Row<Double> value;

    public ArgumentImpl(Row<Double> value) {
        this.value = value;
    }

    public Argument<Row<Double>> addition(Argument<Row<Double>> arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() + arg.getValue().getX(),
                        value.getY() + arg.getValue().getY()
                )
        );
    }

    public Argument<Row<Double>> subtraction(Argument<Row<Double>> arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() - arg.getValue().getX(),
                        value.getY() - arg.getValue().getY()
                )
        );
    }

    public Argument<Row<Double>> multiplication(Argument<Row<Double>> arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() * arg.getValue().getX(),
                        value.getY() * arg.getValue().getY()
                )
        );
    }

    public Argument<Row<Double>> division(Argument<Row<Double>> arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() / arg.getValue().getX(),
                        value.getY() / arg.getValue().getY()
                )
        );
    }

    public Argument<Row<Double>> addition(Double arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() + arg,
                        value.getY() + arg
                )
        );
    }

    public Argument<Row<Double>> subtraction(Double arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() - arg,
                        value.getY() - arg
                )
        );
    }

    public Argument<Row<Double>> multiplication(Double arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() * arg,
                        value.getY() * arg
                )
        );
    }

    public Argument<Row<Double>> division(Double arg) {
        return new ArgumentImpl(
                new Row<Double>(
                        value.getX() / arg,
                        value.getY() / arg
                )
        );
    }
}
