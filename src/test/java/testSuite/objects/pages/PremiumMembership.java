package testSuite.objects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;

import java.time.Duration;

/**
 * This is the popup that allows a person to buy the premium set of talismans
 * For development purposes, there is no actual card details collection, just a button that
 * marks the person as a premium member.
 * It will need to talk to the server and change the user's status
 */
public class PremiumMembership extends CommonPage {
    @FindBy(id = "premiumPurchaseBlock") public WebElement premiumPurchaseBlock;
    @FindBy(id = "startPurchase") public WebElement buyButton;
    @FindBy(id = "alreadyAMember") public WebElement alreadyAMemberButton;

    public boolean purchaseFacilityIsVisible() { return premiumPurchaseBlock.isDisplayed(); }

    /**
     * This is just a stub, for the moment. It will have to enter card details and all that sort of stuff
     */
    public void triggerPurchase() {
        buyButton.click();
    }

    public void purchaseFacilityBecomesVisible() {
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM? - expect it to not be visible in plenty of cases
                .until(ExpectedConditions.visibilityOf(premiumPurchaseBlock));
    }

    public void purchaseFacilityBecomesHidden() {
        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait)
        ).until(ExpectedConditions.invisibilityOf(premiumPurchaseBlock));
    }

    public void signifyAlreadyAMember() { alreadyAMemberButton.click(); }
}
