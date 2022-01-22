package org.veengu.alpha.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class IndividualOnboardingTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testIndividualOnboarding() throws Exception {
        wd.get("https://qa.alpha.veengu.org/login");
        wd.findElement(By.id("input-username")).clear();
        wd.findElement(By.id("input-username")).sendKeys("veengu");
        wd.findElement(By.id("input-password")).clear();
        wd.findElement(By.id("input-password")).sendKeys("123456");
        wd.findElement(By.xpath("//div[@id='root']/div/div/div/form")).click();
        wd.findElement(By.xpath("//div[@id='root']/div/div/div/form/button/span")).click();
        wd.findElement(By.xpath("//div[@id='root']/div/div/div/main/div/div/div/a[7]")).click();
        wd.findElement(By.xpath("//*/text()[normalize-space(.)='Create']/parent::*")).click();
        wd.findElement(By.xpath("//body")).click();
        wd.findElement(By.xpath("//div[@id='menu-solutionId']/div[3]/ul/li")).click();
        wd.findElement(By.cssSelector("button.MuiButtonBase-root.MuiIconButton-root.MuiAutocomplete-popupIndicator > span.MuiIconButton-label > svg.MuiSvgIcon-root")).click();
        wd.findElement(By.id("country-select-demo-option-0")).click();
        wd.findElement(By.id("firstName")).click();
        wd.findElement(By.id("firstName")).clear();
        wd.findElement(By.id("firstName")).sendKeys("Tom");
        wd.findElement(By.id("lastName")).clear();
        wd.findElement(By.id("lastName")).sendKeys("Soyer");
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='​'])[4]/following::input[1]")).clear();
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='​'])[4]/following::input[1]")).sendKeys("+27792354568");
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='​'])[5]/following::span[1]")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
