package Project;

public class DriverShort {
    // Поля 
    private String lastName;
    private String initials;
    private String vehicleLicense; // ПТС

    // Конструктор
    public DriverShort(String lastName, String firstName, String middleName, String vehicleLicense) {
        this.lastName = lastName;
        this.initials = firstName.charAt(0) + "." + middleName.charAt(0) + ".";
        this.vehicleLicense = vehicleLicense;
    }

    // Геттеры
    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    // Метод вывода информации
    @Override
    public String toString() {
        return "Фамилия: " + lastName + ", Инициалы: " + initials + ", ПТС: " + vehicleLicense;
    }

    // Пример
    public static void main(String[] args) {
        DriverShort shortDriver = new DriverShort("Иванов", "Иван", "Иванович", "654321");
        System.out.println(shortDriver.toString());
    }
}
