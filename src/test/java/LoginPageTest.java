import constants.Credentials;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.Assert.*;

public class LoginPageTest extends BaseTest {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    @Test
    public void userAuthorizationWithCorrectCredentials() {
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.USER_PASSWORD);
        assertTrue(homePage.addNewDataButtonIsDisplayed());
    }

    @Test
    public void userAuthorizationWithNonexistentCredentials() {
        loginPage.userAuthorization(Credentials.NONEXISTENT_USER_EMAIL, Credentials.NONEXISTENT_USER_PASSWORD);
        assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @Test
    public void userAuthorizationWithoutEmail() {
        loginPage.userAuthorization(Credentials.EMPTY_USER_EMAIL, Credentials.USER_PASSWORD);
        assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @Test
    public void userAuthorizationWithoutPassword() {
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.EMPTY_USER_PASSWORD);
        assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @Test
    public void userAuthorizationWithoutCredentials() {
        loginPage.userAuthorization(Credentials.EMPTY_USER_EMAIL, Credentials.EMPTY_USER_PASSWORD);
        assertTrue(loginPage.loginButtonIsDisplayed());
    }

    @Test
    public void errorMessageWithInvalidEmail() {
        loginPage.userAuthorization(Credentials.INCORRECT_USER_EMAIL, Credentials.USER_PASSWORD);
        assertTrue(loginPage.errorMessageAboutAnInvalidEmailIsDisplayed());
    }

    @Test
    public void errorMessageWithEmptyEmail() {
        loginPage.userAuthorization(Credentials.EMPTY_USER_EMAIL, Credentials.USER_PASSWORD);
        assertTrue(loginPage.errorMessageAboutAnInvalidEmailIsDisplayed());
    }

    @Test
    public void errorMessageWithInvalidEmailOrPassword() {
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.EMPTY_USER_PASSWORD);
        assertTrue(loginPage.errorMessageAboutAnInvalidEmailOrPasswordIsDisplayed());
    }

    @Test
    public void checkErrorMessageWithInvalidEmail() {
        loginPage.userAuthorization(Credentials.INCORRECT_USER_EMAIL, Credentials.USER_PASSWORD);
        assertEquals("Неверный формат E-Mail", loginPage.getErrorMessageTextAboutAnInvalidEmail());
    }

    @Test
    public void checkErrorMessageWithEmptyEmail() {
        loginPage.userAuthorization(Credentials.EMPTY_USER_EMAIL, Credentials.USER_PASSWORD);
        assertEquals("Неверный формат E-Mail", loginPage.getErrorMessageTextAboutAnInvalidEmail());
    }

    @Test
    public void checkErrorMessageWithInvalidEmailOrPassword() {
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.EMPTY_USER_PASSWORD);
        assertEquals("Неверный E-Mail или пароль", loginPage.getErrorMessageTextAboutAnInvalidEmailOrPassword());
    }

    @Test
    public void closingErrorMessageWithInvalidEmailByClickingButton() {
        loginPage.userAuthorization(Credentials.EMPTY_USER_EMAIL, Credentials.USER_PASSWORD);
        loginPage.clickClosingErrorMessageButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(loginPage.getErrorMessageAboutAnInvalidEmail()));
        try {
            loginPage.errorMessageAboutAnInvalidEmailIsDisplayed();
            fail();
        } catch (NoSuchElementException exception) {
        }
    }

    @Test
    public void closingErrorMessageWithInvalidEmailOrPasswordByClickingButton() {
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.EMPTY_USER_PASSWORD);
        loginPage.clickClosingErrorMessageButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(loginPage.getErrorMessageAboutAnInvalidEmailOrPassword()));
        try {
            loginPage.errorMessageAboutAnInvalidEmailOrPasswordIsDisplayed();
            fail();
        } catch (NoSuchElementException exception) {
        }
    }
}
