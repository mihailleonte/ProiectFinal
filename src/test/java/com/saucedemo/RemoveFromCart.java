package com.saucedemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RemoveFromCart {
    WebDriver driver;

    String url = "https://www.saucedemo.com/";

    @BeforeTest(alwaysRun = true)
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--search-engine-choice-country");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        //
        WebElement usernameInput = driver.findElement(By.cssSelector("input#user-name"));
        usernameInput.sendKeys("standard_user");
        //
        WebElement passwordInput = driver.findElement(By.cssSelector("input#password"));
        passwordInput.sendKeys("secret_sauce");
        //
        WebElement loginButton = driver.findElement(By.cssSelector("input#login-button"));
        loginButton.click();

    }
    @Test
    public void addToCart(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Click Add to Cart
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']")));
        WebElement addToCart = driver.findElement(By.xpath("/html//button[@id='add-to-cart-sauce-labs-backpack']"));
        addToCart.click();
        //Click Add to Cart
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button#add-to-cart-sauce-labs-bike-light")));
        WebElement addToCart1 = driver.findElement(By.cssSelector("button#add-to-cart-sauce-labs-bike-light"));
        addToCart1.click();
        //Click View the Cart
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#shopping_cart_container > .shopping_cart_link")));
        viewCart.click();

        //Remove an item
        WebElement removeItem = driver.findElement(By.cssSelector("button#remove-sauce-labs-backpack"));
        removeItem.click();

        //Check the cart
        WebElement cartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        String expectedCartItems = "1";
        String actualCartItems = cartBadge.getText();
        Assert.assertTrue(actualCartItems.contains(expectedCartItems));

        //Check the remove button
        WebElement removeButton = driver.findElement(By.cssSelector("button#remove-sauce-labs-bike-light"));
        String expectedButton= "Remove";
        String actualButton = removeButton.getText();
        Assert.assertTrue(actualButton.contains(expectedButton));




    }


    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
