package com.mfd.inventorytracking.supplier;

import com.mfd.inventorytracking.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RetrieveSupplierController {

	@FXML private TextField searchField;
	@FXML private Label     nameField;

	@FXML private Label addrField;
	@FXML private Label contactFiled;
	@FXML private Label gstField;
	@FXML private Label debtField;

	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
		if (Utils.Validate.isFieldEmpty(searchField))
			return;

		Supplier s = Queries.retrieveSupplier(searchField.getText());
		if (s != null) {
			nameField.setText(s.getName());
			addrField.setText(s.getAddress());
			gstField.setText(s.getGstin());
			contactFiled.setText(s.getContact());
			debtField.setText(String.valueOf(s.getDebt()));


		}

	}


}
