package Manual;


import Web.AdvantageWebTest;
import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.*;


import com.hp.lft.verifications.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import unittesting.*;




public class OrderHistory_menual_to_auto extends UnitTestClassBase {

    AdvantageWebTest webtests = new AdvantageWebTest();
    String ProductName = "HP PAVILION X360 - 11T TOUCH LAPTOP";

    public OrderHistory_menual_to_auto() {
        //Change this constructor to private if you supply your own public constructor
    }


    @Before
    public void setUp(Scenario scenario) throws Exception {
        Reporter.startTest(scenario.getName());
        webtests.initBeforeTest();
    }

    @After
    public void tearDown() throws Exception {
        webtests.Close();
    }

    @Given("^user is logged in$")
    public void Login() throws GeneralLeanFtException, InterruptedException, ReportException {

        webtests.signIn();

    }

    @When("^user buy an item and preform checkout.$")
    public void BuyAndCheckOut() throws GeneralLeanFtException, InterruptedException, ReportException {

        webtests.selectItemToPurchase(webtests.appModel.AdvantageShoppingPage().LAPTOPSWebElement(),webtests.appModel.AdvantageShoppingPage().laptopFororderService());
        webtests.checkOutAndPaySafePay();
        //ProductName = webtests.appModel.AdvantageShoppingPage().LaptopName().getInnerText();
    }

    @Then("^get a success massege - the purchese success .$")
    public void thenResultShouldBe() throws GeneralLeanFtException {


        int numberOfitems = webtests.getCartProductsNumberFromCartObjectInnerText();
        Verify.isTrue(numberOfitems==0, "Buy And CheckOut Success" );//?? throw error



    }
    @Given("^the user perform checkout in success and still logged in.$")
    public void isLogin() throws GeneralLeanFtException, InterruptedException, ReportException {


        webtests.signIn();
        Verify.isTrue(webtests.isSignedIn(), "isLogin","verify that user still logged in");

    }

    @When("^navigate to 'my orders' and do search on specific item.$")
    public void NavigateToMyOrders() throws GeneralLeanFtException, InterruptedException {

        webtests.appModel.AdvantageShoppingPage().SignOutMainIconWebElement().click();
        webtests.appModel.AdvantageShoppingPage().MyOrdersWebElement().click();
    }

    @Then("^user see the specific item that he buy and delete it$")
    public void SearchItemAndDelete() throws GeneralLeanFtException {


        webtests.appModel.AdvantageShoppingPage().OrderSearchWebElement().click();
        webtests.appModel.AdvantageShoppingPage().SearchOrderEditField().setValue(ProductName);
        webtests.appModel.AdvantageShoppingPage().FirstRemoveItemFromCartLinkWebElement().click();


        Verify.isTrue( webtests.appModel.AdvantageShoppingPage().RemoveFromOrderValidate().exists(),"Verification - Verify Search orders","Verify that the alert window element exists.");
       webtests.appModel.AdvantageShoppingPage().YesCANCELButton().click();

    }



}