package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testSuite.objects.pages.IndexStone;

import static org.junit.Assert.assertTrue;

public class IndexStoneSteps {
    // The SUT is single-page, so the whole thing should always be there (just not visible when not in use)
    final IndexStone indexStone = new IndexStone();// myPage = new CommonPage();

    @And("the index stone is hidden")
    public void theIndexStoneIsHidden() { Assert.assertFalse("The index stone should be hidden", indexStone.indexStoneIsVisible()); }

    @Then("the index stone is visible")
    public void theIndexStoneIsVisible() { assertTrue("The index stone should be visible", indexStone.indexStoneIsVisible()); }

    @Then("the index stone becomes visible")
    public void theIndexStoneBecomesVisible() { indexStone.waitForIndexStoneToBeVisible(); }

    @And("I ask to view the stone {string}")
    public void iAskToViewTheStone(String nameOfStone) { indexStone.selectStone(nameOfStone); }

}
