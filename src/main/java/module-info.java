module com.vehicle.vehicleregistrationsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vehicle.vehicleregistrationsystem to javafx.fxml;
    exports com.vehicle.vehicleregistrationsystem;
}