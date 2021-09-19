package assignmentWeek4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortableJquery {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
driver.switchTo().frame(frame);
WebElement it1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
WebElement it5 = driver.findElement(By.xpath("(//li[@class='ui-state-default ui-sortable-handle'])[5]"));


Point location = it5.getLocation();
int x = location.getX();
int y = location.getY();
Actions builder = new Actions(driver);
builder.dragAndDropBy(it1,x,y).perform();




	}

}
