package game.models;
//элемент игровой доски для игры в крестики нолики
public enum BoardItem {

    UNDEFINED(""),
    X("X"),
    O("0");
    private String name;
     BoardItem(String name) {
        this.name = name;
    }
    //метод для получения значения
    public String getName() {
        return name;
    }
}
