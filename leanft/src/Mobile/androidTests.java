package Mobile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.internal.mobile.MobileUiObjectBase;
import org.junit.*;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.mobile.*;
import com.hp.lft.verifications.*;

import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import unittesting.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class androidTests extends UnitTestClassBase {

    static AdvantageAndroidApp appModel;
    protected static Device device;
    protected static Application app;
    protected static Application appSettings;

    static String UNAME = "androidUser3";
    static String PASS = "Password1";
    static String PASSNEW = "Password23";

    static String appURL = System.getProperty("url", "defaultvalue");
//    static String appURL2 = "www.advantageonlineshopping.com";
//    static String appURL2 = "http://52.34.90.37";      // STAGING NEW
    //"52.88.236.171"; //"35.162.69.22:8080";//
    static String appURL2 = "16.60.158.84";       // CI
//    static String appURL2 = "16.59.19.163:8080";       // DEV localhost
//    static String appURL2 = "52.32.172.3:8080";

    private static int currentNumberOfTest = 0;
    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public androidTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        startTimeAllTests = System.currentTimeMillis();
        instance = new androidTests();
        globalSetup(androidTests.class);

        if (appURL.substring(0,7).equals("http://"))
            appURL = appURL.substring(7);


        if (appURL.equals("defaultvalue")) {
            appURL = appURL2;
            if (appURL.substring(0,7).equals("http://"))
                appURL = appURL.substring(7);
            InitBeforeclassLocal();
        } else
            InitBeforeclass();

        Print("appURL: " + appURL);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        device.unlock();
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        Print("\nandroidTests done in: " + String.valueOf((elapsedTimeAllTests/1000F)/60 + " min"));
        printTimeWholeTests(elapsedTimeAllTests);
    }

    @Before
    public void setUp() throws Exception {
        startTimeCurrentTest = System.currentTimeMillis();
        printCaptionTest(curTestName.getMethodName(), ++currentNumberOfTest);
        Print("restarting application...");
        app.restart();
    }

    @After
    public void tearDown() throws Exception {
        //SignOut();
        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
        String passingTime = String.valueOf((elapsedTimeCurrentTest/1000F)/60 + " min / "
                + String.valueOf(elapsedTimeCurrentTest/1000F) + " sec / "
                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n");
        printEndOfTest(curTestName.getMethodName(), passingTime);
    }

//    public void InitSetUP() throws GeneralLeanFtException, InterruptedException {
//        //change the setting of the server
//        setting();
//        //create a new user for testing if not exists
//        CreateNewUser(false);
//    }

    public void setting() throws GeneralLeanFtException {
        Print("setting() start");
        if (appModel.AdvantageShoppingApplication().ServerNotReachableLabel().exists(5)) {
            tapUiObjectButton(appModel.AdvantageShoppingApplication().OKButton());
            setTextEditField(appModel.AdvantageShoppingApplication().EditTextServer(), appURL);
            tapUiObjectButton(appModel.AdvantageShoppingApplication().ConnectButton());
            threadSleep(10000);
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject(), 5000);
            tapUiObjectButton(appModel.AdvantageShoppingApplication().OKButton());
        } else {
//            boolean isMainMenuExist = waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
            threadSleep(5000);
            tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
            tapUiObjectLabel(appModel.AdvantageShoppingApplication().SETTINGSLabel());

//            String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();

//            if (!server.equals(appURL)) { // check if the setting already set up
                setTextEditField(appModel.AdvantageShoppingApplication().EditTextServer(), appURL);
                tapUiObjectButton(appModel.AdvantageShoppingApplication().ConnectButton());
//                waitUntilElementExists(appModel.AdvantageShoppingApplication().ButtonPanelSettingUiObject(), 10000);
                threadSleep(10000);
                tapUiObjectButton(appModel.AdvantageShoppingApplication().OKButton());
//            }
        }
        app.restart();
        Print("setting() end");
    }

    public void CreateNewUser(boolean isTest) throws GeneralLeanFtException {
        Print("\nCreateNewUser (" + !isTest + ") start");
        // TODO: can be created function with the same code or just refactor
        // create new user if not exists to run all tests
        if (!isTest) { // create a new user
            if (!SignIn(false)) {
//            if (!isSignedIn()) {
                tapUiObjectLabel(appModel.AdvantageShoppingApplication().DonTHaveAnAccount());
                //appModel.AdvantageShoppingApplication().SignUp().tap();

//                waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject(), 5000);
                threadSleep(5000);

                //Set up private details
                setTextEditField(appModel.AdvantageShoppingApplication().UserNameSignUp(), UNAME);
                setTextEditField(appModel.AdvantageShoppingApplication().EmailSignUp(), UNAME + "@default.com");
                setTextEditField(appModel.AdvantageShoppingApplication().PasswordSignUp(), PASS);
                setTextEditField(appModel.AdvantageShoppingApplication().ConfirmPassSignUp(), PASS);
                deviceSwipe(SwipeDirection.UP);
                deviceSwipe(SwipeDirection.UP);

                //set up address details
                setTextEditField(appModel.AdvantageShoppingApplication().AddressSignUpEditField(), "Altalef 5");
                setTextEditField(appModel.AdvantageShoppingApplication().CitySignUpEditField(), "Yahud");
                setTextEditField(appModel.AdvantageShoppingApplication().ZIPSignUpEditField(), "454545");

                tapUiObjectButton(appModel.AdvantageShoppingApplication().REGISTERButton());
//                waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject(), 5000);
                threadSleep(5000);

//                Boolean isSignedIn = SignIn(true);
                Boolean isSignedIn = isSignedIn();
                Print("isSignedIn " + isSignedIn);
                Verification(Verify.isTrue(isSignedIn, "New User creation", "verify that the creation of new user for testing  succeeds"));
            }
        } else { // create existing user test
            if (isSignedIn())
                SignOut();
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
            threadSleep(5000);
            tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
            tapUiObjectLabel(appModel.AdvantageShoppingApplication().Login());

            //appModel.AdvantageShoppingApplication().SignUp().tap();
            tapUiObjectLabel(appModel.AdvantageShoppingApplication().DonTHaveAnAccount());

//            waitUntilElementExists(appModel.AdvantageShoppingApplication().SignUpObject(), 5000);
            threadSleep(5000);

            setTextEditField(appModel.AdvantageShoppingApplication().UserNameSignUp(), UNAME);
            setTextEditField(appModel.AdvantageShoppingApplication().EmailSignUp(), UNAME + "@default.com");
            setTextEditField(appModel.AdvantageShoppingApplication().PasswordSignUp(), PASS);
            setTextEditField(appModel.AdvantageShoppingApplication().ConfirmPassSignUp(), PASS);

            deviceSwipe(SwipeDirection.UP);
            deviceSwipe(SwipeDirection.UP);

            tapUiObjectButton(appModel.AdvantageShoppingApplication().REGISTERButton());
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().AdvantageObjectUiObject(), 5000);
            threadSleep(5000);

            Boolean isSignedIn = SignIn(true);
            Print("isSignedIn " + isSignedIn);
            Verification(Verify.isFalse(isSignedIn, "Existing new User creation", "verify that the creation of Existing user NOT succeed"));
        }
        Print("CreateNewUser (" + !isTest + ") end");
    }

//    @Test
//    public void testConnection() throws GeneralLeanFtException, InterruptedException {
//        setting();
//    }

    @Test
    public void AddNewUserAndCheckInitials() throws GeneralLeanFtException, InterruptedException {
//        InitSetUP();
        //change the setting of the server
        setting();

        // If some user already signed in do sign out
        if (isSignedIn())
            SignOut();

        //create a new user for testing if not exists
        CreateNewUser(false);
    }

    @Test
    public void SilentLoginTest() throws GeneralLeanFtException, InterruptedException {
        if (isSignedIn())
            SignOut();
        SignIn(false);
        app.launch();
        Verify.isTrue(SignIn(true), "Verification - Sign In", "Verify that the user " + UNAME + " In still sign in.");
        SignOut();
    }

    @Test
    public void CreateExistingUserTest() throws GeneralLeanFtException, InterruptedException {
        if (isSignedIn())
            SignOut();
        CreateNewUser(true);
    }

    /**
     * Perform logout and make sure you are not logged in
     * @throws GeneralLeanFtException
     */
    @Test
    public void SignOutTest() {
        // Check if any user already signed in. If any, do sign in
        if (!isSignedIn())
            SignIn(false);

        // Do sign out to signed in user
        SignOut();

        // Verifying if any user signed in
        Verify.isFalse(isSignedIn(), "Verification - Sign Out", "Verify that the user sign out");
    }

    @Test
    public void NegativeLogin() throws GeneralLeanFtException, InterruptedException {
        /*
    	 Try to login with incorrect credentials
 		Verify that correct message appears
    	*/
//        if (SignIn(true))
        if (isSignedIn())
            SignOut();

        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().Login().tap();
        appModel.AdvantageShoppingApplication().UserNameEdit().setText(UNAME);
        appModel.AdvantageShoppingApplication().PassEdit().setText("some pass");
        appModel.AdvantageShoppingApplication().LOGINButton().tap();
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().InvalidUserNameOrPas().exists(), "Verification - Negative Login", "Verify that the user NOT login with incorrect password"));
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
    	if (isSignedIn())
            SignOut();

        SignIn(false);

        //buy a leptop item
        BuyLaptop();
        //goes to cart and edit details

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();
        appModel.AdvantageShoppingApplication().FirstCartItem().tap();

        //change color and amount
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 5000);
        threadSleep(5000);

        appModel.AdvantageShoppingApplication().ProductColor().tap();
        appModel.AdvantageShoppingApplication().colorObject().tap();

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("3");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().UPDATEPRODUCTButton().tap();
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        threadSleep(5000);

        device.back();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();
        CheckOut("Sefepay");
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

    	if (isSignedIn())
    	    SignOut();

        SignIn(false);

        EmptyCart();

        //make  a filter
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().SPEAKERSLabel().tap();
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem());
        threadSleep(10000);
        appModel.AdvantageShoppingApplication().ImageViewFilter().tap();
        appModel.AdvantageShoppingApplication().BYMANUFACTURERLabel().tap();
        appModel.AdvantageShoppingApplication().HPLabel().tap();
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

        //choose item and change his color
        threadSleep(4000);
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().tabletItem());
        appModel.AdvantageShoppingApplication().tabletItem().tap();
        threadSleep(10000);
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor());
        appModel.AdvantageShoppingApplication().ProductColor().tap();
        appModel.AdvantageShoppingApplication().colorObject().tap();

        //set quantity

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText("1000");
        appModel.AdvantageShoppingApplication().APPLYChangeLabel().tap();

        /// need to verify an error msg - not support
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductDetail(), 5000);
        threadSleep(4000);
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(4000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();

        CheckOut("Sefepay"); // use safepay
    }

//    @Test
//    public void OutOfStockTest() throws GeneralLeanFtException {
////        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
//        threadSleep(20000);
//        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
//        tapUiObjectLabel(appModel.AdvantageShoppingApplication().HEADPHONESLabel());
//        threadSleep(10000);
//        tapUiObjectLabel(appModel.AdvantageShoppingApplication().SOLDout());
//
//    	/*
//    	 * verify that we can change color - in web the user can edit the color but here we chack that
//    	 * in not an option
//    	 */
//
//        // all the verification are not working because the attribute "isEnabled" are not include in this version of the app.
//        //Verify.isFalse(appModel.AdvantageShoppingApplication().ProductColor().isEnabled(),"Verification - Out Of Stock", "Verify that we can't change color.");
//
//        //verify that we can't change quantity or add to cart
//        threadSleep(10000);
//        Boolean isClickableProductQuantity = appModel.AdvantageShoppingApplication().ProductQuantity().isClickable();
//        Print("isClickableProductQuantity: " + isClickableProductQuantity);
//
//        Boolean isClickableADDTOCARTButton = appModel.AdvantageShoppingApplication().ADDTOCARTButton().isClickable();
//        Print("isClickableADDTOCARTButton: " + isClickableADDTOCARTButton);
//
//        Verification(Verify.isFalse(isClickableProductQuantity, "Verification - Out Of Stock", "Verify that we can't change quantity."));
////        Verification(Verify.isFalse(isClickableADDTOCARTButton, "Verification - Out Of Stock", "Verify that we can't ADD TO CART."));
//        Verify.isFalse(isClickableADDTOCARTButton, "Verification - Out Of Stock", "Verify that we can't ADD TO CART.");
//
////        assertFalse(isClickableProductQuantity);
////        assertFalse(isClickableADDTOCARTButton);
//    }

    @Test
    public void PayMasterCreditTest() throws GeneralLeanFtException {
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
        if (isSignedIn())
            SignOut();

        SignIn(false);

        threadSleep(2000);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
        threadSleep(2000);
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().LAPTOPSLabel());
        threadSleep(15000);
        tapUiObject(appModel.AdvantageShoppingApplication().ImageViewFilter());
        threadSleep(4000);

        //apply filter- Win 10
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().BYOPERATINGSYSTEMLabel());
        threadSleep(2000);
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().Windows10Label());

        tapUiObjectLabel(appModel.AdvantageShoppingApplication().APPLYChangeLabel());
        tapUiObject(appModel.AdvantageShoppingApplication().LaptopitemWin10());

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 5000);
        threadSleep(5000);
        tapUiObject(appModel.AdvantageShoppingApplication().ProductColor());
        tapUiObject(appModel.AdvantageShoppingApplication().colorObject());
        tapUiObjectButton(appModel.AdvantageShoppingApplication().ADDTOCARTButton());

        //check out and pay with master credit the card number nedded to 12 digits
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(5000);
        tapUiObject(appModel.AdvantageShoppingApplication().CartAccess());

        CheckOut("MasterCredit");
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

        String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        if (isSignedIn())
            SignOut();

        SignIn(false);
        BuyLaptop();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().CartAccess().tap();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().FirstCartItem(), 5000);
        threadSleep(5000);

        String innerTxt = appModel.AdvantageShoppingApplication().CHECKOUT().getText();

        Matcher m = r.matcher(innerTxt);
        boolean match = m.find();
        System.out.println("PayButtonRegExTest- " + innerTxt + " :: " + match);

        Verification(Verify.isTrue(match, "Verification - Verify CHECKOUT RegEx", "Verify that the text in CHECKOUT button start with 'CHECKOUT ({{RegEx}})' ."));
    }

    @Test
    public void ChangePasswordTest() throws GeneralLeanFtException {
    	/*
 		Login
		Click setting
		Click change password
		Change the password
		Logout and login again with the new password
    	 */

    	String savedOriginalPass = PASS;

//        if (SignIn(true))
        if (isSignedIn())
            SignOut();

        // step 1 - change to new pass
        changepassword(PASSNEW, PASS);
        Boolean isChangedToNewPass = SignIn(false);
        Print("isChangedToNewPass " + PASSNEW + " " + isChangedToNewPass);
        Verification(Verify.isTrue(isChangedToNewPass,
                "Verification - Change Password step 1 - change to new pass",
                "Verify that the user login with the new password"));

        SignOut();

        // step 2 -  change back to the default pass
        changepassword(savedOriginalPass, PASSNEW);
        Boolean isChangedToDefaultPass = SignIn(false);
        Print("isChangedToDefaultPass " + savedOriginalPass + " " + isChangedToDefaultPass);
        Verification(Verify.isTrue(isChangedToDefaultPass,
                "Verification - Change Password step 2 -  change back to the default pass",
                "Verify that the user login with the new password"));

        SignOut();
    }

    /**
     * Tests that checks Offline Mode
     *
     * MC requires a full support in offline mode in all mobile apps, including catalog,
     * and full flow - from login to checkout (no prev. connection is required), no user creation or edit account.
     *
     * Setting Offline Mode by switching device to airplane mode.
     * Valicate that:
     * The indication should be in settings: if the user opens the app without internet access,
     * the message should be: "You are not connected to the internet" and when the user clicks OK,
     * the url should say: Offline Mode.
     * The "connect" button at Settings Page should be changed to "apply" and the message "connection successful"
     * should be replaced with: "Note that in offline mode nothing you do will be saved for future use"
     *
     * Allow the user to login without any real authentication, check that there is an offline mode indication in the login page
     * Check that create account is not available - clicking on it pops up a message "not applicable in offline mode"
     * Add products to the cart and then checkout without any validation.
     * Check that the offline mode indication exists in the cart page
     * Try to edit the user account - validate that you get the message: "not applicable in offline mode"
     *
     * Offline mode user details:
     *                                           @"accountType" : @10,
     *                                           @"allowOffersPromotion" : @YES,
     *                                           @"cityName" : @"Newton",
     *                                           @"countryId" : @40,
     *                                           @"countryIsoName" : @"us",
     *                                           @"countryName" : @"United States",
     *                                           @"defaultPaymentMethodId" : @10,
     *                                           @"email" : @"demo@aos.ad",
     *                                           @"firstName" : @"John",
     *                                           @"homeAddress" : @"952 Morseland Ave.",
     *                                           @"id" : @177635799, 
     *                                           @"internalLastSuccesssulLogin" : @0,
     *                                           @"internalUnsuccessfulLoginAttempts" : @0,
     *                                           @"internalUserBlockedFromLoginUntil" : @0,
     *                                           @"lastName" : @"Brown",
     *                                           @"loginName" : @"___",- what the user entered
     *                                           @"mobilePhone" : @"+1-617-527-5555",
     *                                           @"stateProvince" : @"MA",
     *                                           @"zipcode" : @02458
     *
     * @throws GeneralLeanFtException
     * @throws InterruptedException
     */
    @Test
    public void OfflineModeTest() throws GeneralLeanFtException {
        threadSleep(5000);      // wait for application loading
        if (isSignedIn())
            SignOut();
        // init Adnroid Settings application
        appSettings = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("MC.Settings").packaged(false).build());

        toggleAirplaneMode(true);   // enable airplane mode

//        InitBeforeclassLocal();

//        setting();      // update server name to actual and not to use default

//        Print("wait until application finish installing");
//        threadSleep(10000);
//        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
//        tapUiObjectLabel(appModel.AdvantageShoppingApplication().SETTINGSLabel());
//        setTextEditField(appModel.AdvantageShoppingApplication().EditTextServer(), appURL);
//        tapUiObjectButton(appModel.AdvantageShoppingApplication().ConnectButton());
//        threadSleep(10000);
//        tapUiObjectButton(appModel.AdvantageShoppingApplication().OKButton());

        app.restart();      // need for application to enable Offline Mode

        // Check first message on start application that You are not connected to internet
        Verify.isTrue(appModel.AdvantageShoppingApplication().YouAreNotConnectedToLabel().exists(), "Verification - not connected to internet", "Verify that the offline massage appears");
        Print("TAP OKButton");
        appModel.AdvantageShoppingApplication().OKButton().tap();
        threadSleep(5000);

        // Check that default server setted to "Offline Mode"
        String server = appModel.AdvantageShoppingApplication().EditTextServer().getText();
        Print("cur server in EditTextServer: " + server);
        Verification(Verify.isTrue(server.equals("Offline Mode"), "Verification - server massage", "Verify that the offline massage appears in the server setting"));
        Print("TAP ApplyButton");
        appModel.AdvantageShoppingApplication().ApplyButton().tap();
        threadSleep(2000);

        // Check that right message appeared after clicking on APPLY button
        Verify.isTrue(appModel.AdvantageShoppingApplication().NoteOfflineModLabel().exists(), "Verification - offline mode note massage", "Verify that the note for offline mode appears");
        Print("TAP OKButton");
        appModel.AdvantageShoppingApplication().OKButton().tap();

        // TODO: add all functions that contains OfflineMode

        signinInOfflineMode();

        buyAndCheckOutInOfflineMode();

        // TODO:
        // try to edit the user account - validate that you get the message: "not applicable in offline mode"

        toggleAirplaneMode(false);      // disable airplane mode

//        // back to normal state in application
//        // If some user already signed in do sign out
//        if (isSignedIn())
//            SignOut();
//
//        // disable Offline Mode and set default server
//        app.restart();
//        setting();
    }

    @Test
    public void uploadNewImageToProductTest() throws GeneralLeanFtException{
        if (isSignedIn())
            SignOut();

    }

    private void signinInOfflineMode() throws GeneralLeanFtException {
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
//        threadSleep(5000);

        Print("TAP MainMenu");
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        Print("TAP Login");
        appModel.AdvantageShoppingApplication().Login().tap();

        //validation for offline warning
        Verify.isTrue(appModel.AdvantageShoppingApplication().WarningMessageUiObject().exists(), "Verification - offline mode login warning", "Verify that the warning  for offline mode appears in login");

        ///validation that SIGN UP is not available
        Print("TAP DonTHaveAnAccount");
        appModel.AdvantageShoppingApplication().DonTHaveAnAccount().tap();
        Verify.isTrue(appModel.AdvantageShoppingApplication().SignUpObject().exists(), "Verification - sign up offline mode", "Verify that we can create new user in offline mode");

        //TODO: check popup message "not applicable in offline mode"

//        Verify.isTrue(appModel.AdvantageShoppingApplication().WarningMessageUiObject().exists(), "Verification - offline mode login warning", "Verify that the warning  for offline mode appears in login");

        Print("SET to UserNameEdit() offline");
        appModel.AdvantageShoppingApplication().UserNameEdit().setText("offline"); //invalid user name & password - should work
        Print("SET to PassEdit() offline");
        appModel.AdvantageShoppingApplication().PassEdit().setText("offline");
        Print("TAP LOGINButton");
        appModel.AdvantageShoppingApplication().LOGINButton().tap();

        //validate that the Offline user appears
        Print("TAP MainMenu");
        appModel.AdvantageShoppingApplication().MainMenu().tap();

//        String innerTxt = appModel.AdvantageShoppingApplication().LinearLayoutLogin().getVisibleText();
        String innerTxt = appModel.AdvantageShoppingApplication().SignedInUserName().getText();
        Print("current user is " + innerTxt);
        Verify.isTrue(innerTxt.equals("Brown John"), "Verification - offline login", "Verify that we logged in in offline mode");
    }

    private void buyAndCheckOutInOfflineMode() throws GeneralLeanFtException {
        //do a check out without any edit or changing
        BuyLaptop();

        Print("TAP CartAccess");
        appModel.AdvantageShoppingApplication().CartAccess().tap();

        //validation for offline warning
        Verify.isTrue(appModel.AdvantageShoppingApplication().WarningMessageUiObject().exists(), "Verification - offline mode login warning", "Verify that the warning  for offline mode appears in login");

        Print("TAP CHECKOUT");
        appModel.AdvantageShoppingApplication().CHECKOUT().tap();
        threadSleep(2000);

        Print("TAP PAYNOWButton");
        appModel.AdvantageShoppingApplication().PAYNOWButton().tap();
        threadSleep(5000);
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(4), "Verify- purchase success offline mode", " verify that the payment success and we receive the order detail window (offline)"));
        Print("TAP CloseDialog");
        appModel.AdvantageShoppingApplication().CloseDialog().tap();
    }

    /////////////////////////////////////  End of tests  //////////////////////////////////////////////////////

    public void changepassword(String newPass, String oldPass) {
        Print("\nCHANGE PASS to " + newPass);
        SignIn(false);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
        tapUiObject(appModel.AdvantageShoppingApplication().AccountDetails());
        threadSleep(5000);
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ChangePasswordObject(), 5000);
//        waitUntilElementExists((UiObject) appModel.AdvantageShoppingApplication().ChangePasswordLabel());

        //change to another password
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().ChangePasswordLabel());
        setTextEditField(appModel.AdvantageShoppingApplication().OldPassEditField(), oldPass);
        setTextEditField(appModel.AdvantageShoppingApplication().NewPassEditField(), newPass);
        setTextEditField(appModel.AdvantageShoppingApplication().ConfirmNewPassEditField(), newPass);
        deviceSwipe(SwipeDirection.UP);
        deviceSwipe(SwipeDirection.UP);
        tapUiObjectButton(appModel.AdvantageShoppingApplication().UPDATEAccountButton());

        PASS = newPass;
        SignOut();
    }

    /**
     * Function checks if any user signed in
     * @return true if signed in
     */
    public boolean isSignedIn() {
        Print("isSignedIn start");
        boolean result = false;
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
        threadSleep(10000);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
//        String innerTxt = appModel.AdvantageShoppingApplication().LoggedUserName().getText();
        String innerTxt = getTextUiObject(appModel.AdvantageShoppingApplication().LoggedUserName());
        if (!innerTxt.equals("LOGIN"))
            result = true;
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().HOME());
        Print("isSignedIn end (isSignedIn ? " + result + ") " + (result ? innerTxt: "" ));
        return result;
    }

    public boolean SignIn(Boolean quiet) {
        Print("SignIn() start");
        //Print("waitUntilElementExists MainMenu()");
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu());
        threadSleep(5000);

        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
//        String innerTxt = getTextUiObject(appModel.AdvantageShoppingApplication().LoggedUserName());

//        if (innerTxt.equals("LOGIN")) {
            if (!quiet) {
                tapUiObjectLabel(appModel.AdvantageShoppingApplication().Login());
                setTextEditField(appModel.AdvantageShoppingApplication().UserNameEdit(), UNAME);
                setTextEditField(appModel.AdvantageShoppingApplication().PassEdit(), PASS);
                tapUiObjectButton(appModel.AdvantageShoppingApplication().LOGINButton());

                if (!existsLabel(appModel.AdvantageShoppingApplication().InvalidUserNameOrPas())) {
                    System.out.println(UNAME + "  Login Success");
                    Verify.isTrue(true, "Verification - Sign In", "Verify that the user " + UNAME + " signed in properly.");
                    Print("SignIn() end");
//                    deviceBack();
                    return true;
                }
                Print("SignIn() end");
//                deviceBack();
                return false;
            }
//        }
        System.out.println(UNAME + " allready logged in");
//        deviceBack();
        Print("SignIn() end");
        return false;
    }

    /**
     * Function makes log out to logged in user
     * @throws GeneralLeanFtException
     */
    public void SignOut() {
        Print("\nSignOut() start");
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 10000);
        threadSleep(10000);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().SIGNOUTLabel());
        tapUiObjectButton(appModel.AdvantageShoppingApplication().YESButton());
        Print("Signed Out from " + UNAME + "\n");
    }

    public void BuyLaptop() throws GeneralLeanFtException {
        Print("BuyLaptop() start");
        EmptyCart();
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 10000);
        threadSleep(10000);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());

        tapUiObjectLabel(appModel.AdvantageShoppingApplication().LAPTOPSLabel());
        threadSleep(10000);
        tapUiObject(appModel.AdvantageShoppingApplication().LaptopitemWin10());
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductColor(), 10000);
        threadSleep(10000);
//        waitUntilElementExists((UiObject) appModel.AdvantageShoppingApplication().ADDTOCARTButton(), 10000);
        tapUiObjectButton(appModel.AdvantageShoppingApplication().ADDTOCARTButton());
        Print("BuyLaptop() end");
    }

    public void EditShipping() throws GeneralLeanFtException, InterruptedException {
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().EditShippingUiObject(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();
    }

    public void CheckOut(String payment) throws GeneralLeanFtException {
        Print("CheckOut (" + payment + ") start");
        tapUiObjectButton(appModel.AdvantageShoppingApplication().CHECKOUT());

        threadSleep(25000);

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);

        //pay with safepay and don't save details
        if (payment.equals("Sefepay"))
            SafePay(false);
        else
            MasterCredit("123456789123", "456", UNAME, false, true);

        threadSleep(10000);
        tapUiObjectButton(appModel.AdvantageShoppingApplication().PAYNOWButton());
        threadSleep(10000);
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject(), 5000);
        Verification(Verify.isTrue(appModel.AdvantageShoppingApplication().VerifyReceiptWindowUiObject().exists(4), "Verify- purchase success with " + payment, " verify that the payment success and we receive the order detail window"));
        threadSleep(2000);
        tapUiObject(appModel.AdvantageShoppingApplication().CloseDialog());
        Print("CheckOut (" + payment + ") end");
    }

    public void SafePay(boolean save) throws GeneralLeanFtException {
        Print("SafePay(" + save + ") start");
        //start with edit Safepay details

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails(), 5000);
        threadSleep(5000);

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

    public void MasterCredit(String cardnum, String CVV, String HolderName, boolean savedetails, boolean changeShipping) throws GeneralLeanFtException {
        Print("MasterCredit() start");
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().PaymentDetails(), 10000);
        threadSleep(10000);

        Print("changeShipping = " + changeShipping);
        if (changeShipping) {
            Print("tap EditShippingUiObject()");
            appModel.AdvantageShoppingApplication().EditShippingUiObject().tap();
            Print("Check first name editField isEmpty");
            threadSleep(2000);
            if(appModel.AdvantageShoppingApplication().ShippingDetailsFirstNameEditField().getText().isEmpty())
                appModel.AdvantageShoppingApplication().ShippingDetailsFirstNameEditField().setText(UNAME);
            appModel.AdvantageShoppingApplication().ZIPshippingDetaildEditField().setText("12345");
            Print("tap ShippingCheckBox()");
            appModel.AdvantageShoppingApplication().ShippingCheckBox().tap();
            Print("tap APPLYChangeLabel()");
            if(appModel.AdvantageShoppingApplication1().ShippingDetailsAddressTextField().getText() == null ||
                    appModel.AdvantageShoppingApplication1().ShippingDetailsAddressTextField().getText().isEmpty()) {
                appModel.AdvantageShoppingApplication1().ShippingDetailsAddressTextFieldLabel().tap();
                appModel.AdvantageShoppingApplication1().ShippingDetailsAddressTextField().setText("Address");
            }
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

    public void EmptyCart() throws GeneralLeanFtException {
        Print("EmptyCart() start");
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().CartAccess(), 5000);
        threadSleep(5000);
        tapUiObject(appModel.AdvantageShoppingApplication().MainMenu());
        tapUiObjectLabel(appModel.AdvantageShoppingApplication().CARTLabel());
        while (appModel.AdvantageShoppingApplication().FirstCartItem().exists(2)) {
//            waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
            threadSleep(5000);
            appModel.AdvantageShoppingApplication().FirstCartItem().swipe(SwipeDirection.RIGHT);
            threadSleep(2000);
            tapUiObject(appModel.AdvantageShoppingApplication().CartRemove());
        }
//        deviceBack();
        Print("EmptyCart() end");
    }

    public void PurchaseTablet(String quantity) throws GeneralLeanFtException, NumberFormatException, InterruptedException {
        EmptyCart();
//        waitUntilElementExists(appModel.AdvantageShoppingApplication().MainMenu(), 5000);
        threadSleep(5000);
        appModel.AdvantageShoppingApplication().MainMenu().tap();
        appModel.AdvantageShoppingApplication().TABLETSLabel().tap();
        appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

//        waitUntilElementExists(appModel.AdvantageShoppingApplication().ProductQuantity(), 5000);
        threadSleep(5000);

        appModel.AdvantageShoppingApplication().ProductQuantity().tap();
        appModel.AdvantageShoppingApplication().ProductQuantityEditField().setText(quantity);
    }

    public boolean waitUntilElementExists(UiObject appElem) {
        Print("WAIT UNTIL ELEMENT EXISTS " + appElem.getClass().getSimpleName());
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(appElem, new WaitUntilEvaluator<UiObject>() {
                public boolean evaluate(UiObject we) {
                    try {
                        return we.exists();
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (GeneralLeanFtException e) {
            printError(e);
            fail("GeneralLeanFtException: " + appElem.toString());
        }
        return result;
    }

    public boolean waitUntilElementExists(UiObject appElem, long time) {
        Print("WAIT UNTIL ELEMENT EXISTS " + appElem.getClass().getSimpleName() + " for " + time + " millisec");
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(appElem, new WaitUntilEvaluator<UiObject>() {
                public boolean evaluate(UiObject we) {
                    try {
                        return we.exists();
                    } catch (Exception e) {
                        return false;
                    }
                }
            }, time);
        } catch (GeneralLeanFtException e) {
            printError(e);
            fail("GeneralLeanFtException: " + appElem.toString());
        }
        return result;
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod) {
            GeneralLeanFtException e = new GeneralLeanFtException("Verification ERORR - verification of test fails! check runresults.html");
            printError(e);
            throw e;
        }
    }

    private static String getAndroidDeviceId() throws GeneralLeanFtException {
        String result = "";
        for (DeviceInfo deviceInfo : MobileLab.getDeviceList()) {
            String curDeviceName = deviceInfo.getName();
            String curDeviceID = deviceInfo.getId();
            String curDeviceOSType = deviceInfo.getOSType();
            System.out.printf("The device ID is: %s, and its name is: %s, and its OS is: %s\n\n", curDeviceID, curDeviceName, curDeviceOSType);
            if (curDeviceOSType.equals("ANDROID")) {
                result = curDeviceID;
                break;
            }
        }
        return result;
    }

    public static void InitBeforeclass() throws GeneralLeanFtException {
        String deviceID = getAndroidDeviceId();

        Print("Trying to lock device with ID:\nMobileLab.lockDeviceById(" + deviceID + ")");
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
        device = MobileLab.lockDeviceById(getAndroidDeviceId());// ID For galaxy S6

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.Advantage.aShopping").packaged(true).build());

        //connect between the appModel and the device
        appModel = new AdvantageAndroidApp(device);

//        Print("Install application");
//        app.install();
    }

    private static void Print(String msg) {
        System.out.println(msg);
    }

    public void toggleAirplaneMode(boolean onOffValue) throws GeneralLeanFtException {
        Print("\ntoggle airplane mode to " + onOffValue);
        Print("Open Android Settings");
        appSettings.launch();

        threadSleep(2000);

        Print("TAP Search button");
        appModel.SettingsApplication().SettingsSearchButton().tap();
        //appModel.SettingsApplication().SettingsConnections().tap();

        Print("Search for airplane");
        appModel.SettingsApplication().SettingsSearchTextEditField().setText("Airplane");
        //appModel.SettingsApplication().SettingsConnections().tap();

        Print("Click on Airplane settings");
        appModel.SettingsApplication().AirplaneModeSettingsButtonLabel().tap();
        Print("SET Airplane Mode Toggle to " + onOffValue);
        if(onOffValue && appModel.SettingsApplication().AirplaneToggleOffSwitchToggle().exists())
            appModel.SettingsApplication().AirplaneToggleOffSwitchToggle().tap();
        else if(!onOffValue && appModel.SettingsApplication().AirplaneToggleONSwitch().exists())
            appModel.SettingsApplication().AirplaneToggleONSwitch().tap();

//        appModel.SettingsApplication().ConnectionsAirplaneModeToggle().set(onOffValue);

        Print("Back to Settings from Connections");
        device.back();
        threadSleep(2000);
        Print("Close Settings");
        device.back();
//        appSettings.kill();

//        appModel.SettingsApplication().AirplaneMode().tap();
//        appModel.SettingsApplication().AirplaineToggle().set(onOffValue);
    }

    private void tapUiObject(UiObject uiObject) {
        Print("TAPPED " + uiObject.getClass().getSimpleName());
        try {
            uiObject.tap();
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + uiObject.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + uiObject.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + uiObject.getClass().getSimpleName());
        }
    }

    private void tapUiObjectLabel(Label label) {
        Print("TAPPED " + label.getClass().getSimpleName());
        try {
            label.tap();
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + label.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + label.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + label.getClass().getSimpleName());
        }
    }

    private void tapUiObjectButton(Button button) {
        Print("TAPPED " + button.getClass().getSimpleName());
        try {
            button.tap();
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + button.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + button.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + button.getClass().getSimpleName());
        }
    }

    private String getTextUiObject(Label uiObject) {
        Print("GET TEXT from " + uiObject.getClass().getSimpleName());
        String result = "";
        try {
            result = uiObject.getText();
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + uiObject.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + uiObject.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + uiObject.getClass().getSimpleName());
        }
        return result;
    }

    private void setTextEditField(EditField editField, String value) {
        Print("SET TEXT " + value + " to " + editField.getClass().getSimpleName());
        try {
            editField.setText(value);
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + editField.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + editField.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + editField.getClass().getSimpleName());
        }
    }

    private boolean existsLabel(Label label) {
        Print("EXISTS " + label.getClass().getSimpleName());
        boolean result = false;
        try {
            result = label.exists();
        } catch (GeneralLeanFtException e) {
            printError(e);
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + label.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + label.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1);
            }
            fail("GeneralLeanFtException: couldn't click on element " + label.getClass().getSimpleName());
        }
        return result;
    }

    private static void printCaptionTest(String nameOfTest, int curNumberOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println(curNumberOfTest + " START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest, String time) {
        System.out.println("END " + nameOfTest + " in " + time);
    }

    private static void printTimeWholeTests(Long millis) {
        System.out.println("\n--------------------------------------------------");
        Print("AndroidTests done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min\n"));
    }

    private void threadSleep(long millis) {
        try {
            Print("sleep " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            printError(e);
            fail("InterruptedException: failed to sleep for " + millis + " sec");
        }
    }

    private void deviceBack() {
        Print("device.back()");
        try {
            device.back();
        } catch (GeneralLeanFtException e) {
            printError(e);
            fail("GeneralLeanFtException: failed to device.back");
        }
    }

    private void deviceSwipe(SwipeDirection swipeDirection) {
        Print("device.swipe " + swipeDirection.toString());
        try {
            device.swipe(swipeDirection);
        } catch (GeneralLeanFtException e) {
            printError(e);
            fail("GeneralLeanFtException: failed to device.swipe " + swipeDirection.toString());
        }
    }

    private static void printError(Exception e) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + e.getMessage() +  "\n");
    }

}

// TODO: add to tests
// - Settings Page. Server url can contain xxx.xxx.xxx.xxx and http://xxx.xxx.xxx.xxx (after that remove checking for http:// at starting url)