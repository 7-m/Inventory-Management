package com.mfd.inventorytracking.orderbill;

import com.mfd.inventorytracking.Context;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
	public static void insertBill(OrderBill orderBill) {

		//retrieve connection to database
		Connection conn = Context.getConnection();
		//first insert into order_bill then order billing

		try (PreparedStatement orderBillQuery = conn.prepareStatement(
				"INSERT INTO ORDER_BILL VALUES(?, ?, ?, ?, ?);");
			 PreparedStatement orderBillingQuery = conn
					 .prepareStatement("INSERT INTO ORDER_BILLING VALUES(?, ?, ?, ?);")) {

			//to prevent incomplete entries in ORDER_BILL and ORDER_BILLING if any query fails
			// create this as a single transaction
			conn.setAutoCommit(false);

			// prepare query with data
			orderBillQuery.setInt(1, orderBill.getBillno());
			orderBillQuery.setString(2, orderBill.getBuyerName());
			orderBillQuery.setDate(3, orderBill.getDate());
			orderBillQuery.setString(4, orderBill.getRemarks());
			orderBillQuery.setNString(5, orderBill.getPaymentMode().toString());
			orderBillQuery.execute();

			// prepare query for each billed item
			for (OrderBillingItem i : orderBill.getBilledItems()) {

				orderBillingQuery.setString(1, i.part.get());
				orderBillingQuery.setInt(2, orderBill.getBillno());
				orderBillingQuery.setInt(3, i.quantity.get());
				orderBillingQuery.setFloat(4, i.pricePerUnit.get());
				// add the current item to database
				orderBillingQuery.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// display error if any
			Context.displayErrorWindow(e.getMessage());
			try {
				// rollback the transaction to prevent putting db into inconsistent state
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				// commit tranaction after success or rollback in case of failure
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static OrderBill retrieve(int billNo) {
		//Create the bill model for storing details of the bill
		OrderBill bill = new OrderBill();

		//retrieve the connection to database
		Connection conn = Context.getConnection();

		//bill no. is sufficient in billing_order to retrieve all details
		try (PreparedStatement billQuery = conn.prepareStatement("SELECT * FROM ORDER_BILL WHERE Bill_No=? ;");
			 PreparedStatement billItemsQuery = conn.prepareStatement("SELECT * FROM ORDER_BILLING WHERE Bill_No=?;")) {

			billQuery.setInt(1, billNo);

			//execute query
			ResultSet billres = billQuery.executeQuery();

			if (!billres.next()) return null;

			//retrieve the contents of the result and store in model
			bill.setBillno(billres.getInt(1));
			bill.setBuyerName(billres.getString(2));
			bill.setDate(billres.getDate(3));
			bill.setRemarks(billres.getString(4));
			bill.setPaymentMode(PaymentMode.valueOf(billres.getString(5)));

			//retrieve billed items
			bill.setBilledItems(FXCollections.observableArrayList());
			billItemsQuery.setInt(1, billNo);

			ResultSet itemres = billItemsQuery.executeQuery();

			//store details of each billed item in model
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


	public static List<OrderBill> retrieveAllBills() {
		List<OrderBill> bills = new ArrayList<>();

		Connection con = Context.getConnection();

		try (Statement qr = con.createStatement(); ResultSet rs = qr
				.executeQuery("SELECT Bill_No, Buyer_Name, Order_Date FROM ORDER_BILL;")) {

			while (rs.next()) {
				OrderBill b=new OrderBill();
				b.setBillno(rs.getInt(1));
				b.setBuyerName(rs.getString(2));
				b.setDate(rs.getDate(3));
				bills.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
		return bills;
	}
}
