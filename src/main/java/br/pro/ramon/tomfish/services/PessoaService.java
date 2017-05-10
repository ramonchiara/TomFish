package br.pro.ramon.tomfish.services;

import br.pro.ramon.tomfish.models.Pessoa;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/pessoa")
public class PessoaService {

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Pessoa getPessoaXml(
            @QueryParam("nome") String nome,
            @QueryParam("peso") double peso,
            @QueryParam("altura") double altura) {
        return new Pessoa(nome, peso, altura);
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Pessoa getPessoaJson(
            @QueryParam("nome") String nome,
            @QueryParam("peso") double peso,
            @QueryParam("altura") double altura) {
        return new Pessoa(nome, peso, altura);
    }

}
