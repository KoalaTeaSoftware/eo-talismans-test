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

    public boolean indexStoneIsVisible() { return indexBlock.isDisplayed(); }

    /**
     * This is of use when you make transitions (log on, or whatever)
     */
    public void waitForIndexStoneToBeVisible() {
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(indexBlock));
    }

    public boolean singleStoneIsVisible() { return singleBlock.isDisplayed(); }

    public void singleStoneBecomesVisible() {
        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(singleBlock));
    }

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

    public void selectFromIndexStone(String nameOfStone) {
        testSuite.objects.pages.IndexStone indexStone = new IndexStone();
        indexStone.selectStone(nameOfStone);
    }
}
