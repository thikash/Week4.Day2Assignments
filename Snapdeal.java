package assignmentWeek4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// go to mens fashion
		WebElement men = driver.findElementByXPath("(//span[@class='catText'])[6]");
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();

		// go to sports shoes
		driver.findElementByXPath("(//span[text()='Sports Shoes'])[1]").click();

		// get the count of the sports shoes
		String numberofShoes = driver.findElementByXPath("//span[@class='category-count']").getText();
		String sportShoe = numberofShoes.replaceAll("[^0-9]", "");
		System.out.println("Total Number of shoes: " + sportShoe);

		// Click Tranining shoes
		driver.findElementByXPath("(//div[@class='child-cat-name '])[2]").click();

//sort by low to high
		driver.findElementByXPath("//div[@class='sorting-sec animBounce']").click();

		driver.findElementByXPath("(//li[@data-index='1'])[2]").click();
		Thread.sleep(3000);

		// check if the items displayed are sorted correctly
		List<WebElement> przList = driver.findElementsByXPath("//span[@class='lfloat product-price']");
		List<Integer> val = new ArrayList<Integer>();
		for (int i = 0; i < przList.size(); i++) {
			String prize = przList.get(i).getText().replaceAll("[^0-9]", "");
			System.out.println(prize);
			val.add(Integer.parseInt(prize));

		}
		List<Integer> sortlist = new ArrayList<Integer>(val);
		Collections.sort(sortlist);
		for (int i = 0; i < val.size(); i++) {
			if (val.get(i) == sortlist.get(i)) {
				System.out.println("Items are sorted correctly");
			} else {
				System.out.println("items are not sorted");
			}
		}

		// Mouse Hove on VSS Navy Training Shoes
		WebElement productimg = driver.findElementByXPath("//img[@class='product-image wooble' and @title='VSS Navy Training Shoes']");
		builder.moveToElement(productimg).perform();

		// click QuickView button
		driver.findElementByXPath("(//div[@class='center quick-view-bar  btn btn-theme-secondary  ' ])[3]").click();

		// print the cost and the discount percentage
		String shoeCost = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("The cost of Asian White Running Sport Shoes is " + shoeCost);
	Thread.sleep(3000);
		 String shoeDiscount = driver.findElementByXPath("//span[@class='percent-desc ']").getText();
		System.out.println("The Discount cost of Asian White Running Sport Shoes is "+ shoeDiscount);
		Thread.sleep(3000);
			


		// Take the snapshot of the shoes
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snapdealAsianShoe.png");
		FileUtils.copyFile(src1, dst);

		// close the current window Quick view opens as modal popup not as window
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		driver.quit();
	}

}
