package testSuite.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
 * All of the pages of this web site have common furniture (nav bar, contents-zone, footer)
 */
public class CommonPage extends HtmlPageObject {
    protected final WebDriver myDriver;

    // this can not be used in the Factory, as we want to use it before the factory is initialised
    final By lastElement = By.id("parthianShot");

    @FindBy(xpath = "//*[@id='mainNav']//*[contains(@class,'navbar-nav')]") private WebElement mainNav;
    @FindBy(id = "welcome") private WebElement pleaseLogInBlock;
    @FindBy(id = "index") private WebElement indexBlock;
    @FindBy(id = "stone") private WebElement singleBlock;
    @FindBy(id = "logInModal") private WebElement loginBlock;
    @FindBy(id = "logInNav") private WebElement loginButton;
    @FindBy(id = "logOutNav") private WebElement logOutButton;
    @FindBy(id = "backNav") private WebElement backToIndexButton;
    @FindBy(id = "logInEmail") private WebElement logInAddressField;
    @FindBy(id = "logInPassword") private WebElement logIPasswordField;
    @FindBy(id = "doLogin") private WebElement doLoginButton;
    @FindBy(id = "logInMessage") private WebElement loginFormMessage;


    public CommonPage() {
        super();
        myDriver = Context.defaultDriver;
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM? - expect it to not be visible in plenty of cases
                .until(ExpectedConditions.presenceOfElementLocated(lastElement));
        PageFactory.initElements(myDriver, this);
    }

    /**
     * Not the use of normalize() - t is most likely that the user's view of the link text is a noralised string,
     * but the IDE may have munged it (to no end effect - except for space chars sent)
     *
     * @param displayedText - case sensitive the text that the user sees
     * @return - the first element in the main nav bar that IS the text given in the param
     */
    public WebElement getNavItem(String displayedText) {
        // byLinkText consistently fails to find the these links, but the following is fast enough,and reliable
        return mainNav.findElement(By.xpath("//a[normalize-space(text())='" + displayedText + "']"));
    }

    public boolean pliCTAisVisible() { return pleaseLogInBlock.isDisplayed(); }

    public boolean indexStoneIsVisible() {
        return indexBlock.isDisplayed();
    }

    /**
     * This is of use when you make transitions (log on, or whatever)
     */
    public void waitForIndexStoneToBeVisible() {
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(indexBlock));
    }

    public boolean singleStoneIsVisible() {
        return singleBlock.isDisplayed();
    }

    public boolean loginFormIsVisible() { return loginBlock.isDisplayed();}

    public boolean logInButtonIsVisible() { return loginButton.isDisplayed();}

    public boolean backToIndexButtonIsVisible() { return backToIndexButton.isDisplayed();}

    public boolean logOutButtonIsVisible() { return logOutButton.isDisplayed();}

    public void showLoginForm() { loginButton.click(); }

    public void setUsername(String username) { logInAddressField.sendKeys(username); }

    public void setPassword(String password) { logIPasswordField.sendKeys(password); }

    public void clickLoginButton() { doLoginButton.click(); }

    public String getLoginFormMessage() { return loginFormMessage.getText(); }

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
