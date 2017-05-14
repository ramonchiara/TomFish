package br.pro.ramon.tomfish.services;

import br.pro.ramon.tomfish.daos.Dao;
import br.pro.ramon.tomfish.daos.DaoException;
import br.pro.ramon.tomfish.daos.DaoFactory;
import br.pro.ramon.tomfish.daos.JaCadastradoDaoException;
import br.pro.ramon.tomfish.daos.NaoEncontradoDaoException;
import br.pro.ramon.tomfish.models.Pessoa;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pessoas")
public class PessoaCrudService {

    @POST
    public Response create(@FormParam("nome") String nome, @FormParam("peso") double peso, @FormParam("altura") double altura) {
        try {
            Dao dao = DaoFactory.getDao();
            Pessoa pessoa = new Pessoa(nome, peso, altura);
            dao.create(pessoa);
            return Response.created(URI.create("pessoas/" + nome)).build();
        } catch (JaCadastradoDaoException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Pessoa pessoa) {
        try {
            Dao dao = DaoFactory.getDao();
            dao.create(pessoa);
            return Response.created(URI.create("pessoas/" + pessoa.getNome())).build();
        } catch (JaCadastradoDaoException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        try {
            Dao dao = DaoFactory.getDao();
            List<Pessoa> pessoas = dao.findAll();
            return Response.ok().entity(pessoas).build();
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{nome}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findByNome(@PathParam("nome") String nome) {
        try {
            Dao dao = DaoFactory.getDao();
            Pessoa pessoa = dao.findByNome(nome);
            if (pessoa == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.ok().entity(pessoa).build();
            }
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{nome}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("nome") String nome, Pessoa pessoa) {
        try {
            Dao dao = DaoFactory.getDao();
            pessoa.setNome(nome);
            dao.update(pessoa);
            return Response.noContent().build();
        } catch (NaoEncontradoDaoException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{nome}")
    public Response delete(@PathParam("nome") String nome) {
        try {
            Dao dao = DaoFactory.getDao();
            Pessoa pessoa = new Pessoa(nome);
            dao.delete(pessoa);
            return Response.noContent().build();
        } catch (NaoEncontradoDaoException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (DaoException ex) {
            return Response.serverError().build();
        }
    }

}
