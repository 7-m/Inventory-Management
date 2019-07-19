package com.mfd.inventorytracking.part;

import com.mfd.inventorytracking.Context;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Queries {


	static void insertPart(Part part) {
		Connection connection = Context.getConnection();
		try (PreparedStatement insertpartQuery = connection
				.prepareStatement("INSERT INTO PART VALUES (?, ?, ?, ?, ?);")) {

			insertpartQuery

					.setString(1, part.getName());
			insertpartQuery.setInt(2, part.getQuantity());
			insertpartQuery.setFloat(3, part.getTaxSlab());


			Blob imgblob = null;
			if (part.getImage() != null) {
				imgblob = connection.createBlob();
				imgblob.setBytes(1, part.getImage());
			}

			insertpartQuery.setBlob(4, imgblob);

			insertpartQuery.setString(5, part.getDesc());
			insertpartQuery.execute();


		} catch (SQLException e) {

			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());
		}
	}

	/**
	 Retrieves a part if found by the give string name else null
	 @param name
	 @return
	 */
	static Part retrievePart(String name) {
		Connection connection = Context.getConnection();
		Part p = null;

		try (PreparedStatement partQuery = connection
				.prepareStatement("SELECT * FROM PART WHERE Name=?;")
			 ) {

			partQuery.setString(1, name);
			ResultSet r = partQuery.executeQuery();


			if (r.next()) {
				p = new Part();
				p.setName(r.getString(1));
				p.setQuantity(r.getInt(2));
				p.setTaxSlab(r.getFloat(3));

				Blob imgBlob = r.getBlob(4);
				if (imgBlob != null)
					p.setImage(imgBlob.getBytes(1, Math.toIntExact(imgBlob.length())));

				p.setDesc(r.getString(5));
			}


		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());
		}
		return p;
	}


	public static List<Part> retrieveAllParts() {

		Connection connection = Context.getConnection();
		List<Part> parts = new ArrayList<>();

		try (PreparedStatement partQuery = connection
				.prepareStatement("SELECT * FROM PART;")
		) {

			ResultSet r = partQuery.executeQuery();


			while (r.next()) {
				Part p =new Part();
				p.setName(r.getString(1));
				p.setQuantity(r.getInt(2));
				p.setTaxSlab(r.getFloat(3));

				Blob imgBlob = r.getBlob(4);
				if (imgBlob != null)
					p.setImage(imgBlob.getBytes(1, Math.toIntExact(imgBlob.length())));

				p.setDesc(r.getString(5));
				parts.add(p);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			Context.displayErrorWindow(e.getMessage());
		}
		return parts;

	}
}
