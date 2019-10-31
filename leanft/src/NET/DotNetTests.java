package NET;

import Web.ApiTests;

import com.hp.lft.sdk.web.*;

import com.hp.lft.sdk.wpf.TableRow;
import com.hp.lft.sdk.wpf.UiObject;
import org.junit.*;
import com.hp.lft.sdk.*;
//import com.hp.lft.sdk.winforms.*;
import com.hp.lft.verifications.*;

import org.junit.rules.TestName;
import unittesting.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.fail;

public class DotNetTests extends UnitTestClassBase {

    private static DotNetAppModel appModel;
    private static ProcessBuilder window;

    //private static String applocation = "C:\\LeanFTJavaRunner\\dotnet_release\\AdvantageShopAdministrator.exe";     // Path on CI
//    private static String DEFAULT_APPLICATION_PATH = "C:\\LeanFTJavaRunner\\dotnet_release\\AdvantageShopAdministrator.exe";     // Path on CI
//    private static String DEFAULT_APPLICATION_PATH = "C:\\AOS\\AdvancedOnlineShopping1.1.2\\AdvantageShopAdministrator.exe";     // Path on CI
    private static String DEFAULT_APPLICATION_PATH = "C:\\admin_client\\AdvantageShopAdministrator.exe";     
    private static String application = System.getProperty("application_path",DEFAULT_APPLICATION_PATH);    // Path on CI
    //private static String SERVER_DEFAULT = "http://16.60.158.84:80"; // CI
    private static String SERVER_DEFAULT = "http://18.212.178.84"; // Staging
//    private static String SERVER_DEFAULT = "http://16.59.19.38:8080"; // Tamir localhost
    public static String SERVER = System.getProperty("url", SERVER_DEFAULT);

    private static String UNAME = "Dot.NetUser";
    private static String PASSWORD = "Password1";

    public DotNetTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new DotNetTests();
        globalSetup(DotNetTests.class);
        InitBeforeClass();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
        window.command("close");
    }

    @Before
    public void setUp() throws Exception {
        printCaptionTest(curTestName.getMethodName());
    }

    @After
    public void tearDown() throws Exception {
        printEndOfTest(curTestName.getMethodName());
    }

    /**
     * Open tab PRODUCTS
     * Write in search -> mini
     * Select founded product
     * Click on tab Specifications
     * Change weight
     * Click SAVE button
     */
    @Test
    public void searchItemAndChangePropertyTest() throws GeneralLeanFtException, InterruptedException {
        ///just for test the list object
        print("SideList().select(\"PRODUCTS\")");
        appModel.AdvantageShopAdministrator().SideList().select("PRODUCTS");
        print("sleep 3000");
        Thread.sleep(3000);
        print("waitUntilElementExists PRODUCTSUiObject()");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        //appModel.AdvantageShopAdministrator().ProductsDataGridTable().selectCell(9,"Name");

        print("SearchEditField().setText(\"mini\")");
        appModel.AdvantageShopAdministrator().SearchEditField().setText("mini");
        print("waitUntilElementExists PRODUCTSUiObject()");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        print("ProductsDataGridTable().selectCell(0,\"Name\")");
        appModel.AdvantageShopAdministrator().ProductsDataGridTable().selectCell(0, "Name");

        print("ProductsWpfTabStrip().select(\"SPECIFICATIONS\")");
        appModel.AdvantageShopAdministrator().ProductsWpfTabStrip().select("SPECIFICATIONS");
        String val = appModel.AdvantageShopAdministrator().WEIGHTEditField().getText();
        if (val.equals("0.55 lb"))
            appModel.AdvantageShopAdministrator().WEIGHTEditField().setText("1.2 lb");
        else
            appModel.AdvantageShopAdministrator().WEIGHTEditField().setText("0.55 lb");

        /*appModel.AdvantageShopAdministrator().ProductsWpfTabStrip().select("CUSTOMISATION");

        appModel.AdvantageShopAdministrator().ADDCOLORSButton().click();
        appModel.AdvantageShopAdministrator().YelloeCheckBox().click();
        //add quantity
        /////

        appModel.AdvantageShopAdministrator().ProductsColorsGridTable().selectCell(2,"QUANTITY");*/
        print("SAVEButton().click()");
        appModel.AdvantageShopAdministrator().SAVEButton().click();
        //appModel.AdvantageShopAdministrator().SAVEButton().click();

        print("waitUntilElementExists PRODUCTSUiObject");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
    }

    /**
     * Open tab USERS MANAGEMENT
     * Click on ADD USER button
     * Fill fields
     * Click OK button
     * API test browser login with new user
     * Remove new created user
     */
    @Test
    public void addUserTest() throws GeneralLeanFtException, InterruptedException {
        /*
        /create new user of type user (customer)
        save
        search for the new user
       change its password
        delete the user
         */
        Thread.sleep(3000);
        print(".SideList().select(\"USERS MANAGEMENT\")");
        appModel.AdvantageShopAdministrator().SideList().select("USERS MANAGEMENT");
        //appModel.AdvantageShopAdministrator().SideList().select("USERS MANAGEMENT");

        print("waitUntilElementExists UsersControlUiObject");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().UsersControlUiObject());
        System.out.println("Creating user... ");

        print("ADDUSERButton().click()");
        appModel.AdvantageShopAdministrator().ADDUSERButton().click();
        print("UserNameUserManageEditField().setText(UNAME)");
        appModel.AdvantageShopAdministrator().UserNameUserManageEditField().setText(UNAME);
        print("EmailUserManageEditField().setText(\"Dot@Net.com\")");
        appModel.AdvantageShopAdministrator().EmailUserManageEditField().setText("Dot@Net.com");
        threadSleep(1500);
        print("PasswordUserManageEditField().setText(" + PASSWORD);
        appModel.AdvantageShopAdministrator().PasswordUserManageEditField().setText(PASSWORD);
        print("ConfirmPasswordUserManageEditField().setText(" + PASSWORD);
        appModel.AdvantageShopAdministrator().ConfirmPasswordUserManageEditField().setText(PASSWORD);
        print("FirstNameUserManageEditField().setText(\"Dot\")");
        appModel.AdvantageShopAdministrator().FirstNameUserManageEditField().setText("Dot");
        print("LastNameUserManageEditField().setText(\"Net\")");
        appModel.AdvantageShopAdministrator().LastNameUserManageEditField().setText("Net");
        threadSleep(1500);
        print("OKUserManageButton().click()");
        appModel.AdvantageShopAdministrator().OKUserManageButton().click();
        print("Checking for 'user already exist' box" );
        if (appModel.AdvantageShopAdministrator().createUserFailedDialog().exists()){
            print("User already exist");
            threadSleep(1000);
            appModel.AdvantageShopAdministrator().createUserFailedDialog().oKButton().click();
            appModel.AdvantageShopAdministrator().cANCELButton().click();
            threadSleep(1000);
        }

        System.out.println("validate user via web login... ");
        ApiTests test = new ApiTests();
        // TODO: verify in browser
        Verification(Verify.isTrue(test.signIn(UNAME, PASSWORD, SERVER), "Sign In .Net New user via web", "verify that user created  by .Net app and can login via web"));

        //System.out.println("Change Password... ");

        int index = 0;
        Thread.sleep(2000);
        for (TableRow row : appModel.AdvantageShopAdministrator().UsersTypesGridTable().getRows()) {
            //System.out.println(row.getCells().get(0).getValue());
            {
                if (!row.getCells().get(0).getValue().equals(UNAME))
                    index++;
                else
                    break;
            }
        }

        //todo: uncomment this after fixing the bug
        /*appModel.AdvantageShopAdministrator().UsersTypesGridTable().selectCell(index,"RESET PASSWORD");
        ChangePass();*/
        System.out.println("Deleting user...");
        appModel.AdvantageShopAdministrator().UsersTypesGridTable().selectCell(index, "DELETE");
        print("OKDeleteButton().click()");
        appModel.DeleteUserConfirmationWindow().OKDeleteButton().click();
    }

    public static void InitBeforeClass() throws IOException, GeneralLeanFtException {
        print("CURRENT SERVER: " + SERVER);
        appModel = new DotNetAppModel();
        window = new ProcessBuilder(application);
        window.start();
        SignIn();
    }

    public static void SignIn() throws GeneralLeanFtException {

        print("SignIn() start");
        if(appModel.AdvantageShopAdministrator().exists()){
            print("yes");
        }else{
            print("no");
        }
        threadSleep(1000);
        print("UserNameEditField().setText(\"admin\")");
        appModel.AdvantageShopAdministrator().userNameEditField().setText("admin");
        print("PasswordEditField().setText(\"adm1n\");");
        appModel.AdvantageShopAdministrator().PasswordEditField().setText("adm1n");
        print("ServerEditField().setText(SERVER)");
        appModel.AdvantageShopAdministrator().ServerEditField().setText(SERVER);

        print("SIGNINButton().click()");
        appModel.AdvantageShopAdministrator().SIGNINButton().click();
        //print("SIGNINButton().click()");
        //appModel.AdvantageShopAdministrator().SIGNINButton().click();Push
        print("waitUntilElementExists PRODUCTSUiObject(");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        if (checkIsSignIn()){
            print("Successful login");
        }else{
            print("Did not login");
            print("error message is -" + appModel.AdvantageShopAdministrator().AdminUiObject().getText());
            print(appModel.AdvantageShopAdministrator().AdminUiObject().getText());
            print("Checking is there internet connection");
            print(" isNetAvailable returned - " + netIsAvailable());
            print("Trying to click signIn again ");
            appModel.AdvantageShopAdministrator().SIGNINButton().click();
            if(checkIsSignIn ()){
                print("Successful login");
            }else {
                print("Did not login");
                threadSleep(1000);
                print("UserNameEditField().setText(\"Mercury\")");
                appModel.AdvantageShopAdministrator().userNameEditField().setText("Mercury");
                print("PasswordEditField().setText(\"Mercury\");");
                appModel.AdvantageShopAdministrator().PasswordEditField().setText("Mercury");
                print("Trying to click signIn again ");
                appModel.AdvantageShopAdministrator().SIGNINButton().click();
                if(checkIsSignIn ()){
                    print("Successful login");
                }else {
                    print("Did not login");
                    print("Trying to log with different server");
                    threadSleep(1000);
                    print("UserNameEditField().setText(\"Mercury\")");
                    appModel.AdvantageShopAdministrator().userNameEditField().setText("Mercury");
                    print("PasswordEditField().setText(\"Mercury\");");
                    appModel.AdvantageShopAdministrator().PasswordEditField().setText("Mercury");
                    print("ServerEditField().setText(http://18.212.178.84)");
                    if(SERVER.contains("158")){
                        appModel.AdvantageShopAdministrator().ServerEditField().setText("http://18.212.178.84");
                    }else{
                        print("ServerEditField().setText(http://16.60.158.84:80)");
                        appModel.AdvantageShopAdministrator().ServerEditField().setText("://16.60.158.84:80");
                    }
                    appModel.AdvantageShopAdministrator().SIGNINButton().click();
                    if(checkIsSignIn ()){
                        print("Successful login");
                    }else {
                        print("Fail at login");
                    }
                }
            }
        }
        threadSleep(1000);
        print("SignIn() end");
    }

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean checkIsSignIn (){

        print("isSignIn starts");
        boolean isSignIn = false;
        threadSleep(2000);
        try{

            if(appModel.AdvantageShopAdministrator().LogoutButton().exists()){
                isSignIn = true;
                print("isSignIn = true");
            }else{
                print("isSignIn = false");
            }
            return isSignIn;
        }catch (Exception e){
            e.printStackTrace();
            isSignIn = false;
            return isSignIn;
        }
    }

    public void ChangePass() throws GeneralLeanFtException {
        appModel.ResetPasswordForDotNetUserWindow().NewPassEditField().setText("Password2");
        appModel.ResetPasswordForDotNetUserWindow().RePassEditField().setText("Password2");
        appModel.ResetPasswordForDotNetUserWindow().OKPasswordButton().click();
    }

    public static boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException {
        return WaitUntilTestObjectState.waitUntil(appElem, new WaitUntilTestObjectState.WaitUntilEvaluator<UiObject>() {
            public boolean evaluate(UiObject we) {
                try {
                    return we.exists();
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public static void print(String message) {
        System.out.println(message);
    }

    private static void threadSleep(long millis) {
        try {
            print("sleep " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            print("\nERROR: " + e.getMessage() +  "\n");
            fail("InterruptedException: failed to sleep for " + millis + " sec");
        }
    }

    private static void printCaptionTest(String nameOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest) {
        System.out.println("END " + nameOfTest);
    }

}