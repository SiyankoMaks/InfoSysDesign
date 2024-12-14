package Project;

import java.util.List;

public class MainController {
    private DriverModel model;

    // Конструктор
    public MainController(DriverModel model) {
        this.model = model;
    }

    // Метод получения модели
    public DriverModel getModel() {
        return model;
    }

    // Получение ID водителя по индексу строки
    public int getDriverIdByRowIndex(int rowIndex) {
        List<DriverShort> drivers = model.getDrivers(10, (rowIndex / 10) + 1);
        int localIndex = rowIndex % 10;
        return drivers.get(localIndex).getId();
    }

    // Получение списка водителей для отображения на странице
    public List<DriverShort> getDrivers(int pageSize, int pageNum) {
        return model.getDrivers(pageSize, pageNum);
    }

    // Получение водителя по ID
    public Driver getDriverById(int driverId) {
        return model.getDriverById(driverId);
    }

    // Добавление нового водителя
    public void addDriver(String lastName, String firstName, String middleName, String driverLicense,
            String vehicleLicense, String insurancePolicy, int experience) throws Exception {
        model.addDriver(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
    }

    // Обновление информации о водителе
    public void updateDriver(int driverId, String lastName, String firstName, String middleName, String driverLicense,
            String vehicleLicense, String insurancePolicy, int experience) throws Exception {
        model.updateDriver(driverId, lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy,
                experience);
    }

    // Удаление водителя
    public void deleteDriver(int driverId) throws Exception {
        model.deleteDriver(driverId);
    }

    // Получение общего количества водителей
    public int getDriverCount() {
        return model.getCount();
    }
}
