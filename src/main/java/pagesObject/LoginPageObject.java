package pagesObject;

import org.openqa.selenium.WebDriver;

import PageUIs.LoginPageUI;
import commons.AbstractPage;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLoginFormDisplayed() {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		return isControlDisplayed(driver, LoginPageUI.USERNAME_TEXTBOX);
	}

	public void sendKeyToUserName(String sUsername) {
		sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, sUsername);

	}

	public void sendKeyToPassword(String sPassword) {
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, sPassword);
	}

	public void clickToLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
}
