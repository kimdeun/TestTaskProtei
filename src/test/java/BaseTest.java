import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");

        String path = this.getClass().getResource("/qa-test.html").getPath();
        driver.get("file://" + path);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
