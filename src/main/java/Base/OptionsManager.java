package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


public class OptionsManager {

    public static ChromeOptions getChromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--kiosk");
        options.addArguments("--disable-notifications");
      //  options.addArguments("--start-fullscreen");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        WebDriverManager.chromedriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--kiosk");
        options.addArguments("--start-fullscreen");
        return options;
    }



}
