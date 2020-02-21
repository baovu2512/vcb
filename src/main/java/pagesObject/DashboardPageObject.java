package pagesObject;

import org.openqa.selenium.WebDriver;

import PageUIs.DashboardPageUI;
import commons.AbstractPage;

public class DashboardPageObject extends AbstractPage {
	WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardPageDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.CHART_MENU_HEADER);
		return isControlDisplayed(driver, DashboardPageUI.CHART_MENU_HEADER);
	}

	public void inputDatePicker(String sDate) {
		waitForElementVisible(driver, DashboardPageUI.DATE_PICKER_INPUT);
		sendKeyToElement(driver, DashboardPageUI.DATE_PICKER_INPUT, sDate);
	}

	public void clickQueryButton() {
		waitForElementVisible(driver, DashboardPageUI.QUERY_BUTTON);
		clickToElement(driver, DashboardPageUI.QUERY_BUTTON);
		waitForElementInvisible(driver, DashboardPageUI.SPINNER_LOADING);
	}

	public void clickPDFMenu(){
		waitForElementVisible(driver, DashboardPageUI.PDF_MENU);
		clickToElement(driver, DashboardPageUI.PDF_MENU);
	}
}
