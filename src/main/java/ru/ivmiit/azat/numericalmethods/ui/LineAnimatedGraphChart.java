package ru.ivmiit.azat.numericalmethods.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import ru.ivmiit.azat.numericalmethods.graph.SimpleLine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LineAnimatedGraphChart extends AbstractLineGraphChart {
    private JPanel contentPane;
    private JSlider aParamSlider;
    private JPanel graphicPanel;
    private JLabel aValueTextField;
    private JLabel aValueDescriptionTextField;
    private JLabel maxTimeDescriptionTextField;
    private JLabel maxTimeTextField;
    private JSlider maxTimeSlider;
    private LinkedHashMap<Double, List<SimpleLine>> simpleLinesOfA;

    public LineAnimatedGraphChart(LinkedHashMap<Double, List<SimpleLine>> simpleLinesOfA, String xName, String yName, String title, boolean isLine) {
        super(new ArrayList<>(), xName, yName, title, isLine);
        this.simpleLinesOfA = simpleLinesOfA;

        setContentPane(contentPane);
        setModal(true);
        setTitle("Animated line chart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        aParamSlider.setMinimum(0);
        aParamSlider.setMaximum(simpleLinesOfA.size() - 1);
        aParamSlider.setValue(0);

        setChartPanel(createChartPanel());
        graphicPanel.add(getChartPanel());

        if (simpleLinesOfA.size() > 0) {
            setSimpleLineValue(0);
        }

        this.aParamSlider.addChangeListener(this::changeSimpleLine);
        this.maxTimeSlider.addChangeListener(this::changeMaxTime);
    }

    private void changeMaxTime(ChangeEvent changeEvent) {
        JSlider source = (JSlider) changeEvent.getSource();
        int elementNumber = source.getValue();
        Double maxTime = getSimpleLines().get(0).getTime().get(elementNumber);
        setMaxTime(maxTime);
        maxTimeTextField.setText(getMaxTime().toString());
        updateChartPanel();
    }

    private void updateChartPanel() {
        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        getChartPanel().setChart(chart);
    }

    private void changeSimpleLine(ChangeEvent changeEvent) {
        JSlider source = (JSlider) changeEvent.getSource();
        int elementNumber = source.getValue();
        setSimpleLineValue(elementNumber);
    }

    private void setSimpleLineValue(int index) {
        simpleLinesOfA.entrySet().stream().skip(index).findFirst().ifPresent(e -> {
            aValueTextField.setText(e.getKey().toString());
            setSimpleLines(e.getValue());
            maxTimeSlider.setMinimum(0);
            maxTimeSlider.setMaximum(e.getValue().get(0).getTime().size() - 1);
            setMaxTime(e.getValue().get(0).getTime().get(e.getValue().get(0).getTime().size() - 1));
            maxTimeSlider.setValue(maxTimeSlider.getMaximum());
            maxTimeTextField.setText(getMaxTime().toString());
            updateChartPanel();
        });
    }

}
