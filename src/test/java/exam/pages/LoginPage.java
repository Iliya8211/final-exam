package exam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage{

    public final String URL = "http://training.skillo-bg.com:4200/users/login";
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy (css = "form .h4")
    WebElement signInHeader;

    @FindBy(name = "usernameOrEmail")
    WebElement userNameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy (id = "sign-in-button")
    WebElement signInBtn;

    @FindBy(css = ".fa-sign-out-alt")
    WebElement LogoutBtn;

    public void enterUserName (String username){
        enterText(userNameField, username);
    }

    public void enterPassword(String password){
        enterText(passwordField, password);
    }

    public void clickSignIn(){
        clickElement(signInBtn);
    }

    public String getSignInHeaderText (){
        return getElementText(signInHeader);
    }

    public void verifyUrl(){
        verifyUrl(URL);
    }

    public void login(String username, String password){
        enterUserName(username);
        enterPassword(password);
        clickSignIn();
    }

    public void clickLogout(){
        Assert.assertTrue(LogoutBtn.isDisplayed(), "The logout Btn is not displayed");
        clickElement(LogoutBtn);
    }
}
