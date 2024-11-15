package Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class DriverRep {

    private final List<Driver> drivers = new ArrayList<>();
    private DriverStrategy strategy;

    // Метод установки стратегии
    public void setStrategy(DriverStrategy strategy) {
        this.strategy = strategy;
        readAllValues();
    }

    // Чтение
    public void readAllValues() {
        if (strategy != null) {
            strategy.readAllValues(drivers);
        }
    }

    // Запись
    public void writeAllValues() {
        if (strategy != null) {
            strategy.writeAllValues(drivers);
        }
    }

    // Метод получения объекта по ID
    public Driver getObjectById(int id) {
        for (Driver driver : drivers) {
            if (driver.getId() == id) {
                return driver;
            }
        }
        return null;
    }

    // Метод получения списка k по счету n объектов класса Driver
    public List<Driver> getKthNList(int k, int n) {
        int start = (k - 1) * n;
        int end = Math.min(start + n, drivers.size());
        return drivers.subList(start, end);
    }

    // Метод сортировки элементов по фамилии
    public void sortDriversByLastName() {
        drivers.sort(Comparator.comparing(Driver::getLastName));
    }

    // Метод добавления объекта в список с автоматическим назначением ID
    public void addDriver(Driver driver) {
        int newId = drivers.stream().mapToInt(Driver::getId).max().orElse(0) + 1;
        driver.setId(newId);
        driver.equals(driver);
        drivers.add(driver);
        writeAllValues();
    }

    // Метод замены элемента списка по ID
    public void replaceDriverById(int id, Driver newDriver) {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == id) {
                newDriver.setId(id);
                newDriver.equals(newDriver);
                drivers.set(i, newDriver);
                writeAllValues();
                break;
            }
        }
    }

    // Метод удаления элемента списка по ID
    public void deleteDriverById(int id) {
        drivers.removeIf(driver -> driver.getId() == id);
        writeAllValues();
    }

    // Метод получения количества элементов
    public int getCount() {
        return drivers.size();
    }
}
