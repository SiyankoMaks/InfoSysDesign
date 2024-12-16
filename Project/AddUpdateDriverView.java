package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUpdateDriverView extends JFrame {
    private AddUpdateDriverController controller;
    private String action;
    private Driver driverData;

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField driverLicenseField;
    private JTextField vehicleLicenseField;
    private JTextField insurancePolicyField;
    private JTextField experienceField;

    public AddUpdateDriverView(AddUpdateDriverController controller, String action, Driver driverData) {
        this.controller = controller;
        this.action = action;
        this.driverData = driverData;

        setTitle(action.equals("add") ? "Добавить водителя" : "Редактировать водителя");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        // Поля ввода данных
        add(new JLabel("Фамилия:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Имя:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Отчество:"));
        middleNameField = new JTextField();
        add(middleNameField);

        add(new JLabel("Водительское удостоверение:"));
        driverLicenseField = new JTextField();
        add(driverLicenseField);

        add(new JLabel("ПТС:"));
        vehicleLicenseField = new JTextField();
        add(vehicleLicenseField);

        add(new JLabel("Страховка:"));
        insurancePolicyField = new JTextField();
        add(insurancePolicyField);

        add(new JLabel("Стаж (лет):"));
        experienceField = new JTextField();
        add(experienceField);

        // Заполнение данных, в случае редактирования
        if (driverData != null) {
            lastNameField.setText(driverData.getLastName());
            firstNameField.setText(driverData.getFirstName());
            middleNameField.setText(driverData.getMiddleName());
            driverLicenseField.setText(driverData.getDriverLicense());
            vehicleLicenseField.setText(driverData.getVehicleLicense());
            insurancePolicyField.setText(driverData.getInsurancePolicy());
            experienceField.setText(String.valueOf(driverData.getExperience()));
        }

        // Кнопка действия
        JButton actionButton = new JButton(action.equals("add") ? "Добавить" : "Сохранить изменения");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAction();
            }
        });
        add(actionButton);

        setVisible(true);
    }

    private void handleAction() {
        try {
            // Получаем данных из полей ввода
            String lastName = lastNameField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String middleName = middleNameField.getText().trim();
            String driverLicense = driverLicenseField.getText().trim();
            String vehicleLicense = vehicleLicenseField.getText().trim();
            String insurancePolicy = insurancePolicyField.getText().trim();
            int experience = Integer.parseInt(experienceField.getText().trim());
            // Вызов определенного метода контроллера
            if (action.equals("add")) {
                controller.addDriver(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
                JOptionPane.showMessageDialog(this, "Водитель успешно добавлен!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } else if (action.equals("update")) {
                if (driverData.getId() == null) {
                    JOptionPane.showMessageDialog(this, "Ошибка: ID водителя отсутствует.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                controller.updateDriver(driverData.getId(), lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
                JOptionPane.showMessageDialog(this, "Данные водителя успешно обновлены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
