public class Driver {
    // Поля класса (инкапсуляция)
    private String lastName;
    private String firstName;
    private String middleName;
    private int experience;

    // Конструктор для инициализации объекта Driver
    public Driver(String lastName, String firstName, String middleName, int experience) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setExperience(experience);
    }

    // Геттеры

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

    // Сеттеры
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

}