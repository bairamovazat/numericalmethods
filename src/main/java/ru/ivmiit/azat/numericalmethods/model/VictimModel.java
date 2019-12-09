package ru.ivmiit.azat.numericalmethods.model;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Жертва
 */
public abstract class VictimModel<T extends Number> implements AnimalModel<T> {
    /**
     * @param birthRate - Рождаемость жертв
     * @param mortality - Смертность жертв при встрече с хищником
     */
    public VictimModel(T birthRate, T mortality) {
        this.birthRate = birthRate;
        this.mortality = mortality;
    }

    /**
     * Рождаемость жертв
     */
    @Getter
    private T birthRate;

    /**
     * Смертность жертв при встрече с хищником
     */
    @Getter
    private T mortality;

    /**
     * Алиас для рождаемости
     * @return - Рождаемость жертв
     */
    public T a() {
        return birthRate;
    }

    /**
     * Алиас для смертности жертв при встрече с хищником
     * @return - Смертность жертв при встрече с хищником
     */
    public T b() {
        return mortality;
    }
}
