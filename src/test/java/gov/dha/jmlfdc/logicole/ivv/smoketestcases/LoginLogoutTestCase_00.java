package gov.dha.jmlfdc.logicole.ivv.smoketestcases;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginLogoutTestCase_00 extends ScriptBase {

	@BeforeClass
	public static void setUp() {
		homepage.setUpEnv();
	}

	@Test
	public void testChromeSelenium() throws InterruptedException {

		// Step 1 -3: launch url and login to the logicole
		homepage.startAppliaction(EnvironmentType.test_env);
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	}

}