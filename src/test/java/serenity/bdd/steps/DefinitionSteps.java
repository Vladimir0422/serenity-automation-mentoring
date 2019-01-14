package serenity.bdd.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import serenity.bdd.statuses.PetStatus;
import serenity.bdd.steps.serenity.EndUserSteps;

import java.util.HashMap;
import java.util.Map;

public class DefinitionSteps {

    @Steps
    EndUserSteps endUser;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user looks up the definition of the word $word")
    public void whenTheUserLooksUpTheDefinitionOf(String word) {
        endUser.looks_for(word);
    }

    @When("the user looks up the definition of the words in table $wordTable")
    public void whenTheUserLooksUpTheDefinitionInTable(ExamplesTable wordTable) {
        Map<String, String> resultTable = new HashMap<>();
        for (Map<String, String> rows : wordTable.getRows()) {
            String word = rows.get("word");
            String definition = rows.get("definition");
            resultTable.put(word, definition);
        }
    }

    @Then("they should see the definition $definition")
    public void thenTheyShouldSeeADefinitionContainingTheWords(String definition) {
        endUser.should_see_definition(definition);
    }

    @Then("they should click on the link $link")
    public void thenTheyShouldClickOnTheLink(String link) {
        endUser.clickOnLink(link);
    }

    @Then("in the table they should see the definition")
    public void thenTheyShouldSeeADefinitionInTable(String definition) {
        endUser.should_see_definition(definition);
    }

    @Given("system is clean")
    public void systemIsClean(){
        endUser.cleanup();
    }

    @When("the user checks pet by status")
    public void theUserChecksPetByStatus(){
        endUser.getPetByStatus(PetStatus.AVAILABLE);
    }

    @Then("system returns status code")
    public void systemReturnsStatusCode(){
        endUser.verifyStatusCode();
    }
}
