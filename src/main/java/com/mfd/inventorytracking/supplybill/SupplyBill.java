package com.mfd.inventorytracking.supplybill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class SupplyBill {
	private ObservableList<SupplyBillingItem> billedItems = FXCollections.observableArrayList();

	private String      supplierName;
	private int         billno;
	private Date        date;
	private String      remarks;
	private PaymentMode paymentMode;


	public ObservableList<SupplyBillingItem> getBilledItems() {
		return billedItems;
	}

	public void setBilledItems(
			ObservableList<SupplyBillingItem> billedItems) {
		this.billedItems = billedItems;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getBillno() {
		return billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
}
