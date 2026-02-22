package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class  LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    By username = By.id("username");
    By password = By.id("password");
    By submitBtn = By.id("submit");

    // ✅ Correct locators
    By successHeader = By.tagName("h1");
    By errorMessage = By.id("error");

    // ✅ Actions
    public void enterUsername(String user) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        userField.clear();
        userField.sendKeys(user);
    }

    public void enterPassword(String pass) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        passField.clear();
        passField.sendKeys(pass);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    // ✅ Valid login page
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).getText();
    }

    // ✅ Failed login page
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}
