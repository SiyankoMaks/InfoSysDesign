package Project;

public class DriverShort {

    private String lastName;
    private String firstName;
    private String middleName;
    private String vehicleLicense;  // ПТС

    // Конструктор
    public DriverShort(String lastName, String firstName, String middleName, String vehicleLicense) {
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setVehicleLicense(vehicleLicense);
    }

    // Геттеры и сеттеры с проверками
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getVehicleLicense() { return vehicleLicense; }

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

    public void setVehicleLicense(String vehicleLicense) {
        if (validateVehicleLicense(vehicleLicense)) {
            this.vehicleLicense = vehicleLicense;
        } else {
            throw new IllegalArgumentException("ПТС должен иметь формат: 2 цифры, 2 буквы, 6 цифр.");
        }
    }

    // Валидация фамилии (русские буквы)
    public static boolean validateLastName(String lastName) {
        return lastName != null && lastName.matches("[А-Яа-яЁё]+");
    }

    // Валидация имени/отчества (русские буквы)
    public static boolean validateName(String name) {
        return name != null && name.matches("[А-Яа-яЁё]+");
    }

    // Валидация ПТС (2 цифры, 2 буквы, 6 цифр)
    public static boolean validateVehicleLicense(String vehicleLicense) {
        return vehicleLicense != null && vehicleLicense.matches("\\d{2}[A-Z]{2}\\d{6}");
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverShort that = (DriverShort) o;
        return vehicleLicense.equals(that.vehicleLicense);
    }

    // Метод вывода краткой информации
    @Override
    public String toString() {
        return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ". - ПТС: " + vehicleLicense;
    }
}
