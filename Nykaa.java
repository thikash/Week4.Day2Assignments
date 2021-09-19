package assignmentWeek4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// Load the URL nykaa
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//Mouseover on Brands and Mouseover on popular
		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		WebElement popular = driver.findElementByXPath("//a[text()='Popular']");
		builder.moveToElement(popular).perform();

		// Click L'oreal paris
		driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']/img").click();

		// Go to the newly opened window and check the title contains L'oreal Paris
		Set<String> windowHandleSet = driver.getWindowHandles();
		List<String> windowHandleList = new ArrayList<String>(windowHandleSet);
		driver.switchTo().window(windowHandleList.get(1));
		String title = driver.getTitle();
		System.out.println(title);
		int size = windowHandleList.size();
		System.out.println("Number of windows open:" + size);
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Same Page");
		} else {
			System.out.println("Not same page");
		}
		// click sort by and select customer top rated
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//span[@class='pull-left']").click();

		driver.findElementByXPath("(//div[@class='control control--radio text-capitalize'])[4]").click();

		// click Categtory and click shampoo
		driver.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElementByXPath("(//div[@class='pull-right filter-options-toggle'])[1]").click();
		driver.findElementByXPath("(//div[@class='category-wrap-top'])[1]/li").click();
		driver.findElementByXPath("(//div[@class='category-wrap-top'])[2]/li").click();

		driver.findElementByXPath("//input[@id='chk_Shampoo_undefined']/following-sibling::label").click();

		// check whether the Filter is applied with Shampoo
		String text = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']").getText();
		System.out.println("The filter is Applied:" + text);
		if (text.contains("Shampoo")) {
			System.out.println("The filter of shampoo is applied");
		} else {
			System.out.println("The filter of shampoo is not applied");
		}

		// Click on L'oreal Paris colour protect shampoo
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("(//span[contains(text(),'Paris Colour Protect Shampoo')])[3]").click();

		// Go to new window and select and select size as 175ml
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		int size1 = windowHandlesList1.size();
		System.out.println("The number of window is" + size1);
		driver.switchTo().window(windowHandlesList1.get(2));

		System.out.println(driver.getTitle());

		// Print the MRP of the product
		String mrp = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		String mrpPrize = mrp.replaceAll("[^0-9]", "");
		System.out.println("The MRP price of Loreal Paris colour protect shampoo is" + mrpPrize);

		// click on ADD to BAG
		driver.findElementByXPath("(//div[@class='pull-left'])[1]//button").click();

		// Go to Shopping Bag

		driver.findElementByXPath("//div[@class='AddBagIcon']").click();

		// Print the Grand Total amount
		Thread.sleep(3000);
		String grandTotalCart = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		String cartGrandTotal = grandTotalCart.replaceAll("[^0-9]", "");
		System.out.println("The Grand Total Shown in Shopping bag is" + cartGrandTotal);

		// click proceed
		WebElement proceedButton = driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", proceedButton);

		// click on continue as Guest
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//button[@class='btn full big']").click();

		// Check if this grand total is the same in step 15
		String grandtoCheckout = driver.findElementByXPath("(//div[@class='value'])[2]/span").getText();
		String CheckoutGrandTotal = grandtoCheckout.replaceAll("[^0-9]", "");
		System.out.println("The GrandTotal shown in checkout summary page is" + CheckoutGrandTotal);

		if (cartGrandTotal.equalsIgnoreCase(CheckoutGrandTotal)) {
			System.out.println("The GrandTotal in cart and checkout are same");
		} else {
			System.out.println("The GrandTotal in cart and checkout are not same");
		}

		driver.quit();
	}

}
