package Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame implements DriverObserver {
    private MainController controller;
    private JTable driverTable;
    private DefaultTableModel tableModel;
    private int currentPage = 1;
    private int pageSize = 5;

    public MainView(MainController controller) {
        this.controller = controller;

        setTitle("Список водителей");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Таблица для отображения водителей
        tableModel = new DefaultTableModel(new String[]{"№", "Фамилия", "Имя", "Отчество", "Водительское удостоверение"}, 0);
        driverTable = new JTable(tableModel);
        add(new JScrollPane(driverTable), BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Добавить водителя");
        addButton.addActionListener(e -> openAddDriverWindow());
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Редактировать");
        updateButton.addActionListener(e -> viewDriverDetails());
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Удалить водителя");
        deleteButton.addActionListener(e -> deleteDriver());
        buttonPanel.add(deleteButton);

        JButton prevButton = new JButton("Предыдущий");
        prevButton.addActionListener(e -> prevPage());
        buttonPanel.add(prevButton);

        JButton nextButton = new JButton("Следующий");
        nextButton.addActionListener(e -> nextPage());
        buttonPanel.add(nextButton);

        // Регистрация MainView как наблюдателя
        controller.getModel().addObserver(this);

        // Загрузка данных в таблицу
        refreshTable();
        setVisible(true);
    }

    // Метод обновления таблицы
    @Override
    public void update(String action, Object data) {
        if ("add".equals(action) || "update".equals(action) || "delete".equals(action)) {
            refreshTable();
        }
    }

    private void refreshTable() {
        // Очистка таблицы
        tableModel.setRowCount(0);

        // Получение данных из контроллера
        List<DriverShort> drivers = controller.getDrivers(pageSize, currentPage);
        if (drivers.isEmpty() && currentPage > 1) {
            currentPage--;
            refreshTable();
            return;
        }

        int startIndex = (currentPage - 1) * pageSize + 1;
        for (int i = 0; i < drivers.size(); i++) {
            DriverShort driver = drivers.get(i);
            tableModel.addRow(new Object[]{
                startIndex + i,
                driver.getLastName(),
                driver.getFirstName(),
                driver.getMiddleName(),
                driver.getDriverLicense()
            });
        }
    }

    // Окно добавления водителя
    private void openAddDriverWindow() {
        SwingUtilities.invokeLater(() -> new AddUpdateDriverView(new AddDriverController(controller.getModel()), "Добавить", null));
    }

    // Получение выбранного водителя
    private void viewDriverDetails() {
        int selectedRow = driverTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите водителя для редактирования", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int driverId = controller.getDriverIdByRowIndex((currentPage - 1) * pageSize + selectedRow);
        Driver driver = controller.getDriverById(driverId);

        if (driver != null) {
            SwingUtilities.invokeLater(() -> new AddUpdateDriverView(new UpdateDriverController(controller.getModel(), driverId), "Редактировать", driver));
        } else {
            JOptionPane.showMessageDialog(this, "Не удалось найти водителя", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteDriver() {
        int selectedRow = driverTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите водителя для удаления", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        int driverId = controller.getDriverIdByRowIndex((currentPage - 1) * pageSize + selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, "Вы уверены, что хотите удалить водителя?", "Подтверждение", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.deleteDriver(driverId);
                JOptionPane.showMessageDialog(this, "Водитель успешно удален!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ошибка при удалении водителя: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            refreshTable();
        }
    }

    private void nextPage() {
        currentPage++;
        refreshTable();
    }
}
