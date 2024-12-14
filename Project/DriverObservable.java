package Project;

import java.util.ArrayList;
import java.util.List;

// Класс для управления наблюдателями
public class DriverObservable {
    private final List<DriverObserver> observers = new ArrayList<>();

    // Добавить наблюдателя
    public void addObserver(DriverObserver observer) {
        observers.add(observer);
    }

    // Удалить наблюдателя
    public void removeObserver(DriverObserver observer) {
        observers.remove(observer);
    }

    // Уведомить всех наблюдателей
    public void notifyObservers(String action, Object data) {
        for (DriverObserver observer : observers) {
            observer.update(action, data);
        }
    }
}
