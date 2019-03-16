package gov.dha.jmlfdc.logicole.ivv.utils;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.dha.jmlfdc.logicole.ivv.IResourceConstants;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage;


public class BasePage extends IResourceConstants {
	public static WebDriver driver;
	private long start, end;
	private static Logger log = Logger.getLogger(BasePage.class.getName());

	public void waitForElement(By elementLocation) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(elementLocation));

	}

	// Click Method
	public void click(By elementLocation) {
		delayFor(2);
		try {
			waitForElement(elementLocation);
			driver.findElement(elementLocation).click();
		} catch (Exception e) {
			System.out.println("Unable to click the element");
			e.printStackTrace();
		}
	}
	
	// check button  Method
	public void buttonDisabled(WebElement webElement) {
		delayFor(2);
		try {
			assertTrue(!webElement.isEnabled());
		} catch (Exception e) {
			System.out.println("Unable to verify diabled element");
			e.printStackTrace();
		}
	}
	
	// Click Method
	public void buttonEnabled(WebElement webElement) {
		delayFor(2);
		try {
			assertTrue(webElement.isEnabled());
		} catch (Exception e) {
			System.out.println("Unable to verify enable element");
			e.printStackTrace();
		}
	}

	// Write Text
	public void writeText(By elementLocation, String text) {
		delayFor(2);
		waitForElement(elementLocation);
		try {
			driver.findElement(elementLocation).clear();
			driver.findElement(elementLocation).sendKeys(text);
			driver.findElement(elementLocation).sendKeys(Keys.ENTER);
			delayFor(1);
		} catch (Exception e) {
			System.out.println("Unable to write the element");
			e.printStackTrace();
		}
	}

	public void delayFor(int second) {

		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Read Text
	public String readText(By elementLocation) {
		waitForElement(elementLocation);
		return driver.findElement(elementLocation).getText();

	}
	
	// Verify Text
	public void verifyText(By elementLocation, String nameSelection) {
		delayFor(1);
		waitForElement(elementLocation);
		assertTrue(readText(elementLocation).contains(nameSelection));

	}

	public boolean verifyHeader(String header) {
		delayFor(1);
		List<WebElement> elements = driver.findElements(By.tagName("h3"));
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getText().trim().equalsIgnoreCase(header)){
				return true;
			}
		}
		return false;

	}

	public boolean verifyTab(String tabString) {
		delayFor(1);
		List<WebElement> elements = driver.findElements(By.tagName("h4"));
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getText().trim().contains(tabString)){
				return true;
			}
		}
		return false;
	}
	
	public void clickAndSelectFromList(By elementLocation, String nameSelection) {
		delayFor(3);

		waitForElement(elementLocation);
		click(elementLocation);
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getText().equalsIgnoreCase(nameSelection)) {
				start = System.currentTimeMillis();
				elements.get(i).click();
				break;
			}
		}
		delayFor(3);
		if (elementLocation.equals(By.id("changeUserProfile"))) {
			assertTrue(readText(By.id("changeUserProfile")).equals(nameSelection));
		}else{
			assertTrue(readText(By.id("breadcrumb-wrapper")).contains(nameSelection));
		}
		end = System.currentTimeMillis();
		log.info("Time calcuate in milliseconds to click " + nameSelection + ": " + (end - start - 2000));
	}
	
	public void clickAndType(By elementLocation, String nameSelection) {
		delayFor(3);
		waitForElement(elementLocation);
		if(readText(elementLocation).contains(nameSelection)){
			delayFor(8);
		}else{
		click(elementLocation);
		delayFor(3);
		List<WebElement> inputBoxs = driver.findElements(By.tagName("input"));
		if (elementLocation.toString().contains("changeUserProfile")){
		inputBoxs.get(0).clear();
		inputBoxs.get(0).sendKeys(nameSelection);
		}else{
		inputBoxs.get(1).clear();
		inputBoxs.get(1).sendKeys(nameSelection);
		}
		List<WebElement> inputSelections = driver.findElements(By.tagName("a"));
		for (int i = 0; i < inputSelections.size(); i++) {
			if(inputSelections.get(i).getText().contains(nameSelection)){
				inputSelections.get(i).click();
				delayFor(8);
				break;
			}
		}
		}
	}
}