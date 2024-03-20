package org.acme;

import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Path("/main")
public class ExampleResource {

    @Channel("quarkus-rabbitmq")
    Emitter<JsonObject> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        JsonObject msg = new JsonObject();
        msg.put("greeting", "Hello rabbit");
        emitter.send(msg);
        return "^_^";
    }

    @Incoming("quarkus-response-rabbitmq")
    public void getResp(JsonObject msg){
        Log.info(msg.toString());
        System.out.println("!!!message was printed!!!");
    }

}
