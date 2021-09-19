package assignmentWeek4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableJquery {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement frame = driver.findElementByXPath("//iframe[@class='demo-frame']");
		driver.switchTo().frame(frame);
		WebElement it3 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])[3]");
WebElement it7 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])[7]");
	Actions builder=new Actions(driver);
	builder.clickAndHold(it3).moveToElement(it7).release().perform();
	driver.switchTo().defaultContent();
	
	}

}
