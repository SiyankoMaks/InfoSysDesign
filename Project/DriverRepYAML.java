package Project;

import org.yaml.snakeyaml.Yaml;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DriverRepYAML extends DriverRep {

    private final String yamlFilePath;

    // Конструктор
    public DriverRepYAML(String yamlFilePath) {
        this.yamlFilePath = yamlFilePath;
        readAllValues();  // Чтение
    }

    // Чтение
    @Override
    public void readAllValues() {
        drivers.clear();
        try {
            String content = new String(Files.readAllBytes(Paths.get(yamlFilePath)));
            Yaml yaml = new Yaml();
            Iterable<Object> objects = yaml.loadAll(content);
            for (Object obj : objects) {
                if (obj instanceof String) {
                    drivers.add(Driver.fromYaml((String) obj));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Запись
    @Override
    public void writeAllValues() {
        try (FileWriter writer = new FileWriter(yamlFilePath)) {
            for (Driver driver : drivers) {
                writer.write(driver.toYaml());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
