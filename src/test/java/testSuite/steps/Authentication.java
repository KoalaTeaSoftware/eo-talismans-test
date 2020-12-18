package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import testSuite.objects.pages.CommonPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Authentication {
    // The SUT is single-page, so the whole thing should always be there (just not visible when not in use)
    CommonPage myPage = new CommonPage();

    public void refresh() {
        myPage = new CommonPage();
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {

        myPage.showLoginForm();
        theLoginFormIsVisible();

        myPage.setUsername(username);
        myPage.setPassword(password);
        myPage.clickLoginButton();
    }

    @Then("the index stone is visible")
    public void theIndexStoneIsVisible() {
        assertTrue("The index stone should be visible", myPage.indexStoneIsVisible());
    }

    @Then("The index stone becomes visible")
    public void theIndexStoneBecomesVisible() {
        myPage.waitForIndexStoneToBeVisible();
    }

    @And("the please log in CTA is hidden")
    public void thePleaseLogInCTAIsHidden() {
        assertFalse("The please login prompt should be hidden", myPage.pliCTAisVisible());
    }

    @Then("the please log in CTA is visible")
    public void thePleaseLogInCTAIsVisible() {
        Assert.assertTrue("The \"Please Log In\" prompt should be visible", myPage.pliCTAisVisible());
    }

    @And("the index stone is hidden")
    public void theIndexStoneIsHidden() {
        Assert.assertFalse("The index stone should be hidden", myPage.indexStoneIsVisible());
    }

    @And("the single stone is hidden")
    public void theSingleStoneIsHidden() {
        Assert.assertFalse("The single stone should be hidden", myPage.singleStoneIsVisible());
    }


    @And("the login failure message eventually mentions {string}")
    public void theLoginFailureMessageEventuallyMentions(String expected) {
        myPage.waitForLoginMessageToContain(expected);
    }

    @And("the login failure message mentions {string}")
    public void theLoginFailureMessageMentions(String expected) {
        String actualMessage = myPage.getLoginFormMessage();

        Assert.assertTrue(
                String.format(
                        "The form's message >%s< should contain the expected phrase >%s<",
                        actualMessage,
                        expected),
                actualMessage.contains(expected));
    }

    @Then("the login form is visible")
    public void theLoginFormIsVisible() {
        Assert.assertTrue("The login form should be visible", myPage.loginFormIsVisible());
    }

    @And("the login form is hidden")
    public void theLoginFormIsHidden() {
        assertFalse("The login form should be hidden", myPage.loginFormIsVisible());
    }

    @And("the login button is visible")
    public void theLoginButtonIsVisible() { assertTrue("The log in button should be visible", myPage.logInButtonIsVisible()); }

    @And("the login button is hidden")
    public void theLoginButtonIsHidden() { assertFalse("The log in button should be hidden", myPage.logInButtonIsVisible()); }

    @And("the logout button is visible")
    public void theLogoutButtonIsVisible() { assertTrue("the log out button should be hidden", myPage.logOutButtonIsVisible()); }

    @And("the logout button is hidden")
    public void theLogoutButtonIsHidden() { assertFalse("The log out button should be hidden", myPage.logOutButtonIsVisible()); }
}
