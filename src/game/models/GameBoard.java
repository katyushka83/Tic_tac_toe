package game.models;
import java.util.ArrayList;
//класс для представления игры
public class GameBoard  {
    private int size; //размер доски
    private BoardItem[][] board;
    private ArrayList<Integer[]> winItems;

    //конструктор, принимающий входной параметр-размер доски
    public GameBoard(int size) {
        setSize(size);
        winItems = new ArrayList<>();
    }

    //Метод для получение всей доски
    public BoardItem[][] getBoard(){
        return this.board;
    }

    //метод для получения размера доски
    public int getSize() {
        return size;
    }
    //метод для установления значения размер доски
    public void setSize(int size) {
        this.size = size;
        //создаем доску с размером size x size
        board = new BoardItem[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                //задаем элементам НЕОПРЕДЕЛЕНО
                board[i][j] = BoardItem.UNDEFINED;
    }

    //метод для получения элемента игровой доски с индексом (i;j) х или о
    public BoardItem getItemAt(int i, int j) {
        return (i < size && j < size) ? board[i][j] : null;
    }

    //метод для установления элемента игровой доски с индексом (i;j)
    public void setItemAt(BoardItem value, int i, int j) {
        board[i][j] = value;
    }

    //метод для вычисление следующей выигрышной комбинации при текущем состоянии игровой доски
    // winCount -длина линии из одинаковых символов (X или O) которая считается выигрышной комбинацией
    public BoardItem getNextWinner(int winCount) {
        BoardItem result = null;
        //проходимся по элементам
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                //присваиваем result выигрышную последовательность для элемента
                result = findWin(i,j,winCount);
                if (result != null)
                    return result;
            }
        //возвращаем элемент того типа, из которыз составлена выиграшная комбинация
        return result;
    }
    //метод для поиска выигрышной последовательности для элемента
    private BoardItem findWin(int i, int j, int winCount) {
        if (!board[i][j].equals(BoardItem.X) && !board[i][j].equals(BoardItem.O))
            return null;
        int cnti = i;
        int cntj = j;
        BoardItem result = null;
        winItems.clear();
        //поиск влево
        while (cntj >= 0) {
            result = search(i, j, i, cntj, winCount);
            cntj--;
            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;
        }
        cnti = i;
        winItems.clear();
        //поиск вниз
        while (cnti < size) {
            result = search(i, j, cnti, j, winCount);
            cnti++;

            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;

        }
        //поиск по диагоналям
        cnti = i; cntj = j;
        winItems.clear();
        while (cnti < size && cntj  >= 0) {
            result = search(i, j, cnti, cntj, winCount);
            cnti++;
            cntj--;
            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;
        }
        cnti = i; cntj = j;
        winItems.clear();
        while (cnti < size && cntj < size) {
            result = search(i, j, cnti, cntj, winCount);
            cnti++;
            cntj++;
            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;
        }
        return null;
    }

    //метод для поиска выйгрышной комбинации
    private BoardItem search(int i, int j, int cnti, int cntj,
                             int winCount) {
        BoardItem item = board[cnti][cntj];
        if (item.equals(board[i][j])) {
            //список для возможной выигрышной комбинации - добавляем индекс элемента
            winItems.add(new Integer[] { cnti, cntj });
            //комбинация составлена
            if (winItems.size() == winCount) {
                return item;
            }
        } else
            //комбинация не будет составлена
            return null;
        //комбинация возможно будет составлена
        return BoardItem.UNDEFINED;

    }

}
