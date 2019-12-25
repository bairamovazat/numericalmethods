package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentImpl;
import ru.ivmiit.azat.numericalmethods.model.Row;

public class MyFunctionImpl implements Function<Row<Double>> {

    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {
        return new ArgumentImpl(
                new Row<Double>(
                        //-y/t
                        -value.getValue().getY() / time,
                        //-x/t
                        -value.getValue().getX() / time
                )
        );
    }
}
