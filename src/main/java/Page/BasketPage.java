package Page;

import Base.BasePage;

import Util.DataStore;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;


public class BasketPage extends BasePage {

    private Logger logger = Logger.getLogger(BasketPage.class);

    private final By productPrice=By.id("txtUserName");
    private final By storeName=By.cssSelector("span.merchantLink_2Ii8s>a");

    public BasketPage(WebDriver driver) {
        super(driver);
    }



    public void yazdir(){

        String store1=getElementList(storeName).get(0).getText().toLowerCase().trim();
        String store2=getElementList(storeName).get(1).getText().toLowerCase().trim();


        System.out.println(store1);
        System.out.println(store2);



        System.out.println(DataStore.get(store1));
        System.out.println(DataStore.get(store2));


    }

    @SuppressWarnings("unchecked")
    public static <T extends List<?>> T cast(Object obj) {
        return (T) obj;
    }


}
