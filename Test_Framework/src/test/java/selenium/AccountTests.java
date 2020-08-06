package selenium;

import dataproviders.UsersProvider;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.UserAccount;
import utils.DataUtils;


public class AccountTests extends BaseClass {

    private static final String EXPECTED_MESSAGE_ON_FAILURE = "Warning: No match for E-Mail Address and/or Password.";

    @Description("Test that login feature works fine")
    @Test(description = "Test Login Success", dataProvider = "getUserDataFromJson", dataProviderClass = UsersProvider.class)
    public void Test_Login_Successful(UserAccount userAccount){

        // go to login page
        headerPage().goToLogin();

        // enter information
        loginPage().loginWithCredentials(userAccount.getEmail(), userAccount.getPassword());

        // verificacion
        if (userAccount.isShouldLogin())
            Assert.assertTrue(headerPage().isLogoutLinkEnabled());
        else
            Assert.assertTrue(loginPage().isErrorMessageVisibleWithText(EXPECTED_MESSAGE_ON_FAILURE));
    }
}
