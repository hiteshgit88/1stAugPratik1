package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductPOM {

    public ProductPOM(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    //Elements

    @FindBy(how = How.LINK_TEXT, using = "Desktops")
    private WebElement anchorDesktopElement;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Mac")
    private WebElement anchorMacElement;

    @FindBy(how = How.LINK_TEXT, using = "iMac")
    private WebElement anchorIMacElement;

    @FindBy(how = How.ID, using = "button-cart")
    private WebElement btnAddToCartElement;

    @FindBy(how = How.CSS, using = "div#cart > button")
    private WebElement btnGoToCartElement;

    @FindBy(how = How.XPATH, using = ".//p[@class='text-right']/a")
    private WebElement anchorViewCartElement;

    @FindBy(how = How.XPATH, using = ".//div[@id='content']/form/div[1]/table/tbody/tr[1]/td[2]/a")
    public WebElement tblAddedProductElement;

    //end region

    //Action Methods

    private void clickOnDesktopMenu() throws InterruptedException
    {
        anchorDesktopElement.click();

        Thread.sleep(2000);
    }

    public void navigateToMac()  throws InterruptedException
    {
        clickOnDesktopMenu();

        anchorMacElement.click();

        Thread.sleep(2000);
    }

    public void addToCart()  throws InterruptedException
    {
        anchorIMacElement.click();

        Thread.sleep(2000);

        btnAddToCartElement.click();

        Thread.sleep(2000);
    }

    public void goToCart()  throws InterruptedException
    {
        btnGoToCartElement.click();

        Thread.sleep(2000);
    }

    public void viewCart()  throws InterruptedException
    {
        anchorViewCartElement.click();

        Thread.sleep(2000);
    }


    //end region
}
