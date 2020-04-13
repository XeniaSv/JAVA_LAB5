package sql;

import org.h2.tools.DeleteDbFiles;
import sql.model.MusicModel;
import sql.tables.MusicTable;

import java.sql.*;

public class StockExchangeDB {
    // Блок объявления констант
    public static final String DB_DIR = "c:/JavaPrj/SQLDemo/db";
    public static final String DB_FILE = "stockExchange";
    public static final String DB_URL = "jdbc:h2:/" + DB_DIR + "/" + DB_FILE;
    public static final String DB_DRIVER = "org.h2.Driver";

    // Таблицы СУБД
    MusicTable musicTable;

    //Конструктор по умолчанию
    public StockExchangeDB() throws SQLException, ClassNotFoundException {
        this(false);
    }

    //Инициализация
    public StockExchangeDB(boolean renew) throws SQLException, ClassNotFoundException {
        if (renew) {
            DeleteDbFiles.execute(DB_DIR, DB_FILE, false);
        }
        Class.forName(DB_DRIVER);
        // Инициализируем таблицы
        musicTable = new MusicTable();
    }

    //Получить соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    //Создание всех таблиц
    public void createTablesAndForeignKeys() throws SQLException {
        musicTable.createTable();
    }

    public static void main(String[] args) {
        try {
            //Создаем новую таблицу
            StockExchangeDB stockExchangeDB = new StockExchangeDB(true);
            stockExchangeDB.createTablesAndForeignKeys();

            //Создаем экземпляры класс Music
            MusicModel firstMusic = new MusicModel("POP", "Rihanna", "2007-10-02", 5000, 500, 1);
            MusicModel secondMusic = new MusicModel("POP", "Mikhail Jackson", "1980-02-10", 2000, 304, 2);

            //Добавляем экземпляры в таблицу
            stockExchangeDB.musicTable.insertIntoTable(firstMusic);
            stockExchangeDB.musicTable.insertIntoTable(secondMusic);
            //Выводим всю информацию из таблицы
            stockExchangeDB.musicTable.printAll();

            //Ищем музыку по ID
            MusicModel searchMusic = stockExchangeDB.musicTable.searchByID(1);
            searchMusic.printAll();

            //Удаляем музыку из таблицы по ID
            stockExchangeDB.musicTable.deleteByID(1);
            stockExchangeDB.musicTable.printAll();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
