package exam.tests;

import exam.pages.Header;
import exam.pages.HomePage;
import org.testng.annotations.Test;

public class LikePicWhenNotLogin extends BaseTestMethod {

    @Test
    public void likePic (){
        System.out.println("1.Open site URL");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteUrl();

        System.out.println("2. Go to certain user picture");
        Header header = new Header(driver);
        header.clickUserPicture();

        System.out.println("3.Press like Btn on open pic");
        header.pressLikeBtn();

        System.out.println("Verify popUp message appear that you are not login");
        header.getToastMsgText();
    }
}
