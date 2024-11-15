package Project;

import java.util.List;

public interface DriverStrategy {
    void readAllValues(List<Driver> drivers); // Чтение
    void writeAllValues(List<Driver> drivers); // Запись
}