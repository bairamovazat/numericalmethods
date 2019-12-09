package ru.ivmiit.azat.numericalmethods.utils;

import ru.ivmiit.azat.numericalmethods.ModelTraysVolterra;
import ru.ivmiit.azat.numericalmethods.methods.Row;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ErrorUtils {
    public static double getMaxXError(ModelTraysVolterra<Row<Double>> modelTraysVolterra, Function<Double, Double> xOfTFunction) {
       return getMaxError(modelTraysVolterra, xOfTFunction, Row::getX);
    }

    public static double getMaxYError(ModelTraysVolterra<Row<Double>> modelTraysVolterra, Function<Double, Double> yOfTFunction) {
        return getMaxError(modelTraysVolterra, yOfTFunction, Row::getY);
    }

    private static double getMaxError(ModelTraysVolterra<Row<Double>> modelTraysVolterra, Function<Double, Double> tryValueFunction, Function<Row<Double>, Double> getValue) {
        List<Double> errors = new ArrayList<>();
        for (int i = 0; i < modelTraysVolterra.getTime().size(); i++) {
            errors.add(
                    Math.abs(
                            getValue.apply(modelTraysVolterra.getValues().get(i)) - tryValueFunction.apply(modelTraysVolterra.getTime().get(i))
                    )
            );
        }
        return errors.stream().mapToDouble(e -> e).max().orElseThrow(() -> new IllegalArgumentException("Не найден максимальный элемент"));
    }

    public static double getMaxXErrorBigDecimal(ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra, Function<Double, Double> xOfTFunction) {
        return getMaxErrorBigDecimal(modelTraysVolterra, xOfTFunction, e -> e.getX().doubleValue());
    }

    public static double getMaxYErrorBigDecimal(ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra, Function<Double, Double> yOfTFunction) {
        return getMaxErrorBigDecimal(modelTraysVolterra, yOfTFunction, e -> e.getY().doubleValue());
    }

    private static double getMaxErrorBigDecimal(ModelTraysVolterra<Row<BigDecimal>> modelTraysVolterra, Function<Double, Double> tryValueFunction, Function<Row<BigDecimal>, Double> getValue) {
        List<Double> errors = new ArrayList<>();
        for (int i = 0; i < modelTraysVolterra.getTime().size(); i++) {
            errors.add(
                    Math.abs(
                            getValue.apply(modelTraysVolterra.getValues().get(i)) - tryValueFunction.apply(modelTraysVolterra.getTime().get(i))
                    )
            );
        }
        return errors.stream().mapToDouble(e -> e).max().orElseThrow(() -> new IllegalArgumentException("Не найден максимальный элемент"));
    }
}
