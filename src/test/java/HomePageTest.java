import constants.Credentials;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.Assert.*;

public class HomePageTest extends BaseTest {
    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");

        String path = this.getClass().getResource("/qa-test.html").getPath();
        driver.get("file://" + path);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.USER_PASSWORD);
    }

    HomePage homePage = new HomePage(driver);

    @Test
    public void addDataWithCorrectEmail() {
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertEquals(Credentials.USER_EMAIL, homePage.getContentOfTheChosenColumn(1));
    }

    @Test
    public void addDataWithCorrectName() {
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertEquals(Credentials.USER_NAME, homePage.getContentOfTheChosenColumn(2));
    }

    @Test
    public void addDataWithEmptyEmail() {
        homePage.addNewData(Credentials.EMPTY_USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertTrue(homePage.errorMessageAboutAnInvalidEmailIsDisplayed());
    }

    @Test
    public void addDataWithIncorrectEmail() {
        homePage.addNewData(Credentials.INCORRECT_USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertTrue(homePage.errorMessageAboutAnInvalidEmailIsDisplayed());
    }

    @Test
    public void addDataWithEmptyName() {
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.EMPTY_USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertTrue(homePage.errorMessageAboutEmptyNameFieldIsDisplayed());
    }

    @Test
    public void checkErrorMessageWithEmptyEmail() {
        homePage.addNewData(Credentials.EMPTY_USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertEquals("Неверный формат E-Mail", homePage.getErrorMessageTextAboutAnInvalidEmail());
    }

    @Test
    public void checkErrorMessageWithIncorrectEmail() {
        homePage.addNewData(Credentials.INCORRECT_USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertEquals("Неверный формат E-Mail", homePage.getErrorMessageTextAboutAnInvalidEmail());
    }

    @Test
    public void checkErrorMessageWithEmptyName() {
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.EMPTY_USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        assertEquals("Поле имя не может быть пустым", homePage.getErrorMessageTextAboutEmptyName());
    }

    @Test
    public void closingErrorMessageWithInvalidEmailByClickingButton() {
        homePage.addNewData(Credentials.INCORRECT_USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        homePage.clickErrorMessageClosingButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(homePage.getErrorMessageAboutAnInvalidEmail()));
        try {
            homePage.errorMessageAboutAnInvalidEmailIsDisplayed();
            fail();
        } catch (NoSuchElementException exception) {
        }
    }

    @Test
    public void closingErrorMessageWithEmptyNameByClickingButton() {
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.EMPTY_USER_NAME, Credentials.MALE_USER, false, false, false, false, false);
        homePage.clickErrorMessageClosingButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(homePage.getErrorMessageAboutEmptyName()));
        try {
            homePage.errorMessageAboutEmptyNameFieldIsDisplayed();
            fail();
        } catch (NoSuchElementException exception) {
        }
    }
}
