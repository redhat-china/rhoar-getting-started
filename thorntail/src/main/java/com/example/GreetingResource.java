/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
public class GreetingResource {

    private static final String template = "%s";

    @GET
    @Path("/cooking")
    @Produces("application/json")
    public Greeting greeting(@QueryParam("name") String name) {
        if (!ApplicationConfig.IS_ALIVE.get()) {
            throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
        }
        String suffix = name != null ? name : "Egg";
        return new Greeting(String.format(template, suffix));
    }

    @GET
    @Path("/stop")
    public Response stop() {

        if (!ApplicationConfig.IS_ALIVE.get()) {
            throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
        }

        ApplicationConfig.IS_ALIVE.set(false);
        return Response.ok("killed").build();

    }

}
