package com.mfd.inventorytracking.supplybill;

import com.mfd.inventorytracking.Context;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
	public static void insertBill(SupplyBill supplyBill) {
		Connection conn = Context.getConnection();
		//first insert into order_bill then order billing

		try (PreparedStatement orderBillQuery = conn.prepareStatement(
				"INSERT INTO SUPPLY_BILL VALUES(?, ?, ?, ?, ?);");
			 PreparedStatement orderBillingQuery = conn
					 .prepareStatement("INSERT INTO SUPPLY_BILLING VALUES(?, ?, ?, ?, ?);")) {

			//to prevent incomplete entries in SUPPLY_BILL  and SUPPLY_BILLING
			conn.setAutoCommit(false);

			orderBillQuery.setInt(1, supplyBill.getBillno());
			orderBillQuery.setString(2, supplyBill.getSupplierName());
			orderBillQuery.setDate(3, supplyBill.getDate());
			orderBillQuery.setString(4, supplyBill.getRemarks());
			orderBillQuery.setNString(5, supplyBill.getPaymentMode().toString());
			orderBillQuery.execute();

			for (SupplyBillingItem i : supplyBill.getBilledItems()) {

				orderBillingQuery.setString(1, i.part.get());
				orderBillingQuery.setString(2, supplyBill.getSupplierName());
				orderBillingQuery.setInt(3, supplyBill.getBillno());
				orderBillingQuery.setInt(4, i.quantity.get());
				orderBillingQuery.setFloat(5, i.pricePerUnit.get());
				orderBillingQuery.execute();
			}
		} catch (SQLException e) {

			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static SupplyBill retrieve(String supplierName, int billNo) {
		SupplyBill bill = new SupplyBill();
		Connection conn = Context.getConnection();
		//bill no. is sufficient in billing_order to retrieve all details
		try (PreparedStatement billQuery = conn
				.prepareStatement("SELECT * FROM SUPPLY_BILL WHERE Supplier_Name=? AND Bill_No=? ;");
			 PreparedStatement billItemsQuery = conn
					 .prepareStatement("SELECT * FROM SUPPLY_BILLING WHERE Supplier_Name=? AND Bill_No=?;")) {
			billQuery.setString(1, supplierName);
			billQuery.setInt(2, billNo);
			ResultSet billres = billQuery.executeQuery();

			if (!billres.next()) return null;


			bill.setBillno(billres.getInt(1));
			bill.setSupplierName(billres.getString(2));
			bill.setDate(billres.getDate(3));
			bill.setRemarks(billres.getString(4));
			bill.setPaymentMode(PaymentMode.valueOf(billres.getString(5)));

			//retrieve billed items
			bill.setBilledItems(FXCollections.observableArrayList());
			billItemsQuery.setString(1, supplierName);
			billItemsQuery.setInt(2,billNo);
			ResultSet itemres = billItemsQuery.executeQuery();
			while (itemres.next()) {
				SupplyBillingItem
						i = new SupplyBillingItem();

				i.part.set(itemres.getString(1));
				i.pricePerUnit.set(itemres.getFloat(4));
				i.quantity.set(itemres.getInt(5));
				bill.getBilledItems().add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
		return bill;
	}
}
