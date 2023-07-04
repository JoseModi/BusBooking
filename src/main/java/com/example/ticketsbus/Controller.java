package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private AnchorPane log;

    @FXML
    private AnchorPane sig;
    @FXML
    private Button signupBTN;
    @FXML
    private Button signupLogin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button navbar;

    @FXML
    private JFXButton backToLogin;
    private FadeTransition buttonFadeInTransition;
    private FadeTransition signupFadeInTransition;
    private FadeTransition adminloginFadeInTransition;

    private FadeTransition usernamefadeinTransition;

    private FadeTransition userpasswordfadeinTransition;
    @FXML
    private Text userloginLabel;
    @FXML
    private Text adminLoginLabel;
    @FXML
    private TextField adminUsername;
    @FXML
    private TextField adminPassword;

    @FXML
    private Button userControlsBTN;

    @FXML
    private Button adminLog;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtUname;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;
    String first,last, mail, phone, age, state, city,unam, pass;




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
        Stage curr = (Stage) ((Node) event.getSource()).getScene().getWindow();
        curr.setTitle("Sign Up User");
        fadeOut(log);
        fadeIn(sig);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(r->{
            refreshSignUp();
        });
        signupBTN.setOnAction(e->{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            getSignupDetails();
            if(first.isEmpty()||last.isEmpty()||mail.isEmpty()||phone.isEmpty()||age.isEmpty()||state.isEmpty()||city.isEmpty()||unam.isEmpty()||pass.isEmpty()){
                displayErrorAlert("Blank Field", "Not all fields have been filled");
                refreshSignUp();
            }
            else {
                try {

                    Statement statement = connection.createStatement();

                    PreparedStatement stmt = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?,?)");
                    stmt.setString(1, txtUname.getText());
                    stmt.setString(2, txtPassword.getText());
                    stmt.setString(3, txtFirstName.getText());
                    stmt.setString(4, txtLastName.getText());
                    stmt.setString(5, txtPhone.getText());
                    stmt.setString(6, txtAge.getText());
                    stmt.setString(7, txtState.getText());
                    stmt.setString(8, txtCity.getText());
                    if (this.male.isSelected()) {
                        stmt.setString(9, "Male");
                    } else {
                        stmt.setString(9, "Female");
                    }
                    stmt.setString(10, txtMail.getText());

                    int status = stmt.executeUpdate();
                    if (status > 0) {
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Success");
                        alert.show();
                        refreshSignUp();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid ");
                        alert.show();
                        throw new SQLException("Database connection error");
                    }
                } catch (SQLException n) {
                    displayErrorAlert("SQLException", n.getMessage());
                }
            }
        });
        userSetup();
        backToLogin.setOnAction(e -> {
            Stage curr = (Stage) ((Node) e.getSource()).getScene().getWindow();
            curr.setTitle("User Login");
            fadeOut(sig);
            fadeIn(log);
            userSetup();
            fadeLogin();
        });
    }

    private void fadeLogin() {
        buttonFadeInTransition = createFadeInTransition(button);
        signupFadeInTransition = createFadeInTransition(signup);
        adminloginFadeInTransition = createFadeInTransition(adminlogin);
        usernamefadeinTransition = createFadeInTransition(textField);
        userpasswordfadeinTransition = createFadeInTransition(passwordField);

        userpasswordfadeinTransition.play();
        usernamefadeinTransition.play();
        buttonFadeInTransition.play();
        signupFadeInTransition.play();
        adminloginFadeInTransition.play();
    }

    private void fadeOut(AnchorPane pane) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(100), pane);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
        fadeOut.setOnFinished(e -> pane.setVisible(false));
    }

    private void fadeIn(AnchorPane pane) {
        pane.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), pane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private FadeTransition createFadeInTransition(Button button) {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5), button);
        transition.setFromValue(0); // Starting opacity of the button (fully transparent)
        transition.setToValue(1); // Target opacity of the button (fully visible)
        return transition;
    }
    private FadeTransition createFadeInTransition(TextField textfield) {
        FadeTransition transition = new FadeTransition(Duration.seconds(0.5), button);
        transition.setFromValue(0); // Starting opacity of the button (fully transparent)
        transition.setToValue(1); // Target opacity of the button (fully visible)
        return transition;
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

    public void adminLogon(ActionEvent actionEvent) {
        Stage curr = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        curr.setTitle("Admin Login");
        adminControls();
    }

    private void adminControls() {
        adminLoginLabel.setVisible(true);
        userloginLabel.setVisible(false);
        adminlogin.setVisible(false);
        userControlsBTN.setVisible(true);
        adminUsername.setVisible(true);
        textField.setVisible(false);
        adminPassword.setVisible(true);
        passwordField.setVisible(false);
        adminLog.setVisible(true);
        button.setVisible(false);
    }

    public void userControls(ActionEvent actionEvent) {
        Stage curr = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        curr.setTitle("User Login");
        userSetup();
    }

    private void userSetup() {
        userloginLabel.setVisible(true);
        adminLoginLabel.setVisible(false);
        adminlogin.setVisible(true);
        userControlsBTN.setVisible(false);
        textField.setVisible(true);
        adminUsername.setVisible(false);
        passwordField.setVisible(true);
        adminPassword.setVisible(false);
        adminLog.setVisible(false);
        button.setVisible(true);
    }

    private void displayErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        refreshSignUp();
    }
    public void logonAdmin(ActionEvent actionEvent) {
        String uname = adminUsername.getText();
        String pwd = adminPassword.getText();
        if (uname.equals("") || pwd.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter both username and password.");
            alert.show();
        } else {
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                PreparedStatement pst = connection
                        .prepareStatement("select * from admin where username=? and password=?");
                pst.setString(1, uname);
                pst.setString(2, pwd);
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Scene scene = new Scene(parent);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    currentStage.close();
                    window.setScene(scene);
                    window.setTitle("Admin Dashboard");
                    window.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong username or password!");
                    alert.show();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void refreshSignUp(){
        RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtMail.setText("");
        txtPhone.setText("");
        txtAge.setText("");
        txtState.setText("");
        txtCity.setText("");
        txtUname.setText("");
        txtPassword.setText("");
        if(selectedRadioButton==male) {
            gender.selectToggle(male);
        }
    }

    public void getSignupDetails(){
        first = txtFirstName.getText();
        last = txtLastName.getText();
        mail = txtMail.getText();
        phone = txtPhone.getText();
        age = txtAge.getText();
        state = txtState.getText();
        city = txtCity.getText();
        unam = txtUname.getText();
        pass = txtPassword.getText();
    }
}