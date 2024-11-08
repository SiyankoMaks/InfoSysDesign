package Project;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DriverRepJSON {

    private List<Driver> drivers = new ArrayList<>();
    private final String jsonFilePath;

    // Конструктор
    public DriverRepJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        readAllValues();  // Чтение
    }

    // Чтение 
    public void readAllValues() {
        drivers.clear();
        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                drivers.add(new Driver(jsonArray.getJSONObject(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись
    public void writeAllValues() {
        try (FileWriter file = new FileWriter(jsonFilePath)) {
            JSONArray jsonArray = new JSONArray();
            for (Driver driver : drivers) {
                jsonArray.put(driver.toJson()); // toJson() в Driver для JSON-объекта
            }
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод получения объекта по id
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

    // Метод Добавления объекта в список с автоматическим назначением ID
    public void addDriver(Driver driver) {
        int newId = getNewId();
        driver.setId(newId);
        drivers.add(driver);
        writeAllValues();
    }

    private int getNewId() {
        return drivers.stream().mapToInt(Driver::getId).max().orElse(0) + 1;
    }

    // Метод замены элемента списка по ID
    public void replaceDriverById(int id, Driver newDriver) {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == id) {
                newDriver.setId(id);
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

