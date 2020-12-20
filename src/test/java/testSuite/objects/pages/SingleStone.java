package testSuite.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;

import java.time.Duration;

public class SingleStone extends CommonPage {
    public SingleStone() {
        super();
    }

    public String getStoneName() {
        return myDriver.findElement(By.id("singleStoneImage")).getAttribute("data-StoneName");
    }

    public boolean singleStoneIsVisible() { return singleBlock.isDisplayed(); }

    public void singleStoneBecomesVisible() {
        new WebDriverWait(
                Context.defaultDriver,
                Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(singleBlock));
    }

}
