package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;
import gov.dha.jmlfdc.logicole.ivv.utils.EnvironmentCleanUp;

public class HomePage extends BasePage  {

	private static Logger log = Logger.getLogger(HomePage.class.getName());
	private static final String test_url = "https://testa.www.logicole.jmlfdc.mil";
	private static final String pm_test_url = "https://pm-test.logicole.jmlfdc.mil";
	private static final String pm_dev_url = "https://pm-dev.logicole.jmlfdc.mil";
	private Appender fh = null;

	/*****************Web Object Element********************/
	private String okButtonEle = "okButton";
	private String loginButtonEle = "loginButton";
	private String logoutButtonEle = "logoutButton";
	private String userProfileEle = "changeUserProfile";
	private String logoutMessageEle = "body > div > div > div > div";
	private String abiSearchEle = "catalog";
	private String equipmentEle = "equipment";
	private String organizationEle = "changeAccess";
	private String userRoleAdminEle = "userAdminButton";
	private String dashBoardEle = "dashboard";
	private String equipmentRequestsEle = "equipmentRequestsStats";
	private String equipmentRecordsEle = "equipmentRecordsStats";
	private String expectedEnvEle = "main-content";
	/**************************** END **********************************/
	
	
	/***************** Expected String Text ********************/
	private String expectedLogOutText = "You have successfully logged out. Your connection has been terminated.";
	private String expectedEnvText = "This environment is connected to the following DMLSS Servers: Oak Harbor (JW8TEST285) and 42nd MDSS, Maxwell AFB (JW8TEST283)";
	private String expectedTitleText = "LogiCole";
	private String abiSearch = "ABi Search"; 
	private String equipmentRecord = "Equipment Record"; 
	private String equipmentRequest = "Equipment Request";
	private String userProfileManagement = "User Profile Management";
	/**************************** END **********************************/

	
	/**
	 * Method Name : <b>setUpEnv</b> <br>
	 * Description : This method will set up Env.</br>
	 * 
	 */
	public void setUpEnv() {
		log.info(String.format("setupEnv()"));
		
		EnvironmentCleanUp.envCleanup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	/**
	 * Method Name : <b>cleanUpEnv</b> <br>
	 * Description : This method will clean up Env.</br>
	 * 
	 */
	public void cleanUpEnv() {
		log.info(String.format("cleanUpEnv()"));
		
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	/**
	 * Method Name : <b>startAppliaction</b> <br>
	 * Description : This method will Start Application.</br>
	 * 
	 */
	public void startAppliaction(EnvironmentType environmentType) throws InterruptedException {
		log.info(String.format("Browse URL : " + getEnvironmentTypeURL(environmentType) + " login to the application"));

		try {
			Thread certSelectionThread = null;
			Runnable r = new Runnable() {

				public void run() {
					try {
						delayFor(5);
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_ENTER);
					} catch (AWTException e) {
						e.printStackTrace();
					}
				}
			};
			certSelectionThread = new Thread(r);
			certSelectionThread.start();
			driver.get(getEnvironmentTypeURL(environmentType));
			delayFor(10);
			click(By.id(okButtonEle));
			click(By.id(loginButtonEle));
			String title = driver.getTitle();
			assertTrue(title.equals(expectedTitleText));
			if (environmentType.toString().equals("test_env")){
				delayFor(5);
				String envText = readText(By.id(expectedEnvEle));
				assertTrue(envText.contains(expectedEnvText));
			}
			if (certSelectionThread != null) {
				try {
					certSelectionThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			String getTitle = driver.getTitle();
			log.info(String.format("Unable to open the website : " + getTitle));
			e.printStackTrace();
			throw new Error("Unable to open the website");
		}

	}

	public enum EnvironmentType{
		
		test_env,
		pm_test,
		pm_dev
		
	}
	
	private String getEnvironmentTypeURL(EnvironmentType environmentType){
		
		String tempURL = "";
		if (environmentType == EnvironmentType.test_env	){
			tempURL = test_url;
		}else if (environmentType == EnvironmentType.pm_test	){
			tempURL = pm_test_url;
		}else if (environmentType == EnvironmentType.pm_dev	){
			tempURL = pm_dev_url;
		}
		
		return tempURL;
	}
	
	/**
	 * Method Name : <b>logout</b> <br>
	 * Description : This method will logout Application.</br>
	 * 
	 */
	public void logout() {
		log.info(String.format("logout(): logout and verify the output message"));
		
		click(By.id(logoutButtonEle));
		delayFor(3);
		String logoutMessage = readText(By.cssSelector(logoutMessageEle));
		assertTrue(logoutMessage.equals(expectedLogOutText));
	}
	
	/**
	 * Method Name : <b>selectABiSearch</b> <br>
	 * Description : This method will select ABi Search.</br>
	 * 
	 */
	public void selectABiSearch(){
		log.info(String.format("selectABiSearch(%s)",abiSearch));
		
		clickAndSelectFromList(By.id(abiSearchEle), abiSearch);
	}
	
	/**
	 * Method Name : <b>selectDashboard</b> <br>
	 * Description : This method will select Dashboard.</br>
	 * 
	 */
	public void selectDashboard(){
		log.info(String.format("selectDashboard()"));
		
		click(By.id(dashBoardEle));
		assertTrue(driver.getTitle().equals(expectedTitleText));
	}
	
	/**
	 * Method Name : <b>selectEquipmentRequestsDashboard</b> <br>
	 * Description : This method will select equipment request Dashboard.</br>
	 * 
	 */
	public void selectEquipmentRequestsDashboard(){
		log.info(String.format("selectEquipmentRequestsDashboard()"));
		
		click(By.id(equipmentRequestsEle));
	}
	
	/**
	 * Method Name : <b>selectEquipmentRecordsDashboard</b> <br>
	 * Description : This method will select equipment records Dashboard.</br>
	 * 
	 */
	public void selectEquipmentRecordsDashboard(){
		log.info(String.format("selectEquipmentRecordsDashboard()"));
		
		click(By.id(equipmentRecordsEle));
	}
	
	/**
	 * Method Name : <b>selectEquipmentRecord</b> <br>
	 * Description : This method will select Equipment Record.</br>
	 * 
	 */
	public void selectEquipmentRecord(){
		log.info(String.format("selectEquipmentRecord(%s)", equipmentRecord));
		
		clickAndSelectFromList(By.id(equipmentEle), equipmentRecord);
	}
	
	/**
	 * Method Name : <b>selectEquipmentRequest</b> <br>
	 * Description : This method will select Equipment Request.</br>
	 * 
	 */
	public void selectEquipmentRequest(){
		log.info(String.format("selectEquipmentRequest(%s)", equipmentRequest));
		
		clickAndSelectFromList(By.id(equipmentEle), equipmentRequest);
	}
	
	/**
	 * Method Name : <b>selectUserProfileManagement</b> <br>
	 * Description : This method will select User ProfileManagement.</br>
	 * 
	 */
	public void selectUserProfileManagement(){
		log.info(String.format("selectUserProfileManagement(%s)", userProfileManagement));
		
		clickAndSelectFromList(By.id(userRoleAdminEle), userProfileManagement);
	}
	
	/**
	 * Method Name : <b>selectOrganization</b> <br>
	 * Description : This method will select Organization.</br>
	 * 
	 */
	public void selectOrganization(String organization){
		log.info(String.format("selectOrganization(%s)",organization));
		
		clickAndType(By.id(organizationEle), organization);
	}
	
	/**
	 * Method Name : <b>verifyHomePageHeader</b> <br>
	 * Description : This method will verify header.</br>
	 * 
	 */
	public void verifyHomePageHeader(String header){
		log.info(String.format("verifyHeader(%s)", header));
		
		delayFor(10);
		assertTrue(verifyHeader(header));
	}
	
	/**
	 * Method Name : <b>verifyHomePageTab</b> <br>
	 * Description : This method will verify tab value.</br>
	 * 
	 */
	public void verifyHomePageTab(String tab){
		log.info(String.format("verifyHomePageTab(%s)", tab));
		
		assertTrue(verifyTab(tab));
	}
	
	/**
	 * Method Name : <b>selectUserProfile</b> <br>
	 * Description : This method will select User Profile.</br>
	 * 
	 */
	public void selectUserProfile(String userProfile){
		log.info(String.format("selectUserProfile(%s)",userProfile));
		
		delayFor(3);
		clickAndType(By.id(userProfileEle), userProfile);
	}	

}
