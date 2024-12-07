package Examples;
import Project.*;

public class DriverRepDBExample {
    public static void main(String[] args) {
        // Подключение к бд
        DBConnection connection = DBConnection.getInstance("LabDB", "postgres", "123", "127.0.0.1", "5432");
        DriverRepDB driverRepDB = new DriverRepDB(connection);

        // Создание объекта Driver
        Driver newDriver = new Driver(
            0, // ID будет назначен автоматически
            "Петрович", 
            "Петр", 
            "Петрович", 
            "1111111114", 
            "11VL654321", 
            "IPR6543211234", 
            3
        );

        // Добавление водителя
        //driverRepDB.addDriver(newDriver);
        System.out.println("Водитель добавлен: " + newDriver);

        // Получение водителя по ID
        Driver fetchedDriver = driverRepDB.getObjectById(newDriver.getId());
        if (fetchedDriver != null) {
            System.out.println("Найден водитель: " + fetchedDriver);
        } else {
            System.out.println("Водитель не найден.");
        }
    }
}
