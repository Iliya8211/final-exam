package exam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class Header extends BasePage {
    @FindBy(id = "nav-link-home")
    WebElement homeLink;

    @FindBy(id = "nav-link-login")
    WebElement loginLink;

    @FindBy(linkText = "Profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostLink;

    @FindBy(css = "app-post")
    List<WebElement> existingPosts;

    @FindBy(css = ".post-feed-container")
    WebElement userPicture;

    @FindBy(css = ".like")
    WebElement likeBtn;

    @FindBy(css = ".toast-message")
    WebElement popUpMsg;

    @FindBy(css = ".fa-sign-out-alt")
    WebElement LogoutBtn;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin() {
        clickElement(loginLink);
    }

    public void goToHome() {
        clickElement(homeLink);
    }

    public void goToProfile() {
        clickElement(profileLink);
    }

    public void goToNewPost() {
        clickElement(newPostLink);
    }

    public void clickUserPicture() {
        clickElement(userPicture);
    }

    public void pressLikeBtn() {
        clickElement(likeBtn);
    }

    public void getToastMsgText() {
        smallWait.until(ExpectedConditions.visibilityOf(popUpMsg));
        String toastMsg = popUpMsg.getText().trim();
        Assert.assertEquals(toastMsg, "You must login", "Incorrect popUp message");
    }

    public void clickLogout(){
        Assert.assertTrue(LogoutBtn.isDisplayed(), "The logout Btn is not displayed");
        clickElement(LogoutBtn);
    }

    public void invisibilityOfLogoutBtn(){
        smallWait.until(ExpectedConditions.invisibilityOf(LogoutBtn));
    }
}
