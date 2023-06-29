package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable {
    @FXML
    private Button adminlogin;

    @FXML
    private Button button;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signup;

    @FXML
    private TextField textField;

    ResultSet resultSet = null;
    PreparedStatement pst = null;

    @FXML
    void adminlogin(ActionEvent event) {

    }

    @FXML
    void button(ActionEvent event) {
        String username = textField.getText();
        String pass = passwordField.getText();
        if (username.equals("") && pass.equals("")) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Invalid username or password");
            a.show();
        } else {
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                pst = connection.prepareStatement("select * from user where uname=? and password=?");
                pst.setString(1, username);
                pst.setString(2, pass);
                resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    // Write username to file
                    writeUsernameToFile(username);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    // Create and show the splash screen stage
                    Stage splashStage = createSplashStage();
                    splashStage.show();

                    // Delay the execution of newStage.show() using a separate thread
                    Task<Void> delayTask = new Task<>() {
                        @Override
                        protected Void call() throws Exception {
                            Thread.sleep(2000); // Adjust the delay time as needed
                            return null;
                        }
                    };
                    delayTask.setOnSucceeded(e -> {
                        // Close the splash screen stage and show the new stage
                        splashStage.close();
                        showNewStage();
                    });
                    new Thread(delayTask).start();

                    // Close the current stage
                    currentStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid username or password");
                    alert.show();
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Method to create the splash screen stage
    private Stage createSplashStage() {
        Image logoImage = new Image("C:\\Users\\odhis\\IdeaProjects\\TicketsBus\\src\\main\\resources\\com\\example\\ticketsbus\\img\\ce_logo_circle_transparent.png");
        ImageView logoImageView = new ImageView(logoImage);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), logoImageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.play();

        StackPane splashRoot = new StackPane(logoImageView);
        Scene splashScene = new Scene(splashRoot, 300, 200);
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.setScene(splashScene);
        return splashStage;
    }

    // Method to show the new stage
    private void showNewStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("book.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
            newStage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    private void loadBookScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("book.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
            newStage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void signup(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void writeUsernameToFile(String username) throws IOException {
        try (FileWriter writer = new FileWriter("data.txt")) {
            writer.write(username);
            System.out.println("Username has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}