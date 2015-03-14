package com.flowics.dropwizard.helloworld.resources;

import com.flowics.dropwizard.helloworld.core.Saying;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    /* Example of long running task to see how threads monitoring (http://localhost:8081/threads) 
     * is shown by dropwizard
     */
    @GET
    @Path("/delayed")
    @Timed
    public Saying waitToSayHello(@QueryParam("name") Optional<String> name) throws InterruptedException {
        final String value = String.format(template, name.or(defaultName));
        Thread.sleep(10000L);
        return new Saying(counter.incrementAndGet(), value);
    }
}