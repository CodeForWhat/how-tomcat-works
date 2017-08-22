package indi.learn.tomcat.simple;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private OutputStream outputStream;
	private Request request;

	public Response(OutputStream outputStream, Request request) {
		this.outputStream = outputStream;
		this.request = request;
	}

	public void sendStaticResource() {
		File file = new File(HttpServer.WEB_ROOT, request.getUri());
		if (file.isFile()) {
			byte[] buffer = new byte[1024];
			BufferedInputStream bis = null;
			try {
				int cnt = 0;
				bis = new BufferedInputStream(new FileInputStream(file));
				while ((cnt = bis.read(buffer)) != -1) {
					outputStream.write(buffer, 0, cnt);
				}

			} catch (Exception e) {

			} finally {
				try {
					if (outputStream != null) {
						outputStream.close();
					}
				} catch (IOException e) {

				}
			}
		}

	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public Request getRequest() {
		return request;
	}
}
