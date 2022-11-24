import constants.Credentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageChoice1ParameterizedTest extends BaseTest {
    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");

        String path = this.getClass().getResource("/qa-test.html").getPath();
        driver.get("file://" + path);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.USER_PASSWORD);
    }

    private boolean checkBox11;
    private boolean checkBox12;
    private String expectedChoice1;


    public HomePageChoice1ParameterizedTest(boolean checkBox11, boolean checkBox12, String expectedChoice1) {
        this.checkBox11 = checkBox11;
        this.checkBox12 = checkBox12;
        this.expectedChoice1 = expectedChoice1;
    }

    @Parameterized.Parameters
    public static Object[][] parameterizedTest() {
        return new Object[][]{
                {true, false, "1.1"},
                {false, true, "1.2"},
                {true, true, "1.1, 1.2"},
                {false, false, "Нет"}
        };
    }

    @Test
    public void checkChoice1() {
        HomePage homePage = new HomePage(driver);
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.USER_NAME, Credentials.MALE_USER, checkBox11, checkBox12, false, false, false);
        assertEquals(expectedChoice1, homePage.getContentOfTheChosenColumn(4));
    }
}
