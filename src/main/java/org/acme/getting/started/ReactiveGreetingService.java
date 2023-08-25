package org.acme.getting.started;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.acme.getting.model.Hello;

@ApplicationScoped
public class ReactiveGreetingService {

    public Uni<String> greeting(String name) {
        return Uni.createFrom().item(name)
                .onItem().transform(n -> String.format("hello %s", name));
    }

    public Uni<Hello> greetingJson(String name) {
        return Uni.createFrom().item(name)
                .onItem().transform(n -> {
                    Hello hello = new Hello();
                    hello.setText( String.format("hello %s", name));
                    return hello;
                });
    }

    public Multi<String> greetings(int count, String name) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("hello %s - %d", name, n))
                .select().first(count);

    }

}
