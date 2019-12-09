package ru.ivmiit.azat.numericalmethods.model;

/**
 * Овца
 */
public class Sheep<T extends Number> extends VictimModel<T> {
    /**
     * @param birthRate - Рождаемость жертв
     * @param mortality - Смертность жертв при встрече с хищником
     */
    public Sheep(T birthRate, T mortality) {
        super(birthRate, mortality);
    }
}
