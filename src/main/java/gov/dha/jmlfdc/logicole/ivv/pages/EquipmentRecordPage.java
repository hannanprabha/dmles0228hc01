package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;


public class EquipmentRecordPage extends BasePage {

	private static Logger log = Logger.getLogger(EquipmentRecordPage.class.getName());

	/***************** Web Object Element ********************/
	private String searchInputEle = "search";

	/**************************** END **********************************/

	/***************** Expected String Text ********************/
	private String oneResult;
	/**************************** END **********************************/

	/**
	 * Method Name : <b>inputEquipmentRecordText</b> <br>
	 * Description : This method will inputer text for Equipment Record Search.</br>
	 * 
	 */
	public void inputEquipmentRecordText(String input) {
		log.info(String.format("inputEquipmentRecordText(%s)", input));

		writeText(By.id(searchInputEle), input);
	}

	/**
	 * Method Name : <b>verifyEquipmentRecordResult</b> <br>
	 * Description : This method will test and verify Equipment Record Search.</br>
	 * 
	 */
	public void verifyEquipmentRecordResult() {
		log.info(String.format("verifyEquipmentRecordResult()"));
		
		delayFor(2);
		List<WebElement> elements = driver.findElements(By.tagName("td"));
		oneResult = elements.get(2).getText();
		int count = 0;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute("title").contains("Equipment Control Number")) {
				count = count + 1;
			}
		}
		assertTrue(count > 0);
	}
	
	/**
	 * Method Name : <b>verifyOneEquipmentRecordResult</b> <br>
	 * Description : This method will test and verify one equipment record.</br>
	 * 
	 */
	public void verifyOneEquipmentRecordResult() {
		log.info(String.format("verifyOneEquipmentRecordResult(%s)", oneResult));
		
		inputEquipmentRecordText(oneResult);
		delayFor(2);
		List<WebElement> elements = driver.findElements(By.tagName("td"));
		int count = 0;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getText().equals(oneResult)) {
				count = count + 1;
			}
		}
		assertTrue(count == 1);
	}
}
