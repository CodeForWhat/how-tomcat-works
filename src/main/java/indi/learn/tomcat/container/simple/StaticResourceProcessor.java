package indi.learn.tomcat.container.simple;

public class StaticResourceProcessor {
	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
