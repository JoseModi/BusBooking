package com.example.ticketsbus;

import com.example.ticketsbus.connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class BusController implements Initializable {

    @FXML
    private AnchorPane Bookings;

    @FXML
    private JFXButton button;

    @FXML
    private JFXButton dBoard;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private ComboBox datE;

    @FXML
    private ComboBox from;

    @FXML
    private JFXButton mBooking;

    @FXML
    private TableColumn<Service, String> atime;
    @FXML
    private TableColumn<Service, String> dtime;
    @FXML
    private TableColumn<Service, Integer> fare;
    @FXML
    private TableColumn<Service, String> service;

    @FXML
    private TableColumn<Service, String> destination;

    @FXML
    private TableColumn<Service, String> source;

    ResultSet resultSet;
    PreparedStatement pst;

    @FXML
    private CheckComboBox<String> seats;

    @FXML
    private TableView<Service> tableview;

    @FXML
    private ComboBox to;

    @FXML
    private Label sourcelabel;
    @FXML
    private Label serlabel;
    @FXML
    private Label dlabel;
    @FXML
    private Label flabel;
    @FXML
    private Label datelabel;

    @FXML
    private Label seatcount;

    @FXML
    private Label seatlabel;

    private boolean EDIT=false, ADD=true;

    private String query;

    private DataAccessObject dao;

    private String naMe;
    private String pHone;
    private String soUrce;
    private String deStination;
    private String sErvice;
    private String daTe;
    private String seatS;
    private String amoUnt;

    @FXML
    private JFXButton bookSeats;
    @FXML
    private TextField contactNo;

    @FXML
    private Label lblPhone;

    String filePath;
    String fileContent;
    @FXML
    private Label userName;

    public int count, i;

    @FXML
    private Label totalPrice;

    @FXML
    private Button refreshLayout;


    private String serve;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            StringBuilder selectedSeats = new StringBuilder();
            for (String seat : seats.getCheckModel().getCheckedItems()) {
                selectedSeats.append("'").append(seat).append("', ");
            }
            if (selectedSeats.length() > 0) {
                selectedSeats.delete(selectedSeats.length() - 2, selectedSeats.length()); // Remove the trailing comma and space
            }
            System.out.println("Selected Seats: " + selectedSeats.toString());

            // Establish a database connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "")) {
                // Prepare the SQL update statement
                String updateQuery = "UPDATE seat_names SET picked = 1 WHERE seatname IN (" + selectedSeats.toString() + ")";

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Execute the update statement
                int rowsUpdated = preparedStatement.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);
                seats.disableProperty();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        refreshLayout.setOnAction(event -> {
            loadFXMLAndSetScene("book.fxml", event);
        });

        filePath = "data.txt";
        fileContent = readFileToString(filePath);
        dao = new DataAccessObject();
        bookSeats.setOnAction(e->{
            saveBooking();
            seats.setDisable(false);
            loadFXMLAndSetScene("book.fxml", e);
        });

        connect();
        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            count = seats.getCheckModel().getCheckedItems().size();
            seatcount.setText("Selected Count: " + count);
            i = Integer.parseInt(flabel.getText()) * count;
            totalPrice.setText(String.valueOf(i));
            if(count >=3){
//                seats.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("You can select a maximum of 3 Seats");
                alert.show();
                seats.hide();
                seats.setDisable(true);
            }
        });

        seats.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            StringBuilder selectedSeats = new StringBuilder();
            for (String seat : seats.getCheckModel().getCheckedItems()) {
                selectedSeats.append(seat).append(", ");
            }
            if (selectedSeats.length() > 0) {
                selectedSeats.delete(selectedSeats.length() - 2, selectedSeats.length()); // Remove the trailing comma and space
            }
           System.out.println("Selected Seats: " + selectedSeats.toString());
        });
        tableview.setOnMouseClicked(e->{
            try{
                Service service = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                sourcelabel.setText(service.getSource());
                serlabel.setText(service.getService());
                serve = serlabel.getText();
                lblPhone.setText(contactNo.getText());
                dlabel.setText(service.getDestination());
                flabel.setText(String.valueOf(service.getFare()));
                datelabel.setText(String.valueOf(service.getDt()));
                userName.setText(fileContent);
                seats.setVisible(true);
                seatcount.setVisible(true);
                seatlabel.setVisible(true);
                loadSeats();
            } catch(Exception v){
                System.out.println("You did not select a row");
            }
        });
        service.setCellValueFactory(new PropertyValueFactory<>("service"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        fare.setCellValueFactory(new PropertyValueFactory<>("fare"));
        dtime.setCellValueFactory(new PropertyValueFactory<>("dtime"));
        atime.setCellValueFactory(new PropertyValueFactory<>("atime"));

        dashboard.setVisible(true);
        Bookings.setVisible(false);

        dBoard.setOnAction(e->{
        if(Bookings.isVisible()){
            Bookings.setVisible(false);
        }
            dashboard.setVisible(true);
            loadFXMLAndSetScene("book.fxml", e);
        });

        mBooking.setOnAction(e->{
            if(dashboard.isVisible()){
                dashboard.setVisible(false);
            }
            Bookings.setVisible(true);
        });
    }

    private void saveBooking() {
        naMe = userName.getText();
        pHone = lblPhone.getText();
        soUrce = sourcelabel.getText();
        deStination = dlabel.getText();
        sErvice = serlabel.getText();
        daTe = datelabel.getText();
        seatS = String.valueOf(count);
        amoUnt = totalPrice.getText();

        if (isBookingExists(naMe, daTe)) {
            // Handle duplicate entry case, e.g., show an error message
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Duplicate booking entry!");
            alert.show();
            return;
        }

        query = "INSERT INTO bookings(name, phone, source, destination, service, date, seats, amount) VALUES ('"+naMe+"','"+pHone+"','"+soUrce+"','"+deStination+"','"+sErvice+"','"+daTe+"','"+seatS+"','"+amoUnt+"')";

        dao.saveData(query);
    }

    private void loadSeats() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<String> seat = FXCollections.observableArrayList();
        ResultSet rS;
        PreparedStatement PST;
        try{
            PST = connection.prepareStatement("select * from seat_names where picked = 0 AND service='"+serve+"'");
            rS = PST.executeQuery();
            while (rS.next()) {
                String Seat = rS.getString("seatname");
                seat.add(Seat);
            }
            seats.getItems().addAll(seat);
        } catch(SQLException e){
            System.out.println("");
        }
    }

    public void search() {
        loaddata();
    }

    private void loaddata() {

        ObservableList<Service> data = FXCollections.observableArrayList();

        String source = from.getSelectionModel().getSelectedItem().toString();
        String dest = to.getSelectionModel().getSelectedItem().toString();
        String Date = datE.getSelectionModel().getSelectedItem().toString();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(
                    "select *  from search where source ='" + source + "' and dest = '" + dest + "'and date = '" + Date + "' ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Service(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getTime(5),
                        rs.getTime(6),
                        rs.getString(8)));

                tableview.setItems(data);

            }
        } catch (SQLException e) {
            System.out.println("");
        }
    }

    public void connect() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<String> fromData = FXCollections.observableArrayList();
        ObservableList<String> toData = FXCollections.observableArrayList();
        ObservableList<String> DateData = FXCollections.observableArrayList();
        ResultSet rS;
        PreparedStatement PST;
        try {
            PST = connection.prepareStatement("select * from search");
            pst = connection.prepareStatement("select * from bookk");
            rS = PST.executeQuery();
            resultSet = pst.executeQuery();


            while (rS.next()) {
                String Date = rS.getString("date");
                DateData.add(Date);
            }

            datE.setItems(DateData);

            while (resultSet.next()) {
                String location = resultSet.getString("location");

                fromData.add(location);
                toData.add(location);
            }

            from.setItems(fromData);
            to.setItems(toData);

            from.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (toData.contains(newValue)) {
                        toData.remove(newValue);
                        to.setItems(toData);
                    }
                }
            });

            to.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (fromData.contains(newValue)) {
                        fromData.remove(newValue);
                        from.setItems(fromData);
                    }
                }
            });
        } catch (SQLException e) {
            System.out.println("");
        }

    }

    private static String readFileToString(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    content.append(System.lineSeparator());
                }
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return content.toString();
    }
    private int calculateI() {
        int flabelValue = Integer.parseInt(flabel.getText());
        return flabelValue * count;
    }

    private void loadFXMLAndSetScene(String fxmlFilePath, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isBookingExists(String name, String date) {
        // Perform a database query to check if the booking already exists based on name and date
        String checkQuery = "SELECT COUNT(*) FROM bookings WHERE name = '"+name+"' AND date = '"+date+"'";
        int count = dao.getCount(checkQuery);

        return count > 0;
    }

}
