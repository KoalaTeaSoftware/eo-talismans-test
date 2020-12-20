package testSuite.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;

import java.time.Duration;

public class IndexStone extends CommonPage {
    public IndexStone() {
        super();
    }

    public void selectStone(String nameOfStone) {
        myDriver.findElement(By.xpath("//*[name()='svg']/*[name()='rect' and @id='" + nameOfStone + "']")).click();
    }

    public boolean indexStoneIsVisible() { return indexBlock.isDisplayed(); }

    /**
     * This is of use when you make transitions (log on, or whatever)
     */
    public void waitForIndexStoneToBeVisible() {
        new WebDriverWait(Context.defaultDriver, Duration.ofSeconds(Context.pageLoadWait))
                .until(ExpectedConditions.visibilityOf(indexBlock));
    }


}
