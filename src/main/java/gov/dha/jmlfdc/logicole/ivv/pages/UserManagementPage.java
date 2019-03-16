package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;

public class UserManagementPage extends BasePage {
	private static Logger log = Logger.getLogger(UserManagementPage.class.getName());
	private List<String> species = new ArrayList<String>();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	/***************** Web Object Element ********************/
	private String createNewUserProfEle = "createNewUserProfileButton";
	private String createGroupUserProfEle = "createGroupUserProfileButton";
	private String createProfTitleEle = "//*[@id='main-content']/div/div/ui-view/ui-view/div/div/div/div[1]/div/div/span";
	private String profileLookupEle = "userLookupInput";
	private String profileNameEle = "profileName";
	private String profileExpirationDateEle = "profileExpirationDate";
	private String firstNameEle = "firstName";
	private String lastNameEle = "lastName";
	private String emailEle = "email";
	private String phoneEle = "phone";
	private String accessReasonEle = "reasonForAccess";
	private String nextButtonProfileEle = "//*[@id='main-content']/div/div/ui-view/ui-view/div/div/div/div[3]/div/div/button[2]";
	private String nextButtonAccessLvlEle = "//*[@id='main-content']/div/div/ui-view/ui-view/ui-view/div/div/div/div[3]/button[2]";
	private String nextButtonAssignRolesEle = "//*[@id='main-content']/div/div/ui-view/ui-view/ui-view/ui-view/div/div/form/div/div[3]/button[2]";
	private String sendInvitationEle = "confirmSubmit";
	private String activeInvitationsSearchEle = "activeInvitationsSearch";
	private String cancelInvitationButtonEle = "cancelInvitationButton";
	private String emailAcceptedButtonEle = "emailAcceptedButton";
	/**************************** END **********************************/

	/***************** Expected String Text ********************/
	private String createInvitation = "Create Invitation";
	private String createGroupInvitation = "Create Group Invitation";
	private String activeInvitationsTab = "Active Invitations";
	private String activeGroupInvitationsTab = "Active Group Invitations";
	private String userProfilesTab = "User Profiles";
	private String createcreateProfTitle = "Create Profile";
	private String profileNameTxt = null;
	private String profileExpirationDateTxt = "02/14/2020";
	private String firstName = "first" + randomName(5, "ALPHA");
	private String lastName = "last" + randomName(5, "ALPHA");
	private String phone = randomName(10, "NUM");
	private String accessReason = randomName(20, "ALPHA");

	/**************************** END **********************************/

	/**
	 * Method Name : <b>verifyTabValue</b> <br>
	 * Description : This method will test and verify user management
	 * Search.</br>
	 * 
	 */
	public void verifyButtonValue() {
		log.info(String.format("verifyTabValue(%s)", createInvitation + createGroupInvitation));

		delayFor(2);
		verifyText(By.id(createNewUserProfEle), createInvitation);
		verifyText(By.id(createGroupUserProfEle), createGroupInvitation);

	}

	/**
	 * Method Name : <b>verifyUserManagementTabTitles</b> <br>
	 * Description : This method will test and verify user management tab titles
	 * Search.</br>
	 * 
	 */
	public void verifyUserManagementTabTitles() {
		log.info(String.format("verifyUserManagementTabTitles(%s)",
				activeInvitationsTab + activeGroupInvitationsTab + userProfilesTab));

		delayFor(2);
		List<WebElement> elements = driver.findElements(By.tagName("span"));
		for (int i = 0; i < elements.size(); i++) {
			try {
				if (!elements.get(i).getText().isEmpty() && !elements.get(i).getText().equals(null)) {
					species.add(elements.get(i).getText());
				}
			} catch (Exception e) {
			}
		}
		assertTrue(species.contains(activeInvitationsTab));
		assertTrue(species.contains(activeGroupInvitationsTab));
		assertTrue(species.contains(userProfilesTab));
	}

	/**
	 * Method Name : <b>createInvitation</b> <br>
	 * Description : This method will click create invitation button
	 * Search.</br>
	 * 
	 */
	public void createInvitation() {
		log.info(String.format("createInvitation()"));

		delayFor(2);
		click(By.id(createNewUserProfEle));
		verifyText(By.xpath(createProfTitleEle), createcreateProfTitle);
	}

	/**
	 * Method Name : <b>verifyProfileLookup</b> <br>
	 * Description : This method will verify Profile Lookup Search.</br>
	 * 
	 */
	public void verifyProfileLookup(String emailAddress) {
		log.info(String.format("verifyProfileLookup(%s)", emailAddress));

		delayFor(2);
		writeText(By.id(profileLookupEle), emailAddress);
		verifyText(By.id("main-content"), emailAddress);
	}

	/**
	 * Method Name : <b>verifyProfileLookup</b> <br>
	 * Description : This method will verify Profile Lookup Search.</br>
	 * 
	 */
	public void verifyInvalidProfileLookup(String emailAddress) {
		log.info(String.format("verifyInvalidProfileLookup(%s)", emailAddress));

		delayFor(2);
		writeText(By.id(profileLookupEle), emailAddress);
		verifyText(By.id("main-content"), "There are no profiles found using the email provided.");
	}

	/**
	 * Method Name : <b>verifyProfileLookup</b> <br>
	 * Description : This method will verify Profile Lookup Search.</br>
	 * 
	 */
	public void filloutIdentificationInfo(String profileName) {
		log.info(String.format("filloutIdentificationInfo(%s)", profileName));

		profileNameTxt = profileName;
		writeText(By.id(profileNameEle), profileNameTxt);
		writeText(By.id(profileExpirationDateEle), profileExpirationDateTxt);
		writeText(By.id(firstNameEle), firstName);
		writeText(By.id(lastNameEle), lastName);
		writeText(By.id(emailEle), "zhen.x.kuang.ctr@mail.mil");
		writeText(By.id(phoneEle), phone);
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.id(accessReasonEle)));
		writeText(By.id(accessReasonEle), accessReason);
		click(By.xpath(nextButtonProfileEle));
		delayFor(2);
		List<WebElement> items = driver.findElements(By.tagName("select"));
		for (int i = 0; i < items.size(); i++) {
			Select dropdown = new Select(items.get(i));
			dropdown.selectByVisibleText("Root");
		}
	}

	/**
	 * Method Name : <b>selectAccessLevel</b> <br>
	 * Description : This method will select access level Search.</br>
	 * 
	 */
	public void selectAccessLevel(String accessLevel, String orginazation) {
		log.info(String.format("selectAccessLevel(%s)", accessLevel + " and " + orginazation));

		delayFor(2);
		List<WebElement> items = driver.findElements(By.tagName("select"));
		for (int i = 0; i < items.size(); i++) {
			Select dropdown = new Select(items.get(i));
			dropdown.selectByVisibleText(accessLevel);
		}

		List<WebElement> items1 = driver.findElements(By.tagName("span"));
		for (int i = 0; i < items1.size(); i++) {
			try {
				if (items1.get(i).getText().equalsIgnoreCase(orginazation))
					items1.get(i).click();
			} catch (Exception e) {
			}
		}
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath(nextButtonAccessLvlEle)));
		click(By.xpath(nextButtonAccessLvlEle));

		delayFor(2);
		String check = readText(By.xpath("//*[@id='main-content']/div/div/ui-view/ui-view/ui-view/ui-view/div/div/form/div/div[2]/div[1]"));
		assertTrue(check.contains(firstName));
		assertTrue(check.contains(lastName));
		assertTrue(check.contains(profileNameTxt));
	}

	/**
	 * Method Name : <b>selectRoleAccess</b> <br>
	 * Description : This method will select access allow.</br>
	 * 
	 */
	public void selectRoleAccess(String allowAccess) {
		log.info(String.format("selectRoleAccess(%s)", allowAccess));
		
		List<WebElement> allowAccessOptions = driver.findElements(By.tagName("input"));
		try {
			for (int i = 2; i < allowAccessOptions.size(); i++) {
				String checkpoint = "category" + allowAccess + "Allow";
				if (allowAccessOptions.get(i).getAttribute("id").equals(checkpoint)) {
					js.executeScript("arguments[0].scrollIntoView(true)", allowAccessOptions.get(i));
					allowAccessOptions.get(i).click();
					break;
				}

			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * Method Name : <b>invitationConfirmation</b> <br>
	 * Description : This method will confirm the invitation.</br>
	 * 
	 */
	public void invitationConfirmation() {
		log.info(String.format("invitationConfirmation()"));

		delayFor(3);
		click(By.xpath(nextButtonAssignRolesEle));
		String check = readText(By.id("main-content"));
		assertTrue(check.contains(firstName));
		assertTrue(check.contains(lastName));
		assertTrue(check.contains(profileNameTxt));
		click(By.id(sendInvitationEle));
	}
	
	/**
	 * Method Name : <b>verifyCancelInvitation</b> <br>
	 * Description : This method will verify and cancel the invitation.</br>
	 * 
	 */
	public void verifyCancelInvitation() {
		log.info(String.format("verifyCancelInvitation()"));

		delayFor(2);
		writeText(By.name(activeInvitationsSearchEle), profileNameTxt);
		List<WebElement> activeInvitationOptions = driver.findElements(By.tagName("tr"));
		try {
			for (int i = 0; i < activeInvitationOptions.size(); i++) {
				if (activeInvitationOptions.get(i).getText().contains(profileNameTxt)) {
					activeInvitationOptions.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
		}
		
		delayFor(2);
		String check1 = readText(By.id("main-content"));
		assertTrue(check1.contains(firstName));
		assertTrue(check1.contains(lastName));
		assertTrue(check1.contains(profileNameTxt));
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.id(cancelInvitationButtonEle)));
		click(By.id(cancelInvitationButtonEle));
		delayFor(1);
		writeText(By.id("modalComment"), "test");
		click(By.id("confirmButton"));
	}
}
