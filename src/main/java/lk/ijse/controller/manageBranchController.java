package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.tm.BranchTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class manageBranchController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSaveBranch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colmnBranchId;

    @FXML
    private TableColumn<?, ?> colmnBranchLocation;

    @FXML
    private TableColumn<?, ?> colmnEmail;

    @FXML
    private TableColumn<?, ?> colmnMobile;

    @FXML
    private TableView<BranchTM> tblBooks;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtBranchId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearch;
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    public void initialize() throws Exception {
        generateNextUserId();
        loadAllBranches();
        setCellValueFactory();
        tableListener();
    }
    public void clearfield(){
        txtBranchId.setText("");
        txtLocation.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtSearch.setText("");
    }

    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = branchBO.generateNewUserID();
        txtBranchId.setText(nextId);
    }

    private void loadAllBranches() throws Exception {
        ObservableList<BranchTM> obList = FXCollections.observableArrayList();
        try{
            List<BranchDTO> dtoList = branchBO.getAllBranches();

            for (BranchDTO dto : dtoList) {
                obList.add(
                        new BranchTM(
                                dto.getId(),
                                dto.getLocation(),
                                dto.getEmail(),
                                dto.getMobile()
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

    private void setData(BranchTM row) {
        txtBranchId.setText(row.getId());
        txtLocation.setText(row.getLocation());
        txtEmail.setText(String.valueOf(row.getEmail()));
        txtPhone.setText(String.valueOf(row.getMobile()));
    }

    private void setCellValueFactory() {
        colmnBranchId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmnBranchLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colmnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colmnMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearfield();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtBranchId.getText();
        try {
            branchBO.deleteBranch(id);
            new Alert(Alert.AlertType.CONFIRMATION,"Branch Added !", ButtonType.OK).show();
            generateNextUserId();
            clearfield();
            loadAllBranches();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Branch Not Added !", ButtonType.OK).show();
        }
    }

    @FXML
    void btnSaveBranchOnAction(ActionEvent event) {
        String id = txtBranchId.getText();
        String location = txtLocation.getText();
        String email = txtEmail.getText();
        String mobile = txtPhone.getText();
        try {
            branchBO.addBranch(new BranchDTO(id, location, email, mobile));
            new Alert(Alert.AlertType.CONFIRMATION,"Branch Added !", ButtonType.OK).show();
            generateNextUserId();
            clearfield();
            loadAllBranches();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Branch Not Added !", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtBranchId.getText();
        String location = txtLocation.getText();
        String email = txtEmail.getText();
        String mobile = txtPhone.getText();
        try {
            branchBO.updateBranch(new BranchDTO(id, location, email, mobile ));
            new Alert(Alert.AlertType.CONFIRMATION,"Branch Updated !", ButtonType.OK).show();
            generateNextUserId();
            clearfield();
            loadAllBranches();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Branch Not Updated !", ButtonType.OK).show();
        }
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            BranchDTO branchDTO;
            branchDTO = branchBO.search(id);
            if (branchDTO != null) {
                txtBranchId.setText(String.valueOf(branchDTO.getId()));
                txtLocation.setText(branchDTO.getLocation());
                txtEmail.setText(branchDTO.getEmail());
                txtPhone.setText(String.valueOf(branchDTO.getMobile()));
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
    void txtBranchIdOnAction(ActionEvent event) {
        txtLocation.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        txtPhone.requestFocus();
    }

    @FXML
    void txtLocationOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }


    @FXML
    void txtPhoneOnAction(ActionEvent event) {
        btnSaveBranch.requestFocus();
    }
}