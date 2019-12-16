package ru.ivmiit.azat.numericalmethods;

import lombok.Getter;
import ru.ivmiit.azat.numericalmethods.model.Argument;
import ru.ivmiit.azat.numericalmethods.methods.CauchysProblemMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Моде́ль Ло́тки — Вольте́рры - модель взаимодействия двух видов типа «хищник — жертва»
 */
public class ModelTraysVolterra<T> implements CauchysProblem {
    private CauchysProblemMethod<T> method;
    private Argument<T> zeroState;

    @Getter
    private List<T> values = new ArrayList<T>();
    @Getter
    private List<Double> time = new ArrayList<Double>();

    public ModelTraysVolterra(CauchysProblemMethod<T> method, Argument<T> zeroState) {
        this.method = method;
        this.zeroState = zeroState;
    }

    /**
     * @param start          - начальная точка
     * @param iterationCount - кол-во итераций (нумерация начинается с 0 и заканчивается iterationCount)
     * @param step           - шаг
     * @return
     */
    public Argument<T> calculate(double start, int iterationCount, double step) {
        values = new ArrayList<T>();

        double currentTime = start;
        Argument<T> current = zeroState;

        values.add(current.getValue());
        time.add(currentTime);


        for (int i = 1; i < iterationCount; i++) {
            current = method.next(currentTime, current, step, i);
            values.add(current.getValue());
            currentTime += step;
            time.add(currentTime);
        }
        return current;
    }

    public Argument<T> calculate(double start, double end, double step) {
        values = new ArrayList<T>();

        double currentTime = start;
        Argument<T> current = zeroState;

        values.add(current.getValue());
        time.add(currentTime);

        int iteration = 0;
        while (currentTime <= end) {
            current = method.next(currentTime, current, step, ++iteration);
            values.add(current.getValue());
            currentTime += step;
            time.add(currentTime);
        }
        return current;
    }

    //    /**
//     * @param predator - Хищник
//     * @param victim - Жертва
//     */
//    public ModelTraysVolterra(PredatorModel predator, VictimModel victim) {
//        this.predator = predator;
//        this.victim = victim;
//    }

//    /**
//     * Хищник
//     */
//    private PredatorModel predator;
//
//    /**
//     * Жертва
//     */
//    private VictimModel victim;
}
