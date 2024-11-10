package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private String dbName;
    private String user;
    private String password;
    private String host;
    private String port;

    private DBConnection(String dbName, String user, String password, String host, String port) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;

        connect();
    }

    private void connect() {
        try {
            String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Соединение установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка подключения к базе данных");
        }
    }

    public static DBConnection getInstance(String dbName, String user, String password, String host, String port) {
        if (instance == null) {
            instance = new DBConnection(dbName, user, password, host, port);
        }
        return instance;
    }

    protected Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Соединение закрыто или отсутствует. Новая попытка подключения...");
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
