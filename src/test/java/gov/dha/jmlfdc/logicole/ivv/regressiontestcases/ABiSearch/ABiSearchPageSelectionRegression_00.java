package gov.dha.jmlfdc.logicole.ivv.regressiontestcases.ABiSearch;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.dha.jmlfdc.logicole.ivv.ScriptBase;
import gov.dha.jmlfdc.logicole.ivv.pages.ABiSearchPage.PageSelections;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;

public class ABiSearchPageSelectionRegression_00 extends ScriptBase{ 

	@BeforeClass
	public static void setUp() {
		homepage.setUpEnv();
	}

	@Test
	public void testChromeSelenium() throws InterruptedException {

		// Step 1 -3: launch url and login to the logicole
		homepage.startAppliaction(EnvironmentType.test_env);
		
		homepage.selectUserProfile("Abi Management");
		
		homepage.selectABiSearch();
		
		abisearchpage.expandSidePanel();
		
		abisearchpage.selectProductType("Dental");
		
		abisearchpage.pageActions(PageSelections.Next_Page);
		
		abisearchpage.pageActions(PageSelections.Next_Page);
		
		abisearchpage.pageActions(PageSelections.Prevous_Page);
		
		abisearchpage.pageActions(PageSelections.Last_Page);
		
		abisearchpage.pageActions(PageSelections.Next_Page);
		
		abisearchpage.pageActions(PageSelections.Prevous_Page);
		
		abisearchpage.pageActions(PageSelections.Next_Page);
		
		abisearchpage.pageActions(PageSelections.First_Page);
		
		abisearchpage.pageActions(PageSelections.Next_Page);
		
		abisearchpage.pageNumberSelection("8");
//		
		abisearchpage.pageNumberItems("50");
		
		
		// Step 4-6: logout and verify the output message
		homepage.logout();
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}
		
}