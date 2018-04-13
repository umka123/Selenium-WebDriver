import org.junit.Test;

public class CartTest extends TestBase{

    @Test
    public void addRemove() {
        app.addProducts(3);
        app.removeAllProducts();
    }

}
