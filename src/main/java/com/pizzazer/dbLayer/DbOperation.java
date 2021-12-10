package com.pizzazer.dbLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.pizzazer.pojo.Order;
import com.pizzazer.pojo.Pizza;

public class DbOperation {

	private static Connection con;

	private static void loadConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "Root@123");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		if (con == null) {
			loadConnection();
		}
		return con;
	}

	private static final String getPizzaInfoData = "SELECT pizzahub.pizzainfo.pizzaId, pizzahub.pizzainfo.pizzaName, pizzahub.pizzainfo.pizzaprice, pizzahub.incrediantlist.IncredientName FROM pizzahub.pizzainfo left join pizzahub.pizzaincredientcombo on  pizzaincredientcombo.pizzaid = pizzainfo.pizzaId left join pizzahub.incrediantlist on incrediantlist.incredientId = pizzaincredientcombo.incredientId ";
	private static final String getOrdersList = "SELECT pizzahub.orders.*, pizzahub.pzinfo.pizzaid, pizzahub.pzinfo.pizzaName, pizzahub.pzinfo.pizzaprice , pizzahub.oitems.quantity, pizzahub.incredientList.incredientName FROM pizzahub.orders join pizzahub.orderitems as oitems on oitems.orderid = orders.orderid join pizzahub.pizzainfo as pzinfo on pzinfo.pizzaid = oitems.pizzaid join pizzahub.pizzaincredientcombo as incredientCombo on  incredientCombo.pizzaid = pzinfo.pizzaId join pizzahub.incrediantlist as incredientList on incredientList.incredientId = incredientCombo.incredientId";
	private static final String getOrderDetailforOrderId = "select * from  (SELECT pizzahub.orders.*, pizzahub.pzinfo.pizzaid, pizzahub.pzinfo.pizzaName, pizzahub.pzinfo.pizzaprice , pizzahub.oitems.quantity, pizzahub.incredientList.incredientName FROM pizzahub.orders join pizzahub.orderitems as oitems on oitems.orderid = orders.orderid join pizzahub.pizzainfo as pzinfo on pzinfo.pizzaid = oitems.pizzaid join pizzahub.pizzaincredientcombo as incredientCombo on  incredientCombo.pizzaid = pzinfo.pizzaId join pizzahub.incrediantlist as incredientList on incredientList.incredientId = incredientCombo.incredientId ) as dum where dum.orderid = ?";

	public static Map<Integer, Pizza> getPizzaInfo() throws SQLException {

		Map<Integer, Pizza> pizzaMap = new HashMap<Integer, Pizza>();
		ResultSet rs = null;

		PreparedStatement ps = getConnection().prepareStatement(getPizzaInfoData);
		rs = ps.executeQuery();
		while (rs.next()) {

			int pizzaId = rs.getInt(1);
			String pizzaName = rs.getString(2);
			float pizzaPrice = rs.getFloat(3);
			String incredientName = rs.getString(4);

			if (pizzaMap.containsKey(pizzaId)) {

				pizzaMap.get(pizzaId).getIngredients().add(incredientName);

			} else {

				Pizza pizzaInfo = new Pizza();
				pizzaInfo.setPizzaId(pizzaId);
				pizzaInfo.setPizzaName(pizzaName);
				pizzaInfo.setPizzaPrice(pizzaprice);
				pizzaInfo.setIngredients(new ArrayList<String>());
				pizzaInfo.getIngredients().add(incredientName);
				pizzaMap.put(pizzaId, pizzaInfo);

			}
		}
		return pizzaMap;
	}

	public static Map<Integer, Order> getOrdersList() throws SQLException {

		Map<Integer, Pizza> pizzaMap = new HashMap<Integer, Pizza>();
		Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
		ResultSet rs = null;

		PreparedStatement ps = getConnection().prepareStatement(getOrdersList);
		rs = ps.executeQuery();
		while (rs.next()) {
			int orderId = rs.getInt(1);
			String customerName = rs.getString(2);
			int pizzaId = rs.getInt(3);
			String pizzaName = rs.getString(4);
			float pizzaprice = rs.getFloat(5);
			int pizzaQuantity = rs.getInt(6);
			String incredientName = rs.getString(7);

			if (orderMap.containsKey(orderId)) {
				if (orderMap.get(orderId).getPizzaMap().containsKey(pizzaId)) {
					orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients().add(incredientName);
					System.out.println(orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients());
				} else {
					Pizza pizzaInfo = new Pizza();
					pizzaInfo.setPizzaId(pizzaId);
					pizzaInfo.setPizzaName(pizzaName);
					pizzaInfo.setPizzaPrice(pizzaprice);
					pizzaInfo.setIngredients(new ArrayList<String>());
					pizzaInfo.getIngredients().add(incredientName);
					pizzaMap.put(pizzaId, pizzaInfo);
					System.out.println("new" + orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients());
				}

			} else {

				Pizza pizzaInfo = new Pizza();
				pizzaInfo.setPizzaId(pizzaId);
				pizzaInfo.setPizzaName(pizzaName);
				pizzaInfo.setPizzaPrice(pizzaprice);
				pizzaInfo.setIngredients(new ArrayList<String>());
				pizzaInfo.getIngredients().add(incredientName);
				pizzaMap.put(pizzaId, pizzaInfo);

				Order orderInfo = new Order();
				orderInfo.setOrderId(orderId);
				orderInfo.setCustomerName(customerName);
				orderInfo.setPizzaList(new ArrayList<Pizza>());
				orderInfo.setPizzaMap(pizzaMap);
				orderInfo.getPizzaList().add(pizzaInfo);
				orderInfo.setQuantity(pizzaQuantity);
				orderMap.put(orderId, orderInfo);

			}
		}
		return orderMap;
	}

	public static Order getOrderDetails(int orderID) throws SQLException {

		Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
		Map<Integer, Pizza> pizzaMap = new HashMap<Integer, Pizza>();
		Order orderDetails = null;

		ResultSet rs = null;

		PreparedStatement ps = getConnection().prepareStatement(getOrderDetailforOrderId);
		ps.setInt(1, orderID);
		rs = ps.executeQuery();
		while (rs.next()) {
			int orderId = rs.getInt(1);
			String customerName = rs.getString(2);
			int pizzaId = rs.getInt(3);
			String pizzaName = rs.getString(4);
			float pizzaprice = rs.getFloat(5);
			int pizzaQuantity = rs.getInt(6);
			String incredientName = rs.getString(7);

			if (orderMap.containsKey(orderId)) {
				if (orderMap.get(orderId).getPizzaMap().containsKey(pizzaId)) {
					orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients().add(incredientName);
					System.out.println(orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients());
				} else {
					Pizza pizzaInfo = new Pizza();
					pizzaInfo.setPizzaId(pizzaId);
					pizzaInfo.setPizzaName(pizzaName);
					pizzaInfo.setPizzaPrice(pizzaprice);
					pizzaInfo.setIngredients(new ArrayList<String>());
					pizzaInfo.getIngredients().add(incredientName);
					pizzaMap.put(pizzaId, pizzaInfo);
					System.out.println("new" + orderMap.get(orderId).getPizzaMap().get(pizzaId).getIngredients());
				}

			} else {

				Pizza pizzaInfo = new Pizza();
				pizzaInfo.setPizzaId(pizzaId);
				pizzaInfo.setPizzaName(pizzaName);
				pizzaInfo.setPizzaPrice(pizzaprice);
				pizzaInfo.setIngredients(new ArrayList<String>());
				pizzaInfo.getIngredients().add(incredientName);
				pizzaMap.put(pizzaId, pizzaInfo);

				Order orderInfo = new Order();
				orderInfo.setOrderId(orderId);
				orderInfo.setCustomerName(customerName);
				orderInfo.setPizzaList(new ArrayList<Pizza>());
				orderInfo.setPizzaMap(pizzaMap);
				orderInfo.getPizzaList().add(pizzaInfo);
				orderInfo.setQuantity(pizzaQuantity);
				orderMap.put(orderId, orderInfo);

			}

		}
		orderDetails = orderMap.get(orderID);
		
		return orderDetails;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * try { System.out.println(getOrdersList().entrySet()); } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * for (Order orderInfo : orderMap.values()) {
	 * System.out.println(orderInfo.getPizzaMap().values()); for (Pizza pizzaInfo :
	 * orderInfo.getPizzaMap().values()) {
	 * System.out.println(pizzaInfo.getIngredients().toArray()); } }
	 * 
	 * }
	 */
}
