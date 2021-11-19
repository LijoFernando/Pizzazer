package com.pizzazer.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pizzazer.logic.PizzaLogic;
import com.pizzazer.pojo.Order;

/**
 * Servlet implementation class orders
 */

public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getPathInfo());
		// TODO Auto-generated method stub
		System.out.println("Am Here //Order");

		String pathInfo = request.getPathInfo();

		Gson gson = new Gson();

		Object jasonObject = null;

		if (pathInfo != null) {

			String pathString[] = pathInfo.split("/", 5);

			int orderid = Integer.parseInt(pathString[1]);

			Order orderDeatils = PizzaLogic.getOrderDetails(orderid);

			jasonObject = gson.toJson(orderDeatils);

		} else {

			List<Order> orderList = PizzaLogic.getOrderList();

			jasonObject = gson.toJson(orderList);

		}
		if (jasonObject != null) {

			response.setContentType("application/json");

			response.getWriter().print(jasonObject);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
