package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utility {

    Actions action;

    public void implictWait(WebDriver driver, int intNoOfSecondsToWait) {
        driver.manage().timeouts().implicitlyWait(intNoOfSecondsToWait, TimeUnit.SECONDS);
    }

    public WebElement waitForElementToBeVisible(WebDriver driver, By locator, int intNoOfSecondsToWait) {
        return new WebDriverWait(driver, intNoOfSecondsToWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element, int intNoOfSecondsToWait) {
        return new WebDriverWait(driver, intNoOfSecondsToWait).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForElementCollectionToBeVisible(WebDriver driver, By locator, int intNoOfSecondsToWait) {
        return new WebDriverWait(driver, intNoOfSecondsToWait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementToBeClickable(WebDriver driver, By locator, int intNoOfSecondsToWait) {
        return new WebDriverWait(driver, intNoOfSecondsToWait).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Object executeJavaScript(WebDriver driver, String strJs)
    {
        return ((JavascriptExecutor) driver).executeScript(strJs);
    }

    public Object executeJavaScript(WebDriver driver, String strJs, WebElement element)
    {
        return ((JavascriptExecutor) driver).executeScript(strJs, element);
    }

    public void moveToElementAndClick(WebDriver driver, WebElement element)
    {
        action = new Actions(driver);
        action.moveToElement(element).build().perform();
        element.click();
    }
}