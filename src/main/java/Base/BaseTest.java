package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BaseTest {

    protected String env="local"; // local -- remote
    protected String selectBrowser = "chrome";
    protected WebDriverWait wait;
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private String HUB_ADDRESS="http://localhost:4444";
    protected String WEB_URL="https://www.hepsiburada.com/";


    @BeforeMethod
    public void setup () throws MalformedURLException {
        PropertyConfigurator.configure("log4j.properties");

        if (env.equals("local")){
            if (selectBrowser.equals("chrome")){
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
                wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                driver.get().get(WEB_URL);
            }else if (selectBrowser.equals("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
                wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                driver.get().get(WEB_URL);
            }
        }else {
            if (selectBrowser.equals("chrome")){
                driver.set(new RemoteWebDriver(new URL(HUB_ADDRESS), OptionsManager.getChromeOptions()));
                wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                driver.get().get(WEB_URL);
            }else if (selectBrowser.equals("firefox")){
                driver.set(new RemoteWebDriver(new URL(HUB_ADDRESS), OptionsManager.getFirefoxOptions()));
                wait=new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                driver.get().get(WEB_URL);
            }
        }

    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        driver.remove();
    }





}
