package indi.learn.tomcat.container.simple;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimitiveServlet implements Servlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		System.out.println("from service");
		
		// set response body and the head of datagram
		String statusLine = "HTTP/1.1 200 OK\r\n";
		out.write(statusLine);

		// content type
		String contentType = "Content-Type:text/html;charset=utf8\n\n";
		out.write(contentType);
		out.println("Hello, servlet");
		out.println("over");
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {
		System.out.println("destroy");
	}

}
