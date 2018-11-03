package com.mfd.inventorytracking;

import com.mfd.inventorytracking.buyer.BuyerMenuController;
import com.mfd.inventorytracking.orderbill.OrderBillingMenuController;
import com.mfd.inventorytracking.part.PartMenuController;
import com.mfd.inventorytracking.supplier.SupplierMenuController;
import com.mfd.inventorytracking.supplybill.SupplyBillingMenuController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HomeLayoutController
		implements ContentDisplayPane, Initializable {
	/*@FXML
	private BuyerMenuController supplierMenu;*/
	@FXML private AnchorPane                  displayPane;
	@FXML private SupplyBillingMenuController supplyBillingMenuController;

	@FXML private SupplierMenuController     supplierMenuController;
	@FXML private PartMenuController         partMenuController;
	@FXML private BuyerMenuController        buyerMenuController;
	@FXML private OrderBillingMenuController orderBillingMenuController;

	private Connection conn; // handle to database


	@Override
	public void displayContent(Node content) {

		AnchorPane.setBottomAnchor(content, 0.0);
		AnchorPane.setLeftAnchor(content, 0.0);
		AnchorPane.setRightAnchor(content, 0.0);
		AnchorPane.setTopAnchor(content, 0.0);

		displayPane.getChildren().clear();
		displayPane.getChildren().add(content);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		supplierMenuController.setContentDisplayPlane(this::displayContent);
		supplyBillingMenuController.setContentDisplayPlane(this::displayContent);
		partMenuController.setContentDisplayPlane(this::displayContent);
		buyerMenuController.setContentDisplayPlane(this::displayContent);
		orderBillingMenuController.setContentDisplayPlane(this::displayContent);
	}
}
