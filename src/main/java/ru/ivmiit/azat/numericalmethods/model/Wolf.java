package ru.ivmiit.azat.numericalmethods.model;

/**
 * Волк
 */
public class Wolf<T extends Number> extends PredatorModel<T> {
    /**
     * @param decline      - Убыль хищников когда они голодные
     * @param reproduction - Воспроизводство хищников когда они сытые
     */
    public Wolf(T decline, T reproduction) {
        super(decline, reproduction);
    }
}
