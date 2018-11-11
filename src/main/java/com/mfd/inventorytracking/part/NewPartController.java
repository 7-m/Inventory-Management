package com.mfd.inventorytracking.part;

import com.mfd.inventorytracking.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewPartController {

	@FXML private ImageView imageView;
	@FXML private TextField nameField;
	@FXML private TextField taxField;
	@FXML private TextArea  descField;

	private       File      partImage;
	@FXML private TextField quantityField;

	@FXML
	private void onClickAddPart(ActionEvent actionEvent) {
		try {
			if (Utils.Validate.isFieldEmpty(nameField, taxField))
				return;
			int qty = quantityField.getText().isEmpty() ? 0 : Integer.valueOf(quantityField.getText());
			Part p = new Part(nameField.getText(), qty, Float.valueOf(taxField.getText()), descField.getText(),
					partImage == null ? null :
							Part.getBytes(partImage));


			Queries.insertPart(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void onClickSelectImageFile(ActionEvent actionEvent) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select Image File");
		partImage = chooser.showOpenDialog(null);


		if (imageView != null) {
			try (FileInputStream fis = new FileInputStream(partImage)) {
				imageView.setImage(new Image(fis));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
