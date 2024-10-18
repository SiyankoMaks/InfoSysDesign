package Project;

import org.json.JSONObject;

// Класс Driver
public class Driver {
    // Поля класса (инкапсуляция)
    private String lastName;
    private String firstName;
    private String middleName;
    private String driverLicense;      // Водительское удостоверение
    private String vehicleLicense;     // Паспорт транспортного средства
    private String insurancePolicy;    // Страховка автомобиля
    private int experience;

    // Конструктор
    public Driver(String lastName, String firstName, String middleName, 
                  String driverLicense, String vehicleLicense, 
                  String insurancePolicy, int experience) {
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
        setDriverLicense(driverLicense);
        setVehicleLicense(vehicleLicense);
        setInsurancePolicy(insurancePolicy);
        setExperience(experience);
    }

    // Перегруженный конструктор для создания объекта из CSV строки
    public Driver(String csv) {
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

        try {
            setExperience(Integer.parseInt(fields[6].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат стажа.");
        }
    }

    // Перегруженный конструктор для создания объекта из JSON
    public Driver(JSONObject json) {
        setLastName(json.optString("lastName"));
        setFirstName(json.optString("firstName"));
        setMiddleName(json.optString("middleName"));
        setDriverLicense(json.optString("driverLicense"));
        setVehicleLicense(json.optString("vehicleLicense"));
        setInsurancePolicy(json.optString("insurancePolicy"));
        setExperience(json.optInt("experience", -1));

        if (!json.has("experience") || json.getInt("experience") < 0) {
            throw new IllegalArgumentException("Неверный формат стажа в JSON.");
        }
    }

    // Методы для валидации
    public static String validateAndSetField(String value, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            throw new IllegalArgumentException(fieldName + " не может быть пустым.");
        }
    }

    public static boolean validateExperience(int experience) {
        return experience >= 0;
    }

    // Геттеры и сеттеры для полей
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getDriverLicense() {
        return driverLicense;
    }
    public String getVehicleLicense() {
        return vehicleLicense;
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
    public void setDriverLicense(String driverLicense) {
        this.driverLicense = validateAndSetField(driverLicense, "Водительское удостоверение");
    }
    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = validateAndSetField(vehicleLicense, "Паспорт транспортного средства");
    }
    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = validateAndSetField(insurancePolicy, "Страховка автомобиля");
    }
    public void setExperience(int experience) {
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // Переопределение метода toString для полной информации
    @Override
    public String toString() {
        return "Водитель: " + lastName + " " + firstName + " " + middleName + 
               ", Водительское удостоверение: " + driverLicense + 
               ", ПТС: " + vehicleLicense + 
               ", Страховка: " + insurancePolicy + 
               ", Стаж: " + experience + " лет.";
    }

    // Переопределение метода equals для сравнения объектов Driver
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return experience == driver.experience &&
                lastName.equals(driver.lastName) &&
                firstName.equals(driver.firstName) &&
                middleName.equals(driver.middleName) &&
                driverLicense.equals(driver.driverLicense) &&
                vehicleLicense.equals(driver.vehicleLicense) &&
                insurancePolicy.equals(driver.insurancePolicy);
    }

    // Пример
    public static void main(String[] args) {

        // Взаимодействие с наследуемым классом
        DriverShort driverShort = new DriverShort("Петров", "Петр", "Петрович", "654321", "123456", "567890", 5);
        System.out.println(driverShort.toString());

        // Driver driver = new Driver("Иванов", "Иван", "Иванович", 
        // "123456", "654321", "987654", 10);

        // // Полная версия
        // System.out.println("Полная версия: " + driver.toString());

        // // Краткая версия
        // System.out.println("Краткая версия: " + driver.getShortInfo());
        
        // String jsonString = "{ \"lastName\": \"Иванов\", \"firstName\": \"Иван\", \"middleName\": \"Иванович\", \"driverLicense\": \"123456\", \"vehicleLicense\": \"654321\", \"insurancePolicy\": \"987654\", \"experience\": 10 }";
        // JSONObject jsonObject = new JSONObject(jsonString);
    
        // Driver driverFromJson = new Driver(jsonObject);
        // System.out.println(driverFromJson.toString());
    }
}


