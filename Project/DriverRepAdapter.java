package Project;

import java.util.List;

public class DriverRepAdapter {

    private DriverRep driverRep;

    // Конструктор
    public DriverRepAdapter(DriverStrategy strategy) {
        this.driverRep = new DriverRep() {{setStrategy(strategy);}};
    }

    // Методы

    // Получение объекта по ID
    public Driver getObjectById(int id) {
        return driverRep.getObjectById(id);
    }

    // Получение списка k по счету n объектов
    public List<Driver> getKthNList(int k, int n) {
        return driverRep.getKthNList(k, n);
    }

    // Добавление объекта
    public void addDriver(Driver driver) {
        driverRep.addDriver(driver);
    }

    // Замена объекта по ID
    public boolean replaceDriverById(int id, Driver newDriver) {
        Driver existingDriver = driverRep.getObjectById(id);
        if (existingDriver != null) {
            driverRep.replaceDriverById(id, newDriver);
            return true;
        }
        return false;
    }

    // Удаление объекта по ID
    public void deleteDriverById(int id) {
        driverRep.deleteDriverById(id);
    }

    // Получение количества объектов
    public int getCount() {
        return driverRep.getCount();
    }

    // Установка новой стратегии
    public void setStrategy(DriverStrategy strategy) {
        driverRep.setStrategy(strategy);
    }
}
