package com.api.ParkU;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.api.ParkU.config.SpringMongoConfig;
import com.api.ParkU.model.User;

@Path("/user")
public class UserAPI {

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public User saveUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		
		User user = new User(username, password);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		// save
		mongoOperation.save(user);
		// now user object got the created id.
		((ConfigurableApplicationContext)ctx).close();
		return user;
	}
}
