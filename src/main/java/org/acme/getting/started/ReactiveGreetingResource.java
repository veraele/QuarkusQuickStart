package org.acme.getting.started;

import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.acme.getting.model.Hello;
import org.jboss.resteasy.reactive.RestSseElementType;

@Path("/hello")
public class ReactiveGreetingResource {

    @Inject
    ReactiveGreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public Uni<String> greeting(String name) {
        return service.greeting(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/greeting")
    public Uni<Hello> greetingPost(Hello greeting) {
        return service.greetingJson(greeting.getText());
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{count}/{name}")
    public Multi<String> greetings(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @NonBlocking
    public String hello() {
        return "hello";
    }
}
