package exam.tests;

import exam.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeletePicture extends BaseTestMethod {

    @DataProvider(name = "userAndPass")
    public Object[][] userAndPass() {
        return new Object[][]{
                {"Iliya11", "I123456"}
    };
    }

    @Test (dataProvider = "userAndPass")
    public void deletePic (String username, String password){
        System.out.println("1.Open site URL");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteUrl();

        System.out.println("2. Click Login Bth");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Click on profile Btn");
        header.goToProfile();

        System.out.println("4.Count current posts");
        ProfilePage profilePage = new ProfilePage(driver);
        int numCurrentPost =  profilePage.getExistingPostCount();
        System.out.println(numCurrentPost);

        System.out.println("5.Click on some pic");
        PostModal postModal = new PostModal(driver);
        postModal.clickPic();

        System.out.println("6.Press delete Btn picture");
        profilePage.clickDelBtnPic();

        System.out.println("7.Press YES to confirm deletion");
        profilePage.confirmPicDeletion();
        profilePage.verifyUrl();

        System.out.println("8. Verify that number of pictures has decrease with one");
        int newNumberOfPic = profilePage.getExistingPostCount();
        System.out.println(newNumberOfPic);
        Assert.assertEquals(newNumberOfPic, numCurrentPost -1, "Picture was not delete it");
    }
}
