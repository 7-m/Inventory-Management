package com.mfd.inventorytracking.orderbill;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class OrderBill {
	private ObservableList<OrderBillingItem> billedItems = FXCollections.observableArrayList();

	private String      buyerName;
	private int         billno;
	private Date        date;
	private String      remarks;
	private PaymentMode paymentMode;

	public ObservableList<OrderBillingItem> getBilledItems() {
		return billedItems;
	}

	public void setBilledItems(
			ObservableList<OrderBillingItem> billedItems) {
		this.billedItems = billedItems;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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
