package com.mfd.inventorytracking;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

public class Context {
	private static Connection connection;

	public static Connection getConnection() {
		return connection;
	}

	static void setConnection(Connection connection) {
		Context.connection = connection;
	}
	public static void displayErrorWindow(String message){
		Label messagelabel = new Label(message);
		Button dismiss=new Button("Ok");
		Scene s=new Scene(new VBox(messagelabel,dismiss));

		Stage errorWindow = new Stage();
		errorWindow.setTitle("Error");
		errorWindow.initModality(Modality.APPLICATION_MODAL);

		dismiss.setOnAction(event ->errorWindow.close());
		errorWindow.setScene(s);

		errorWindow.show();


	}
}
