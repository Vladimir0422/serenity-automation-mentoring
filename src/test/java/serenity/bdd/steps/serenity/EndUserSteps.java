package serenity.bdd.steps.serenity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import serenity.bdd.models.Pet;
import serenity.bdd.models.PetStoreOrder;
import serenity.bdd.pages.DictionaryPage;
import serenity.bdd.statuses.OrderStatus;
import serenity.bdd.statuses.PetStatus;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static serenity.bdd.config.Config.*;

public class EndUserSteps {

    private PetStoreOrder petStoreOrder;
    private static EndUserSteps instance;

    public EndUserSteps() {

    }

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step
    public void clickOnLink(String linkName) {
        dictionaryPage.clickOnLink(linkName);
    }

    @Step
    public Response getPetById(String id) {
        return given()
                .pathParam("petId", id)
                .when()
                .get(PET_BY_ID)
                .then()
                .extract().response();
    }

    @Step
    public Response getPetByStatus(PetStatus status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(FIND_BY_STATUS)
                .then()
                .extract().response();
    }

    @Step
    public Response createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .extract().response();
    }

    @Step
    public Response updatePet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .put(CREATE_PET)
                .then()
                .extract().response();
    }

    @Step
    public Response deletePetById(long id) {
        return given()
                .pathParam("petId", id)
                .when()
                .delete(PET_BY_ID)
                .then()
                .extract().response();
    }

    @Step
    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(PETS_STORE_BASE_URL)
                .contentType(ContentType.JSON);
    }

    @Step
    public void verifyStatusCode() {
        instance.getInstance()
                .getPetByStatus(PetStatus.AVAILABLE)
                .then()
                .statusCode(200);
    }

    ///Pet store order

    @Step
    public Response createPetOrder(long petId, long orderId, int quantityForOrder) {
        return given()
                .body(createDefaultOrder(petId, orderId, quantityForOrder))
                .contentType(ContentType.JSON)
                .when()
                .post(CREATE_PET_STORE_ORDER);
    }

    @Step
    public Response deletePetOrder(long orderId) {
        return given()
                .baseUri(PETS_STORE_BASE_URL)
                .pathParam("orderId", orderId)
                .when()
                .delete(FIND_PET_STORE_ORDER_BY_ORDER_ID);
    }

    @Step
    public Response getPetOrderByOrderId(long orderId) {
        return given()
                .pathParam("orderId", orderId)
                .get(FIND_PET_STORE_ORDER_BY_ORDER_ID);
    }

    @Step
    public static void cleanup() {
        instance.getInstance().getPetByStatus(PetStatus.AVAILABLE)
                .body()
                .jsonPath()
                .getList("findAll {item -> item.name == 'BarsikSV5'}", Pet.class)
                .stream()
                .forEach(pet -> instance.getInstance().deletePetById(pet.getId()));
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

    public static EndUserSteps getInstance() {
        if (instance == null) {
            instance = new EndUserSteps();
        }
        return instance;
    }
}