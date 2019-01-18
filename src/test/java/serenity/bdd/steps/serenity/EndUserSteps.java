package serenity.bdd.steps.serenity;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import serenity.bdd.endpoints.PetStorePetEndpoint;
import serenity.bdd.models.Pet;
import serenity.bdd.pages.DictionaryPage;
import serenity.bdd.statuses.PetStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    PetStorePetEndpoint petStoreEndpoint = PetStorePetEndpoint.getInstance();
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
        return petStoreEndpoint.getPetById(id);
    }

    @Step
    public Response getPetByStatus(PetStatus status) {
        return petStoreEndpoint.getPetByStatus(status);
    }

    @Step
    public Response createPet(Pet pet) {
        return petStoreEndpoint.createPet(pet);
    }

    @Step
    public Response updatePet(Pet pet) {
        return petStoreEndpoint.updatePet(pet);
    }

    @Step
    public Response deletePetById(long id) {
        return petStoreEndpoint.deletePetById(id);
    }

    @Step
    public void verifyStatusCode(PetStatus status, int code) {
        petStoreEndpoint.verifyStatusCode(status, code);
    }

    @Step
    public void cleanup(){
        petStoreEndpoint.cleanup();
    }

    ///Pet store order

    @Step
    public Response createPetOrder(long petId, long orderId, int quantityForOrder) {
        return petStoreEndpoint.createPetOrder(petId, orderId, quantityForOrder);
    }

    @Step
    public Response deletePetOrder(long orderId) {
        return deletePetOrder(orderId);
    }

    @Step
    public Response getPetOrderByOrderId(long orderId) {
        return petStoreEndpoint.getPetOrderByOrderId(orderId);
    }
}