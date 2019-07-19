package com.mfd.inventorytracking.orderbill;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AllOrdersController implements Initializable {
	@FXML private TableColumn<OrderBill, Number> billNoCol;
	@FXML private TableColumn<OrderBill, String> buyerNameCol;
	@FXML private TableColumn<OrderBill, Date>   dateCol;
	@FXML private TableView<OrderBill>           allOrders;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		billNoCol.setCellValueFactory(param -> new ReadOnlyIntegerWrapper(param.getValue().getBillno()));
		buyerNameCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getBuyerName()));
		dateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDate()));
		allOrders.setItems(FXCollections.observableArrayList(Queries.retrieveAllBills()));
	}
}
