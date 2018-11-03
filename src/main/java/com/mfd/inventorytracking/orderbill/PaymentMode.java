package com.mfd.inventorytracking.orderbill;

public enum PaymentMode {
	CHEQ("cheque"), CASH("cash"), DEDR("demand draft"), NODOD("not done");

	private String desc;
	private PaymentMode(String desc){
		this.desc=desc;
	}

	String getDesc() {
		return desc;
	}
}
