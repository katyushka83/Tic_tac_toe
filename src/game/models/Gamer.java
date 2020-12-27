package game.models;
//класс игрок
public class Gamer {
    private String name; //имя игрока
    private int ball; //количество баллов
    private int id;
    //конструктор
    public Gamer (String name,int ball){
        this.setName(name);
        this.setBall(ball);
    }
    //конструктор
    public Gamer (int id,String name,int ball){
        this.setID(id);
        this.setName(name);
        this.setBall(ball);
    }

    //метод для получения имена
    public String getName() {
        return name;
    }
    //метод для получения кол-во баллов
    public int getBall() {
        return ball;
    }
    //метод для установления имени
    public void setName(String name) {
        this.name = name;
    }
    //метод для установления кол-ва баллов
    public void setBall(int ball) {
        this.ball = ball;
    }
    //метод для установления id
    public void setID(int id) {
        this.id=id;
    }
}
