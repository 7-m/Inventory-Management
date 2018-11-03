package com.mfd.inventorytracking.orderbill;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

 class OrderBillingItem {
	 StringProperty part=new SimpleStringProperty();
	 IntegerProperty quantity=new SimpleIntegerProperty();
	 IntegerProperty pricePerUnit=new SimpleIntegerProperty();

	  StringProperty partProperty() {
		 return part;
	 }

	  IntegerProperty quantityProperty() {
		 return quantity;
	 }

	  IntegerProperty pricePerUnitProperty() {
		 return pricePerUnit;
	 }

 }
