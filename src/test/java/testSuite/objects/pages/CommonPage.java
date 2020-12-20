package testSuite.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testFramework.objects.HtmlPageObject;

import java.time.Duration;

/**
 * The app is a Single Page app. The elements that comprise the page
 */
public class CommonPage extends HtmlPageObject {
    // this can not be used in the Factory, as we want to use it before the factory is initialised
    final By lastElement = By.id("parthianShot");

    @FindBy(xpath = "//*[@id='mainNav']//*[contains(@class,'navbar-nav')]") public WebElement mainNav;
    @FindBy(id = "welcomeBlock") public WebElement pleaseLogInBlock;
    @FindBy(id = "indexBlock") public WebElement indexBlock;
    @FindBy(id = "stoneBlock") public WebElement singleBlock;
    @FindBy(id = "logInModal") public WebElement loginBlock;
    @FindBy(id = "logInNav") public WebElement loginButton;
    @FindBy(id = "logOutNav") public WebElement logOutButton;
    @FindBy(id = "backNav") public WebElement backToIndexButton;
    @FindBy(id = "logInEmail") public WebElement logInAddressField;
    @FindBy(id = "logInPassword") public WebElement logIPasswordField;
    @FindBy(id = "doLogin") public WebElement doLoginButton;
    @FindBy(id = "logInMessage") public WebElement loginFormMessage;

    public CommonPage() {
        // ToDo: see if this can be turned into a Singleton
        super();
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM? - expect it to not be visible in plenty of cases
                .until(ExpectedConditions.presenceOfElementLocated(lastElement));
        PageFactory.initElements(Context.defaultDriver, this);
    }

    public boolean pliCTAisVisible() { return pleaseLogInBlock.isDisplayed(); }

    public boolean loginFormIsVisible() { return loginBlock.isDisplayed();}

    public boolean logInButtonIsVisible() { return loginButton.isDisplayed();}

    public boolean backToIndexButtonIsVisible() { return backToIndexButton.isDisplayed();}

    public boolean logOutButtonIsVisible() { return logOutButton.isDisplayed();}

    public void showLoginForm() {
        loginButton.click();

        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(loginBlock));
    }

    public void enterUsername(String username) { logInAddressField.sendKeys(username); }

    public void enterPassword(String password) { logIPasswordField.sendKeys(password); }

    public void triggerLogin() { doLoginButton.click(); }

    public String getLoginFormMessage() { return loginFormMessage.getText(); }

    /**
     * Custom wait. text-is-this is a standard, this is is contains
     *
     * @param element - the element that you are testing
     * @param text    - the text that you hope to see IN it
     * @return - whether the text is IN the text of the elements
     */
    private ExpectedCondition<Boolean> elementTextContains(WebElement element, String text) {
        return driver -> element.getText().contains(text);
    }

    public void waitForLoginMessageToContain(String expected) {
        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait))
                .until(elementTextContains(loginFormMessage, expected));
    }

}
