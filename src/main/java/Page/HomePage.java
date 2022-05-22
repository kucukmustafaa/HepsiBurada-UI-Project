package Page;

import Base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {

    private Logger logger = Logger.getLogger(HomePage.class);


    private final By myAccountButton=By.id("myAccount");
    private final By myLoginButton=By.id("login");
    private final By myAccountUser=By.className("sf-OldMyAccount-1k66b");
    private final By searchTextBox=By.xpath("//div[@id='SearchBoxOld']//input");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage gotoLoginPage(){
        hover(myAccountButton);
        click(myLoginButton);
        logger.info("Ana sayfa üye girişi butonuna tıklandı.");
        return new LoginPage(driver);
    }

    public HomePage isUserLogin(){
        boolean isUserLogin=isDisplayed(myAccountUser);
        Assert.assertTrue(isUserLogin);
        logger.info("User başarılı şekilde login olmuştur.");
        return this;
    }


    public ProductListPage search(String text){
        write(searchTextBox,text);
        enter(searchTextBox);
        logger.info(text+ " ile arama yapıldı.");
        return new ProductListPage(driver);
    }




}
