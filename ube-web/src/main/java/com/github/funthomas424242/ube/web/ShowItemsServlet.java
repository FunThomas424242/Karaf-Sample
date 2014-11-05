package com.github.funthomas424242.ube.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.ops4j.pax.cdi.api.OsgiService;

import de.inovex.javamagazin.domain.InventoryItem;
import de.inovex.javamagazin.jpa.InventoryRepository;

@WebServlet(urlPatterns = "/items/auflisten")
public class ShowItemsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@OsgiService
	InventoryRepository inventoryRepository;

	
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		List<InventoryItem> allItems = inventoryRepository.getAllItems();

		response.setCharacterEncoding("utf8");
		response.setContentType("text/html");
		response.setHeader("location",
				"http://localhost:8181/ube/items/auflisten");
		response.setHeader(
				"link",
				"<http://localhost:8181/ube/index.html>;rel=\"next\";title=\"Homepage\"");

		final PrintWriter writer = response.getWriter();

		final ServletContext context = getServletContext();

		final InputStream inStream = context
				.getResourceAsStream("/items/itemlist.html");
		int c = inStream.read();
		while (c != -1) {
			writer.print((char) c);
			c = inStream.read();
		}
		writer.flush();
		writer.close();
	}

}
