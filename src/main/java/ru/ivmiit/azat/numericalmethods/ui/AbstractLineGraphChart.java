package ru.ivmiit.azat.numericalmethods.ui;

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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class AbstractLineGraphChart extends JDialog {

    @Getter
    @Setter
    private ChartPanel chartPanel;

    @Setter
    @Getter
    private List<SimpleLine> simpleLines;

    private final String xName;
    private final String yName;
    private final String title;

    @Getter
    @Setter
    private Double maxTime = null;

    @Getter
    @Setter
    private boolean isLine;

    protected AbstractLineGraphChart(List<SimpleLine> simpleLines, String xName, String yName, String title, boolean isLine){
        this.simpleLines = simpleLines;
        this.xName = xName;
        this.yName = yName;
        this.title = title;
        this.isLine = isLine;
    }

    protected ChartPanel createChartPanel() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        this.chartPanel = chartPanel;
        return chartPanel;
    }

    protected XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        this.simpleLines.forEach(e -> dataset.addSeries(e.toXYSeries(maxTime)));
        return dataset;
    }

    protected JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYStepAreaChart(
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

        if (!this.isLine) {
            XYDotRenderer renderer = new XYDotRenderer();
            for (int i = 0; i < simpleLines.size(); i++) {
                renderer.setSeriesPaint(i, simpleLines.get(i).getColor());
                renderer.setSeriesStroke(i, new BasicStroke(1f));
                renderer.setDotHeight(1);
                renderer.setDotWidth(1);
            }
            plot.setRenderer(renderer);
        } else {
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            for (int i = 0; i < simpleLines.size(); i++) {
                renderer.setSeriesPaint(i, simpleLines.get(i).getColor());
                renderer.setSeriesStroke(i, new BasicStroke(2f));
                renderer.setSeriesStroke(i, new BasicStroke(2f));
                Rectangle rect = new Rectangle(1, 1);
                renderer.setSeriesShape(i, rect);
            }
            plot.setRenderer(renderer);
        }


        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        plot.setRangeZeroBaselineVisible(true);
        plot.setRangeZeroBaselineStroke(new BasicStroke(1.0f));
        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(
                new TextTitle(title,
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
}
