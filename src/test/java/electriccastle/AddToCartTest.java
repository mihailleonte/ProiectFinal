package electriccastle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


public class AddToCartTest {


    WebDriver driver;

    String url = "https://electriccastle.ro/";

    @BeforeTest(alwaysRun = true)
    public void setUp(){

        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();


    }

    @Test
    public void addToCart(){
    //Click allow Cookies
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("CybotCookiebotDialogBodyButtonAccept")));
        WebElement allowCookies = driver.findElement(By.id("CybotCookiebotDialogBodyButtonAccept"));
        allowCookies.click();

        //Click Get Tickets
        WebElement getTickets = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.std-button.std-button--light.cta")));
        getTickets.click();

        //Click Add to Cart
        WebElement addToCart1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("section#GENERAL > div > article:nth-of-type(1)  .tag_add_to_cart.tickets__item__select")));
        addToCart1.click();

        //Click View the Cart
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".margin-5.std-button.std-button--bordered")));
        viewCart.click();


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
