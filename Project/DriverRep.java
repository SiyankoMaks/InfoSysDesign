package Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DriverRep {

    protected List<Driver> drivers = new ArrayList<>();
    private DriverStrategy strategy;

    // Конструктор
    public DriverRep(DriverStrategy strategy){
        this.setStrategy(strategy);
        this.drivers = readAllValues();
    }
    
    // Метод установки стратегии
    public void setStrategy(DriverStrategy strategy) {
        this.strategy = strategy;
    }

    // Чтение
    public List<Driver> readAllValues() {
        if (strategy != null) {
            return strategy.readAllValues();
        }
        return new ArrayList<>();
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

    // Метод проверки данных на уникальность
    private boolean isUnique(String driverLicense) {
        for (Driver driver : drivers) {
            if (driver.getDriverLicense().equals(driverLicense)) {
                return false;
            }
        }
        return true;
    }

    // Метод добавления объекта в список с автоматическим назначением ID
    public void addDriver(Driver driver) throws Exception {
        int newId = drivers.stream().mapToInt(Driver::getId).max().orElse(0) + 1;
        driver.setId(newId);
        if (!isUnique(driver.getDriverLicense())) {
            throw new Exception("Водитель с таким водительским удостоверением уже существует!");
        }
        drivers.add(driver);
        writeAllValues();
    }

    // Метод замены элемента списка по ID
    public void replaceDriverById(int id, Driver newDriver) throws Exception {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == id) {
                if (!isUnique(newDriver.getDriverLicense())) {
                    throw new Exception("Нельзя заменить водителя: водитель с таким водительским удостоверением уже существует!");
                }
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
