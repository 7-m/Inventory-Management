package com.mfd.inventorytracking.part;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Part {
	private String name="";
	private float  taxSlab;
	private int    quantity;
	private String desc="";
	private byte[] image=new byte[0];

	public Part() {

	}

	public Part(String name, int quantity, Float taxSlab, String desc, byte[] image) {
		this.name = name;
		this.taxSlab = taxSlab;
		this.quantity = quantity;
		this.desc = desc;
		this.image = image;
	}

	public static byte[] getBytes(File image) throws IOException {
		byte[] buf = new byte[Math.toIntExact(image.length())];
		FileInputStream fis = new FileInputStream(image);
		fis.read(buf);
		return buf;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getTaxSlab() {
		return taxSlab;
	}

	public void setTaxSlab(Float taxSlab) {
		this.taxSlab = taxSlab;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
