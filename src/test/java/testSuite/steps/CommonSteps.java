package testSuite.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testSuite.objects.pages.CommonPage;

/**
 * Steps that can be performed on all of the pages for this site
 */
public class CommonSteps {
    @Given("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // the constructor for this sort of object will wait until it thinks that the page is complete
        new CommonPage();
    }

    @When("I go back to the index stone")
    public void iGoBackToTheIndexStone() {
        CommonPage myPage = new CommonPage();
        myPage.backToIndexButton.click();
    }
}
