package Project;

import java.util.List;

public interface DriverStrategy {
    List<Driver> readAllValues(); // Чтение
    void writeAllValues(List<Driver> drivers); // Запись
}