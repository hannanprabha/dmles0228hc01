package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;

public class EquipmentRequestPage extends BasePage {

	private static Logger log = Logger.getLogger(EquipmentRequestPage.class.getName());
	private List<String> species = new ArrayList<String>();
	
	/***************** Web Object Element ********************/
	private String createNewReqEle = "createNewRequestButton";
	private String myReqEle = "filterSubmittedByMeButton";
	private String myOrgEle = "filterSubmittedByMyOrgButton";
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
	private String createNewReq = "Create a Request";
	private String myReq = "My Requests";
	private String myOrg = "My Organization's Requests";
	private String activeRequests = "Active Requests";
	private String historicalRequests = "Historical Requests";

	/**************************** END **********************************/

	/**
	 * Method Name : <b>verifyTabValue</b> <br>
	 * Description : This method will test and verify Equipment Request
	 * Search.</br>
	 * 
	 */
	public void verifyEquipmentRequestButton() {
		log.info(String.format("verifyTabValue(%s)", createNewReq + myReq + myOrg));

		delayFor(2);
		verifyText(By.id(createNewReqEle), createNewReq);
		verifyText(By.id(myReqEle), myReq);
		verifyText(By.id(myOrgEle), myOrg);

	}

	/**
	 * Method Name : <b>createNewRequest</b> <br>
	 * Description : This method will test and create a new Equipment Request
	 * .</br>
	 * 
	 */
	public void createNewRequest() {
		log.info(String.format("createNewRequest"));

		delayFor(2);
		click(By.id(createNewReqEle));
	}

	/**
	 * Method Name : <b>verifyUserManagementTabTitles</b> <br>
	 * Description : This method will test and verify user management tab titles
	 * Search.</br>
	 * 
	 */
	public void verifyEquipmentRequestTabTitles() {
		log.info(String.format("verifyUserManagementTabTitles(%s)", activeRequests + historicalRequests));

		delayFor(2);
		List<WebElement> elements = driver.findElements(By.tagName("span"));
		for (int i = 0; i < elements.size(); i++) {
			try {
				if (!elements.get(i).getText().isEmpty() && !elements.get(i).getText().equals(null)) {
					species.add(elements.get(i).getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		assertTrue(species.contains(activeRequests));
		assertTrue(species.contains(historicalRequests));
	}

}
