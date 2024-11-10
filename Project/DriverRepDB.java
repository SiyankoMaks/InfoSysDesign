package Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverRepDB {

    private final DBConnection dbConnection;
    private final String TABLE_NAME = "drivers";

    public DriverRepDB(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getConnection();
    }

    // Получение объекта по ID
    public Driver getObjectById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Получение списка k по счету n объектов
    public List<Driver> getKthNList(int k, int n) {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY lastName OFFSET ? LIMIT ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (k - 1) * n);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(new Driver(
                        rs.getInt("id"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("driverLicense"),
                        rs.getString("vehicleLicense"),
                        rs.getString("insurancePolicy"),
                        rs.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Добавление объекта с автоматическим назначением ID
    public void addDriver(Driver driver) {
        String sql = "INSERT INTO " + TABLE_NAME + " (lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driver.getLastName());
            stmt.setString(2, driver.getFirstName());
            stmt.setString(3, driver.getMiddleName());
            stmt.setString(4, driver.getDriverLicense());
            stmt.setString(5, driver.getVehicleLicense());
            stmt.setString(6, driver.getInsurancePolicy());
            stmt.setInt(7, driver.getExperience());

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
    public void replaceDriverById(int id, Driver newDriver) {
        String sql = "UPDATE " + TABLE_NAME + " SET lastName = ?, firstName = ?, middleName = ?, driverLicense = ?, vehicleLicense = ?, insurancePolicy = ?, experience = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newDriver.getLastName());
            stmt.setString(2, newDriver.getFirstName());
            stmt.setString(3, newDriver.getMiddleName());
            stmt.setString(4, newDriver.getDriverLicense());
            stmt.setString(5, newDriver.getVehicleLicense());
            stmt.setString(6, newDriver.getInsurancePolicy());
            stmt.setInt(7, newDriver.getExperience());
            stmt.setInt(8, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
