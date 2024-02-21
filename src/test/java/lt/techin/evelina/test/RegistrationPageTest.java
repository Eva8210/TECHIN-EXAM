package lt.techin.evelina.test;

import lt.techin.evelina.MainPage;
import lt.techin.evelina.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class RegistrationPageTest extends BasePageTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/validUserForRegistration.csv", numLinesToSkip = 1)
    void validRegistrationAndLogout(String nameCsv,String passwordCsv,String passwordConfirmCsv) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkToRegistration();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterName(nameCsv);
        registrationPage.enterPassword(passwordCsv);
        registrationPage.enterPasswordConfirm(passwordConfirmCsv);
        registrationPage.clickButtonCreate();

        String actualAccountName = registrationPage.getAccountName();
        String expectedPageTitle = "Skaičiuotuvas";

        Assertions.assertEquals(nameCsv, actualAccountName, "Error:  Shown name on the page is different from entered name.");
        Assertions.assertEquals(expectedPageTitle, driver.getTitle(), "Error: Page title is not the same.");

        registrationPage.clickLinkLogout();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/invalidInputsForRegistration.csv", numLinesToSkip = 1)
    void inValidRegistration (String nameCsv, String passwordCsv, String passwordConfirmCsv, String messageErrorCsv) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkToRegistration();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillAllInputsToRegister(nameCsv, passwordCsv, passwordConfirmCsv);

        Assertions.assertTrue(registrationPage.isErrorMessageDisplayed(messageErrorCsv), "Error message: " + messageErrorCsv);
    }
    @Test
    void testLogout() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkToRegistration();

        String name = "testingLogout";
        String password = "testingLogout";
        String passwordConfirm = "testingLogout";

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillAllInputsToRegister(name, password, passwordConfirm);

        registrationPage.clickLinkLogout();
        String expectedPageTitleAfterLogout = "Prisijungimas";
        String expectedLoginPageHeaderName = "Prisijungimas";
        String actualLoginPageHeaderName = registrationPage.checkLoginPageHeader();
        Assertions.assertEquals(expectedPageTitleAfterLogout, driver.getTitle(), "Error: Page title is not the same.");
        Assertions.assertEquals(expectedLoginPageHeaderName, actualLoginPageHeaderName, "Error: Login form's header text is not the same.");
    }
}
