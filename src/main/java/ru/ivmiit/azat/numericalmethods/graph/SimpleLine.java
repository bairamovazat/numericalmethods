package ru.ivmiit.azat.numericalmethods.graph;

import lombok.Data;
import org.jfree.data.xy.XYSeries;

import java.awt.*;
import java.util.List;

@Data
public class SimpleLine {
    private String name;
    private Paint color;

    private List<Double> x;
    private List<Double> y;
    private List<Double> time;

    public SimpleLine(String name, Paint color, List<Double> x, List<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Размерность аргументов(" + x.size() + ") не соответствует размерности значений(" + y.size() + ")");
        }
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public SimpleLine(String name, Paint color, List<Double> x, List<Double> y, List<Double> time) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Размерность аргументов(" + x.size() + ") не соответствует размерности значений(" + y.size() + ")");
        }
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public XYSeries toXYSeries() {
        XYSeries series = new XYSeries(name);
        for (int i = 0; i < x.size(); i++) {
            series.add(x.get(i), y.get(i));
        }
        return series;
    }

    /**
     * @param maxXToSeries - Больше этого числа не будет добавляться
     * @return
     */
    public XYSeries toXYSeries(Double maxXToSeries) {
        XYSeries series = new XYSeries(name, false);
        for (int i = 0; i < x.size(); i++) {
            if (maxXToSeries == null || time.get(i) <= maxXToSeries) {
                series.add(x.get(i), y.get(i));
            }
        }
        return series;
    }

}
