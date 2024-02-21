package lt.techin.evelina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@name='username']")
    WebElement inputLoginName;
    @FindBy(xpath = "//input[@name='password']")
    WebElement inputLoginPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonLogin;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement linkLogout;
    @FindBy(xpath = "//span[.='Sėkmingai atsijungėte']")
    WebElement messageAboutSuccessfulLogout;
    @FindBy(xpath = "//form[@action='/prisijungti']/div/span[2]")
    WebElement messageAboutWrongLoginInputs;

    public void enterInputLoginName(String name){
        inputLoginName.sendKeys(name);
    }
    public void enterInputLoginPassword(String password) {
        inputLoginPassword.sendKeys(password);
    }
    public void clickButtonLogin() {
        buttonLogin.click();
    }
    public String getAccountName() {
        String logoutText = linkLogout.getText();
        return logoutText.split(" ")[1];
    }
    public void clickLinkLogout() {
        linkLogout.click();
    }
    public boolean isAlertMessageDisplayed() {
        return messageAboutSuccessfulLogout.isDisplayed();
    }
    public String getSuccessfulAlertMessage() {
        return messageAboutSuccessfulLogout.getText();
    }
    public boolean isAlertMessageAboutWrongInputsDisplayed() {
        return messageAboutWrongLoginInputs.isDisplayed();
    }
    public String getWrongInputsAlertMessageText(){
        return messageAboutWrongLoginInputs.getText();
    }

}
