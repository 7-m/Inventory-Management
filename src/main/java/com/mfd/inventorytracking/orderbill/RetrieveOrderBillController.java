package com.mfd.inventorytracking.orderbill;

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


public class RetrieveOrderBillController
		implements Initializable {

	@FXML
	private TextField billNoText;
	@FXML
	private Label     billNoDisplay;
	@FXML
	private Label     dateDisplay;
	@FXML
	private Label     remarksDisplay;
	@FXML
	private Label     modeOfPayDisplay;

	@FXML
	private Label buyerNameDisplay;


	@FXML private TableColumn<OrderBillingItem, String> partColumn;
	@FXML private TableColumn<OrderBillingItem, Number> quantityColumn;
	@FXML private TableColumn<OrderBillingItem, Number> priceColumn;


	@FXML private TableView<OrderBillingItem> billItemsTable;


	@FXML
	private void onClickRetrieveBill(ActionEvent actionEvent) {
		//look up bill with given supp id and bill no
		if (isFieldEmpty(billNoText))
			return;

		OrderBill bill = Queries.retrieve(Integer.parseInt(billNoText.getText()));
		if (bill == null)
			return;

		billNoDisplay.setText(String.valueOf(bill.getBillno()));
		remarksDisplay.setText(bill.getRemarks());
		modeOfPayDisplay.setText(bill.getPaymentMode().toString());
		dateDisplay.setText(bill.getDate().toString());
		buyerNameDisplay.setText(bill.getBuyerName());


		billItemsTable.setItems(bill.getBilledItems());

	}

	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partColumn.setCellValueFactory(param -> param.getValue().part);
		quantityColumn.setCellValueFactory(param -> param.getValue().quantity);
		priceColumn.setCellValueFactory(param -> param.getValue().pricePerUnit);

	}
}
