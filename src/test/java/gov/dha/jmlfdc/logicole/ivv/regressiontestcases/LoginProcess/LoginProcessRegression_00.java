package gov.dha.jmlfdc.logicole.ivv.regressiontestcases.LoginProcess;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginProcessRegression_00 extends ScriptBase {

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
		
		homepage.verifyHomePageHeader("Equipment Requests");
		
		homepage.verifyHomePageHeader("Equipment Records");
		
		homepage.verifyHomePageHeader("User Profile Administration");
		
		homepage.verifyHomePageTab("My Action Required");

		homepage.verifyHomePageTab("My Requests");
		
		homepage.verifyHomePageTab("My Organization's Requests");
		
		homepage.verifyHomePageTab("Active Accountable Records");
		
		homepage.verifyHomePageTab("Active Records Requiring Maintenance");
		
		homepage.verifyHomePageTab("Total Active Records");
		
		homepage.verifyHomePageTab("Total Value of Active Records");
		
		homepage.verifyHomePageTab("Active Invitations");
		
		homepage.verifyHomePageTab("Active Profiles");
		
		homepage.verifyHomePageTab("Inactive Profiles");
		
		homepage.verifyHomePageTab("Locked Profiles");
		
		homepage.verifyHomePageTab("Relocked Profiles");
		
//		homepage.verifyHomePageTab("Suspended Profiles"); TODO: removed base on 1.6
		
		homepage.verifyHomePageTab("Total Authorized Profiles");
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
		
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	}

}