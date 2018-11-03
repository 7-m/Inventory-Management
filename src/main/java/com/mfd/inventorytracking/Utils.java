package com.mfd.inventorytracking;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;

public class Utils {
	private Utils() {
	}

	public static class Validate {
		private Validate() {
		}

		public static boolean isFieldEmpty(TextInputControl... fields) {
			for (TextInputControl f : fields)
				if (f.getText().isEmpty()) return true;

			return false;
		}

		public static boolean isFieldEmpty(ComboBoxBase... cbxs) {
			for (ComboBoxBase cbx : cbxs)
				if (cbx.getValue() == null) return true;

			return false;
		}

		public static boolean isFieldEmpty(ChoiceBox... cbxs) {
			for (ChoiceBox cbx : cbxs)
				if (cbx.getValue() == null) return true;

			return false;

		}

	}
}
