package com.mfd.inventorytracking.part;

import com.mfd.inventorytracking.Utils;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class RetrievePartController
		implements Initializable {
	@FXML private TextField                 partNameField;
	@FXML private TableView<Part>           partsTable;
	private       ObservableList<Part>      parts = FXCollections.observableArrayList();
	@FXML private TableColumn<Part, String> nameColumn;
	@FXML private TableColumn<Part, Number> taxColumn;
	@FXML private TableColumn<Part, Number> quantityColumn;
	@FXML private TableColumn<Part, String> descColumn;
	@FXML private ImageView                 imageView;


	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
		if (Utils.Validate.isFieldEmpty(partNameField))
			return;

		Part p = Queries.retrievePart(partNameField.getText());
		if (p != null) parts.add(p);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		partsTable.setItems(parts);
		nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));

		quantityColumn.setCellValueFactory(param -> new ReadOnlyIntegerWrapper(param.getValue().getQuantity()));

		taxColumn.setCellValueFactory(param -> new ReadOnlyFloatWrapper(param.getValue().getTaxSlab()));

		descColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDesc()));


		partsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
				imageView.setImage(new Image(new ByteArrayInputStream(newValue.getImage()))));

	}

	@FXML
	private void retrieveAllParts(ActionEvent actionEvent) {
		partsTable.setItems(FXCollections.observableArrayList(Queries.retrieveAllParts()));
	}
}
