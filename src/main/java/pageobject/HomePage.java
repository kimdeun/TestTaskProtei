package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailField = By.id("dataEmail");
    private final By nameField = By.id("dataName");
    private final By genderDropdown = By.id("dataGender");
    private final By variant11CheckBox = By.id("dataCheck11");
    private final By variant12CheckBox = By.id("dataCheck12");
    private final By variant21Select = By.id("dataSelect21");
    private final By variant22Select = By.id("dataSelect22");
    private final By variant23Select = By.id("dataSelect23");
    private final By errorMessageAboutAnInvalidEmail = By.id("emailFormatError");
    private final By errorMessageAboutEmptyNameField = By.id("blankNameError");
    private final By errorMessageClosingButton = By.className("uk-alert-close");
    private final By addNewDataButton = By.id("dataSend");


    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setGender(String gender) {
        Select dropdown = new Select(driver.findElement(genderDropdown));
        if (gender.equals("Мужской")) {
            dropdown.selectByVisibleText("Мужской");
        } else {
            dropdown.selectByVisibleText("Женский");
        }
    }

    public void setCheckBox(boolean checkBox11, boolean checkBox12) {
        if (checkBox11 && !checkBox12) {
            driver.findElement(variant11CheckBox).click();
        } else if (!checkBox11 && checkBox12) {
            driver.findElement(variant12CheckBox).click();
        } else if (checkBox11 && checkBox12) {
            driver.findElement(variant11CheckBox).click();
            driver.findElement(variant12CheckBox).click();
        } else {
        }
    }

    public void setSelect(boolean select21, boolean select22, boolean select23) {
        if (select21) {
            driver.findElement(variant21Select).click();
        } else if (select22) {
            driver.findElement(variant22Select).click();
        } else if (select23) {
            driver.findElement(variant23Select).click();
        } else {
        }
    }

    public void addNewData(String email, String name, String gender, boolean checkBox11, boolean checkbox12, boolean select21, boolean select22, boolean select23) {
        setEmail(email);
        setName(name);
        setGender(gender);
        setCheckBox(checkBox11, checkbox12);
        setSelect(select21, select22, select23);
        clickAddNewDataButton();
    }

    public String getContentOfTheChosenColumn(int numberOfTheColumn) {
        return driver.findElement(By.xpath(".//tbody/tr[1]/td[" + numberOfTheColumn + "]")).getText();
    }

    public boolean addNewDataButtonIsDisplayed() {
        return driver.findElement(addNewDataButton).isDisplayed();
    }

    public void clickAddNewDataButton() {
        driver.findElement(addNewDataButton).click();
    }

    public String getErrorMessageTextAboutAnInvalidEmail() {
        return driver.findElement(errorMessageAboutAnInvalidEmail).getText();
    }

    public WebElement getErrorMessageAboutAnInvalidEmail() {
        return driver.findElement(errorMessageAboutAnInvalidEmail);
    }

    public String getErrorMessageTextAboutEmptyName() {
        return driver.findElement(errorMessageAboutEmptyNameField).getText();
    }

    public WebElement getErrorMessageAboutEmptyName() {
        return driver.findElement(errorMessageAboutEmptyNameField);
    }

    public boolean errorMessageAboutAnInvalidEmailIsDisplayed() {
        return driver.findElement(errorMessageAboutAnInvalidEmail).isDisplayed();
    }

    public boolean errorMessageAboutEmptyNameFieldIsDisplayed() {
        return driver.findElement(errorMessageAboutEmptyNameField).isDisplayed();
    }

    public void clickErrorMessageClosingButton() {
        driver.findElement(errorMessageClosingButton).click();
    }

}
