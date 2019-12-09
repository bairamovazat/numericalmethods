package ru.ivmiit.azat.numericalmethods.methods;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArgumentBigDecimalImpl implements Argument<Row<BigDecimal>> {
    @Getter
    public Row<BigDecimal> value;

    public ArgumentBigDecimalImpl(Row<BigDecimal> value) {
        this.value = value;
    }

    public Argument<Row<BigDecimal>> addition(Argument<Row<BigDecimal>> arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().add(arg.getValue().getX()),
                        value.getY().add(arg.getValue().getY())
                )
        );
    }

    public Argument<Row<BigDecimal>> subtraction(Argument<Row<BigDecimal>> arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().subtract(arg.getValue().getX()),
                        value.getY().subtract(arg.getValue().getY())
                )
        );
    }

    public Argument<Row<BigDecimal>> multiplication(Argument<Row<BigDecimal>> arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().multiply(arg.getValue().getX()),
                        value.getY().multiply(arg.getValue().getY())
                )
        );
    }

    public Argument<Row<BigDecimal>> division(Argument<Row<BigDecimal>> arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().divide(arg.getValue().getX(), RoundingMode.HALF_UP),
                        value.getY().divide(arg.getValue().getY(), RoundingMode.HALF_UP)
                )
        );
    }

    public Argument<Row<BigDecimal>> addition(Double arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().add(BigDecimal.valueOf(arg)),
                        value.getY().add(BigDecimal.valueOf(arg))
                )
        );
    }

    public Argument<Row<BigDecimal>> subtraction(Double arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().subtract(BigDecimal.valueOf(arg)),
                        value.getY().subtract(BigDecimal.valueOf(arg))
                )
        );
    }

    public Argument<Row<BigDecimal>> multiplication(Double arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().multiply(BigDecimal.valueOf(arg)),
                        value.getY().multiply(BigDecimal.valueOf(arg))
                )
        );
    }

    public Argument<Row<BigDecimal>> division(Double arg) {
        return new ArgumentBigDecimalImpl(
                new Row<BigDecimal>(
                        value.getX().divide(BigDecimal.valueOf(arg), RoundingMode.HALF_UP),
                        value.getY().divide(BigDecimal.valueOf(arg), RoundingMode.HALF_UP)
                )
        );
    }
}
