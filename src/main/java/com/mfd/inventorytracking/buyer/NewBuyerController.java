package com.mfd.inventorytracking.buyer;

import com.mfd.inventorytracking.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewBuyerController {


	@FXML private TextField nameField;
	@FXML private TextField gstField;
	@FXML private TextField addressField;
	@FXML private TextField contactField;

	@FXML
	private void onClickAddBuyer(ActionEvent actionEvent) {

		if (Utils.Validate.isFieldEmpty(nameField, gstField, addressField, contactField))
			return;

		Buyer supplier =
				new Buyer(nameField.getText(), gstField.getText(), addressField.getText(), contactField.getText(),0);
		Queries.insertBuyer(supplier);
	}
}
