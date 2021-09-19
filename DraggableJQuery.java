package assignmentWeek4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DraggableJQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver=new ChromeDriver();
driver.get("https://jqueryui.com/draggable/");
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
WebElement frame = driver.findElementByXPath("//iframe[@class='demo-frame']");
driver.switchTo().frame(frame);
WebElement drag = driver.findElementByXPath("//div[@id='draggable']");
	Actions builder=new Actions(driver);
	builder.dragAndDropBy(drag, 100, 100).perform();
	driver.switchTo().defaultContent();
	driver.close();
	}

}
