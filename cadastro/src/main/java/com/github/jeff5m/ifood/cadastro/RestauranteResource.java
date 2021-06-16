package com.github.jeff5m.ifood.cadastro;

import com.github.jeff5m.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.jeff5m.ifood.cadastro.dto.RestauranteMapper;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurante")
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    @Tag(name = "restaurante")
    public List<Restaurante> buscar() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    @Tag(name = "restaurante")
    public Response salvar(AdicionarRestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteMapper.toRestaurante(restauranteDTO);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name = "restaurante")
    public void substituir(@PathParam("id") Long id, Restaurante restauranteDTOPut) {
        Restaurante restauranteSalvo = (Restaurante) Restaurante.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        restauranteSalvo.nome = restauranteDTOPut.nome;
        restauranteSalvo.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name = "restaurante")
    public void deletar(@PathParam("id") Long id) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Restaurante.deleteById(restauranteEncontrado.id);
    }

    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(idRestaurante)
                .orElseThrow(NotFoundException::new);
        return Prato.list("restaurante", restauranteEncontrado);
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name = "prato")
    public Response salvarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato pratoDTO) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(idRestaurante)
                .orElseThrow(NotFoundException::new);
        Prato pratoASerSalvo = new Prato();
        pratoASerSalvo.nome = pratoDTO.nome;
        pratoASerSalvo.descricao = pratoDTO.descricao;
        pratoASerSalvo.preco = pratoDTO.preco;
        pratoASerSalvo.restaurante = restauranteEncontrado;
        pratoASerSalvo.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void substituirPrato(@PathParam("idRestaurante") Long idRestaurante,
                                    @PathParam("id") Long id,
                                    Prato pratoDTO) {
        Prato pratoEncontrado = (Prato) Prato.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        pratoEncontrado.preco = pratoDTO.preco;
        pratoEncontrado.persist();
    }

    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void deletarPrato(@PathParam("idRestaurante") Long idRestaurante,
                             @PathParam("id") Long id) {
        Restaurante.findByIdOptional(idRestaurante)
                .orElseThrow(NotFoundException::new);
        Prato pratoEncontrado = (Prato) Prato.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Prato.deleteById(pratoEncontrado.id);
    }
}
