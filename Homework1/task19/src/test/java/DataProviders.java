import com.tngtech.java.junit.dataprovider.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DataProviders {
    @DataProvider
    public static Object[][] validProducts() {
        return new Object[][] {
                { Products.newEntity()
                        .withAmount((int) (Math.random()*3 + 1)) },
                /* ... */
        };
    }
}
