package utilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.timeout = 30000;

    }

    @Before
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }
}
