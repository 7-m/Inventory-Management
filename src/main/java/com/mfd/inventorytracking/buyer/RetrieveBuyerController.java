package com.mfd.inventorytracking.buyer;

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

public class RetrieveBuyerController
		implements Initializable {
	@FXML private TableView<Buyer>           supplierTableView;
	@FXML private TableColumn<Buyer, String> nameColumn;
	@FXML private TableColumn<Buyer, String> gstinColumn;
	@FXML private TableColumn<Buyer, String> addressColumn;
	@FXML private TableColumn<Buyer, String> contactColumn;
	@FXML private TextField                  searchField;
	private       ObservableList<Buyer>      buyers = FXCollections.observableArrayList();
	@FXML private TableColumn<Buyer,Number> outAmtColumn;

	@FXML
	private void onClickSearch(ActionEvent actionEvent) {
		if (Utils.Validate.isFieldEmpty(searchField))
			return;

		Buyer s = Queries.retrieveBuyer(searchField.getText());
		if (s != null) {
			buyers.clear();
			buyers.add(s);
		}


	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		supplierTableView.setItems(buyers);

		nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
		gstinColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getGstin()));
		addressColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress()));
		contactColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getContact()));
		outAmtColumn.setCellValueFactory(param -> new ReadOnlyIntegerWrapper(param.getValue().getOutAmt()));
	}
}
