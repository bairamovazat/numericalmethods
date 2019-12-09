package ru.ivmiit.azat.numericalmethods.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Хищник
 */
public abstract class PredatorModel<T extends Number> implements AnimalModel<T> {
    /**
     * @param decline - Убыль хищников когда они голодные
     * @param reproduction - Воспроизводство хищников когда они сытые
     */
    public PredatorModel(T decline, T reproduction) {
        this.decline = decline;
        this.reproduction = reproduction;
    }

    /**
     * Убыль хищников когда они голодные
     */
    @Getter
    public T decline;

    /**
     * Воспроизводство хищников когда они сытые
     */
    @Getter
    private T reproduction;

    /**
     * Алиас для убыли хищников когда они голодные
     * @return - Убыль хищников когда они голодные
     */
    public T c() {
        return decline;
    }

    /**
     * Алиас для воспроизводства хищников когда они сытые
     * @return - Воспроизводство хищников когда они сытые
     */
    public T d() {
        return reproduction;
    }

}
