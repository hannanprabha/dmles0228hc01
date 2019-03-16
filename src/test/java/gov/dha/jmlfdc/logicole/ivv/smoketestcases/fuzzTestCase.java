package gov.dha.jmlfdc.logicole.ivv.smoketestcases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

public class fuzzTestCase extends ScriptBase{
	
	String randomAlphaNumMin = randomName(1, "ALPHA_NUM");
	String randomAlphaNumAvg = randomName(30, "ALPHA_NUM");
	String randomAlphaNumMax120 = randomName(120, "ALPHA_NUM");
	String randomAlphaNumMax4000 = randomName(4000, "ALPHA_NUM");
	
	String randomAlphaMin = randomName(1, "ALPHA");
	String randomAlphaAvg = randomName(30, "ALPHA");
	String randomAlphaMax120 = randomName(120, "ALPHA");
	String randomAlphaMax4000 = randomName(4000, "ALPHA");
	
	String randomNumMin = randomName(1, "NUM");
	String randomNumAvg = randomName(4, "NUM");
	String randomNumAvg1 = randomName(4, "ALPHA_NUM");
	String randomNumMax120 = randomName(120, "NUM");
	String randomNumMax4000 = randomName(4000, "NUM");
	
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
		
		homepage.selectEquipmentRequest();
		
		equipmentrequestpage.verifyEquipmentRequestButton();
		
		equipmentrequestpage.createNewRequest();

		equipmenteqeustfuzzpage.fillRequestTitle(randomAlphaNumMin);
		
		equipmenteqeustfuzzpage.fillRequestTitle(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillRequestTitle(randomAlphaNumMax120);
		
		equipmenteqeustfuzzpage.fillMissionImpact(randomAlphaNumMin);
		
		equipmenteqeustfuzzpage.fillMissionImpact(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillMissionImpact(randomAlphaNumMax4000);
	
		equipmenteqeustfuzzpage.fillRequestDescription(randomAlphaNumMin);
		
		equipmenteqeustfuzzpage.fillRequestDescription(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillRequestDescription(randomAlphaNumMax120);
		
		equipmenteqeustfuzzpage.fillRequestFirstName(randomAlphaMin);
		
		equipmenteqeustfuzzpage.fillRequestFirstName(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillRequestFirstName(randomAlphaMax120);
		
		equipmenteqeustfuzzpage.fillRequestLastName(randomAlphaMin);
		
		equipmenteqeustfuzzpage.fillRequestLastName(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillRequestLastName(randomAlphaMax120);
		
		equipmenteqeustfuzzpage.fillRequestPhoneNumber(randomNumMin);
		
		equipmenteqeustfuzzpage.fillRequestPhoneNumber(randomNumAvg);
		
		equipmenteqeustfuzzpage.fillRequestPhoneNumber(randomNumMax120);
		
		equipmenteqeustfuzzpage.fillEquipmentDescription(randomAlphaNumMin);
		
		equipmenteqeustfuzzpage.fillEquipmentDescription(randomAlphaNumAvg);
		
		equipmenteqeustfuzzpage.fillEquipmentDescription(randomAlphaNumMax4000);
		
		equipmenteqeustfuzzpage.fillUnitCost(randomNumMin);
		
		equipmenteqeustfuzzpage.fillUnitCost(randomNumAvg);
		
		equipmenteqeustfuzzpage.fillUnitCost(randomNumAvg1);
		
		equipmenteqeustfuzzpage.fillUnitCost(randomNumMax120);
			
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}

}
