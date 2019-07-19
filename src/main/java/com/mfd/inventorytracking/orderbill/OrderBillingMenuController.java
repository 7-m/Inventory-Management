package com.mfd.inventorytracking.orderbill;

import com.mfd.inventorytracking.AbstractMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OrderBillingMenuController
		extends AbstractMenuController {


	@FXML
	private void onClickCreateNewBill(ActionEvent actionEvent) {
		setView("/layouts/order_billing/new_bill.fxml");
	}

	@FXML
	private void onClickRetrieveBill(ActionEvent actionEvent) {
		setView("/layouts/order_billing/retrieve_bill.fxml");
	}


	@FXML
	private void onClickRetrieveAllBills(ActionEvent actionEvent) {
		setView("/layouts/order_billing/retrieve_all_order.fxml");
	}
}
