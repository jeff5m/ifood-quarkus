package com.github.jeff5m.ifood.cadastro;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    public List<Restaurante> buscar() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public Response salvar(Restaurante restauranteDTO) {
        restauranteDTO.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void substituir(@PathParam("id") Long id, Restaurante restauranteDTOPut) {
        Restaurante restauranteSalvo = (Restaurante) Restaurante.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        restauranteSalvo.nome = restauranteDTOPut.nome;
        restauranteSalvo.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Restaurante.deleteById(restauranteEncontrado.id);
    }
}