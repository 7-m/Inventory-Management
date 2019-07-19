package com.mfd.inventorytracking.buyer;

class Buyer {
	private String  name;
	private String  gstin;
	private String  address;
	private String  contact;
	private int credit;

	int getCredit() {
		return credit;
	}

	void setCredit(int credit) {
		this.credit = credit;
	}

	public Buyer() {

	}

	public Buyer(String name, String gstin, String address, String contact, int credit) {
		this.name = name;
		this.gstin = gstin;
		this.address = address;
		this.contact = contact;
		this.credit=credit;
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
