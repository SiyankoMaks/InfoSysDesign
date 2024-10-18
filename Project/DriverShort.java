package Project;

public class DriverShort extends Driver {

    public DriverShort(String lastName, String firstName, String middleName, 
                       String driverLicense, String vehicleLicense, 
                       String insurancePolicy, int experience) {
        super(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
    }

    // Метод вывода краткой информации
    @Override
    public String toString() {
        return getLastName() + " " + getFirstName().charAt(0) + "." + getMiddleName().charAt(0) + ". - ПТС: " + getVehicleLicense();
    }
}
