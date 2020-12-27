package game.Database;

import game.models.Gamer;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String PATH_TO_DB_FAIL = "results.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FAIL;

    //создаем объект для соединения
    public static Connection connection;
    //создаем объект для выполнения SQL-запросов
    public static Statement statement;
    //создаем объект для создания результата запроса
    public static ResultSet resultSet;

    //закрытие соединения
    public static void closeConnection() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
    }

    //соединение
    public static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(URL);
        if (connection != null) {
            System.out.println("База Подключена!");
            createDB();
        }
    }

    //метод для создания таблицы
    public static void createDB() throws SQLException {
        statement = connection.createStatement();
        //запрос на создание таблицы
        statement.execute("CREATE TABLE if not exists 'Result' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'ball' int);");
        System.out.println("Таблица создана или уже существует.");
    }

    //метод для записи в таблицу БД
    public static void AddResult(Gamer gamers) throws SQLException {
        //выполняем запрос
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Result(`name`,`ball`) " +
                "VALUES(?,?)");
        //определяем значение параметров
        statement.setObject(1, gamers.getName());
        statement.setObject(2, gamers.getBall());
        statement.execute();

    }
    //метод для записи в таблицу БД
    public static void EditResult(Gamer gamer, int index) throws SQLException {
        //выполняем запрос
        PreparedStatement statement = connection.prepareStatement("UPDATE Result SET name=?,ball=? WHERE id=?");
        //определяем значение параметров
        statement.setObject(1, gamer.getName());
        statement.setObject(2, gamer.getBall());
        statement.setObject(3, index);
        statement.execute();

    }

    //метод для чтения таблицы БД
    public static ArrayList<Gamer> read() throws SQLException {
        ArrayList<Gamer> list = new ArrayList<>();
        //создаем объект statement
        statement = connection.createStatement();
        //выполняем запрос
        resultSet = statement.executeQuery("SELECT * FROM Result");
        // пока есть что выбирать, выполняем
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int ball = resultSet.getInt("ball");
            Gamer ins = null;
            ins = new Gamer(id, name, ball);
            list.add(ins);
        }
        //возвращаем список
        return list;
    }

}
