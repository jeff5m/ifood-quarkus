package com.github.jeff5m.ifood.cadastro;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Restaurant")
public class RestaurantResource {

    @GET
    @Tag(name = "Restaurant")
    public List<Restaurant> find() {
        return Restaurant.listAll();
    }

    @POST
    @Transactional
    @Tag(name = "Restaurant")
    public Response save(Restaurant restaurantDTO) {
        restaurantDTO.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name = "Restaurant")
    public void replace(@PathParam("id") Long id, Restaurant restaurantDTOPut) {
        Restaurant restaurantSalvo = (Restaurant) Restaurant.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        restaurantSalvo.name = restaurantDTOPut.name;
        restaurantSalvo.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name = "Restaurant")
    public void delete(@PathParam("id") Long id) {
        Restaurant foundedRestaurant = (Restaurant) Restaurant.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Restaurant.deleteById(foundedRestaurant.id);
    }

    @GET
    @Path("{idRestaurant}/plates")
    @Tag(name = "Plate")
    public List<Restaurant> findPlates(@PathParam("idRestaurant") Long idRestaurant) {
        Restaurant foundedRestaurant = (Restaurant) Restaurant.findByIdOptional(idRestaurant)
                .orElseThrow(NotFoundException::new);
        return Plate.list("restaurant", foundedRestaurant);
    }

    @POST
    @Path("{idRestaurant}/plates")
    @Transactional
    @Tag(name = "Plate")
    public Response savePlate(@PathParam("idRestaurant") Long idRestaurant, Plate plateDTO) {
        Restaurant foundedRestaurant = (Restaurant) Restaurant.findByIdOptional(idRestaurant)
                .orElseThrow(NotFoundException::new);
        Plate plateASerSalvo = new Plate(
                plateDTO.name,
                plateDTO.description,
                foundedRestaurant,
                plateDTO.price
        );
        plateASerSalvo.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurant}/plates/{id}")
    @Transactional
    @Tag(name = "Plate")
    public void replacePlate(@PathParam("idRestaurant") Long idRestaurant,
                                    @PathParam("id") Long id,
                                    Plate plateDTO) {
        Plate foundedPlate = (Plate) Plate.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        foundedPlate.price = plateDTO.price;
        foundedPlate.persist();
    }

    @DELETE
    @Path("{idRestaurant}/plates/{id}")
    @Transactional
    @Tag(name = "Plate")
    public void deletePlate(@PathParam("idRestaurant") Long idRestaurant,
                             @PathParam("id") Long id) {
        Restaurant.findByIdOptional(idRestaurant)
                .orElseThrow(NotFoundException::new);
        Plate foundedPlate = (Plate) Plate.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
        Plate.deleteById(foundedPlate.id);
    }
}
