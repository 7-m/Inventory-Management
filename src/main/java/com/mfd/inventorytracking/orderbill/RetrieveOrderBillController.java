package com.mfd.inventorytracking.orderbill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static com.mfd.inventorytracking.Utils.Validate.*;

import java.sql.Connection;


public class RetrieveOrderBillController {
	@FXML
	private TextField supplierNameText;
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
	private TableView billItemsTable;
	@FXML
	private Label     supplierNameDisplay;

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	@FXML
	private void onClickRetrieveBill(ActionEvent actionEvent) {
		//look up bill with given supp id and bill no
		if (isFieldEmpty(supplierNameText,billNoText))
			return;

		//to make things neat wrap the results section in an anchor pane and make it visible only when a valid
		// result is retrived
		//fetch bill, display appropriate message if found or not found

	}

	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
	}
}
