package Project;

import java.util.ArrayList;
import java.util.List;

public class DriverController implements DriverObserver {
    private IDriverRep driverRep;  // Интерфейс для работы с репозиторием водителей
    private DriverView view;  // Представление для отображения данных

    public DriverController(IDriverRep driverRep, DriverView view) {
        this.driverRep = driverRep;
        this.view = view;
        // Подписываем контроллер на изменения модели
        driverRep.addObserver(this);
        refreshDriverList();
        setupActions();
    }

    public void setupActions() {
        // Настройка действий для кнопок
        view.setAddButtonListener(e -> {
            Driver newDriver = view.getDriverInput();  // Получаем данные для нового водителя
            addDriver(newDriver);
        });
        
        view.setDeleteButtonListener(e -> {
            int driverId = view.getSelectedDriverId();  // Получаем ID выбранного водителя
            if (driverId != -1) {
                deleteDriver(driverId);  // Удаление водителя
            }
        });
        
        view.setUpdateButtonListener(e -> {
            int driverId = view.getSelectedDriverId();  // Получаем ID выбранного водителя
            if (driverId != -1) {
                Driver updatedDriver = view.getDriverInput();  // Получаем обновленные данные водителя
                updateDriver(driverId, updatedDriver);
            }
        });
        
        view.setRefreshButtonListener(e -> {
            refreshDriverList();  // Обновление списка водителей
        });
    }

    @Override
    public void update(List<Driver> drivers) {
        // Контроллер получает обновленные данные от модели и обновляет представление
        view.update(drivers);
    }

    public void addDriver(Driver driver) {
        try {
            driverRep.addDriver(driver);  // Добавление нового водителя
        } catch (Exception e) {
            view.showError("Ошибка при добавлении водителя: " + e.getMessage());
        }
    }

    public void deleteDriver(int driverId) {
        try {
            driverRep.deleteDriverById(driverId);  // Удаление водителя по ID
        } catch (Exception e) {
            view.showError("Ошибка при удалении водителя: " + e.getMessage());
        }
    }

    public void updateDriver(int driverId, Driver updatedDriver) {
        try {
            boolean success = driverRep.replaceDriverById(driverId, updatedDriver);  // Обновление данных водителя
            if (!success) {
                view.showError("Ошибка при обновлении водителя: водитель не найден.");
            }
        } catch (Exception e) {
            view.showError("Ошибка при обновлении водителя: " + e.getMessage());
        }
    }

    public void refreshDriverList() {
        List<DriverShort> driversShort = driverRep.getKthNList(1, driverRep.getCount());
    
        // Преобразуем List<DriverShort> в List<Driver>
        List<Driver> drivers = new ArrayList<>();
        for (DriverShort driverShort : driversShort) {
            Driver driver = new Driver(
                driverShort.getLastName(),
                driverShort.getFirstName(),
                driverShort.getMiddleName(),
                driverShort.getDriverLicense(),
                "", // Здесь можешь добавить логику для ПТС
                "", // Логика для страховки
                0   // Логика для стажа
                );
                drivers.add(driver);
            }
        view.update(drivers); // Обновляем представление с преобразованным списком
    }
}
