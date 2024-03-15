package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.TransactionDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.TransactionTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
    private TableView<TransactionTM> tblTransactions;

    @FXML
    private TextField txtSearchBooks;
    @FXML
    private TableColumn<?, ?> colmnBookId1;

    @FXML
    private TextField txtSearchMembers;

    @FXML
    private TextField txtSearchTransactions;
    @FXML
    private ComboBox<String> cmbMember;
    @FXML
    private ComboBox<String> cmbBooks;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    public void initialize() throws Exception {
        generateNextUserId();
        loadAllTransactions();
        setCellValueFactory();
        tableListener();
        loadMemberIds();
        loadBookIds();
    }

    public void clearfield(){
        lbld.setText("");
        cmbMember.setValue(null);
        lblMemberId.setText("");
        cmbBooks.setValue(null);
        lblBookName.setText("");
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = transactionBO.generateNewTransactionID();
        lbld.setText(nextId);
    }

    private void loadAllTransactions() throws Exception {
        ObservableList<TransactionTM> obList = FXCollections.observableArrayList();
        try{
            List<TransactionDTO> dtoList = transactionBO.getAllTransaction();

            for (TransactionDTO dto : dtoList) {
                obList.add(
                        new TransactionTM(
                                dto.getTranID(),
                                dto.getMemID(),
                                dto.getMemName(),
                                dto.getBookID(),
                                dto.getBookName(),
                                dto.getTranDate(),
                                dto.getTranEndDate()
                        )
                );
            }
            tblTransactions.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void tableListener() {
        tblTransactions.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            System.out.println(newValue);
            setData(newValue);
        });
    }

    private void setData(TransactionTM row) {
        lbld.setText(row.getTranID());
        lblMemberId.setText(String.valueOf(row.getMemName()));
        lblBookName.setText(row.getBookName());
        datepick.setValue(LocalDate.parse(row.getTranEndDate()));
    }

    private void setCellValueFactory() {
        colmnBorrowId.setCellValueFactory(new PropertyValueFactory<>("tranID"));
        colmnMemberId.setCellValueFactory(new PropertyValueFactory<>("memID"));
        colmnMemberId.setCellValueFactory(new PropertyValueFactory<>("memName"));
        colmnBookId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colmnBookId1.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colmnBorrwedDate.setCellValueFactory(new PropertyValueFactory<>("tranDate"));
        colmnReturnDate.setCellValueFactory(new PropertyValueFactory<>("tranEndDate"));
    }

    private void loadBookIds() throws Exception {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<BookDTO> idList = bookBO.getAllBookes();

            for (BookDTO dto : idList) {
                obList.add(dto.getId());
            }

            cmbBooks.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMemberIds() throws Exception {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<UserDTO> idList = userBO.getAllUser();

            for (UserDTO dto : idList) {
                obList.add(dto.getId());
            }

            cmbMember.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lbld.getText();
        String bookID = cmbBooks.getValue();

        try {
            transactionBO.deleteTransaction(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Book Borrowed !", ButtonType.OK).show();
            transactionBO.returnedBook(bookID);
            new Alert(Alert.AlertType.CONFIRMATION,"QTY Updated !", ButtonType.OK).show();
            generateNextUserId();
            loadAllTransactions();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Book Not Added !", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lbld.getText();
        String memID = cmbMember.getValue();
        String memName = lblBookName.getText();
        String bookID = cmbBooks.getId();
        String bookName = lblBookName.getText();
        String borrowDateValue = lblBorrowDate.getText();
        String returnDateValue = String.valueOf(datepick.getValue());

        try {
            transactionBO.addTransaction(new TransactionDTO(id, memID, memName, bookID,bookName,borrowDateValue,returnDateValue));
            new Alert(Alert.AlertType.CONFIRMATION,"Book Borrowed !", ButtonType.OK).show();
            transactionBO.borrowedBook(bookID);
            new Alert(Alert.AlertType.CONFIRMATION,"QTY Updated !", ButtonType.OK).show();
            generateNextUserId();
            loadAllTransactions();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Book Not Added !", ButtonType.OK).show();
        }

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

    @FXML
    void cmbMemberOnAction(ActionEvent event) {
        String id = cmbMember.getValue();
        try {
            UserDTO userDTO = userBO.search(id);
            lblMemberId.setText(userDTO.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void cmbBooksOnAction(ActionEvent event) {
        String id = cmbBooks.getValue();
        try {
            BookDTO branchDTO = bookBO.search(id);
            lblBookName.setText(branchDTO.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
