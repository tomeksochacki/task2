package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.fail;


public class Task2Test {

    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    @BeforeTest
    public void before() {
        webDriver.manage().window().maximize();
    }

    @Test
    public void Task2Test() {
        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");

        if (isElementPresent(By.xpath("//li/a[@class='interlanguage-link-target']"))) {
            List<WebElement> languageList = webDriver.findElements(By.xpath("//li/a[@class='interlanguage-link-target']"));
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(languageList));

            for (WebElement language : languageList) {
                if (language.getText().equals("English")) {
                    System.out.println(language.getText() + " " + webDriver.findElement(By.cssSelector("[href='https://en.wikipedia.org/wiki/Wiki']")).getAttribute("href"));

                }
                System.out.println(language.getText());
            }
        } else {
            fail("The language list on the wikipedia site does not exist");
        }
    }

    @AfterTest
    public void afterTest() {
        webDriver.quit();
    }

    public boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
