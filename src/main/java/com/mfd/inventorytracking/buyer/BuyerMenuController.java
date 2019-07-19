package com.mfd.inventorytracking.buyer;

import com.mfd.inventorytracking.AbstractMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BuyerMenuController
		extends AbstractMenuController {



	@FXML
	private void onClickAddBuyer(ActionEvent actionEvent) {
		setView("/layouts/buyer/new_buyer.fxml");
	}

	@FXML
	private void onClickRetrieveBuyer(ActionEvent actionEvent) {
		setView("/layouts/buyer/retrieve_buyer.fxml");
	}

	@FXML
	private void onClickRetrieveAllBuyer(ActionEvent actionEvent) {
		setView("/layouts/buyer/retrieve_all_buyer.fxml");
	}
}
