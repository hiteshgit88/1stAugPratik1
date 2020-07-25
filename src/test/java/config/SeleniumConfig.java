package config;

import Util.ExcelHelper;
import Util.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Properties;

public abstract class SeleniumConfig {

    public eBrowserType currentBrowser;
    public WebDriver driver;
    public Properties _properties = new Properties();
    public HashMap<String, String> _hashmapAppProp = new HashMap<>();
    public HashMap<String, String> _hashmapAppMsgProp = new HashMap<>();
    public String strPathToDatasheet;
    public JTable _jTableXL;

    public Utility _utility = new Utility();
    public ExcelHelper _excelhelper = new ExcelHelper();

    public SoftAssert _softAssert = new SoftAssert();

    public enum eBrowserType
    {
        CHROME,
        FIREFOX
    }

    public enum eNoOfRegistration
    {
        CHROME (2),
        FIREFOX (2);

        private int _intNoOfRegistration;

        eNoOfRegistration(int IntDataPtr)
        {
            _intNoOfRegistration = IntDataPtr;
        }

        public int getNoOfRegistration()
        {
            return _intNoOfRegistration;
        }
    }

    public enum eRegistrationDataPtr
    {
        CHROME (0),
        FIREFOX (2);

        private int _intRegistrationDataPtr;

        eRegistrationDataPtr(int IntDataPtr)
        {
            _intRegistrationDataPtr = IntDataPtr;
        }

        public int getRegistrationDataPtr()
        {
         return _intRegistrationDataPtr;
        }
    }


    public SeleniumConfig()
    {
        try
        {
            _hashmapAppProp = loadPropertiesFile("/app");
            _hashmapAppMsgProp = loadPropertiesFile("/appMsg");
            strPathToDatasheet = getDirPathToResources() + "DataSheets/";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String getDirPathToResources()
    {
        String strDecodedPath = null;

        try {
            String strPath = SeleniumConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            strDecodedPath = URLDecoder.decode(strPath, "UTF-8").substring(0, strPath.lastIndexOf("/") - 1);

            if(strDecodedPath.startsWith("/"))
            {
                strDecodedPath = strDecodedPath.substring(1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return strDecodedPath;
    }

    private HashMap loadPropertiesFile(String strPathToPropertiesFile)
    {
        HashMap<String, String> _hashmap;

        try {

            _hashmap = new HashMap<String, String>();

            _properties.load(this.getClass().getResourceAsStream(strPathToPropertiesFile));

            for (String Key : _properties.stringPropertyNames()) {
                String value = _properties.getProperty(Key);
                _hashmap.put(Key, value);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            _hashmap=null;
        }

        return _hashmap;
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void browserSetup(@Optional("Chrome") String strBrowser)
    {
        if(strBrowser.equalsIgnoreCase(eBrowserType.CHROME.toString()))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            currentBrowser = eBrowserType.CHROME;

            launchURL(_hashmapAppProp.get("appURL"));
        }
        else if(strBrowser.equalsIgnoreCase(eBrowserType.FIREFOX.toString()))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

            currentBrowser = eBrowserType.FIREFOX;

            launchURL(_hashmapAppProp.get("appURL"));
        }
        else
        {
            System.out.println("Please select appropriate browser");
        }
    }

    private void launchURL(String strURL)
    {
        driver.navigate().to(strURL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void browserQuit()
    {
        driver.quit();
    }
}
