package ru.ivmiit.azat.numericalmethods;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame(){
        super("Система типа Хищник-Жертва. Модель Вольтерра. Метод Ческино");
        this.setBounds(100,100,250,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,1,2,2));
    }
}
