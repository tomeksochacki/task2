package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task4Test {

    List<YTTile> ytTileList = new ArrayList<YTTile>();
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);

    @BeforeTest
    public void before() {
        webDriver.manage().window().fullscreen();
    }

    @Test
    public void task4Test() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriver.get("https://www.youtube.com/");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tp-yt-paper-button//yt-formatted-string[contains(text(), 'Accept all')]"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-icon-button/button[@aria-label='Close']")));
        webDriver.findElement(By.xpath("//yt-icon-button/button[@aria-label='Close']")).click();
        List<YTTile> expectedTilesList = new ArrayList<>();

        for (int i = 0; i < getRowTiles().size(); i++) {
            String title = "";
            String canal = "";
            String time = "";

            YTTile temp = getRowTiles().get(i);
            Character firstCharacter = temp.getDescription().charAt(0);
            if (!Character.isDigit(firstCharacter)) {
                temp.setTitle(title = temp.getDescription().split("\\n")[0]);
                temp.setChannel(canal = temp.getDescription().split("\\n")[1]);
                temp.setLength(time = "LIVE");

            } else {
                temp.setLength(time = temp.getDescription().split("\\n")[0]);
                temp.setTitle(title = temp.getDescription().split("\\n")[1]);
                temp.setChannel(canal = temp.getDescription().split("\\n")[2]);

            }

            expectedTilesList.add(temp);
            System.out.println(temp);

            if (i == 11) {
                break;
            }
        }

    }

    @AfterTest
    public void afterTest() {
        webDriver.quit();
    }

    public List<YTTile> getRowTiles() {
        List<YTTile> rowTiles = new ArrayList<YTTile>();
        WebElement table = webDriver.findElement(By.xpath("//ytd-rich-grid-renderer/div[@id='contents']"));
        List<WebElement> rowsList = table.findElements(By.xpath("//ytd-rich-grid-row/div[@id='contents']"));

        List<WebElement> tileList = null;

        for (WebElement row : rowsList) {
            tileList = row.findElements(By.xpath("//ytd-rich-grid-media[@class='style-scope ytd-rich-item-renderer']"));

            for (int i = 0; i < tileList.size(); i++) {
                WebElement column = tileList.get(i);
                YTTile ytTile = YTTile
                        .builder().description(column.getText()).build();

                rowTiles.add(ytTile);
            }

        }
        return rowTiles;
    }
}


////////////////////////////////////////////
/*


package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task4Test {

    List<YTTile> ytTileList = new ArrayList<YTTile>();
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);

    @BeforeTest
    public void before() {
        webDriver.manage().window().fullscreen();
    }

    @Test
    public void task4Test() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriver.get("https://www.youtube.com/");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tp-yt-paper-button//yt-formatted-string[contains(text(), 'Accept all')]"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-icon-button/button[@aria-label='Close']")));
        webDriver.findElement(By.xpath("//yt-icon-button/button[@aria-label='Close']")).click();
        List<YTTile> expectedTilesList = new ArrayList<>();

        for (int i = 0; i < getRowTiles().size(); i++) {
            String title = "";
            String canal = "";
            String time = "";

            YTTile temp = getRowTiles().get(i);
            Character firstCharacter = temp.getDescription().charAt(0);
            if (!Character.isDigit(firstCharacter)) {
                temp.setTitle(title = temp.getDescription().split("\\n")[0]);
                temp.setChannel(canal = temp.getDescription().split("\\n")[1]);
                temp.setLength(time = "LIVE");

            } else {
                temp.setLength(time = temp.getDescription().split("\\n")[0]);
                temp.setTitle(title = temp.getDescription().split("\\n")[1]);
                temp.setChannel(canal = temp.getDescription().split("\\n")[2]);

            }

            expectedTilesList.add(temp);
            System.out.println(temp);

            if (i == 11) {
                break;
            }
        }

    }

    @AfterTest
    public void afterTest() {
        webDriver.quit();
    }

    public List<YTTile> getRowTiles() {
        List<YTTile> rowTiles = new ArrayList<YTTile>();
        WebElement table = webDriver.findElement(By.xpath("//ytd-rich-grid-renderer/div[@id='contents']"));
        List<WebElement> rowsList = table.findElements(By.xpath("//ytd-rich-grid-row/div[@id='contents']"));

        List<WebElement> tileList = null;
        List<WebElement> durations = null;

        for (WebElement row : rowsList) {
            tileList = row.findElements(By.xpath("//ytd-rich-grid-media[@class='style-scope ytd-rich-item-renderer']"));
            durations = row.findElements(By.xpath("//span[@class='style-scope ytd-thumbnail-overlay-time-status-renderer']"));

            for (int i = 0; i < tileList.size(); i++) {
                WebElement duration;
                WebElement column = tileList.get(i);
                if (durations.get(i)==null){
                    duration = durations.get(i);
                    String time = "LIVE";
                    //duration = time;
                }
                YTTile ytTile = YTTile
                        .builder().description(column.getText())
                        //.length(duration.getText())
                        .build();

                rowTiles.add(ytTile);
            }

        }
        return rowTiles;
    }
}


*/
