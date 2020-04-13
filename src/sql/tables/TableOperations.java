package sql.tables;

import java.sql.SQLException;

//Интерфейс операций, которые должны выполнять таблицы
public interface TableOperations {
    //Создать таблицу
    void createTable() throws SQLException; // создание таблицы
}
