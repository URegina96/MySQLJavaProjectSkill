package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

            // Создание SQL-запроса для получения среднего количества покупок в месяц для каждого курса за 2018 год
            String sql = "SELECT course_name, COUNT(*) / COUNT(DISTINCT MONTH(subscription_date)) AS average_purchases " +
                    "FROM PurchaseList " +
                    "WHERE YEAR(subscription_date) = 2018 " +
                    "GROUP BY course_name";

            // Создание объекта Statement для выполнения запроса
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Обработка результатов
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                double averagePurchases = resultSet.getDouble("average_purchases");
                System.out.printf("Курс: %s, Среднее количество покупок в месяц: %.2f%n", courseName, averagePurchases);
            }

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
