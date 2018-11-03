package com.mfd.inventorytracking;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public abstract class AbstractMenuController {
	private ContentDisplayPane contentDisplayPane;

	public void setContentDisplayPlane(ContentDisplayPane contentDisplayPlane) {
		this.contentDisplayPane = contentDisplayPlane;
	}
	final protected void setView(String location) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
			Node n = loader.load();
			contentDisplayPane.displayContent(n);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
