package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testSuite.objects.pages.SingleStone;

import java.time.Duration;


public class SingleStoneSteps {
    // The SUT is single-page, so the whole thing should always be there (just not visible when not in use)
    SingleStone myStone = new SingleStone();

    @And("the single stone is hidden")
    public void theSingleStoneIsHidden() {
        Assert.assertFalse("The single stone should be hidden", myStone.singleStoneIsVisible());
    }

    @Then("the single stone becomes visible")
    public void theSingleStoneBecomesVisible() {
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(myStone.singleBlock));
    }

    @And("I ask to view the stone {string}")
    public void iAskToViewTheStone(String nameOfStone) { myStone.selectFromIndexStone(nameOfStone); }

    @And("the single stone is the {string} stone")
    public void theSingleStoneIsTheStone(String arg0) {
    }


}
