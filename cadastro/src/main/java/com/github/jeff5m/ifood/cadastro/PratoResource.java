package com.github.jeff5m.ifood.cadastro;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @GET
    public List<Prato> buscar() {
        return Prato.listAll();
    }

    @POST
    @Transactional
    public Response salvar(Prato pratoDTO) {
        pratoDTO.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void substituir(@PathParam("id") Long id, Prato pratoDTOPut) {
        Prato pratoSalvo = (Prato) Prato.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        pratoSalvo.nome = pratoDTOPut.nome;
        pratoSalvo.descricao = pratoDTOPut.descricao;
        pratoSalvo.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Prato pratoEncontrado = (Prato) Prato.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Prato.deleteById(pratoEncontrado.id);
    }
}