package com.example.ticketsbus;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane adminDrawer;

    @FXML
    private Button adminNav;

    @FXML
    private JFXButton dBoard;

    @FXML
    private JFXButton mBooking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), adminDrawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), adminDrawer);
        adminNav.setOnAction((ActionEvent evt) -> {
            if (adminDrawer.getTranslateX() != 0) {
                openNav.play();
            } else {
                closeNav.setToX(-(adminDrawer.getWidth()));
                closeNav.play();
            }
        });
    }
}
