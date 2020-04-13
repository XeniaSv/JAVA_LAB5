package sql.model;

//Класс музыки
public class MusicModel extends BaseModel {
    private String title;
    private String artist;
    private String date;
    private int listPrice;
    private int price;
    private int version;

    //Конструктор с параметрами
    public MusicModel(String title, String artist, String date, int listPrice, int price, int version) {
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.listPrice = listPrice;
        this.price = price;
        this.version = version;
    }

    //Конструктор по умолчанию
    public MusicModel() {
    }

    //Получить имя артиста
    public String getArtist() {
        return artist;
    }

    //Установить имя артиста
    public void setArtist(String artist) {
        this.artist = artist;
    }

    //Получить дату песни
    public String getDate() {
        return date;
    }

    //Установить дату песни
    public void setDate(String date) {
        this.date = date;
    }

    //Получить list price
    public int getListPrice() {
        return listPrice;
    }

    //Установить list price
    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    //Получить цену
    public int getPrice() {
        return price;
    }

    //Установить цену
    public void setPrice(int price) {
        this.price = price;
    }

    //Получить версию
    public int getVersion() {
        return version;
    }

    //Установить версию
    public void setVersion(int version) {
        this.version = version;
    }

    //Получить title
    public String getTitle() {
        return title;
    }

    //Установить title
    public void setTitle(String title) {
        this.title = title;
    }

    //Вывести на экран всю информацию
    public void printAll() {
        System.out.println(title + '\t' + artist + '\t' + date + '\t' + listPrice + '\t' + price + '\t' + version);
    }
}
