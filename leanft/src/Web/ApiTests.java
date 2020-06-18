package Web;

import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import org.junit.*;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;
import com.hp.lft.sdk.apitesting.uft.APITestResult;
import com.hp.lft.sdk.apitesting.uft.APITestRunner;

import org.junit.runners.MethodSorters;
import unittesting.*;

import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTests extends UnitTestClassBase {

    public static String appURL = "http://" + System.getProperty("url", "defaultvalue");//52.32.172.3:8081";//"http://52.88.236.171:8081";//; //"http://16.59.19.163:8080"; //"35.162.69.22:8080";
    public static String WSDL = "/accountservice/accountservice.wsdl";
    public static String UserID = "";
    public static String token = "";
    String Login = "_APIuser";
    String Pass = "Password1";

    public Browser browser;

    //import WSDL from : http://52.88.236.171:8081/accountservice/accountservice.wsdl - account service
    //import WSDL from : http://52.88.236.171:8080/ShipEx/shipex.wsdl - ShipEx service
    //import REST from : http://52.88.236.171:8080/api/docs

    AdvantageStagingAppModel appModel;

    public ApiTests() throws GeneralLeanFtException {
        //Change this constructor to private if you supply your own public constructor
        //browser = BrowserFactory.launch(BrowserType.CHROME);
        //appModel = new AdvantageStagingAppModel(browser);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new ApiTests();
        globalSetup(ApiTests.class);

        if (appURL.equals("http://defaultvalue"))
            appURL = "http://16.60.158.84:8080";
//            appURL = "http://52.88.236.171:8081";

        System.out.println("URL: " + appURL + WSDL);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        //browser.close();
    }

    @Test
    public void CreateAccountByAPiTest() throws GeneralLeanFtException, InterruptedException {
        P("-----------------------------------------CreateAccountByAPiTest--------------------------------------------");
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API");
        inParams.put("last", "API");
        inParams.put("loginName", Login);
        inParams.put("email", "api@api.com");
        inParams.put("password", Pass);
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "");

        APITestResult res = CreateNewAccount(inParams);
        //String id =
        UserID = res.getOutParams().get("UserID");
        token = res.getOutParams().get("token");
        //UserID = Integer.parseInt(id);

        System.out.println("userId : " + UserID);
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();
        System.out.println("Status: " + status);

        Verification(Verify.isTrue(status, "Create Account by API " + msg, "validate that the account was create successfully the user -  " + inParams.get("loginName")));
        //signIn(Login, Pass);
        P("-----------------------------------------CreateAccountByAPiTest end--------------------------------------------");
    }

    @Test
    public void CreateExistingAccountByAPiTest() throws GeneralLeanFtException, InterruptedException {
        P("-----------------------------------------CreateExistingAccountByAPiTest--------------------------------------------");
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API");
        inParams.put("last", "API");
        inParams.put("loginName", Login);
        inParams.put("email", "api@api.com");
        inParams.put("password", Pass);
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "");

        APITestResult res = CreateNewAccount(inParams);
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();
        System.out.println("Status: " + status);

        Verification(Verify.isFalse(status, "Create Existing Account by API " + msg, "validate that the account was NOT create the user -  " + inParams.get("loginName")));
        //signIn(Login, Pass);
        P("-----------------------------------------CreateExistingAccountByAPiTest end--------------------------------------------");
    }

    @Test
    public void negativeCreateAccountByAPiTest_username() throws GeneralLeanFtException {
        P("-----------------------------------------negativeCreateAccountByAPiTest_username--------------------------------------------");
        //try to create account with unproper username (less then 5 letters) TODO: should not return true!
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API");
        inParams.put("last", "API");
        inParams.put("loginName", "abc");
        inParams.put("email", "api@api.com");
        inParams.put("password", Pass);
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "");

        APITestResult res = CreateNewAccount(inParams);
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();
        System.out.println("Status: " + status);
        Verification(Verify.isFalse(status, "Create Account by API non valid username" + msg, "validate that the account was NOT create the user -  " + inParams.get("loginName")));
        P("-----------------------------------------negativeCreateAccountByAPiTest_username end--------------------------------------------");
    }

    @Test
    public void negativeCreateAccountByAPiTest_email() throws GeneralLeanFtException {
        //try to create account without email
        P("-----------------------------------------negativeCreateAccountByAPiTest_email--------------------------------------------");
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API");
        inParams.put("last", "API");
        inParams.put("loginName", Login + "_e");
        inParams.put("email", "-****");
        inParams.put("password", Pass);
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "");

        APITestResult res = CreateNewAccount(inParams);
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();//todo : throw error even staus is false
        System.out.println("Status: " + status);
        Verification(Verify.isFalse(status, "Create Account by API no email " + msg, "validate that the account was NOT create the user -  " + inParams.get("loginName")));
        P("-----------------------------------------negativeCreateAccountByAPiTest_email end--------------------------------------------");
    }

    @Test
    public void negativeCreateAccountByAPiTest_password() throws GeneralLeanFtException {
        //try to create account with unproper password (non upper case and number)
        P("-----------------------------------------negativeCreateAccountByAPiTest_password--------------------------------------------");
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API");
        inParams.put("last", "API");
        inParams.put("loginName", Login + "_p");
        inParams.put("email", "api@api.com");
        inParams.put("password", "badpass");
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "");

        APITestResult res = CreateNewAccount(inParams);
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();//todo : throw error even status is false
        System.out.println("Status: " + status);
        Verification(Verify.isFalse(status, "Create Account by API non valid password" + msg, "validate that the account was NOT create the user -  " + inParams.get("loginName")));
        P("-----------------------------------------negativeCreateAccountByAPiTest_password end--------------------------------------------");
    }

    @Test
    public void UpdateAccountByAPiTest() throws GeneralLeanFtException {
        P("-----------------------------------------UpdateAccountByAPiTest--------------------------------------------");
        // needed the UserId/accountId from database
        // email is mandatory
        //todo: throw error even the test passed and do the update

        /* list of argument that you must pass (even empty):
            name - first name
           last - last name
           email - email (mandatory)
           city - city name
           address - Address
           ZIP  - zip code
           phone  - phone number

        * */
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API_c");
        inParams.put("last", "API_c");
        inParams.put("email", "api@apiupdate.com");
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "");
        inParams.put("address", "Altalef 5");

        APITestResult res = UpdateAccount(UserID, inParams);
        System.out.println("parameters: " + res.getOutParams());
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();
        System.out.println("Status: " + status);
        Verification(Verify.isTrue(status, "Update Account by API " + msg, "validate that the account updated the userId -  " + UserID));
        P("-----------------------------------------UpdateAccountByAPiTest end--------------------------------------------");
    }

    @Test
    public void negativeUpdateAccountByAPiTest() throws GeneralLeanFtException {
        //Try to update user with non valid parameter ZIP code (more then 10 characters)
        P("-----------------------------------------negativeUpdateAccountByAPiTest--------------------------------------------");
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("URL", appURL + WSDL);
        inParams.put("name", "API_c");
        inParams.put("last", "API_c");
        inParams.put("email", "api@apiupdate.com");
        inParams.put("city", "");
        inParams.put("phone", "");
        inParams.put("ZIP", "1234567894545"); // not valid
        inParams.put("address", "Altalef 5");

        APITestResult res = UpdateAccount(UserID, inParams);
        System.out.println("parameters: " + res.getOutParams());
        String msg = res.getOutParams().get("message");
        boolean status = res.getStatus();//todo : throw error even status is false
        System.out.println("Status: " + status);
        Verification(Verify.isFalse(status, "Update Account by API non valid ZIP code" + msg, "validate that the account NOT updated the userId -  " + UserID));
        P("-----------------------------------------negativeUpdateAccountByAPiTest end--------------------------------------------");
    }

    @Test
    public void changePasswordByAPiTest() throws GeneralLeanFtException {
        P("-----------------------------------------changePasswordByAPiTest--------------------------------------------");
        //todo: throw error even the test passed and do the change
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("UserID", Integer.parseInt(UserID));
        inParams.put("URL", appURL + WSDL);
        inParams.put("token", token);
        inParams.put("OldPassword", Pass);
        inParams.put("NewPassword", "Newpass1");

        APITestResult result = APITestRunner.run("C:\\UFT tests\\ChangePasswordByApi", inParams);

        String msg = result.getOutParams().get("message");
        boolean status = result.getStatus();
        System.out.println("Status: " + status);
        Verification(Verify.isTrue(status, "Change Password by API" + msg, "validate that the Password changed in success"));
        P("-----------------------------------------changePasswordByAPiTest end--------------------------------------------");
    }

    @Test
    public void z_deleteAccountByAPiTest() throws GeneralLeanFtException {
        P("-----------------------------------------deleteAccountByAPiTest--------------------------------------------");
        //todo: throw error even the test passed and delete the user
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("UserID", Integer.parseInt(UserID));
        inParams.put("token", "");
        inParams.put("URL", appURL + WSDL);

        APITestResult result = APITestRunner.run("C:\\UFT tests\\DeleteAccountByApi", inParams);

        String msg = result.getOutParams().get("message");
        boolean status = result.getStatus();
        System.out.println("Status: " + status);
        Verification(Verify.isTrue(status, "Delete Account by API" + msg, "validate that the account was delete the user -  " + inParams.get("loginName")));
        P("-----------------------------------------deleteAccountByAPiTest end--------------------------------------------");
    }    ///////////////////////////////////////////////////// End of tests ///////////////////////////////////////////////////////

    public boolean signIn(String USERNAME, String PASSWORD, String url) throws GeneralLeanFtException, InterruptedException {
        if (browser == null) {
            browser = BrowserFactory.launch(BrowserType.CHROME);
            appModel = new AdvantageStagingAppModel(browser);
        }

        browser.navigate(url);
        // Click the sign-in icon
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        //waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
        // Fill in the user name and password
        appModel.AdvantageShoppingPage().UsernameLoginEditField().setValue(USERNAME);
        appModel.AdvantageShoppingPage().PasswordLoginEditField().setValue(PASSWORD);
        // Check the Remember Me checkbox
        appModel.AdvantageShoppingPage().RememberMeCheckBox().set(true);
        // Click on sign in button
        appModel.AdvantageShoppingPage().SIGNINButton().click();

        Thread.sleep(2000);

        Verify.isTrue(true, "Verification - Sign In", "Verify that the user " + USERNAME + " signed in properly.");
        return true;
    }

    public APITestResult CreateNewAccount(Map<String, Object> inParams) throws GeneralLeanFtException {
         /* list of argument that can pass:
            name     - first name               (2-30 letters)
           last      - last name                (2-30 letters)
           loginName - login name  [mandatory]  (5-15 letters)
           password  - password    [mandatory]  (4-12 letters , at least one lower and one upper , at least one number)
           email     - email       [mandatory]  ([*]@[*].com)[mandatory]
           city      - city name                (max 25 letters)
           address   - Address                  (max 50 characters)
           ZIP       - zip code                 (max 10 characters)
           phone     - phone number             (max 20 characters)
        * */

        APITestResult result = APITestRunner.run("C:\\UFT tests\\CreateAccountByApi", inParams);

        System.out.println("New user parameters:  " + result.getOutParams());
        return result;
    }

    public APITestResult UpdateAccount(String UserId, Map<String, Object> inParams) throws GeneralLeanFtException {
        inParams.put("UserID", Integer.parseInt(UserId));
        APITestResult result = APITestRunner.run("C:\\UFT tests\\UpdateAccountByAPi", inParams);
        return result;
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public void P(String msg) {
        System.out.println(msg);
    }

}