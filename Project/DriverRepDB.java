package Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepDB implements IDriverModel{
    private DBConnection dbConnection; // Делегация для БД
    private String TABLE_NAME = "drivers";
    
    public DriverRepDB(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getConnection(); // Делегация к DBConnection
    }

    // Методы для исправления повтора кода
    // Заполнение PreparedStatement
    private void fillDriverStatement(PreparedStatement stmt, Driver driver) throws SQLException {
        stmt.setString(1, driver.getLastName());
        stmt.setString(2, driver.getFirstName());
        stmt.setString(3, driver.getMiddleName());
        stmt.setString(4, driver.getDriverLicense());
        stmt.setString(5, driver.getVehicleLicense());
        stmt.setString(6, driver.getInsurancePolicy());
        stmt.setInt(7, driver.getExperience());
    }

    // Запись результата в объект
    private Driver mapDriver(ResultSet rs) throws SQLException {
        return new Driver(
            rs.getInt("id"),
            rs.getString("lastName"),
            rs.getString("firstName"),
            rs.getString("middleName"),
            rs.getString("driverLicense"),
            rs.getString("vehicleLicense"),
            rs.getString("insurancePolicy"),
            rs.getInt("experience")
        );
    }
    private DriverShort mapDriverShort(ResultSet rs) throws SQLException {
        return new DriverShort(
            rs.getInt("id"),
            rs.getString("lastName"),
            rs.getString("firstName"),
            rs.getString("middleName"),
            rs.getString("driverLicense")
        );
    }

    // Проверка данных на уникальность
    private boolean isUnique(String driverLicense) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE driverLicense = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driverLicense);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) == 0; // Уникально, если результат = 0
        }
    }

    // Получение объекта по ID
    public Driver getObjectById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapDriver(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DriverShort> getKthNList(int k, int n) {
        List<DriverShort> driversShort = new ArrayList<>();
        String sql = "SELECT id, lastName, firstName, middleName, driverLicense " +
                     "FROM " + TABLE_NAME + " " +
                     "ORDER BY lastName OFFSET ? LIMIT ?";
    
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (k - 1) * n); // Смещение
            stmt.setInt(2, n);           // Лимит
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                driversShort.add(mapDriverShort(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return driversShort;
    }

    // Добавление объекта с автоматическим назначением ID
    public void addDriver(Driver driver) throws SQLException {
        if (!isUnique(driver.getDriverLicense())) {
            throw new SQLException("Клиент с таким водительским удостоверением уже существует!");
        }

        String sql = "INSERT INTO " + TABLE_NAME + " (lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            fillDriverStatement(stmt, driver);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("id");
                driver.setId(generatedId);
                System.out.println("Водитель добавлен с ID: " + generatedId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Замена элемента по ID
    public boolean replaceDriverById(int id, Driver newDriver) throws SQLException {
        if (!isUnique(newDriver.getDriverLicense())) {
            throw new SQLException("Нельзя заменить водителя: водитель с таким водительским удостоверением уже существует!");
        }
    
        String sql = "UPDATE " + TABLE_NAME + " SET lastName = ?, firstName = ?, middleName = ?, driverLicense = ?, vehicleLicense = ?, insurancePolicy = ?, experience = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            fillDriverStatement(stmt, newDriver);
            stmt.setInt(8, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Удаление элемента по ID
    public void deleteDriverById(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Получение количества элементов
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
