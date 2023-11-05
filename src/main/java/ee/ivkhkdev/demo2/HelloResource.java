package ee.ivkhkdev.demo2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class HelloResource {
    @Inject
    UserRepository userRepository;

    @GET
    @Path("/world")
    @Produces("text/plain")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GET
    @Path("/user/{id}")
    public String helloUser(@PathParam("id") Long id){
        User user = null;
        try {
            user = userRepository.findById(id);
        }catch (Exception e){

        }
        if(user == null) return "{}";
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("userId", user.getUserId());
        job.add("firstname", user.getFirstname());
        job.add("lastname", user.getLastname());
        return job.build().toString();
    }

}