package com.mfd.inventorytracking.buyer;

import com.mfd.inventorytracking.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Queries {
	static void insertBuyer(Buyer supplier) {
		Connection conn = Context.getConnection();
		try (PreparedStatement insertSupplier = conn.prepareStatement("INSERT INTO BUYER VALUES(?, ?, ?, ?)");) {
			insertSupplier.setString(1, supplier.getName());
			insertSupplier.setString(2, supplier.getGstin());
			insertSupplier.setString(3, supplier.getAddress());
			insertSupplier.setString(4, supplier.getContact());
			insertSupplier.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
	}

	static Buyer retrieveBuyer(String supplierName) {
		Connection conn = Context.getConnection();
		Buyer s = null;
		try (PreparedStatement retrieveSupplier = conn
				.prepareStatement("SELECT * FROM BUYER WHERE Name=?"); PreparedStatement debtqry = conn
				.prepareStatement(" select  SUM(ORDER_BILLING_ITEM_SUMMER(s.Bill_No)) from " +
						"ORDER_BILL s WHERE s.Buyer_Name= ?");) {
			retrieveSupplier.setString(1, supplierName);

			debtqry.setString(1, supplierName);

			ResultSet debt = debtqry.executeQuery();
			ResultSet r = retrieveSupplier.executeQuery();
			if (r.next() && debt.next()) {
				s = new Buyer();
				s.setName(r.getString(1));
				s.setGstin(r.getString(2));
				s.setAddress(r.getString(3));
				s.setContact(r.getString(4));
				s.setCredit(debt.getInt(1));
			}

			//fetch debt


		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
		return s;
	}

	public static List<Buyer> getAllBuyers() {
		List<Buyer> suppliers = new ArrayList<>();

		Connection conn = Context.getConnection();

		try (ResultSet rs = conn.prepareStatement("SELECT * FROM BUYER;").executeQuery();) {
			while (rs.next()) {
				Buyer s = new Buyer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 0);
				suppliers.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());
		}

		return suppliers;
	}

}
