package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class transactionFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colmnBookId;

    @FXML
    private TableColumn<?, ?> colmnBorrowId;

    @FXML
    private TableColumn<?, ?> colmnBorrwedDate;

    @FXML
    private TableColumn<?, ?> colmnGenre;

    @FXML
    private TableColumn<?, ?> colmnMemberId;

    @FXML
    private TableColumn<?, ?> colmnReturnDate;

    @FXML
    private DatePicker datepick;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBorrowDate;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblMemberId;

    @FXML
    private Label lbld;

    @FXML
    private TableView<?> tblTransactions;

    @FXML
    private TextField txtSearchBooks;

    @FXML
    private TextField txtSearchMembers;

    @FXML
    private TextField txtSearchTransactions;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchBooksOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchMembersOnAction(ActionEvent event) {

    }
    @FXML
    void txtSearchTransactionsOnAction(ActionEvent event) {

    }

}
