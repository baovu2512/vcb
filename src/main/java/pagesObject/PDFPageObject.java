package pagesObject;

import PageUIs.AbstractPageUI;
import PageUIs.DashboardPageUI;
import PageUIs.PdfPageUI;
import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PDFPageObject extends AbstractPage {
	WebDriver driver;

	public PDFPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPdfPageeDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.CHART_MENU_HEADER);
		return isControlDisplayed(driver, DashboardPageUI.CHART_MENU_HEADER);
	}

	public void inputDatePicker(String sDate) {
		waitForElementVisible(driver, DashboardPageUI.DATE_PICKER_INPUT);
		sendKeyToElement(driver, DashboardPageUI.DATE_PICKER_INPUT, sDate);
	}

	public void clickQueryButton() {
		waitForElementVisible(driver, PdfPageUI.QUERY_BUTTON);
		clickToElement(driver,PdfPageUI.QUERY_BUTTON);
		waitForElementInvisible(driver,DashboardPageUI.SPINNER_LOADING);
	}

	public List<WebElement> clickToKyBaoCaoAndGetAllElement(){
		waitForElementVisible(driver, PdfPageUI.TENBAOCAO_INPUT);
		clickToElement(driver, PdfPageUI.TENBAOCAO_INPUT);
		waitForElementPresence(driver,PdfPageUI.DROPDOWN_ELEMENT_SPAN);
		return getAllElement(driver,PdfPageUI.DROPDOWN_ELEMENT_SPAN);
	}

	public boolean verifyTenBaoCao(String locator){
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TENBAOCAO_TABLEDATA,locator);
		return isControlDisplayed(driver,AbstractPageUI.DYNAMIC_TENBAOCAO_TABLEDATA,locator);

	}
	public void selectKyBaoCao(WebElement element){
		waitForElementVisible(driver, element);
		clickToElementByAction(driver,element);
	}


}
