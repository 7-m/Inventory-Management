package com.mfd.inventorytracking.orderbill;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import static com.mfd.inventorytracking.Utils.Validate.isFieldEmpty;

public class NewOrderBillController
		implements Initializable {

	private Connection                  connection;
	@FXML
	private TableView<OrderBillingItem> billItemsTable;
	@FXML
	private ChoiceBox<PaymentMode>      paymentChoiceBox;
	@FXML
	private TextField                   supplierNameText;
	@FXML
	private TextField                   billNoText;
	@FXML
	private DatePicker                  dateField;
	@FXML
	private TextArea                    remarksTextArea;


	public void setDatabseConnection(Connection connection) {
		this.connection = connection;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// init billItemsTable
		initBillingTable();
		initChoiceBox();

		remarksTextArea.setEditable(false);

	}

	private void initChoiceBox() {
		paymentChoiceBox.setItems(FXCollections.observableArrayList(PaymentMode.values()));
	}

	private void initBillingTable() {

		billItemsTable.setEditable(true);
		billItemsTable.setItems(FXCollections.observableArrayList());


		// retrieve columns
		TableColumn<OrderBillingItem, String> names = (TableColumn<OrderBillingItem, String>) billItemsTable.getColumns().get(0);
		TableColumn<OrderBillingItem, Number> qty = (TableColumn<OrderBillingItem, Number>) billItemsTable.getColumns().get(1);
		TableColumn<OrderBillingItem, Number> unitprice =
				(TableColumn<OrderBillingItem, Number>) billItemsTable.getColumns().get(2);


		// setup colums for dispaying
		names.setCellValueFactory(param -> param.getValue().part);
		qty.setCellValueFactory(param -> param.getValue().quantity);
		unitprice.setCellValueFactory(param -> param.getValue().pricePerUnit);


		// set up columns for editing
		names.setCellFactory(TextFieldTableCell.forTableColumn());
		names.setOnEditCommit(event -> event.getRowValue().part.set(event.getNewValue()));

		qty.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		qty.setOnEditCommit(event -> event.getRowValue().quantity.set(event.getNewValue().intValue()));

		unitprice.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		unitprice.setOnEditCommit(event -> event.getRowValue().pricePerUnit.set(event.getNewValue().intValue()));
	}

	@FXML
	private void onClickAddItem(ActionEvent actionEvent) {
		billItemsTable.getItems().add(new OrderBillingItem());
	}

	@FXML
	private void onClickDeleteItem(ActionEvent actionEvent) {
		billItemsTable.getItems().remove(billItemsTable.getSelectionModel().getFocusedIndex());
	}

	@FXML
	private void onClickCreateBill(ActionEvent actionEvent) {
		// code to validate input and create record in the db
		if (isFieldEmpty(supplierNameText, billNoText, remarksTextArea) ||
				isFieldEmpty(dateField) || isFieldEmpty(paymentChoiceBox))
			return;

		//do some validation checks
		//update database here


	}
}
