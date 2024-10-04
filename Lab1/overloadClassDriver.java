package Lab1;

public class overloadClassDriver {
    // Поля класса (инкапсуляция)
    private String lastName;
    private String firstName;
    private String middleName;
    private int experience;

    // Основной конструктор
    public overloadClassDriver(String lastName, String firstName, String middleName, int experience) {
        this.lastName = validateAndSetField(lastName, "Фамилия");
        this.firstName = validateAndSetField(firstName, "Имя");
        this.middleName = validateAndSetField(middleName, "Отчество");

        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Перегруженный конструктор для создания объекта из CSV строки
    public overloadClassDriver(String csv) {
        String[] fields = csv.split(",");
        if (fields.length != 4) {
            throw new IllegalArgumentException("Неверный формат CSV. Ожидается 4 поля.");
        }

        this.lastName = validateAndSetField(fields[0].trim(), "Фамилия");
        this.firstName = validateAndSetField(fields[1].trim(), "Имя");
        this.middleName = validateAndSetField(fields[2].trim(), "Отчество");

        try {
            int experience = Integer.parseInt(fields[3].trim());
            if (validateExperience(experience)) {
                this.experience = experience;
            } else {
                throw new IllegalArgumentException("Стаж не может быть отрицательным.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат стажа.");
        }
    }

    // Геттеры и сеттеры
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

    // Валидация строк
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
            // Основной конструктор
            overloadClassDriver driver = new overloadClassDriver("Иванов", "Иван", "Иванович", 10);
            System.out.println(driver.info());

            // CSV
            overloadClassDriver csvDriver = new overloadClassDriver("Петров,Петр,Петрович,5");
            System.out.println(csvDriver.info());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
