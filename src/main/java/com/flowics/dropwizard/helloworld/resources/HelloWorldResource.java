package com.flowics.dropwizard.helloworld.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.flowics.dropwizard.helloworld.core.Saying;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/hello-world")
@Api("/hello-world")
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
    @ApiOperation("Sample endpoint")
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
    @ApiOperation("Sample endpoint delayed")
    public Saying waitToSayHello(@QueryParam("name") Optional<String> name) throws InterruptedException {
        final String value = String.format(template, name.or(defaultName));
        Thread.sleep(10000L);
        return new Saying(counter.incrementAndGet(), value);
    }
}