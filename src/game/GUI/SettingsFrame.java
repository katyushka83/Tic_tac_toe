package game.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    //необходимые поля
    private JLabel welcome;
    private JButton btnPlay, cancel;
    private static JPanel panel, panel1, panel2, panel3;
    private JTextField txtname1, txtname2, txtboardsize;
    private JLabel name1, name2, boardsize;

    public SettingsFrame() {
        super("Настройка игры");
        gui_setting();
        //запрет на изменение окна
        setResizable(false);
        //устанавливаем размеры окна
        setSize(350, 250);
        //размещаем окно по центру
        setLocationRelativeTo(null);
        //видимость окна
        setVisible(true);
        //поведение окна на закрытие окна
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void gui_setting() {
        //Основная панель
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        //Верхняя панель
        panel1 = new JPanel();
        //Текст
        welcome = new JLabel("Настройка игры");
        Font font = new Font("Centure Gotic", Font.BOLD, 16);
        //по центру панели
        welcome.setVerticalAlignment(JLabel.TOP);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setFont(font);
        //Занос текста
        panel1.add(welcome);

        //средняя панель
        panel2 = new JPanel();
        //менеджер расположения элементов
        panel2.setLayout(new GridLayout(3, 2));
        name1 = new JLabel("Имя 1-ого игрока: ");
        name1.setFont(font);
        //создаем поле для имени игрока1 , устанавливаем размер
        txtname1 = new JTextField(10);

        name2 = new JLabel("Имя 2-ого игрока: ");
        name2.setFont(font);
        //создаем поле для имени игрока2 , устанавливаем размер
        txtname2 = new JTextField(10);
        boardsize = new JLabel("N размер поля (n*n): ");
        boardsize.setFont(font);
        //создаем поле для размера , устанавливаем размер
        txtboardsize = new JTextField(10);

        //нижняя панель
        panel3 = new JPanel();
        //менеджер расположения элементов
        panel3.setLayout(new FlowLayout());

        //создаем кнопку
        btnPlay = new JButton("Играть!");
        btnPlay.setBackground(Color.decode("#96baff"));
        btnPlay.setFont(font);
        //обработчик кнопки
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((txtboardsize.getText().trim().length() > 0) && (txtname1.getText().trim().length() > 0) && (txtname2.getText().trim().length() > 0)) {
                    if (((Integer.parseInt(txtboardsize.getText())) > 2) && (Integer.parseInt(txtboardsize.getText())) < 9) {
                        try {
                            GameFrame form = new GameFrame(Integer.parseInt(txtboardsize.getText()), txtname1.getText(), txtname2.getText());
                            SettingsFrame.this.dispose();
                            //обработчик ошибок
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(SettingsFrame.this, "Проверьте введённые данные", "Окно сообщения", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(SettingsFrame.this, "Размер поля должен быть от 3 до 8", "Окно сообщения", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SettingsFrame.this, "Заполните все поля", "Окно сообщения", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //создаем кнопку
        cancel = new JButton("Отмена");
        cancel.setBackground(Color.decode("#96baff"));
        cancel.setFont(font);
        //обработчик кнопки
        cancel.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 SettingsFrame.this.dispose();
             }
         });
        //добавляем все кнопки на панель
        panel2.add(name1);
        panel2.add(txtname1);
        panel2.add(name2);
        panel2.add(txtname2);
        panel2.add(boardsize);
        panel2.add(txtboardsize);
        panel3.add(btnPlay);
        panel3.add(cancel);
        //занос панелей
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        //делаем панель основной
        getContentPane().add(panel);
    }
}
