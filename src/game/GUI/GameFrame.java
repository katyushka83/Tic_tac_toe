package game.GUI;

import game.models.GamerTableModel;
import game.models.BoardItem;
import game.models.GameBoard;
import game.models.Gamer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//окно игры
class GameFrame extends JFrame {

    // размер игровой доски
    private final int BOARD_SIZE;
    // размер ячейки игровой доски, px
    private final int FIELD_WIDTH = 34;
    // количество крестиков или ноликов в непрерывной линии, при котором
    // засчитывается выигрыш
    private int WIN_COUNT = 4;
    // размеры окна, px
    private final int FRAME_DEFAULT_WIDH = 390;
    private final int FRAME_DEFAULT_HEIGTH = 390;
    // если true - первыми начинают игру крестики
    private boolean playerIsX = true;
    //панель для игры
    private JPanel gamePanel;
    // счет игры
    public int xCount = 0;
    public int oCount = 0;
    //имена игроков
    public String name1;
    public String name2;
    Gamer g1;
    Gamer g2;
    Gamer g3;
    Gamer g4;
    //игровая доска
    private GameBoard board;
    //модель таблицы
    public GamerTableModel model = new GamerTableModel();

    //конструктор с входными параметрами размером доски и именами игроков
    public GameFrame(int s, String name11, String name22) {
        setTitle("Крестики-нолики");
        // запрет на изменение окна
        setResizable(false);
        //расположение по центру
        setLocationRelativeTo(null);
        //видимость окна
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //размер доски
        this.BOARD_SIZE = s;
        //если размер доски = 3
        if (s == 3) {
            WIN_COUNT = 3;//то выйгрышная линия будет состоять из 3 элементов одного типа(Х или О)
        }
        this.name1 = name11;
        this.name2 = name22;

        //создаем игровую доску с заданным размером
        board = new GameBoard(BOARD_SIZE);
        gui_setting();
    }

    private void gui_setting() {
        // создание меню
        JMenuBar mainMenu = new JMenuBar();
        mainMenu.setBackground(Color.decode("#7b68ee"));
        setJMenuBar(mainMenu);
        JMenu playMenu = new JMenu("Игра");
        JMenu helpMenu = new JMenu("Правило");
        JMenu resMenu = new JMenu("Результаты");
        JMenuItem exitItem = new JMenuItem("Выход");
        JMenuItem clearItem = new JMenuItem("Новая игра");
        JMenuItem resItem = new JMenuItem("Результаты игры");
        resMenu.add(resItem);
        playMenu.add(exitItem);
        playMenu.add(clearItem);
        JMenuItem aboutItem = new JMenuItem("О игре");
        helpMenu.add(aboutItem);
        mainMenu.add(playMenu);
        mainMenu.add(resMenu);
        mainMenu.add(helpMenu);

        // панель на которой размещена игровая доска
        gamePanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        //устанавливаем размер
        scrollFrame.setPreferredSize(new Dimension(FRAME_DEFAULT_WIDH, FRAME_DEFAULT_HEIGTH));

        //построение игровой доски
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                //создаем кнопки
                JButton btn = new JButton(board.getItemAt(i, j).getName());
                //предпочитаемый размер кнопок
                btn.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_WIDTH));
                //устанавливаем обработчики события на кнопки
                btn.setAction(new ButtonClickAction(i, j));
                //добавляем кнопки на панель
                gamePanel.add(btn);
            }
        }
        //делаем панель основной
        setContentPane(scrollFrame);
        //устанавливаем необходимый размер  для отображения всех компонентов
        pack();
        //добавляем в таблицу игроков с именем и 0кол-ком баллов
        model.Add(new Gamer(name1, xCount));
        model.Add(new Gamer(name2, oCount));
        // события для элементов меню
        resItem.addActionListener(new ResActionListener());
        exitItem.addActionListener(new ExitActionListener());
        aboutItem.addActionListener(new AboutActionListener());
        clearItem.addActionListener(new ClearActionListener());
    }

    // очистка игровой доски
    private void clearGamePanel() {
        // первый ход для х
        playerIsX = true;
        int count = 0;
        //очистка элементов игровой доски - тех, которые были использованы
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                BoardItem item = board.getItemAt(i, j);
                if (!item.equals(BoardItem.UNDEFINED)) {
                    JButton button = (JButton) gamePanel.getComponents()[count];
                    button.setText(new String());
                    button.setEnabled(true);
                    button.setBackground(null);
                    button.setForeground(null);
                }
                count++;
            }
        }
        //создаем доску заново
        board = new GameBoard(BOARD_SIZE);
    }

    // действие при нажатии на кнопку (игровую ячейку)
    private class ButtonClickAction extends AbstractAction {
        // индекс ячейки игровой доски
        private int i, j;

        public ButtonClickAction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton source = (JButton) e.getSource();
            // клик по пустому полю
            if (!board.getItemAt(i, j).equals(BoardItem.UNDEFINED))
                return;
            // установка крестика или нолика на поле
            board.setItemAt(playerIsX ? BoardItem.X : BoardItem.O, i, j);
            //устанавливаем текст
            source.setText(board.getItemAt(i, j).getName());
            //блокируем кнопку
            source.setEnabled(false);
            // меняем игрока
            playerIsX = !playerIsX;

            // проверка, есть ли победитель
            BoardItem winner = board.getNextWinner(WIN_COUNT);
            if (winner != null && !winner.equals(BoardItem.UNDEFINED)) {
                String msg;

                if (winner.equals(BoardItem.X)) {
                    msg = "Победил: " + name1;
                    xCount++;
                } else {
                    msg = "Победил: " + name2;
                    oCount++;
                }
                model.Set(new Gamer(name1, xCount));
                model.Set(new Gamer(name2, oCount));
                JOptionPane.showMessageDialog(GameFrame.this, msg
                                + ", счет " + " - крестики (" + xCount + "): нолики ("
                                + oCount + ")", "Победа",
                        JOptionPane.INFORMATION_MESSAGE);
                clearGamePanel();
            }else{

                boolean end = true;
                //Проверка наличия пустой ячейки на поле
                BoardItem[][] allBoard = board.getBoard();
                for (BoardItem[] boardItems : allBoard) {
                    for (BoardItem boardItem : boardItems) {
                        //Если пустая ячейка нашлась, то выходим из цикла
                        if (boardItem.equals(BoardItem.UNDEFINED)) {
                            end = false;
                            break;
                        }
                    }
                    //Если пустая ячейка нашлась, то выходим из цикла
                    if (!end)
                        break;
                }
                //Если свободных ячеек на поле не нашлось, то выводим сообщение о ничьей
                if (end){
                    JOptionPane.showMessageDialog(GameFrame.this, "Ничья! попробуйте сыграть еще раз");
                    clearGamePanel();
                }

            }

        }
    }

    // выход из приложения
    private class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    // просмотр результатов
    private class ResActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            GameResults form = new GameResults(model);
        }
    }

    // новая игра
    private class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //закрываем текущее окно
            GameFrame.this.dispose();
            SettingsFrame form = new SettingsFrame();
        }
    }

    // окно "о игре"
    private class AboutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(GameFrame.this, "Крестики-нолики "
                            + BOARD_SIZE + "x" + BOARD_SIZE
                            + ". \nДля победы составьте линию из " + WIN_COUNT
                            + " крестиков или ноликов.", "О программе",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

}