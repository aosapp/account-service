package Mobile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.mobile.*;
import com.hp.lft.verifications.*;

import unittesting.*;

//Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class androidTests extends UnitTestClassBase {

    static AdvantageAndroidApp appModel;
    protected static Device device;
    protected static Application app;

    static String UNAME = "androidUser1";
    static String PASS = "Password1";

    static String appURL2 = "www.advantageonlineshopping.com";//"52.88.236.171"; //"52.32.172.3:8080";//"35.162.69.22:8080";//
//    static String appURL2 = "16.60.158.84";
    static String appURL = System.getProperty("url", "defaultvalue");

    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public androidTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        startTimeAllTests = System.currentTimeMillis();
        instance = new androidTests();
        globalSetup(androidTests.class);

        if (appURL.equals("defaultvalue")) {
            appURL = appURL2;
            InitBeforeclassLocal();
        } else
            InitBeforeclass();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        device.unlock();
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        Print("androidTests done in: " + String.valueOf((elapsedTimeAllTests/1000F)/60 + " min"));
    }

    @Before
    public void setUp() throws Exception {
        startTimeCurrentTest = System.currentTimeMillis();
        Print("restarting application...");
        app.restart();
        Print("waitUntilElementExists MainMenu ");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 10000);
    }

    @After
    public void tearDown() throws Exception {
        //SignOut();
        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
        Print(String.valueOf((elapsedTimeCurrentTest/1000F)/60 + " min / "
                + String.valueOf(elapsedTimeCurrentTest/1000F) + " sec / "
                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n"));
    }

    public void InitSetUP() throws GeneralLeanFtException, InterruptedException {
        //change the setting of the server
        setting();
        //create a new user for testing if not exists
        CreateNewUser(false);
    }

    public void setting() throws GeneralLeanFtException, InterruptedException {
        Print("setting() start");
        if (appModel.AdvantageShoppingApplication().ServerNotReachableLabel().exists(5)) {
            Print("tap OKButton()");
            appModel.AdvantageShoppingApplication().OKButton().tap();
            appModel.AdvantageShoppingApplication().EditTextServer().setText(appURL);

            Print("tap ConnectButton()");
            appModel.AdvantageShoppingApplication().ConnectButton().tap();
            Print("sleep 10000");
            Thread.sleep(10000);

            Print("waitUntilElementExists ButtonPanelSettingUiObject");
            waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject(), 5000);

            appModel.AdvantageShoppingApplication().OKButton().tap();
        } else {
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
            Print("waitUntilElementExists MainMenu");
            boolean isMainMenuExist = waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);

            Print("tap MainMenu()");
            appModel.AdvantageShoppingApplication().MainMenu().tap();
            Print("tap SETTINGSLabel()");
            appModel.AdvantageShoppingApplication().SETTINGSLabel().tap();

            String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();

//            if (!server.equals(appURL)) { // check if the setting already set up
                appModel.AdvantageShoppingApplication().EditTextServer().setText(appURL);

                Print("tap ConnectButton()");
                appModel.AdvantageShoppingApplication().ConnectButton().tap();

                Print("waitUntilElementExists ButtonPanelSettingUiObject");
                waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject(), 10000);

                Print("tap OKButton()");
                appModel.AdvantageShoppingApplication().OKButton().tap();
//            }
        }
        app.restart();
        Print("setting() end");
    }

    public void CreateNewUser(boolean isTest) throws GeneralLeanFtException, InterruptedException {
        Print("CreateNewUser(" + isTest + ") start");
        // create new user if not exists to run all tests
        if (!isTest) { // create a new user
            if (!SignIn(false)) {
                appModel.AdvantageShoppingApplication().DonTHaveAnAccount().tap();
                //appModel.AdvantageShoppingApplication().SignUp().tap();

                Print("waitUntilElementExists SignUpObject");
                waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject(), 5000);

                //Set up private details
                appModel.AdvantageShoppingApplication().UserNameSignUp().setText(UNAME);
                appModel.AdvantageShoppingApplication().EmailSignUp().setText(UNAME + "@default.com");
                appModel.AdvantageShoppingApplication().PasswordSignUp().setText(PASS);
                appModel.AdvantageShoppingApplication().ConfirmPassSignUp().setText(PASS);
                device.swipe(SwipeDirection.UP);
                device.swipe(SwipeDirection.UP);

                //set up address details
                appModel.AdvantageShoppingApplication().AddressSignUpEditField().setText("Altalef 5");
                appModel.AdvantageShoppingApplication().CitySignUpEditField().setText("Yahud");
                appModel.AdvantageShoppingApplication().ZIPSignUpEditField().setText("454545");

                appModel.AdvantageShoppingApplication().REGISTERButton().tap();
                Print("waitUntilElementExists AdvantageObjectUiObject");
                waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject(), 5000);

                Verification(Verify.isTrue(SignIn(true), "New User creation", "verify that the creation of new user for testing  succeeds"));
            }
        } else { // create existing user test
            SignOut();
            Print("waitUntilElementExists MainMenu");
            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
            appModel.AdvantageShoppingApplication().MainMenu().tap();
            appModel.AdvantageShoppingApplication().Login().tap();

            //appModel.AdvantageShoppingApplication().SignUp().tap();
            appModel.AdvantageShoppingApplication().DonTHaveAnAccount().tap();

            Print("waitUntilElementExists SignUpObject");
            waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject(), 5000);

            appModel.AdvantageShoppingApplication().UserNameSignUp().setText(UNAME);
            appModel.AdvantageShoppingApplication().EmailSignUp().setText(UNAME + "@default.com");
            appModel.AdvantageShoppingApplication().PasswordSignUp().setText(PASS);
            appModel.AdvantageShoppingApplication().ConfirmPassSignUp().setText(PASS);
            device.swipe(SwipeDirection.UP);
            device.swipe(SwipeDirection.UP);

            appModel.AdvantageShoppingApplication().REGISTERButton().tap();
            Print("waitUntilElementExists AdvantageObjectUiObject");
            waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject(), 5000);

            Verification(Verify.isFalse(SignIn(true), "Existing new User creation", "verify that the creation of Existing user NOT succeed"));
        }
        Print("CreateNewUser(" + isTest + ") end");
    }

    /////////////////////////////////////  Start of tests  //////////////////////////////////////////////////////

    @Test
    public void AddNewUserAndCheckInitials() throws GeneralLeanFtException, InterruptedException {
        Print("---------- START AddNewUserAndCheckInitials ----------");
        InitSetUP();
        Print("---------- END AddNewUserAndCheckInitials ------------");
    }

    @Test
    public void SilentLoginTest() throws GeneralLeanFtException, InterruptedException {
        Print("---------- START SilentLoginTest ----------");
        SignOut();
        SignIn(false);
        app.launch();
        Verify.isTrue(SignIn(true), "Verification - Sign In", "Verify that the user " + UNAME + " In still sign in.");
        Print("---------- END SilentLoginTest ------------");
    }

    @Test
    public void CreateExsitingUserTest() throws GeneralLeanFtException, InterruptedException {
        Print("---------- START CreateExsitingUserTest ----------");
        CreateNewUser(true);
        Print("---------- END CreateExsitingUserTest ------------");
    }

    @Test
    public void SignOutTest() throws GeneralLeanFtException, InterruptedException {
        //Perform logout and make sure you are not logged in: in the menu, the option is to login .
        //In Method "SignIn() we make all the validation in the user are logged in- here we use this "
        Print("---------- START SignOutTest ----------");
        if (!SignIn(true))
            SignIn(false);
        SignOut();
        Verify.isFalse(SignIn(true), "Verification - Sign Out", "Verify that the user sign out correctly");
        Print("---------- END SignOutTest ------------");
    }

    @Test
    public void NegativeLogin() throws GeneralLeanFtException, InterruptedException {
        /*
    	 Try to login with incorrect credentials
 		Verify that correct message appears
    	*/
        Print("---------- START NegativeLogin ----------");
        if (SignIn(true))
            SignOut();

        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().Login().tap();
        appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
        appModel.AdvantageShoppingApplication().PassEdit().setText("some pass");
        appModel.AdvantageShoppingApplication().LOGINButton().tap();
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists(), "Verification - Negative Login", "Verify that the user NOT login with incorrect password"));
        Print("---------- END NegativeLogin ------------");
    }

    @Test
    public void UpdateCartTest() throws GeneralLeanFtException, InterruptedException {
    	/*			1.���Open app
					2.���Login if not logged in
					3.���Select Tablets tile
					4.���Select a Tablet
					5.���Add +1 to quantity
					6.���Click on Add to Cart
					7.���Click on Cart
					8.���swipe to Edit
					9.���Change amount and color
					10.� click on add to cart
					11.� Open cart menu and validate that the cart was updated correctly (the old color is not there�..)
					12.� click checkout
					13.� Select SafePay as Payment method
					14.� Click Pay Now
					15.� Verify receipt 
    	 */
        Print("---------- START UpdateCartTest ----------");
        SignIn(false);

        //buy a leptop item
        BuyLaptop();
        //goes to cart and edit details

        Print("waitUntilElementExists CartAccess");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();
        appModel.AdvantageShoppingApplication().FirstCartItem().tap();

        //change color and amount
        Print("waitUntilElementExists ProductColor");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 5000);

        appModel.AdvantageShoppingApplication().ProductColor().tap();
        appModel.AdvantageShoppingApplication().colorObject().tap();

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("3");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

        Print("waitUntilElementExists ProductDetail");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail(), 5000);
        appModel.AdvantageShoppingApplication().UPDATEPRODUCTButton().tap();
        Print("waitUntilElementExists MainMenu");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);

        device.back();

        Print("waitUntilElementExists CartAccess");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();
        CheckOut("Sefepay");
        Print("---------- END UpdateCartTest ------------");
    }

    @Test
    public void PurchaseHugeQuantityTest() throws GeneralLeanFtException, InterruptedException {
    	/*
    	 * �Login
���� 		Select Speakers tile
����� 		Select Manufacture filter � HP
�� 			Select a Speaker
����� 		Change Color
�� 			add +1000 speakers
��� 		Click on Add to Cart
���� 		Check quantity message warning
 			Open cart menu and click checkout
� 			Select SafePay as Payment method
� 			Fill in SafePay user & pass
� 			Un-Check �Save changes in profile for future use�
� 			Click Pay Now
�			Verify receipt
� 			Validate safePay details didn�t changed (via my account)
    	 */
        Print("---------- START PurchaseHugeQuantityTest ----------");

        SignIn(false);

        EmptyCart();

        //make  a filter

        Print("waitUntilElementExists MainMenu");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().SPEAKERSLabel().tap();
        Print("waitUntilElementExists tabletItem");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem(), 5000);
        appModel.AdvantageShoppingApplication().ImageViewFilter().tap();
        appModel.AdvantageShoppingApplication().BYMANUFACTURERLabel().tap();
        appModel.AdvantageShoppingApplication().HPLabel().tap();
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

        //choose item and change his color

        Print("waitUntilElementExists tabletItem");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem(), 5000);
        appModel.AdvantageShoppingApplication().tabletItem().tap();
        Print("waitUntilElementExists ProductColor");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 5000);
        appModel.AdvantageShoppingApplication().ProductColor().tap();
        appModel.AdvantageShoppingApplication().colorObject().tap();

        //set quantity

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("1000");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

        /// need to verify an error msg - not support
        Print("waitUntilElementExists ProductDetail");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail(), 5000);
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

        Print("waitUntilElementExists CartAccess");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();

        CheckOut("Sefepay"); // use safepay
        Print("---------- END PurchaseHugeQuantityTest ------------");
    }

    @Test
    public void OutOfStockTest() throws GeneralLeanFtException {
        Print("---------- START OutOfStockTest ----------");
        Print("waitUntilElementExist: MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);

        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().HEADPHONESLabel().tap();
        Print("waitUntilElementExist: MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        appModel.AdvantageShoppingApplication().SOLDout().tap();
    	
    	/*
    	 * verify that we can change color - in web the user can edit the color but here we chack that 
    	 * in not an option
    	 */

        // all the verification are not working because the attribute "isEnabled" are not include in this version of the app.
        //Verify.isFalse(appModel.AdvantageShoppingApplication().ProductColor().isEnabled(),"Verification - Out Of Stock", "Verify that we can't change color.");

        //verify that we can't change quantity or add to cart
        Verify.isFalse(appModel.AdvantageShoppingApplication().ProductQuantity().isClickable(), "Verification - Out Of Stock", "Verify that we can't change quantity.");
        Verify.isFalse(appModel.AdvantageShoppingApplication().ADDTOCARTButton().isClickable(), "Verification - Out Of Stock", "Verify that we can't ADD TO CART.");
        Print("---------- END OutOfStockTest ------------");
    }

    @Test
    public void PayMasterCreditTest() throws GeneralLeanFtException, InterruptedException {
/*    	    Login if not logged in
���         Select Laptops category
����        Select Operating system filter - Win 10
����� 		Select a laptop
���� 		Select another laptop
���� 		Change color
��� 		Click Add to Cart
���� 		Open cart menu
� 			Click 'Edit shipping details'
� 			Change shipping �postal code�
�		    Un-Check �Save changes in profile for future use�(?)
� 			Click next
� 			Select MasterCredit as Payment method
�		    Insert all card details
� 			Un-Check �Save changes in profile for future use�
�		    Click Pay Now
� 			Verify receipt
� 			Validate shipping details didn�t changed (via my account)
�			 Validate MasterCredit details didn�t changed (via my account)
*/
        Print("---------- START PayMasterCreditTest ----------");

        SignIn(false);

        Print("sleep 2000");
        Thread.sleep(2000);
        Print("tap MainMenu()");
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        Print("sleep 2000");
        Thread.sleep(2000);
        Print("tap LAPTOPSLabel()");
        appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();
        Print("sleep 15000");
        Thread.sleep(15000);
        Print("tap ImageViewFilter()");
        appModel.AdvantageShoppingApplication().ImageViewFilter().tap();
        Print("sleep 4000");
        Thread.sleep(4000);

        //apply filter- Win 10
        Print("tap BYOPERATINGSYSTEMLabel()");
        appModel.AdvantageShoppingApplication().BYOPERATINGSYSTEMLabel().tap();
        Print("sleep 2000");
        Thread.sleep(2000);
        appModel.AdvantageShoppingApplication().Windows10Label().tap();

        Print("tap APPLYChangeLabel()");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
        Print("tap LaptopitemWin10()");
        appModel.AdvantageShoppingApplication().LaptopitemWin10().tap();

        Print("waitUntilElementExists ProductColor");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 5000);
        Print("tap ProductColor()");
        appModel.AdvantageShoppingApplication().ProductColor().tap();
        Print("tap colorObject()");
        appModel.AdvantageShoppingApplication().colorObject().tap();
        Print("tap ADDTOCARTButton");
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

        //check out and pay with master credit the card number nedded to 12 digits
        Print("waitUntilElementExists CartAccess");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        Print("tap CartAccess()");
        appModel.AdvantageShoppingApplication().CartAccess().tap();

        CheckOut("MasterCredit");
        Print("---------- END PayMasterCreditTest ------------");
    }

    /*@Test
    public void MobileWebTest() throws GeneralLeanFtException, InterruptedException{
    	

    	waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());

    	MobileWeb mobileWeb = new MobileWeb("05157df581dae805",appURL);

    	mobileWeb.PurchaseTest(UNAME , PASS);

    	
    }*/

    @Test
    public void PayButtonRegExTest() throws GeneralLeanFtException, InterruptedException {
        //The button text always starts with Pay to allow for adding regular expressions in object identification.

        //In  web the button calls "CHECKOUT ({{RegEx}})"
        Print("---------- START PayButtonRegExTest ----------");

        String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        SignIn(false);
        BuyLaptop();

        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();

        waitUntilElementExists(appModel.AdvantageShoppingApplication().FirstCartItem(), 5000);

        String innerTxt = appModel.AdvantageShoppingApplication().CHECKOUT().getText();

        Matcher m = r.matcher(innerTxt);
        boolean match = m.find();
        System.out.println("PayButtonRegExTest- " + innerTxt + " :: " + match);

        Verify.isTrue(match, "Verification - Verify CHECKOUT RegEx", "Verify that the text in CHECKOUT button start with 'CHECKOUT ({{RegEx}})' .");
        Print("---------- END PayButtonRegExTest ------------");
    }

    @Test
    public void ChangePasswordTest() throws GeneralLeanFtException, InterruptedException {
    	/*
 		Login
		Click setting
		Click change password
		Change the password
		Logout and login again with the new password
    	 */
    	Print("---------- START ChangePasswordTest ----------");
        String Oldpass = PASS;

        if (SignIn(true))
            SignOut();

        //step 1 - change to new pass
        Print("---------- ChangePasswordTest step 1 ----------");
        changepassword("Password23");
        Verification(Verify.isTrue(SignIn(false), "Verification - Change Password step 1 - change to new pass", "Verify that the user login with the new password"));

        //step 2 -  change back to the default pass
        Print("---------- ChangePasswordTest step 2 ----------");
        changepassword(Oldpass);
        Verification(Verify.isTrue(SignIn(false), "Verification - Change Password step 2 -  change back to the default pass", "Verify that the user login with the new password"));
        Print("---------- END ChangePasswordTest -------------");
    }

    // TODO: Finish this test
//    @Test
    public void OfflineModeTest() throws GeneralLeanFtException, InterruptedException {
        //todo: IMPORTANT - switch device to airplane mode or turn off the network before you RUN this test
        /*
        change to airplane mode
        validate that:  
        The indication should be in settings: if the user opens the app w/o internet access, the message should be:
        "You are not connected to the internet", and when the user clicks OK, 
        the url should say: Offline Mode, 
        the "connect" should change to "apply" and the message "connection successful" should be replaced with: "
        Note that in offline mode nothing you do will be saved for future use"



		check that there is offline mode indication in the login page
        check that create account is not available - clicking on it pops up a message "not applicable in offline mode"
        Allow the user to login without any real authentication,
        try to edit the user account - validate that you get the message: "not applicable in offline mode"

        add products to the cart and then checkout without any validation.
        check that the offline mode indication exists in the cart page
         */
        offlinemode(true);   // switch device to airplane mode
        InitBeforeclassLocal();
        Verify.isTrue(appModel.AdvantageShoppingApplication().YouAreNotConnectedToLabel().exists(), "Verification - not connected to internet", "Verify that the offline massage appears");
        appModel.AdvantageShoppingApplication().OKButton().tap();

        String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();
        Verify.isTrue(server.equals("Offline Mode"), "Verification - server massage", "Verify that the offline massage appears in the server setting");
        appModel.AdvantageShoppingApplication().ConnectButton().tap();
        Print("sleep 2000");
        Thread.sleep(2000);

        Verify.isTrue(appModel.AdvantageShoppingApplication().NoteOfflineModLabel().exists(), "Verification - offline mode note massage", "Verify that the note for offline mode appears");
        appModel.AdvantageShoppingApplication().OKButton().tap();

        // TODO: add all functions that contains OfflineMode
    }

    public void SigninOfflineMode() throws GeneralLeanFtException {
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().Login().tap();

        //validation for offline warning
        Verify.isTrue(appModel.AdvantageShoppingApplication().WarningMessageUiObject().exists(), "Verification - offline mode login warning", "Verify that the warning  for offline mode appears in login");

        ///validation for create account is not available
        appModel.AdvantageShoppingApplication().DonTHaveAnAccount().tap();
        Verify.isFalse(appModel.AdvantageShoppingApplication().SignUpObject().exists(), "Verification - sign up offline mode", "Verify that we can create new user in offline mode");

        Verify.isTrue(appModel.AdvantageShoppingApplication().WarningMessageUiObject().exists(), "Verification - offline mode login warning", "Verify that the warning  for offline mode appears in login");

        appModel.AdvantageShoppingApplication().UserNameEdit().setText("offline"); //invalid user name & password - should work
        appModel.AdvantageShoppingApplication().PassEdit().setText("offline");
        appModel.AdvantageShoppingApplication().LOGINButton().tap();

        //validate that the Offline user appears
        appModel.AdvantageShoppingApplication().MainMenu().tap();

        String innerTxt = appModel.AdvantageShoppingApplication().LinearLayoutLogin().getVisibleText();

        Verify.isTrue(innerTxt.equals("Brown John"), "Verification - offline login", "Verify that we logged in in offline mode");
    }

    public void BuyAndCheckOutOfflineMode() throws GeneralLeanFtException, InterruptedException {
        //do a check out without any edit or changing
        BuyLaptop();

        appModel.AdvantageShoppingApplication().CartAccess().tap();
        appModel.AdvantageShoppingApplication().CHECKOUT().tap();
        Print("sleep 2000");
        Thread.sleep(2000);

        appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
        Print("sleep 3000");
        Thread.sleep(3000);
        waitUntilElementExists(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject(), 5000);
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(4), "Verify- purchase success offline mode", " verify that the payment success and we receive the order detail window (offline)"));
        appModel.AdvantageShoppingApplication().CloseDialog().tap();
    }

    /////////////////////////////////////  End of tests  //////////////////////////////////////////////////////

    public void changepassword(String newpass) throws GeneralLeanFtException, InterruptedException {
        SignIn(false);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().AccountDetails().tap();
        Print("sleep 2000");
        Thread.sleep(2000);
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ChangePasswordObject(), 5000);

        //change to another password
        appModel.AdvantageShoppingApplication().ChangePasswordLabel().tap();
        appModel.AdvantageShoppingApplication().OldPassEditField().setText(PASS);
        appModel.AdvantageShoppingApplication().NewPassEditField().setText(newpass);
        appModel.AdvantageShoppingApplication().ConfirmNewPassEditField().setText(newpass);
        device.swipe(SwipeDirection.UP);
        device.swipe(SwipeDirection.UP);
        appModel.AdvantageShoppingApplication().UPDATEAccountButton().tap();

        PASS = newpass;
        SignOut();
    }

    public boolean SignIn(Boolean quiet) throws GeneralLeanFtException, InterruptedException {
        Print("SignIn() start");
        Print("waitUntilElementExists MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 10000);
        Print("tap MainMenu()");
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        String innerTxt = appModel.AdvantageShoppingApplication().LinearLayoutLogin().getVisibleText();
        //System.out.println(appModel.AdvantageShoppingApplication().AccountDetails().exists(2))

        if (innerTxt.equals("LOGIN")) {
            if (!quiet) {
                Print("tap Login()");
                appModel.AdvantageShoppingApplication().Login().tap();
                appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
                appModel.AdvantageShoppingApplication().PassEdit().setText(PASS);
                Print("tap LOGINButton");
                appModel.AdvantageShoppingApplication().LOGINButton().tap();

                if (!appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists()) {
                    System.out.println(UNAME + "  Login Success");
                    Verify.isTrue(true, "Verification - Sign In", "Verify that the user " + UNAME + " signed in properly.");
                    Print("SignIn() end");
                    return true;
                }
                Print("SignIn() end");
                return false;
            }
            Print("SignIn() end");
            return false;
        }

        System.out.println(UNAME + " allready logged in");
        device.back();
        Print("waitUntilElementExists MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        Print("SignIn() end");
        return true;
    }

    public void SignOut() throws GeneralLeanFtException, InterruptedException {
        Print("SignOut() start");
        if (SignIn(true)) {
            appModel.AdvantageShoppingApplication().MainMenu().tap();
            appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();
            appModel.AdvantageShoppingApplication().YESButton().tap();
            System.out.println(UNAME + "Signed Out in success");
        }
        Print("SignOut() end");
    }

    public void BuyLaptop() throws GeneralLeanFtException, InterruptedException {
        Print("BuyLaptop() start");
        EmptyCart();
        Print("waitUntilElementExists MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 10000);
        Print("tap MainMenu");
        appModel.AdvantageShoppingApplication().MainMenu().tap();

        Print("tap LAPTOPSLabel");
        appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();
        Print("tap LaptopitemWin10");
        appModel.AdvantageShoppingApplication().LaptopitemWin10().tap();
        Print("waitUntilElementExists ProductColor()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 10000);
        Print("sleep 5000");
        Thread.sleep(5000);
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();
        Print("BuyLaptop() end");
    }

    public void EditShipping() throws GeneralLeanFtException, InterruptedException {
        Print("waitUntilElementExists EditShippingUiObject()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().EditShippingUiObject(), 5000);
        appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();
    }

    public void CheckOut(String payment) throws GeneralLeanFtException, InterruptedException {
        Print("CheckOut(" + payment + ") start");
        Print("tap CHECKOUT()");
        appModel.AdvantageShoppingApplication().CHECKOUT().tap();
        Print("sleep 25000");
        Thread.sleep(25000);

        Print("waitUntilElementExists MainMenu()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);

        //pay with safepay and don't save details
        if (payment.equals("Sefepay"))
            SafePay(false);
        else
            MasterCredit("123456789123", "456", UNAME, false, true);

        Print("sleep 10000");
        Thread.sleep(10000);
        Print("tap PAYNOWButton()");
        appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
        Print("sleep 10000");
        Thread.sleep(10000);
        Print("waitUntilElementExists VerifyReceiptWindowUiObject()");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject(), 5000);
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(4), "Verify- purchase success with " + payment, " verify that the payment success and we receive the order detail window"));
        Print("sleep 2000");
        Thread.sleep(2000);
        Print("tap CloseDialog()");
        appModel.AdvantageShoppingApplication().CloseDialog().tap();
        Print("CheckOut(" + payment + ") end");
    }

    public void SafePay(boolean save) throws GeneralLeanFtException {
        Print("SafePay(" + save + ") start");
        //start with edit Safepay details

        waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails(), 5000);

        appModel.AdvantageShoppingApplication().PaymentDetails().tap();
        appModel.AdvantageShoppingApplication().PaymentDetails().tap();
        appModel.AdvantageShoppingApplication().ImageViewSafePay().tap();
        appModel.AdvantageShoppingApplication().SafePayUserfieldEditField().setText(UNAME);
        appModel.AdvantageShoppingApplication().SafePayPassFieldEditField().setText(PASS);

        if (!save) {
            //by default the checkbox is checked , tap on it - don't save the details
            appModel.AdvantageShoppingApplication().SaveSafePayCredentialsCheckBox().tap();
        }

        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
        Print("SafePay(" + save + ") end");
    }

    public void MasterCredit(String cardnum, String CVV, String HolderName, boolean savedetails, boolean changeShipping) throws GeneralLeanFtException, InterruptedException {
        Print("MasterCredit() start");
        Print("waitUntilElementExists PaymentDetails");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails(), 10000);
        //Print("sleep 10000");
        //Thread.sleep(10000);

        Print("changeShipping = " + changeShipping);
        if (changeShipping) {
            Print("tap EditShippingUiObject()");
            appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();
            appModel.AdvantageShoppingApplication().ZIPshippingDetaildEditField().setText("12345");
            Print("tap ShippingCheckBox()");
            appModel.AdvantageShoppingApplication().ShippingCheckBox().tap();
            Print("tap APPLYChangeLabel()");
            appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
        }
        Print("tap PaymentDetails()");
        appModel.AdvantageShoppingApplication().PaymentDetails().tap();
        Print("tap ImageViewMasterCredit()");
        appModel.AdvantageShoppingApplication().ImageViewMasterCredit().tap();

        //set the details

        appModel.AdvantageShoppingApplication().CardNumderMasterCreditEditField().setText(cardnum);
        appModel.AdvantageShoppingApplication().CardNumderMasterCreditEditField().setText(cardnum);
        appModel.AdvantageShoppingApplication().CVVMasterCreditEditField().setText(CVV);
        appModel.AdvantageShoppingApplication().CardHolderMasterCreditEditField().setText(HolderName);

//        Print("if (!savedetails) = " + !savedetails);
//        if (!savedetails) { // by default it save the details
//            Print("tap SaveMasterCreditCredenCheckBox()");
//            appModel.AdvantageShoppingApplication().SaveMasterCreditCredenCheckBox().tap();
//        }

        Print("tap APPLYChangeLabel()");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();
        Print("MasterCredit() end");
    }

    public void EmptyCart() throws NumberFormatException, GeneralLeanFtException, InterruptedException {
        Print("EmptyCart() start");
        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);

        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().CARTLabel().tap();
        while (appModel.AdvantageShoppingApplication().FirstCartItem().exists(2)) {
            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
            appModel.AdvantageShoppingApplication().FirstCartItem().swipe(SwipeDirection.RIGHT);
            //Thread.sleep(1000);
            appModel.AdvantageShoppingApplication().CartRemove().tap();
        }
        device.back();
        Print("EmptyCart() end");
    }

    public void PurchaseTablet(String quantity) throws GeneralLeanFtException, NumberFormatException, InterruptedException {
        EmptyCart();
        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().TABLETSLabel().tap();
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductQuantity(), 5000);

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText(quantity);
    }

    public boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException {
        return WaitUntilTestObjectState.waitUntil(appElem, new WaitUntilEvaluator<UiObject>() {
            public boolean evaluate(UiObject we) {
                try {
                    return we.exists();
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public boolean waitUntilElementExists(UiObject appElem, long time) throws GeneralLeanFtException {
        return WaitUntilTestObjectState.waitUntil(appElem, new WaitUntilEvaluator<UiObject>() {
            public boolean evaluate(UiObject we) {
                try {
                    return we.exists();
                } catch (Exception e) {
                    return false;
                }
            }
        }, time);
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public static void InitBeforeclass() throws GeneralLeanFtException {
        String deviceID = "";
        for (DeviceInfo deviceInfo : MobileLab.getDeviceList()) {
            //System.out.printf("The device ID is: %s, and its name is: %s\n\n", deviceInfo.getId(), deviceInfo.getName());
            String[] s = deviceInfo.getOSVersion().split("\\.");
            String Join = "";
            for (String s1 : s)
                Join += s1;

            int version = Integer.parseInt(Join);
            if (deviceInfo.getOSType().equals("ANDROID") && version >= 600) {
                deviceID = deviceInfo.getId();
                break;
            }
        }

        device = MobileLab.lockDeviceById(deviceID);// ID For galaxy S6

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build());

        //connect between the appModel and the device
        appModel = new AdvantageAndroidApp(device);
        app.install();
    }

    //use this in local testing

    public static void InitBeforeclassLocal() throws GeneralLeanFtException {
        device = MobileLab.lockDeviceById("05157df581dae805");// ID For galaxy S6
        //device = MobileLab.lockDeviceById("06157df623745934");// ID For galaxy S6

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build());

        //connect between the appModel and the device
        appModel = new AdvantageAndroidApp(device);

        //app.install();
    }

    private static void Print(String msg) {
        System.out.println(msg);
    }

    // TODO: refactor for Android 7
    public void offlinemode(boolean mode) throws GeneralLeanFtException {
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("MC.Settings").packaged(false).build());
        app.launch();

        appModel.SettingsApplication().AirplaneMode().tap();
        appModel.SettingsApplication().AirplaineToggle().set(mode);
    }

}