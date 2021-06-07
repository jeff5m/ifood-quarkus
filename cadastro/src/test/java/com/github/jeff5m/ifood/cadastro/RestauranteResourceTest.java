package com.github.jeff5m.ifood.cadastro;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.approvaltests.Approvals;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.PlateCreator;
import util.RestaurantCreator;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(CadastroTestLifeCycleManager.class)
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@DataSet(cleanAfter = true)
class RestauranteResourceTest {

    @Test
    @DisplayName("buscar returns list of restaurants when successful")
    @DataSet("restaurants-scenario-1.yml")
    void buscar_ReturnsListOfRestaurants_WhenSuccessful() {
        String result = given()
                .when().get("/restaurantes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(result); // change json generated file from "*.received.json" to "*.approved.json"
    }

    @Test
    @DisplayName("salvar returns status code 201 when successful")
    void salvar_ReturnsStatusCode201_WhenSuccessful() {
        given().when()
                .contentType(ContentType.JSON)
                .body(RestaurantCreator.createValidRestaurantToBeSaved())
                .post("/restaurantes")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    @DisplayName("substituir updates restaurant and returns status code 204 when successful")
    @DataSet("restaurants-scenario-1.yml")
    void substituir_UpdatesRestaurantAndReturnsStatusCode204_WhenSuccessful() {
        Restaurante restaurantDTO = new Restaurante("Restaurante atualizado", "ID do keyclok");
        restaurantDTO.id = 123L;
        restaurantDTO.nome = "Restaurante Atualizado";
        ValidatableResponse response = given()
                .with()
                .contentType(ContentType.JSON)
                .pathParam("id", restaurantDTO.id)
                .body(restaurantDTO)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
        Restaurante foundedRestaurant = Restaurante.findById(restaurantDTO.id);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.extract().statusCode())
                .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        Assertions.assertThat(foundedRestaurant.nome)
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(restaurantDTO.id)
                .isEqualTo(foundedRestaurant.id);
        Assertions.assertThat(restaurantDTO.nome)
                .isEqualTo(foundedRestaurant.nome);
    }

    @Test
    @DisplayName("deletar deletes a restaurant and returns status code 204 when successful")
    @DataSet("restaurants-scenario-1.yml")
    void deletar_DeletesARestaurantAndReturnsStatusCode204_WhenSuccessful() {
        Long idToBeDeleted = 123L;
        given()
                .with()
                .pathParam("id", idToBeDeleted)
                .when()
                .delete("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    @DisplayName("buscar returns list of plates in a restaurants when successful")
    @DataSet(value = "plates-scenario-1.yml", cleanAfter = true)
    void buscar_ReturnsListOfPlatesInARestaurant_WhenSuccessful() {
        given()
                .with()
                .pathParam("idRestaurante", 123)
                .when()
                .get("/restaurantes/{idRestaurante}/pratos")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @DisplayName("buscar returns status 404 if no restaurant is found")
    void buscar_ReturnsStatus404_IfNoRestaurantIsFound() {
        given()
                .with()
                .pathParam("idRestaurante", 123)
                .when()
                .get("/restaurantes/{idRestaurante}/pratos")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @DisplayName("salvarPrato returns status code 201 when successful")
    @DataSet("restaurants-scenario-1.yml")
    void salvarPrato_ReturnsStatusCode201_WhenSuccessful() {
        ValidatableResponse response = given()
                .with()
                .pathParam("idRestaurante", 123)
                .when()
                .contentType(ContentType.JSON)
                .body(PlateCreator.createValidPlateToBeSaved())
                .post("/restaurantes/{idRestaurante}/pratos")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.extract().statusCode())
                .isEqualTo(Response.Status.CREATED.getStatusCode());
    }


    @Test
    @DisplayName("salvarPrato returns status code 201 when successful")
    void salvarPrato_ReturnsStatus404_IfNoRestaurantIsFound() {
        ValidatableResponse response = given()
                .with()
                .pathParam("idRestaurante", 1)
                .when()
                .contentType(ContentType.JSON)
                .body(PlateCreator.createValidPlateToBeSaved())
                .post("/restaurantes/{idRestaurante}/pratos")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.extract().statusCode())
                .isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @DisplayName("substituir updates plate and returns status code 204 when successful")
    @DataSet("plates-scenario-1.yml")
    void substituir_UpdatesPlateAndReturnsStatusCode204_WhenSuccessful() {
        Prato plateDTO = new Prato("Restaurante atualizado", RestaurantCreator.createValidRestaurant());
        plateDTO.id = 1L;
        plateDTO.nome = "Prato Atualizado";
        ValidatableResponse response = given()
                .with()
                .contentType(ContentType.JSON)
                .pathParam("id", plateDTO.id)
                .body(plateDTO)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
        Restaurante foundedRestaurant = Restaurante.findById(plateDTO.id);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.extract().statusCode())
                .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        Assertions.assertThat(foundedRestaurant.nome)
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(plateDTO.id)
                .isEqualTo(foundedRestaurant.id);
        Assertions.assertThat(plateDTO.nome)
                .isEqualTo(foundedRestaurant.nome);
    }
}
