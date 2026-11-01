package com.vehicle.vehicleregistrationsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.vehicle.vehicleregistrationsystem.BlockchainService; // Make sure this import is here

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // --- THIS IS THE TEST CODE ---
        try {
            BlockchainService blockchain = new BlockchainService();
            blockchain.registerVehicle("ABC123", "Edwin", "VIN001");
            String[] car = blockchain.getVehicle("ABC123");
            System.out.println("SUCCESS! Owner: " + car[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // -----------------------------

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}