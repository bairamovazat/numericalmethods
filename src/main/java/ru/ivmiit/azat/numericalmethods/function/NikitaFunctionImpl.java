package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentImpl;
import ru.ivmiit.azat.numericalmethods.model.Row;

public class NikitaFunctionImpl implements Function<Row<Double>> {

    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {
        return new ArgumentImpl(
                new Row<Double>(
                        (value.getValue().getX() / (2 + 2 * time)) - 2 * time * value.getValue().getY(),
                        (value.getValue().getY() / (2 + 2 * time)) + 2 * time * value.getValue().getX()
                )
        );
    }
}
