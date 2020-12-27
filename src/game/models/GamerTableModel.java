package game.models;

import game.Database.Database;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

//модел таблицы
public class GamerTableModel extends AbstractTableModel {
    //создаем список из элементов класса Control
    ArrayList<Gamer> data = new ArrayList<>();
    //создаем объект класса бд
    Database db = new Database();

    //конструктор
    public GamerTableModel() {
        try {
            //устанавливаем соединение
            db.openConnection();
            //считываем данные
            data = db.read();
            //закрываем соединение
            db.closeConnection();
            System.out.println("Данные считаны.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }

    //получение кол-ва строк
    @Override
    public int getRowCount() {
        return data.size();
    }

    //получение количесства столбцов
    @Override
    public int getColumnCount() {
        return 2;
    }

    //получение значение ячеек
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gamer st = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return st.getName();
            case 1:
                return st.getBall();
        }
        return "";
    }

    //получение название столбцов
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Имя игрока";
            case 1:
                return "Кол-во баллов";
        }
        return "";
    }

    //метод добавления записи
    public void Add(Gamer gamers) {
        try {
            //устанавливаем соединение
            db.openConnection();
            db.AddResult(gamers);//добавляем в бд
            //добавляем в таблицу
            data.add(gamers);
            //закрываем соединение
            db.closeConnection();
            System.out.println("Запись добавлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        //обновляем таблицу
        this.fireTableDataChanged();
    }

    //метод редактирования записи
    public void Set(Gamer gamers) {
        try {
            int index = 0;
            for (Gamer gamer : data) {
                String nameTmp = gamer.getName();
                //если имя имеющего объекта равно имени нового объекта
                if (nameTmp.equals(gamers.getName()))
                    //находим индекс имеющегося объекта с данным именем
                    index = data.indexOf(gamer);
            }
            //устанавливаем соединение
            db.openConnection();
            db.EditResult(gamers,index+1);//редактируем в бд
            //редактируем в таблице
            data.set(index, gamers);
            //закрываем соединение
            db.closeConnection();
            System.out.println("Запись отредактирована.");
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        //обновляем таблицу
        this.fireTableDataChanged();
    }
}
