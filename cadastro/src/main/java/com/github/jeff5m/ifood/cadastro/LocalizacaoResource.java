package com.github.jeff5m.ifood.cadastro;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/localizacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocalizacaoResource {

    @GET
    public List<Localizacao> buscar() {
        return Localizacao.listAll();
    }

    @POST
    @Transactional
    public Response salvar(Localizacao localizacaoDTO) {
        localizacaoDTO.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void substituir(@PathParam("id") Long id, Localizacao localizacaoDTOPut) {
        Localizacao localizacaoSalva = (Localizacao) Localizacao.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        localizacaoSalva.latitude = localizacaoDTOPut.latitude;
        localizacaoSalva.longitude = localizacaoDTOPut.longitude;
        localizacaoSalva.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        Localizacao localizacaoEncontrada = (Localizacao) Localizacao.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Localizacao.deleteById(localizacaoEncontrada.id);
    }
}