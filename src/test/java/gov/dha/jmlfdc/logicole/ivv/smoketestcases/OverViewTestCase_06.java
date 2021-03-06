package gov.dha.jmlfdc.logicole.ivv.smoketestcases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

public class OverViewTestCase_06 extends ScriptBase{ 

	@BeforeClass
	public static void setUp() {
		homepage.setUpEnv();
	}

	@Test
	public void testChromeSelenium() throws InterruptedException {

		// Step 1 -3: launch url and login to the logicole
		homepage.startAppliaction(EnvironmentType.test_env);
		
		homepage.selectUserProfile("Site Equipment Custodian");
		
		homepage.selectOrganization("Winn Army Community Hospital");
		
		homepage.selectABiSearch();
		
		abisearchpage.inputABiSearchText("durapore");
		
		abisearchpage.expandSidePanel();
		
		abisearchpage.inputSearchWithinResult("10");
		
		abisearchpage.selectProductType("Tape");
		
		abisearchpage.verifySearchResults();
		
		homepage.selectDashboard();
		
		homepage.selectEquipmentRequestsDashboard();
		
		equipmentrequestpage.verifyEquipmentRequestButton();
		
		equipmentrequestpage.verifyEquipmentRequestTabTitles();
		
		homepage.selectDashboard();
		
		homepage.selectEquipmentRecordsDashboard();
		
		equipmentrecordpage.inputEquipmentRecordText("micro");
		
		equipmentrecordpage.verifyEquipmentRecordResult();
		
		homepage.selectDashboard();
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}
		
}
