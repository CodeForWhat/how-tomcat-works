package indi.learn.tomcat.container.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpServer1 {
	/** WEB_ROOT is the directory where our HTML and other files reside. 
	 *  For this package, WEB_ROOT is the "webroot" directory under the 
	 *  working directory. 
	 *  The working directory is the location in the file system 
	 *  from where the java command was invoked. 
	 **/ 
	// shutdown command 
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			try {
				while(!shutdown) {
					Socket socket = serverSocket.accept();
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream();
					
					Request request = new Request(is);
					Response response = new Response(os);
					
					request.parse();
					if(request.getUri().startsWith("/servlet/")) {
						ServletProcessor1 processor = new ServletProcessor1();
						processor.process(request, response);
					} else {
						StaticResourceProcessor processor = new StaticResourceProcessor();
						processor.process(request, response);
					}
					
					socket.close();
					shutdown = request.getUri().equals(SHUTDOWN_COMMAND) ? true : false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		HttpServer1 server = new HttpServer1();
		server.await();
	}

}
