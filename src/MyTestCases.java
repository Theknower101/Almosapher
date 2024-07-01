import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTestCases {
WebDriver driver=new ChromeDriver();
String url="https://www.almosafer.com/en";
String expectedLanguage="en";
String expectedArabicLanguage="ar";
String expectedCurrency="SAR";
String expectedContactNumber="+966554400000";
boolean expectedLogoIsThere=true;
String expectedCheck="false";
Random rand=new Random();

@BeforeTest
public void setUp() {
	driver.get(url);
}
@Test(priority=1)
public void closeWindow() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__continue.btn.btn-primary")).click();
}
@Test(priority=2 , enabled=false)
public void checkTheLanguage() {
WebElement html=driver.findElement(By.tagName("html"));
String actualLanguage=html.getAttribute("lang");
Assert.assertEquals(actualLanguage, expectedLanguage);
}
@Test(priority=3 , enabled=false)
public void checkCurrency() {
	String actualCurrency=driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
	Assert.assertEquals(actualCurrency, expectedCurrency);
}
@Test(priority=4 ,enabled=false)
public void checkContactNumber() {
	String actualContactNumber=driver.findElement(By.tagName("strong")).getText();
	Assert.assertEquals(actualContactNumber, expectedContactNumber);
	
}
//@Test(priority=5)
//public void checkQitafLogo() {
//	WebElement footer=driver.findElement(By.tagName("footer"));
//	WebElement actualLogo=footer.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg"));
//	boolean isThereLogo=actualLogo.isDisplayed();
//	Assert.assertEquals(isThereLogo, expectedLogoIsThere);
//}
@Test(priority=6 , enabled=false)
public void checkHotelsTabsNotSelected() {
	String actualCheck=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
	Assert.assertEquals(actualCheck, expectedCheck);
}
@Test(priority=7,enabled=false)
public void checkDepartureDate() {
	LocalDate today=LocalDate.now();
	int expectedDepartureDay= today.plusDays(1).getDayOfMonth();
	int actualDepartureDay=Integer.parseInt(driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText());
	Assert.assertEquals(actualDepartureDay, expectedDepartureDay);
}
@Test(priority=8 , enabled=false)
public void checkReturnDate() { 
	LocalDate returnDate=LocalDate.now();
	int expectedrReturnDay=returnDate.plusDays(2).getDayOfMonth();
	int actualReturnDay=Integer.parseInt(driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText());
	Assert.assertEquals(expectedrReturnDay, actualReturnDay);
}
@Test(priority=9)
public void changeLanguageRandomly() {
	String[] languages= {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
	int randomIndex=rand.nextInt(languages.length);
	driver.get(languages[randomIndex]);
	if(driver.getCurrentUrl().contains("en")) {
	WebElement html=driver.findElement(By.tagName("html"));
	String actualLanguage=html.getAttribute("lang");
	Assert.assertEquals(actualLanguage, expectedLanguage);
	
	
	}
	else if(driver.getCurrentUrl().contains("ar")) {
		WebElement html=driver.findElement(By.tagName("html"));
		String actualLanguage=html.getAttribute("lang");
		Assert.assertEquals(actualLanguage, expectedArabicLanguage);
	}
	}
@Test(priority=10)
public void hotels() {
	WebElement hotelsTab=driver.findElement(By.cssSelector("body > div:nth-child(1) > section:nth-child(3) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > nav:nth-child(1) > a:nth-child(2) > div:nth-child(1)"));
	hotelsTab.click();
	WebElement hotelsSearch=driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
	WebElement html=driver.findElement(By.tagName("html"));
	String actualLanguage=html.getAttribute("lang");
     if(actualLanguage.equals("en")) {
    	 String []englishCities= {"Dubai","Jeddah","Riyadh"};
    	 int randomIndex=rand.nextInt(englishCities.length);
    	 hotelsSearch.sendKeys(englishCities[randomIndex]);
     }
     else if(actualLanguage.equals("ar")) {
    	 String []arabicCities= {"جدة","دبي","رياض"};
    	 int randomIndex=rand.nextInt(arabicCities.length);
    	 hotelsSearch.sendKeys(arabicCities[randomIndex]);
     }
} 
@Test(priority=11)
public void selectRoom() {
	WebElement element=driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
	Select mySelector=new Select(element);
	element.click();
	int randomIndex=rand.nextInt(2);
	mySelector.selectByIndex(randomIndex);
	WebElement button=driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
	button.click();
}
@Test(priority=12)
public void verifyLoading() {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofMinutes(1));
	WebElement resultTabs= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
	Assert.assertEquals(resultTabs.getText().contains("found")||resultTabs.getText().contains("وجدنا"), true);
}
@Test(priority=13)
public void verifyTheLowest() {
	WebElement lowestButton=driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__RECOMMENDATION']"));
	lowestButton.click();
	WebElement pricesContainer=driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
	List<WebElement>prices=driver.findElements(By.className("Price__Value"));
	int firstPrice=Integer.parseInt(prices.get(0).getText());
	int lastPrice=Integer.parseInt(prices.get(prices.size()-1).getText());
	Assert.assertEquals(lastPrice>firstPrice, true);
}
}
