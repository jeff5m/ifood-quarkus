package com.github.jeff5m.ifood.cadastro;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(CadastroTestLifeCycleManager.class)
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class RestauranteResourceTest {

    @Test
    @DisplayName("list returns list of restaurants when successful")
    @DataSet("restaurants-scenario-1.yml")
    void list_ReturnsListOfRestaurants_WhenSuccessful() {
        String result = given()
                .when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
        Approvals.verifyJson(result); // change json generated file from "*.received.json" to "*.approved.json"
    }
}
