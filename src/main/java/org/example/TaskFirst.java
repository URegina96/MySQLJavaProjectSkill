package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TaskFirst {
    private static final String URL = "jdbc:mysql://localhost:3306/****";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "*****";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Установка соединения с базой данных
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Успешно подключено к базе данных!");

            // Здесь вы можете выполнять операции с базой данных

        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        } finally {
            // Закрытие соединения
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Соединение закрыто.");
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
                }
            }
        }
    }
}
