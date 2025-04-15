package SignUpTest;

import BaseTest.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTest extends Base {
    @Test
    public void TC01_Success() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement((By.xpath("//input[@id='firstName']"))).sendKeys("Lê Văn");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Hào");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("levanhao2182@gmail.com");
        driver.findElement(By.xpath("//label[normalize-space()='Male']")).click();

        driver.findElement(By.xpath("//input[@id='userNumber']")).sendKeys("0123456789");
        WebElement dobField = driver.findElement(By.id("dateOfBirthInput"));
        dobField.click();
        dobField.sendKeys(Keys.CONTROL + "a"); // Chọn toàn bộ
        dobField.sendKeys("15 Apr 2000");
        dobField.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subjectsInput")));
        inputelement.sendKeys("English");
        driver.findElement(By.xpath("//div[contains(@class, 'subjects-auto-complete__menu')]//div[text()='English']")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Sports']")).click();
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        addressField.sendKeys("12 Minh Khai Hà Nội");
        WebElement uploadButton = driver.findElement(By.id("uploadPicture"));
        uploadButton.sendKeys("\u202AC:\\Users\\Lenovo\\Pictures\\img.png");
        WebElement stateDropdown = driver.findElement(By.id("react-select-3-input"));
        Select stateSelect = new Select(stateDropdown);
        WebElement cityDropdown = driver.findElement(By.id("react-select-4-input"));
        Select citySelect = new Select(cityDropdown);
        citySelect.selectByVisibleText("Delhi");
        driver.findElement(By.xpath("//button[@id='submit']")).click();
        WebElement Text = driver.findElement(By.xpath("//div[@id='example-modal-sizes-title-lg']"));
        Assert.assertEquals(Text.getText(),"Thanks for submitting the form");
    }
    @Test
    public void TC02_Fail(){
        driver.findElement(By.xpath("//button[@id='submit']")).click();
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for empty fields");
    }
}

