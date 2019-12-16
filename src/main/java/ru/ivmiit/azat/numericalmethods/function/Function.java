package ru.ivmiit.azat.numericalmethods.function;

import ru.ivmiit.azat.numericalmethods.model.Argument;

/**
 * Функция F(tn, yn-1, n)
 */
public interface Function<T> {
    /**
     * @param time - tn - время
     * @param value - yn-1 - предыдущее значение функции
     * @param iterationNumber - n
     * @return -
     */
    public Argument<T> get(double time, Argument<T> value, int iterationNumber);

}
