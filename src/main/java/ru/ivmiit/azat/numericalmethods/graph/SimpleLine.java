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

    public SimpleLine(String name, Paint color,  List<Double> x, List<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Размерность аргументов(" + x.size() + ") не соответствует размерности значений(" + y.size() + ")");
        }
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public XYSeries toXYSeries() {
        XYSeries series = new XYSeries(name);
        for(int i = 0; i < x.size(); i++) {
            series.add(x.get(i), y.get(i));
        }
        return series;
    }

}
