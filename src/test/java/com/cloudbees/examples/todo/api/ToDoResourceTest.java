package com.cloudbees.examples.todo.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cloudbees.examples.todo.api.model.ToDo;

public class ToDoResourceTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.getConfiguration().enable(
		// new JsonJaxbFeature());

		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	/**
	 * Test creating a todo
	 */
	@Test
	public void testCreateOne() {
		ToDo todo = new ToDo();
		todo.setDescription("Test your demo man.");

		Entity<ToDo> entity = Entity.entity(todo, MediaType.APPLICATION_JSON);

		Response response = target.path("todos").request().buildPost(entity)
				.invoke();

		Assert.assertNotNull(response.getEntity());
		Assert.assertTrue((response.readEntity(ToDo.class)).getId() > -1);

	}

	/**
	 * Test getting all todos
	 */
	@Test
	public void testGet() {
		Response response = target.path("todos")
				.request(MediaType.APPLICATION_JSON).get();

		Assert.assertNotNull(response.getEntity());

		// ToDo firstOne = (ToDo) (response.readEntity(List.class)).get(0);
		// Assert.assertNotNull(firstOne);

	}

}
