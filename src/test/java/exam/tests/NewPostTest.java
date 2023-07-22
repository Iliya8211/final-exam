package exam.tests;

import exam.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class NewPostTest extends BaseTestMethod {
    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{
                {"iliya11", "I123456", new File("src/test/java/exam/upload/sk08.jpg"), "football"}
        };
    }

    @Test(dataProvider = "getData")
    public void testCreatePost(String username, String password, File file, String capText) throws InterruptedException {
        System.out.println("1. Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteUrl();

        System.out.println("2. Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page and get current post count");
        header.goToProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        Thread.sleep(2000);
        int existPost = profilePage.getExistingPostCount();

        System.out.println("4. Go to new post");
        header.goToNewPost();
        NewPostPage postPage = new NewPostPage(driver);
        postPage.verifyUrl();

        System.out.println("5. Upload new picture");
        postPage.uploadImage(file);

        System.out.println("6. Verify that the image is visible");
        postPage.waitForImageToShow();

        System.out.println("7. Verify the image name is correct");
        Assert.assertEquals(postPage.getImageFileName(), file.getName(), "Incorrect file name");

        System.out.println("8. Populate the post caption");
        postPage.populateCaption(capText);

        System.out.println("9. Click create post");
        postPage.clickSubmitBtn();
        profilePage.verifyUrl();

        System.out.println("10. Verify the post number has increased");
        Thread.sleep(2000);
        int currentPostCount = profilePage.getExistingPostCount();
        Assert.assertEquals(currentPostCount, existPost + 1, "Incorrect post number");

        System.out.println("11. Open the latest post");
        profilePage.openPostByIndex(currentPostCount - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();
    }

}

