package waitpck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waitexp {

    By Field= new By.ById("revealed");


    @Test
    public void Second_Test() throws InterruptedException {
        //open chrome
        WebDriver driver = new ChromeDriver();

        //navigate to url
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");


        driver.findElement(By.id("reveal")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(Field));
        element.sendKeys("abdndjasj");

        wait.until(ExpectedConditions.attributeToBe(Field, "value", "abdndjasj"));

        System.out.println("Text successfully entered!");

        Thread.sleep(200);
        driver.close();


    }
}