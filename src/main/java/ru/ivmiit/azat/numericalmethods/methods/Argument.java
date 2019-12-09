package ru.ivmiit.azat.numericalmethods.methods;

public interface Argument<T> {
    /**
     * Сложение
     * @param arg
     * @return
     */
    Argument<T> addition(Argument<T> arg);

    /**
     * Вычитание
     * @param arg
     * @return
     */
    Argument<T> subtraction(Argument<T> arg);

    /**
     * Умножение
     * @param arg
     * @return
     */
    Argument<T> multiplication(Argument<T> arg);

    /**
     * Деление
     * @param arg
     * @return
     */
    Argument<T> division(Argument<T> arg);

    /**
     * Сложение
     * @param arg
     * @return
     */
    Argument<T> addition(Double arg);

    /**
     * Вычитание
     * @param arg
     * @return
     */
    Argument<T> subtraction(Double arg);

    /**
     * Умножение
     * @param arg
     * @return
     */
    Argument<T> multiplication(Double arg);

    /**
     * Деление
     * @param arg
     * @return
     */
    Argument<T> division(Double arg);

    public T getValue();
}
