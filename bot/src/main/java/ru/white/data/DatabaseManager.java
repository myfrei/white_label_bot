package ru.white.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:h2:~/test"; // Путь к вашей базе данных H2
    private static final String USER = "sa";
    private static final String PASS = "";

    public DatabaseManager() {
        // Инициализация подключения к базе данных, если это необходимо
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Метод для выполнения запросов на чтение (SELECT)
    public ResultSet executeQuery(String query) {
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            return rs; // Возвращаем ResultSet для обработки вызывающим методом

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Метод для выполнения обновлений (INSERT, UPDATE, DELETE)
    public boolean executeUpdate(String sql) {
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Дополнительные методы для работы с базой данных
}
