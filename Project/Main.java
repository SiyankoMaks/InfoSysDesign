package Project;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Настройки базы данных
        String dbUrl = "LabDB";
        String dbUser = "postgres";
        String dbPassword = "123";
        String host = "127.0.0.1";
        String port = "5432";

        // Создаем подключение к базе данных
        DBConnection dbConnection = DBConnection.getInstance(dbUrl, dbUser, dbPassword, host, port);

        // Создаем репозиторий
        DriverRepDB repository = new DriverRepDB(dbConnection);

        // Создаем модель, используя адаптер репозитория
        DriverModel model = new DriverModel(repository);

        // Создаем контроллер
        MainController controller = new MainController(model);

        // Запускаем главное окно
        SwingUtilities.invokeLater(() -> new MainView(controller));
    }
}
