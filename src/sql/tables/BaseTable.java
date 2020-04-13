package sql.tables;

import sql.StockExchangeDB;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//Класс базовой таблицы
public class BaseTable implements Closeable {
    protected Connection connection;  // JDBC-соединение для работы с таблицей
    protected String tableName;       // Имя таблицы

    //Конструктор
    BaseTable(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.connection = StockExchangeDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }

    //Перегрузка метода close, для закрытия БД
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    //Состояние sql базы
    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        statement.execute(sql); // Выполняем statement - sql команду
        statement.close();      // Закрываем statement для фиксации изменений в СУБД
        if (description != null) {
            System.out.println(description);
        }
    }

    //Выполнить состояние sql базы
    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    }

    //Переподключение к базе данных, если оно отключено
    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = StockExchangeDB.getConnection();
        }
    }
}
