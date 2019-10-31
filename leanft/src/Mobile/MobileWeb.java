package Mobile;
 
import Web.AdvantageStagingAppModel;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.WaitUntilTestObjectState;
import com.hp.lft.sdk.mobile.Device;
import com.hp.lft.sdk.mobile.MobileLab;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.verifications.Verify;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.*;

/**
 * Created by gadian on 01/05/2017.
 */
public class MobileWeb {

    static AdvantageStagingAppModel appModel;
    static Device device;
    static Browser browser;
    String  appUrl;


   public MobileWeb(String deviceID , String Url) throws GeneralLeanFtException {

       device   = MobileLab.lockDeviceById(deviceID);
       browser  = BrowserFactory.launch(BrowserType.CHROME,device);
       appUrl   = Url;

       //navigate to Advantage online
       browser.navigate(appUrl);
       appModel = new AdvantageStagingAppModel(browser);


    }



    public boolean signIn(String USERNAME , String PASSWORD) throws GeneralLeanFtException, InterruptedException
    {
        boolean isSignedIn = true;

        waitUntilElementExists(appModel.AdvantageShoppingPage().MobileBtnWeb());

        if(!isSignedIn())
        {
            // Click the sign-in icon
            appModel.AdvantageShoppingPage().MobileBtnWeb().click();
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
            Verify.isTrue(isSignedIn,"Verification - Sign In Mobile web"  ,"Verify that the user " + USERNAME + " signed in properly.");


        }

        return isSignedIn;
    }

    public boolean isSignedIn() throws GeneralLeanFtException
    {
        waitUntilElementExists(appModel.AdvantageShoppingPage().SignOutMainIconWebElement());

        String loggedInUserName = getUsernameFromSignOutElement();

        if(loggedInUserName.isEmpty())
            return false;

        return true;
    }

    public String getUsernameFromSignOutElement() throws GeneralLeanFtException
    {
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


    public void PurchaseTest(String Uname , String Pass) throws GeneralLeanFtException, InterruptedException {

        // Sign in to the store
        signIn(Uname,Pass);

        // Empty the shopping cart
        emptyTheShoppingCart();

        // Go to home page
        appModel.AdvantageShoppingPage().AdvantageDEMOHomeLink().click();
        browser.sync();

        // Select an item to purchase and add it to the cart
        try{
            selectItemToPurchase(appModel.AdvantageShoppingPage().LaptopsImg(),appModel.AdvantageShoppingPage().HPENVY17tTouchLaptop(),1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // Pay for the item
        checkOutAndPay(true); // Verification inside

        browser.close();

    }
    public void selectItemToPurchase(WebElement productsCategory, WebElement product, int productQuantity) throws GeneralLeanFtException, InterruptedException
    {
        // Pick the product's category
        Thread.sleep(1000);
        productsCategory.click();
        Thread.sleep(1000);

        // Pick the specific product
        product.click();
        Thread.sleep(1000);

        // Select the first non-selected available color for the product
        appModel.AdvantageShoppingPage().ColorSelectorFirstWebElement().click();
        Thread.sleep(1000);

        // If the quantity is more than 1, set this value in the quantity edit-field
        if(productQuantity!= 1)
        {
            appModel.AdvantageShoppingPage().QuantityOfProductWebEdit().setValue(Integer.toString(productQuantity));
        }

        // Add it to the cart
        appModel.AdvantageShoppingPage().ADDTOCARTButton().click();
        Thread.sleep(1000);
    }


    public void checkOutAndPay(boolean fillCredentials) throws GeneralLeanFtException
    {
        // Checkout the cart for purchase
        // Click the cart icon
        appModel.AdvantageShoppingPage().CartIcon().click();
        // Click the checkout button
        appModel.AdvantageShoppingPage().CHECKOUTHoverButton().click();
        // Click Next to continue the purchase wizard
        appModel.AdvantageShoppingPage().NEXTButton().click();
        // Select the payment method
        appModel.AdvantageShoppingPage().SafepayImage().click();

        if(fillCredentials)
        {
            // Set the payment method user name
            appModel.AdvantageShoppingPage().SafePayUsernameEditField().setValue("HPE123");
            // Set the payment method password
            appModel.AdvantageShoppingPage().SafePayPasswordEditField().setValue("Aaaa1");

            // Set the Remember Me checkbox to true or false
            appModel.AdvantageShoppingPage().SaveChangesInProfileForFutureUse().set(true);
        }

        //appModel.AdvantageShoppingPage().NEXTButton().click();
        // Click the "Pay Now" button
        appModel.AdvantageShoppingPage().PAYNOWButton().click();

        waitUntilElementExists(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement());

        // Verify that the product was purchased
        if(fillCredentials)

            Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2),"Verification - Product Purchase MobileWEb:"," Verify that the product was purchased successfully"));

        else
            Verification(Verify.isTrue(appModel.AdvantageShoppingPage().ThankYouForBuyingWithAdvantageWebElement().exists(2),"Verification - Product Purchase MobileWEb:"," Verify that the product was purchased successfully"));

    }

    // This internal method empties the shopping cart
    public void emptyTheShoppingCart() throws GeneralLeanFtException
    {
        if(!isCartEmpty())
        {
            // Navigate to the cart
            appModel.AdvantageShoppingPage().CartIcon().click();
            browser.sync();

            // Get the rows number from the cart table
            int numberOfRowsInCart = appModel.AdvantageShoppingPage().CartTable().getRows().size();
            int numberOfRelevantProductRowsInCart = numberOfRowsInCart - 3; // Removing the non-relevant rows number from our counter. These are the title etc.. and rows that do not represent actual products

            // Iterate and click the "Remove" link for all products
            for(; numberOfRelevantProductRowsInCart > 0; numberOfRelevantProductRowsInCart--)
            {
                waitUntilElementExists(appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement());
                appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement().click(); // Remove the top product from the cart
            }

        }
    }

    public boolean isCartEmpty() throws GeneralLeanFtException
    {
        if(getCartProductsNumberFromCartObjectInnerText() == 0)
            return true;

        return false;
    }

    public int getCartProductsNumberFromCartObjectInnerText() throws GeneralLeanFtException
    {
        int productsNunberInCart = 0;

        // Get the regular expression pattern from the Cart icon object design time description
        //String pattern = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Get the actual inner text of the Cart icon object during runtime
        String advantageCartIcontInnerText = appModel.AdvantageShoppingPage().LinkCartIcon().getInnerText();

        // Extracting the user name from the object's text. It is concatenated to the beginning of the text.
        String productsNunberInCartString = advantageCartIcontInnerText.split("[ ]+")[0];//m.group(1).trim();
        if(!productsNunberInCartString.isEmpty())
            productsNunberInCart = Integer.parseInt(productsNunberInCartString);

        return productsNunberInCart;
    }





    public boolean waitUntilElementExists(WebElement webElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(webElem,new WaitUntilTestObjectState.WaitUntilEvaluator<WebElement>(){
            public boolean evaluate(WebElement we){
                try{
                    return we.exists() && we.isVisible();
                }
                catch(Exception e){
                    return false;
                }
            }
        });
    }

    public void Verification(boolean VerifyMethod) throws GeneralLeanFtException{

        if(!VerifyMethod)
            throw new GeneralLeanFtException("varfication ERORR - verification of test fails! check runresults.html");
    }


public static void main(String[] args){


    String[] s = "4.4.4".split("\\.");
    String join = "";


     //String sjoin = StringUtils.join(new Object[](s),"");
    System.out.println(join);


   //float x = Float.parseFloat("4.4.4");


}


}
