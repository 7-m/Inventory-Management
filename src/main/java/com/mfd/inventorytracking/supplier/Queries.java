package com.mfd.inventorytracking.supplier;

import com.mfd.inventorytracking.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Queries {
	static void insertSupplier(Supplier supplier) {
		Connection conn = Context.getConnection();
		try (PreparedStatement insertSupplier = conn.prepareStatement("INSERT INTO SUPPLIER VALUES(?, ?, ?, ?)");) {
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

	static Supplier retrieveSupplier(String supplierName) {
		Connection conn = Context.getConnection();
		Supplier s = null;
		try (PreparedStatement retrieveSupplier = conn.prepareStatement("SELECT * FROM SUPPLIER WHERE Name=?")) {
			retrieveSupplier.setString(1, supplierName);

			ResultSet r = retrieveSupplier.executeQuery();
			if (r.next()) {
				s = new Supplier();
				s.setName(r.getString(1));
				s.setGstin(r.getString(2));
				s.setAddress(r.getString(3));
				s.setContact(r.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());

		}
		return s;
	}

}
