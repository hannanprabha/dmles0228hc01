package gov.dha.jmlfdc.logicole.ivv.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.jetty.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import gov.dha.jmlfdc.logicole.ivv.pages.HomePage.EnvironmentType;
import gov.dha.jmlfdc.logicole.ivv.utils.BasePage;

public class ABiSearchPage extends BasePage {

	private static Logger log = Logger.getLogger(ABiSearchPage.class.getName());
	private WebElement firstPageEle = null, prePageEle = null, nextPageEle = null, lastPageEle = null;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	private long start, end;

	/***************** Web Object Element ********************/
	private String searchInputEle = "searchInput";
	private String sidePanelEle = "toggleLeftSidePanel";
	private String productLineEle = "Product LineCategoryPanel";
	private String productCategoryEle = "Product CategoryCategoryPanel";
	private String productTypeEle = "Product TypeCategoryPanel";
	private String searchWithinResultEle = "searchWithinResultsInput";
	private String resultRowsEle = "#abiSearchDiv div[class^='ui-grid-row']";
	
	private String leftSidePanelResultEle = "//*[@id='abiLeftSidePanel']/div/div[2]/div/div/div/selected-facet-options-breadbox/div";
	/**************************** END **********************************/

	/***************** Expected String Text ********************/
	private String productLineTxt = "Product Line";
	private String productCategoryTxt = "Product Category";
	private String productTypeTxt = "Product Type";
	private int searchResult1 = 0, searchResult2 = 0, searchResult3 = 0;
	private String pageFirstButtonTxt = "Page to first";
	private String pageBackButtonTxt = "Page back";
	private String pageForwardButtonTxt = "Page forward";
	private String pageLastButtonTxt = "Page to last";
	private String firstPageSelectionTxt = "first-bar";
	private String prevousPageSelectionTxt = "first-triangle prev-triangle";
	private String nextPageSelectionTxt = "last-triangle next-triangle";
	private String lastPageSelectionTxt = "last-triangle";

	/**************************** END **********************************/

	/**
	 * Method Name : <b>inputABiSearchText</b> <br>
	 * Description : This method will inputer text for ABi Search.</br>
	 * 
	 */
	public void inputABiSearchText(String input) {
		log.info(String.format("inputABiSearchText(%s)", input));

		writeText(By.id(searchInputEle), input);
		delayFor(2);
		String result = readText(By.xpath("//*[@id='abiSearchDiv']/div[2]/div/div/div/span"));
		searchResult1 = Integer.parseInt(result.substring(0, result.indexOf("item")).trim());

		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		for (int i = 55; i < buttons.size(); i++) {
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageFirstButtonTxt)) {
				firstPageEle = buttons.get(i);
				buttonDisabled(firstPageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageBackButtonTxt)) {
				prePageEle = buttons.get(i);
				buttonDisabled(prePageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageForwardButtonTxt)) {
				nextPageEle = buttons.get(i);
				buttonEnabled(nextPageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageLastButtonTxt)) {
				lastPageEle = buttons.get(i);
				buttonEnabled(lastPageEle);
			}
		}
	}

	/**
	 * Method Name : <b>expandSidePanel</b> <br>
	 * Description : This method will expand side panel.</br>
	 * 
	 */
	public void expandSidePanel() {
		log.info(String.format("expandSidePanel()"));

		start = System.currentTimeMillis();
		click(By.id(sidePanelEle));
		verifyText(By.id(productLineEle), productLineTxt);
		verifyText(By.id(productCategoryEle), productCategoryTxt);
		verifyText(By.id(productTypeEle), productTypeTxt);
		end = System.currentTimeMillis();
		log.info("Time calcuate in milliseconds to click side panel: " + (end - start - 5000));

	}

	/**
	 * Method Name : <b>inputABiSearchText</b> <br>
	 * Description : This method will inputer text for ABi Search.</br>
	 * 
	 */
	public void inputSearchWithinResult(String input) {
		log.info(String.format("inputSearchWithinResult(%s)", input));

		writeText(By.id(searchWithinResultEle), input);
		delayFor(2);
		String result = readText(By.xpath("//*[@id='abiSearchDiv']/div[2]/div/div/div/span"));
		searchResult2 = Integer.parseInt(result.substring(0, result.indexOf("item")).trim());
	}

	/**
	 * Method Name : <b>selectProductType</b> <br>
	 * Description : This method will select type of the product.</br>
	 * 
	 */
	public void selectProductType(String inputType) {
		log.info(String.format("selectProductType(%s)", inputType));

		List<WebElement> elements = driver.findElements(By.xpath("//span"));
		for (int i = 0; i < elements.size(); i++) {
			try {
				if (elements.get(i).getText().equalsIgnoreCase(inputType)) {
					elements.get(i).click();
				}
			} catch (Exception e) {
			}
		}

		delayFor(1);
		String expProductLineResult = "Product Line: " + inputType;
		List<WebElement> elementsResults = driver.findElements(By.xpath(leftSidePanelResultEle));
		for (int i = 0; i < elementsResults.size(); i++) {
			try {
				if (elementsResults.get(i).getText().equals(expProductLineResult)) {
					assertTrue(true);
					break;
				}
			} catch (Exception e) {
			}
		}

		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		for (int i = 55; i < buttons.size(); i++) {
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageFirstButtonTxt)) {
				firstPageEle = buttons.get(i);
				buttonDisabled(firstPageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageBackButtonTxt)) {
				prePageEle = buttons.get(i);
				buttonDisabled(prePageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageForwardButtonTxt)) {
				nextPageEle = buttons.get(i);
				buttonEnabled(nextPageEle);
			}
			if (buttons.get(i).getAttribute("title").equalsIgnoreCase(pageLastButtonTxt)) {
				lastPageEle = buttons.get(i);
				buttonEnabled(lastPageEle);
			}
		}

		delayFor(3);
		String result = readText(By.xpath("//*[@id='abiSearchDiv']/div[2]/div/div/div/span"));
		searchResult3 = Integer.parseInt(result.substring(0, result.indexOf("item")).trim());
	}

	/**
	 * Method Name : <b>pageActions</b> <br>
	 * Description : This method will navigate to page and verify the page
	 * buttons.</br>
	 * 
	 */
	public void pageActions(PageSelections pageSelections) {
		log.info(String.format("Page Navigation: " + getPageSelections(pageSelections)));

		List<WebElement> pageActButtons = null;
		pageActButtons = driver.findElements(By.tagName("div"));
		for (int i = 1080; i < pageActButtons.size(); i++) {
			if (pageActButtons.get(i).getAttribute("class").equalsIgnoreCase(getPageSelections(pageSelections))) {
				js.executeScript("arguments[0].scrollIntoView(true)", pageActButtons.get(i));
				pageActButtons.get(i).click();
				if (getPageSelections(pageSelections).equalsIgnoreCase(firstPageSelectionTxt)) {
					buttonDisabled(firstPageEle);
					buttonDisabled(prePageEle);
					buttonEnabled(nextPageEle);
					buttonEnabled(lastPageEle);
					break;
				}
				if (getPageSelections(pageSelections).equalsIgnoreCase(lastPageSelectionTxt)) {
					buttonEnabled(firstPageEle);
					buttonEnabled(prePageEle);
					buttonDisabled(nextPageEle);
					buttonDisabled(lastPageEle);
					prePageEle.click();
					break;
				}
				if (getPageSelections(pageSelections).equalsIgnoreCase(prevousPageSelectionTxt)) {
					if (!firstPageEle.isEnabled()) {
						buttonDisabled(firstPageEle);
						buttonDisabled(prePageEle);
						buttonEnabled(nextPageEle);
						buttonEnabled(lastPageEle);
						break;
					} else {
						buttonEnabled(firstPageEle);
						buttonEnabled(prePageEle);
						buttonEnabled(nextPageEle);
						buttonEnabled(lastPageEle);
						break;
					}
				}
				if (getPageSelections(pageSelections).equalsIgnoreCase(nextPageSelectionTxt)) {
					if (!lastPageEle.isEnabled()) {
						buttonEnabled(firstPageEle);
						buttonEnabled(prePageEle);
						buttonDisabled(nextPageEle);
						buttonDisabled(lastPageEle);
						prePageEle.click();
						break;
					} else {
						buttonEnabled(firstPageEle);
						buttonEnabled(prePageEle);
						buttonEnabled(nextPageEle);
						buttonEnabled(lastPageEle);
						break;
					}
				}
			}
		}
	}

	/**
	 * Method Name : <b>pageNumberSelection</b> <br>
	 * Description : This method will navigate to specify page.</br>
	 * 
	 */
	public void pageNumberSelection(String pageNumber) {
		log.info(String.format("Navigate to Page Number: " + pageNumber));

		delayFor(2);
		int currentPageNum = 0, currentItemPerPage = 0, totalNumbers = 0;
		List<WebElement> pageNumSelection = driver.findElements(By.tagName("input"));
		for (int i = 30; i < pageNumSelection.size(); i++) {
			try {
				if (pageNumSelection.get(i).getAttribute("title").equalsIgnoreCase("Selected page")) {
					try {
						js.executeScript("arguments[0].scrollIntoView(true)", pageNumSelection.get(i));
						pageNumSelection.get(i).click();
						pageNumSelection.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"));
						pageNumSelection.get(i).sendKeys(pageNumber);
						currentPageNum = Integer.parseInt(pageNumSelection.get(i).getAttribute("value"));
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		List<WebElement> items = driver.findElements(By.tagName("select"));
		for (int i = 0; i < items.size(); i++) {
			Select dropdown = new Select(items.get(i));
			currentItemPerPage = Integer.parseInt(dropdown.getFirstSelectedOption().getText());
		}
		List<WebElement> itemsNum = driver.findElements(By.tagName("span"));
		String test, testConv;
		for (int i = 600; i < itemsNum.size(); i++) {

			try {
				if (itemsNum.get(i).getAttribute("ng-show").contains("totalItems")) {
					test = itemsNum.get(i).getText();
					testConv = test.substring(test.indexOf("-") + 1, test.indexOf("of"));
					totalNumbers = Integer.parseInt(testConv.trim());
				}
			} catch (Exception e) {
			}
		}
		assertTrue(totalNumbers == currentPageNum * currentItemPerPage);
	}

	/**
	 * Method Name : <b>pageNumberItems</b> <br>
	 * Description : This method will display numbers of items.</br>
	 * 
	 */
	public void pageNumberItems(String itemsPerPage) {
		log.info(String.format("Display number of items per page: " + itemsPerPage));

		delayFor(2);

		List<WebElement> items = driver.findElements(By.tagName("select"));
		for (int i = 0; i < items.size(); i++) {
			Select dropdown = new Select(items.get(i));
			dropdown.selectByVisibleText(itemsPerPage);
		}

		List<WebElement> itemResults = driver.findElements(By.tagName("div"));
		int count = 0;
		for (int i = 480; i < itemResults.size(); i++) {
			try {
				if (itemResults.get(i).getAttribute("ng-repeat").contains("(rowRenderIndex")) {
					count++;
				}
			} catch (Exception e) {
			}
		}
		List<WebElement> itemsNum = driver.findElements(By.tagName("span"));
		String itemsPerPageTest = null, test1;
		for (int i = 600; i < itemsNum.size(); i++) {
			try {
				if (itemsNum.get(i).getAttribute("ng-if").contains("visibleRowCache")) {
					js.executeScript("arguments[0].scrollIntoView(true)", itemsNum.get(i));
					test1 = itemsNum.get(i).getText();
					itemsPerPageTest = test1.substring(test1.indexOf(":") + 1, test1.indexOf(")"));
				}
			} catch (Exception e) {
			}
		}

		assertTrue(Integer.parseInt(itemsPerPage.replaceAll("\\s+", "")) == count);
		assertTrue(Integer.parseInt(itemsPerPage.replaceAll("\\s+", "")) == Integer
				.parseInt(itemsPerPageTest.replaceAll("\\s+", "")));
	}

	/**
	 * Method Name : <b>verifyABiSearchResult</b> <br>
	 * Description : This method will test and verify ABi Search.</br>
	 * 
	 */
	public void verifyABiSearchResult() {
		log.info(String.format("verifyABiSearchResult()"));

		delayFor(2);
		List<WebElement> elements = driver.findElements(By.tagName("button"));
		int count = 0;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute("id").contains("goToDetailsButtonLink")) {
				count = count + 1;
			}
		}
		assertTrue(count > 0);
	}

	/**
	 * Method Name : <b>verifySearchResults</b> <br>
	 * Description : This method will verify search results.</br>
	 * 
	 */
	public void verifySearchResults() {
		log.info(String.format("verifySearchResults()"));

		try {
			if (searchResult1 >= searchResult2 && searchResult2 >= searchResult3)
				;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public enum PageSelections {
		First_Page, Prevous_Page, Next_Page, Last_Page

	}

	private String getPageSelections(PageSelections pageSelections) {

		String tempPageSelection = "";
		if (pageSelections == PageSelections.First_Page) {
			tempPageSelection = firstPageSelectionTxt;
		} else if (pageSelections == PageSelections.Prevous_Page) {
			tempPageSelection = prevousPageSelectionTxt;
		} else if (pageSelections == PageSelections.Next_Page) {
			tempPageSelection = nextPageSelectionTxt;
		} else if (pageSelections == PageSelections.Last_Page) {
			tempPageSelection = lastPageSelectionTxt;
		}

		return tempPageSelection;
	}
	
	
	/**
	 * Description : This method will click on Product Identifier button to view product details in searched results table
	 * Usage : viewProductDetails("00382903096046");
	 * 
	 */
	public void viewProductDetails(String productIdentifier)
	{
		WebElement rowObject = getResultRowObject(productIdentifier);
		Actions action = new Actions(driver);
		WebElement button_object = rowObject.findElement(By.cssSelector("button[id^='goToDetailsButtonLink']"));
		action.moveToElement(button_object).click().perform();
	}
	
	
	/**
	 * Description : This method will click on check box to compare product in searched results table
	 * Usage : checkProductToCompare("00382903096046");
	 * 
	 */
	public void checkProductToCompare(String productIdentifier)
	{
		WebElement rowObject = getResultRowObject(productIdentifier);
		Actions action = new Actions(driver);
		WebElement checkbox_object = rowObject.findElement(By.cssSelector("input[type='checkbox'][title^='Select to compare']"));
		action.moveToElement(checkbox_object).click().perform();
	}
	
	
	/**
	 * Description : This method will click on view related site records button to view product related site records in searched results table
	 * Usage : viewProductRelatedSiteRecords("00382903096046");
	 * 
	 */
	public void viewProductRelatedSiteRecords(String productIdentifier)
	{
		WebElement rowObject = getResultRowObject(productIdentifier);
		Actions action = new Actions(driver);
		WebElement button_object = rowObject.findElement(By.cssSelector("button[id^='viewRelatedSiteRecordsButtonLink']"));
		action.moveToElement(button_object).click().perform();
	}
	
	
	/**
	 * Description : This method will click on view related products button to view related products of product in searched results table
	 * Usage : viewRelatedProducts("00382903096046");
	 * 
	 */
	public void viewRelatedProducts(String productIdentifier)
	{
		WebElement rowObject = getResultRowObject(productIdentifier);
		Actions action = new Actions(driver);
		WebElement button_object = rowObject.findElement(By.cssSelector("button[id^='viewRelatedProductsButtonLink']"));
		action.moveToElement(button_object).click().perform();
	}
	
	
	/**
	 * Description : This method will return Abi search result row object by product identifier value
	 * Usage : getResultRowObject("00382903096046");
	 * 
	 */
	private WebElement getResultRowObject(String productIdentifier) 
	{
		int rowIndex = -1;
		WebElement rowObject = null;
		List<WebElement> elements = driver.findElements(By.cssSelector("button[id^='goToDetailsButtonLink']"));
		for (int row = 0; row < elements.size(); row++) 
		{
			String actualProductIdentifier = elements.get(row).getText();
			if (productIdentifier.equals(actualProductIdentifier)) 
			{
				rowIndex = row;
				break;
			}
		}
		if (rowIndex != -1)
		{
			List<WebElement> rowElements = driver.findElements(By.cssSelector(resultRowsEle));
			rowObject = rowElements.get(rowIndex);
			js.executeScript("arguments[0].scrollIntoView(true)", rowObject);
		}
		else
		{
			String msg = String.format("[%s] Product Identifier not exist in Abi search result", productIdentifier); 
			throw new RuntimeException(msg);
		}
		
		return rowObject;
	}
		
}