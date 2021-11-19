package com.pizzazer.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.pizzazer.dbLayer.DbOperation;
import com.pizzazer.pojo.Order;
import com.pizzazer.pojo.Pizza;

public class PizzaLogic {

	public static List<Pizza> getPizzaList() {

		Map<Integer, Pizza> pizzaListObject = null;

		List<Pizza> pizzaList = new ArrayList<Pizza>();

		try {

			pizzaListObject = DbOperation.getPizzaInfo();

			for (Pizza pizza : pizzaListObject.values()) {

				pizzaList.add(pizza);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pizzaList;
	}

	public static List<Order> getOrderList() {

		Map<Integer, Order> OrderListMap = null;

		List<Order> OrderList = new ArrayList<Order>();

		try {

			OrderListMap = DbOperation.getOrdersList();

			for (Order order : OrderListMap.values()) {

				OrderList.add(order);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OrderList;
	}

	public static Order getOrderDetails(int orderId) {

		Order orderDetail = null;
		
		try {
			
			orderDetail = DbOperation.getOrderDetails(orderId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderDetail;
	}
}
