package Project;

import java.util.List;

public class DriverRepAdapter {

    private DriverRep driverRep;

    // Конструктор
    public DriverRepAdapter(DriverStrategy strategy) {
        this.driverRep = new DriverRep(strategy); // Создание нового экземпляра класса полного
    }

    // Методы

    // Получение объекта по ID
    public Driver getObjectById(int id) {
        return driverRep.getObjectById(id);
    }

    // Получение списка k по счету n объектов
    public List<DriverShort> getKthNList(int k, int n) {
        return driverRep.getKthNList(k, n);
    }

    // Добавление объекта
    public void addDriver(Driver driver) throws Exception {
        driverRep.addDriver(driver);
        driverRep.writeAllValues();
    }

    // Замена объекта по ID
    public boolean replaceDriverById(int id, Driver newDriver) throws Exception {
        try {
            driverRep.replaceDriverById(id, newDriver);
            driverRep.writeAllValues();
            return true;
        } catch (Exception e) {return false;}
    }

    // Удаление объекта по ID
    public void deleteDriverById(int id) {
        driverRep.deleteDriverById(id);
    }

    // Получение количества объектов
    public int getCount() {
        return driverRep.getCount();
    }
}
