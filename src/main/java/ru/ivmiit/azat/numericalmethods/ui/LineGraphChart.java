package ru.ivmiit.azat.numericalmethods.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import lombok.Getter;
import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import ru.ivmiit.azat.numericalmethods.graph.SimpleLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LineGraphChart extends AbstractLineGraphChart {
    private JPanel contentPane;


    public LineGraphChart(List<SimpleLine> simpleLines, String xName, String yName, String title, boolean isLine) {
        super(simpleLines, xName, yName, title, isLine);

        setContentPane(contentPane);
        setModal(true);
        setTitle("Line chart");
//        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setChartPanel(createChartPanel());
        contentPane.add(getChartPanel());
    }

}
