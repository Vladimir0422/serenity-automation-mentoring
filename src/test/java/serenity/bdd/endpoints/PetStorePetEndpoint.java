package serenity.bdd.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import serenity.bdd.models.Pet;
import serenity.bdd.models.PetStoreOrder;
import serenity.bdd.statuses.OrderStatus;
import serenity.bdd.statuses.PetStatus;

import java.time.LocalDateTime;

import static serenity.bdd.config.Config.*;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 11/25/2018
 */


public class PetStorePetEndpoint {

    private PetStorePetEndpoint() {

    }

    private PetStoreOrder petStoreOrder;
    private static PetStorePetEndpoint instance;

    public Response getPetById(String id) {
        return given()
                .pathParam("petId", id)
                .when()
                .get(PET_BY_ID)
                .then()
                .extract().response();
    }

    public Response getPetByStatus(PetStatus status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(FIND_BY_STATUS)
                .then()
                .extract().response();
    }

    public Response createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .extract().response();
    }

    public Response updatePet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .put(CREATE_PET)
                .then()
                .extract().response();
    }

    public Response deletePetById(long id) {
        return given()
                .pathParam("petId", id)
                .when()
                .delete(PET_BY_ID)
                .then()
                .extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(PETS_STORE_BASE_URL)
                .contentType(ContentType.JSON);
    }

    @Step
    public void verifyStatusCode(PetStatus status, int code) {
        instance.getInstance()
                .getPetByStatus(status)
                .then()
                .statusCode(code);
    }

    public static void cleanup() {
        instance.getInstance().getPetByStatus(PetStatus.AVAILABLE)
                .body()
                .jsonPath()
                .getList("findAll {item -> item.name == 'BarsikSV5'}", Pet.class)
                .stream()
                .forEach(pet -> instance.getInstance().deletePetById(pet.getId()));
    }

    ///Pet store order

    public Response createPetOrder(long petId, long orderId, int quantityForOrder) {
        return given()
                .body(createDefaultOrder(petId, orderId, quantityForOrder))
                .contentType(ContentType.JSON)
                .when()
                .post(CREATE_PET_STORE_ORDER);
    }

    public Response deletePetOrder(long orderId) {
        return given()
                .baseUri(PETS_STORE_BASE_URL)
                .pathParam("orderId", orderId)
                .when()
                .delete(FIND_PET_STORE_ORDER_BY_ORDER_ID);
    }

    public Response getPetOrderByOrderId(long orderId) {
        return given()
                .pathParam("orderId", orderId)
                .get(FIND_PET_STORE_ORDER_BY_ORDER_ID);
    }

    private PetStoreOrder createDefaultOrder(long petId, long orderId, int quantityForOrder) {
        petStoreOrder = new PetStoreOrder();

        petStoreOrder.setId(orderId);
        petStoreOrder.setPetId(petId);
        petStoreOrder.setQuantity(quantityForOrder);
        petStoreOrder.setShipDate(LocalDateTime.now().plusDays(1));
        petStoreOrder.setStatus(OrderStatus.DELIVERED);
        petStoreOrder.setComplete(true);

        return petStoreOrder;
    }

    public PetStoreOrder getPetStoreObject() {
        return petStoreOrder;
    }

    public static PetStorePetEndpoint getInstance() {
        if (instance == null) {
            instance = new PetStorePetEndpoint();
        }
        return instance;
    }
}
