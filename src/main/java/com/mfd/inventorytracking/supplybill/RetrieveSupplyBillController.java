package com.mfd.inventorytracking.supplybill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.mfd.inventorytracking.Utils.Validate.isFieldEmpty;


public class RetrieveSupplyBillController
		implements Initializable {

	@FXML private TextField billNoText;
	@FXML private Label     billNoDisplay;
	@FXML private Label     dateDisplay;
	@FXML private Label     remarksDisplay;
	@FXML private Label     modeOfPayDisplay;

	@FXML private Label supplierNameDisplay;


	@FXML private TableColumn<SupplyBillingItem, String> partColumn;
	@FXML private TableColumn<SupplyBillingItem, Number> quantityColumn;
	@FXML private TableColumn<SupplyBillingItem, Number> priceColumn;


	@FXML private TableView<SupplyBillingItem> billItemsTable;
	@FXML private TextField                    supplierNameText;


	@FXML
	private void onClickRetrieveBill(ActionEvent actionEvent) {
		//look up bill with given supp id and bill no
		if (isFieldEmpty(supplierNameText, billNoText))
			return;

		SupplyBill bill = Queries.retrieve(supplierNameText.getText(), Integer.parseInt(billNoText.getText()));
		if (bill == null)
			return;

		billNoDisplay.setText(String.valueOf(bill.getBillno()));
		remarksDisplay.setText(bill.getRemarks());
		modeOfPayDisplay.setText(bill.getPaymentMode().toString());
		dateDisplay.setText(bill.getDate().toString());
		supplierNameDisplay.setText(bill.getSupplierName());


		billItemsTable.setItems(bill.getBilledItems());

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partColumn.setCellValueFactory(param -> param.getValue().part);
		quantityColumn.setCellValueFactory(param -> param.getValue().quantity);
		priceColumn.setCellValueFactory(param -> param.getValue().pricePerUnit);

	}
}
