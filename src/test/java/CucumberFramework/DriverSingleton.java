package CucumberFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Using Singleton design pattern to maintain a single driver instance across the steps


public class DriverSingleton {
    private static WebDriver driver;


    private DriverSingleton() {
        // Private constructor to prevent instantiation from outside
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/CucumberFramework/resource/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void resetDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }
}