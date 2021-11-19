package com.pizzazer.pojo;

import java.util.List;
import java.util.Map;

public class Order {

	private int orderId;
	private String customerName;
	private List<Pizza> pizzaList;
	private Map<Integer, Pizza> pizzaMap;

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

	private int quantity;
	private float Total;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Map<Integer, Pizza> getPizzaMap() {
		return pizzaMap;
	}

	public void setPizzaMap(Map<Integer, Pizza> pizzaMap) {
		this.pizzaMap = pizzaMap;
	}

}
