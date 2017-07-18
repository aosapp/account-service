package Web;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.Verify;

import unittesting.*;

// Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdvantageWebTest extends UnitTestClassBase {

    public static final String USERNAME = "johnhpe1";
    public static final String PASSWORD = "HPEsw123";
    public static String SearchURL = "";
    public static String appURL = System.getProperty("url", "defaultvalue");
    public static String appURL2 = "52.32.172.3";
//	public static String appURL2 = "16.60.158.84";			// CI
//	public static String appURL2 = "16.59.19.163:8080";		// LOCALHOST
//	public static String appURL2 = "35.162.69.22:8080";		//
//	public static String appURL2 = "156.152.164.67:8080";	//
//	public static String appURL2 = "52.88.236.171";			// PRODUCTION

    public String browserTypeValue = System.getProperty("browser_type", "defaultvalue");
    public BrowserType browserType;

    protected static Browser browser;

    private static long startTimeAllTests;
    private long startTimeCurrentTest;
    private static long elapsedTimeAllTests;
    private long elapsedTimeCurrentTest;

    public AdvantageStagingAppModel appModel;

    public AdvantageWebTest() {

    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        startTimeAllTests = System.currentTimeMillis();
        instance = new AdvantageWebTest();
        globalSetup(AdvantageWebTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //browser.navigate("./RunResults/runresults.html");
        globalTearDown();
        elapsedTimeAllTests = System.currentTimeMillis() - startTimeAllTests;
        Print("AdvantageWebTest done in: " + String.valueOf((elapsedTimeAllTests / 1000F) / 60 + " min"));
    }

    @Before
    public void setUp() throws Exception {
        startTimeCurrentTest = System.currentTimeMillis();
        initBeforeTest();
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        browser.close();
        elapsedTimeCurrentTest = System.currentTimeMillis() - startTimeCurrentTest;
        Print(String.valueOf((elapsedTimeCurrentTest / 1000F) / 60 + " min / "
                + String.valueOf(elapsedTimeCurrentTest / 1000F) + " sec / "
                + String.valueOf(elapsedTimeCurrentTest) + " millisec\n"));
    }

    // This internal method checks if a user is already signed in to the web site
    public boolean isSignedIn() throws GeneralLeanFtException {
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        String loggedInUserName = getUsernameFromSignOutElement();
        if (loggedInUserName.isEmpty())
            return false;
        return true;
    }

    public static void Print(String msg) {
        System.out.println(msg);
    }

    // This internal method gets the username from the text that appears on the SignOutMainIconWebElement object
    public String getUsernameFromSignOutElement() throws GeneralLeanFtException {
        // Get the regular expression pattern from the Sign in Out object design time description
        String pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
        // Get the actual inner text of the Sign in Out object during runtime
        String signInOutIconElementInnerText = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(signInOutIconElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String loggedInUserName = m.group(1).trim();
        return loggedInUserName;
    }

    // This internal method gets the shipping costs from the text that appears on the SHIPPINGCostWebElement object
    public double getShippingCostFromShippingWebElement() throws GeneralLeanFtException {
        Print("getShippingCostFromShippingWebElement start");
        // Get the regular expression pattern from the SHIPPINGCostWebElement object design time description
        String pattern = appModel.AdvantageShoppingPage().SHIPPINGCostWebElement().getDescription().getInnerText().toString();
        // Get the actual inner text of the SHIPPINGCostWebElement object during runtime
        String SHIPPINGCostWebElementInnerText = appModel.AdvantageShoppingPage().SHIPPINGCostWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(SHIPPINGCostWebElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String shippingCostString = m.group(1).trim();

        // TODO: remove if test is working
//        double shippingCost = Double.parseDouble(shippingCostString);
//        return shippingCost;
        Print("getShippingCostFromShippingWebElement end");
        return Double.parseDouble(shippingCostString);
    }

    //This internal method signs the user in - a user must be in the system:
    //username - johnhpe1, password - HPEsw123
    public boolean signIn() throws GeneralLeanFtException, InterruptedException {
        Print("signIn() start");
        boolean isSignedIn = true;

        if (!isSignedIn()) {
            // Click the sign-in icon
            appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
            // Fill in the user name and password
            appModel.AdvantageShoppingPage().UsernameLoginEditField().setValue(USERNAME);
            appModel.AdvantageShoppingPage().PasswordLoginEditField().setValue(PASSWORD);
            // Check the Remember Me checkbox
            appModel.AdvantageShoppingPage().RememberMeCheckBox().set(true);
            // Click on sign in button
            appModel.AdvantageShoppingPage().SIGNINButton().click();

            Thread.sleep(2000);

            isSignedIn = isSignedIn();
            Verify.isTrue(isSignedIn, "Verification - Sign In", "Verify that the user " + USERNAME + " signed in properly.");
        }
        Print("signIn() end (isSignedIn = " + isSignedIn + " )");
        return isSignedIn;
    }

    //This internal method signs the user out
    public void signOut() throws GeneralLeanFtException {
        if (isSignedIn()) {
            // Click the sign-in icon
            appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
            // Click the sign out link
            appModel.AdvantageShoppingPage().SignOutWebElement().click();
        }
    }

    // This internal method gets a products category object and a product object and adds them to the cart
    // WebElementNodeBase productsCategory - 	the category object
    // ImageNodeBase product - 					the specific product object
    public void selectItemToPurchase(WebElement productsCategory, WebElement product, int productQuantity) throws GeneralLeanFtException, InterruptedException {
        Print("selectItemToPurchase() start");
        // Pick the product's category
        Thread.sleep(2000);
        Print("productsCategory click");
        productsCategory.click();
        Thread.sleep(2000);

        // Pick the specific product
        Print("product click");
        product.click();
        Thread.sleep(1000);

        // Select the first non-selected available color for the product
        Print("ColorSelectorFirstWebElement click");
        appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement().click();
        Thread.sleep(1000);

        // If the quantity is more than 1, set this value in the quantity edit-field
        if (productQuantity != 1) {
            Print("QuantityOfProductWebEdit setValue" + Integer.toString(productQuantity));
            appModel.AdvantageShoppingPage().QuantityOfProductWebEdit().setValue(Integer.toString(productQuantity));
        }

        // Add it to the cart
        Print("ADDTOCARTButton click");
        appModel.AdvantageShoppingPage().ADDTOCARTButton().click();
        Thread.sleep(2000);
        Print("selectItemToPurchase() end");
    }

    public void selectItemToPurchase(WebElement productsCategory, WebElement product) throws GeneralLeanFtException, InterruptedException {
        selectItemToPurchase(productsCategory, product, 1); // The default product quantity is 1
    }

    // This method will checkout to the cart and pay for the cart content
    // The boolean fillCredentials specifies if to fill the credentials in the form or not
    public void checkOutAndPay(boolean fillCredentials) throws GeneralLeanFtException {
        Print("checkOutAndPay start");
        // Checkout the cart for purchase
        // Click the cart icon
        Print("CartIcon click");
        appModel.AdvantageShoppingPage().CartIcon().click();
        // Click the checkout button
        Print("CHECKOUTHoverButton click");
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();
        // Click Next to continue the purchase wizard
        Print("NEXTButton click");
        appModel.AdvantageShoppingPage().NEXTButton().click();
        // Select the payment method
        Print("SafepayImage click");
        appModel.AdvantageShoppingPage().SafepayImage().click();

        if (fillCredentials) {
            // Set the payment method user name
            Print("SafePayUsernameEditField setValue HPE123");
            appModel.AdvantageShoppingPage().SafePayUsernameEditField().setValue("HPE123");
            // Set the payment method password
            Print("SafePayPasswordEditField setValue Aaaa1");
            appModel.AdvantageShoppingPage().SafePayPasswordEditField().setValue("Aaaa1");
            // Set the Remember Me checkbox to true or false
            appModel.AdvantageShoppingPage().SaveChangesInProfileForFutureUse().set(true);
        }

        //appModel.AdvantageShoppingPage().NEXTButton().click();
        // Click the "Pay Now" button
        Print("PAYNOWButton click");
        appModel.AdvantageShoppingPage().PAYNOWButton().click();

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement());

        // Verify that the product was purchased
        if (fillCredentials)
            Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
        else
            Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase:", " Verify that the product was purchased successfully"));
        Print("checkOutAndPay end");
    }

    public void checkOutAndPay() throws GeneralLeanFtException {
        checkOutAndPay(true); // Default value is true - fill credentials
    }


    public void checkOutAndPayMasterCredit(String cardnum, String CVV, String holdername, boolean save) throws GeneralLeanFtException {
        Print("checkOutAndPayMasterCredit start");
        // Checkout the cart for purchase
        // Click the cart icon
        appModel.AdvantageShoppingPage().CartIcon().click();
        // Click the checkout button
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();
        // Click Next to continue the purchase wizard
        appModel.AdvantageShoppingPage().NEXTButton().click();
        // Select the payment method
        appModel.AdvantageShoppingPage().MasterCreditImage().click();


        // Set the card number
        appModel.AdvantageShoppingPage().CardNumberEditField().setValue(cardnum);
        // Set the CVV number
        appModel.AdvantageShoppingPage().CvvNumberEditField().setValue(CVV);
        // Set the card holder name
        appModel.AdvantageShoppingPage().CardholderNameEditField().setValue(holdername);
        if (!save) {
            // Set the Remember Me checkbox to true or false
            appModel.AdvantageShoppingPage().SaveMasterCreditCheckBox().set(false);
        }


        //appModel.AdvantageShoppingPage().NEXTButton().click();
        // Click the "Pay Now" button
        appModel.AdvantageShoppingPage().PAYNOWButton().click();

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement());

        // Verify that the product was purchased
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2), "Verification - Product Purchase MasterCredit:", " Verify that the product was purchased successfully with MasterCredit "));
        Print("checkOutAndPayMasterCredit end");
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

        browser = BrowserFactory.launch(browserType);
        if (appURL.equals("defaultvalue"))
            appURL = appURL2;

        Reporter.addRunInformation("URL", appURL);

        // Navigate to the store site
        browser.navigate(appURL);
        browser.sync();

        // Formulate the search URL
        if (SearchURL.isEmpty()) {
            SearchURL = browser.getURL();
            if (!SearchURL.endsWith("/")) {
                SearchURL = SearchURL + "/";
            }
            SearchURL = SearchURL + "search/";
        }

        // Instantiate the application model object
        appModel = new AdvantageStagingAppModel(browser);
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

    // This internal method generates a random username and adds it to the site
    // - When it gets any empty value as input, it adds a random username to the site
    // - When it gets a username and password as input, it adds a new user with these values
    // - The boolean isNegativeTest specifies if to perform a negative test or not, meaning, cannot register the user when the Register form is not properly filled
    public void createNewAccountEx(String pUserName, String pPassword, boolean isNegativeTest) throws GeneralLeanFtException, ReportException, InterruptedException {
        // In order to create a new account, we must be signed out
        signOut();

        // Click the Sign in icon
        Print("click SignOutMainIconWebElement()");
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();

        // Click the Create New Account link
        Print("click CREATENEWACCOUNTLink");
        appModel.AdvantageShoppingPage().CREATENEWACCOUNTLink().click();

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

        waitUntilElementExists(appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit());
        Thread.sleep(2000);
        Print("CreateAccountUsernameWebEdit setValue: " + username);
        appModel.AdvantageShoppingPage().CreateAccountUsernameWebEdit().setValue(username);

        // Fill the Create Account form

        if (!isNegativeTest) { // Do not fill the mail field in a negative test
            Print("CreateAccountEmailEditField setValue: john@hpe.com ");
            appModel.AdvantageShoppingPage().CreateAccountEmailEditField().setValue("john@hpe.com");
        }

        Print("CreateAccountPasswordEditField setValue: " + password);
        appModel.AdvantageShoppingPage().CreateAccountPasswordEditField().setValue(password);

        if (!isNegativeTest) { // Do not confirm the password in a negative test
            Print("CreateAccountPasswordConfirmEditField setValue: " + password);
            appModel.AdvantageShoppingPage().CreateAccountPasswordConfirmEditField().setValue(password);
        }

        appModel.AdvantageShoppingPage().CreateAccountFirstNameEditField().setValue("John");
        appModel.AdvantageShoppingPage().CreateAccountLastNameEditField().setValue("HPE");
        appModel.AdvantageShoppingPage().CreateAccountPhoneNumberEditField().setValue("+97235399999");
        appModel.AdvantageShoppingPage().CreateAccountCityEditField().setValue("Yehud");
        appModel.AdvantageShoppingPage().CreateAccountAddressEditField().setValue("Shabazi 19");
        appModel.AdvantageShoppingPage().CreateAccountPostalCodeEditField().setValue("56100");
        //appModel.AdvantageShoppingPage().CreateAccountReceiveOffersCheckBox().set(false);
        appModel.AdvantageShoppingPage().CreateAccountAgreeToTermsCheckBox().set(true);

        if (!isNegativeTest) {
            waitUntilElementExists(appModel.AdvantageShoppingPage().REGISTERButton());
            // Click the Register button
            appModel.AdvantageShoppingPage().REGISTERButton().click();

            Thread.sleep(2000);

            browser.sync();
            waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

            // Verify that the user name we added now appears in the inner text of the Sign In element
            Verify.areEqual(username, getUsernameFromSignOutElement(), "Verification - Create New Account", "  Verify that a new account was created successfully for user name: " + username + ".");

            Assert.assertEquals("Verification - Create New Account:  Verify that a new account was created successfully for user name: " + username + ".", username, getUsernameFromSignOutElement());
        } else { // In a negative test, verify that the Register button is indeed disabled
            // Verify that a new account cannot be created successfully
            Verification(Verify.isFalse(appModel.AdvantageShoppingPage().CreateAccountREGISTERNotValidWebElement().exists(2), "Verification - Create New Account Negative test", "Verify that a new account cannot be created successfully."));
        }
    }

    // This internal method waits until an object exists and visible
    public boolean waitUntilElementExists(WebElement webElem) throws GeneralLeanFtException {
        return WaitUntilTestObjectState.waitUntil(webElem, new WaitUntilEvaluator<WebElement>() {
            public boolean evaluate(WebElement we) {
                try {
                    return we.exists() && we.isVisible();
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public boolean waitUntilElementExists(WebElement webElem, long time) throws GeneralLeanFtException {
        return WaitUntilTestObjectState.waitUntil(webElem, new WaitUntilEvaluator<WebElement>() {
            public boolean evaluate(WebElement we) {
                try {
                    return we.exists() && we.isVisible();
                } catch (Exception e) {
                    return false;
                }
            }
        }, time);
    }


    // This internal method empties the shopping cart
    public void emptyTheShoppingCart() throws GeneralLeanFtException, InterruptedException {
        if (!isCartEmpty()) {

            Print("sleep 2000");
            Thread.sleep(2000);

            Print("Empty the cart....");
            // Navigate to the cart
            Print("CartIcon click");
            appModel.AdvantageShoppingPage().CartIcon().click();
            browser.sync();

            // Get the rows number from the cart table
            int numberOfRowsInCart = appModel.AdvantageShoppingPage().CartTable().getRows().size();
            Print("nubmerOfRowsInCart = " + numberOfRowsInCart);
            int numberOfRelevantProductRowsInCart = numberOfRowsInCart - 3; // Removing the non-relevant rows number from our counter. These are the title etc.. and rows that do not represent actual products
            Print("numberOfRelevantProductRowsInCart = " + numberOfRelevantProductRowsInCart);

            // Iterate and click the "Remove" link for all products
            for (; numberOfRelevantProductRowsInCart > 0; numberOfRelevantProductRowsInCart--) {
//                waitUntilElementExists(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement(), 10000);

                Print("sleep 2000");
                Thread.sleep(2000);

                Print("FirstRemoveItemFromCartLinkWebElement click");
                appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement().click();
                Thread.sleep(2000);// Remove the top product from the cart
            }

            Print("cart is empty");
            //browser.refresh();
        }
    }

    // This internal method returns the number of items in the shopping cart by the inner text of the cart icon object
    public int getCartProductsNumberFromCartObjectInnerText() throws GeneralLeanFtException {
        Print("getCartProductsNumberFromCartObjectInnerText start");
        int productsNunberInCart = 0;

        // Get the regular expression pattern from the Cart icon object design time description
        //String pattern = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Get the actual inner text of the Cart icon object during runtime
        String advantageCartIcontInnerText = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Create a Pattern object
        //Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        //Matcher m = r.matcher(advantageCartIcontInnerText);
        //m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String productsNunberInCartString = advantageCartIcontInnerText.split("[ ]+")[0];//m.group(1).trim();
        if (!productsNunberInCartString.isEmpty())
            productsNunberInCart = Integer.parseInt(productsNunberInCartString);

        Print("Product number in cart: " + productsNunberInCartString);
        Print("getCartProductsNumberFromCartObjectInnerText end");
        return productsNunberInCart;
    }

    // This internal method checks if the shopping cart is empty or not
    public boolean isCartEmpty() throws GeneralLeanFtException {
        if (getCartProductsNumberFromCartObjectInnerText() == 0)
            return true;
        return false;
    }

    //This test method creates the default user to be used in the tests, if it already does not exist - a user must be in the system:
    //username - johnhpe1, password - HPEsw123
    @Test
    public void AddMainUserIfNotExists() throws GeneralLeanFtException, ReportException, InterruptedException {
        Print("----- 1 START AddMainUserIfNotExists-----------------------------");
        // Sign in to the store
        signIn();

        if (!isSignedIn()) {
            appModel.AdvantageShoppingPage().CloseSignInPopUpBtnWebElement().click();
            createNewAccountEx(USERNAME, PASSWORD, false);
        }
        Print("----- 1 END AddMainUserIfNotExists-----------------------------");
    }

    // This test purchases the first item in the Speakers category
    @Test
    public void purchaseSpeakersTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 16 START purchaseSpeakersTest-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
        Print("----- 16 END purchaseSpeakersTest-----------------------------");
    }

    // This test purchases a 1000 of the first item in the Speakers category
    @Test
    public void purchase1000SpeakersNegativeTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 12 START purchase1000SpeakersNegativeTest-----------------------------");

        // Sign in to the store
        signIn();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click: ");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        Print("sleep 3000");
        Thread.sleep(3000);

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage(), 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        browser.sync();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Verify that the quantity that was actually added to the cart is 10 and not a 1000
        int productsQuantityInCart = getCartProductsNumberFromCartObjectInnerText();
        Verification(Verify.isTrue(productsQuantityInCart == 10, "Verification - Purchase 1000 Speakers negative test", " Verify that you cannot buy a 1000 items. You can only buy 10 max."));

        // Pay for the item
        checkOutAndPay(); // Verification inside
        Print("----- 12 END purchase1000SpeakersNegativeTest-----------------------------");
    }

    // This test verifies that the shipping costs is free when purchasing 1 item
    // 	and that the shipping cost for 4 items is not free
    @Test
    public void verifyShippingCostsTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 21 START verifyShippingCostsTest-----------------------------");

        // Sign in to the store
        signIn();

        double shippingCost = 0;

        // Purchase 1 item

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Navigate to the shopping cart table
        Print("LinkCartIcon click");
        appModel.AdvantageShoppingPage().LinkCartIcon().click();
        // Navigate to the check-out page
        Print("CHECKOUTHoverButton click");
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();

        shippingCost = getShippingCostFromShippingWebElement();

        Print("shippingCost = " + shippingCost);
        Print("shippingCost == 0.0 ?");
        // Verify that the shipping costs are for free
        Verification(Verify.isTrue(shippingCost == 0.0, "Verification - shipping costs", " Verify that the shipping costs for 1 item are free."));

        // Purchase 4 items

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage(), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Navigate to the shopping cart table
        Print("LinkCartIcon click");
        appModel.AdvantageShoppingPage().LinkCartIcon().click();
        // Navigate to the check-out page
        Print("CHECKOUTHoverButton click");
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();

        shippingCost = getShippingCostFromShippingWebElement();

        Print("shippingCost = " + shippingCost);
        Print("shippingCost > 0.0 ?");

        // Verify that the shipping costs are for free
        Verification(Verify.isTrue(shippingCost > 0.0, "Verification - shipping costs", " Verify that the shipping costs for 4 item are NOT free."));
        Print("----- 21 END verifyShippingCostsTest-----------------------------");
    }

    // This test purchases the first item in the Speakers category
    // It does the purchase twice:
    // 1st time - it fills all the details in the payment form and marks the Save Changes In Profile For Future Use to be true
    // 2nd time - tries to do the payment without filling the credentials. If succeeded, it means that the fields values were remembered and used correctly
    @Test
    public void verifySaveChangesInProfilePaymentTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 19 START verifySaveChangesInProfilePaymentTest-----------------------------");

        // Sign in to the store
        signIn();

        // Empty the cart
        emptyTheShoppingCart();

        // 1st time purchase

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Fill credentials. Verification inside

        // 2nd time purchase

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(false); // Do not fill credentials. Verification inside
        Print("----- 19 END verifySaveChangesInProfilePaymentTest-----------------------------");
    }

    // This test purchases the first item in the Tablets category
    @Test
    public void purchaseTabletTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 17 START purchaseTabletTest-----------------------------");

        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().TabletsImgWebElement(), appModel.AdvantageShoppingPage().HPProTablet608G1());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
        Print("----- 17 END purchaseTabletTest-----------------------------");
    }

    // This test purchases the first item in the Laptops category
    @Test
    public void purchaseLaptopTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 14 START purchaseLaptopTest-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().LAPTOPSShopNowWebElement(), appModel.AdvantageShoppingPage().HPENVY17tTouchLaptop());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
        Print("----- 14 END purchaseLaptopTest-----------------------------");
    }

    // This test purchases the first item in the Mice category
    @Test
    public void purchaseMouseTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 15 START purchaseMouseTest-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().MICEShopNowWebElement(), appModel.AdvantageShoppingPage().LogitechG502ProteusCore7());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
        Print("----- 15 END purchaseMouseTest-----------------------------");
    }

    // This test purchases the first item in the Headphones category
    @Test
    public void purchaseHeadphonesTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 13 START purchaseHeadphonesTest-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().HEADPHONESShopNowWebElement(), appModel.AdvantageShoppingPage().HPH2310InEarHeadset());
            // Pay for the item
            checkOutAndPay(); // Verification inside
        } catch (Exception e) {
            e.printStackTrace();
        }
        Print("----- 13 END purchaseHeadphonesTest-----------------------------");
    }

    // This test verifies that the Contact Us form filling and sending works
    //@Test todo: an error accrues when trying to execute  'select'
    public void contactUsTest() throws GeneralLeanFtException, InterruptedException {
        Print("----------------------------START contactUsTest-----------------------------");

        // Sign in to the store
        signIn();

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Fill in the Contact Us form
        appModel.AdvantageShoppingPage().CONTACTUSMainWebElement().click();
        appModel.AdvantageShoppingPage().SelectProductLineContactUsListBox().select(4);
        appModel.AdvantageShoppingPage().SelectProductListBox2().select(2);
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("john@hpe.com");
        appModel.AdvantageShoppingPage().ContactUsSubject().setValue("Thank you");

        // Submit the form by sending it
        appModel.AdvantageShoppingPage().SENDContactUsButton().click();

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement());
        // Verify that the support request was sent successfully
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2), "Verification - Contact Us", " Verify that the support request was sent successfully"));

        Print("----------------------------END contactUsTest-----------------------------");
    }

    // This test purchases the first item in the popular items list
    @Test
    public void popularItemPurchaseFirst() throws GeneralLeanFtException, InterruptedException {
        Print("----- 11 START popularItemPurchaseFirst-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        try {
            //selectItemToPurchase(appModel.AdvantageShoppingPage().POPULARITEMSMainWebElement(), appModel.AdvantageShoppingPage().SpecialOfferViewDetailsItem1Link());
            appModel.AdvantageShoppingPage().SpecialOfferViewDetailsItem1Link().click();
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
        Print("----- 11 END popularItemPurchaseFirst-----------------------------");
    }

    // This test purchases the first item in the special offer items list
    @Test
    public void specialOfferPurchase() throws GeneralLeanFtException, InterruptedException {
        Print("----- 18 START specialOfferPurchase-----------------------------");
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        try {
            selectItemToPurchase(appModel.AdvantageShoppingPage().SPECIALOFFERMainWebElement(), appModel.AdvantageShoppingPage().SEEOFFERButton());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(); // Verification inside
        Print("----- 18 END specialOfferPurchase-----------------------------");
    }

    // This test method creates a random user and adds it to the site
    @Test
    public void createNewAccount() throws GeneralLeanFtException, ReportException, InterruptedException {
        Print("----- 9 START createNewAccount-----------------------------");
        createNewAccountEx("", "", false);
        Print("----- 9 END createNewAccount-----------------------------");
    }

    // This test makes a negative test for registering a new user
    @Test
    public void createNewAccountNegative() throws GeneralLeanFtException, ReportException, InterruptedException {
        Print("----- 10 START createNewAccountNegative-----------------------------");
        createNewAccountEx("", "", true);
        Print("----- 10 END createNewAccountNegative-----------------------------");
    }

    // This test starts a chat with the support of the site
//    @Test
    public void contactUsChatTest() throws GeneralLeanFtException, ReportException, InterruptedException {
        Print("----- 8 START contactUsChatTest-----------------------------");

        // Sign in to the store
        signIn();

        Print("sleep 4000");
        Thread.sleep(4000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Click the Contact Us link
        Print("CONTACTUSMainWebElement click");
        appModel.AdvantageShoppingPage().CONTACTUSMainWebElement().click();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Click the Chat With Us link
        appModel.AdvantageShoppingPage().ChatLogoImage().click();

        Print("sleep 2000");
        Thread.sleep(2000);
        // IMPORTANT: Make sure to enable pop-up messages from this site in BROWSER

        // Verify that the chat window has opened
        // Close the pop up message browser
        Browser chatBrowser;
        BrowserDescription chatBrowserDescription = new BrowserDescription();
        chatBrowserDescription.setTitle("Advantage Online Shopping Demo Support Chat");

        try {
            chatBrowser = BrowserFactory.attach(chatBrowserDescription);
            String brURL = chatBrowser.getURL();
            Verification(Verify.isTrue(brURL.matches(".*/chat\\.html.*"), "Verification - Contact Us Chat", " Verify that the browser navigated to the chat URL"));
            chatBrowser.close();
        } catch (Exception e) {
            Reporter.reportEvent("contact Us Chat", "Could not locate the pop up chat browser", Status.Failed);
            Assert.assertTrue("Verification - Contact Us Chat: The chat window was not created", false);
        }
        Print("----- 8 END contactUsChatTest-----------------------------");
    }

    // This internal method gets a regular expression pattern and tries to match it to any title of the current open browsers
    // The browsers are from the type defined in the test
    // Returns the actual title of the located browser
    public String getBrowserRegExTitleFromBrowsersList(String regExTitlePattern) throws GeneralLeanFtException {
        BrowserDescription desc = new BrowserDescription();
        desc.setType(browserType);
        Browser[] allOpenBrowsers = BrowserFactory.getAllOpenBrowsers(desc);

        // Create a Pattern object
        Pattern r = Pattern.compile(regExTitlePattern);

        int length = allOpenBrowsers.length;
        for (int index = length - 1; index >= 0; index--) {
            String curTitle = allOpenBrowsers[index].getTitle();

            if (curTitle != null) {
                // Now create matcher object
                Matcher m = r.matcher(curTitle);
                //m.matches();

                if (m.find())
                    return curTitle;
            }
        }
        return "";
    }

    // This test verifies that the social media links work properly by clicking them
    @Test
    public void verifySocialMedia() throws GeneralLeanFtException, ReportException, InterruptedException {
        Print("----- 22 START verifySocialMedia-----------------------------");

        // Sign in to the store
        signIn();

        Print("sleep 2000");
        Thread.sleep(2000);

        // Go to home page
        Print("AdvantageDEMOHomeLink click");
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        String brURL = " ";
        String socialLink = " ";

        String Facebooktitle = "HP Application Lifecycle Management | Facebook";
        String Twittertitle = "HPE ALM (@HPE_ALM) | Twitter";
        String Linkedintitle = "HPE Software | LinkedIn";

        try {
            // Verify the Facebook link

            // Clicking te link opens a new browser tab
            // We attach to it and verify its title and URL are ads expected, then close it
            Print("FacebookImage click");
            appModel.AdvantageShoppingPage().FacebookImage().click();
            browser.sync();
            Thread.sleep(4000);
            socialLink = "facebook";

            Browser fbBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Facebooktitle).build());
            fbBrowser.sync();
            brURL = fbBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the Facebook site was launched properly.", brURL.matches(".*facebook\\.com.*"));
            fbBrowser.close();

            // Verify the Twitter link
            Print("TwitterImage click");
            appModel.AdvantageShoppingPage().TwitterImage().click();
            browser.sync();
            Thread.sleep(4000);
            socialLink = "twiiter";

            Browser tweetBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Twittertitle).build());
            tweetBrowser.sync();
            brURL = tweetBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the Twitter site was launched properly.", brURL.matches(".*twitter\\.com.*"));
            tweetBrowser.close();

            // Verify the LinkedIn link
            Print("LinkedInImage click");
            appModel.AdvantageShoppingPage().LinkedInImage().click();
            browser.sync();
            Thread.sleep(4000);
            socialLink = "linkedin";

            Browser linkedinBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Linkedintitle).build());
            linkedinBrowser.sync();
            brURL = linkedinBrowser.getURL();
            Assert.assertTrue("Verification - Verify Social Media: Verify that the LinkedIn site was launched properly.", brURL.matches(".*linkedin\\.com.*"));
            linkedinBrowser.close();
        } catch (Exception e) {
            Reporter.reportEvent("verify Social Media ERROR", "Could not locate the browser with the matching URL of : " + socialLink, Status.Failed);
            Assert.assertTrue("Verification - Verify Social Media: Could not locate the browser with the  matching URL of the social media: " + socialLink, false);
        }
        Print("----- 22 END verifySocialMedia-----------------------------");
    }

    // This test gets the site version from the site UI and prints it to the console
   /* @Test
    public void verifyAdvantageVersionNumber() throws GeneralLeanFtException, ReportException
    {
    	// Get the regular expression pattern from the Copyright object design time description
    	String pattern = appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement().getDescription().getInnerText().toString();
    	waitUntilElementExists(appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement());
    	// Get the actual inner text of the Copyright object during runtime
    	String advantageIncCopyrightVersionElementInnerText = appModel.AdvantageShoppingPage().AdvantageIncCopyrightVersionWebElement().getInnerText();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object
        Matcher m = r.matcher(advantageIncCopyrightVersionElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String copyrightVersion = m.group(1).trim();

        Reporter.reportEvent("verifyAdvantageVersionNumber", "The page copyright version is: " + copyrightVersion, Status.Passed);
        //Verification(Verify.isTrue(!copyrightVersion.isEmpty(),"Verification - Verify Advantage Site Version Number","Verify that the site version: " + copyrightVersion + " was located correctly."));
		Verify.isTrue(!copyrightVersion.isEmpty(),"Verification - Verify Advantage Site Version Number","Verify that the site version: " + copyrightVersion + " was located correctly.");

	}*/

    // This test verifies that the main user links work
    @Test
    public void verifyUserLinks() throws GeneralLeanFtException, InterruptedException {
        Print("----- 23 START verifyUserLinks-----------------------------");

        // Sign in to the store
        signIn();

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        appModel.AdvantageShoppingPage().MyAccountWebElement().click();
        browser.sync();
        waitUntilElementExists(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement().exists(2), "Verification - Verify User Links", " Verify that the user links navigations work - My Account."));

        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        appModel.AdvantageShoppingPage().MyOrdersWebElement().click();
        browser.sync();
        waitUntilElementExists(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement().exists(2), "Verification - Verify User Links", " Verify that the user links navigations work - My Orders."));

        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        appModel.AdvantageShoppingPage().SignOutWebElement().click();

        browser.refresh();
        browser.sync();

        Verification(Verify.isTrue(!isSignedIn(), "Verification - Verify User Links", " Verify that the user links navigations work - Sign Out."));
        Print("----- 23 END verifyUserLinks-----------------------------");
    }

    // This internal method strips the search parameter from the Search result page title and returns it
    public String getSearchParameterFromSearchResultsTitle() throws GeneralLeanFtException {
        // Get the regular expression pattern from the Search Result Title object design time description
        String pattern = appModel.AdvantageShoppingPage().SearchResultTitleWebElement().getDescription().getInnerText().toString();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Get the actual inner text of the Search Result Title object during runtime
        String searchResultTitleElementInnerText = appModel.AdvantageShoppingPage().SearchResultTitleWebElement().getInnerText();
        // Now create the matcher object
        Matcher m = r.matcher(searchResultTitleElementInnerText);
        m.matches();
        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String searchParameterFromTitle = m.group(1).trim();

        // The title encapsulates the search parameter with quotes, so we need to remove them by trimming
        searchParameterFromTitle = searchParameterFromTitle.substring(1, searchParameterFromTitle.length() - 1);

        return searchParameterFromTitle;
    }

    // This test verifies that the search page works, using the Search URL
    @Test
    public void verifySearchUsingURL() throws GeneralLeanFtException, InterruptedException {
        Print("----- 20 START verifySearchUsingURL-----------------------------");

        // Sign in to the store
        signIn();

        String searchParameter;
        searchParameter = "Laptops";

        // Go to the Search page as a workaround - search for Laptops
        browser.navigate(SearchURL + "?viewAll=" + searchParameter);
        waitUntilElementExists(appModel.AdvantageShoppingPage().LaptopFilterSearchCheckbox());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().LaptopFilterSearchCheckbox().exists(), "Verification - Verify Search using URL", " Verify that the Laptops checkbox element exists."));

        // Get the actual inner text of the Search Result Title object during runtime
        Verification(Verify.isTrue(getSearchParameterFromSearchResultsTitle().equals(searchParameter), "Verification - Verify Search using URL", " Verify that the title reflects the search parameter: " + searchParameter + "."));

        searchParameter = "Speakers";
        // Go to the Search page as a workaround - search for Speakers
        browser.navigate(SearchURL + "?viewAll=" + searchParameter);
        waitUntilElementExists(appModel.AdvantageShoppingPage().SpeakersFilterSearchCheckbox());
        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().SpeakersFilterSearchCheckbox().exists(), "Verification - Verify Search using URL", " Verify that the Speakers checkbox element exists."));

        // Get the actual inner text of the Search Result Title object during runtime
        Verification(Verify.isTrue(getSearchParameterFromSearchResultsTitle().equals("Speakers"), "Verification - Verify Search using URL", " Verify that the title reflects the search parameter: " + searchParameter + "."));
        Print("----- 20 END verifySearchUsingURL-----------------------------");
    }

    @Test
    public void VerifyDownloadPageTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 7 START VerifyDownloadPageTest-----------------------------");
        waitUntilElementExists(appModel.AdvantageShoppingPage().MICEShopNowWebElement());

        browser.navigate(appURL + "/downloads");
        Print("sleep 4000");
        Thread.sleep(4000);
        Verification(Verify.isTrue(appModel.DownloadPage().DownloadAndroidAppWebElement().exists(), "Download Verification : Android", "verift that the android link works"));
        Verification(Verify.isTrue(appModel.DownloadPage().DownloadIosAppWebElement().exists(), "Download Verification : IOS", "verift that the IOS link works"));
        //appModel.DownloadPage().DownloadIosAppWebElement().click();
        //Thread.sleep(2000);
        Print("----- 7 END VerifyDownloadPageTest-----------------------------");
    }

    ////////////////////////////////////////////////// moti gadian Code added on  27/3/17 /////////////////////////////////////////////////////////////

    @Test
    public void OrderServiceTest() throws GeneralLeanFtException, InterruptedException {
   /*
    * login
		purchase�a product - remember it's name
		goto orders history page:
		Search by name�- look for the number you just created
		=> the search result shall show all relevant entries

	Delete an order =>delete the order you just created, when the user clicks delete validate that the application informs the user�that his order will be cancelled, and ask him to approve.�

	Validate that the following was added to the order grid:
		Order time*/

        Print("----- 5 START OrderServiceTest-----------------------------");

        signIn();

        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();
        appModel.AdvantageShoppingPage().LAPTOPSWebElement().click();

        // Select an item to purchase and add it to the cart
        try {
            //selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImgWebElement(), appModel.AdvantageShoppingPage().BoseSoundlinkImage());
            appModel.AdvantageShoppingPage().laptopFororderService().click();
            String ProductName = appModel.AdvantageShoppingPage().LaptopName().getInnerText();
            Print("ORDER:" + ProductName);

            appModel.AdvantageShoppingPage().ADDTOCARTButton().click();

            Checkout();


            appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
            appModel.AdvantageShoppingPage().MyOrdersWebElement().click();
            appModel.AdvantageShoppingPage().OrderSearchWebElement().click();
            appModel.AdvantageShoppingPage().SearchOrderEditField().setValue(ProductName);
            appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement().click();


            Verify.isTrue(appModel.AdvantageShoppingPage().RemoveFromOrderValidate().exists(), "Verification - Verify Search orders", "Verify that the alert window element exists.");
            Assert.assertTrue("Verification - Verify Search orders: Verify that the alert window element exists.", appModel.AdvantageShoppingPage().YesNoButtonsRemoveOrderSearch().exists());
            appModel.AdvantageShoppingPage().YesCANCELButton().click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Print("----- 5 END OrderServiceTest-----------------------------");
    }

   /*@Test
    public void OutOfStockTest() throws GeneralLeanFtException, InterruptedException{


    	/*
    	 * Missing (Quantity=0 in all colors)- validate that the UI is marked correctly and that the user can watch all its details
    	 * - change color to see the different pictures,
    	 * read the 'more info' section (click and open it in mobile) but,
    	 * that the user can't change the quantity or add it to cart
    	 *

    	browser.sync();
    	appModel.AdvantageShoppingPage().HEADPHONESShopNowWebElement().click();
    	appModel.AdvantageShoppingPage().SoldOutHeadphonesWebElement().click();
    	appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement().click();


    	Verify.isFalse(appModel.AdvantageShoppingPage().ADDTOCARTButton().isEnabled(),"Verification - Verify sold out item","Verify that the ADD TO CART button not enabled.");
    	Verify.isTrue(appModel.AdvantageShoppingPage().QuantityOfProductWebEdit().isReadOnly(),"Verification - Verify sold out item","Verify that the Quantity field not enabled to edit.");




    }*/

    @Test
    public void PayButtonRegExTest() throws GeneralLeanFtException, InterruptedException {
        Print("----- 6 START PayButtonRegExTest-----------------------------");

        //The button text always starts with Pay to allow for adding regular expressions in object identification.

        //In  web the button calls "CHECKOUT ({{RegEx}})"

        String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        signIn();
        appModel.AdvantageShoppingPage().LAPTOPSWebElement().click();

        // Select an item to purchase and add it to the cart
        try {
            appModel.AdvantageShoppingPage().laptopFororderService().click();
            appModel.AdvantageShoppingPage().ADDTOCARTButton().click();


            String checkOutTXT = appModel.AdvantageShoppingPage().CHECKOUTHoverButton().getInnerText();
            Matcher m = r.matcher(checkOutTXT);
            boolean match = m.find();
            System.out.println(checkOutTXT + " :: " + match);

            Verification(Verify.isTrue(match, "Verification - Verify CHECKOUT RegEx", " Verify that the text in CHECKOUT button start with 'CHECKOUT' ."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Print("----- 6 END PayButtonRegExTest-----------------------------");
    }

    @Test
    public void NegativeLoginTest() throws GeneralLeanFtException, InterruptedException {
        //Try to login with non valid credentials and verify the message "invalid user name or password"
        Print("----- 4 START NegativeLoginTest-----------------------------");

        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
        // Fill in the user name and password
        appModel.AdvantageShoppingPage().UsernameLoginEditField().setValue("bla bla");
        appModel.AdvantageShoppingPage().PasswordLoginEditField().setValue("bla pss");
        // Check the Remember Me checkbox
        appModel.AdvantageShoppingPage().RememberMeCheckBox().set(true);
        // Click on sign in button
        appModel.AdvantageShoppingPage().SIGNINButton().click();

        Thread.sleep(2000);
        boolean invalid = appModel.AdvantageShoppingPage().InvalidUserMessageWebElement().exists();

        Verification(Verify.isTrue(invalid, "Verification - Negative Sign In", "Verify that the we can't login with non valid credentials"));
        Print("----- 4 END NegativeLoginTest-----------------------------");
    }

    @Test
    public void LogOutTest() throws GeneralLeanFtException, InterruptedException {
        //perform logout and make sure you are not logged in
        Print("----- 3 START LogOutTest-----------------------------");

        signIn();
        browser.sync();
        signOut();
        browser.refresh();
        Verification(Verify.isFalse(isSignedIn(), "Verification - Verify logout", " Verify that the is realy logout from the site ."));

        Print("----- 3 END LogOutTest-----------------------------");
    }




   /* @Test
    public void ChatSupportTest() throws GeneralLeanFtException, InterruptedException, ReportException {


     *todo: the test runs until he needs to set value in the chat support edit field
     * the app model can't recognize the field although the spy find one single match.
     *

    	// check if the Chat option for support are work fine and send a respond to user msg.


    	// Make sure to enable pop-up messages from this site


    	Browser chatBrowser;

    	try{
    		appModel.AdvantageShoppingPage().ChatLogoImage().click();
        	browser.sync();
        	Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().exists(),"Verification - Contact Us Chat","The chat window was created");
        	//waitUntilElementExists(appModel.AdvantageOnlineShoppingDemoSupportChatPage().ServerConnectmsg());

			chatBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title("Advantage Online Shopping Demo Support Chat").build());
			String brURL = chatBrowser.getURL();
			Thread.sleep(2000);
	    	Verify.isTrue(brURL.matches(".*//*chat\\.html.*"),"Verification - Contact Us Chat","Verify that the browser navigated to the chat URL");
            Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().ServerConnectmsg().exists() ,"Verification - Contact Us Chat","The 'server concted' massege show up");

    	}
    	catch (Exception e)
    	{
			Reporter.reportEvent("verify ContactUS  ERROR", "Could not locate the browser with the matching URL"  , Status.Failed);
			Verify.isTrue(false,"Verification - Contact Us Chat","The chat window was not created");
    	}


    	appModel.AdvantageOnlineShoppingDemoSupportChatPage().TypeAMessageEditField().setValue("Hello I need Help.");
    	appModel.AdvantageOnlineShoppingDemoSupportChatPage().ChatSendImage().click();

    	browser.sync();

    	Verify.isTrue(appModel.AdvantageOnlineShoppingDemoSupportChatPage().RespondChatWebElement().exists(),"Verification - Verify chat respond","Verify that we get respond to our massege.");
    	assertNotNull(appModel.AdvantageOnlineShoppingDemoSupportChatPage().RespondChatWebElement().getInnerText());
    }*/

    @Test
    public void ContactSupportTest() throws GeneralLeanFtException, InterruptedException {
    	/*
    	 * validate all the following fields
			- email address (mandatory)
			- select category (optional)
			- select product  from the selected category - one product only can be selected (optional)
			- free text of the issue (mandatory)
				"Send" option

			after clicking OK,  the user will receive a message saying "thank you for contacting Advantage support"
			play with this - select something, go back and try to change it - try to break this feature
    	 */

        Print("----- 2 START ContactSupportTest-----------------------------");
        //try to send request with just txt in the email field
        Print("EmailContactUsWebElement().setValue(\"fffff\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("fffff");
        Verification(Verify.isFalse(appModel.AdvantageShoppingPage().SENDContactUsButton().isEnabled(), "Verification - Verify contact Us request", "Verify that we cant send request with unproper Email."));

        //try to send request with just email  in the email field
        Print("EmailContactUsWebElement().setValue(\"user@demo.com\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("user@demo.com");
        Verification(Verify.isFalse(appModel.AdvantageShoppingPage().SENDContactUsButton().isEnabled(), "Verification - Verify contact Us request", "Verify that we cant send request with Email without Subject."));

        //try to send request with just txt in the email field and subject (not should be working)
        Print("EmailContactUsWebElement().setValue(\"sometxt\")");
        appModel.AdvantageShoppingPage().EmailContactUsWebElement().setValue("sometxt");
        Print("ContactUsSubject().setValue(\"I have Problem..\")");
        appModel.AdvantageShoppingPage().ContactUsSubject().setValue("I have Problem..");

        Print("SENDContactUsButton().click()");
        appModel.AdvantageShoppingPage().SENDContactUsButton().click();

        // Verify that the support request was not sent successfully
        Verify.isFalse(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2), "Verification - Verify contact Us request", "Verify that we cant send request with unproper Email and Subject.");
        Print("----- 2 END ContactSupportTest-----------------------------");
    }

    public void Checkout() throws GeneralLeanFtException {
        appModel.AdvantageShoppingPage().ADDTOCARTButton().click();

        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();
        appModel.AdvantageShoppingPage().NEXTButton().click();
        appModel.AdvantageShoppingPage().PAYNOWButton().click();

        browser.sync();
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
        if (!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }

    public void Close() throws GeneralLeanFtException {
        browser.close();
    }

}