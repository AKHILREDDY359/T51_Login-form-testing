package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.*;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    // ✅ OPEN LOGIN PAGE
    @Given("user is on Practice Test login page")
    public void open_login_page() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        loginPage = new LoginPage(driver);
    }

    // ✅ ENTER CREDENTIALS
    @When("user logs in with username {string} and password {string}")
    public void enter_credentials(String username, String password) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
    }

    // ✅ VERIFY RESULT
    @Then("{string} should be displayed")
    public void verify_result(String result) {

        String url = driver.getCurrentUrl().toLowerCase();
        String message = "";

        // Try capturing error message (exists only for failed login)
        try {
            message = loginPage.getErrorMessage().toLowerCase();
        } catch (Exception e) {
            // No error message → likely success page
        }

        switch (result.toLowerCase()) {

            case "success":
                assertTrue("❌ Login failed but expected success",
                        url.contains("logged-in-successfully"));
                break;

            case "invalid credentials":
                assertTrue("❌ Expected invalid credentials message",
                        message.contains("invalid"));
                break;

            case "username required":
                assertTrue("❌ Expected username required validation",
                        message.contains("username"));
                break;

            case "password required":
                assertTrue("❌ Expected password required validation",
                        message.contains("password"));
                break;

            case "both required":
                assertTrue("❌ Expected required fields validation",
                        message.contains("username") || message.contains("password"));
                break;

            default:
                fail("⚠ Unexpected result in feature file → " + result);
        }

        driver.quit();
    }
}
