package CucumberFramework.hooks;

import CucumberFramework.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp() {

        System.out.println("Browser setup called");
        driver = DriverSingleton.getDriver();

        // When the browser opens, maximise the window
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }



//    @After
//    public void tearDown() throws InterruptedException {
//        if (driver != null){
//            Thread.sleep(4000);
//            DriverSingleton.resetDriver();
//        }
//    }
}
