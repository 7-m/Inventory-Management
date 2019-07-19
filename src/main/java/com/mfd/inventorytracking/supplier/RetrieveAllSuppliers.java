package com.mfd.inventorytracking.supplier;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class RetrieveAllSuppliers
		implements Initializable {
	@FXML private TableView<Supplier> getCol;
	@FXML private TableColumn<Supplier,String> nameCol;
	@FXML private TableColumn<Supplier,String> gstCol;
	@FXML private TableColumn<Supplier,String> addrCol;
	@FXML private TableColumn<Supplier,String> contactCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
		gstCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getGstin()));
		addrCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress()));
		contactCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getContact()));

		getCol.setItems(FXCollections.observableArrayList(Queries.getAllSuppliers()));
	}
}
