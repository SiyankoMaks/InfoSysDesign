package Project;

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

    // Методы для валидации
    private String validateAndSetField(String value, String fieldName) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            throw new IllegalArgumentException(fieldName + " не может быть пустым.");
        }
    }

    private boolean validateExperience(int experience) {
        return experience >= 0;
    }

    // Геттеры и сеттеры для полей
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = validateAndSetField(lastName, "Фамилия");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateAndSetField(firstName, "Имя");
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = validateAndSetField(middleName, "Отчество");
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = validateAndSetField(driverLicense, "Водительское удостоверение");
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = validateAndSetField(vehicleLicense, "Паспорт транспортного средства");
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = validateAndSetField(insurancePolicy, "Страховка автомобиля");
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        if (validateExperience(experience)) {
            this.experience = experience;
        } else {
            throw new IllegalArgumentException("Стаж не может быть отрицательным.");
        }
    }

    // // Переопределение метода toString для полной информации
    // @Override
    // public String toString() {
    //     return "Водитель: " + lastName + " " + firstName + " " + middleName + 
    //            ", Водительское удостоверение: " + driverLicense + 
    //            ", ПТС: " + vehicleLicense + 
    //            ", Страховка: " + insurancePolicy + 
    //            ", Стаж: " + experience + " лет.";
    // }

    // Переопределение метода equals для сравнения объектов Driver
    // Закомментировано согласно требованиям
    /*
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
    */
// // Примеры использования
// public class Main {
//     public static void main(String[] args) {
//         try {
//             // Создание объектов
//             Driver driver1 = new Driver("Иванов", "Иван", "Иванович", 10);
//             Driver driver2 = new Driver("Петров", "Петр", "Петрович", 5);

            // // CSV
            // Driver csvDriver = new Driver("Петров,Петр,Петрович,5");
            // System.out.println(csvDriver.info());

//             // Создание краткой версии объекта driver1
//             DriverShortInfo shortInfo1 = new DriverShortInfo(driver1);

//             // Полная информация
//             System.out.println("Полная версия объекта driver1: " + driver1.toString());

//             // Сравнение объектов
//             System.out.println("driver1 равен driver2? " + driver1.equals(driver2));

//         } catch (IllegalArgumentException e) {
//             System.out.println("Ошибка: " + e.getMessage());
//         }
//     }
// }
}