package com.mfd.inventorytracking.supplier;

import com.mfd.inventorytracking.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewSupplierController {
	@FXML private TextField nameField;
	@FXML private TextField gstField;
	@FXML private TextField addressField;
	@FXML private TextField contactField;

	@FXML
	private void onClickAddSupplier(ActionEvent actionEvent) {
		if (Utils.Validate.isFieldEmpty(nameField, gstField, addressField, contactField))
			return;

		Supplier supplier =
				new Supplier(nameField.getText(), gstField.getText(), addressField.getText(), contactField.getText(),0);
		Queries.insertSupplier(
				supplier);
	}
}
