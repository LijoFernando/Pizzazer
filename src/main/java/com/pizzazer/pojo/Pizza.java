package com.pizzazer.pojo;

import java.util.List;

public class Pizza {
	private int pizzaId;
	private String pizzaName;
	private float pizzaPrice;
	private List<String> ingredients;

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public float getPizzaPrice() {
		return pizzaPrice;
	}

	public void setPizzaPrice(float pizzaPrice) {
		this.pizzaPrice = pizzaPrice;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

}
