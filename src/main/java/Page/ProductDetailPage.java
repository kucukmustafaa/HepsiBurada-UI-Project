package Page;

import Base.BasePage;
import Util.DataStore;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class ProductDetailPage extends BasePage {

    private Logger logger = Logger.getLogger(ProductDetailPage.class);

    private final By productPriceElement=By.cssSelector("del[id='originalPrice']");
    private final By productNameElement=By.id("product-name");
    private final By addToBasketButton=By.id("addToCart");
    private final By addToCartSuccessMessage=By.cssSelector("span[class='checkoutui-ProductOnBasketHeader-22Wrk']");
    private final By mainStoreText=By.cssSelector("span.seller>span");
    private final By otherStoreText=By.cssSelector("span.seller>span");
    private final By closePopupButton=By.cssSelector("a[class='checkoutui-Modal-2iZXl']");
    private final By otherStoreName=By.xpath("(//a[@class='merchantStore small'])[1]");
    private final By otherStoreProductPrice=By.xpath("(//span[@class='price-text'])[1]");
    private final By otherStoreAddToBasketButton=By.xpath("(//button[@class='add-to-basket button small'])[1]");
    private final By closeButton=By.cssSelector("i.close");
    private final By goToBasketPageButton=By.id("shoppingCart");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage controlProductDetailPageOpenSuccessfully(){
        changeWindow(1);
        String productListPage_selectedProductName= (String) DataStore.get("productListPage_productName");
        String productListPage_selectedProductPrice= (String) DataStore.get("productListPage_productPrice");

        String productDetailPageProductName=getText(productNameElement).trim();
        String productDetailPageProductPrice=getTextWithJS(productPriceElement).trim();

        logger.info("-------------------------------------");
        logger.info("ürün listeleme sayfası ürün ismi: "+ productListPage_selectedProductName);
        logger.info("ürün detay sayfası ürün ismi: "+ productDetailPageProductName);
        logger.info("-------------------------------------");
        logger.info("ürün listeleme sayfası ürün fiyatı: "+productListPage_selectedProductPrice);
        logger.info("ürün detay sayfası ürün fiyatı: "+productDetailPageProductPrice);

        Assert.assertTrue(productDetailPageProductName.contains(productListPage_selectedProductName));
        Assert.assertTrue(productDetailPageProductPrice.contains(productListPage_selectedProductPrice));

        logger.info("Ürün detay sayfası başarılı açılmıştır.");
        return this;
    }

    public ProductDetailPage addToBasket(){
        String productDetailPageProductName=getText(productNameElement).trim();
        String productDetailPageProductPrice=getTextWithJS(productPriceElement).trim();
        String mainStore=getText(mainStoreText).trim();
        DataStore.put(mainStore,productDetailPageProductName+"-"+productDetailPageProductPrice);
        click(addToBasketButton);
        return this;
    }

    public ProductDetailPage controlProductAddedToBasketMessage(){
        if (isDisplayed(closePopupButton))
            click(closePopupButton);
        logger.info("Ürün sepete başarılı şekilde eklendi.");
        return this;
    }

    public ProductDetailPage addToBasketFromOtherStore(){
        String productName=getText(productNameElement).trim();
        String storeName=getText(otherStoreName).trim().toLowerCase();
        String productPrice=getText(otherStoreProductPrice).trim();
        DataStore.put(storeName,productName+"-"+productPrice);
        click(otherStoreAddToBasketButton);

        if (isDisplayed(closeButton))
            click(closeButton);

        return this;
    }


    public BasketPage goToBasketPage(){

        click(goToBasketPageButton);
        return new BasketPage(driver);
    }


}
