package lt.techin.evelina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[@href='/registruoti']")
    WebElement linkToRegistration;
    @FindBy(css = ".form-signin-heading")
    WebElement registrationFormHeader;

    public void clickLinkToRegistration(){
        linkToRegistration.click();
    }
    public String printRegistrationFormHeader(){
        return registrationFormHeader.getText();
    }
}
