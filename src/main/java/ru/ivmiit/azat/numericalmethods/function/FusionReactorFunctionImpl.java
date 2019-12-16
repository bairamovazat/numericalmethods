package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentImpl;
import ru.ivmiit.azat.numericalmethods.model.Row;

public class FusionReactorFunctionImpl implements Function<Row<Double>> {

    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {
        Double x = value.getValue().getX();
        Double y = value.getValue().getY();

        return new ArgumentImpl(
                new Row<Double>(
                        //-y/t
                        -y + x * ((x * x) + (y * y) - 1),
                        //-x/t
                        x + y * ((x * x) + (y * y) - 1)
                )
        );
    }
}
