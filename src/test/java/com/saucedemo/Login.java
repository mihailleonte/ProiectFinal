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
import org.testng.Assert;

import java.time.Duration;


public class Login {
    WebDriver driver;

    String url = "https://www.saucedemo.com/";

    @BeforeTest(alwaysRun = true)
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--search-engine-choice-country");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();}

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

        //check loading url
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

    }

}
