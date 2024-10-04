public class Driver {
    // Поля класса (инкапсуляция)
    private String lastName;
    private String firstName;
    private String middleName;
    private int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName, int experience) {
        // Проверка валидности полей
        if (validateLastName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия не может быть пустой.");
        }

        if (validateFirstName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }

        if (validateMiddleName(middleName)) {
            this.middleName = middleName;
        } else {
            throw new IllegalArgumentException("Отчество не может быть пустым.");
        }

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
        if (validateLastName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия не может быть пустой.");
        }
    }

    public void setFirstName(String firstName) {
        if (validateFirstName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
    }

    public void setMiddleName(String middleName) {
        if (validateMiddleName(middleName)) {
            this.middleName = middleName;
        } else {
            throw new IllegalArgumentException("Отчество не может быть пустым.");
        }
    }

    public void setExperience(int experience) {
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Статические методы валидации
    public static boolean validateLastName(String lastName) {
        return lastName != null && !lastName.trim().isEmpty();
    }

    public static boolean validateFirstName(String firstName) {
        return firstName != null && !firstName.trim().isEmpty();
    }

    public static boolean validateMiddleName(String middleName) {
        return middleName != null && !middleName.trim().isEmpty();
    }

    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    // Методы
    public String info() {
        return "Водитель: " + lastName + " " + firstName + " " + middleName + ", Стаж: " + experience + " лет.";
    }

    // Примеры
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
