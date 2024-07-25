package com.saucedemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class AddToCartTest {
    WebDriver driver;

    String url = "https://www.saucedemo.com/";

    @BeforeTest(alwaysRun = true)
    public void setUp(){


        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();


    }
    @Test
    public void login(){
        //enter username
        WebElement usernameInput = driver.findElement(By.cssSelector("input#user-name"));
        usernameInput.sendKeys("standard_user");

        //enter password
        WebElement passwordInput = driver.findElement(By.cssSelector("input#password"));
        passwordInput.sendKeys("secret_sauce");


        //click login
        WebElement loginButton = driver.findElement(By.cssSelector("input#login-button"));
        loginButton.click();


    }

    @Test
    public void addToCart(){

        //Click allow Cookies
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("overlay")));
        WebElement selectEngine = driver.findElement(By.id("overlay"));
        selectEngine.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("actionButton")));
        WebElement setAsDefault = driver.findElement(By.id("actionButton"));
        setAsDefault.click();



        //Click Add to Cart
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']")));
        WebElement addToCart = driver.findElement(By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']"));
        addToCart.click();

        //Click Add to Cart
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']")));
        WebElement addToCart1 = driver.findElement(By.cssSelector("/html//button[@id='add-to-cart-sauce-labs-backpack']"));
        addToCart1.click();

        //Click View the Cart
        //WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        //viewCart.click();


    }

//    @AfterTest(alwaysRun = true)
//    public void tearDown() {
//        driver.close();
//    }

    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
