package Project;

import org.yaml.snakeyaml.Yaml;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DriverStrategyYAML implements DriverStrategy {
    private final String yamlFilePath;

    public DriverStrategyYAML(String yamlFilePath) {
        this.yamlFilePath = yamlFilePath;
    }

    @Override
    public void readAllValues(List<Driver> drivers) {
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

    @Override
    public void writeAllValues(List<Driver> drivers) {
        try (FileWriter writer = new FileWriter(yamlFilePath)) {
            for (Driver driver : drivers) {
                writer.write(driver.toYaml());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}