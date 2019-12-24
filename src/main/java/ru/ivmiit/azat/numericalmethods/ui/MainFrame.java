package ru.ivmiit.azat.numericalmethods.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ru.ivmiit.azat.numericalmethods.NumericalMethodsDoubleForChart;
import ru.ivmiit.azat.numericalmethods.NumericalMethodsForChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    //Строится график сравнения полученного решения с идеальным
    private JButton createComparisonChartButton;
    //Начало времени t
    private JTextField chartStartTimeTextField;
    //Конец времени t
    private JTextField chartEndTimeTextField;
    //Количество итераций
    private JTextField chartIterationCountTextField;

    //Строится график ошибки
    private JButton createErrorChartButton;
    //Строится график ошибки k порядка (по умолчанию 4)
    private JButton createFourthOrderErrorGraphButton;
    //Начало времени t
    private JTextField errorChartStartTimeTextField;
    //Конец времени t
    private JTextField errorChartEndTimeTextField;
    //Начальный шаг h (ε)
    private JTextField errorChartStartHValueTextField;
    //Конечный шаг h (ε)
    private JTextField errorChartEndHValueTextField;
    //Шаг h (ε)
    private JTextField errorChartHValueStepTextField;
    //Степень k
    private JTextField errorChartErrorPowerTextField;

    //Строится график зависимости x от y в зафисимости от параметра а
    private JButton createDependencyChartButton;
    //Начало времени t
    private JTextField parameterChartStartTimeTextField;
    //Конец времени t
    private JTextField parameterChartEndTimeTextField;
    //Начальное δ
    private JTextField parameterChartStartAValueTextField;
    //Конечное δ (δ нач < δ кон)
    private JTextField parameterChartEndAValueTextField;
    //Шаг δ
    private JTextField parameterChartStepAValueTextField;
    //Хищников в начале (X)
    private JTextField parameterChartXStartValueTextField;
    //Жертв в начале (Y)
    private JTextField parameterChartYStartValueTextField;
    //Шаг h (ε)
    private JTextField parameterChartStepValueTextField;
    private JButton createDependencyChartXButton;
    private JButton createDependencyChartYButton;

    private NumericalMethodsForChart methodsForChart;

    public MainFrame() {
        setContentPane(contentPane);
        setTitle("Система типа хищник-жертва. Модель Вольтерра");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.createComparisonChartButton.addActionListener(this::createComparisonChart);
        this.createErrorChartButton.addActionListener(this::createErrorChart);
        this.createFourthOrderErrorGraphButton.addActionListener(this::createFourthOrderErrorGraph);
        this.createDependencyChartButton.addActionListener(this::createDependencyChart);
        this.createDependencyChartXButton.addActionListener(this::createDependencyChartX);
        this.createDependencyChartYButton.addActionListener(this::createDependencyChartY);

        methodsForChart = new NumericalMethodsDoubleForChart();
    }

    //Строится график сравнения полученного решения с идеальным
    private void createComparisonChart(ActionEvent actionEvent) {
        //Начало времени t
        String chartStartTimeText = chartStartTimeTextField.getText();
        //Конец времени t
        String chartEndTimeText = chartEndTimeTextField.getText();
        //Количество итераций
        String chartIterationCountText = chartIterationCountTextField.getText();

        //Начало времени t
        Double chartStartTime = Double.parseDouble(chartStartTimeText);
        //Конец времени t
        Double chartEndTime = Double.parseDouble(chartEndTimeText);
        //Количество итераций
        Double chartIterationCount = Double.parseDouble(chartIterationCountText);

        methodsForChart.testTaskCalculateAndShowDialog(chartStartTime, chartEndTime, chartIterationCount.intValue(), this);

    }


    //Строится график ошибки
    private void createErrorChart(ActionEvent actionEvent) {
        //Начало времени t
        String errorChartStartTimeText = errorChartStartTimeTextField.getText();
        //Конец времени t
        String errorChartEndTimeText = errorChartEndTimeTextField.getText();
        //Начальный шаг h (ε)
        String errorChartStartHValueText = errorChartStartHValueTextField.getText();
        //Конечный шаг h (ε)
        String errorChartEndHValueText = errorChartEndHValueTextField.getText();
        //Шаг h (ε)
        String errorChartHValueStepText = errorChartHValueStepTextField.getText();
        //Степень k
        String errorChartErrorPowerText = errorChartErrorPowerTextField.getText();
        Double errorChartStartTime = Double.parseDouble(errorChartStartTimeText);
        Double errorChartEndTime = Double.parseDouble(errorChartEndTimeText);
        Double errorChartStartHValue = Double.parseDouble(errorChartStartHValueText);
        Double errorChartEndHValue = Double.parseDouble(errorChartEndHValueText);
        Double errorChartHValueStep = Double.parseDouble(errorChartHValueStepText);

        methodsForChart.errorsEAndShowDialog(errorChartStartTime, errorChartEndTime, errorChartStartHValue, errorChartEndHValue, (e) -> e + errorChartHValueStep, this);
    }

    //Строится график ошибки k порядка (по умолчанию 4)
    private void createFourthOrderErrorGraph(ActionEvent actionEvent) {
        //Начало времени t
        String errorChartStartTimeText = errorChartStartTimeTextField.getText();
        //Конец времени t
        String errorChartEndTimeText = errorChartEndTimeTextField.getText();
        //Начальный шаг h (ε)
        String errorChartStartHValueText = errorChartStartHValueTextField.getText();
        //Конечный шаг h (ε)
        String errorChartEndHValueText = errorChartEndHValueTextField.getText();
        //Шаг h (ε)
        String errorChartHValueStepText = errorChartHValueStepTextField.getText();
        //Степень k
        String errorChartErrorPowerText = errorChartErrorPowerTextField.getText();
        Double errorChartStartTime = Double.parseDouble(errorChartStartTimeText);
        Double errorChartEndTime = Double.parseDouble(errorChartEndTimeText);
        Double errorChartStartHValue = Double.parseDouble(errorChartStartHValueText);
        Double errorChartEndHValue = Double.parseDouble(errorChartEndHValueText);
        Double errorChartHValueStep = Double.parseDouble(errorChartHValueStepText);
        Double errorChartErrorPower = Double.parseDouble(errorChartErrorPowerText);

        methodsForChart.errorsEh4AndShowDialog(errorChartStartTime, errorChartEndTime, errorChartStartHValue, errorChartEndHValue, errorChartErrorPower.intValue(), (e) -> e + errorChartHValueStep, this);
    }

    //Строится график зависимости x от y в зафисимости от параметра а
    private void createDependencyChart(ActionEvent actionEvent) {
        //Начало времени t
        String parameterChartStartTimeText = parameterChartStartTimeTextField.getText();
        //Конец времени t
        String parameterChartEndTimeText = parameterChartEndTimeTextField.getText();
        //Начальное δ
        String parameterChartStartAValueText = parameterChartStartAValueTextField.getText();
        //Конечное δ (δ нач < δ кон)
        String parameterChartEndAValueText = parameterChartEndAValueTextField.getText();
        //Шаг δ
        String parameterChartStepAValueText = parameterChartStepAValueTextField.getText();
        //Хищников в начале (X)
        String parameterChartXStartValueText = parameterChartXStartValueTextField.getText();
        //Жертв в начале (Y)
        String parameterChartYStartValueText = parameterChartYStartValueTextField.getText();
        //Шаг h (ε)
        String parameterChartStepValueText = parameterChartStepValueTextField.getText();

        Double parameterChartStartTime = Double.parseDouble(parameterChartStartTimeText);
        Double parameterChartEndTime = Double.parseDouble(parameterChartEndTimeText);
        Double parameterChartStartAValue = Double.parseDouble(parameterChartStartAValueText);
        Double parameterChartEndAValue = Double.parseDouble(parameterChartEndAValueText);
        Double parameterChartStepAValue = Double.parseDouble(parameterChartStepAValueText);
        Double parameterChartXStartValue = Double.parseDouble(parameterChartXStartValueText);
        Double parameterChartYStartValue = Double.parseDouble(parameterChartYStartValueText);
        Double parameterChartStepValue = Double.parseDouble(parameterChartStepValueText);

        methodsForChart.studyScheduleAndShowDialog(
                parameterChartStartAValue, parameterChartEndAValue, parameterChartStepAValue,
                parameterChartXStartValue, parameterChartYStartValue,
                parameterChartStartTime, parameterChartEndTime, parameterChartStepValue,
                this
        );

    }

    private void createDependencyChartY(ActionEvent actionEvent) {
        //Начало времени t
        String parameterChartStartTimeText = parameterChartStartTimeTextField.getText();
        //Конец времени t
        String parameterChartEndTimeText = parameterChartEndTimeTextField.getText();
        //Начальное δ
        String parameterChartStartAValueText = parameterChartStartAValueTextField.getText();
        //Конечное δ (δ нач < δ кон)
        String parameterChartEndAValueText = parameterChartEndAValueTextField.getText();
        //Шаг δ
        String parameterChartStepAValueText = parameterChartStepAValueTextField.getText();
        //Хищников в начале (X)
        String parameterChartXStartValueText = parameterChartXStartValueTextField.getText();
        //Жертв в начале (Y)
        String parameterChartYStartValueText = parameterChartYStartValueTextField.getText();
        //Шаг h (ε)
        String parameterChartStepValueText = parameterChartStepValueTextField.getText();

        Double parameterChartStartTime = Double.parseDouble(parameterChartStartTimeText);
        Double parameterChartEndTime = Double.parseDouble(parameterChartEndTimeText);
        Double parameterChartStartAValue = Double.parseDouble(parameterChartStartAValueText);
        Double parameterChartEndAValue = Double.parseDouble(parameterChartEndAValueText);
        Double parameterChartStepAValue = Double.parseDouble(parameterChartStepAValueText);
        Double parameterChartXStartValue = Double.parseDouble(parameterChartXStartValueText);
        Double parameterChartYStartValue = Double.parseDouble(parameterChartYStartValueText);
        Double parameterChartStepValue = Double.parseDouble(parameterChartStepValueText);

        methodsForChart.studyScheduleYAndShowDialog(
                parameterChartStartAValue, parameterChartEndAValue, parameterChartStepAValue,
                parameterChartXStartValue, parameterChartYStartValue,
                parameterChartStartTime, parameterChartEndTime, parameterChartStepValue,
                this
        );
    }

    private void createDependencyChartX(ActionEvent actionEvent) {
        //Начало времени t
        String parameterChartStartTimeText = parameterChartStartTimeTextField.getText();
        //Конец времени t
        String parameterChartEndTimeText = parameterChartEndTimeTextField.getText();
        //Начальное δ
        String parameterChartStartAValueText = parameterChartStartAValueTextField.getText();
        //Конечное δ (δ нач < δ кон)
        String parameterChartEndAValueText = parameterChartEndAValueTextField.getText();
        //Шаг δ
        String parameterChartStepAValueText = parameterChartStepAValueTextField.getText();
        //Хищников в начале (X)
        String parameterChartXStartValueText = parameterChartXStartValueTextField.getText();
        //Жертв в начале (Y)
        String parameterChartYStartValueText = parameterChartYStartValueTextField.getText();
        //Шаг h (ε)
        String parameterChartStepValueText = parameterChartStepValueTextField.getText();

        Double parameterChartStartTime = Double.parseDouble(parameterChartStartTimeText);
        Double parameterChartEndTime = Double.parseDouble(parameterChartEndTimeText);
        Double parameterChartStartAValue = Double.parseDouble(parameterChartStartAValueText);
        Double parameterChartEndAValue = Double.parseDouble(parameterChartEndAValueText);
        Double parameterChartStepAValue = Double.parseDouble(parameterChartStepAValueText);
        Double parameterChartXStartValue = Double.parseDouble(parameterChartXStartValueText);
        Double parameterChartYStartValue = Double.parseDouble(parameterChartYStartValueText);
        Double parameterChartStepValue = Double.parseDouble(parameterChartStepValueText);

        methodsForChart.studyScheduleXAndShowDialog(
                parameterChartStartAValue, parameterChartEndAValue, parameterChartStepAValue,
                parameterChartXStartValue, parameterChartYStartValue,
                parameterChartStartTime, parameterChartEndTime, parameterChartStepValue,
                this
        );
    }


    public static void main(String[] args) {
        MainFrame dialog = new MainFrame();
        dialog.pack();
        dialog.setVisible(true);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(7, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-2763307));
        contentPane.add(panel1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Иследование параметра δ");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 2, new Insets(5, 5, 5, 5), -1, -1));
        contentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        final JLabel label2 = new JLabel();
        label2.setText("Начало времени t");
        panel2.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Конец времени t");
        panel2.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chartIterationCountTextField = new JTextField();
        chartIterationCountTextField.setText("100");
        panel2.add(chartIterationCountTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        chartEndTimeTextField = new JTextField();
        chartEndTimeTextField.setText("-5");
        panel2.add(chartEndTimeTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        chartStartTimeTextField = new JTextField();
        chartStartTimeTextField.setText("-10");
        panel2.add(chartStartTimeTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Количество итераций");
        panel2.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Действия");
        panel2.add(label5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        createComparisonChartButton = new JButton();
        createComparisonChartButton.setText("Построить график сравнения");
        panel3.add(createComparisonChartButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-2763307));
        contentPane.add(panel4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(7, 2, new Insets(5, 5, 5, 5), -1, -1));
        contentPane.add(panel5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        final JLabel label6 = new JLabel();
        label6.setText("Начало времени t");
        panel5.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Конец времени t");
        panel5.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorChartStartHValueTextField = new JTextField();
        errorChartStartHValueTextField.setText("1");
        panel5.add(errorChartStartHValueTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        errorChartEndTimeTextField = new JTextField();
        errorChartEndTimeTextField.setText("-5");
        panel5.add(errorChartEndTimeTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        errorChartStartTimeTextField = new JTextField();
        errorChartStartTimeTextField.setText("-10");
        panel5.add(errorChartStartTimeTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        errorChartEndHValueTextField = new JTextField();
        errorChartEndHValueTextField.setText("0.001");
        panel5.add(errorChartEndHValueTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Начальный шаг h (ε)");
        panel5.add(label8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Конечный шаг h (ε)");
        panel5.add(label9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Шаг h (ε)");
        panel5.add(label10, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorChartHValueStepTextField = new JTextField();
        errorChartHValueStepTextField.setText("-0.05");
        panel5.add(errorChartHValueStepTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Действия");
        panel5.add(label11, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        createErrorChartButton = new JButton();
        createErrorChartButton.setText("Построить график e");
        panel6.add(createErrorChartButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createFourthOrderErrorGraphButton = new JButton();
        createFourthOrderErrorGraphButton.setText("Построить график e/h^k");
        panel6.add(createFourthOrderErrorGraphButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorChartErrorPowerTextField = new JTextField();
        errorChartErrorPowerTextField.setText("4");
        panel5.add(errorChartErrorPowerTextField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Степень k");
        panel5.add(label12, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(11, 2, new Insets(5, 5, 5, 5), -1, -1));
        contentPane.add(panel7, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        final JLabel label13 = new JLabel();
        label13.setText("Начало времени t");
        panel7.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Конец времени t");
        panel7.add(label14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parameterChartStartAValueTextField = new JTextField();
        parameterChartStartAValueTextField.setText("1");
        panel7.add(parameterChartStartAValueTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        parameterChartEndTimeTextField = new JTextField();
        parameterChartEndTimeTextField.setText("60");
        panel7.add(parameterChartEndTimeTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        parameterChartStartTimeTextField = new JTextField();
        parameterChartStartTimeTextField.setText("1");
        panel7.add(parameterChartStartTimeTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        parameterChartEndAValueTextField = new JTextField();
        parameterChartEndAValueTextField.setText("0.1");
        panel7.add(parameterChartEndAValueTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Начальное δ");
        panel7.add(label15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Конечное δ (δ нач < δ кон)");
        panel7.add(label16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parameterChartXStartValueTextField = new JTextField();
        parameterChartXStartValueTextField.setText("1");
        panel7.add(parameterChartXStartValueTextField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Жертв в начале (X)");
        panel7.add(label17, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parameterChartYStartValueTextField = new JTextField();
        parameterChartYStartValueTextField.setText("1");
        panel7.add(parameterChartYStartValueTextField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Хищников в начале (Y)");
        panel7.add(label18, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Шаг δ");
        panel7.add(label19, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parameterChartStepAValueTextField = new JTextField();
        parameterChartStepAValueTextField.setText("-0.05");
        panel7.add(parameterChartStepAValueTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Шаг h (ε)");
        panel7.add(label20, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parameterChartStepValueTextField = new JTextField();
        parameterChartStepValueTextField.setText("0.05");
        panel7.add(parameterChartStepValueTextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Действия");
        panel7.add(label21, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDependencyChartButton = new JButton();
        createDependencyChartButton.setText("Построить график зависимости X/Y от δ");
        panel7.add(createDependencyChartButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDependencyChartXButton = new JButton();
        createDependencyChartXButton.setText("Построить график зависимости X/t от δ");
        panel7.add(createDependencyChartXButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDependencyChartYButton = new JButton();
        createDependencyChartYButton.setText("Построить график зависимости Y/t от δ");
        panel7.add(createDependencyChartYButton, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-2763307));
        contentPane.add(panel8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Построить график ошибки");
        panel8.add(label22, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setBackground(new Color(-2763307));
        contentPane.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("Построить график");
        panel9.add(label23, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
