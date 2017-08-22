package indi.learn.tomcat.container.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {
	private static final int BUFFERSIZE = 1024;
	private OutputStream stream;
	private PrintWriter writer;
	private Request request;
	
	public Response(OutputStream outputStream) {
		this.stream = outputStream;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void sendStaticResource() {
		byte[] buffer = new byte[BUFFERSIZE];
		FileInputStream fis = null;
		try {
			// set response body and the head of datagram
			String statusLine = "HTTP/1.1 200 OK\r\n";
			stream.write(statusLine.getBytes());
			
			// content type
			String contentType = "Content-Type:text/html;charset=utf8\n\n";
			stream.write(contentType.getBytes());
			
			// transfer static file data
			File file = new File(Constants.WEB_ROOT, request.getUri());
			fis = new FileInputStream(file);
			int count = -1;
			while((count = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, count);
			}
		} catch(FileNotFoundException e) {
			
		} catch(IOException e) {
			
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		// println will auto flush but print would not 
		writer = new PrintWriter(stream, true);
		return writer;
	}

	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub

	}

	public void setContentLength(int len) {
		// TODO Auto-generated method stub

	}

	public void setContentType(String type) {
		// TODO Auto-generated method stub

	}

	public void setBufferSize(int size) {
		// TODO Auto-generated method stub

	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub

	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

}
