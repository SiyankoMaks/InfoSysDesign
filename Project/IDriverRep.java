package Project;

import java.util.List;

public interface IDriverRep {
    Driver getObjectById(int id);
    List<DriverShort> getKthNList(int k, int n);
    void addDriver(Driver driver) throws Exception;
    boolean replaceDriverById(int id, Driver newDriver) throws Exception;
    void deleteDriverById(int id);
    int getCount();

    void addObserver(DriverObserver observer);
    void removeObserver(DriverObserver observer);
    void notifyObservers();
}
