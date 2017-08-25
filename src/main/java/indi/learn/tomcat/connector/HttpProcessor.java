package indi.learn.tomcat.connector;

import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletInputStream;

public class HttpProcessor {
	private HttpConnector connector;
	
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}
	
	public void process(Socket socket) {
		ServletInputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			// create a HttpRequest Object
//			inputStream = socket.getInputStream();
		} catch(Exception e) {
			
		}
	}

}
