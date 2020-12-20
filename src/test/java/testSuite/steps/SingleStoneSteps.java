package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testSuite.objects.pages.SingleStone;

public class SingleStoneSteps {
    // The SUT is single-page, so the whole thing should always be there (just not visible when not in use)
    final SingleStone myStone = new SingleStone();

    @And("the single stone is hidden")
    public void theSingleStoneIsHidden() {
        Assert.assertFalse("The single stone should be hidden", myStone.singleStoneIsVisible());
    }

    @Then("the single stone becomes visible")
    public void theSingleStoneBecomesVisible() { myStone.singleStoneBecomesVisible(); }

    @And("the single stone is the {string} stone")
    public void theSingleStoneIsTheStone(String arg0) {
        Assert.assertEquals("Unexpected stone", arg0, myStone.getStoneName());
    }


}
