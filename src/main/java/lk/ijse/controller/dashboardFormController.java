package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class dashboardFormController {

    @FXML
    private Label lblBorrowed;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalUsers;

    public void initialize() throws SQLException {

    }

}
