package exam.tests;

import exam.pages.Header;
import exam.pages.HomePage;
import exam.pages.LoginPage;
import exam.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommentPicture extends BaseTestMethod {

    @DataProvider(name = "userPass")
    public Object[][] userPass() {
        return new Object[][]{
                {"Iliya11", "I123456"}
        };
    }


    @Test(dataProvider = "userPass")
    public void commentPicture (String username, String password) {
        System.out.println("1.Open site URL");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteUrl();

        System.out.println("2. Click Login Bth");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3.Click on picture");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickOnPic();

        System.out.println("4.Make comment");
        profilePage.enterComment();

        System.out.println("5.Click on last comment");
        int actualNumbersOfComment = loginPage.getExistingCommentCount();
        loginPage.clickLastComment(actualNumbersOfComment-1);



        System.out.println("6.Verify text is posted");
        int newNumberOfComment = actualNumbersOfComment;
        Assert.assertEquals(newNumberOfComment, actualNumbersOfComment, "Comment wasnt created");
    }
}
