package Project;

import java.util.List;

public class DriverModel extends DriverObservable {
    private IDriverModel driverRep;

    // Конструктор
    public DriverModel(IDriverModel driverRep) {
        this.driverRep = driverRep;
    }

    // Получение списка водителей для отображения на странице
    public List<DriverShort> getDrivers(int pageSize, int pageNum) {
        return driverRep.getKthNList(pageNum, pageSize);
    }

    // Получение водителя по ID
    public Driver getDriverById(int driverId) {
        return driverRep.getObjectById(driverId);
    }

    // Добавление нового водителя и уведомление наблюдателей
    public void addDriver(String lastName, String firstName, String middleName, String driverLicense,
                          String vehicleLicense, String insurancePolicy, int experience) throws Exception {
        Driver newDriver = new Driver(0, lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
        driverRep.addDriver(newDriver);
        notifyObservers("add", newDriver);
    }

    // Обновление данных водителя и уведомление наблюдателей
    public void updateDriver(int driverId, String lastName, String firstName, String middleName, String driverLicense,
                             String vehicleLicense, String insurancePolicy, int experience) throws Exception {
        Driver updatedDriver = new Driver(driverId, lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
        boolean success = driverRep.replaceDriverById(driverId, updatedDriver);
        if (success) {
            notifyObservers("update", updatedDriver);
        } else {
            throw new Exception("Failed to update driver with ID: " + driverId);
        }
    }

    // Удаление водителя и уведомление наблюдателей
    public void deleteDriver(int driverId) throws Exception {
        driverRep.deleteDriverById(driverId);
        notifyObservers("delete", driverId);
    }

    // Получение общего количества водителей
    public int getCount() {
        return driverRep.getCount();
    }
}

