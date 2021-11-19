package com.pizzazer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pizzazer.logic.PizzaLogic;
import com.pizzazer.pojo.Pizza;

/**
 * Servlet implementation class pizza
 */

public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PizzaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Am Here //Pizza");

		List<Pizza> pizzaList = PizzaLogic.getPizzaList();

		Gson gson = new Gson();

		Object jasonObject = gson.toJson(pizzaList);

		response.setContentType("application/json");

		response.getWriter().print(jasonObject);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
