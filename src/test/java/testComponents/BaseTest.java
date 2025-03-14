package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectsPackage.LandingPage;

public class BaseTest {
	// initialize driver
	public WebDriver driver;
	public LandingPage landingPage;
	// create landing page
	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//globalData.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			System.out.println("nothing for now");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.close();
		Thread.sleep(500);
	}

	public List<HashMap<String, String>> getJSonDataToMap(String filePath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// string to hashmap: required dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		File destination = new File(filePath);

		FileUtils.copyFile(screenshotAs, destination);
		return filePath; // Return the file path
	}

	public void manualTearDown() {
		driver.close();
	}

	public boolean expected_opened_webpage(String pageName) {
		switch (pageName) {
		case "login": {
			return driver.findElements(By.cssSelector("#login-button")).size()>0;
		}
		case "inventory": {
			return driver.findElements(By.cssSelector("#inventory_container")).size()>0;
		}
		case "cart": {
			return driver.findElements(By.cssSelector("#cart_contents_container")).size()>0;
		}
		case "product": {
			return driver.findElements(By.cssSelector("#inventory_item_container")).size()>0;
		}
		case "checkout": {
			return driver.findElements(By.cssSelector("#checkout_info_container")).size()>0;
		}
		case "overview": {
			return driver.findElements(By.cssSelector("#checkout_summary_container")).size()>0;
		}
		case "confirmation": {
			return driver.findElements(By.cssSelector("#checkout_complete_container")).size()>0;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + pageName);
		}
	}


}
