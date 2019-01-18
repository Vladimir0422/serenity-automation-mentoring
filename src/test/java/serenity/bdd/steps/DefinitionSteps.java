package serenity.bdd.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import props.EnvironmentPropertyLoader;
import serenity.bdd.statuses.PetStatus;
import serenity.bdd.steps.serenity.EndUserSteps;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DefinitionSteps {
    protected static Properties envProperties = EnvironmentPropertyLoader.loadByEnvironment();

    @Steps
    EndUserSteps endUser;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user looks up the definition of the word")
    public void whenTheUserLooksUpTheDefinitionOf() {
        endUser.looks_for(envProperties.getProperty("word.for.test"));
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

    @Then("they should see the definition")
    public void thenTheyShouldSeeADefinitionContainingTheWords() {
        endUser.should_see_definition(envProperties.getProperty("definition.for.test"));
    }

    @Then("they should click on the link")
    public void thenTheyShouldClickOnTheLink() {
        endUser.clickOnLink(envProperties.getProperty("link.for.test"));
    }

    @Then("in the table they should see the definition")
    public void thenTheyShouldSeeADefinitionInTable(String definition) {
        endUser.should_see_definition(definition);
    }

    @Given("system is clean")
    public void systemIsClean() {
        endUser.cleanup();
    }

    @When("the user checks pet by status $status")
    public void theUserChecksPetByStatus(PetStatus status) {
        endUser.getPetByStatus(status);
    }

    @Then("system returns status code $code")
    public void systemReturnsStatusCode(PetStatus status, int code) {
        endUser.verifyStatusCode(status, code);
    }
}
