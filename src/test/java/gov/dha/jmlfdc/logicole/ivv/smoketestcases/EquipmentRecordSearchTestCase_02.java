package gov.dha.jmlfdc.logicole.ivv.smoketestcases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

public class EquipmentRecordSearchTestCase_02 extends ScriptBase{ 

	@BeforeClass
	public static void setUp() {
		homepage.setUpEnv();
	}

	@Test
	public void testChromeSelenium() throws InterruptedException {

		// Step 1 -3: launch url and login to the logicole
		homepage.startAppliaction(EnvironmentType.test_env);
		
		homepage.selectUserProfile("Site Equipment Custodian");
		
		homepage.selectOrganization("Pennsylvania Air National");
		
		homepage.selectEquipmentRecord();
		
		equipmentrecordpage.inputEquipmentRecordText("001");
		
		equipmentrecordpage.verifyEquipmentRecordResult();
		
		homepage.selectUserProfile("Site Equipment Manager");
		
		homepage.selectEquipmentRecord();
		
		equipmentrecordpage.inputEquipmentRecordText("002");
		
		equipmentrecordpage.verifyEquipmentRecordResult();
		
		equipmentrecordpage.verifyOneEquipmentRecordResult();
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}
		
}
