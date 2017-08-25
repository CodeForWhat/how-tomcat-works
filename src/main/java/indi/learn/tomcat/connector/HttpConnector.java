package indi.learn.tomcat.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpConnector implements Runnable {
	private boolean stoped;
	private String schema = "http";
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		ServerSocket server = null;
		try {
			int port = 8080;
			server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(!stoped) {
			// Accept the next incoming connection from the server socket
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				continue;
			}
			HttpProcessor processor = new HttpProcessor(this);
			processor.process(socket);
		}
	}
	
	public String getSchema() {
		return schema;
	}

}
