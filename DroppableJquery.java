package assignmentWeek4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DroppableJquery {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement frame = driver.findElementByXPath("//iframe[@class='demo-frame']");
	driver.switchTo().frame(frame);
	WebElement drag = driver.findElementByXPath("//div[@id='draggable']");
	WebElement drop = driver.findElementByXPath("//div[@id='droppable']");
	Actions builder=new Actions(driver);
	builder.dragAndDrop(drag, drop).perform();
	driver.switchTo().defaultContent();
	}

}
