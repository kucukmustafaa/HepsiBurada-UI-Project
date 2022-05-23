package Test;

import Base.BaseTest;
import Page.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;


@Feature("E2E Tests")
public class TestExample extends BaseTest {

    @Story("Login olarak sepete ürün ekleme")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "e2e")
    public void test1(){

        new HomePage(getDriver()).
                    gotoLoginPage().
                    isLoginPageOpen().
                    login("","").
                    isUserLogin();

    }


    @Story("Login olmadan sepete ürün ekleme")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "e2e")
    public void test2(){
        new HomePage(getDriver()).
                search("bilgisayar").
                controlProductListPageSuccessOpen("bilgisayar").
                clickRandomProduct().
                controlProductDetailPageOpenSuccessfully().
                addToBasket().
                controlProductAddedToBasketMessage().
                addToBasketFromOtherStore().
                goToBasketPage();



    }
}
