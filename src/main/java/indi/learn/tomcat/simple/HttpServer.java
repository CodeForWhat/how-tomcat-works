package indi.learn.tomcat.simple;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpServer {
	public static final String WEB_ROOT = "E:\\documentations\\jdk1.8.0_92_docs\\api";

	private static final String SHUTDOWN = "shutdown";

	private boolean shutdown = false;

	public void await() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		while (!shutdown) {
			try {
				socket = serverSocket.accept();

				Request request = Request.parseRequest(socket.getInputStream());

				Response response = new Response(socket.getOutputStream(), request);

				response.sendStaticResource();
				
				shutdown = request.getUri().toLowerCase().endsWith(SHUTDOWN);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			} finally {
				if(socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		HttpServer hs = new HttpServer();
		hs.await();
	}
}