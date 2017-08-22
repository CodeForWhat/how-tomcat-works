package indi.learn.tomcat.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Request {
	private String uri;
	private String method;
	private String protocol;
	private InputStream inputStream;
	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, String> parameters = new HashMap<String, String>();

	public static Request parseRequest(InputStream inputStream) throws IOException {
		Request request = new Request();
		String line = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String[] parts = null;
		if (((line = br.readLine()) != null) && (parts = line.split(" ")).length == 3) {
			request.method = parts[0].toUpperCase();
			request.uri = parts[1];
			request.protocol = parts[2];
		}

		// header
		while ((line = br.readLine()) != null && !line.equals("")) {
			if ((parts = line.split(":")).length == 2) {
				request.headers.put(parts[0], parts[1]);
			}
		}

		// request body
		if (request.method.toUpperCase().equals("POST") && line.equals("")) {
			line = br.readLine();

			if (line.length() > 0) {
				String[] parameters = line.split("&");
				for (String parameter : parameters) {
					String[] pair = parameter.split("=");
					request.parameters.put(pair[0], pair[1]);
				}
			}
		}

		return request;
	}

	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}

	public String getProtocol() {
		return protocol;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "Request [uri=" + uri + ", method=" + method + ", protocol=" + protocol + ", inputStream=" + inputStream
				+ ", headers=" + headers + ", parameters=" + parameters + "]";
	}
}
