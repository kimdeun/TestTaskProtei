import constants.Credentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageGenderParameterizedTest extends BaseTest {
    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");

        String path = this.getClass().getResource("/qa-test.html").getPath();
        driver.get("file://" + path);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.USER_PASSWORD);
    }

    private String gender;
    private String expectedGender;


    public HomePageGenderParameterizedTest(String gender, String expectedGender) {
        this.gender = gender;
        this.expectedGender = expectedGender;
    }

    @Parameterized.Parameters
    public static Object[][] parameterizedTest() {
        return new Object[][]{
                {"Мужской", "Мужской"},
                {"Женский", "Женский"},
        };
    }

    @Test
    public void checkGender() {
        HomePage homePage = new HomePage(driver);
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.USER_NAME, gender, false, false, false, false, false);
        assertEquals(expectedGender, homePage.getContentOfTheChosenColumn(3));
    }
}
