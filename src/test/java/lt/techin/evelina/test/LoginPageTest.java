package lt.techin.evelina.test;

import lt.techin.evelina.LoginPage;
import lt.techin.evelina.MainPage;
import lt.techin.evelina.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LoginPageTest extends BasePageTest{

    @ParameterizedTest
    @CsvFileSource(resources = "/validUserForLogin.csv", numLinesToSkip = 1)
    void validLogin(String nameCsv,String passwordCsv,String passwordConfirmCsv){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkToRegistration();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillAllInputsToRegister(nameCsv, passwordCsv, passwordConfirmCsv);
        registrationPage.clickLinkLogout();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterInputLoginName(nameCsv);
        loginPage.enterInputLoginPassword(passwordCsv);
        loginPage.clickButtonLogin();

        String expectedPageTitle = "Skaičiuotuvas";
        Assertions.assertEquals(expectedPageTitle, driver.getTitle(), "Error: Page title is not the same.");

        String actualAccountName = loginPage.getAccountName();
        Assertions.assertEquals(nameCsv, actualAccountName, "Error:  Shown name on the page is different from entered name.");

        loginPage.clickLinkLogout();

        Assertions.assertTrue(loginPage.isAlertMessageDisplayed());

        String expectedTextOfSuccessfulLogout = "Sėkmingai atsijungėte";
        String actualTextOfSuccessfulLogout = loginPage.getSuccessfulAlertMessage();
        Assertions.assertEquals(expectedTextOfSuccessfulLogout, actualTextOfSuccessfulLogout, "Error: alert message text is different.");
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/invalidUser.csv", numLinesToSkip = 1)
    void invalidLogin(String nameCsv, String passwordCsv) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterInputLoginName(nameCsv);
        loginPage.enterInputLoginPassword(passwordCsv);
        loginPage.clickButtonLogin();

        Assertions.assertTrue(loginPage.isAlertMessageAboutWrongInputsDisplayed());

        String expectedTextOfWrongInputsMessage = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actualTextOfWrongInputsMessage = loginPage.getWrongInputsAlertMessageText();

        Assertions.assertEquals(expectedTextOfWrongInputsMessage,actualTextOfWrongInputsMessage, "Error: alert message text is different.");


    }
}
