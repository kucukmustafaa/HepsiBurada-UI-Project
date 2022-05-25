package Page;

import Base.BasePage;
import Util.DataStore;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.util.List;

public class ProductListPage extends BasePage {


    private Logger logger = Logger.getLogger(ProductListPage.class);

    private final By searchResultText=By.cssSelector("h1[class='searchResultSummaryBar-bold']");
    private final By productName=By.cssSelector("h3[data-test-id='product-card-name']");
    private final By productCard=By.cssSelector("div[data-test-id='product-card-container']");
    private final By productCurrentPrice=By.cssSelector("div[data-test-id='price-current-price']");
    private final By addToBasketButton=By.cssSelector("button[data-test-id='product-info-button']");
    private final By goTobasketPageButton=By.id("shoppingCart");
    private final By successfulAddedToCartMessage=By.cssSelector("div[class='hb-toast-text']");


    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage controlProductListPageSuccessOpen(String expectedText){
        String actualSearchResultText=getText(searchResultText);
        Assert.assertTrue(actualSearchResultText.equals(expectedText));
        logger.info("Ürün listeleme sayfası başarılı şekilde açılmıştır.");
        return this;
    }

    public ProductListPage addToBasket(){
        List<WebElement> productCardList=getElementList(productCard);
        List<WebElement> productNameList=getElementList(productName);
        List<WebElement> productCurrentPriceList=getElementList(productCurrentPrice);

        int productListSize=productCardList.size();
        int productOrderNumber=getRandomNumber(productListSize);

        WebElement selectedProduct=productCardList.get(productOrderNumber);
        String selectedProductName=productNameList.get(productOrderNumber).getText();
        String selectedProductPrice=productCurrentPriceList.get(productOrderNumber).getText();

        logger.info("Ürün listesinden "+ productOrderNumber+". ürün seçildi");
        logger.info("Seçilen ürünün ismi: "+selectedProductName);
        logger.info("Seçilen ürünün fiyatı: "+selectedProductPrice);

        DataStore.put("productListPage_productName",selectedProductName);
        DataStore.put("productListPage_productPrice",selectedProductPrice);

        hoverToElement(selectedProduct);

        click(addToBasketButton);

        return this;
    }


    public ProductDetailPage clickRandomProduct(){
        List<WebElement> productCardList=getElementList(productCard);
        List<WebElement> productNameList=getElementList(productName);
        List<WebElement> productCurrentPriceList=getElementList(productCurrentPrice);

        int productListSize=productCardList.size();
        int productOrderNumber=getRandomNumber(productListSize);

        WebElement selectedProduct=productCardList.get(productOrderNumber);
        String selectedProductName=productNameList.get(productOrderNumber).getText();
        String selectedProductPrice=productCurrentPriceList.get(productOrderNumber).getText();

        logger.info("Ürün listesinden "+ productOrderNumber+". ürün seçildi");
        logger.info("Seçilen ürünün ismi: "+selectedProductName);
        logger.info("Seçilen ürünün fiyatı: "+selectedProductPrice);

        DataStore.put("productListPage_productName",selectedProductName);
        DataStore.put("productListPage_productPrice",selectedProductPrice);

        selectedProduct.click();

        return new ProductDetailPage(driver);
    }

    public ProductListPage controlSuccessAddedToBasketMessage(String expectedText){
        String actualText=getText(successfulAddedToCartMessage);
        Assert.assertTrue(actualText.trim().equals(expectedText));
        logger.info("Ürün sepete eklendi mesjaı başarılı şekilde görülmüştür.");
        return this;
    }

    public BasketPage gotoBasketPage(){
        click(goTobasketPageButton);
        return new BasketPage(driver);
    }





}
