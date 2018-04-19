package org.smartsupply.web;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class LoginPageIT {
    static WebDriver driver;
    private String baseUrl = "http://localhost:9090/rms";
    private String logoutUrl = "http://localhost:9090/rms/ServiceLogout";

    @BeforeClass
    public static void setup() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void cleanUp() {
        driver.quit();
    }

    @Test
    public void should_load_login_page() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl);
        assertEquals("Username:", driver.findElement(By.id("usernameLabel")).getText());
    }


    @Test
    public void should_login_successfully() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:9090/rms");

        driver.findElement(By.id("j_username")).sendKeys("administrator");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("btnSubmitOnLoginInterface")).click();

        assertEquals("Student Search", driver.findElement(By.id("content-header")).getText());
        driver.get(logoutUrl);
    }
}
