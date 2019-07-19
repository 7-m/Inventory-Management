package com.mfd.inventorytracking.supplier;

class Supplier {
	private String name;
	private String gstin;
	private String address;
	private String contact;
	private int debt;

	public Supplier() {

	}

	public Supplier(String name, String gstin, String address, String contact, int debt) {
		this.name = name;
		this.gstin = gstin;
		this.address = address;
		this.contact = contact;
		this.debt = debt;
	}

	int getDebt() {
		return debt;
	}

	void setDebt(int debt) {
		this.debt = debt;
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
