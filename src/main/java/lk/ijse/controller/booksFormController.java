package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class booksFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSaveBook;

    @FXML
    private Button btnUpdate;

    @FXML
    private JFXComboBox<?> cmbBranch;

    @FXML
    private TableColumn<?, ?> colmnAuthor;

    @FXML
    private TableColumn<?, ?> colmnBookId;

    @FXML
    private TableColumn<?, ?> colmnBookName;

    @FXML
    private TableColumn<?, ?> colmnBranch;

    @FXML
    private TableColumn<?, ?> colmnBranchId;

    @FXML
    private TableColumn<?, ?> colmnGenre;

    @FXML
    private Label lblBranchId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {

    }

    @FXML
    void cmbBranchOnAction(ActionEvent event) {

    }

    @FXML
    void txtAuthorOnAction(ActionEvent event) {

    }

    @FXML
    void txtBookIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtBookNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtGenreOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}