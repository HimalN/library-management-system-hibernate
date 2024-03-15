package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.tm.BookTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


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
    private TableView<BookTM> tblBooks;

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
    private Label lblBranchName;

    @FXML
    private TableColumn<?, ?> colmnQty;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    public void initialize(){

    }
    private void clearFields(){

    }


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
    private void setData(BookTM row) {

    }

    private void setCellValueFactory() {

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