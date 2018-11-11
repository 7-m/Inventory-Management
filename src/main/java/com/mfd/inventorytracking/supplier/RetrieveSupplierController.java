package com.mfd.inventorytracking.supplier;

import com.mfd.inventorytracking.Utils;
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

import java.net.URL;
import java.util.ResourceBundle;

public class RetrieveSupplierController
		implements Initializable {
	@FXML private TableView<Supplier>           supplierTableView;
	@FXML private TableColumn<Supplier, String> nameColumn;
	@FXML private TableColumn<Supplier, String> gstinColumn;
	@FXML private TableColumn<Supplier, String> addressColumn;
	@FXML private TableColumn<Supplier, String> contactColumn;
	@FXML private TextField                     searchField;
	private       ObservableList<Supplier>      suppliers = FXCollections.observableArrayList();
	@FXML private TableColumn<Supplier,Number> outAmtColumn;

	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
		if (Utils.Validate.isFieldEmpty(searchField))
			return;

		Supplier s = Queries.retrieveSupplier(searchField.getText());
		if (s != null) {
			suppliers.clear();
			suppliers.add(s);
		}


	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		supplierTableView.setItems(suppliers);

		nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
		gstinColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getGstin()));
		addressColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress()));
		contactColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getContact()));
		outAmtColumn.setCellValueFactory(param -> new ReadOnlyIntegerWrapper(param.getValue().getOutAmt()));
	}
}
