package com.juaracoding.traveltest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        // Inisialisasi WebDriver
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/test/newtours/register.php");
    }

    @Test
    public void registerUserTest() throws InterruptedException {

        // isi form country di awal menghindari popup -
        // (karna ada popup saat isi country)
        // maaf belum menumukan caranya. masih bingung .
        // Ada saran dari temen pakai JavascriptExecutor
        // namun belum saya implementasikan
        Select select = new Select(driver.findElement(By.name("country")));
        select.selectByVisibleText("INDONESIA");

        WebElement selectedOption = select
                .getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getText(), selectedOption.getAttribute("value"));
        Assert.assertEquals(selectedOption.getText(), "INDONESIA");
        Assert.assertEquals(selectedOption.getAttribute("value"), "INDONESIA");

        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("nailul");
        Thread.sleep(500);
        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("aqtor");
        Thread.sleep(500);
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("085744444444");
        Thread.sleep(500);
        WebElement email = driver.findElement(By.name("userName"));
        email.sendKeys("aqtor@gmail.com");
        Thread.sleep(500);

        WebElement Address = driver.findElement(By.name("address1"));
        Address.sendKeys("wonopringgo");
        Thread.sleep(500);
        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys("pekalongan");
        Thread.sleep(500);
        WebElement state = driver.findElement(By.name("state"));
        state.sendKeys("jawa tengah");
        Thread.sleep(500);
        WebElement postalCode = driver.findElement(By.name("postalCode"));
        postalCode.sendKeys("511811");
        Thread.sleep(500);

        // //aktifkan ini jika mau beruntutan -
        // // non aktifkan code isi form country di paling atas jika pakai code ini
        // Select select = new Select(driver.findElement(By.name("country")));
        // select.selectByVisibleText("INDONESIA");
        // Thread.sleep(500);

        // WebElement selectedOption = select
        // .getFirstSelectedOption();
        // Assert.assertEquals(selectedOption.getText(),
        // selectedOption.getAttribute("value"));
        // Assert.assertEquals(selectedOption.getText(), "INDONESIA");
        // Assert.assertEquals(selectedOption.getAttribute("value"), "INDONESIA");

        WebElement userName = driver.findElement(By.id("email"));
        userName.sendKeys("nailul");
        Thread.sleep(500);
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password123");
        Thread.sleep(500);
        WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));
        confirmPassword.sendKeys("password123");
        Thread.sleep(500);

        WebElement buttonSubmit = driver.findElement(By.name("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonSubmit);
        buttonSubmit.click();

        Thread.sleep(5000);

        // // memaksa klik submit meskipun ada popuP
        // //aktifkan ini jika pakai code isi form country yang beraturan
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
        // buttonSubmit);

        String actual1 = driver.getCurrentUrl();
        String expected1 = "https://demo.guru99.com/test/newtours/register_sucess.php";
        Assert.assertEquals(actual1, expected1);

        // validate pesan registerasi berhasil
        String actual2 = driver.findElement(By.xpath("//b[contains(text(),' Dear ')]")).getText();
        String expected2 = "Dear nailul aqtor,";
        Assert.assertEquals(actual2, expected2);

        // validate pesan user name
        String actual3 = driver.findElement(By.xpath("//b[contains(text(),' Note: Your user name is ')]")).getText();
        String expected3 = "Note: Your user name is nailul.";
        Assert.assertEquals(actual3, expected3);

        Thread.sleep(2000);
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        // Tutup browser
        Thread.sleep(3000); // ==> menunggu selama 3 detik
        driver.quit();

    }
}
