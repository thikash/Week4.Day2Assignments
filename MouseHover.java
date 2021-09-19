package assignmentWeek4.day2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHover {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement test = driver.findElementByXPath("//a[@class='btnMouse']");
		Actions builder = new Actions(driver);
		builder.moveToElement(test).perform();
		
		driver.findElementByXPath("//a[text()='WebServices']").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		builder.moveToElement(test).perform();
		
		List<WebElement> list = driver.findElementsByXPath("//a[@class='listener']");
		for (WebElement webElement : list) {
			String text = webElement.getText();
			System.out.println(text);
			
		}
		
		
	}

}
