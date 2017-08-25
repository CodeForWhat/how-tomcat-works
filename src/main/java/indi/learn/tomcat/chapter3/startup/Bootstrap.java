package indi.learn.tomcat.chapter3.startup;

import indi.learn.tomcat.chapter3.http.HttpConnector;

public final class Bootstrap {
	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
}	
