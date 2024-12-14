package Project;

import java.util.List;

public class MainController {
    private final DriverModel model;

    // Конструктор
    public MainController(DriverModel model) {
        this.model = model;
    }

    // Получение списка водителей для отображения на странице
    public List<DriverShort> getDrivers(int pageSize, int pageNum) {
        return model.getDrivers(pageSize, pageNum);
    }
    
}
