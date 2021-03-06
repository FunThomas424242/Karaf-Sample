package com.github.funthomas424242.ube.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.funthomas424242.lib.hateoas.servlet.HateoasBuilder;
import com.github.funthomas424242.lib.hateoas.servlet.HttpMethod;
import com.github.funthomas424242.lib.hateoas.servlet.NavigationServlet;
import com.github.funthomas424242.lib.hateoas.servlet.PlaceholderMap;

@WebServlet(urlPatterns = "/items/auflisten")
public class ShowItemsServlet extends NavigationServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final HateoasBuilder hateoasBuilder = new HateoasBuilder(request);
		hateoasBuilder.addLinkSELF(HttpMethod.GET, "Neu laden");
		hateoasBuilder.addLinkBACK(HttpMethod.GET, "Zur&uuml;ck");
		hateoasBuilder.addLink("next", "/kategorien/auflisten", HttpMethod.GET,
				"Kategorien auflisten");
		hateoasBuilder.addLink("home", "/index.html", HttpMethod.GET,
				"Startseite");
		hateoasBuilder.addLink("new", "/items/anlegen", HttpMethod.GET,
				"Neues Item anlegen");
		final PlaceholderMap placeholderMap = hateoasBuilder
				.createPlaceholderMap();
		final StringBuffer pageContent = hateoasBuilder.buildPageContent(
				getServletContext(), placeholderMap, "/items/itemlist.html");

		response.setCharacterEncoding("utf8");
		response.setContentType("text/html");
		hateoasBuilder.addLocationHeaderTo(response);
		hateoasBuilder.addLinkHeaderTo(response);

		final PrintWriter writer = response.getWriter();
		writer.print(pageContent.toString());
		writer.flush();
		writer.close();
	}

}
