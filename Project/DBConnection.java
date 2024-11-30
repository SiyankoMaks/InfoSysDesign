package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance; // ОДИН экземпляр класса
    private Connection connection;

    private String TABLE_NAME = "drivers";

    private String dbName;
    private String user;
    private String password;
    private String host;
    private String port;

    // Конструктор
    private DBConnection(String dbName, String user, String password, String host, String port) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        connect();
        // Перенести создание таблицы createTableIfNotExists
        createTableIfNotExists();
    }
    // Метод для создания таблицы, если она не существует
    private void createTableIfNotExists() {
        String createTableSQL = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "id SERIAL PRIMARY KEY, " +
                        "lastName VARCHAR(100) NOT NULL, " +
                        "firstName VARCHAR(100) NOT NULL, " +
                        "middleName VARCHAR(100), " +
                        "driverLicense VARCHAR(50) UNIQUE NOT NULL, " +
                        "vehicleLicense VARCHAR(50) NOT NULL, " +
                        "insurancePolicy VARCHAR(50) NOT NULL, " +
                        "experience INT NOT NULL CHECK (experience >= 0)" +
                        ")",
                TABLE_NAME
        );

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.execute();
            System.out.println("Таблица \"" + TABLE_NAME + "\" проверена/создана.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании таблицы: " + TABLE_NAME);
        }
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
