package com.mfd.inventorytracking.orderbill;

import com.mfd.inventorytracking.Context;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
	public static void insertBill(OrderBill orderBill) {
		Connection conn = Context.getConnection();
		//first insert into order_bill then order billing

		try (PreparedStatement orderBillQuery = conn.prepareStatement(
				"INSERT INTO ORDER_BILL VALUES(?, ?, ?, ?, ?);");
			 PreparedStatement orderBillingQuery = conn
					 .prepareStatement("INSERT INTO ORDER_BILLING VALUES(?, ?, ?, ?);")) {

			//to prevent incomplete entries in ORDER_BILL and ORDER_BILLING if any query fails
			conn.setAutoCommit(false);

			orderBillQuery.setInt(1, orderBill.getBillno());
			orderBillQuery.setString(2, orderBill.getBuyerName());
			orderBillQuery.setDate(3, orderBill.getDate());
			orderBillQuery.setString(4, orderBill.getRemarks());
			orderBillQuery.setNString(5, orderBill.getPaymentMode().toString());
			orderBillQuery.execute();

			for (OrderBillingItem i : orderBill.getBilledItems()) {

				orderBillingQuery.setString(1, i.part.get());
				orderBillingQuery.setInt(2, orderBill.getBillno());
				orderBillingQuery.setInt(3, i.quantity.get());
				orderBillingQuery.setFloat(4, i.pricePerUnit.get());
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

	public static OrderBill retrieve(int billNo) {
		OrderBill bill = new OrderBill();
		Connection conn = Context.getConnection();

		//bill no. is sufficient in billing_order to retrieve all details
		try (PreparedStatement billQuery = conn.prepareStatement("SELECT * FROM ORDER_BILL WHERE Bill_No=? ;");
			 PreparedStatement billItemsQuery = conn.prepareStatement("SELECT * FROM ORDER_BILLING WHERE Bill_No=?;")) {
			billQuery.setInt(1, billNo);
			ResultSet billres = billQuery.executeQuery();

			if (!billres.next()) return null;

			bill.setBillno(billres.getInt(1));
			bill.setBuyerName(billres.getString(2));
			bill.setDate(billres.getDate(3));
			bill.setRemarks(billres.getString(4));
			bill.setPaymentMode(PaymentMode.valueOf(billres.getString(5)));

			//retrieve billed items
			bill.setBilledItems(FXCollections.observableArrayList());
			billItemsQuery.setInt(1, billNo);

			ResultSet itemres = billItemsQuery.executeQuery();
			while (itemres.next()) {
				OrderBillingItem i = new OrderBillingItem();

				i.part.set(itemres.getString(1));
				i.quantity.set(itemres.getInt(3));
				i.pricePerUnit.set(itemres.getFloat(4));

				bill.getBilledItems().add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
		return bill;
	}
}
