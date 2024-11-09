package Project;

import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DriverRepJSON extends DriverRep {

    private final String jsonFilePath;

    // Конструктор
    public DriverRepJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        readAllValues();  // Чтение
    }

    // Чтение 
    @Override
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
    @Override
    public void writeAllValues() {
        try (FileWriter file = new FileWriter(jsonFilePath)) {
            JSONArray jsonArray = new JSONArray();
            for (Driver driver : drivers) {
                jsonArray.put(driver.toJson());
            }
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
