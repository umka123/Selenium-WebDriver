import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriver driver;
    private  WebDriverWait wait;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProducts(int n){
        /* Add products to the cart */
        for (Integer i = 0; i < n; i++ ){
            addOneProduct();
        }
    }

    public void addOneProduct(){
        mainPage.open();
        mainPage.pickProduct();
        productPage.pickSize((int)(Math.random()*3+1));
        productPage.addToCart();
    }

    public void removeAllProducts(){
        cartPage.open();
        while (!cartPage.isEmpty()){
            int before = cartPage.itemsQuantity();
            cartPage.removeItem();
            int after = cartPage.itemsQuantity();
            assert (after == before - 1);
        }
    }

}
