import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyTestCases {
	WebDriver driver=new ChromeDriver();
	String url="https://global.almosafer.com/en";
	String expectedLanguage="en";
	String expectedCurrency="SAR";
	String expectedContactNumber="+966554400000";
	boolean expectedLogo=true;
	

@BeforeTest
public void mySetUp() {
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
}
@Test
public void checkLanguage() {
	WebElement html=driver.findElement(By.tagName("html"));
	String actualLanguage=html.getAttribute("lang");
	Assert.assertEquals(actualLanguage, expectedLanguage);
	
}
@Test
public void checkCurrency() {
	String actualCurrency=driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
	Assert.assertEquals(actualCurrency, expectedCurrency);
}
@Test
public void checkContactNumber() {
	String actualContactNumber=driver.findElement(By.tagName("strong")).getText();
	Assert.assertEquals(actualContactNumber, expectedContactNumber);
}
@Test
public void verifyLogo() {
	WebElement actualLogo=driver.findElement(By.xpath("//svg[@data-testid='Footer__ApplePayLogo']"));
	boolean isThere=actualLogo.isDisplayed();
	Assert.assertEquals(isThere, expectedLogo);
}
}
