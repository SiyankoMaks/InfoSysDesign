package Project;

public class AddDriverController implements DriverController {
    private DriverModel model;

    public AddDriverController(DriverModel model) {
        this.model = model;
    }

    @Override
    public void handle(String lastName, String firstName, String middleName, String driverLicense,
                       String vehicleLicense, String insurancePolicy, int experience) throws Exception {
                        
        DriverController.validateData(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);

        model.addDriver(lastName, firstName, middleName, driverLicense, vehicleLicense, insurancePolicy, experience);
    }
}
