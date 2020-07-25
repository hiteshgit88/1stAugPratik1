package pom;

import config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class RegisterationPOM {

    //Object/Variables

    SeleniumConfig _selConfig;
    WebElement _element;

    //end region

    public RegisterationPOM(SeleniumConfig _seleniumConfig)
    {
        PageFactory.initElements(_seleniumConfig.driver, this);
        _selConfig = _seleniumConfig;
    }

    //Elements

    @FindBy(how = How.XPATH, using = ".//a[@title='My Account']")
    private WebElement anchorMyAccElement;
    private By byAnchorMyAccElement = By.xpath(".//a[@title='My Account']");

    @FindBy(how = How.LINK_TEXT, using = "Register")
    private WebElement anchorRegisterElement;
    private By byAnchorRegisterElement = By.linkText("Register");

    @FindBy(how = How.ID, using = "input-firstname")
    private WebElement txtFirstNameElement;
    private By byTxtFirstNameElement = By.id("input-firstname");

    @FindBy(how = How.ID, using = "input-lastname")
    private WebElement txtLastNameElement;

    @FindBy(how = How.ID, using = "input-email")
    public WebElement txtEmailIdElement;

    @FindBy(how = How.ID, using = "input-telephone")
    public WebElement txtPhoneNoElement;

    @FindBy(how = How.ID, using = "input-password")
    public WebElement txtPwdElement;

    @FindBy(how = How.ID, using = "input-confirm")
    public WebElement txtConfPwdElement;

    @FindBy(how = How.XPATH, using = ".//input[@name='agree']")
    public WebElement ChkboxAgreementElement;

    @FindBy(how = How.XPATH, using = ".//input[@type='submit']")
    public WebElement btnSubmitElement;

    @FindBy(how = How.XPATH, using = ".//div[@id='content']/p[1]")
    public WebElement divRegisterSuccessMsgElement;
    public By byDivRegisterSuccessMsgElement = By.xpath(".//div[@id='content']/p[1]");

    //end region

    //Action Methods

    public void clickOnRegisterLink()
    {
        //Wait for My Account element to be clickable & Click on it
        _element = _selConfig._utility.waitForElementToBeClickable(_selConfig.driver, byAnchorMyAccElement, Integer.parseInt(_selConfig._hashmapAppProp.get("noOfSecondToWait")));
        _selConfig._utility.executeJavaScript(_selConfig.driver, "arguments[0].click();", _element);

        //Wait for Register element to be clickable & Click on it
        _element = _selConfig._utility.waitForElementToBeClickable(_selConfig.driver, byAnchorRegisterElement, Integer.parseInt(_selConfig._hashmapAppProp.get("noOfSecondToWait")));
        _selConfig._utility.executeJavaScript(_selConfig.driver, "arguments[0].click();", _element);
    }

    public void fillRegistrationDetails(int intRecordPtr, JTable _jTableSource)
    {
        //Wait for First Name element to be visible
        _selConfig._utility.waitForElementToBeVisible(_selConfig.driver, byAnchorMyAccElement, Integer.parseInt(_selConfig._hashmapAppProp.get("noOfSecondToWait")));
        txtFirstNameElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("FirstName").getModelIndex()).toString());

        txtLastNameElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("LastName").getModelIndex()).toString());
        txtEmailIdElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("EmailId").getModelIndex()).toString());
        txtPhoneNoElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("PhoneNo").getModelIndex()).toString());
        txtPwdElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("Password").getModelIndex()).toString());
        txtConfPwdElement.sendKeys(_jTableSource.getValueAt(intRecordPtr, _jTableSource.getColumn("ConfPassword").getModelIndex()).toString());

        ChkboxAgreementElement.click();

        btnSubmitElement.click();
    }

    //end region
}
