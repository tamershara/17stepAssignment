package com.qacart.todo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TodoTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
    }

    @Test
    public void should_User_Be_Able_To_Register()
    {
        driver.get("https://todo.qacart.com/signup");
        WebElement firstName = driver.findElement(By.cssSelector("[data-testid=first-name]"));
        firstName.sendKeys("Automation");
        WebElement lastName = driver.findElement(By.cssSelector("[data-testid=last-name]"));
        lastName.sendKeys("QA");
        WebElement email = driver.findElement(By.cssSelector("[data-testid=email]"));
        email.sendKeys("qa@qa.com");
        WebElement password = driver.findElement(By.cssSelector("[data-testid=password]"));
        password.sendKeys("qa");
        WebElement confirmPassword = driver.findElement(By.cssSelector("[data-testid=confirm-password]"));
        confirmPassword.sendKeys("qa");
        WebElement submitButton = driver.findElement(By.cssSelector("[data-testid=submit]"));
        submitButton.click();

    }
}
