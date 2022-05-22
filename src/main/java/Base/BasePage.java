package Base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {


    protected WebDriver driver;
    protected WebDriverWait wait;

    private Logger logger = Logger.getLogger(BasePage.class);


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    protected void click(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.click();
            logger.info(by.toString()+" elementine tiklandi.");
        }catch (Exception e){
            logger.error(by.toString()+" elementine tiklanamadi.");
        }
    }

    protected void write(By by,String text){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.sendKeys(text);
            logger.info(by.toString()+" elementine "+text+" degeri yazildi..");
        }catch (Exception e){
            logger.error(by.toString()+" elementine "+text+ "degeri yazilmadi.");
        }
    }

    protected void enter(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(Keys.ENTER);
        logger.info(by.toString()+" enter tuşu gönderildi.");
    }


    protected void tab(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(Keys.TAB);
        logger.info(by.toString()+" tab tuşu gönderildi.");

    }

    protected void waitSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
            logger.info(seconds+" saniye bekleniyor.");
        } catch (Exception e){
            logger.error("bekleme gerceklesmedi.");
        }
    }

    protected String getText(By by){
        WebElement element=this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getText();
    }

    protected WebElement findElement(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));;
        return element;
    }

    protected void clickWithOnFocus(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.click();
            logger.info(by.toString()+" elementine onFocus ile tiklandi.");
        }catch (Exception e){
            logger.error(by.toString()+" elementine onFocus ile tiklanamadi.");
        }
    }

    public boolean isChecked(By by){
        return findElement(by).isSelected();
    }

    public String getAttribute(By by,String attributeName){
        return findElement(by).getAttribute(attributeName);
    }



    protected List<WebElement> getElementList(By by){
        List<WebElement> elementList = null;
        try {
            elementList=this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            logger.info(by.toString()+" elementin listesi geldi.");
        }catch (Exception e){
            logger.error(by.toString()+" element listesi gelmedi..");
        }
        return elementList;
    }

    protected void hover(By by){
        Actions actions=new Actions(driver);
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        actions.moveToElement(element).build().perform();
    }

    protected void hoverToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected String getPageTitle(){
        return driver.getTitle();
    }

    protected boolean isDisplayed(By by){
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    protected int getRandomNumber(int limit){
        Random random=new Random();
        int randomNumber=random.nextInt(limit);
        return randomNumber;
    }

    public void changeWindow(int windowNumber){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(windowNumber));
    }

    public String getTextWithJS(By by){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement element=this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
        String text = (String) js.executeScript("return arguments[0].innerHTML",element);
        return text;
    }

    public int getElementListSize(By by){
        List<WebElement> elementList=getElementList(by);
        return elementList.size();
    }

    public WebElement getElementFromList(By by,int index){
        return getElementList(by).get(index);
    }


    public String getTextFromElementList(By by,int index){
        List<WebElement> elementList=getElementList(by);
        return elementList.get(index).getText();
    }


}
