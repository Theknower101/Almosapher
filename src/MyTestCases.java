import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyTestCases {
	WebDriver driver=new ChromeDriver();
@BeforeTest
public void mySetUp() {
	String url="https://global.almosafer.com/en";
	driver.get(url);
}
@Test
public void myTest() {
	
}
}
