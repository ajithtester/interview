package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			driver.manage().window().maximize();
			driver.get(url);
		} else if (isEqual(Browser, "Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
		} else {
			System.err.println("Please try browser : Chrome or Firefox ");
		}

	}

	public static WebElement getlocator(String locator) throws Throwable {

		String Locator = repository.getProperty(locator);

		String locatortype = Locator.split(" ")[0];
		String locatorvalue = Locator.split(" ")[1];

		WebElement webElement;
		switch (locatortype) {

		case "id":
			return  webElement = driver.findElement(By.id(locatorvalue));

		case "xpath":
			return  webElement = driver.findElement(By.xpath(locatorvalue));
		case "css":
			return  webElement = driver.findElement(By.cssSelector(locatorvalue));
		case "tag":
			return  webElement = driver.findElement(By.tagName(locatorvalue));
		case "partialText":
			return  webElement = driver.findElement(By.partialLinkText(locatorvalue));
		case "linkText":
			return  webElement = driver.findElement(By.linkText(locatorvalue));
		case "name":
			return  webElement = driver.findElement(By.name(locatorvalue));
		case "className":
			return  webElement = driver.findElement(By.className(locatorvalue));

		default:
			return  webElement = driver.findElement(By.xpath(locatorvalue));
		}
	}

	public static List<WebElement> getlocators(String locator) throws Throwable {

		String Locator = repository.getProperty(locator);

		String locatortype = Locator.split(" ")[0];
		String locatorvalue = Locator.split(" ")[1];

		List<WebElement> webElement;
		switch (locatortype) {

		case "id":
			return webElement = driver.findElements(By.id(locatorvalue));
		case "xpath":
			return webElement = driver.findElements(By.xpath(locatorvalue));
		case "css":

			return webElement = driver.findElements(By.cssSelector(locatorvalue));
		case "tag":
			return webElement = driver.findElements(By.tagName(locatorvalue));
		case "partialText":
			return webElement = driver.findElements(By.partialLinkText(locatorvalue));
		case "linkText":
			return webElement = driver.findElements(By.linkText(locatorvalue));
		case "name":
			return webElement = driver.findElements(By.name(locatorvalue));
		case "className":
			return webElement = driver.findElements(By.className(locatorvalue));
		default:
			return webElement = driver.findElements(By.xpath(locatorvalue));
		}
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

	public static boolean isDisplayed(String Locator) throws Throwable {
		return getwebElement(Locator).isDisplayed();
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
				printInConsole("Movie selected from the suggestion is: "+ movieName);
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

	public static void printInConsole(String value) throws Throwable {
		System.out.println(value);
	}

}
