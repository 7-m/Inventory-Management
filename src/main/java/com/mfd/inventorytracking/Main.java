package com.mfd.inventorytracking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Main
		extends Application {

	private Connection connection;

	public static void main(String... args) throws Exception {

		if (args.length < 3) {
			System.out.println(
					"usage: java -jar <app-name>.jar --address=<address>:<port> --user=<user> --password<passwd>");
			System.exit(0);
		}
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//establish connection
		connectToDatabse();
		Context.setConnection(connection);

		URL resource = getClass().getResource("/layouts/home_layout.fxml");
		FXMLLoader loader = new FXMLLoader(resource);
		Scene sc = new Scene(loader.load());
		primaryStage.setScene(sc);

		primaryStage.setMinHeight(500.0);
		primaryStage.setMinWidth(800.0);
		primaryStage.setTitle("Inventory Manager");
		primaryStage.show();
	}

	private void connectToDatabse() {

		//configure the database to connect to using commad line args
		Map<String, String> params = getParameters().getNamed();
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://" + params.get("address") + "/INVENTORY_MANAGEMENT",
							params.get("user"),
							params.get("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}


	}

	@Override
	public void stop() throws Exception {
		super.stop();
		connection.close();
	}
}
