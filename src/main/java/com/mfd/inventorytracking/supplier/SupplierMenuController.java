package com.mfd.inventorytracking.supplier;

import com.mfd.inventorytracking.AbstractMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SupplierMenuController
		extends AbstractMenuController {



	@FXML
	private void onClickAddSupplier(ActionEvent actionEvent) {
		setView("/layouts/supplier/new_supplier.fxml");
	}

	@FXML
	private void onClickRetrieveSupplier(ActionEvent actionEvent) {
		setView("/layouts/supplier/retrieve_supplier.fxml");
	}

	@FXML
	private void onClickRetrieveAllSupplier(ActionEvent actionEvent) {
		setView("/layouts/supplier/retrieve_all_supplier.fxml");
	}
}
