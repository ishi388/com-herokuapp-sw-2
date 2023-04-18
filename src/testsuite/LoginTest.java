package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);//opening browser
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");//finding element for username and send value
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");//finding element for username and send key
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();//finding element and click
        String expectedMessage = "Secure Area";//
        WebElement actualMessage = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]"));
        String actualMessage1 = actualMessage.getText();
        //System.out.println(actualMessage1);
        Assert.assertEquals("Error Message is not Displayed",expectedMessage,actualMessage1);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //verifying the error message is displayed or not
        String expectedMessage = "Your username is invalid!";
        WebElement actualMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessageText = actualMessage.getText().substring(0,25);
        // System.out.println(actualMessageText);
        Assert.assertEquals("Your username is invalid",expectedMessage,actualMessageText);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //this is requirement
        String expectedText = "Your password is invalid!";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualText = actualTextElement.getText().substring(0,25);
        Assert.assertEquals(expectedText, actualText);

    }

    @After
    public void tearDown() {

        //  closeBrowser();
    }

}