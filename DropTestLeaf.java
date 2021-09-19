package assignmentWeek4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropTestLeaf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
ChromeDriver driver = new ChromeDriver();
driver.get("http://www.leafground.com/pages/drop.html");
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement drag = driver.findElementByXPath("//div[@id='draggable']");
WebElement drop = driver.findElementByXPath("//div[@id='droppable']");
Actions builder = new Actions(driver);
builder.dragAndDrop(drag, drop).perform();
String text = driver.findElementByXPath("//p[text()='Dropped!']").getText();
System.out.println(text);
if(text.equalsIgnoreCase("Dropped!")) {
	System.out.println("source is dropped inside target");
}
else {
	System.out.println("source is not dropped inside target");
}
	}

}
