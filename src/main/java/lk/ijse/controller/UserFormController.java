package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSaveBook;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colmnAddress;

    @FXML
    private TableColumn<?, ?> colmnEmail;

    @FXML
    private TableColumn<?, ?> colmnId;

    @FXML
    private TableColumn<?, ?> colmnName;

    @FXML
    private TableColumn<?, ?> colmnPhone;

    @FXML
    private TableView<UserTM> tblBooks;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtpassword;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws Exception {
        generateNextUserId();
        loadAllUseres();
        setCellValueFactory();
        tableListener();
    }

    public void clearfield(){
        txtUserId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtpassword.setText("");
        txtSearch.setText("");
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = userBO.generateNewUserID();
        txtUserId.setText(nextId);
    }

    private void loadAllUseres() throws Exception {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        try{
            List<UserDTO> dtoList = userBO.getAllUser();

            for (UserDTO dto : dtoList) {
                obList.add(
                        new UserTM(
                                dto.getId(),
                                dto.getUsername(),
                                dto.getPhoneno(),
                                dto.getEmail(),
                                dto.getPassword()
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

    private void setData(UserTM row) {
        txtUserId.setText(row.getId());
        txtName.setText(row.getUsername());
        txtEmail.setText(row.getEmail());
        txtpassword.setText(row.getPassword());
    }

    private void setCellValueFactory() {
        colmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmnPhone.setCellValueFactory(new PropertyValueFactory<>("phoneno"));
        colmnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colmnAddress.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        try {
            System.out.println(id);
            userBO.deleteUser(id);
            new Alert(Alert.AlertType.CONFIRMATION,"User Deleted !!!", ButtonType.OK).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "User Not Deleted !!!", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String password = txtpassword.getText();
        try {
            System.out.println(id);
            userBO.addUser(new UserDTO(id, name,phone, email, password));
            new Alert(Alert.AlertType.CONFIRMATION,"User Added Successfully !!!", ButtonType.OK).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "User Added Not Successful !!!", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String password = txtpassword.getText();
        try {

            System.out.println(id);
            userBO.updateUser(new UserDTO(id, name,phone, email, password));
            new Alert(Alert.AlertType.CONFIRMATION,"User Updated !!!", ButtonType.OK).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "User Not Updated !!!", ButtonType.OK).show();
        }
    }

    @FXML
    void txtpassworsOnAction(ActionEvent event) {
        btnSaveBook.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        txtpassword.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtPhone.requestFocus();
    }

    @FXML
    void txtPhoneOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            UserDTO userDTO;
            userDTO = userBO.search(id);
            if (userDTO != null) {
                txtUserId.setText(String.valueOf(userDTO.getId()));
                txtName.setText(userDTO.getUsername());
                txtEmail.setText(userDTO.getEmail());
                txtpassword.setText(String.valueOf(userDTO.getPassword()));
            } else {
                new Alert(Alert.AlertType.ERROR,"Student Doesn't exist").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

}
