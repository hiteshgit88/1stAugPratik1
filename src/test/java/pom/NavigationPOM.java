package pom;

import config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;
import java.util.List;

public class NavigationPOM {

    //Object/Variables

    SeleniumConfig _selConfig;

    //end region

    public NavigationPOM(SeleniumConfig _seleniumConfig)
    {
        PageFactory.initElements(_seleniumConfig.driver, this);
        _selConfig = _seleniumConfig;
    }

    //Elements

    @FindBy(how = How.LINK_TEXT, using = "Desktops")
    private WebElement anchorDesktopElement;
    private By byAnchorDesktopElement = By.linkText("Desktops");

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Mac")
    private WebElement anchorMacElement;
    private By byAnchorMacElement = By.partialLinkText("Mac");

    @FindBy(how = How.XPATH, using = ".//ul[@class='nav navbar-nav']/li[1]/div/div/ul/li/a")
    private List<WebElement> anchorListOfDesktopSubmenu;
    private By byAnchorListOfDesktopSubmenu = By.xpath(".//ul[@class='nav navbar-nav']/li[1]/div/div/ul/li/a");

    //end region

    //Action Methods

    private void clickOnDesktopMenu() throws InterruptedException
    {
        //anchorDesktopElement.click();

        //Thread.sleep(2000);

        _selConfig._utility.waitForElementToBeVisible(_selConfig.driver, byAnchorDesktopElement, Integer.parseInt(_selConfig._hashmapAppProp.get("noOfSecondToWait"))).click();
    }

    public DesktopPOM navigateToDesktopSubmenu(String strDesktopSubMenuOption)  throws InterruptedException
    {
        clickOnDesktopMenu();

        List<WebElement> listDesktopSubmenus = _selConfig._utility.waitForElementCollectionToBeVisible(_selConfig.driver, byAnchorListOfDesktopSubmenu, Integer.parseInt(_selConfig._hashmapAppProp.get("noOfSecondToWait")));
        ArrayList<WebElement> arrListDesktopSubmenus = new ArrayList<>(listDesktopSubmenus);

        for (WebElement _element: arrListDesktopSubmenus) {

            if(_element.getText().substring(0, _element.getText().indexOf(' ')).equalsIgnoreCase(strDesktopSubMenuOption))
            {
                _element.click();
                break;
            }

        }

        //Thread.sleep(2000);

        return new DesktopPOM(_selConfig);
    }

    //end region
}
