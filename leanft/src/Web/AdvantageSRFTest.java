package Web;

import com.hp.lft.report.*;
import com.hp.lft.sdk.web.*;
import org.junit.*;
import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;

import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import unittesting.*;

import java.awt.image.RenderedImage;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdvantageSRFTest extends UnitTestClassBase {

    public static final String USERNAME = "WebUser1";
    public static final String PASSWORD = "HPEsw123";
    public static String SearchURL = "";
    public static String appURL = System.getProperty("url", "defaultvalue");
    //    public static String appURL2 = "52.32.172.3";
//    public static String appURL2 = "52.38.138.5:8080";      // PRODUCTION updated
	public static String appURL2 = "16.60.158.84";			// CI
//	public static String appURL2 = "16.59.19.163:8080";		// LOCALHOST
//	public static String appURL2 = "35.162.69.22:8080";		//
//	public static String appURL2 = "156.152.164.67:8080";	//
//	public static String appURL2 = "52.88.236.171";			// PRODUCTION
//    public static String appURL2 = "52.34.13.179:8080";     // QUALLY

    public static String browserTypeValue = System.getProperty("browser_type", "defaultvalue");
    public static String envTypeValue = System.getProperty("env_type", "local");
    //public String osValue = System.getProperty("os", "Windows");
    //public String versionValue = System.getProperty("version", "Windows");
    public BrowserType browserType;

    protected static Browser browser;

    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public AdvantageStagingAppModel appModel;
    private RenderedImage img;


    public AdvantageSRFTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @Rule
    public TestName curTestName = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        ModifiableReportConfiguration reportConfig = ReportConfigurationFactory.createDefaultReportConfiguration();
        reportConfig.setOverrideExisting(true);
//        reportConfig.setTargetDirectory("RunResults"); // The folder must exist under C:\
//        reportConfig.setReportFolder("WebTests");
        reportConfig.setTitle("WEB TESTS REPORT");
//        reportConfig.setDescription("Report Description");
        reportConfig.setSnapshotsLevel(CaptureLevel.All);

        Reporter.init(reportConfig);

        startTimeAllTests = System.currentTimeMillis();
        instance = new AdvantageSRFTest();
        globalSetup(AdvantageSRFTest.class);

        Print("browserTypeValue: " + browserTypeValue);
        Print("envTypeValue: " + envTypeValue);
        Print("appURL: " + appURL);
//        Print("appURL2: " + appURL2);

        Print("Wait for CI to be ready... 2 min");
        Thread.sleep(120000);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //browser.navigate("./RunResults/runresults.html");
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        printTimeWholeTests(elapsedTimeAllTests);
    }

    @Before
    public void setUp() throws Exception {
        startTimeCurrentTest = System.currentTimeMillis();
        printCaptionTest(curTestName.getMethodName());
        initBeforeTest();
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        browser.close();
        printEndOfTest(curTestName.getMethodName());
//        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
//        Print(String.valueOf((elapsedTimeCurrentTest / 1000F) / 60 + " min / "
//                + String.valueOf(elapsedTimeCurrentTest / 1000F) + " sec / "
//                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n"));
    }

    // This internal method checks if a user is already signed in to the web site
    public boolean isSignedIn() {

        String loggedInUserName = getUsernameFromSignOutElement();

        if(loggedInUserName.contains("My account") || loggedInUserName.isEmpty()){
            Print(" User is not logged in. Get name is " + loggedInUserName);
            return false;
        }else{

            Print("isSignedIn end with true and loggedInUserName = '" + loggedInUserName + "'");
            return true;
        }

    }

    public static void Print(String msg) {
        System.out.println(msg);
    }

    private static void printTimeWholeTests(Long millis) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("AdvantageWebTest done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min"));
    }

    private static void printError(Exception e, String objName) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + objName + "\n" + e.getMessage() +  "\n");
    }

    private static void printError(String errorMessage) {
        System.out.println("\n##################################################");
        System.out.println("ERROR: " + errorMessage);
    }

    private static void printCaptionTest(String nameOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest) {
        System.out.println("END " + nameOfTest);
    }

    //This method is an internal function that initializes the tests
    public void initBeforeTest() throws GeneralLeanFtException {
        // Launch the browser
        switch (browserTypeValue) {
            case "Chrome":
                browserType = BrowserType.CHROME;
                break;
            case "Firefox":
                browserType = BrowserType.FIREFOX;
                break;
            case "IE":
                browserType = BrowserType.INTERNET_EXPLORER;
                break;
            default:
                browserType = BrowserType.CHROME;
                break;
        }

        if (envTypeValue.equals("SRF")) {
            System.out.println("Starting to run Tests in SRF");
            browser = SrfLab.launchBrowser(new BrowserDescription.Builder().type(browserType).set("osType", "Windows").set("osVersion", "10").build());
        } else {
            System.out.println("Starting to run Tests in local env.");
            browser = BrowserFactory.launch(browserType);
        }

        if (appURL.equals("defaultvalue"))
            appURL = appURL2;


        Reporter.addRunInformation("URL", appURL);

        // Navigate to the store site
        browserNavigate(appURL);

        // Formulate the search URL
        if (SearchURL.isEmpty()) {
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        if (curTestName.getMethodName().equals("verifySearchUsingURL")) {
            Print("Without this sleep url will be without '/#/' => error in verifySearchUsingURL");
            threadSleep(5000);
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        // Instantiate the application model object
        appModel = new AdvantageStagingAppModel(browser);
    }

    // This internal method gets the username from the text that appears on the SignOutMainIconWebElement object
    public String getUsernameFromSignOutElement() {
        Print("getUsernameFromSignOutElement start");
        // Get the regular expression pattern from the Sign in Out object design time description
        String pattern = null;
        try {
            pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
            pattern = pattern.substring(4,14);

        // Get the actual inner text of the Sign in Out object during runtime
        String signInOutIconElementInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        if (signInOutIconElementInnerText.indexOf(pattern)== 0){
            Print("No user is logged in, get element is - " + signInOutIconElementInnerText);
            return "";
        }
        signInOutIconElementInnerText = signInOutIconElementInnerText.substring(0,signInOutIconElementInnerText.indexOf(pattern)).trim();
        Print("User name - "+ signInOutIconElementInnerText);

//      //Former code
        // Create a Pattern object
//        Pattern r = Pattern.compile(pattern);
//+
//        // Now create matcher object
//        Matcher m = r.matcher(signInOutIconElementInnerText);
//        m.matches();
//        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
//        String loggedInUserName = m.group(1).trim();

            return signInOutIconElementInnerText;
        } catch (GeneralLeanFtException e) {
        e.printStackTrace();
        return "";
        }


    }

    private void browserNavigate(String navigateUrl) {
        Print("browser navigate to " + navigateUrl);
        try {
            browser.navigate(navigateUrl);
        } catch (GeneralLeanFtException e) {
            printError(e, "browser navigate() ERROR");
            fail("GeneralLeanFtException: browser navigate() ERROR");
        }
    }

    private void threadSleep(long millis) {
        try {
            Print("sleep " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Print("\nERROR: " + e.getMessage() +  "\n");
            fail("InterruptedException: failed to sleep for " + millis + " sec");
        }
    }

    private String getWebElementInnerText(WebElement webElement) {
        String result = "";
        try {
            result = webElement.getInnerText();
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: getInnerText from element " + webElement.getClass().getSimpleName());
        }
        return result;
    }

    /**
     * Sign in to the store
     * @return
     */
    public boolean signIn() throws GeneralLeanFtException, ReportException {
        Print("signIn() start");
        boolean isSignedIn = false;

        if (!isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            // Fill in the user name and password
            setValueEditField(appModel.AdvantageShoppingPage().UsernameLoginEditField(), USERNAME);
            setValueEditField(appModel.AdvantageShoppingPage().PasswordLoginEditField(), PASSWORD);
            // Check the Remember Me checkbox
            setCheckBox(appModel.AdvantageShoppingPage().RememberMeCheckBox(), true);
            // Click on sign in button
            clickWebElement(appModel.AdvantageShoppingPage().SIGNINButton());
            Print("Wait for closing login popup window");
            threadSleep(2000);
            isSignedIn = isSignedIn();
        }

        Print("signIn() end (isSignedIn = " + isSignedIn + " )");
        return isSignedIn;
    }

    private void clickWebElement(WebElement webElement) {
        Print("CLICKED " + webElement.getClass().getSimpleName());
        try {
            webElement.click();
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            try {
                Reporter.reportEvent("Error clicking on element", "Could not click on element: " + webElement.getClass().getSimpleName(), Status.Failed);
                Assert.assertTrue("Could not click on element: " + webElement.getClass().getSimpleName(), false);
            } catch (ReportException e1) {
                printError(e1, webElement.getClass().getSimpleName());
            }
            fail("GeneralLeanFtException: couldn't click on element " + webElement.getClass().getSimpleName());
        }
    }

    private void setValueEditField(EditField editField, String value) {
        Print("SET VALUE '" + value + "' to " + editField.getClass().getSimpleName());
        try {
            editField.setValue(value);
        } catch (GeneralLeanFtException e) {
            printError(e, editField.getClass().getSimpleName());
            fail("GeneralLeanFtException: setValue to element " + editField.getClass().getSimpleName());
        }
    }

    // This internal method generates a random username and adds it to the site
    // - When it gets any empty value as input, it adds a random username to the site
    // - When it gets a username and password as input, it adds a new user with these values
    // - The boolean isNegativeTest specifies if to perform a negative test or not, meaning, cannot register the user when the Register form is not properly filled
    public void createNewAccountEx(String pUserName, String pPassword, boolean isNegativeTest) throws GeneralLeanFtException, ReportException {
        // In order to create a new account, we must be signed out
        signOut();

        // Click the Sign in icon
        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        // Click the Create New Account link
        clickWebElement(appModel.AdvantageShoppingPage().CREATENEWACCOUNTLink());

        // Set the user name and submit the new account
        // Make sure that the user name does not exist
        //int triesNumber = 5;
        //boolean newUserCreated = false;
        String username = pUserName;
        String password = pPassword;
        boolean getRandomName = true;

        // Get a random user name
        if (!pUserName.isEmpty()) {
            //triesNumber = 1;
            getRandomName = false;
        }

        if (getRandomName) {
            username = getRandomUserName();
            password = PASSWORD;
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit(), username);

        // Fill the Create Account form

        if (!isNegativeTest) { // Do not fill the mail field in a negative test
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountEmailEditField(), "john@hpe.com");
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordEditField(), password);

        if (!isNegativeTest) { // Do not confirm the password in a negative test
            setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPasswordConfirmEditField(), password);
        }

        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountFirstNameEditField(), "John");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountLastNameEditField(), "HPE");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPhoneNumberEditField(), "+97235399999");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountCityEditField(), "Yehud");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountAddressEditField(), "Shabazi 19");
        setValueEditField(appModel.AdvantageShoppingPage().CreateAccountPostalCodeEditField(), "56100");
        //appModel.AdvantageShoppingPage().CreateAccountReceiveOffersCheckBox().set(false);
        setCheckBox(appModel.AdvantageShoppingPage().CreateAccountAgreeToTermsCheckBox(), true);

        if (!isNegativeTest) {
            waitUntilElementExists(appModel.AdvantageShoppingPage().REGISTERButton());
            // Click the Register button
            clickWebElement(appModel.AdvantageShoppingPage().REGISTERButton());

            browserSync();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

            String curGetUsernameFromSignOutElement = getUsernameFromSignOutElement();
            Print("'" + username + "' == '" + curGetUsernameFromSignOutElement + "'");

            Boolean result = username.equals(curGetUsernameFromSignOutElement);
            img = browser.getPage().getSnapshot();
            if (result) {
                Reporter.reportEvent("Verify username", "Names are the same", Status.Passed, img);
            } else {
                Reporter.reportEvent("Verify username", "Names are not the same", Status.Failed, img);
            }


            // Verify that the user name we added now appears in the inner text of the Sign In element
//            Verify.areEqual(username, curGetUsernameFromSignOutElement,
//                    "Verification - Create New Account", "  Verify that a new account was created successfully for user name: " + username + ".");
//            Assert.assertEquals("Verification - Create New Account:  Verify that a new account was created successfully for user name: " + username + ".",
//                    username, curGetUsernameFromSignOutElement);
        } else { // In a negative test, verify that the Register button is indeed disabled
            // Verify that a new account cannot be created successfully
            try {

                if (appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2)) {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Create New Account Negative test", "Verify that a new account cannot be created successfully.", Status.Passed, img);
                } else {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Create New Account Negative test", "Verify that a new account cannot be created successfully.", Status.Failed, img);
                }

//                Verification(Verify.isFalse(appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2), "Verification - Create New Account Negative test", "Verify that a new account cannot be created successfully."));
            } catch (GeneralLeanFtException e) {
                printError(e, "createNewAccountEx");
                fail("GeneralLeanFtException: createNewAccountEx");
            }
        }
    }

    //This internal method signs the user out
    public void signOut() {
        if (isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            // Click the sign out link
            clickWebElement(appModel.AdvantageShoppingPage().SignOutWebElement());
        }
    }

    //This internal method generates a random username
    public String getRandomUserName() {
        SecureRandom random = new SecureRandom();
        String randomString = new BigInteger(24, random).toString(16); // Generate a random string by hex number maximum 0xFFFFFF
        int len = 6 - randomString.length(); // Pad the string so it will contain 6 characters
        String padding = "";
        if (len > 0)
            padding = String.format("%0" + len + "d", 0);
        return "John_" + padding + randomString;
    }

    private void setCheckBox(CheckBox checkBox, Boolean value) {
        Print("SET '" + value + "' to " + checkBox.getClass().getSimpleName());
        try {
            checkBox.set(value);
        } catch (GeneralLeanFtException e) {
            printError(e, checkBox.getClass().getSimpleName());
            fail("GeneralLeanFtException: set '" + value + "' to element " + checkBox.getClass().getSimpleName());
        }
    }

    // This internal method waits until an object exists and visible
    public boolean waitUntilElementExists(WebElement webElement) {
        Print("WAIT UNTIL ELEMENT EXISTS " + webElement.getClass().getSimpleName());
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElement, new WaitUntilTestObjectState.WaitUntilEvaluator<WebElement>() {
                public boolean evaluate(WebElement we) {
                    try {
                        return we.exists() && we.isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: " + webElement.getClass().getSimpleName());
        }
        return result;
    }

    private void browserSync() {
        try {
            browser.sync();
        } catch (GeneralLeanFtException e) {
            printError(e, "browser.sync() error");
            fail("GeneralLeanFtException: browser.sync() error");
        }
    }

    public void Checkout() {
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        Print("Wait for page to bee loaded");
        threadSleep(10000);
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());

        // if SafePay userName and pass are empty will be error
        // Set the payment method user name
        setValueEditField(appModel.AdvantageShoppingPage().SafePayUsernameEditField(), "HPE123");
        // Set the payment method password
        setValueEditField(appModel.AdvantageShoppingPage().SafePayPasswordEditField(), "Aaaa1");

        Print("Wait for page to bee loaded");
        threadSleep(10000);
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButton());
        // appModel.AdvantageShoppingPage().PAYNOWButtonManualPayment().click();
        browserSync();
    }

    private boolean existsWebElement(WebElement webElement) {
        boolean result = false;
        Print("EXISTS " + webElement.getClass().getSimpleName());
        try {
            result = webElement.exists();
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: exists on element " + webElement.getClass().getSimpleName());
        }
        return result;
    }

    private void checkWithReporterIsTrueOnly(Boolean isValue, String stepName, String description) throws ReportException, GeneralLeanFtException {
        img = browser.getPage().getSnapshot();
        if (isValue) {
            Reporter.reportEvent(stepName, description, Status.Passed, img);
        } else {
            Reporter.reportEvent(stepName, description, Status.Failed, img);
        }
    }

    /**
     * This method empties the shopping cart
     */
    public void emptyTheShoppingCart() {
        if (!isCartEmpty()) {
            Print("Empty the cart....");
            // Navigate to the cart
            clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
            Print("Wait for cart to be loaded");
            threadSleep(4000);

            // Get the rows number from the cart table
            int numberOfRowsInCart = 0;
            try {
                numberOfRowsInCart = appModel.AdvantageShoppingPage().CartTable().getRows().size();
            } catch (GeneralLeanFtException e) {
                printError(e, "appModel.AdvantageShoppingPage().CartTable().getRows().size()");
                fail("GeneralLeanFtException: emptyTheShoppingCart appModel.AdvantageShoppingPage().CartTable().getRows().size()");
            }
            int numberOfRelevantProductRowsInCart = numberOfRowsInCart - 3; // Removing the non-relevant rows number from our counter. These are the title etc.. and rows that do not represent actual products
            Print("numberOfRelevantProductRowsInCart = " + numberOfRelevantProductRowsInCart);

            // Iterate and click the "Remove" link for all products
            for (; numberOfRelevantProductRowsInCart > 0; numberOfRelevantProductRowsInCart--) {
                clickWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());
            }
            Print("cart is empty");
        }
    }

    // This internal method checks if the shopping cart is empty or not
    public boolean isCartEmpty() {
        if (getCartProductsNumberFromCartObjectInnerText() == 0)
            return true;
        return false;
    }

    // This internal method gets a products category object and a product object and adds them to the cart
    // WebElementNodeBase productsCategory - 	the category object
    // ImageNodeBase product - 					the specific product object
    public void selectItemToPurchase(WebElement productsCategory, WebElement product, int productQuantity) {
        Print("selectItemToPurchase() start");
        // Pick the product's category
        clickWebElement(productsCategory);

        // Pick the specific product
        clickWebElement(product);

        // Select the first non-selected available color for the product
        try {
            if(appModel.AdvantageShoppingPage().SelectedColorForEnv17().exists())
                clickWebElement(appModel.AdvantageShoppingPage().SelectedColorForEnv17());
            else
                clickWebElement(appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement());
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        // If the quantity is more than 1, set this value in the quantity edit-field
        if (productQuantity != 1) {
            setValueEditField(appModel.AdvantageShoppingPage().QuantityOfProductWebEdit(), Integer.toString(productQuantity));
        }

        // Add it to the cart
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        Print("selectItemToPurchase() end");
    }

    public void selectItemToPurchase(WebElement productsCategory, WebElement product) {
        selectItemToPurchase(productsCategory, product, 1); // The default product quantity is 1
    }

    public void checkOutAndPayMasterCredit(String cardnum, String CVV, String holdername, boolean save) throws ReportException {
        Print("checkOutAndPayMasterCredit start");
        // Checkout the cart for purchase
        // Click the cart icon
        clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
        // Click the checkout button
        clickWebElement(appModel.AdvantageShoppingPage().CheckOutButton());
        // Click Next to continue the purchase wizard
        Print("Wait for window to be loaded");
        threadSleep(5000);
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());
        // Select the payment method
        clickWebElement(appModel.AdvantageShoppingPage().MasterCreditImage());

        // Set the card number
        setValueEditField(appModel.AdvantageShoppingPage().CardNumberEditField(), cardnum);
        setValueEditField(appModel.AdvantageShoppingPage().CardNumberEditField(), cardnum);
        // Set the CVV number
        setValueEditField(appModel.AdvantageShoppingPage().CvvNumberEditField(), CVV);
        setValueEditField(appModel.AdvantageShoppingPage().CvvNumberEditField(), CVV);
        // Set the card holder name
        setValueEditField(appModel.AdvantageShoppingPage().CardholderNameEditField(), holdername);
        if (!save) {
            // Set the Remember Me checkbox to true or false
            setCheckBox(appModel.AdvantageShoppingPage().SaveMasterCreditCheckBox(), false);
        }

        //appModel.AdvantageShoppingPage().NEXTButton().click();
        // Click the "Pay Now" button
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButtonManualPayment());

        // Verify that the product was purchased
        try {
            Boolean isProductPurchased = appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2);
            // Take screenshot

            if (isProductPurchased) {
                img = browser.getPage().getSnapshot();
                Reporter.reportEvent("Verify if product purchased", "Product successfully purchased", Status.Passed, img);
            } else {
                img = browser.getPage().getSnapshot();
                Reporter.reportEvent("Verify if product purchased", "Product didn't purchase", Status.Failed, img);
            }

            //Verification(Verify.isTrue(isProductPurchased, "Verification - Product Purchase MasterCredit:", " Verify that the product was purchased successfully with MasterCredit "));
        } catch (GeneralLeanFtException e) {
            printError(e, "appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
            fail("GeneralLeanFtException: appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
        }
        Print("checkOutAndPayMasterCredit end");
    }

    // This internal method returns the number of items in the shopping cart by the inner text of the cart icon object
    public int getCartProductsNumberFromCartObjectInnerText() {
        Print("getCartProductsNumberFromCartObjectInnerText start");
        int productsNumberInCart = 0;

        // Get the regular expression pattern from the Cart icon object design time description
        //String pattern = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Get the actual inner text of the Cart icon object during runtime
        String advantageCartIcontInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().LinkCartIcon());
        Print("advantageCartIcontInnerText: " + advantageCartIcontInnerText);

        // Create a Pattern object
        //Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        //Matcher m = r.matcher(advantageCartIcontInnerText);
        //m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String productsNumberInCartString = advantageCartIcontInnerText.split("[ ]+")[0];//m.group(1).trim();
        Print("productsNunberInCartString: " + productsNumberInCartString);
        if (!productsNumberInCartString.isEmpty())
            productsNumberInCart = Integer.parseInt(productsNumberInCartString);

        Print("getCartProductsNumberFromCartObjectInnerText end (products in cart: " + productsNumberInCart);
        return productsNumberInCart;
    }

    // This method will checkout to the cart and pay for the cart content
    // The boolean fillCredentials specifies if to fill the credentials in the form or not
    public void checkOutAndPaySafePay(boolean fillCredentials) throws ReportException {
        Print("checkOutAndPaySafePay start");
        // Checkout the cart for purchase
        // Click the cart icon
        clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
        // Click the checkout button
        clickWebElement(appModel.AdvantageShoppingPage().CheckOutButton());
        Print("Wait for window to be loaded");
        threadSleep(5000);
        // Click Next to continue the purchase wizard
        clickWebElement(appModel.AdvantageShoppingPage().NEXTButton());
        // Select the payment method
        clickWebElement(appModel.AdvantageShoppingPage().SafepayImage());

        if (fillCredentials) {
            // Set the payment method user name
            setValueEditField(appModel.AdvantageShoppingPage().SafePayUsernameEditField(), "HPE123");
            // Set the payment method password
            setValueEditField(appModel.AdvantageShoppingPage().SafePayPasswordEditField(), "Aaaa1");
            // Set the Remember Me checkbox to true or false
            setCheckBox(appModel.AdvantageShoppingPage().SaveChangesInProfileForFutureUse(), true);
        }

        // Click the "Pay Now" button
        clickWebElement(appModel.AdvantageShoppingPage().PAYNOWButton());

        try {
            // Verify that the product was purchased
            if (fillCredentials) {
                if (appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)) {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully", Status.Passed, img);
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully", Status.Failed, img);
                } else {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully", Status.Failed, img);
                }
//                Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2),
//                        "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
            } else {
                if (appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)) {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully.", Status.Passed, img);
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully.", Status.Failed, img);
                } else {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully.", Status.Failed, img);
                }
//                Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2),
//                        "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
            }
        } catch (GeneralLeanFtException e) {
            printError(e, "appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
            fail("GeneralLeanFtException: appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
        }
        Print("checkOutAndPaySafePay end");
    }

    public void checkOutAndPaySafePay() throws ReportException {
        checkOutAndPaySafePay(true); // Default value is true - fill credentials
    }

    private void checkWithReporterIsFalse(Boolean isValue, String stepName, String description) throws ReportException, GeneralLeanFtException {
        img = browser.getPage().getSnapshot();
        if (isValue) {
            printError(stepName + ". " + description);
            Reporter.reportEvent(stepName, description, Status.Failed, img);
            fail("Should be " + !isValue);
        } else {
            Reporter.reportEvent(stepName, description, Status.Passed, img);
        }
    }

    /**
     * Adding main user if not exists
     * @return true - main user added, false - main user not added
     */
    @Test
    public void addMainUserIfNotExists() throws GeneralLeanFtException, ReportException {
        signIn();       // Sign in to the store

        if (!isSignedIn()) {
            clickWebElement(appModel.AdvantageShoppingPage().CloseSignInPopUpBtnWebElement());
            createNewAccountEx(USERNAME, PASSWORD, false);
        }
    }

    /**
     * login
     purchase�a product - remember it's name
     goto orders history page:
     Search by name�- look for the number you just created
     => the search result shall show all relevant entries

     Delete an order =>delete the order you just created, when the user clicks delete validate that the application informs the user�that his order will be cancelled, and ask him to approve.�

     Validate that the following was added to the order grid:
     Order time
     */
    @Test
    public void orderServiceTest() throws GeneralLeanFtException, ReportException {
        signIn();

        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();
        clickWebElement(appModel.AdvantageShoppingPage().LaptopsImg());

        // Select an item to purchase and add it to the cart
        //selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg, appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS());
        clickWebElement(appModel.AdvantageShoppingPage().laptopFororderService());
        String ProductName = getWebElementInnerText(appModel.AdvantageShoppingPage().LaptopName());
        Print("ORDER:" + ProductName);

        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        Print("Wait while product adding to the shopping cart");
        threadSleep(4000);

        Checkout();

        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().MyOrdersWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().OrderSearchWebElement());
        setValueEditField(appModel.AdvantageShoppingPage().SearchOrderEditField(), ProductName);

        // TODO: check if previously purchased product in this list
        if (existsWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement())) {
            clickWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());

            checkWithReporterIsTrueOnly(existsWebElement(appModel.AdvantageShoppingPage().RemoveFromOrderValidate()),
                    "Verify Search orders", "Verify that the alert window element exists.");
            clickWebElement(appModel.AdvantageShoppingPage().YesCANCELButton());
        } else {
            printError("Empty list of orders");
        }
    }

    @Test
    public void alvaro() throws GeneralLeanFtException, ReportException{


        BrowserDescription bd = new BrowserDescription();

      //  bd.setType(BrowserType.INTERNET_EXPLORER);
        bd.set("type", BrowserType.INTERNET_EXPLORER) ;
        //bd.set("type", "INTERNET_EXPLORER")

        bd.set("version", "11");

        bd.set("osType", "Windows");

        bd.set("osVersion", "10");

        bd.set("testName", "My LeanFT web test");

        Browser browser = SrfLab.launchBrowser(bd);

        browser.navigate("http://www.advantageonlineshopping.com/");
        Link tabletsTxtLink = browser.describe(Link.class, new LinkDescription.Builder()
                .innerText("TABLETS")
                .tagName("SPAN").build());
        tabletsTxtLink.click();
        browser.back();
        tabletsTxtLink.click();
        browser.back();

    }

    @Test
    public void purchaseMasterCreditLaptopTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().LaptopsImg(), appModel.AdvantageShoppingPage().HPENVY17tTouchLaptop());

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
    }

    // This test purchases the first item in the Tablets category
    @Test
    public void purchaseSafePayTabletTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().TabletsImgWebElement(), appModel.AdvantageShoppingPage().TabletHPPro608G1());

        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    /**
     * Perform logout and make sure you are not logged in
     * @throws GeneralLeanFtException
     * @throws ReportException
     */
    @Test
    public void logOutTest() throws GeneralLeanFtException, ReportException {
        signIn();
        browserSync();
        signOut();
        Print("Wait for user will sign out");
        threadSleep(5000);
        checkWithReporterIsFalse(isSignedIn(), "Verify logout", "Verify if the user really signed out from site");
    }



}