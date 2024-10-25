package Project;

import org.json.JSONObject;

public class Driver extends DriverShort {

    private String driverLicense;      // Водительское удостоверение
    private String insurancePolicy;    // Страховка автомобиля
    private int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName,
                  String driverLicense, String vehicleLicense,
                  String insurancePolicy, int experience) {
        super(lastName, firstName, middleName, vehicleLicense);
        setDriverLicense(driverLicense);
        setInsurancePolicy(insurancePolicy);
        setExperience(experience);
    }

    // Конструктор из CSV
    public Driver(String csv) {
        super(null, null, null, null);
        String[] fields = csv.split(",");
        if (fields.length != 7) {
            throw new IllegalArgumentException("Неверный формат CSV. Ожидается 7 полей.");
        }

        setLastName(fields[0].trim());
        setFirstName(fields[1].trim());
        setMiddleName(fields[2].trim());
        setDriverLicense(fields[3].trim());
        setVehicleLicense(fields[4].trim());
        setInsurancePolicy(fields[5].trim());
        setExperience(Integer.parseInt(fields[6].trim()));
    }

    // Конструктор из JSON
    public Driver(JSONObject json) {
        super(json.optString("lastName"), json.optString("firstName"),
              json.optString("middleName"), json.optString("vehicleLicense"));
        setDriverLicense(json.optString("driverLicense"));
        setInsurancePolicy(json.optString("insurancePolicy"));
        setExperience(json.optInt("experience", -1));
    }

    // Геттеры и сеттеры с проверками
    public String getDriverLicense() { return driverLicense; }
    public String getInsurancePolicy() { return insurancePolicy; }
    public int getExperience() { return experience; }

    public void setDriverLicense(String driverLicense) {
        if (validateDriverLicense(driverLicense)) {
            this.driverLicense = driverLicense;
        } else {
            throw new IllegalArgumentException("Водительское удостоверение должно содержать 10 цифр.");
        }
    }

    public void setInsurancePolicy(String insurancePolicy) {
        if (validateInsurancePolicy(insurancePolicy)) {
            this.insurancePolicy = insurancePolicy;
        } else {
            throw new IllegalArgumentException("Страховой полис должен содержать 3 буквы и 10 цифр.");
        }
    }

    public void setExperience(int experience) {
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Методы валидации для уникальных полей Driver
    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    public static boolean validateDriverLicense(String driverLicense) {
        return driverLicense != null && driverLicense.matches("\\d{10}");
    }

    public static boolean validateInsurancePolicy(String insurancePolicy) {
        return insurancePolicy != null && insurancePolicy.matches("[A-Z]{3}\\d{10}");
    }

    // Метод вывода полной информации
    @Override
    public String toString() {
        return super.toString() +
               ", Водительское удостоверение: " + driverLicense +
               ", Страховка: " + insurancePolicy +
               ", Стаж: " + experience + " лет.";
    }
}
