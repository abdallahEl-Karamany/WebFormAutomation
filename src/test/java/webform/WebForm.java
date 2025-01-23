package webform;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class WebForm {
    final String name="Abdallah";
    final String password="asda";
    final String txtArea="asdasdasdasd";
    String getHeader=null;
    final String actualHead="Web form";
    String getHeader2=null;
    final String actualHead2="Form submitted";
    WebDriver driver ;
    File uploadFile = new File("E:/ITI/ISTQB/CTFL/Mind Maps/ch1.png");
    String dynamicXPath;

    @BeforeTest
    public void beforeClass(){
        driver= new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }
    @AfterTest
    public void afterClass(){
        // driver.close();
    }
    @Test
    public void m()  {




        driver.findElement( By.id("my-text-id")).sendKeys(name);


        driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[2]/input")).sendKeys(password);


        driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[3]/textarea")).sendKeys(txtArea);

        getHeader=driver.findElement(By.xpath("/html/body/main/div/div/div/h1")).getText();

        Assert.assertEquals(getHeader,actualHead);

        Select dropDown=new Select(driver.findElement(By.name("my-select")));
        dropDown.selectByVisibleText("Two");


        WebElement search= driver.findElement(By.name("my-datalist"));

        List<WebElement> list=driver.findElements(By.xpath("//datalist[@id=\"my-options\"]//option"));
        for(WebElement lists: list){
            String x=lists.getDomAttribute("value");
            if(x.equals("Seattle")){
                search.click();
                search.sendKeys("Seattle");
                break;
            }

        }


        //search.sendKeys("Seattle");





        driver.findElement(By.id("my-check-1")).click();
        driver.findElement(By.id("my-check-2")).click();
        driver.findElement(By.id("my-radio-1")).click();
        driver.findElement(By.id("my-radio-2")).click();
        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

        WebElement colorpicker=driver.findElement(By.cssSelector("body > main > div > form > div > div:nth-child(3) > label:nth-child(1) > input"));

        colorpicker.sendKeys("#31480F");

        String birthdate="23/06/1954";
        String[] parts = birthdate.split("/");
        int day=Integer.parseInt(parts[0]);
        int month=Integer.parseInt(parts[1]);
        int year=Integer.parseInt(parts[2]);
        int cenYear =(year /100);
        int thYear=(year%100);
        thYear/=10;
        int tenthYear=year%10;
        WebElement date=driver.findElement(By.name("my-date"));
        date.click();

        date=driver.findElement(By.xpath("/html/body/div/div[1]/table/thead/tr[2]/th[2]"));
        date.click();

        date=driver.findElement(By.xpath("/html/body/div/div[2]/table/thead/tr[2]/th[2]"));
        date.click();

        date=driver.findElement(By.xpath("/html/body/div/div[3]/table/thead/tr[2]/th[2]"));
        date.click();

        date=driver.findElement(By.xpath("/html/body/div/div[4]/table/thead/tr[2]/th[2]"));
        date.click();

        for(int i=1;i<=12;i++) {

            if(cenYear ==19)
            {
                dynamicXPath = String.format("/html/body/div/div[5]/table/tbody/tr/td/span[%d]", (i));
                date = driver.findElement(By.xpath(dynamicXPath));

                date.click();
                break;
            }
            if(cenYear >19) {
                cenYear = cenYear - 1;
            }
        }

        for(int i=0;i<12;i++)
        {
            if(thYear==i) {

                dynamicXPath=String.format("/html/body/div/div[4]/table/tbody/tr/td/span[%d]",(i+2));
                date = driver.findElement(By.xpath(dynamicXPath));
                date.click();
                break;
            }
        }


        for(int i=0;i<12;i++) {
            if(i == tenthYear) {
                int index=i+2;

                dynamicXPath=String.format("/html/body/div/div[3]/table/tbody/tr/td/span[%d]",index);
                date = driver.findElement(By.xpath(dynamicXPath));
                date.click();
                break;
            }
        }


        for(int i=1;i<=12;i++) {

            if(month==i) {

                dynamicXPath=String.format("/html/body/div/div[2]/table/tbody/tr/td/span[%d]",(i));
                date = driver.findElement(By.xpath(dynamicXPath));
                date.click();
                break;
            }
        }


        for(int i=1;i<=6;i++) {
            for(int j=1;j<=7;j++) {

                dynamicXPath=String.format("/html/body/div/div[1]/table/tbody/tr[%d]/td[%d]",i,j);
                date = driver.findElement(By.xpath(dynamicXPath));
                String dayClass=date.getDomAttribute("class");
                if(dayClass.equals("day")) {
                    System.out.println(dayClass);
                    String getDay = driver.findElement(By.xpath(dynamicXPath)).getText();
                    int dayInt = Integer.parseInt(getDay);

                    if (day == dayInt) {
                        date.click();
                        break;
                    }
                }
            }
        }

        WebElement slider=driver.findElement(By.name("my-range"));
        for(int i=0 ;i<10;i++){
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        for(int i=10;i>0;i--){
            slider.sendKeys(Keys.ARROW_LEFT);
        }



        driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button")).click();

        getHeader2=driver.findElement(By.xpath("/html/body/main/div/div[1]/div/h1")).getText();

        Assert.assertEquals(getHeader2,actualHead2);


    }




}
