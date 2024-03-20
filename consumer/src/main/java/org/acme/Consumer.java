package org.acme;

import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class Consumer {

    @Channel("quarkus-response-rabbitmq")
    Emitter<JsonObject> emitter;

    @Incoming("quarkus-rabbitmq")
    public void process(JsonObject msg){
        Log.info(msg.toString());
        msg.put("response", "Nice 2 meet u too");
        System.out.println("!!!message was printed!!!");
        emitter.send(msg);
    }

}
