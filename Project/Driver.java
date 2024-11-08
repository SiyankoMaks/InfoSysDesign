package Project;

import org.json.JSONObject;

public class Driver extends DriverShort {

    private String vehicleLicense;     // ПТС
    private String insurancePolicy;    // Страховка автомобиля
    private int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName,
                  String driverLicense, String vehicleLicense,
                  String insurancePolicy, int experience) {
        super(lastName, firstName, middleName, driverLicense);
        setVehicleLicense(vehicleLicense);
        setInsurancePolicy(insurancePolicy);
        setExperience(experience);
    }

    public Driver(Integer id, String lastName, String firstName, String middleName,
                  String driverLicense, String vehicleLicense,
                  String insurancePolicy, int experience) {
        this(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
        setVehicleLicense(vehicleLicense);
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
        super(json.optInt("id", -1), json.optString("lastName"),
              json.optString("firstName"), json.optString("middleName"),
              json.optString("driverLicense"));
        setVehicleLicense(json.optString("vehicleLicense"));
        setInsurancePolicy(json.optString("insurancePolicy"));
        setExperience(json.optInt("experience", -1));
    }

    // Метод toJson для преобразования объекта в JSONObject
    public JSONObject toJson() {
        JSONObject jsonDriver = new JSONObject();
        jsonDriver.put("id", getId());
        jsonDriver.put("lastName", getLastName());
        jsonDriver.put("firstName", getFirstName());
        jsonDriver.put("middleName", getMiddleName());
        jsonDriver.put("driverLicense", getDriverLicense());
        jsonDriver.put("vehicleLicense", getVehicleLicense());
        jsonDriver.put("insurancePolicy", getInsurancePolicy());
        jsonDriver.put("experience", getExperience());
        return jsonDriver;
    }

    // Геттеры и сеттеры с проверками
    public String getVehicleLicense() { return vehicleLicense; }
    public String getInsurancePolicy() { return insurancePolicy; }
    public int getExperience() { return experience; }

    public void setVehicleLicense(String vehicleLicense) {
        if (validateVehicleLicense(vehicleLicense)) {
            this.vehicleLicense = vehicleLicense;
        } else {
            throw new IllegalArgumentException("ПТС должен иметь формат: 2 цифры, 2 буквы, 6 цифр.");
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

    // Валидация ПТС (2 цифры, 2 буквы, 6 цифр)
    public static boolean validateVehicleLicense(String vehicleLicense) {
        return vehicleLicense != null && vehicleLicense.matches("\\d{2}[A-Z]{2}\\d{6}");
    }

    // Валидация стажа
    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    public static boolean validateInsurancePolicy(String insurancePolicy) {
        return insurancePolicy != null && insurancePolicy.matches("[A-Z]{3}\\d{10}");
    }

    // Переопределение метода equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
    
        Driver driver = (Driver) obj;
        return getDriverLicense() != null && getDriverLicense().equals(driver.getDriverLicense());
    }    

    // Метод вывода полной информации
    @Override
    public String toString() {
        return super.toString() +
               ", ПТС: " + vehicleLicense +
               ", Страховка: " + insurancePolicy +
               ", Стаж: " + experience + " лет.";
    }
}
