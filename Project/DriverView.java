package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class DriverView extends JFrame {
    private JTable driverTable;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton refreshButton;
    private JTextField surnameField;
    private JTextField nameField;
    private JTextField patronymicField;
    private JTextField driverLicenseField;
    private JTextField vehicleLicenseField;
    private JTextField insurancePolicyField;
    private JTextField experienceField;

    public DriverView() {
        setTitle("Driver Management");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Таблица для отображения водителей
        driverTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(driverTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Панель для ввода данных водителя
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));

        // Панель ввода данных водителя
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Фамилия:"));
        surnameField = new JTextField();
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Имя:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Отчество:"));
        patronymicField = new JTextField();
        inputPanel.add(patronymicField);
        inputPanel.add(new JLabel("Номер водительского удостоверения:"));
        driverLicenseField = new JTextField();
        inputPanel.add(driverLicenseField);
        inputPanel.add(new JLabel("Номер ПТС:"));
        vehicleLicenseField = new JTextField();
        inputPanel.add(vehicleLicenseField);
        inputPanel.add(new JLabel("Страховка:"));
        insurancePolicyField = new JTextField();
        inputPanel.add(insurancePolicyField);
        inputPanel.add(new JLabel("Стаж:"));
        experienceField = new JTextField();
        inputPanel.add(experienceField);
        controlPanel.add(inputPanel);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Добавить");
        deleteButton = new JButton("Удалить");
        updateButton = new JButton("Обновить");
        refreshButton = new JButton("Обновить список");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);
        controlPanel.add(buttonPanel);

        // Добавляем панели управления в окно
        add(controlPanel, BorderLayout.SOUTH);
    }

    // Метод для получения данных водителя из полей ввода
    public Driver getDriverInput() {
        String lastName = surnameField.getText();
        String firstName = nameField.getText();
        String middleName = patronymicField.getText();
        String driverLicense = driverLicenseField.getText();
        String vehicleLicense = vehicleLicenseField.getText();
        String insurancePolicy = insurancePolicyField.getText();
        int experience = 0;

        try {
            String experienceText = experienceField.getText().trim();
            if (!experienceText.isEmpty()) {
                experience = Integer.parseInt(experienceText);
            }
        } catch (NumberFormatException e) {
            experience = 0; // Если введено некорректное значение, оставляем 0
        }

        return new Driver(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
    }

    // Получение выбранного ID водителя из таблицы
    public int getSelectedDriverId() {
        int selectedRow = driverTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) driverTable.getValueAt(selectedRow, 0);
        }
        return -1; // Возвращает -1, если строка не выбрана
    }

    // Установка слушателей для кнопок
    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    // Метод для обновления таблицы с данными водителей
    public void update(List<Driver> drivers) {
        String[] columnNames = {"ID", "Фамилия", "Имя", "Отчество", "Водительское удостоверение", "Номер ПТС", "Страховка", "Стаж"};
        Object[][] data = new Object[drivers.size()][columnNames.length];

        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            data[i][0] = driver.getId();
            data[i][1] = driver.getLastName();
            data[i][2] = driver.getFirstName();
            data[i][3] = driver.getMiddleName();
            data[i][4] = driver.getDriverLicense();
            data[i][5] = driver.getVehicleLicense();
            data[i][6] = driver.getInsurancePolicy();
            data[i][7] = driver.getExperience();
        }

        driverTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    // Метод для отображения ошибок
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    // Метод для отображения окна
    public void display() {
        setVisible(true);
    }
}
