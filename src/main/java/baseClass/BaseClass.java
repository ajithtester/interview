package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties repository = new Properties();
	public static File file;
	public static FileInputStream fileInput;
	public static WebDriver driver;

	public static String wikiPage = "./src/test/resources/Properties/imdbPageLocators.Properties";

	public static String imdbPage = "./src/test/resources/Properties/wikiPageLocators.Properties";

	public static String browserProperties = "./src/test/resources/Properties/App.Properties";

	public static void loadpropertiesFile() throws Throwable {

		file = new File(wikiPage);
		fileInput = new FileInputStream(file);
		repository.load(fileInput);

		file = new File(imdbPage);
		fileInput = new FileInputStream(file);
		repository.load(fileInput);

		file = new File(browserProperties);
		fileInput = new FileInputStream(file);
		repository.load(fileInput);

	}

	public static void openBrowserAndLaunchURL(String browser, String URL) throws Throwable {

		String Browser = repository.getProperty(browser);
		String url = repository.getProperty(URL);

		if (isEqual(Browser, "Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			printInConsole(Browser + " browser is launched");
			driver.manage().window().maximize();
			driver.get(url);
			printInConsole("Navigated to the URL: " + url);

		} else if (isEqual(Browser, "Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			printInConsole(Browser + " browser is launched");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			printInConsole("Navigated to the URL: " + url);

		} else {
			printErrorInConsole("Please try browser : Chrome or Firefox ");
		}
	}
	
	public static void endBrowser() throws Throwable {
		
		driver.quit();
	}

	public static WebElement getlocator(String locator) throws Throwable {

		String Locator = repository.getProperty(locator);

		String locatortype = Locator.split(" ")[0];
		String locatorvalue = Locator.split(" ")[1];

		if (locatortype.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorvalue));
		else if (locatortype.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorvalue));
		else if (locatortype.toLowerCase().equals("classname") || (locatortype.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorvalue));
		else if (locatortype.toLowerCase().equals("tagname") || (locatortype.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorvalue));
		else if (locatortype.toLowerCase().equals("linktext") || (locatortype.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorvalue));
		else if (locatortype.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorvalue));
		else if ((locatortype.toLowerCase().equals("cssselector")) || (locatortype.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorvalue));
		else if (locatortype.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorvalue));
		else
			throw new Exception("Unknown locator type '" + locatortype + "'");

	}

	public static List<WebElement> getlocators(String locator) throws Throwable {

		String Locator = repository.getProperty(locator);

		String locatortype = Locator.split(" ")[0];
		String locatorvalue = Locator.split(" ")[1];

		if (locatortype.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorvalue));
		else if (locatortype.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorvalue));
		else if (locatortype.toLowerCase().equals("classname") || (locatortype.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorvalue));
		else if (locatortype.toLowerCase().equals("tagname") || (locatortype.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorvalue));
		else if (locatortype.toLowerCase().equals("linktext") || (locatortype.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorvalue));
		else if (locatortype.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorvalue));
		else if ((locatortype.toLowerCase().equals("cssselector")) || (locatortype.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorvalue));
		else if (locatortype.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorvalue));
		else
			throw new Exception("Unknown locator type '" + locatortype + "'");
	}

	public static WebElement getwebElement(String Locator) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 90);
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(getlocator(Locator)));
		return webElement;
	}

	public static void click(String Locator) throws Throwable {
		getwebElement(Locator).click();
	}

	public static void typeDataInTheElement(String Locator, String locatorvalue) throws Throwable {
		getwebElement(Locator).sendKeys(locatorvalue);
	}

	public static String getText(String Locator) throws Throwable {
		return getwebElement(Locator).getText();
	}

	public static boolean isEqual(String ActualValue, String ExpectedValue) throws Throwable {

		return ActualValue.equalsIgnoreCase(ExpectedValue);
	}

	public static List<WebElement> findElementandClick(String Locator, String value) throws Throwable {

		List<WebElement> Webelements = getlocators(Locator);

		for (WebElement Key : Webelements) {
			String movieName = Key.getText();
			if (Key.getText().contains(value)) {
				Key.click();
				printInConsole("Movie selected from the suggestion is: " + movieName);
				break;
			}
		}
		return Webelements;
	}

	public static void scrollToTheElement(String Locator) throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = getwebElement(Locator);
		js.executeScript("arguments[0].scrollIntoView();", Element);

	}

	public static void assertValues(String ActualValue, String ExpectedValue, String ErrorMessage) throws Throwable {
		Assert.assertEquals(ActualValue, ExpectedValue, ErrorMessage);
	}

	public static void isDisplayed(String Locator, String ErrorMessage) throws Throwable {
		boolean status = getwebElement(Locator).isDisplayed();
		Assert.assertTrue(status, ErrorMessage);
	}

	public static void printInConsole(String value) throws Throwable {
		System.out.println(value);
	}
	
	public static void printErrorInConsole(String value) throws Throwable {
		System.err.println(value);
	}

}
