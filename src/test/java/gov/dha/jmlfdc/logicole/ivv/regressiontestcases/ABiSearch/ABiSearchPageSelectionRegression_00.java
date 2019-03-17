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
		
//		
		Thread.sleep(10000);
		abisearchpage.viewProductDetails("00382903096046");
		abisearchpage.checkProductToCompare("00382903096046");
		abisearchpage.checkProductToCompare("10884521133105");
		abisearchpage.checkProductToCompare("30382903726104");
		abisearchpage.viewProductRelatedSiteRecords("30382903726104");
		abisearchpage.viewRelatedProducts("30382903726104");
		Thread.sleep(10000);
		
		
	}

	@AfterClass
	public static void cleanUp() {
		homepage.cleanUpEnv();
	
	}
		
}