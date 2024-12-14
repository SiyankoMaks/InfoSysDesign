package Project;

import java.util.List;

public interface IDriverModel {
    Driver getObjectById(int id);

    List<DriverShort> getKthNList(int k, int n);

    void addDriver(Driver driver) throws Exception;

    boolean replaceDriverById(int id, Driver newDriver) throws Exception;

    void deleteDriverById(int id);

    int getCount();
}
