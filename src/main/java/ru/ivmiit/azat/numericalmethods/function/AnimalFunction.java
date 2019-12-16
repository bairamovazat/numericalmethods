package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Row;
import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.model.ArgumentImpl;
import ru.ivmiit.azat.numericalmethods.model.PredatorModel;
import ru.ivmiit.azat.numericalmethods.model.VictimModel;

public class AnimalFunction implements Function<Row<Double>> {
    private PredatorModel<Double> predatorModel;
    private VictimModel<Double> victimModel;

    public AnimalFunction(PredatorModel<Double> predatorModel, VictimModel<Double> victimModel) {
        this.predatorModel = predatorModel;
        this.victimModel = victimModel;
    }

    @Override
    public Argument<Row<Double>> get(double time, Argument<Row<Double>> value, int iterationNumber) {
        Double a = victimModel.a();
        Double b = victimModel.b();
        Double d = predatorModel.d();
        Double c = predatorModel.c();

        Double X = (d / a) * value.getValue().getX();
        Double Y = (b / a) * value.getValue().getY();
        Double t = a * time;
        Double a_ = c / a;

        Double x = X - X * Y ;
        Double y = -a_ * Y + X * Y;

        return new ArgumentImpl(
                new Row<>(x, y)
        );
    }
}
