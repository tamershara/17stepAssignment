package com.qacart.todo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://todo.qacart.com/signup");
        WebElement firstName = driver.findElement(By.cssSelector("[data-testid=first-name]"));
        firstName.sendKeys("Automation");
        WebElement lastName = driver.findElement(By.cssSelector("[data-testid=last-name]"));
        lastName.sendKeys("QA");
        WebElement email = driver.findElement(By.cssSelector("[data-testid=email]"));
        long timeStamp = Instant.now().getEpochSecond();
        String emailAddress = "Automation" + timeStamp + "@qacart.com";
        email.sendKeys(emailAddress);
        WebElement password = driver.findElement(By.cssSelector("[data-testid=password]"));
        password.sendKeys("qa123456");
        WebElement confirmPassword = driver.findElement(By.cssSelector("[data-testid=confirm-password]"));
        confirmPassword.sendKeys("qa123456");
        WebElement submitButton = driver.findElement(By.cssSelector("[data-testid=submit]"));
        submitButton.click();
        // For new users they always have to see no todos
        WebElement noTodoMessage = driver.findElement(By.cssSelector("[data-testid=no-todos]"));
        Assert.assertTrue(noTodoMessage.isDisplayed());
    }

    @Test
    public void user_Should_Be_Able_To_Add_TODO()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //login
        driver.get("https://todo.qacart.com");
        WebElement email = driver.findElement(By.cssSelector("[data-testid=email]"));
        email.sendKeys("automationuser@example.com");
        WebElement password = driver.findElement(By.cssSelector("[data-testid=password]"));
        password.sendKeys("12345678a");
        WebElement submitButton = driver.findElement(By.cssSelector("[data-testid=submit]"));
        submitButton.click();

        //Add todos
        WebElement addButton = driver.findElement(By.cssSelector("[data-testid=add]"));
        addButton.click();
        WebElement newTodoText = driver.findElement(By.cssSelector("[data-testid=new-todo]"));
        newTodoText.sendKeys("Learn Automation");
        WebElement submitAddButton = driver.findElement(By.cssSelector("[data-testid=submit-newTask]"));
        submitAddButton.click();

        //Assertion to check if the new todos added
        List<WebElement> tasks = driver.findElements(By.cssSelector("[data-testid=todo-item]"));
        Assert.assertTrue(tasks.get(0).isDisplayed());
    }

    @Test
    public void user_Should_Be_Able_To_Delete_Todo() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //login
        driver.get("https://todo.qacart.com");
        WebElement email = driver.findElement(By.cssSelector("[data-testid=email]"));
        email.sendKeys("automationuser@example.com");
        WebElement password = driver.findElement(By.cssSelector("[data-testid=password]"));
        password.sendKeys("12345678a");
        WebElement submitButton = driver.findElement(By.cssSelector("[data-testid=submit]"));
        submitButton.click();

        //Add todos
        WebElement addButton = driver.findElement(By.cssSelector("[data-testid=add]"));
        addButton.click();
        WebElement newTodoText = driver.findElement(By.cssSelector("[data-testid=new-todo]"));
        newTodoText.sendKeys("Learn Automation");
        WebElement submitAddButton = driver.findElement(By.cssSelector("[data-testid=submit-newTask]"));
        submitAddButton.click();
        List<WebElement> tasks = driver.findElements(By.cssSelector("[data-testid=todo-item]"));
        int count = tasks.size();
        System.out.println(count);
        //Delete todos
        WebElement deleteButton = driver.findElement(By.cssSelector("[data-testid=delete]"));
        deleteButton.click();
        if(count == 1)
        {
            WebElement noTodoMessage = driver.findElement(By.cssSelector("[data-testid=no-todos]"));
            Assert.assertTrue(noTodoMessage.isDisplayed());
        }else {
            driver.navigate().refresh();
            List<WebElement> tasksAfterDelete = driver.findElements(By.cssSelector("[data-testid=todo-item]"));
            int expectedCount = tasksAfterDelete.size();
            Assert.assertEquals(expectedCount , count - 1);
        }
    }
}
