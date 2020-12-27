package game.GUI;

import game.models.GamerTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainFrame extends JFrame {
    //необходимые поля
    private JLabel welcome;
    private JButton btnPlay, btnRules, btnExit;
    private static JPanel panel, panel1, panel2;
    public GamerTableModel model =new GamerTableModel() ;
    public MainFrame() {
        super("Крестики-Нолики");
        gui_setting();
    }

    private void gui_setting() {
        //Основная панель
        panel = new JPanel();
        panel.setBackground(Color.decode("#96ba7d"));
        //менеджер расположения элементов
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        //Верхняя панель
        panel1 = new JPanel();
        //стиль текста
        Font font = new Font("Centure Gotic",Font.BOLD, 16);
        //текст
        welcome = new JLabel("Меню игры");
        //расположение текста
        welcome.setVerticalAlignment(JLabel.CENTER);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        //добавляем к тексту стиль
        welcome.setFont(font);
        //средняя панель
        panel2 = new JPanel();
        //менеджер расположения элементов
        panel2.setLayout(new GridLayout(3, 1));

        //создаем кнопку
        btnPlay = new JButton("Играть!");
        btnPlay.setFocusPainted(false);
        btnPlay.setBackground(Color.decode("#96baff"));
        btnPlay.setFont(font);
        //обработчик кнопки
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  dispose();
                SettingsFrame form = new SettingsFrame();

            }
        });
        //создаем кнопку
        btnRules = new JButton("Результаты игр");
        btnRules.setBackground(Color.decode("#96baff"));
        btnRules.setFont(font);
        //обработчик кнопки
        btnRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameResults form = new GameResults(model);

            }
        });
        //создаем кнопку
        btnExit = new JButton("Выход");
        btnExit.setBackground(Color.decode("#96baff"));
        btnExit.setFont(font);
        //обработчик кнопки
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //добавляем все кнопки на панель
        panel1.add(welcome);
        panel2.add(btnPlay);
        panel2.add(btnRules);
        panel2.add(btnExit);
        //занос панелей
        panel.add(panel1);
        panel.add(panel2);
        //делаем панель основной
        getContentPane().add(panel);
    }

    public static void main()  {
        //создаем главное окно
        MainFrame frame = new  MainFrame();
        //запрет на изменение окна
        frame.setResizable(false);
        //размер окна
        frame.setSize(300, 200);
        //расположение по центру
        frame.setLocationRelativeTo(null);
        //видимость
        frame.setVisible(true);
        //поведение окна на закрытие окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
