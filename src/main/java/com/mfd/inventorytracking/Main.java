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
		URL resource = getClass().getResource("/layouts/home_layout.fxml");
		FXMLLoader loader = new FXMLLoader(resource);
		Scene sc = new Scene(loader.load());
		primaryStage.setScene(sc);

		primaryStage.setMinHeight(500.0);
		primaryStage.setMinWidth(800.0);

		primaryStage.show();
	}

	private void connectToDatabse() throws SQLException {

		//configure the database to connect to using commad line args
		Map<String, String> params = getParameters().getNamed();
		connection = DriverManager
				.getConnection("jdbc:mysql://" + params.get("address") + "/Library", params.get("user"),
						params.get("password"));

	}

	@Override
	public void stop() throws Exception {
		super.stop();
		connection.close();
	}
}
