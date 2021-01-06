package game.GUI;

import game.models.GamerTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameResults extends JFrame {
    //необходимые поля
    private JLabel welcome;
    private JButton bt1;
    private JPanel panel, panel1, panel2, panel3;
    private JScrollPane scrollpane;
    private JTable table;
    public GamerTableModel model;

    public GameResults(GamerTableModel model) {
        super("Результаты");
        this.model=model;
        gui_setting();
        //запрет на изменение окна
        setResizable(false);
        //размер окна
        setSize(250, 300);
        //расположение по центру
        setLocationRelativeTo(null);
        //видимость
        setVisible(true);
        //поведение окна на закрытие окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void gui_setting() {
        //Основная панель
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Верхняя панель
        panel1 = new JPanel();
        //Текст
        welcome = new JLabel("Результаты игр");
        Font font = new Font("Centure Gotic",Font.BOLD, 16);
        //по центру панели
        welcome.setVerticalAlignment(JLabel.TOP);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setFont(font);
        //Занос текста
        panel1.add(welcome);
        //средняя панель
        panel2 = new JPanel();
        //размер устанавливаем
        panel2.setSize(450, 150);
        //менеджер расположения
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        //создаем таблицу
        table = new JTable();
        //передаем в таблицу модель
        table.setModel(model);
        //создаем скроллпэйн
        scrollpane = new JScrollPane(table);
        //добавляем скроллпэйн на панель
        panel2.add(scrollpane);
        //нижняя панель
        panel3 = new JPanel();
        //размеер
        panel3.setSize(300, 50);
        //менеджер расположения элементов
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        //создаем кнопку
        bt1 = new JButton("Ок");
        bt1.setFont(font);
        bt1.setBackground(Color.decode("#96baff"));
        //обработчик кнопки
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //добавляем все кнопки на панель
        panel3.add(bt1);
        //занос панелей
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        //делаем панель основной
        getContentPane().add(panel);

    }

}
