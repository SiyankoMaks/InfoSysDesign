package Project;

public interface DriverController {
    void handle(String lastName, String firstName, String middleName, String driverLicense,
    String vehicleLicense, String insurancePolicy, int experience) throws Exception;

    public static void validateData(String lastName, String firstName, String middleName, String driverLicense,
                                    String vehicleLicense, String insurancePolicy, int experience) throws IllegalArgumentException {
        StringBuilder errors = new StringBuilder();

        if (!DriverShort.validateLastName(lastName)) {
            errors.append("Фамилия должна содержать только русские буквы.\n");
        }
        if (!DriverShort.validateName(firstName)) {
            errors.append("Имя должно содержать только русские буквы.\n");
        }
        if (!DriverShort.validateName(middleName)) {
            errors.append("Отчество должно содержать только русские буквы.\n");
        }
        if (!DriverShort.validateDriverLicense(driverLicense)) {
            errors.append("Водительское удостоверение должно содержать 10 цифр.\n");
        }
        if (!Driver.validateVehicleLicense(vehicleLicense)) {
            errors.append("ПТС должен иметь формат: 2 цифры, 2 буквы, 6 цифр.\n");
        }
        if (!Driver.validateInsurancePolicy(insurancePolicy)) {
            errors.append("Страховой полис должен содержать 3 буквы и 10 цифр.\n");
        }
        if (!Driver.validateExperience(experience)) {
            errors.append("Стаж не может быть отрицательным.\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
