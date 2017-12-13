package com.likhtarovich.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class TestBase {

    private static final String URL = "http://localhost:8080/pma/";
    protected static WebDriver driver;

    public void setup() {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\LIKHTAROVICH\\IdeaProjects\\HT3\\src\\main\\java\\com\\likhtarovich\\geckoDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    public void exit() {
        driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

