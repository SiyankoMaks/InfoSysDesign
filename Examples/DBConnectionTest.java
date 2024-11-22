package Examples;
import Project.*;

public class DBConnectionTest {
    public static void main(String[] args) {
        DBConnection connection = DBConnection.getInstance("LabDB", "postgres", "123", "127.0.0.1", "5432");

        try {
            if (connection.getConnection() != null) {
                System.out.println("Соединение с базой данных успешно установлено!");
            }
        } catch (Exception e) {
            System.err.println("Ошибка соединения с базой данных: " + e.getMessage());
        }
    }
}
