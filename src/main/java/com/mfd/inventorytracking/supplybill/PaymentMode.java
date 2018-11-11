package com.mfd.inventorytracking.supplybill;

public enum PaymentMode {
	CHEQ("cheque"), CASH("cash"), DEDR("demand draft"), UNPD("unpaid"), RTGS("RTGS");

	private String desc;

	PaymentMode(String desc) {
		this.desc = desc;
	}

	String getDesc() {
		return desc;
	}
}
