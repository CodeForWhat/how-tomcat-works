package indi.learn.tomcat.container.simple;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor1 {
	
	public void process(Request request, Response response) {
		String servletName = request.getUri().substring(request.getUri().lastIndexOf('/') + 1);
		servletName = "indi.learn.tomcat.container.simple." + servletName;
		
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			// the forming of repository is taken from the 
			// createClassLoader method in 
			// org.apache.catalina.startup.ClassLoaderFactory 
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			// the code for forming the URL is taken from 
			// the addRepository method in 
			// org.apache.catalina.loader.StandardClassLoader. 
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Class<?> myClass = null;
		try {
			myClass = loader.loadClass(servletName);
		} catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
		Servlet servlet = null;
		try {
			servlet = (Servlet)myClass.newInstance();
			servlet.service((ServletRequest)request, (ServletResponse)response);
		} catch(Exception e) {
			System.err.println(e);
		} catch(Throwable t) {
			System.err.println(t);
		}
	}

}
