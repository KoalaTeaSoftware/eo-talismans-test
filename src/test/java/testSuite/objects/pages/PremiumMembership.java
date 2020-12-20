package testSuite.objects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This is the popup that allows a person to buy the premium set of talismans
 * For development purposes, there is no actual card details collection, just a button that
 * marks the person as a premium member.
 * It will need to talk to the server and change the user's status
 */
public class PremiumMembership extends CommonPage {
    @FindBy(id = "premiumPurchaseBlock")
    public WebElement premiumPurchaseBlock;

    @FindBy(id = "startPurchase")
    public WebElement buyButton;

    public boolean purchaseFacilityIsVisible() { return premiumPurchaseBlock.isDisplayed(); }

    /**
     * This is just a stub, for the moment. It will have to enter card details and all that sort of stuff
     */
    public void triggerPurchase() {
        buyButton.click();
    }
}
