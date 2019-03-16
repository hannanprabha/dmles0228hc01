package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;

public class EquipmentRequestFuzzPage extends BasePage {

	private static Logger log = Logger.getLogger(EquipmentRequestFuzzPage.class.getName());

	/***************** Web Object Element ********************/
	private String requInfoEle = "requestInfoForm";
	private String equTitleEle = "requestTitle";
	private String missionImpactEle = "missionImpact";
	private String reqDescEle = "requestDescription";
	private String customerFormEle = "customerForm";
	private String reqFirstNameEle = "requesterNameFirst";
	private String reqLastNameEle = "requesterNameLast";
	private String reqUesterPhoneEle = "requesterPhone";
	private String equInfoFormEle = "equipmentForm";
	private String equDescriptionEle = "equipmentDescription";
	private String unitCostEle = "unitCost";
	private String quantityEle = "quantity";
	

	/**************************** END **********************************/

	/***************** Expected String Text ********************/

	/**************************** END **********************************/

	/**
	 * Method Name : <b>fillRequestTitle</b> <br>
	 * Description : This method will test and fill request title
	 * .</br>
	 * 
	 */
	public void fillRequestTitle(String requestTitle) {
		log.info(String.format("fillRequestTitle(%s)", requestTitle));

		click(By.name(requInfoEle));
		writeText(By.id(equTitleEle), requestTitle);
		if (requestTitle.length() >= 120) {
			String actValue = "//*[@id='request']/div/form/ng-form[1]/div/div[2]/div/div[1]/div/div/dmles-text-input/span";
			String expValue = "120 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	
	/**
	 * Method Name : <b>fillMissionImpact</b> <br>
	 * Description : This method will test and fill mission impact
	 * .</br>
	 * 
	 */
	public void fillMissionImpact(String description) {
		log.info(String.format("fillMissionImpact(%s)", description));

		click(By.name(requInfoEle));
		writeText(By.id(missionImpactEle), description);
		if (description.length() >= 4000) {
			String actValue = "//*[@id='request']/div/form/ng-form[1]/div/div[2]/div/div[3]/div/div/dmles-text-area/span";
			String expValue = "4000 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}

	/**
	 * Method Name : <b>fillRequestDescription</b> <br>
	 * Description : This method will test and fill request description
	 * .</br>
	 * 
	 */
	public void fillRequestDescription(String description) {
		log.info(String.format("fillRequestDescription(%s)", description));

		click(By.name(requInfoEle));
		writeText(By.id(reqDescEle), description);
		if (description.length() >= 120) {
			String actValue = "//*[@id='request']/div/form/ng-form[1]/div/div[2]/div/div[4]/div/div/dmles-text-input/span";
			String expValue = "120 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	
	/**
	 * Method Name : <b>fillRequestDescription</b> <br>
	 * Description : This method will test and fill request first name
	 * .</br>
	 * 
	 */
	public void fillRequestFirstName(String firstName) {
		log.info(String.format("fillRequestFirstName(%s)", firstName));

		click(By.name(customerFormEle));
		writeText(By.id(reqFirstNameEle), firstName);
		if (firstName.length() >= 120) {
			String actValue = "//*[@id='request']/div/form/ng-form[2]/div/div[2]/div/div[2]/div[1]/div/dmles-text-input/span";
			String expValue = "120 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	
	/**
	 * Method Name : <b>fillRequestLastName</b> <br>
	 * Description : This method will test and fill request last name
	 * .</br>
	 * 
	 */
	public void fillRequestLastName(String lastName) {
		log.info(String.format("fillRequestLastName(%s)", lastName));

		click(By.name(customerFormEle));
		writeText(By.id(reqLastNameEle), lastName);
		if (lastName.length() >= 120) {
			String actValue = "//*[@id='request']/div/form/ng-form[2]/div/div[2]/div/div[2]/div[2]/div/dmles-text-input/span";
			String expValue = "120 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	
	/**
	 * Method Name : <b>fillRequestPhoneNumber</b> <br>
	 * Description : This method will test and fill request phone number
	 * .</br>
	 * 
	 */
	public void fillRequestPhoneNumber(String phoneNumber) {
		log.info(String.format("fillRequestPhoneNumber(%s)", phoneNumber));

		click(By.name(customerFormEle));
		writeText(By.id(reqUesterPhoneEle), phoneNumber);
		if (phoneNumber.length() >= 120) {
			String actValue = "//*[@id='request']/div/form/ng-form[2]/div/div[2]/div/div[3]/div[1]/div/dmles-text-input/span";
			String expValue = "120 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	
	 /** Method Name : <b>fillEquipmentDescription</b> <br>
	 * Description : This method will test and fill new equipment description
	 * .</br>
	 * 
	 */
	public void fillEquipmentDescription(String description) {
		log.info(String.format("fillEquipmentDescription(%s)", description));

		click(By.name(equInfoFormEle));
		writeText(By.id(equDescriptionEle), description);
		if (description.length() >= 4000) {
			String actValue = "//*[@id='equipmentIdNotProvidedFields']/div/div/div/dmles-text-area/span";
			String expValue = "4000 character limit. Characters left: 0";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	 
	 /** Method Name : <b>fillUnitCost</b> <br>
	 * Description : This method will test and fill unit cost
	 * .</br>
	 * 
	 */
	public void fillUnitCost(String unitCost) {
		log.info(String.format("fillUnitCost(%s)", unitCost));

		click(By.name(equInfoFormEle));
		writeText(By.id(unitCostEle), unitCost);
		if (unitCost.length() >= 10 || unitCost.contains("[a-zA-Z]+") == true) {
			String actValue = "//*[@id='request']/div/form/ng-form[3]/div/div[2]/div/div[4]/div[1]/div/dmles-currency-input/div/span[1]";
			String expValue = "Value must be a number between 0-999999999.99";
			assertTrue(expValue.equals(readText(By.xpath(actValue))));
		}
	}
	 
//	 /** Method Name : <b>fillRequestPhoneNumber</b> <br>
//	 * Description : This method will test and fill request phone number
//	 * .</br>
//	 * 
//	 */
//	public void fillQuanity(String quanity) {
//		log.info(String.format("fillRequestFirstName(%s)", quanity));
//
//		click(By.name(customerFormEle));
//		writeText(By.id(reqUesterPhoneEle), quanity);
//		if (quanity.length() >= 10 || quanity.contains("[a-zA-Z]+") == false) {
//			String actValue = "//*[@id='request']/div/form/ng-form[2]/div/div[2]/div/div[3]/div[1]/div/dmles-text-input/span";
//			String expValue = "Value must be a number between 0-999999999.99";
//			assertTrue(expValue.equals(readText(By.xpath(actValue))));
//		}
//	}


}

