package com.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BootstrapServlet extends HttpServlet {

	private static final long serialVersionUID = -6620637326128648521L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		System.out.println("==============");
		System.out.println(config);
		System.out.println(config.getServletName());
		System.out.println(config.getInitParameterNames());
	}

}
