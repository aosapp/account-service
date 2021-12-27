package Mobile;

import static org.junit.Assert.*;

import org.junit.*;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;

import com.hp.lft.verifications.*;

import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import unittesting.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IOSTests extends UnitTestClassBase {

    protected static AdvantageIOSApp appModel;
    protected static AdvantageAndroidApp appModel2;
    protected static Device device;
    protected static Application app;
    static String DEVICE_ID = "98b098dc0c6c797784460d811cffc6de7b887c8d";       // ID iPhone S6

    // TODO: remove IosUser1
//    static String UNAME = "IosUser1";
//    static String UNAME = "IosUser2";
//    static String UNAME = "IosUser3";
//    static String UNAME = "IosUser4";
    static String UNAME = "IosUser5";


    static String PASS = "Password1";
    static String UNAME_WRONG = "user";
    static String PASS_WRONG = "pass";
    //    static String appURL2 = "52.88.236.171:80"; //"52.32.172.3:8080";//""35.162.69.22:8080";//
    //static String appURL2 = "16.60.158.84:80";  // CI
    static String appURL2 = "http://advantageonlineshopping.com:8080";  // production new
    static String appURL = System.getProperty("url", "defaultvalue");
    private static final String HUGE_QUANTITY = "999";

    private static int currentNumberOfTest = 0;
    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public IOSTests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new IOSTests();
        globalSetup(IOSTests.class);

        if (appURL.equals("defaultvalue")) {
            appURL = appURL2;
            System.out.println("appURL: " + appURL);
            InitBeforeclass();
        } else {
            System.out.println("appURL: " + appURL);
            InitBeforeclass();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        device.unlock();
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        print("\niOSTests done in: " + String.valueOf((elapsedTimeAllTests/1000F)/60 + " min"));
        printTimeWholeTests(elapsedTimeAllTests);
    }

    @Before
    public void setUp() throws Exception {
        Print("set up start ");
        startTimeCurrentTest = System.currentTimeMillis();
        printCaptionTest(curTestName.getMethodName(), ++currentNumberOfTest);
        app.restart();
        if(!isConnectedToServer()){
            connectToInternet();
            app.restart();
        }
//        waitUntilElementExists(appModel.IshoppingApplication().MenuObjUiObject());
    }

    @After
    public void tearDown() throws Exception {
        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
        String passingTime = String.valueOf((elapsedTimeCurrentTest/1000F)/60 + " min / "
                + String.valueOf(elapsedTimeCurrentTest/1000F) + " sec / "
                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n");
        printEndOfTest(curTestName.getMethodName(), passingTime);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////

//

    // TODO: decide how to check. If user already exist in database test failed or not?
    @Test
    public void AddNewUserTest() throws Exception {

        if (isSignedIn())
            SignOut();

        boolean isSignedIn = SignIn();
        if (isSignedIn) {
            print("USER ALREADY EXIST");
            SignOut();
            return;
        } else {
            boolean isNewUserCreated = CreateNewUser();
            Verification(Verify.isTrue(isNewUserCreated, "Create New User", "Verify that the user was created successfully"));
            Verification(Verify.isTrue(IsLoggedInUser(UNAME), "Create New User", "Verify that the user just created is logged in"));
        }
        print("FINISH " + this.getTestName());
    }

    public Boolean isConnectedToServer()throws Exception {
        Print("isConnectedToInternet start");
        threadSleep(3000);
        if(!appModel.IshoppingApplication().MenuButton().exists()){

            Print("Not connected to server");
                return false;
        }
        else{
            Print("Connected to server ");
            return true;
        }
    }
    public void connectToInternet() throws Exception{

        Print("connectToInternet starts");
        device.home();
        threadSleep(1000);

        if(appModel.homeApplication().settingsButton().exists()){
            Print("Tap setting");
            appModel.homeApplication().settingsButton().tap();
        }
        else{
            device.home();
            Print("Back and tap setting");
            appModel.homeApplication().settingsButton().tap();
        }
        Print("Tap Wi-fi");
        threadSleep(1000);
        appModel.settingsApplication().wiFiLabel().tap();
        if(!appModel.settingsApplication().wiFiToggle().isChecked()){
            Print("Toggle Wi-fi switch to on ");
            appModel.settingsApplication().wiFiToggle().tap();
            threadSleep(22500);
            if(appModel.websheetApplication().doneButton().exists()){
                Print("Connected to internet");
                device.home();
                return;
            }
            if(!appModel.settingsApplication().wiFiToggle().isChecked()){
                Print("Toggle Wi-fi switch to second try ");
                appModel.settingsApplication().wiFiToggle().tap();
                threadSleep(22500);
                if(appModel.websheetApplication().doneButton().exists()){
                    Print("Connected to internet");
                    device.home();
                    return;
                }
            }
            Print("wi-fi switch is on according to leanFT ");
            device.home();
        }
    }

//    WORK
    @Test
    public void InvalidLoginTest() throws GeneralLeanFtException, InterruptedException {
        if (isSignedIn())
            SignOut();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP LoginObj");
        appModel.IshoppingApplication().LoginObj().tap();
        print("SET UserNameLoginditField: " + UNAME_WRONG);
        appModel.IshoppingApplication().UserNameLoginditField().setText(UNAME_WRONG);
        print("SET PasswordLoginEditField: " + PASS_WRONG);
        appModel.IshoppingApplication().PasswordLoginEditField().setText(PASS_WRONG);
        print("TAP LOGINButton");
        appModel.IshoppingApplication().LOGINButton().tap();

        Thread.sleep(2000);

        boolean invalidUserNameOrPassMessage = appModel.IshoppingApplication().LOGINButton().exists(3);
        if (invalidUserNameOrPassMessage)
                print("Could not login");

        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();

        Verification(Verify.isTrue(invalidUserNameOrPassMessage, "Invalid Sign in (Test)", "verify that user can't sign in with invalid user"));
        print("FINISH " + this.getTestName());
    }

    @Test
    public void CreateExistingUserTest() throws GeneralLeanFtException, InterruptedException {
        if (isSignedIn())
            SignOut();

        appModel.IshoppingApplication().MenuButton().tap();
        appModel.IshoppingApplication().LoginLabel().tap();

//        CreateNewUser(true);
        boolean isUserExistMessage = CreateNewUser();
        threadSleep(1000);
        if(isUserExistMessage){
            Print("No user exist message was displayed \n Most likely the user wasn't created yet. Let's check ");
            SignOut();
            threadSleep(1000);
            isUserExistMessage = CreateNewUser();
        }
        Verification(Verify.isFalse(isUserExistMessage, "Create New User", "Verify if user already exist"));

    }

//    Not needed
//    @Test
//    public void SilentLoginTest() throws GeneralLeanFtException, InterruptedException {
//        if (SignIn(false)) {
//            SignOut();
//        }
//        SignIn(false);
//        Verification(Verify.isTrue(SignIn(true), "Silent login", "verify that user sign in success"));
//    }

//    WORK
    @Test
    public void LogOutTest() throws GeneralLeanFtException, InterruptedException {
        if (!isSignedIn())
            SignIn();
        SignOut();
    }

    @Test
    public void UpdateCartTest() throws GeneralLeanFtException, InterruptedException {
        //todo:need to pay with safepay but for now we use MasterCredit until fixing the bug

        if (!isSignedIn())
            SignIn();

        //EmptyCart();

        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();
        print("TAP TABLETSLabel");
        appModel.IshoppingApplication().tABLETSLabel().tap();
        print("TAP TabletItem");
        appModel.IshoppingApplication().hPElitePad1000G2TabletLabel().tap();
        print("TAP ADDTOCARTButton");
        appModel.IshoppingApplication().ADDTOCARTButton().tap();
        print("TAP CarticonButton");
        appModel.IshoppingApplication().CarticonButton().tap();
        print("TAP FirstCartElement");
        appModel.IshoppingApplication().FirstCartElement().tap();
        waitUntilElementExists(appModel.IshoppingApplication().TabletObjUiObject());

        print("TAP ColorButton");
        appModel.IshoppingApplication().ColorButton().tap();
        print("TAP ColorObject");
        appModel.IshoppingApplication().ColorObject().tap();

        print("TAP QuantityButton");
        appModel.IshoppingApplication().QuantityButton().tap();
        for (int i = 0; i < 3; i++) { //increase quantity
            print("TAP ");
            appModel.IshoppingApplication().PlusButton().tap();
        }
        appModel.IshoppingApplication().APPLYButton().tap();
        appModel.IshoppingApplication().UPDATECARTButton().tap();

        threadSleep(1500);
        //device.back();
        CheckOut("MasterCredit");

        //Buy(appModel.IshoppingApplication().TABLETSLabel(), appModel.IshoppingApplication().TabletItem(),"MasterCredit");

        //SignIn();
    }

//    WORK
    @Test
    public void OutOfStockTest() throws GeneralLeanFtException, InterruptedException {
        LogOutTest();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP HEADPHONESLabel");
        appModel.IshoppingApplication().MainMenuHeadphones().tap();
        print("TAP SoldOutUiObject");
        appModel.IshoppingApplication().SoldOutUiObject().tap();

        boolean isQuantityButtonEnabled = appModel.IshoppingApplication().QuantityButton().isEnabled();
        print("isQuantityButtonEnabled: " + isQuantityButtonEnabled);
        boolean isAddToCartButtonenabled = appModel.IshoppingApplication().ADDTOCARTButton().isEnabled();
        print("isAddToCartButtonenabled: " + isAddToCartButtonenabled);

        print("TAP BackButton");
        appModel.IshoppingApplication().BackButton().tap();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();

        print("Check if QUANTITY is disabled: " + !isQuantityButtonEnabled);
        Verify.isFalse(isQuantityButtonEnabled, "Verification - Out Of Stock", "Verify that we can't change quantity.");

        print("Check if ADD TO CART BUTTON is disabled: " + !isAddToCartButtonenabled);
        Verify.isFalse(isAddToCartButtonenabled, "Verification - Out Of Stock", "Verify that we can't ADD TO CART.");
        print("FINISH " + this.getTestName());
    }

    @Test
    public void priceRangeElement() throws GeneralLeanFtException, InterruptedException{
        if (!isSignedIn())
            SignIn();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP SPEAKERSLabel");
        appModel.IshoppingApplication().SPEAKERSLabel().tap();
        print("TAP SpeakerImgUiObject");
        appModel.IshoppingApplication().ProductsPageFilterButton().tap();
        appModel.IshoppingApplication().FilterByPriceTabLabel().tap();
        appModel.IshoppingApplication().MobileObjectFilterPriceSliderUiObject().swipe(SwipeDirection.RIGHT);


    }

    @Test
    public void PurchaseHugeQuantityTest() throws GeneralLeanFtException, InterruptedException {

        if (!isSignedIn())
            SignIn();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP SPEAKERSLabel");
        appModel.IshoppingApplication().SPEAKERSLabel().tap();
        print("TAP SpeakerImgUiObject");
        appModel.IshoppingApplication().SpeakerImgUiObject().tap();
        print("TAP ColorButton");
        appModel.IshoppingApplication().ColorButton().tap();
        print("TAP ColorObject");
        appModel.IshoppingApplication().ColorObject().tap();
        print("TAP QuantityButton");
        appModel.IshoppingApplication().QuantityButton().tap();
        print("TAP QuantityEditField");
        appModel.IshoppingApplication().mobileEditEditField().tap();
        print("SET QuantityEditField: " + HUGE_QUANTITY);
        appModel.IshoppingApplication().mobileEditEditField().setText(HUGE_QUANTITY);
        print("TAP ApplyQuantity");
        appModel.IshoppingApplication().ApplyQuantity().tap();
        print("TAP ADDTOCARTButton");
        appModel.IshoppingApplication().ADDTOCARTButton().tap();

        boolean isAddProductMessageExist = appModel.IshoppingApplication().AddProductMessage().exists();
        Verify.isTrue(isAddProductMessageExist, "Verification - Purchase huge quantity", "Verify that message appeared. In message told about existing limit");
        if (isAddProductMessageExist) {
            print("TAP AddProductMessageOkButton");
            appModel.IshoppingApplication().AddProductMessageOkButton().tap();
        }

        print("TAP BackButton");
        appModel.IshoppingApplication().BackButton().tap();
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();
        print("FINISH " + this.getTestName());
    }

//    @Test
    public void PurchseWithMasterCreditTest() throws GeneralLeanFtException, InterruptedException {
//        if (!SignIn(true))
//            SignIn(false);

        Buy(appModel.IshoppingApplication().LAPTOPSLabel(), appModel.IshoppingApplication().LeptopItem(), "MasterCredit");


        //SignIn();
    }

    /*@Test
    public void ChangePasswordTest() throws GeneralLeanFtException, InterruptedException {

        //SignIn();
        //todo: implement test - change Password doesn't work yet

        if(!SignIn(true))
            SignIn(false);

        String oldPass = PASS;

        boolean pass = ChangePassword(PASS,"NewPass8");
        Verification(Verify.isTrue(pass,"Change Password - step 1" , "verify that Password changed  successfully"));

        pass = ChangePassword("NewPass1" , oldPass);
        Verification(Verify.isTrue(pass,"Change Password - step 2" , "verify that Password changed back successfully"));
    }*/

    //////////////////////////////////////////////        end of tests   ///////////////////////////////////////////////


    public boolean IsLoggedInUser(String userName) throws GeneralLeanFtException {
        print("\nSTART IsLoggedInUser()");
        boolean result = false;
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        String loginTxt = appModel.IshoppingApplication().LoginLabel().getText();
        Print("current login = '" + loginTxt + "'");
        if (loginTxt.equals(userName)) {
            result = true;
        }
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();
        print("END IsLoggedInUser() ? " + result);
        return result;
    }


//    public boolean SignIn(boolean quiet) throws GeneralLeanFtException, InterruptedException {
    public boolean SignIn() throws GeneralLeanFtException, InterruptedException {
        print("\nSTART SignIn");
        /*app.restart();
        waitUntilElementExists(appModel.IshoppingApplication().MenuObjUiObject());*/
        print("tap MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        String loginTxt = appModel.IshoppingApplication().LoginLabel().getText();
        Print("current loginTxt = '" + loginTxt + "'");
        if (!loginTxt.equals("LOG IN")) {

            Print("Seems there is a user logged in-  "+loginTxt);
            return true;
        }
        else {
            print("tap LoginObj");
            appModel.IshoppingApplication().LoginObj().tap();
//                  threadSleep(3000);
            print("tap UserNameLoginditField");
            appModel.IshoppingApplication().UserNameLoginditField().tap();
//                  threadSleep(3000);
            print("setText UserNameLoginditField " + UNAME);
//                  appModel.IshoppingApplication().UserNameLoginditField().setText(UNAME);
            appModel.IshoppingApplication().UserNameLabelEditField().setText(UNAME);
//                  threadSleep(3000);
            print("tap PasswordLogin");
            appModel.IshoppingApplication().PasswordLogin().tap();
//                  threadSleep(3000);
            print("setText PasswordLogin " + PASS);
//                  appModel.IshoppingApplication().PasswordLoginEditField().setText(PASS);
            appModel.IshoppingApplication().PasswordLogin().setText(PASS);

            print("tap LOGINButton");
            appModel.IshoppingApplication().LOGINButton().tap();

            threadSleep(2000);
            Print("Checking if incorrectUserNameOrPasswordLabel exists");
            if (!appModel.IshoppingApplication().incorrectUserNameOrPasswordLabel().exists()) {
                Print(UNAME + " - Login success");
                Verify.isTrue(true, "Sign in", "verify that user sign in success");
                return true;
            }
            Verify.isTrue(true, "Invalid Sign in", "verify that user can't sign in with invalid user");
            Print("Assuming the user was'nt created yet");
            if (CreateNewUser()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void EmptyCart() throws GeneralLeanFtException, InterruptedException {
        Print("EmptyCart start");


        CheckOut("safepay");
    }

    public void Print(String msg) {
        System.out.println(msg);
    }

    public static void InitBeforeclass() throws GeneralLeanFtException {
        String deviceID = "";
        print("Num of devices = " + MobileLab.getDeviceList().length);
        for (DeviceInfo deviceInfo : MobileLab.getDeviceList()) {
            print(deviceInfo.getOSType());
            //System.out.printf("The device ID is: %s, and its name is: %s\n\n", deviceInfo.getId(), deviceInfo.getName());
            String[] s = deviceInfo.getOSVersion().split("\\.");
            String Join = "";
            for (String s1 : s)
                Join += s1;

            int version = Integer.parseInt(Join);
            if (deviceInfo.getOSType().equals("IOS")) {
                deviceID = deviceInfo.getId();
                break;
            }
        }

        print("deviceID: " + deviceID);
        device = MobileLab.lockDeviceById(deviceID);

        print("device os type: " + device.getOSType());

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.mf.iShopping").packaged(true).build());

        print("app version: " + app.getVersion());
        print("app get name: " + app.getName());
        print("app get display name: " + app.getDisplayName());

        //connect between the appModel and the device
        appModel = new AdvantageIOSApp(device);

    }

    //use this in local testing

    public static void InitBeforeclassLocal() throws GeneralLeanFtException {
        print("DEVICE ID: " + DEVICE_ID);
        device = MobileLab.lockDeviceById(DEVICE_ID);

        print("device os type: " + device.getOSType());

        // Describe the AUT.
        app = device.describe(Application.class, new ApplicationDescription.Builder()
                .identifier("com.mf.iShopping").packaged(true).build());

        print("app version: " + app.getVersion());
        print("app get name: " + app.getName());
        print("app get display name: " + app.getDisplayName());

        //connect between the appModel and the device
        appModel = new AdvantageIOSApp(device);
        app.install();
    }

    public void SignOut() throws GeneralLeanFtException, InterruptedException {
        print("SignOut started");
//        if(SignIn(true)){
        System.out.println("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        System.out.println("TAP LoginLabel");
        appModel.IshoppingApplication().LoginLabel().tap();
        System.out.println("TAP YesSignOutButton");
        appModel.IshoppingApplication().YesSignOutButton().tap();

        // TODO: create function that will check in menu
        Verification(Verify.isTrue(!isSignedIn(), "Signed Out ", "Verify that the user is signed out from app"));
//        }
//        else
//            Print("no user login");
        print("SignOut ended");
    }

//    public void CreateNewUser(boolean isTest) throws GeneralLeanFtException, InterruptedException {
    public boolean CreateNewUser() throws GeneralLeanFtException, InterruptedException {
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        print("TAP LoginObj");
        appModel.IshoppingApplication().LoginObj().tap();

        appModel.IshoppingApplication().SignUpButton().tap();
        Thread.sleep(5000);

        appModel.IshoppingApplication().userNameTextFieldLabelEditField().tap();
        System.out.println("SET UserNameSignUpEditField: " + UNAME);
        appModel.IshoppingApplication().userNameTextFieldLabelEditField().setText(UNAME);

        appModel.IshoppingApplication().emailTextFieldLabelEditField().tap();
        System.out.println("SET EmailSignUpEditField: " + UNAME + "@default.com");
        appModel.IshoppingApplication().emailTextFieldLabelEditField().setText(UNAME + "@default.com");

        appModel.IshoppingApplication().PasswordSignUpEditField().tap();
        System.out.println("SET PasswordSignUpEditField: " + PASS);
        appModel.IshoppingApplication().PasswordSignUpEditField().setText(PASS);

        appModel.IshoppingApplication().ConfirmPasswordSignUpEditField().tap();
        System.out.println("SET ConfirmPasswordSignUpEditField: " + PASS);
        appModel.IshoppingApplication().ConfirmPasswordSignUpEditField().setText(PASS);

        print("Go to First Name");
        appModel.IshoppingApplication().KeyboardNext().tap();
        print("Go to Last Name");
        appModel.IshoppingApplication().KeyboardNext().tap();
        print("Go to State Name");
        appModel.IshoppingApplication().KeyboardNext().tap();
        print("Go to Street Name");
        appModel.IshoppingApplication().KeyboardNext().tap();

        appModel.IshoppingApplication().CountryLabel().tap();
        appModel.IshoppingApplication().CountryPickerDone().tap();

        appModel.IshoppingApplication().StreetSignUpEditField().tap();
        System.out.println("SET StreetSignUpEditField: " + "Altalef 5");
        appModel.IshoppingApplication().StreetSignUpEditField().setText("Altalef 5");

        appModel.IshoppingApplication().CitySignUpEditField() .tap();
        System.out.println("SET StreetSignUpEditField: " + "Yahud");
        appModel.IshoppingApplication().CitySignUpEditField().setText("Yahud");

        appModel.IshoppingApplication().ZipSignUpEditField() .tap();
        System.out.println("SET ZipSignUpEditField: " + "454545");
        appModel.IshoppingApplication().ZipSignUpEditField().setText("454545");

        print("TAP KeyboardDone()");
        appModel.IshoppingApplication().KeyboardDone().tap();

        print("TAP RegisterButton()");
        appModel.IshoppingApplication().RegisterButton().tap();

        boolean isUserExistMessage = appModel.IshoppingApplication().UserNameAlreadyExistsLabel1().exists();
//        boolean existUser = appModel.IshoppingApplication().UserNameAlreadyExistsLabel1().exists(3);
        if (isUserExistMessage) {
            print("TAP CreateAccountOkButton");
            appModel.IshoppingApplication().CreateAccountOkButton().tap();
            print("TAP MenuButton");
            appModel.IshoppingApplication().MenuButton().tap();
            print("TAP MainMenuHome");
            appModel.IshoppingApplication().MainMenuHome().tap();
            print("USER ALREADY EXIST!");
            return !isUserExistMessage;
        }
//      If not received error message that tells that user already exists that means that user created successfully
        Verification(Verify.isFalse(isUserExistMessage, "Create New User", "Verify if received message that user already exist"));
        Print("Registration completed successfully ");
        return !isUserExistMessage;
    }

    public void CheckOut(String paymethod) throws GeneralLeanFtException {
        if(appModel.IshoppingApplication().CarticonButton().exists()){
            appModel.IshoppingApplication().CarticonButton().tap();
        }
        else{
            System.out.println("TAP MenuButton");
            appModel.IshoppingApplication().MenuButton().tap();
            System.out.println("TAP Cart Button");
            appModel.IshoppingApplication().CARTLabel().tap();
        }

        tapRightCheckoutButton();

        threadSleep(1000);
        Print("Tapping payBtn");
        appModel.IshoppingApplication().PAYNOWButton().tap();
        Print("Checking is preferredPaymentWasNotSpecifiedLabel exists");
        if(appModel.IshoppingApplication().preferredPaymentWasNotSpecifiedLabel().exists()){
            Print("No prefered payment label exists");
            appModel.IshoppingApplication().OKBtnPayment().tap();
            Print("Tap change payment method");
            appModel.IshoppingApplication().orderBox().tap();
            Print("Taping safepay");
            appModel.IshoppingApplication().SafePayOption().tap();
            appModel.IshoppingApplication().SAFEPAYUSERNAMEEditField().setText(UNAME);
            appModel.IshoppingApplication().SAFEPAYPASSWORDEditField().setText(PASS);
            threadSleep(1000);
            appModel.IshoppingApplication().APPLYButton().tap();
            appModel.IshoppingApplication().PAYNOWButton().tap();
        }
        threadSleep(2000);

        Verification(Verify.isTrue(appModel.IshoppingApplication().OkButton().exists(), "Purchase safepay", "verify that user purchased in success using safepay"));
        appModel.IshoppingApplication().OkButton().tap();

//        appModel.IshoppingApplication().mobileObjectUiObject1().tap();
//
//        if (paymethod.equals("safepay")) {
//            // pay with safepay
//
//            SafePay(false);
//        }
//
//        // todo: need to separate the images of safepay and master credit
//        else {
//            // pay with master credit
//
//            MasterCredit("1234567812347894", "458", UNAME, false);
//        }
    }
    public void tapRightCheckoutButton()throws GeneralLeanFtException{
        Print("Checking for right checkout button");
        if(appModel.IshoppingApplication().cHECKOUT1().exists()){
            Print("Tap checkout-1");
            appModel.IshoppingApplication().cHECKOUT1().tap();
            return;
        }
        if(appModel.IshoppingApplication().cHECKOUT().exists()){
            Print("Tap checkout-2");
            appModel.IshoppingApplication().cHECKOUT().tap();
            return;
        }
        if(appModel.IshoppingApplication().cHECKOUT11().exists()){
            Print("Tap checkout-3");
            appModel.IshoppingApplication().cHECKOUT11().tap();
            return;
        }else{
            Print("couldn't find checkout button");
        }

    }

    public void SafePay(boolean save) throws GeneralLeanFtException {
        //appModel.IshoppingApplication().safepay().tap();
        appModel.IshoppingApplication().SAFEPAYUSERNAMEEditField().setText(UNAME);
        appModel.IshoppingApplication().SAFEPAYPASSWORDEditField().setText(PASS);
        if (!save)
            appModel.IshoppingApplication().SaveSafePAyCredentialsLabel().tap();

        appModel.IshoppingApplication().APPLYButton().tap();
        appModel.IshoppingApplication().PAYNOWButton().tap();
        waitUntilElementExists(appModel.IshoppingApplication().OrderObj());

        Verification(Verify.isTrue(appModel.IshoppingApplication().OrderObj().exists(), "Purchase safepay", "verify that user purchased in success using safepay"));
        appModel.IshoppingApplication().OkButton().tap();
    }

    public void MasterCredit(String cardnum, String CVV, String holdername, boolean save) throws GeneralLeanFtException {
        //appModel.IshoppingApplication().MasterCredit().tap();
        appModel.IshoppingApplication().CARDNUMBEREditField().setText(cardnum);
        appModel.IshoppingApplication().CVVNUMBEREditField().setText(CVV);

        appModel.IshoppingApplication().MmLabel().tap();
        appModel.IshoppingApplication().MonthDropDown().select("07");
        appModel.IshoppingApplication().Done().tap();

        appModel.IshoppingApplication().YyyyLabel().tap();
        appModel.IshoppingApplication().YearDropDown().select("2019");
        appModel.IshoppingApplication().Done().tap();

        appModel.IshoppingApplication().CARDHOLDERNAMEEditField().setText(holdername);

        if (!save)
            appModel.IshoppingApplication().SaveMasterCreditCredenLabel().tap();

        appModel.IshoppingApplication().APPLYButton().tap();
        appModel.IshoppingApplication().PAYNOWButton().tap();
        waitUntilElementExists(appModel.IshoppingApplication().OrderObj());

        Verification(Verify.isTrue(appModel.IshoppingApplication().OrderObj().exists(2), "Purchase Master Credit", "verify that user purchased in success using Master Credit"));
        appModel.IshoppingApplication().OkButton().tap();
    }

    public void Buy(Label category, UiObject item, String payment) throws GeneralLeanFtException {
        appModel.IshoppingApplication().MenuButton().tap();
        category.tap();
        item.tap();
        appModel.IshoppingApplication().ADDTOCARTButton().tap();
        CheckOut(payment);
    }

    public boolean ChangePassword(String oldPass, String newPass) throws GeneralLeanFtException, InterruptedException {
        PASS = newPass;

        appModel.IshoppingApplication().MenuButton().tap();
        appModel.IshoppingApplication().UserSettingsButton().tap();
        waitUntilElementExists(appModel.IshoppingApplication().PasswordObjUiObject());

        appModel.IshoppingApplication().CHANGEPASSWORDButton().tap();

        appModel.IshoppingApplication().OLDPASSWORDEditField().setText(oldPass);
        appModel.IshoppingApplication().NEWPASSWORDEditField().setText(newPass);
        appModel.IshoppingApplication().ConfirmPasswordSignUpEditField().setText(newPass);

        device.swipe(SwipeDirection.UP);
        device.swipe(SwipeDirection.UP);

        appModel.IshoppingApplication().UPDATEUserSettingButton().tap();

        waitUntilElementExists(appModel.IshoppingApplication().MenuObjUiObject());

        SignOut();

        return SignIn();
    }

    /**
     * Function checks if any user signed in
     *
     * @return true if signed in
     */
    public boolean isSignedIn() throws GeneralLeanFtException {
        Print("isSignedIn start");
        boolean result = false;
        print("TAP MenuButton");
        appModel.IshoppingApplication().MenuButton().tap();
        String innerTxt = appModel.IshoppingApplication().LoggedUserName().getText();
        if (!innerTxt.equals("LOG IN"))
            result = true;
        print("TAP MainMenuHome");
        appModel.IshoppingApplication().MainMenuHome().tap();
        Print("isSignedIn end (isSignedIn ? " + result + ") " + (result ? innerTxt : ""));
        return result;
    }

//    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
//        if (!VerifyMethod)
//            throw new GeneralLeanFtException("Verification ERROR - verification of test fails! check runresults.html");
//    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod) {
            GeneralLeanFtException e = new GeneralLeanFtException("Verification ERORR - verification of test fails! check runresults.html");
            printError(e);
            throw e;
        }
    }

    public boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException {
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

    private static void print(String string) {
        System.out.println(string);
    }

    private static void printError(Exception e) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + e.getMessage() + "\n");
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
        print("iOSTests done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min\n"));
    }

}