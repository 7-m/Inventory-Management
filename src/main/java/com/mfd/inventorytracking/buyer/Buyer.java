package com.mfd.inventorytracking.buyer;

class Buyer {
	private String  name;
	private String  gstin;
	private String  address;
	private String  contact;
	private Integer outAmt;

	public Buyer() {

	}

	public Buyer(String name, String gstin, String address, String contact) {
		this.name = name;
		this.gstin = gstin;
		this.address = address;
		this.contact = contact;
	}

	Integer getOutAmt() {
		return outAmt;
	}

	void setOutAmt(Integer outAmt) {
		this.outAmt = outAmt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
