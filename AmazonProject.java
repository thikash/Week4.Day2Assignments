package assignmentWeek4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonProject {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//search as oneplus 9 pro
		WebElement searchBox = driver.findElementByXPath("//input[@id='twotabsearchtextbox']");
		searchBox.sendKeys("iPhone 12 Pro Max");
		searchBox.sendKeys(Keys.ENTER);

		// Get the price of the first product
		String priceofthePhone = driver.findElementByXPath("(//span[@class='a-price-whole'])[2]").getText();
		System.out.println("The price of the first product is " + priceofthePhone);

		// print the number of customer ratings for the first displayed product
		String customerRatings = driver.findElementByXPath("(//span[@class='a-size-base'])[1]").getText();
		System.out.println("The number of ratings for first product is " + customerRatings);

		// Mouse hover on the stats
		Thread.sleep(3000);
		WebElement starRating = driver.findElementByXPath("(//span[@class='a-declarative'])[3]");
		Actions builder = new Actions(driver);
		builder.moveToElement(starRating).perform();
		System.out.println("Mouse Hovered on Star Rating");

		// Get the percentage of ratings for the 5 stars

		// String fiveStar = driver.findElementByXPath("").getText();
		// System.out.println("The Percentage of ratings for 5 star is " +fiveStar);

		// Click the first text link of the first image
		driver.findElementByXPath("(//a[@class='a-link-normal a-text-normal']/span)[1]").click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		int size = windowHandlesList.size();
		System.out.println("The Number of window is " + size);
		driver.switchTo().window(windowHandlesList.get(1));

		// Take a screen shot of the product displayed
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/Amazon.png");
		FileUtils.copyFile(src, dst);

		// click 'Add to Cart' button
		// Thread.sleep(2000);
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//input[@id='add-to-cart-button']").click();

		// Get the cart subtotal and verify if it is correct
		Thread.sleep(3000);
		WebElement viewCart = driver.findElementByXPath("//form[@id='attach-view-cart-button-form']/span");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", viewCart);
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@id='nav-cart-count-container']").click();
		String subTotal = driver.findElementByXPath("//span[@id='sc-subtotal-amount-activecart']").getText();
		System.out.println("The SubTotal of the iPhone: " + subTotal);

		if (priceofthePhone.equalsIgnoreCase(subTotal)) {
			System.out.println("The Cart subtotal is verified and its correct");
		} else {
			System.out.println("ERROR! Please check the PriceofthePhone and Cart subTotal");
		}
		driver.quit();
	}

}
