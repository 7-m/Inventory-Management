package com.mfd.inventorytracking.supplybill;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static com.mfd.inventorytracking.Utils.Validate.isFieldEmpty;

public class NewSupplyBillController
		implements Initializable {


	private SupplyBill                   supplyBill = new SupplyBill();
	@FXML
	private TableView<SupplyBillingItem> billItemsTable;
	@FXML
	private ChoiceBox<PaymentMode>       paymentChoiceBox;
	@FXML
	private TextField                    supplierNameText;
	@FXML
	private TextField                    billNoText;
	@FXML
	private DatePicker                   dateField;
	@FXML
	private TextArea                     remarksTextArea;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// init billItemsTable
		initBillingTable();
		initChoiceBox();

	}

	private void initChoiceBox() {
		paymentChoiceBox.setItems(FXCollections.observableArrayList(PaymentMode.values()));
	}

	private void initBillingTable() {

		billItemsTable.setEditable(true);
		billItemsTable.setItems(supplyBill.getBilledItems());


		// retrieve columns
		TableColumn<SupplyBillingItem, String> names =
				(TableColumn<SupplyBillingItem, String>) billItemsTable.getColumns().get(0);
		TableColumn<SupplyBillingItem, Number> qty =
				(TableColumn<SupplyBillingItem, Number>) billItemsTable.getColumns().get(1);
		TableColumn<SupplyBillingItem, Number> unitprice =
				(TableColumn<SupplyBillingItem, Number>) billItemsTable.getColumns().get(2);


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
		billItemsTable.getItems().add(new SupplyBillingItem());
	}

	@FXML
	private void onClickDeleteItem(ActionEvent actionEvent) {
		billItemsTable.getItems().remove(billItemsTable.getSelectionModel().getFocusedIndex());
	}

	@FXML
	private void onClickCreateBill(ActionEvent actionEvent) {
		// code to validate input and create record in the db
		if (isFieldEmpty(supplierNameText, billNoText) ||
				isFieldEmpty(dateField) || isFieldEmpty(paymentChoiceBox))
			return;

		supplyBill.setBillno(Integer.valueOf(billNoText.getText()));
		supplyBill.setSupplierName(supplierNameText.getText());
		supplyBill.setDate(Date.valueOf(dateField.getValue().toString()));
		supplyBill.setPaymentMode(paymentChoiceBox.getValue());
		supplyBill.setRemarks(remarksTextArea.getText());

		Queries.insertBill(supplyBill);


	}
}
