package gov.dha.jmlfdc.logicole.ivv.utils;


import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;

public class Util {

	private static Logger log = Logger.getLogger(Util.class.getName());


	protected WebDriver driver = null;
	private String m_Browser = null;
	private static String downloadPath = "C:\\Downloads\\";
	
	public Util (WebDriver driver){
		this.driver = driver;

	}

	public void selectElement(WebElement element, String itme){

		Assert.assertNotNull(element);

		Select elementComboBox = new  Select(element);

		List <WebElement> list = elementComboBox.getOptions();
		for (WebElement e : list){
			if(e.getText().contentEquals(itme)){
				e.click();
				break;
			}
		}
	}

	public void selectElementEx(WebElement element, String itme){

		Assert.assertNotNull(element);

		Select elementComboBox = new  Select(element);

		List <WebElement> list = elementComboBox.getOptions();
		for (WebElement e : list){
			if(e.getText().contains(itme)){
				e.click();
				break;
			}
		}
	}


	public void enterText(WebElement element, String value){


		Assert.assertNotNull(element);

		element.clear();
		element.sendKeys(value);
	}

	public enum CheckBox{
		CHECK,
		UNCHECK,
		DONOTHING}

	public void selectCheckBox(WebElement element, CheckBox value){

		if (value == CheckBox.CHECK	){
			if(!isChecked(element)){
				element.click();
			}
		}else if (value == CheckBox.UNCHECK	){
			if(isChecked(element)){
				element.click();
			}
		}else if (value == CheckBox.DONOTHING	){

		}
	}

	public void selectOptionButton(WebElement element, CheckBox value){

		if (value == CheckBox.CHECK	){
			if(!isChecked(element)){
				element.click();
			}
		}/*else if (value == CheckBox.UNCHECK	){
			if(isChecked(element)){
				element.click();
			}
		}*/

	}

	public boolean isChecked(WebElement element){

		boolean isChecked = false;

		isChecked = element.isSelected();

		return  isChecked;
	}



	public void hoverItem(WebElement element){

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();

	}

	public void hoverItemEx (WebElement element){

		Locatable hoverItem = (Locatable) element;
		Mouse mouse = ((HasInputDevices)driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());

	}

	/**
	 * 	Method Name   : <b>assertAdministrationPageExist</b>
	 * 	<br>Description : This method will assert if Users Page exist.</br>
	 * 
	 *  @author 
	 *  @return
	 *  @since  
	 */
	public void assertPageExist(WebElement element,String pageName){

		String isExist = "";

		if(element.isDisplayed()){		

			isExist = "Exist.";

		}else{
			isExist = "Does Not Exist.";
		}

		Assert.assertEquals(true, element.isDisplayed());

		//return pageName + " Page " + isExist;
		log.info(pageName + " Page " + isExist);
	}

	public void assertMessage(WebElement element, String expectedMessage){



		String actualMessage = element.getText();
		log.info("Expected Message : " + expectedMessage);
		log.info("Actual Message : " + actualMessage);


		Assert.assertEquals(actualMessage,expectedMessage);

	}

	public WebElement waitForElementDisplayed(final By locator,int timeToWaitInSec) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {

				WebElement element = null;

				driver.manage().timeouts().implicitlyWait(100,TimeUnit.MILLISECONDS);
				element = driver.findElement(locator);
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

				if (element != null && element.isDisplayed()) {
					highlight(element);
					return element;
				}
				return null;
			}
		});
		return foo;
	}

	public void highlight(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "border: 2px solid yellow;");
			delayFor(100);
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
			delayFor(100);
		}
	}

	public void delayFor(int miliSecond){

		try {
			Thread.sleep(miliSecond);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}


	public static String switchWindowByTitle(WebDriver driver, String titleToMatch) {
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String item : windows) {
			System.out.println(item);
			if (item.contentEquals(item)) {
				driver.switchTo().window(item);
				currentWindow = item;
				String title = driver.getTitle();
				if (title.contains(titleToMatch)) {
					break;
				}
			}
		}
		return currentWindow;
	}

	public void assertAlertText(String expectedMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String actualMessage = alert.getText();
			Assert.assertEquals( actualMessage , expectedMessage);	        
			alert.accept();
			driver.switchTo().parentFrame();
		} catch (Exception e) {
			//exception handling
		}
	}

	public void checkAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			//exception handling
		}
	}

	public String getBrowser() {
		return m_Browser;
	}

	public void setBrowser(String m_Browser) {
		this.m_Browser = m_Browser;
	}

	/*public String getBrowser(String browser){

		this.m_Browser = browser;
		//return browser;
	}

	public void setBrowser(){


	}*/

	public boolean elementIsPresent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickOverrideLink(WebDriver driver){

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean isPresent = driver.findElements(By.partialLinkText("Continue to this website")).size() > 0;

		if (isPresent){
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
		driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
	}

	public void verifyTitle(WebDriver driver, String expectedTitle){

		String title = driver.getTitle();
		Assert.assertEquals(title, expectedTitle);
	}

	public void jsClick(WebElement element){

		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click", element);
	}


	public void jsScrollElementIntoView(WebElement element){

		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click", element);

		((JavascriptExecutor)driver).executeScript("arguments[0].scroolIntoView(true, arg1);",element);
	}

	/**
	 * Method Name : <b>assertProperty</b>
	 * <br>Description : This method will verify Property.</br>
	 *
	 */
	public void assertProperty(WebElement element, String propertyName, String expectedValue){
		//log.info(String.format("verifyProperty(%s)", expectedValue));

		try {

			String actualValue =element.getAttribute(propertyName);

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);
		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not fould");
		}


	}

	/**
	 * Method Name : <b>assertValue</b>
	 * <br>Description : This method will verify Value of and Object.</br>
	 *
	 */
	public void assertValue(WebElement element,  String expectedValue){

		try {

			String actualValue =element.getAttribute("value");

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);
		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not fould");
		}
	}

	/**
	 * Method Name : <b>assertIsEnable</b>
	 * <br>Description : This method will verify Object is enable.</br>
	 *
	 */
	public void assertIsEnable(WebElement element,  Boolean expectedValue){
		
		try {

			Boolean actualValue = element.isEnabled();

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);

		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not found");
		}
	}

	/**
	 * Method Name : <b>assertIsVisible</b>
	 * <br>Description : This method will verify Object is enable.</br>
	 *
	 */
	public void assertIsVisible(WebElement element,  Boolean expectedValue){
		
		try {

			Boolean actualValue = element.isDisplayed();

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);

		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not found");
		}
	}
	
	
	/**
	 * Method Name : <b>assertElementExist</b>
	 * <br>Description : This method will verify Object is Exist.</br>
	 *
	 */
	public void assertElementExist(WebElement element,  Boolean expectedValue){
		
		try {
			
			Boolean actualValue = isElementPresent(element);

			//Boolean actualValue = element.isDisplayed();

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);

		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not found");
		}
	}
	
	public boolean isElementPresent(WebElement element){
       
		Boolean isPresent = false;
		
		try{
        	
        	//if ((attachmentDescriptionController ==null) || (attachmentDescriptionController !=null)){
        if ((element != null) || (((List<WebElement>) element).size()>0))
            //driver.findElement(element);
        		isPresent = true;
        }
        catch(NoSuchElementException e){
        	isPresent  = false;
        }
		return isPresent;
    }
	
	
	/**
	 * Method Name : <b>assertIsVisible</b>
	 * <br>Description : This method will verify Object is enable.</br>
	 *
	 */
	public void assertIsSelected(WebElement element,  Boolean expectedValue){
		
		try {

			//Boolean actualValue = element.isDisplayed();
			
			Boolean actualValue =isChecked(element);

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);

		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not found");
		}
	}
	
	/**
	 * Method Name : <b>assertText</b>
	 * <br>Description : This method will verify Text of and Object.</br>
	 *
	 */
	public void assertText(WebElement element,  String expectedValue){

		try {

			String actualValue =element.getText();

			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);
		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not fould");
		}
	}
	
	
	/**
	 * Method Name : <b>assertSelectedText</b>
	 * <br>Description : This method will verify Selected dropt down Text of an Object.</br>
	 *
	 */
	public void assertSelectedText(WebElement element,  String expectedValue){

		try {

			Select getDropDownList = new Select(element);			
			String actualValue =getDropDownList.getFirstSelectedOption().getText();
			
			log.info("Expected Message : " + expectedValue);
			log.info("Actual Message : " + actualValue);

			Assert.assertEquals(actualValue,expectedValue);
		} catch (Exception e) {
			// Assert.assertTrue(condition, message);
			//Assert.assertNull(element, "Element was not fould");
		}
	}
	
	/**
	 * Method Name : <b>getUploadDataFilePath</b>
	 * <br>Description : This method will Get Data file Path.</br>
	 */
	public String getUploadDataFilePath(){
		
		String filePath ="";
		
		try {
			 File directory = new File(".");
			 filePath = directory.getCanonicalPath() + "/src/main/resources/dataFiles/";
		} catch (Exception e) {
			
		}
		
		return filePath;
	}
	
	
	public Boolean isVisible(WebElement element){

		boolean isVisible = false;

		isVisible  =  element.isDisplayed();

		if (isVisible){
			isVisible = true;
		}else {
			isVisible = false;
		}

		return isVisible;


	}
	

	/**
	 * Method Name : <b>getDestinationFolderReportPath</b>
	 * <br>Description : This method will Get Path for Report XML file Destination.</br>
	 *
	 */
	public static String getDestinationFolderReportPath(){
		
		String filePath ="";
		
		try {
			 File directory = new File(".");
			 filePath = directory.getCanonicalPath() + "/target/allureXMLBackup/";
		} catch (Exception e) {
			
		}
		
		return filePath;
	}
	
	
	/**
	 * Method Name : <b>getSourceFolderReportPath</b>
	 * <br>Description : This method will Get Path for Report XML file Source.</br>
	 *
	 */
	public static String getSourceFolderReportPath(){
		
		String filePath ="";
		
		try {
			 File directory = new File(".");
			 filePath = directory.getCanonicalPath() + "/target/allure-results/";
		} catch (Exception e) {
			
		}
		
		return filePath;
	}
	
	/**
	 * Method Name : <b>getSourceFolderReportPath</b>
	 * <br>Description : This method will Ove all Allure Report XML file from Source to Destination .</br>
	 *
	 */
	public void moveAllureReportXMLFiles(){
		
		//log.info(String.format("moveAllureReportXMLFiles()"));
		
		File destinationFolder = new File(getDestinationFolderReportPath());
	    File sourceFolder = new File(getSourceFolderReportPath());

	    if (!destinationFolder.exists())
	    {
	        destinationFolder.mkdirs();
	    }

	    // Check weather source exists and it is folder.
	    if (sourceFolder.exists() && sourceFolder.isDirectory())
	    {
	        // Get list of the files and iterate over them
	        File[] listOfFiles = sourceFolder.listFiles();

	        if (listOfFiles != null)
	        {
	        	log.info(String.format("Backing Up Report XML Files"));
	        	log.info(String.format("Backedup XML file Path : " + getDestinationFolderReportPath()));
	            for (File child : listOfFiles )
	            {
	                // Move files to destination folder
	                child.renameTo(new File(destinationFolder + "\\" + child.getName()));
	                if (child.exists()){
	                	child.delete();
	                }
	            }

	            // Add if you want to delete the source folder 
	            //sourceFolder.delete();
	        }
	    }
	    else
	    {
	        System.out.println(sourceFolder + "  Folder does not exists");
	    }
	}
	
	
	public WebElement scrollToViewElement ( WebElement element){
		
		try {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {

		}
		
		return element;
 
	}
	
	public enum CalendarDate{
		
		FUTURE_DATE,
		CURRENT_DATE,
		PAST_DATE
		
	}
	public String getDate(CalendarDate date){
		
		String futureDate = "";
		int intMonth = 0 ;
		
		
		Calendar now = Calendar.getInstance();
		
		if (date== CalendarDate.FUTURE_DATE){
			
			now.add(Calendar.MONTH, 2);
			intMonth = (now.get(Calendar.MONTH) +1 );
			
		}else if  (date== CalendarDate.CURRENT_DATE){

			intMonth  = (now.get(Calendar.MONTH) + 1) ;
			
		}else if  (date== CalendarDate.PAST_DATE){

			now.add(Calendar.MONTH, -2);			
			intMonth = (now.get(Calendar.MONTH) +1 );
		}
		
		String strMonth = Integer.toString(intMonth);
		
		futureDate = strMonth  + "/" + now.get(Calendar.DATE) + "/" + now.get(Calendar.YEAR);
	    
	    return futureDate;
	}
	
	/**
	 * Method Name : <b>assertDropDownAllListItems</b>
	 * <br>Description : This method will verify All List items dropt down Text of an Object.</br>
	 *
	 */
	public void assertDropDownAllListItems(WebElement element,  String expectedValue){

		try {

			String tempList = "";
			
		
			Assert.assertNotNull(element);

			Select elementComboBox = new  Select(element);

			List <WebElement> list = elementComboBox.getOptions();
			for (WebElement e : list){
				tempList = tempList + e.getText() + " ";
			
			
				
			}
			
			String actualValue = tempList.trim();
			//String actualValue = tempList.substring(tempList.length(), 2);

			log.info(String.format("Expected List Items : " + expectedValue));
			log.info(String.format("Expected List Items : " + actualValue));
        	
			Assert.assertEquals(actualValue,expectedValue);
			
		} catch (Exception e) {

		}
	}
	
	
}


