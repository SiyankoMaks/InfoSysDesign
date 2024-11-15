package Project;

import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DriverStrategyJSON implements DriverStrategy {
    private final String jsonFilePath;

    public DriverStrategyJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public void readAllValues(List<Driver> drivers) {
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

    @Override
    public void writeAllValues(List<Driver> drivers) {
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