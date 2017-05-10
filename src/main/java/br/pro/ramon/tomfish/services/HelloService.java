package br.pro.ramon.tomfish.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Olá, mundo!";
    }

    @GET
    @Path("/{nome}")
    @Produces(MediaType.TEXT_HTML)
    public String hello(@PathParam("nome") String nome) {
        return String.format("Olá, <strong>%s</strong>!", nome);
    }

}
