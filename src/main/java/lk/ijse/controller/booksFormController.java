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
        txtBookId.setText("");
        txtBookName.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtQty.setText("");
        cmbBranch.setValue(null);
        lblBranchId.setText(null);
        lblBranchName.setText(null);

    }
    private void generateNextBookId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = bookBO.generateNewUserID();
       txtBookId.setText(nextId);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtBookId.getText();
        try {
            bookBO.deleteBook(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Book Deleted !", ButtonType.OK).show();
            generateNextBookId();
            clearFields();
            loadAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Book Not Deleted !", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSaveBookOnAction(ActionEvent event) {
        String id = txtBookId.getText();
        String name = txtBookName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String branchId = lblBranchId.getText();
        try {
            bookBO.addBook(new BookDTO(id, name, author, genre, qty, branchId));
            new Alert(Alert.AlertType.CONFIRMATION,"Book Added !", ButtonType.OK).show();
            generateNextBookId();
            clearFields();
            loadAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Book Not Added !", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateBookOnAction(ActionEvent event) {
        String id = txtBookId.getText();
        String name = txtBookName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String branchId = lblBranchId.getText();
        try {
            bookBO.updateBook(new BookDTO(id, name, author, genre, qty, branchId));
            new Alert(Alert.AlertType.CONFIRMATION,"Book Updated !", ButtonType.OK).show();
            generateNextBookId();
            clearFields();
            loadAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Book Not Update !", ButtonType.OK).show();
        }
    }

    @FXML
    void cmbBranchOnAction(ActionEvent event) {
        String id = (String) cmbBranch.getValue();
        try {
            BranchDTO branchDTO = branchBO.search(id);
            lblBranchId.setText(branchDTO.getId());
            lblBranchName.setText(branchDTO.getLocation());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void loadAllBooks() throws Exception {
        ObservableList<BookTM> obList = FXCollections.observableArrayList();
        try{
            List<BookDTO> dtoList = bookBO.getAllBookes();

            for (BookDTO dto : dtoList) {
                obList.add(
                        new BookTM(
                                dto.getId(),
                                dto.getName(),
                                dto.getAuthor(),
                                dto.getGenre(),
                                dto.getQty(),
                                dto.getBranchId()
                        )
                );
            }
            tblBooks.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void tableListener() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            System.out.println(newValue);
            setData(newValue);
        });
    }
    private void setData(BookTM row) {
        lblBranchId.setText(row.getId());
        txtBookName.setText(row.getName());
        txtAuthor.setText(String.valueOf(row.getAuthor()));
        txtGenre.setText(String.valueOf(row.getGenre()));
        txtQty.setText(String.valueOf(row.getQty()));
        lblBranchId.setText(String.valueOf(row.getBranchId()));
    }

    private void setCellValueFactory() {
        colmnBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmnBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colmnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colmnQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colmnBranch.setCellValueFactory(new PropertyValueFactory<>("id"));
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