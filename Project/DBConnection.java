package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance; // ОДИН экземпляр класса
    private Connection connection;

    private final String dbName;
    private final String user;
    private final String password;
    private final String host;
    private final String port;

    // Конструктор
    private DBConnection(String dbName, String user, String password, String host, String port) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        connect();
    }

    // Подключение к бд
    private void connect() {
        try {
            String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка подключения к базе данных");
        }
    }

    // Одиночка
    public static synchronized DBConnection getInstance(String dbName, String user, String password, String host, String port) {
        if (instance == null) {
            instance = new DBConnection(dbName, user, password, host, port);
        }
        return instance;
    }

    // Соединение
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Соединение закрыто или отсутствует. Повторное подключение...");
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
