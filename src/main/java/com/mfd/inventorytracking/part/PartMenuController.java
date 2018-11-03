package com.mfd.inventorytracking.part;

import com.mfd.inventorytracking.AbstractMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PartMenuController extends AbstractMenuController {

	@FXML
	private void onClickAddPart(ActionEvent actionEvent) {
		setView("/layouts/part/new_part.fxml");
	}

	@FXML
	private void onClickRetrievePart(ActionEvent actionEvent) {
		setView("/layouts/part/retrieve_part.fxml");
	}
}
