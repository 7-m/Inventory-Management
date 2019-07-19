package com.mfd.inventorytracking.buyer;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class RetrieveAllBuyersController
		implements Initializable {
	@FXML private TableView<Buyer> getCol;
	@FXML private TableColumn<Buyer,String> nameCol;
	@FXML private TableColumn<Buyer,String> gstCol;
	@FXML private TableColumn<Buyer,String> addrCol;
	@FXML private TableColumn<Buyer,String> contactCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
		gstCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getGstin()));
		addrCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress()));
		contactCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getContact()));

		getCol.setItems(FXCollections.observableArrayList(Queries.getAllBuyers()));
	}
}
