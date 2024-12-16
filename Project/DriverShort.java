package Project;

public class DriverShort {

    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String driverLicense;   // Водительское удостоверение

    // Конструктор
    public DriverShort(String lastName, String firstName, String middleName, String driverLicense) {
        setId(null);
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setDriverLicense(driverLicense);
    }

    public DriverShort(Integer id, String lastName, String firstName, String middleName, String driverLicense) {
        this(lastName, firstName, middleName, driverLicense);
        setId(id);
    }

    // Геттеры и сеттеры с проверками
    public Integer getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getDriverLicense() { return driverLicense; }

    public void setId(Integer id) {
        if (id == null || validateId(id)) this.id = id;
        else throw new IllegalArgumentException("ID должен быть положительным числом.");
    }

    public void setLastName(String lastName) {
        if (validateLastName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия должна содержать только русские буквы.");
        }
    }

    public void setFirstName(String firstName) {
        if (validateName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя должно содержать только русские буквы.");
        }
    }

    public void setMiddleName(String middleName) {
        if (validateName(middleName)) {
            this.middleName = middleName;
        } else {
            throw new IllegalArgumentException("Отчество должно содержать только русские буквы.");
        }
    }

    public void setDriverLicense(String driverLicense) {
        if (validateDriverLicense(driverLicense)) {
            this.driverLicense = driverLicense;
        } else {
            throw new IllegalArgumentException("Водительское удостоверение должно содержать 10 цифр.");
        }
    }

    // Валидация Id
    public static boolean validateId(int id) {
        return id >= 0;
    }

    // Валидация фамилии (русские буквы)
    public static boolean validateLastName(String lastName) {
        return lastName != null && lastName.matches("[А-Яа-яЁё]+");
    }

    // Валидация имени/отчества (русские буквы)
    public static boolean validateName(String name) {
        return name != null && name.matches("[А-Яа-яЁё]+");
    }

    // Валидация водительского удостоверения
    public static boolean validateDriverLicense(String driverLicense) {
        return driverLicense != null && driverLicense.matches("\\d{10}");
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverShort that = (DriverShort) o;
        return driverLicense.equals(that.driverLicense);
    }

    // Метод вывода краткой информации
    @Override
    public String toString() {
        return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }
}
