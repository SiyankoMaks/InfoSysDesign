package Lab1;
import java.util.Objects;

public class Driver {
    // Поля класса (инкапсуляция)
    private String lastName;
    private String firstName;
    private String middleName;
    private int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName, int experience) {
        // Используем общий метод для валидации строковых полей
        this.lastName = validateAndSetField(lastName, "Фамилия");
        this.firstName = validateAndSetField(firstName, "Имя");
        this.middleName = validateAndSetField(middleName, "Отчество");

        // Валидация опыта
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Геттеры
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getExperience() {
        return experience;
    }

    // Сеттеры
    public void setLastName(String lastName) {
        this.lastName = validateAndSetField(lastName, "Фамилия");
    }

    public void setFirstName(String firstName) {
        this.firstName = validateAndSetField(firstName, "Имя");
    }

    public void setMiddleName(String middleName) {
        this.middleName = validateAndSetField(middleName, "Отчество");
    }

    public void setExperience(int experience) {
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Методы для валидации
    public static String validateAndSetField(String value, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            throw new IllegalArgumentException(fieldName + " не может быть пустым.");
        }
    }

    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "Водитель: " + lastName + " " + firstName + " " + middleName + ", Стаж: " + experience + " лет.";
    }

    // Метод для краткой версии объекта (только фамилия и имя)
    public String shortInfo() {
        return "Водитель: " + lastName + " " + firstName;
    }

    // Переопределение метода equals для сравнения объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return experience == driver.experience &&
                Objects.equals(lastName, driver.lastName) &&
                Objects.equals(firstName, driver.firstName) &&
                Objects.equals(middleName, driver.middleName);
    }

    // Примеры использования
    public static void main(String[] args) {
        try {
            // Создание объектов
            Driver driver1 = new Driver("Иванов", "Иван", "Иванович", 10);
            Driver driver2 = new Driver("Петров", "Петр", "Петрович", 5);
            Driver driver3 = new Driver("Иванов", "Иван", "Иванович", 10);

            // Полная информация о водителе
            System.out.println("Полная версия объекта driver1: " + driver1.toString());
            // Краткая информация о водителе
            System.out.println("Краткая версия объекта driver1: " + driver1.shortInfo());

            // Сравнение объектов
            System.out.println("driver1 равен driver2? " + driver1.equals(driver2));
            System.out.println("driver1 равен driver3? " + driver1.equals(driver3));

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
