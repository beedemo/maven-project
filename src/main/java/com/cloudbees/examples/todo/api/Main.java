package com.cloudbees.examples.todo.api;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Main class.
 * 
 */
public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8181/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in com.cloudbees.examples.todo.api package
		final ResourceConfig rc = new ResourceConfig()
				.packages("com.cloudbees.examples.todo.api");

		WebappContext webappContext = new WebappContext("context");
		webappContext
				.addContextInitParameter("contextClass",
						"org.springframework.web.context.support.XmlWebApplicationContext");
		webappContext.addContextInitParameter("contextConfigLocation",
				"classpath*:application-context.xml");
		webappContext
				.addListener("org.springframework.web.context.ContextLoaderListener");
		// Create a servlet registration for the web application in order to
		// wire up Spring managed collaborators to Jersey resources.
		ServletRegistration servletRegistration = webappContext.addServlet(
				"jersey-servlet", ServletContainer.class);
		servletRegistration.addMapping("/*");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),
				rc);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format(
				"Jersey app started with WADL available at "
						+ "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		server.stop();
	}
}
