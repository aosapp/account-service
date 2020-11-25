package Web;
 
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.net.HttpHeaders;
import com.hp.lft.report.*;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.WaitUntilTestObjectState.WaitUntilEvaluator;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.Verify;

import unittesting.*;

import static org.junit.Assert.fail;

// Make sure the tests run at the ascending alphabet name order (JUnit 4.11 and above)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdvantageWebTest extends UnitTestClassBase {

    public static final String USERNAME = "WebUser1";
    public static final String PASSWORD = "HPEsw123";
    public static String SearchURL = "";
    public static String appURL = System.getProperty("url", "defaultvalue");
//  public static String appURL2 = "52.32.172.3";
//public static String appURL2 = "18.212.178.84";             //Stage
	public static String appURL2 = "16.60.158.84";			// CI
// 	public static String appURL2 = "34.228.54.91";			// production-ngix
//	public static String appURL2 = "16.59.19.163:8080";		// LOCALHOST
//	public static String appURL2 = "16.59.19.38:8080";		// LOCALHOST Tamir
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

    public AdvantageWebTest() {

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
        instance = new AdvantageWebTest();
        globalSetup(AdvantageWebTest.class);

        Print("browserTypeValue: " + browserTypeValue);
        Print("envTypeValue: " + envTypeValue);
        Print("appURL: " + appURL);
//        Print("appURL2: " + appURL2);
        if (appURL.equals("defaultvalue"))
            appURL = appURL2;


        Reporter.addRunInformation("URL", appURL);

        Print("Wait for " + appURL + "  to be ready...");
        if(!WaitForAOS()){
            throw new GeneralLeanFtException("App at " + appURL + " is not ready");
        }
    }

    private static boolean WaitForAOS() throws IOException, InterruptedException{
        URL url = new URL(appURL + "/order/api/v1/healthcheck");
        Print("healthcheck endpoint " + url);
        String returnValue = "";
        int numOfWait = 0;
        do{
            Thread.sleep(60000);
            numOfWait++;
            try{
                returnValue = httpGet(url);
            }catch (Exception e){
                Print("healthcheck exception");
                e.printStackTrace();
                Print(e.getStackTrace().toString());
            }
            Print("returnValue " + returnValue);
            if(!returnValue.equalsIgnoreCase("\"SUCCESS\"")){
                Print("healthcheck failed... sleeping");
            }
        }while(!returnValue.equalsIgnoreCase("\"SUCCESS\"") && numOfWait < 10);

        if(numOfWait == 10 && !returnValue.equalsIgnoreCase("\"SUCCESS\""))
            return false;


        return true;
    }

    private static String httpGet(URL url) throws IOException{
        Proxy proxy = null;
        try{
            proxy = getProxyFromProperties();
        } catch (Exception e){
            logger.error("Unable to build proxy", e);
        }

        HttpURLConnection conn = null;
        if(proxy == null){
            conn = (HttpURLConnection) url.openConnection();
        }
        else
            conn = (HttpURLConnection) url.openConnection(proxy);
        logger.debug("HttpURLConnection = " + conn.getURL().toString());
        conn.setRequestProperty(HttpHeaders.USER_AGENT, "AdvantageService/order");
        logger.debug("waiting for AOS to be ready");

        int responseCode = conn.getResponseCode();
        String returnValue = responseSolver(responseCode, conn);
        conn.disconnect();
        return returnValue;

    }

    private static Proxy getProxyFromProperties() {
        String host = System.getProperty("aos.proxy.host");
        String port = System.getProperty("aos.proxy.port");

        if(host == null || port == null)
            return null;
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port)));
    }

    private static String responseSolver (int responseCode, HttpURLConnection conn) throws IOException{

        String returnValue;
        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                // Buffer the result into a string
                InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                returnValue = sb.toString();
                break;
            case HttpURLConnection.HTTP_CONFLICT:
                //  Product not found
                returnValue = "CONFLICT";
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                returnValue = "NOT FOUND";
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                returnValue = "FORBIDDEN";
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                returnValue = "UNAUTHORIZED";
                break;
            default:
                IOException e = new IOException(conn.getResponseMessage());
                throw e;
        }

        return returnValue;
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
        Print("isSignedIn starts");
        String loggedInUserName = getUsernameFromSignOutElement();
        if (loggedInUserName.isEmpty()) {
            Print("isSignedIn FALSE");
            return false;
        }
        Boolean isNotUserName = loggedInUserName.equals("My");

        if(isNotUserName){
            Print("isSignedIn FALSE");
            return false;
        }

        Print(String.valueOf(isNotUserName));
        Print("isSignedIn TRUE. Signed in as - "+ loggedInUserName);

        return true;
    }

    public static void Print(String msg) {
        System.out.println(msg);
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

    // This internal method gets the username from the text that appears on the SignOutMainIconWebElement object
    public String getUsernameFromSignOutElement() {
        Print("getUsernameFromSignOutElement start");

        String fullStringFromWebElement = getWebElementInnerText(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        //Print("fullStringFromWebElement " + fullStringFromWebElement);

        String [] words = fullStringFromWebElement.split("\\s+");

        Print("words[0] " + words[0]);

        return words[0];

    }
        // Get the regular expression pattern from the Sign in Out object design time description
//        String pattern = null;
//        try {
//            pattern = appModel.AdvantageShoppingPage().SignOutMainIconWebElement().getDescription().getInnerText().toString();
//            Print(pattern);
//        } catch (GeneralLeanFtException e) {
////            printError(e);
//            Print("\nERROR: " + e.getMessage() +  "\n");
////            fail("GeneralLeanFtException: getUsernameFromSignOutElement");
//            pattern = null;
//        }
//        // Get the actual inner text of the Sign in Out object during runtime
//        String signInOutIconElementInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
//        Print(signInOutIconElementInnerText);
//
//        // Create a Pattern object
//        Pattern r = Pattern.compile(pattern);
//
//        Print("something");
//        // Now create matcher object
//        Matcher m = r.matcher(signInOutIconElementInnerText);
//        Print("2222");
//        m.matches();
//        Print("333");
//        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
//        String loggedInUserName = m.group(1).trim();
//        Print("4444");
//        Print("getUsernameFromSignOutElement end loggedInUserName = '" + loggedInUserName + "'");
//        return loggedInUserName;
//    }

    /**
     * Function return Shipping Cost from WebElement in Order Payment page
     */
    public double getShippingCost() {
        String shippingCost = getWebElementInnerText(appModel.AdvantageShoppingPage().ShippingCost());
        return Double.valueOf(shippingCost.substring(1));
    }

    /**
     * Sign in to the store
     * @return
     */
    public boolean signIn() throws GeneralLeanFtException, ReportException {
        Print("signIn() start");
        boolean isSignedIn = isSignedIn();

        if (!isSignedIn) {
            // Click the sign-in icon
            clickWebElement(appModel.myAccountMyOrdersSignOutLink());
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

    public boolean signInWithCredentials(String userName, String password) throws GeneralLeanFtException, ReportException {
        Print("signIn() start");
        boolean isSignedIn = isSignedIn();

        if (!isSignedIn) {
            // Click the sign-in icon
            clickWebElement(appModel.myAccountMyOrdersSignOutLink());
            // Fill in the user name and password
            setValueEditField(appModel.AdvantageShoppingPage().UsernameLoginEditField(), userName);
            setValueEditField(appModel.AdvantageShoppingPage().PasswordLoginEditField(), password);
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

    private void setCheckBox(CheckBox checkBox, Boolean value) {
        Print("SET '" + value + "' to " + checkBox.getClass().getSimpleName());
        try {
            checkBox.set(value);
        } catch (GeneralLeanFtException e) {
            printError(e, checkBox.getClass().getSimpleName());
            fail("GeneralLeanFtException: set '" + value + "' to element " + checkBox.getClass().getSimpleName());
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

    private boolean isEnabledButton(Button button) {
        boolean result = false;
        Print("IS ENABLED " + button.getClass().getSimpleName());
        try {
            result = button.isEnabled();
        } catch (GeneralLeanFtException e) {
            printError(e, button.getClass().getSimpleName());
            fail("GeneralLeanFtException: is enabled on element " + button.getClass().getSimpleName());
        }
        return result;
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

    private boolean existsWebElement(WebElement webElement, int index) {
        boolean result = false;
        Print("EXISTS " + webElement.getClass().getSimpleName() + " " + index);
        try {
            result = webElement.exists(index);
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: exists on element " + webElement.getClass().getSimpleName());
        }
        return result;
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

    //This internal method signs the user out
    public void signOut() {
        if (isSignedIn()) {
            // Click the sign-in icon
            clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
            // Click the sign out link
            clickWebElement(appModel.AdvantageShoppingPage().SignOutWebElement());
        }
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
                threadSleep(15000);
                clickWebElement(appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement());
        } catch (GeneralLeanFtException e) {
            e.printStackTrace();
        }
        //comment

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

    // This method will checkout to the cart and pay for the cart content
    // The boolean fillCredentials specifies if to fill the credentials in the form or not
    public void checkOutAndPaySafePay(boolean fillCredentials) throws ReportException {
        Print("checkOutAndPaySafePay start");
        // Checkout the cart for purchase
        threadSleep(7000);
        waitUntilElementExists(appModel.AdvantageShoppingPage().CartIcon());
        // Click the cart icon
        clickWebElement(appModel.AdvantageShoppingPage().CartIcon());
        // Click the checkout button
        clickWebElement(appModel.AdvantageShoppingPage().CheckOutButton());
        Print("Wait for window to be loaded");
        threadSleep(3000);
        waitUntilElementExists(appModel.AdvantageShoppingPage().NEXTButton());
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
                } else {
                    img = browser.getPage().getSnapshot();
                    Reporter.reportEvent("Verify Product Purchase:", "Verify that the product was purchased successfully", Status.Failed, img);
                    fail("Fail to verify that the product was purchased successfully: No ThankYouForBuyingWithAdvantage element");
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
                    fail("Fail to verify that the product was purchased successfully: No ThankYouForBuyingWithAdvantage element");
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

    public void checkOutAndPayMasterCredit(String cardnum, String CVV, String holdername, boolean save) throws ReportException {
        Print("checkOutAndPayMasterCredit start");
        threadSleep(7000);
        // Checkout the cart for purchase
        // Click the cart icon
        waitUntilElementExists(appModel.AdvantageShoppingPage().CartIcon());
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
                fail("Fail to verify that the product was purchased successfully: No ThankYouForBuyingWithAdvantage element");
            }

            //Verification(Verify.isTrue(isProductPurchased, "Verification - Product Purchase MasterCredit:", " Verify that the product was purchased successfully with MasterCredit "));
        } catch (GeneralLeanFtException e) {
            printError(e, "appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
            fail("GeneralLeanFtException: appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2)");
        }
        Print("checkOutAndPayMasterCredit end");
    }

    //This method is an internal function that initializes the tests
    public void initBeforeTest() throws GeneralLeanFtException, InterruptedException, IOException {
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

    // This internal method waits until an object exists and visible
    public boolean waitUntilElementExists(WebElement webElement) {
        Print("WAIT UNTIL ELEMENT EXISTS " + webElement.getClass().getSimpleName());
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElement, new WaitUntilEvaluator<WebElement>() {
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

    public boolean waitUntilElementExists(WebElement webElement, long time) {
        Print("WAIT UNTIL ELEMENT EXISTS " + webElement.getClass().getSimpleName() + " for " + time + " millisec");
        boolean result = false;
        try {
            result = WaitUntilTestObjectState.waitUntil(webElement, new WaitUntilEvaluator<WebElement>() {
                public boolean evaluate(WebElement we) {
                    try {
                        return we.exists() && we.isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                }
            }, time);
        } catch (GeneralLeanFtException e) {
            printError(e, webElement.getClass().getSimpleName());
            fail("GeneralLeanFtException: " + webElement.getClass().getSimpleName());
        }
        return result;
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
            threadSleep(2000);

            // Get the rows number from the cart table
            int numberOfRowsInCart = 0;
            try {
                numberOfRowsInCart = appModel.AdvantageShoppingPage().CartTable().getRows().size();
                threadSleep(1000);
            } catch (GeneralLeanFtException e) {
                printError(e, "appModel.AdvantageShoppingPage().CartTable().getRows().size()");
                fail("GeneralLeanFtException: emptyTheShoppingCart appModel.AdvantageShoppingPage().CartTable().getRows().size()");
            }
            int numberOfRelevantProductRowsInCart = numberOfRowsInCart - 3; // Removing the non-relevant rows number from our counter. These are the title etc.. and rows that do not represent actual products
            Print("numberOfRelevantProductRowsInCart = " + numberOfRelevantProductRowsInCart);

            // Iterate and click the "Remove" link for all products
            for (; numberOfRelevantProductRowsInCart > 0; numberOfRelevantProductRowsInCart--) {
                threadSleep(1000);
                clickWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());
            }
            if (!isCartEmpty()) {
                Print("cart is not empty");
            }
            else {
                Print("Cart is empty");
            }
        }
    }

    // This internal method returns the number of items in the shopping cart by the inner text of the cart icon object
    public int getCartProductsNumberFromCartObjectInnerText() {
        Print("getCartProductsNumberFromCartObjectInnerText start");
        int productsNumberInCart = 0;
        threadSleep(1000);

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

    // This internal method checks if the shopping cart is empty or not
    public boolean isCartEmpty() {
        if (getCartProductsNumberFromCartObjectInnerText() == 0)
            return true;
        return false;
    }

    /**
     * List of all tests
     * Test tests in random order
     *
     * @throws GeneralLeanFtException
     * @throws ReportException
     */

//    @Test
    public void testNew() throws GeneralLeanFtException, ReportException {


    }

    //This test method creates the default user to be used in the tests, if it already does not exist - a user must be in the system:
    //username - johnhpe1, password - HPEsw123

    /**
     * Adding main user if not exists
     * @return true - main user added, false - main user not added
     */
    @Test
    public void addMainUserIfNotExists() throws GeneralLeanFtException, ReportException {
//        Print("taping new item");
//        threadSleep(1000);
//        clickWebElement(appModel.myAccountMyOrdersSignOutLink());
//        Boolean isSignInBoxThere = appModel.sIGNINWITHFACEBOOKORUsernamePasswordEmailREMEMBERMESIGNINForgotYourPasswordCREATENEWACCOUNTWebElement().exists();
//        Print( String.valueOf(isSignInBoxThere));
        if(isSignedIn()){
            signOut();
        }
        //threadSleep(100000);
        signIn();       // Sign in to the store

        if (!isSignedIn()) {
            clickWebElement(appModel.AdvantageShoppingPage().CloseSignInPopUpBtnWebElement());
            createNewAccountEx(USERNAME, PASSWORD, false);
        }
    }

    // This test purchases the first item in the Speakers category
    @Test
    public void purchaseSafePaySpeakersTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS());

        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    private void browserSync() {
        try {
            browser.sync();
        } catch (GeneralLeanFtException e) {
            printError(e, "browser.sync() error");
            fail("GeneralLeanFtException: browser.sync() error");
        }
    }

    private void browserRefresh() {
        try {
            browser.refresh();
        } catch (GeneralLeanFtException e) {
            printError(e, " browser.refresh() ERROR");
            fail("GeneralLeanFtException: browser.refresh() ERROR");
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

    /**
     * This test purchases a 1000 of the first item in the Speakers category
     * Flow: Trying to purchase 1000 products. Web application shows message that maximum is 10 and continuing with 10
     */
    @Test
    public void purchaseSafePay1000SpeakersNegativeTest() throws GeneralLeanFtException, ReportException {
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS(), 1000);

        Print("Wait for something");
        threadSleep(2000);

        // Verify that the quantity that was actually added to the cart is 10 and not a 1000
        int productsQuantityInCart = getCartProductsNumberFromCartObjectInnerText();

        checkWithReporterIsTrue(productsQuantityInCart == 10, "Verify products quantity", "There are 10 products as expected");

        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    // This test verifies that the shipping costs is free when purchasing 1 item
    // 	and that the shipping cost for 4 items is not free
    //@Test
    public void verifyShippingCostsTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        double shippingCost = 0;

        Print("Purchase 1 item");

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS());

        // Navigate to the shopping cart table
        clickWebElement(appModel.AdvantageShoppingPage().LinkCartIcon());
        // Navigate to the check-out page
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());

        shippingCost = getShippingCost();
        Print("shippingCost:  " + shippingCost + " == 0.0");

        // Verify that the shipping costs are for free
        checkWithReporterIsTrue(shippingCost == 0.0, "Verify shipping costs", "Verify that the shipping costs for 1 item are free");
        ////////////////////

        Print("Purchase 4 items");

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS(), 4);

        // Navigate to the shopping cart table
        clickWebElement(appModel.AdvantageShoppingPage().LinkCartIcon());
        // Navigate to the check-out page
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());

        shippingCost = getShippingCost();
        Print("shippingCost: " + shippingCost + " > 0.0");

        // Verify that the shipping costs are not free
        checkWithReporterIsTrue(shippingCost > 0.0, "Verify shipping costs", "Verify that the shipping costs for 4 items > 0.0");
    }

    // This test purchases the first item in the Speakers category
    // It does the purchase twice:
    // 1st time - it fills all the details in the payment form and marks the Save Changes In Profile For Future Use to be true
    // 2nd time - tries to do the payment without filling the credentials. If succeeded, it means that the fields values were remembered and used correctly
    @Test
    public void verifySaveChangesInProfilePaymentTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the cart
        emptyTheShoppingCart();

        // 1st time purchase

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS());

        // Pay for the item
        checkOutAndPaySafePay(); // Fill credentials. Verification inside

        // 2nd time purchase

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().SpeakersImg(), appModel.AdvantageShoppingPage().SpeakerBoseSoundlinkWS());

        // Pay for the item
        checkOutAndPaySafePay(false); // Do not fill credentials. Verification inside
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

    // This test purchases the first item in the Laptops category
    @Test
    public void purchaseMasterCreditLaptopTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        if(!isSignedIn()){
            signIn();
        }

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

    // This test purchases the first item in the Mice category
    @Test
    public void purchaseMasterCreditMouseTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        if(!isSignedIn()){
            signIn();
        }

        // Empty the shopping cart
        emptyTheShoppingCart();
        threadSleep(1000);

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        threadSleep(1000);
        browserSync();

        // Select an item to purchase and add it to the cart
        // TODO: find other places where is used LogitechG502ProteusCore7 and remove element from model
        selectItemToPurchase(appModel.AdvantageShoppingPage().MiceImg(), appModel.AdvantageShoppingPage().MiceLogitechG502Img());
        threadSleep(2000);

        // Pay for the item
        checkOutAndPayMasterCredit("123412341234", "774", USERNAME, false); // Verification inside
    }

    // This test purchases the first item in the Headphones category
    @Test
    public void purchaseSafePayHeadphonesTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Select an item to purchase and add it to the cart
        selectItemToPurchase(appModel.AdvantageShoppingPage().HeadphonesImg(), appModel.AdvantageShoppingPage().HPH2310InEarHeadset());
        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    // This test verifies that the Contact Us form filling and sending works
    //@Test todo: an error accrues when trying to execute  'select'
    public void contactUsTest() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        // Fill in the Contact Us form
        clickWebElement(appModel.AdvantageShoppingPage().CONTACTUSMainWebElement());
        appModel.AdvantageShoppingPage().SelectProductLineContactUsListBox().select(4);
        appModel.AdvantageShoppingPage().SelectProductListBox2().select(2);
        setValueEditField(appModel.AdvantageShoppingPage().EmailContactUsWebElement(), "john@hpe.com");
        setValueEditField(appModel.AdvantageShoppingPage().ContactUsSubject(), "Thank you");

        // Submit the form by sending it
        clickWebElement(appModel.AdvantageShoppingPage().SENDContactUsButton());

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement());
        // Verify that the support request was sent successfully
        Boolean isSuccessfullContacting = appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2);
        if (isSuccessfullContacting) {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify Contact Us", "Verify that the support request was sent successfully", Status.Passed, img);
        } else {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify Contact Us", "Verify that the support request was sent successfully", Status.Failed, img);
        }
//        Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2), "Verification - Contact Us", " Verify that the support request was sent successfully"));
    }

    // This test purchases the first item in the popular items list
    @Test
    public void purchaseSafePayPopularItemFirst() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());

        selectItemToPurchase(appModel.AdvantageShoppingPage().POPULARITEMSMainWebElement(), appModel.AdvantageShoppingPage().PopularItemViewDetails());

        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        clickWebElement(appModel.AdvantageShoppingPage().PopularItemViewDetails());

        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    // This test purchases the first item in the special offer items list
    @Test
    public void purchaseSafePaySpecialOffer() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        selectItemToPurchase(appModel.AdvantageShoppingPage().SPECIALOFFERMainWebElement(), appModel.AdvantageShoppingPage().SEEOFFERButton());

        // Pay for the item
        checkOutAndPaySafePay(); // Verification inside
    }

    // This test method creates a random user and adds it to the site
    @Test
    public void createNewAccount() throws GeneralLeanFtException, ReportException {
        createNewAccountEx("", "", false);
    }

    // This test makes a negative test for registering a new user
    @Test
    public void createNewAccountNegative() throws GeneralLeanFtException, ReportException {
        createNewAccountEx("", "", true);
    }

    // This test starts a chat with the support of the site
    //TODO: check why this test pass successfully on local LeanFT but crash on CI

    /**
     * IMPORTANT: Make sure to enable pop-up messages from this site in BROWSER
     * @throws ReportException
     */
//    @Test
//    public void contactUsChatTest() throws ReportException, GeneralLeanFtException {
//        // Sign in to the store
//        signIn();
//
//        // Go to home page
//        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
//
//        // Click the Contact Us link
//        clickWebElement(appModel.AdvantageShoppingPage().CONTACTUSMainWebElement());
//
//        // Click the Chat With Us link
//        clickWebElement(appModel.AdvantageShoppingPage().ChatLogoImage());
//
//        Print("Wait for window with chat loading");
//        threadSleep(5000);
//
//        // Verify that the chat window has opened
//        // Close the pop up message browser
//        Browser chatBrowser = null;
//        BrowserDescription chatBrowserDescription = new BrowserDescription();
//        chatBrowserDescription.setTitle("Advantage Online Shopping Demo Support Chat");
//
//        String brURL = "";
//        try {
//            chatBrowser = BrowserFactory.attach(chatBrowserDescription);
//            brURL = chatBrowser.getURL();
//        } catch (GeneralLeanFtException e) {
//            printError(e, "BrowserFactory.attach(chatBrowserDescription)");
//            Reporter.reportEvent("contact Us Chat", "Could not locate the pop up chat browser", Status.Failed);
//            Assert.assertTrue("Verification - Contact Us Chat: The chat window was not created", false);
//        }
//
//        Boolean isUrlRight =  brURL.matches(".*/chat\\.html.*");
//        checkWithReporterIsTrue(isUrlRight, "Verify Contact Us Chat", "Verify that the browser navigated to the chat URL");
//        chatBrowser.close();
//    }

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
    //    TODO: change facebook and twitter to Micro Focus pages
//    @Test
//    public void verifySocialMedia() throws ReportException, GeneralLeanFtException {
//        // Sign in to the store
//        signIn();
//
//        // Go to home page
//        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
//        browserSync();
//
//        String brURL = " ";
//        String socialLink = " ";
//
//        String Facebooktitle = "HP Application Lifecycle Management - Software, Interest | Facebook";
//        String Facebooktitle1 = "HP Application Lifecycle Management | Facebook";
//        String Twittertitle = "HPE ALM (@HPE_ALM) | Twitter";
//        String Linkedintitle = "Micro Focus Software | LinkedIn";
//
//        try {
//            // Verify the Facebook link
//
//            // Clicking te link opens a new browser tab
//            // We attach to it and verify its title and URL are ads expected, then close it
//            clickWebElement(appModel.AdvantageShoppingPage().FacebookImage());
//            threadSleep(5000);
//            socialLink = "facebook";
//            Print("facebook title: " + Facebooktitle);
//            Browser fbBrowser = null;
//            try{
//                fbBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Facebooktitle).build());
//            }
//            catch (GeneralLeanFtException e1){
//                printError(e1, "BrowserFactory.attach(new BrowserDescription.Builder().title(Facebooktitle).build())");
//                Reporter.reportEvent("verify Social Media ERROR", "Could not locate the browser with the matching URL of : " + socialLink, Status.Failed);
//
//            }
//            if(fbBrowser == null){
//                Print("Trying facebook alternate title " + Facebooktitle1);
//                fbBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Facebooktitle1).build());
//            }
//            fbBrowser.sync();
//            brURL = fbBrowser.getURL();
//            Print("facebook url:\n" + brURL);
//            Assert.assertTrue("Verification - Verify Social Media: Verify that the Facebook site was launched properly.", brURL.matches(".*facebook\\.com.*"));
//            fbBrowser.close();
//
//            // Verify the Twitter link
//			/*
//            clickWebElement(appModel.AdvantageShoppingPage().TwitterImage());
//            threadSleep(5000);
//            socialLink = "twiiter";
//            Print("twitter title: " + Twittertitle);
//            Browser tweetBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Twittertitle).build());
//            tweetBrowser.sync();
//            brURL = tweetBrowser.getURL();
//            Print("twitter url:\n" + brURL);
//            Assert.assertTrue("Verification - Verify Social Media: Verify that the Twitter site was launched properly.", brURL.matches(".*twitter\\.com.*"));
//            tweetBrowser.close();*/
//
//            // Verify the LinkedIn link
//            clickWebElement(appModel.AdvantageShoppingPage().LinkedInImage());
//            threadSleep(5000);
//            socialLink = "linkedin";
//            Print("linkedIn title: " + Linkedintitle);
//            Browser linkedinBrowser = BrowserFactory.attach(new BrowserDescription.Builder().title(Linkedintitle).build());
//            linkedinBrowser.sync();
//            brURL = linkedinBrowser.getURL();
//            Print("linkedIn url:\n" + brURL);
//            Assert.assertTrue("Verification - Verify Social Media: Verify that the LinkedIn site was launched properly.", brURL.matches(".*linkedin\\.com.*"));
//            linkedinBrowser.close();
//        } catch (GeneralLeanFtException e) {
//            printError(e, "BrowserFactory.attach(new BrowserDescription.Builder().title(Linkedintitle).build())");
//            Reporter.reportEvent("verify Social Media ERROR", "Could not locate the browser with the matching URL of : " + socialLink, Status.Failed);
//            Assert.assertTrue("Verification - Verify Social Media: Could not locate the browser with the  matching URL of the social media: " + socialLink, false);
//        }
//    }

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
    public void verifyUserLinks() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        // Go to home page
        clickWebElement(appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink());
        browserSync();

        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().MyAccountWebElement());
        browserSync();
        if (existsWebElement(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement(), 2)) {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - My Account.", Status.Passed, img);
        } else {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - My Account.", Status.Failed, img);
        }
//        Verification(Verify.isTrue(existsWebElement(appModel.AdvantageShoppingPage().MyAccountHeaderLabelWebElement(), 2), "Verification - Verify User Links", " Verify that the user links navigations work - My Account."));

        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().MyOrdersWebElement());
        browserSync();
        if (existsWebElement(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement(), 2)) {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - My Orders.", Status.Passed, img);
        } else {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - My Orders.", Status.Failed, img);
        }
//        Verification(Verify.isTrue(existsWebElement(appModel.AdvantageShoppingPage().MyOrdersHeaderLabelWebElement(), 2), "Verification - Verify User Links", " Verify that the user links navigations work - My Orders."));

        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().SignOutWebElement());

        browserRefresh();
        browserSync();

        if (!isSignedIn()) {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - Sign Out.", Status.Passed, img);
        } else {
            img = browser.getPage().getSnapshot();
            Reporter.reportEvent("Verify User Links", "Verify that the user links navigations work - Sign Out.", Status.Failed, img);
        }
//        Verification(Verify.isTrue(!isSignedIn(), "Verification - Verify User Links", " Verify that the user links navigations work - Sign Out."));
    }

    // This internal method strips the search parameter from the Search result page title and returns it
    public String getSearchParameterFromSearchResultsTitle() throws GeneralLeanFtException {
        // Get the regular expression pattern from the Search Result Title object design time description
        String pattern = appModel.AdvantageShoppingPage().SearchResultTitleWebElement().getDescription().getInnerText().toString();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Get the actual inner text of the Search Result Title object during runtime
        String searchResultTitleElementInnerText = getWebElementInnerText(appModel.AdvantageShoppingPage().SearchResultTitleWebElement());
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
    public void verifySearchUsingURL() throws GeneralLeanFtException, ReportException {
        // Sign in to the store
        signIn();

        String searchParameter;
        searchParameter = "Laptops";

        // Go to the Search page as a workaround - search for Laptops
        browserNavigate(SearchURL + "?viewAll=" + searchParameter);
        browserSync();
//        Boolean isSuccessfullContacting = appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement().exists(2);
        checkWithReporterIsTrue(existsWebElement(appModel.AdvantageShoppingPage().LaptopFilterSearchCheckbox()),
                "Verify Search using URL", "Verify that the Laptops checkbox element exists.");

        // Get the actual inner text of the Search Result Title object during runtime
        checkWithReporterIsTrue(getSearchParameterFromSearchResultsTitle().equals(searchParameter),
                "Verify Search using URL", "Verify that title reflects the search parameter: " + searchParameter + ".");

        searchParameter = "Speakers";
        // Go to the Search page as a workaround - search for Speakers
        browserNavigate(SearchURL + "?viewAll=" + searchParameter);
        browserSync();
        checkWithReporterIsTrue(existsWebElement(appModel.AdvantageShoppingPage().SpeakersFilterSearchCheckbox()),
                "Verify Search using URL", "Verify that the Speakers checkbox element exists.");

        // Get the actual inner text of the Search Result Title object during runtime
        checkWithReporterIsTrue(getSearchParameterFromSearchResultsTitle().equals("Speakers"),
                "Verify Search using URL", "Verify that the title reflects the search parameter: " + searchParameter + ".");
    }

	/*
    @Test
    public void verifyDownloadPageTest() throws GeneralLeanFtException, ReportException {
        Print("Wait for main page loading...");
        threadSleep(5000);
        browserNavigate(appURL + "/downloads");
        browserSync();
        checkWithReporterIsTrue(existsWebElement(appModel.DownloadPage().DownloadAndroidAppWebElement()),
                "Verify Download page", "Verify that the android link works");
        checkWithReporterIsTrue(existsWebElement(appModel.DownloadPage().DownloadIosAppWebElement()),
                "Verify Download page", "Verify that the iOS link works");
    }
*/
    ////////////////////////////////////////////////// moti gadian Code added on  27/3/17 /////////////////////////////////////////////////////////////

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
        threadSleep(1000);
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
        //setValueEditField(appModel.AdvantageShoppingPage().SearchOrderEditField(), ProductName);

        // TODO: check if previously purchased product in this list
        if (existsWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement())) {
            clickWebElement(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());

            checkWithReporterIsTrueOnly(existsWebElement(appModel.AdvantageShoppingPage().RemoveFromOrderValidate()),
                    "Verify Search orders", "Verify that the alert window element exists.");
            clickWebElement(appModel.AdvantageShoppingPage().YesCANCELButton());
            threadSleep(1000);
            signOut();
            threadSleep(1000);
        } else {
            printError("Empty list of orders");
        }
    }

   /*@Test
    public void outOfStockTest() throws GeneralLeanFtException {

    	/*
    	 * Missing (Quantity=0 in all colors)- validate that the UI is marked correctly and that the user can watch all its details
    	 * - change color to see the different pictures,
    	 * read the 'more info' section (click and open it in mobile) but,
    	 * that the user can't change the quantity or add it to cart
    	 *

    	browser.sync();
    	appModel.AdvantageShoppingPage().HeadphonesImg().click();
    	appModel.AdvantageShoppingPage().SoldOutHeadphonesWebElement().click();
    	appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement().click();


    	Verify.isFalse(appModel.AdvantageShoppingPage().ADDTOCARTButton().isEnabled(),"Verification - Verify sold out item","Verify that the ADD TO CART button not enabled.");
    	Verify.isTrue(appModel.AdvantageShoppingPage().QuantityOfProductWebEdit().isReadOnly(),"Verification - Verify sold out item","Verify that the Quantity field not enabled to edit.");

    }*/

    @Test
    public void payButtonRegExTest() throws GeneralLeanFtException, ReportException {
        //The button text always starts with Pay to allow for adding regular expressions in object identification.

        //In  web the button calls "CHECKOUT ({{RegEx}})"

        String pattern = "CHECKOUT(.*)(\\d+).(\\d+)[')']";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        signIn();
        browserRefresh();
        threadSleep(1000);
        clickWebElement(appModel.AdvantageShoppingPage().LaptopsImg());

        // Select an item to purchase and add it to the cart
        clickWebElement(appModel.AdvantageShoppingPage().laptopFororderService());
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        threadSleep(1000);


        String checkOutTXT = getWebElementInnerText(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        Matcher m = r.matcher(checkOutTXT);
        boolean match = m.find();
        Print(checkOutTXT + " :: " + match);

        checkWithReporterIsTrue(match, "Verify CHECKOUT RegEx", "Verify that the text in CHECKOUT button start with 'CHECKOUT'.");
    }

    @Test
    public void negativeLoginTest() throws GeneralLeanFtException, ReportException {
        //Try to login with non valid credentials and verify the message "invalid user name or password"
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        clickWebElement(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());
        waitUntilElementExists(appModel.AdvantageShoppingPage().SIGNINButton());
        // Fill in the user name and password
        setValueEditField(appModel.AdvantageShoppingPage().UsernameLoginEditField(), "bla bla");
        setValueEditField(appModel.AdvantageShoppingPage().PasswordLoginEditField(), "bla pss");
        // Check the Remember Me checkbox
        setCheckBox(appModel.AdvantageShoppingPage().RememberMeCheckBox(), true);
        // Click on sign in button
        clickWebElement(appModel.AdvantageShoppingPage().SIGNINButton());
        Print("Wait for message");

//        Boolean isInvalidUserMessageElement = existsWebElement(appModel.AdvantageShoppingPage().InvalidUserMessageWebElement());
//        Print("isInvalidUserMessageElement " + isInvalidUserMessageElement);
        checkWithReporterIsTrue(existsWebElement(appModel.AdvantageShoppingPage().InvalidUserMessageWebElement()),
                "Verify Negative Sign In", "Verify that we can't login with non valid credentials");
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


   /* @Test
    public void chatSupportTest() throws GeneralLeanFtException, ReportException {

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
			threadSleep(2000);
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

    /**
     * validate all the following fields
     * - email address (mandatory)
     * - select category (optional)
     * - select product  from the selected category - one product only can be selected (optional)
     * - free text of the issue (mandatory) "Send" option
     * after clicking OK,  the user will receive a message saying "thank you for contacting Advantage support"
     * play with this - select something, go back and try to change it - try to break this feature
     * @throws GeneralLeanFtException
     * @throws ReportException
     */
    @Test
    public void contactSupportTest() throws GeneralLeanFtException, ReportException {
    	String emailTestValue1 = "fffff";
    	String emailTestValue2 = "user@demo.com";
    	String emailTestValue3 = "sometxt";
    	String subjectTestValue1 = "Advantage Online Shopping ...";

        //try to send request with just txt in the email field (not should be working)
        setValueEditField(appModel.AdvantageShoppingPage().EmailContactUsWebElement(), emailTestValue1);
        Boolean isEnabledButton = isEnabledButton(appModel.AdvantageShoppingPage().SENDContactUsButton());
        Print("is SENDContactUsButton enabled: " + isEnabledButton);
        checkWithReporterIsFalse(isEnabledButton, "Verify contact Us request",
                "Verify that we cant send request with improper Email.");

        //try to send request with just email  in the email field (not should be working)
        setValueEditField(appModel.AdvantageShoppingPage().EmailContactUsWebElement(), emailTestValue2);
        isEnabledButton = isEnabledButton(appModel.AdvantageShoppingPage().SENDContactUsButton());
        Print("is SENDContactUsButton enabled: " + isEnabledButton);
        checkWithReporterIsFalse(isEnabledButton, "Verify Contact Us request",
                "Verify that we cant send request with Email without Subject.");

        //try to send request with just txt in the email field and subject (not should be working)
        setValueEditField(appModel.AdvantageShoppingPage().EmailContactUsWebElement(), emailTestValue3);
        setValueEditField(appModel.AdvantageShoppingPage().ContactUsSubject(), subjectTestValue1);
        clickWebElement(appModel.AdvantageShoppingPage().SENDContactUsButton());
        Boolean isExistsThankYouForContactingAdvantageSupportWebElement = existsWebElement(appModel.AdvantageShoppingPage().ThankYouForContactingAdvantageSupportWebElement(),2);
        Print("is ThankYouForContactingAdvantageSupportWebElement exists: " + isExistsThankYouForContactingAdvantageSupportWebElement);
        checkWithReporterIsFalse(isExistsThankYouForContactingAdvantageSupportWebElement, "Verify Contact Us request",
                "Verify that we cant send request with unproper Email and Subject.");
    }

    @Test
    public void deleteAccountTest() throws GeneralLeanFtException, ReportException {

        createNewAccountEx("deleteUser", "Delete123",false);
        threadSleep(2000);
        clickWebElement(appModel.AdvantageShoppingPage().MyAccountWebElement());
        clickWebElement(appModel.deleteAccountButton());
        clickWebElement(appModel.yesWebElement());
        threadSleep(1000);
        boolean deletedSuccessfullyElement = appModel.AdvantageShoppingPage().accountDeletedSuccessfullyWebElement().isVisible();
        Print("Wait for user will sign out");
        threadSleep(5000);
        checkWithReporterIsTrue(deletedSuccessfullyElement, "Verify logout", "Verify if the user really signed out from site");
    }

//    public boolean isWorngCategoryForProduct() throws Exception {
//        setUpBeforeClass();
//
//        setUp();
//        appModel = new AdvantageStagingAppModel(browser);
//        browser.sync();
//
//        return false;
//    }


    public void Checkout() {
        clickWebElement(appModel.AdvantageShoppingPage().ADDTOCARTButton());
        clickWebElement(appModel.AdvantageShoppingPage().CHECKOUTHoverButton());
        Print("Wait for page to bee loaded");
        threadSleep(5000);
        waitUntilElementExists(appModel.AdvantageShoppingPage().NEXTButton());
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

//    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException {
//        if (!VerifyMethod)
//            throw new GeneralLeanFtException("Verification ERORR - verification of test fails! check runresults.html");
//    }

    public void Close() {
        try {
            browser.close();
        } catch (GeneralLeanFtException e) {
            printError(e, "browser.close ERROR");
            fail("GeneralLeanFtException browser.close ERROR");
        }
    }

    private static void printCaptionTest(String nameOfTest) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("START " + nameOfTest);
    }

    private static void printEndOfTest(String nameOfTest) {
        System.out.println("END " + nameOfTest);
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

    private void checkWithReporterIsTrue(Boolean isValue, String stepName, String description) throws ReportException, GeneralLeanFtException {
        img = browser.getPage().getSnapshot();
        if (isValue) {
            Reporter.reportEvent(stepName, description, Status.Passed, img);
        } else {
            printError(stepName + ". " + description);
            Reporter.reportEvent(stepName, description, Status.Failed, img);
            fail("Should be " + !isValue);
        }
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

    private void checkWithReporterIsTrueOnly(Boolean isValue, String stepName, String description) throws ReportException, GeneralLeanFtException {
        img = browser.getPage().getSnapshot();
        if (isValue) {
            Reporter.reportEvent(stepName, description, Status.Passed, img);
        } else {
            Reporter.reportEvent(stepName, description, Status.Failed, img);
        }
    }

}
