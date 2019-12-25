package ru.ivmiit.azat.numericalmethods.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import lombok.Getter;
import ru.ivmiit.azat.numericalmethods.NumericalMethodsDoubleForChart;
import ru.ivmiit.azat.numericalmethods.NumericalMethodsForChart;
import ru.ivmiit.azat.numericalmethods.model.SolverType;
import ru.ivmiit.azat.numericalmethods.model.TaskType;

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

    private JRadioButton taskNikitaRadioButton;
    private JRadioButton taskAzatRadioButton;
    private JRadioButton solverCheskinoRadioButton;
    private JRadioButton solverRungeKuttaRadioButton;

    @Getter
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
        this.taskAzatRadioButton.addActionListener(actionEvent -> {
            getMethodsForChart().setTaskType(TaskType.AZAT);

        });
        this.taskNikitaRadioButton.addActionListener(actionEvent -> {
            getMethodsForChart().setTaskType(TaskType.NIKITA);
        });

        this.solverCheskinoRadioButton.addActionListener(actionEvent -> {
            getMethodsForChart().setSolverType(SolverType.CHESKINO);

        });
        this.solverRungeKuttaRadioButton.addActionListener(actionEvent -> {
            getMethodsForChart().setSolverType(SolverType.RUNGE_KUTTA);

        });
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

}
