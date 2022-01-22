package org.veengu.alpha.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TeamMemberCreation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private JavascriptExecutor js;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testTeamMemberCreation() throws Exception {
    driver.get("https://demo.alpha.veengu.org/login");
    driver.findElement(By.id("input-username")).click();
    driver.findElement(By.id("input-username")).clear();
    driver.findElement(By.id("input-username")).sendKeys("veengu");
    driver.findElement(By.id("input-password")).click();
    driver.findElement(By.id("input-password")).clear();
    driver.findElement(By.id("input-password")).sendKeys("NQyqj2D5WMTAeYEc");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::span[2]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/main/div/div/div/a[14]")).click();
    driver.findElement(By.xpath("//*/text()[normalize-space(.)='Create']/parent::*")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("Test112");
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Test112");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test112@mail.com");
    driver.findElement(By.xpath("//p[@class='MuiFormHelperText-root MuiFormHelperText-contained MuiFormHelperText-filled Mui-focused MuiFormHelperText-marginDense']")).click();
    driver.findElement(By.xpath("//div[@id='menu-roleId']/div[3]/ul/li")).click();
    driver.findElement(By.xpath("//form/div[2]/button/span")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
