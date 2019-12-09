package ru.ivmiit.azat.numericalmethods.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class LineGraph extends JFrame {

    private List<SimpleLine> simpleLines;

    private String xName;
    private String yName;
    private String title;

    public LineGraph(List<SimpleLine> simpleLines, String xName, String yName, String title) {
        this.simpleLines = simpleLines;
        this.xName = xName;
        this.yName = yName;
        this.title = title;
        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        this.simpleLines.forEach(e -> dataset.addSeries(e.toXYSeries()));
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xName,
                yName,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for(int i = 0; i < simpleLines.size(); i++) {
            renderer.setSeriesPaint(i, simpleLines.get(i).getColor());
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(
                new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}
