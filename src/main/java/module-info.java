module com.example.ticketsbus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires org.controlsfx.controls;


    opens com.example.ticketsbus to javafx.fxml;
    exports com.example.ticketsbus;
}