package pom;

import config.SeleniumConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DesktopPOM {

    //Object/Variables

    SeleniumConfig _selConfig;

    //end region

    public DesktopPOM(SeleniumConfig _seleniumConfig)
    {
        PageFactory.initElements(_seleniumConfig.driver, this);
        _selConfig = _seleniumConfig;
    }

    //Elements

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
