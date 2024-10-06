package Lab1;
import java.util.Objects;

// Класс Driver
public class Driver {
    // Поля класса (инкапсуляция)
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName, int experience) {
        this.lastName = validateAndSetField(lastName, "Фамилия");
        this.firstName = validateAndSetField(firstName, "Имя");
        this.middleName = validateAndSetField(middleName, "Отчество");

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

    // Переопределение метода toString для полной информации
    @Override
    public String toString() {
        return "Водитель: " + lastName + " " + firstName + " " + middleName + ", Стаж: " + experience + " лет.";
    }

    // Переопределение метода equals для сравнения объектов Driver
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return experience == driver.experience &&
                lastName.equals(driver.lastName) &&
                firstName.equals(driver.firstName) &&
                middleName.equals(driver.middleName);
    }
}

// Класс DriverShortInfo, наследующий Driver
class DriverShortInfo extends Driver {

    // Конструктор
    public DriverShortInfo(Driver driver) {
        // Вызов конструктора класса Driver
        super(driver.lastName, driver.firstName, driver.middleName, driver.experience);
    }

    // Переопределение метода toString для краткой информации
    @Override
    public String toString() {
        return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }
}

// // Примеры использования
// public class Main {
//     public static void main(String[] args) {
//         try {
//             // Создание объектов
//             Driver driver1 = new Driver("Иванов", "Иван", "Иванович", 10);
//             Driver driver2 = new Driver("Петров", "Петр", "Петрович", 5);

//             // Создание краткой версии объекта driver1
//             DriverShortInfo shortInfo1 = new DriverShortInfo(driver1);

//             // Полная информация
//             System.out.println("Полная версия объекта driver1: " + driver1.toString());

//             // Краткая информация
//             System.out.println("Краткая версия объекта driver1: " + shortInfo1.toString());

//             // Сравнение объектов
//             System.out.println("driver1 равен driver2? " + driver1.equals(driver2));

//         } catch (IllegalArgumentException e) {
//             System.out.println("Ошибка: " + e.getMessage());
//         }
//     }
// }