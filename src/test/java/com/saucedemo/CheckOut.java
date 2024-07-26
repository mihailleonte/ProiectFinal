package com.saucedemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CheckOut {
    WebDriver driver;

    String url = "https://www.saucedemo.com/";

    @BeforeTest(alwaysRun = true)
    public void setUp(){

        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement usernameInput = driver.findElement(By.cssSelector("input#user-name"));
        usernameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.cssSelector("input#password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.cssSelector("input#login-button"));
        loginButton.click();
    }


    @Test
    public void Checkout(){

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

        //Click CheckOut
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#checkout")));
        checkOut.click();

        //Enter first name
        WebElement firstName = driver.findElement(By.cssSelector("input#first-name"));
        firstName.sendKeys("Leonte");

        //Enter last name
        WebElement lastName = driver.findElement(By.cssSelector("input#last-name"));
        lastName.sendKeys("Mihail");

        //Enter zipcode
        WebElement zipCode = driver.findElement(By.cssSelector("input#postal-code"));
        zipCode.sendKeys("700210");

        //Click continue
        WebElement continueButton = driver.findElement(By.cssSelector("input#continue"));
        continueButton.click();

        //Click finish

        WebElement finish = driver.findElement(By.cssSelector("button#finish"));
        finish.click();

        //Check message
        WebElement message = driver.findElement(By.cssSelector("div#checkout_complete_container > .complete-header"));
        String expectedMessage = "Thank you for your order!";
        String actualMessage = message.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }

    public void wait(int milliseconds) {
        try {
            driver.wait(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

