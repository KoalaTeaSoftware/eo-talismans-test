package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testSuite.objects.pages.PremiumMembership;

import java.time.Duration;

public class PurchaseSteps {
    PremiumMembership myFacility = new PremiumMembership();

    @Then("the call to purchase the {string} becomes visible")
    public void theCallToPurchaseTheBecomesVisible(String stoneName) {
        Assert.assertTrue("The purchase premium membership popup should be visible", myFacility.purchaseFacilityIsVisible());
    }

    @And("I purchase premium membership")
    public void iPurchasePremiumMembership() {
        myFacility.triggerPurchase();
    }

    @Then("the call to purchase the becomes hidden")
    public void theCallToPurchaseTheBecomesHidden() {
        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait)
        ).until(ExpectedConditions.invisibilityOf(myFacility.premiumPurchaseBlock));
    }
}
