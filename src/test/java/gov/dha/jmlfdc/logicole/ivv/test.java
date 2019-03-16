package gov.dha.jmlfdc.logicole.ivv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import gov.dha.jmlfdc.logicole.ivv.IResourceConstants;
import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage;

/**
 * Handle Untrusted HTTPs Connections With Selenium web driver. Also, handle an
 * additional wizards like, 'Add Security Exception' by Confirm Security
 * Exception
 *
 * 
 *
 */
public class test {
	private static Logger logging = Logger.getLogger(test.class.getName());
	private static final String URL = "https://pm-test.www.logicole.jmlfdc.mil/#/dmles/security"
			+ "";
	
	
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			boolean append = true;
			FileHandler handler = new FileHandler("default.log", append);
			logging.addHandler(handler);

		} catch (SecurityException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		WebDriver driver = null;
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\Fayaz\\Downloads\\chromedriver.exe");

		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
		// true);
//		DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();  
//				handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
				
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-extensions");
		options.setExperimentalOption("useAutomationExtension", false);
		// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
//		driver = new ChromeDriver (handlSSLErr);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread certSelectionThread = null;
		Runnable r = new Runnable() {

			public void run() {

				try {
					Thread.sleep(1000 * 5);
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_ENTER);
				} catch (AWTException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		certSelectionThread = new Thread(r);
		certSelectionThread.start();
		driver.get(URL);
		try {
			Thread.sleep(1000 * 10);
			driver.findElement(By.cssSelector("#okButton")).click();
			Thread.sleep(1000 * 5);
			driver.findElement(By.cssSelector("#loginButton")).click();
			String title = driver.getTitle();
			logging.warning("warning message");
			System.out.println("******" + title);
			Thread.sleep(1000 * 5);
			driver.findElement(By.id("changeUserProfile")).click();
			Thread.sleep(1000 * 5);
//			List<WebElement> elements = driver.findElements(By.tagName("a"));
//			for (int i = 0; i < elements.size(); i++) {
//				System.out.println("*********" + elements.get(i).getText());
//				if (elements.get(i).getText().equalsIgnoreCase(Abi_Management_Role)) {
//					elements.get(i).click();
//					break;
//				}
//			}
			driver.findElement(By.id("catalog")).click();
			driver.findElement(By.id("abi")).click();
			;
			Thread.sleep(1000 * 5);
			driver.findElement(By.id("searchInput")).click();
			;
			driver.findElement(By.id("searchInput")).sendKeys("Syringe");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (certSelectionThread != null) {
			try {
				certSelectionThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}