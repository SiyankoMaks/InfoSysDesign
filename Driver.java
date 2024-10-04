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

    // Методы
    // Общий метод для валидации строк
    public static String validateAndSetField(String value, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            throw new IllegalArgumentException(fieldName + " не может быть пустым.");
        }
    }

    // Стаж
    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    // Информация о водителе
    public String info() {
        return "Водитель: " + lastName + " " + firstName + " " + middleName + ", Стаж: " + experience + " лет.";
    }

    // Примеры использования
    public static void main(String[] args) {
        try {
            // Хороший пример
            Driver driver = new Driver("Иванов", "Иван", "Иванович", 10);
            System.out.println(driver.info());

            // Плохой пример
            Driver invalidDriver = new Driver("", "Иван", "Иванович", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
