package lt.techin.evelina.test;

import lt.techin.evelina.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends BasePageTest{

    @Test
    void moveToRegistrationPage () {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLinkToRegistration();
        String expectedRegistrationFormHeader = "Naujos paskyros sukūrimas";
        String actualRegistrationFormHeader = mainPage.printRegistrationFormHeader();
        String expectedPageTitle = "Registracija";

        Assertions.assertEquals(expectedPageTitle, driver.getTitle(), "Error: Page title is not the same.");
        Assertions.assertEquals(expectedRegistrationFormHeader, actualRegistrationFormHeader, "Error: Registration form's header text is not the same.");
    }
}
