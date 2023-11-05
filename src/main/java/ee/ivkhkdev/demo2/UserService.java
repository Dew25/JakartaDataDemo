package ee.ivkhkdev.demo2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

@Path("/user")
@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    Validator validator;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (ConstraintViolation<User> v : violations) {
                jsonArrayBuilder.add(v.getMessage());
            }
            return jsonArrayBuilder.build().toString();
        }
        userRepository.save(user);
        JsonObject json = Json.createObjectBuilder()
                .add("userId", user.getUserId())
                .add("lastname", user.getLastname())
                .add("firstname", user.getFirstname()).build();

        return json.toString();
    }
    @DELETE
    @Path("/{id}")
    public String remove(@PathParam("id") Long id){
        userRepository.deleteByUserId(id);
        return "";
    }
    @GET
    public String retrieve() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (User u : userRepository.findAll()) {
            JsonObject json = Json.createObjectBuilder()
                    .add("userId", u.getUserId())
                    .add("lastname", u.getLastname())
                    .add("firstname", u.getFirstname()).build();
            jab.add(json);
        }
        return jab.build().toString();
    }
}