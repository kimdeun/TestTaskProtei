package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailField = By.id("loginEmail");
    private final By passwordField = By.id("loginPassword");
    private final By loginButton = By.id("authButton");
    private final By errorMessageAboutAnInvalidEmail = By.id("emailFormatError");
    private final By errorMessageAboutAnInvalidEmailOrPassword = By.id("invalidEmailPassword");
    private final By closingErrorMessageButton = By.className("uk-alert-close");

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void userAuthorization(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    public boolean loginButtonIsDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public boolean errorMessageAboutAnInvalidEmailIsDisplayed() {
        return driver.findElement(errorMessageAboutAnInvalidEmail).isDisplayed();
    }

    public boolean errorMessageAboutAnInvalidEmailOrPasswordIsDisplayed() {
        return driver.findElement(errorMessageAboutAnInvalidEmailOrPassword).isDisplayed();
    }

    public void clickClosingErrorMessageButton() {
        driver.findElement(closingErrorMessageButton).click();
    }

    public WebElement getErrorMessageAboutAnInvalidEmail() {
        return driver.findElement(errorMessageAboutAnInvalidEmail);
    }

    public WebElement getErrorMessageAboutAnInvalidEmailOrPassword() {
        return driver.findElement(errorMessageAboutAnInvalidEmailOrPassword);
    }

    public String getErrorMessageTextAboutAnInvalidEmail() {
        return driver.findElement(errorMessageAboutAnInvalidEmail).getText();
    }

    public String getErrorMessageTextAboutAnInvalidEmailOrPassword() {
        return driver.findElement(errorMessageAboutAnInvalidEmailOrPassword).getText();
    }
}
