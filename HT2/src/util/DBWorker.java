package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {

    private String mySqlDriver = "com.mysql.jdbc.Driver";
    private String dbURL = "jdbc:mysql://localhost/phonebook";
    private String dbPassword = "root";
    private String dbUsername = "root";

    // Количество рядов таблицы, затронутых последним запросом.
    private Integer affected_rows = 0;

    // Значение автоинкрементируемого первичного ключа, полученное после
    // добавления новой записи.
    private Integer last_insert_id = 0;

    // Указатель на экземпляр класса.
    private static DBWorker instance = null;

    // Метод для получения экземпляра класса (реализован Singleton).
    public static DBWorker getInstance() {
        if (instance == null) {
            instance = new DBWorker();
        }

        return instance;
    }

    // "Заглушка", чтобы экземпляр класса нельзя было получить напрямую.
    private DBWorker() {
        // Просто "заглушка".
    }

    // Выполнение запросов на выборку данных.
    public ResultSet getDBData(String query) {
        Statement statement;
        Connection connect;
        ResultSet resultSet;
        try {
            Class.forName(mySqlDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }

        try {
            connect = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Error encountered while trying to read database.");
        return null;

    }

    // Выполнение запросов на модификацию данных.
    public Integer changeDBData(String query) {
        Statement statement;
        Connection connect;
        ResultSet resultSet;
        try {
            Class.forName(mySqlDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }

        try {
            connect = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = connect.createStatement();
            this.affected_rows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            // Получаем last_insert_id() для операции вставки.
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                this.last_insert_id = resultSet.getInt(1);
            }

            return this.affected_rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Error encountered while trying to modify database");
        return null;
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++
    // Геттеры и сеттеры.
    public Integer getAffectedRowsCount() {
        return this.affected_rows;
    }

    public Integer getLastInsertId() {
        return this.last_insert_id;
    }
    // Геттеры и сеттеры.
    // -------------------------------------------------
}

