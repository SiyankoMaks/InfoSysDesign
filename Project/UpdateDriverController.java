package Project;

public class UpdateDriverController implements DriverController {
    private DriverModel model;
    private int driverId;

    public UpdateDriverController(DriverModel model, int driverId) {
        this.model = model;
        this.driverId = driverId;
    }

    @Override
    public void handle(String lastName, String firstName, String middleName, String driverLicense,
                       String vehicleLicense, String insurancePolicy, int experience) throws Exception {

        DriverController.validateData(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);

        model.updateDriver(driverId, lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
    }
}
