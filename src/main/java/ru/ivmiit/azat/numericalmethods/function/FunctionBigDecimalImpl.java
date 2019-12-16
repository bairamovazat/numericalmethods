package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentBigDecimalImpl;
import ru.ivmiit.azat.numericalmethods.model.Row;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionBigDecimalImpl implements Function<Row<BigDecimal>> {

    public Argument<Row<BigDecimal>> get(double time, Argument<Row<BigDecimal>> value, int iterationNumber) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        //-y/t
                        value.getValue().getY().multiply(BigDecimal.valueOf(-1)).divide(BigDecimal.valueOf(time), RoundingMode.HALF_UP),
                        //-x/t
                        value.getValue().getX().multiply(BigDecimal.valueOf(-1)).divide(BigDecimal.valueOf(time), RoundingMode.HALF_UP)
                )
        );
    }
}
