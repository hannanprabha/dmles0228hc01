package gov.dha.jmlfdc.logicole.ivv.regressiontestcases.AccessManagement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

public class RootLevelRoleManagementCreateNewRoleRegression_00 extends ScriptBase{ 

	@BeforeClass
	public static void setUp() {
		homepage.setUpEnv();
	}

	@Test
	public void testChromeSelenium() throws InterruptedException {

		// Step 1 -3: launch url and login to the logicole
		homepage.startAppliaction(EnvironmentType.test_env);
		
		homepage.selectUserProfile("Root");
		
		homepage.selectUserProfileManagement();
		
		usermanagement.verifyButtonValue();
		
		usermanagement.verifyUserManagementTabTitles();
		
		usermanagement.createInvitation();
		
		usermanagement.verifyInvalidProfileLookup("qeq2eqwe");
		
		usermanagement.verifyProfileLookup("zhen.x.kuang.ctr@mail.mil");
		
		usermanagement.filloutIdentificationInfo("IVV automation root");
		
		usermanagement.selectAccessLevel("Root", "DML-ES");
		
		usermanagement.selectRoleAccess("Admin Manager");
		
		usermanagement.selectRoleAccess("Manage Users");
		
		usermanagement.selectRoleAccess("Read-only User Profile Management");
		
		usermanagement.selectRoleAccess("ABi Management");
		
		usermanagement.selectRoleAccess("ABi Search");
		
		usermanagement.invitationConfirmation();
		
		usermanagement.verifyCancelInvitation();
		
		homepage.selectDashboard();
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}
		
}


