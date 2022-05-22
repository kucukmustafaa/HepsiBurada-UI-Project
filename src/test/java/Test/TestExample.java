package Test;

import Base.BaseTest;
import Page.HomePage;
import org.testng.annotations.Test;

public class TestExample extends BaseTest {


    @Test(groups = "e2e")
    public void test1(){

        new HomePage(getDriver()).
                    gotoLoginPage().
                    isLoginPageOpen().
                    login("","").
                    isUserLogin();

    }


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
