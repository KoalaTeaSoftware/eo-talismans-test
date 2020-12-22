package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import testSuite.objects.pages.PremiumMembership;

public class PurchaseSteps {
    final PremiumMembership myFacility = new PremiumMembership();

    @Then("the call to purchase the {string} becomes visible")
    public void theCallToPurchaseTheBecomesVisible(String stoneName) { myFacility.purchaseFacilityBecomesVisible(); }

    @Then("the call to purchase becomes hidden")
    public void theCallToPurchaseBecomesHidden() { myFacility.purchaseFacilityBecomesHidden(); }

    @And("I purchase premium membership")
    public void iPurchasePremiumMembership() {
        myFacility.triggerPurchase();
    }

    @And("I click the already a premium user button")
    public void iClickTheAlreadyAPremiumUserButton() { myFacility.signifyAlreadyAMember(); }
}
