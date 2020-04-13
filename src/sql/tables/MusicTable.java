package sql.tables;

import sql.model.MusicModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

//Класс таблицы музыки
public class MusicTable extends BaseTable implements TableOperations {

    //Конструктор
    public MusicTable() throws SQLException {
        super("Musics");
    }

    //Переопределение метода создать таблицу
    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Musics(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "title NVARCHAR(30) NOT NULL," +
                "artist NVARCHAR(30) NOT NULL," +
                "date NVARCHAR(30) NOT NULL," +
                "listPrice integer NOT NULL," +
                "price integer NOT NULL," +
                "version integer NOT NULL)", "Создана таблица " + tableName);
    }

    //Вставить в таблицу экземпляр класса MusicModel
    public void insertIntoTable(MusicModel music) throws SQLException {
        super.executeSqlStatement("INSERT INTO Musics VALUES " +
                "(DEFAULT, '" + music.getTitle() + "', '" + music.getArtist() +
                "', date '" + music.getDate() + "'," + music.getListPrice() + ","
                + music.getPrice() + ", " + music.getVersion() + ");\n");
    }

    //Вывести всю информацию из таблицы
    public void printAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Musics");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String artist = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            int listPrice = resultSet.getInt(5);
            int price = resultSet.getInt(6);
            int version = resultSet.getInt(7);
            System.out.println(id + "   \t" + title + '\t' + artist + '\t' + date + '\t' + listPrice + '\t' + price + '\t' + version);
        }
    }

    //Найти Music из таблицы по ID
    public MusicModel searchByID(int inputID) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Musics WHERE ID =" + inputID);
        MusicModel music = new MusicModel();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String artist = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            int listPrice = resultSet.getInt(5);
            int price = resultSet.getInt(6);
            int version = resultSet.getInt(7);
            music.setTitle(title);
            music.setArtist(artist);
            music.setDate(date.toString());
            music.setListPrice(listPrice);
            music.setPrice(price);
            music.setVersion(version);
        }
        return music;
    }

    //Удалить Music из таблицы по ID
    public void deleteByID(int inputID) throws SQLException {
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate("DELETE FROM Musics WHERE Id = " + inputID);

        System.out.println(rows + " delete");
    }
}
