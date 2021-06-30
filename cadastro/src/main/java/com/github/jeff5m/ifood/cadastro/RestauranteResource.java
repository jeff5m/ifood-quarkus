package com.github.jeff5m.ifood.cadastro;

import com.github.jeff5m.ifood.cadastro.dto.prato.AdicionarPratoDTO;
import com.github.jeff5m.ifood.cadastro.dto.prato.AtualizarPratoDTO;
import com.github.jeff5m.ifood.cadastro.dto.prato.PratoMapper;
import com.github.jeff5m.ifood.cadastro.dto.prato.PratoResponseDTO;
import com.github.jeff5m.ifood.cadastro.dto.restaurante.AdicionarRestauranteDTO;
import com.github.jeff5m.ifood.cadastro.dto.restaurante.AtualizarRestauranteDTO;
import com.github.jeff5m.ifood.cadastro.dto.restaurante.RestauranteMapper;
import com.github.jeff5m.ifood.cadastro.dto.restaurante.RestauranteResponseDTO;
import com.github.jeff5m.ifood.cadastro.exceptions.ConstraintViolationImpl;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Inject
    PratoMapper pratoMapper;

    @GET
    @Tag(name = "restaurante")
    public List<RestauranteResponseDTO> buscar() {
        return restauranteMapper.toListOfRestaurantResponseDTO(Restaurante.listAll());
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Caso restaturante seja adicionado com sucesso")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationImpl.class)))
    @Tag(name = "restaurante")
    public Response salvar(@Valid AdicionarRestauranteDTO restauranteDTO) {
        Restaurante restaurante = restauranteMapper.toRestaurante(restauranteDTO);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name = "restaurante")
    public void substituir(@PathParam("id") Long id, AtualizarRestauranteDTO atualizarRestauranteDTO) {
        Restaurante restauranteSalvo = (Restaurante) Restaurante.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Restaurante restaurante = restauranteMapper.toRestaurante(atualizarRestauranteDTO, restauranteSalvo);
        restaurante.persist();
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
    public List<PratoResponseDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(idRestaurante)
                .orElseThrow(NotFoundException::new);
        List<Prato> pratos = Prato.list("restaurante", restauranteEncontrado);

        return pratoMapper.toListOfPratoResponseDTO(pratos);
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name = "prato")
    public Response salvarPrato(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDTO pratoDTO) {
        Restaurante restauranteEncontrado = (Restaurante) Restaurante.findByIdOptional(idRestaurante)
                .orElseThrow(NotFoundException::new);
        Prato prato = pratoMapper.toPrato(pratoDTO, idRestaurante);
        prato.restaurante = restauranteEncontrado;
        prato.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void substituirPrato(@PathParam("idRestaurante") Long idRestaurante,
                                @PathParam("id") Long id,
                                AtualizarPratoDTO pratoDTO) {
        Prato pratoEncontrado = (Prato) Prato.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Prato prato = pratoMapper.toPrato(pratoDTO, pratoEncontrado);
        prato.persist();
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
