package lt.techin.evelina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@id = 'username']")
    WebElement inputName;
    @FindBy(xpath = "//input[@id = 'password']")
    WebElement inputPassword;
    @FindBy(xpath = "//input[@id = 'passwordConfirm']")
    WebElement inputPasswordConfirm;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonCreate;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement linkLogout;
    @FindBy(css = ".form-heading")
    WebElement loginPageHeader;

    @FindBy(xpath = "//form[@id='userForm']//span")
    List<WebElement> registrationErrorMessages;

    public void enterName (String name) {
        inputName.sendKeys(name);
    }
    public void enterPassword (String password) {
        inputPassword.sendKeys(password);
    }
    public void enterPasswordConfirm (String passwordConfirm) {
        inputPasswordConfirm.sendKeys(passwordConfirm);
    }
    public void clickButtonCreate() {
        buttonCreate.click();
    }
    public String getAccountName() {
        String logoutText = linkLogout.getText();
        return logoutText.split(" ")[1];
    }
    public void clickLinkLogout () {
        linkLogout.click();
    }
    public String checkLoginPageHeader() {
        return loginPageHeader.getText();
    }
    public void fillAllInputsToRegister(String name, String password, String passwordConfirm) {
        enterName(name);
        enterPassword(password);
        enterPasswordConfirm(passwordConfirm);
        clickButtonCreate();
    }
   public boolean isErrorMessageDisplayed(String errorMessageText){
        ArrayList<String> newErrorMessages = registrationErrorMessages.stream().map(WebElement::getText).collect(Collectors.toCollection(ArrayList::new));
        return newErrorMessages.contains(errorMessageText);
    }
}
