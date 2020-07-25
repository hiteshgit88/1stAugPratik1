package test;

import config.SeleniumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.RegisterationPOM;

public class TestMyAccount extends SeleniumConfig {

    int intNoOfRegistration;
    int intRegistrationDataPtr;

    public TestMyAccount()
    {
        if(currentBrowser.equals(eBrowserType.CHROME))
        {
            intNoOfRegistration = eNoOfRegistration.CHROME.getNoOfRegistration();
            intRegistrationDataPtr = eNoOfRegistration.CHROME.getNoOfRegistration();
        }
        else if(currentBrowser.equals(eBrowserType.FIREFOX))
        {
            intNoOfRegistration = eNoOfRegistration.FIREFOX.getNoOfRegistration();
            intRegistrationDataPtr = eNoOfRegistration.FIREFOX.getNoOfRegistration();
        }
    }

    @Test(priority = 0)
    public void registerMyAccountTest()
    {
        try {

            System.out.println("Thread : " + Thread.currentThread().getName());

            _jTableXL = _excelhelper.convertExcelToDataTable(strPathToDatasheet + _hashmapAppProp.get("TestRegistrationDataFileName"), "Sheet1");

            RegisterationPOM _registrationPOM = new RegisterationPOM(this);

            _registrationPOM.clickOnRegisterLink();

            //for (int intPtr = 0; intPtr < _jTableXL.getRowCount() - 1; intPtr++) {

            for (int intPtr = intRegistrationDataPtr; intPtr < intNoOfRegistration + intRegistrationDataPtr; intPtr++) {

                //_registrationPOM.fillRegistrationDetails(intPtr, _jTableXL);

                //_softAssert.assertEquals(_hashmapAppMsgProp.get("registrationSuccessMsg"), _utility.waitForElementToBeVisible(driver, _registrationPOM.byDivRegisterSuccessMsgElement, Integer.parseInt(_hashmapAppProp.get("noOfSecondToWait"))).getText(), "Success Registration message doesn't match");
            }

            //_softAssert.assertAll();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }

    }
}
