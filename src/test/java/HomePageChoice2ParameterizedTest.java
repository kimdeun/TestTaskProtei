import constants.Credentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageChoice2ParameterizedTest extends BaseTest {
    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");

        String path = this.getClass().getResource("/qa-test.html").getPath();
        driver.get("file://" + path);
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.userAuthorization(Credentials.USER_EMAIL, Credentials.USER_PASSWORD);
    }

    private boolean select21;
    private boolean select22;
    private boolean select23;
    private String expectedChoice2;

    public HomePageChoice2ParameterizedTest(boolean select21, boolean select22, boolean select23, String expectedChoice2) {
        this.select21 = select21;
        this.select22 = select22;
        this.select23 = select23;
        this.expectedChoice2 = expectedChoice2;
    }

    @Parameterized.Parameters
    public static Object[][] parameterizedTest() {
        return new Object[][]{
                {true, false, false, "2.1"},
                {false, true, false, "2.2"},
                {false, false, true, "2.3"},
                {false, false, false, "Нет"}
        };
    }

    @Test
    public void checkChoice2() {
        HomePage homePage = new HomePage(driver);
        homePage.addNewData(Credentials.USER_EMAIL, Credentials.USER_NAME, Credentials.FEMALE_USER, false, false, select21, select22, select23);
        assertEquals(expectedChoice2, homePage.getContentOfTheChosenColumn(5));
    }
}
