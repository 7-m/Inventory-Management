package com.mfd.inventorytracking.supplybill;

import javafx.beans.property.*;

class SupplyBillingItem {
	StringProperty  part         =new SimpleStringProperty();
	IntegerProperty quantity     =new SimpleIntegerProperty();
	FloatProperty   pricePerUnit =new SimpleFloatProperty();

	  StringProperty partProperty() {
		 return part;
	 }

	  IntegerProperty quantityProperty() {
		 return quantity;
	 }

	  FloatProperty pricePerUnitProperty() {
		 return pricePerUnit;
	 }

 }
