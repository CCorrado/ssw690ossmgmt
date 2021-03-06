package com.ossp.database.model;

import javax.persistence.*;

@Entity(name = "Inventories")
public class Inventory {

	@Id
	@GeneratedValue
	private long InventoryID;

	@ManyToOne
	@JoinColumn(name = "BusinessID")
	private Business business;

	@Column(name = "Product")
	private String product;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "Price")
	private double price;

	public long getInventoryID() {
		return InventoryID;
	}

	public void setInventoryID(Long inventoryID) {
		this.InventoryID = inventoryID;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}
}
