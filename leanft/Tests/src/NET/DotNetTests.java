package NET;

import static org.junit.Assert.*;

import Web.ApiTests;
import Web.ApiTests.*;
import com.hp.lft.sdk.wpf.*;
import com.hp.lft.sdk.wpf.TableCell;
import com.hp.lft.sdk.wpf.TableRow;
import com.hp.lft.sdk.wpf.UiObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.winforms.*;
import com.hp.lft.verifications.*;

import unittesting.*;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class DotNetTests extends UnitTestClassBase {


    private static DotNetAppModel appModel;
    private static ProcessBuilder window;

    private static String applocation = "C:\\Users\\gadian\\Desktop\\AOS\\2017_05_24_AdvantageShopAdministrator\\Debug\\AdvantageShopAdministrator.exe";
    private static String SERVER = "http://52.32.172.3:8080";

    private static String UNAME = "Dot.NetUser";
    private static String PASSWORD = "Password1";

    public DotNetTests()  {
        //Change this constructor to private if you supply your own public constructor


    }

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
    }

    @After
    public void tearDown() throws Exception {
    }


    ///////////////////////////////////////////// START TESTS //////////////////////////////////

    @Test
    public void AddColorAndQuantityTest() throws GeneralLeanFtException, InterruptedException {

        ///just for test the list object
        appModel.AdvantageShopAdministrator().SideList().select("PRODUCTS");
        Thread.sleep(3000);
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        //appModel.AdvantageShopAdministrator().ProductsDataGridTable().selectCell(9,"Name");

        appModel.AdvantageShopAdministrator().SearchEditField().setText("mini");
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());
        appModel.AdvantageShopAdministrator().ProductsDataGridTable().selectCell(0,"Name");

        appModel.AdvantageShopAdministrator().ProductsWpfTabStrip().select("SPECIFICATIONS");
        String val = appModel.AdvantageShopAdministrator().WEIGHTEditField().getText();
        if(val.equals("0.55 lb"))
            appModel.AdvantageShopAdministrator().WEIGHTEditField().setText("1.2 lb");
        else
            appModel.AdvantageShopAdministrator().WEIGHTEditField().setText("0.55 lb");


        /*appModel.AdvantageShopAdministrator().ProductsWpfTabStrip().select("CUSTOMISATION");

        appModel.AdvantageShopAdministrator().ADDCOLORSButton().click();
        appModel.AdvantageShopAdministrator().YelloeCheckBox().click();
        //add quantity
        /////

        appModel.AdvantageShopAdministrator().ProductsColorsGridTable().selectCell(2,"QUANTITY");*/
        appModel.AdvantageShopAdministrator().SAVEButton().click();
        appModel.AdvantageShopAdministrator().SAVEButton().click();

        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());


    }

    @Test
    public void UserManagementTest() throws GeneralLeanFtException, InterruptedException {

        /*
        /create new user of type user (customer)
        save
        search for the new user
       change its password
        delete the user
         */
        Thread.sleep(3000);
        appModel.AdvantageShopAdministrator().SideList().select("USERS MANAGEMENT");
        appModel.AdvantageShopAdministrator().SideList().select("USERS MANAGEMENT");

        waitUntilElementExists(appModel.AdvantageShopAdministrator().UsersControlUiObject());
        System.out.println("Creating user... ");

        appModel.AdvantageShopAdministrator().ADDUSERButton().click();
        appModel.AdvantageShopAdministrator().UserNameUserManageEditField().setText(UNAME);
        appModel.AdvantageShopAdministrator().EmailUserManageEditField().setText("Dot@Net.com");
        appModel.AdvantageShopAdministrator().PasswordUserManageEditField().setText(PASSWORD);
        appModel.AdvantageShopAdministrator().ConfirmPasswordUserManageEditField().setText(PASSWORD);
        appModel.AdvantageShopAdministrator().FirstNameUserManageEditField().setText("Dot");
        appModel.AdvantageShopAdministrator().LastNameUserManageEditField().setText("Net");
        appModel.AdvantageShopAdministrator().OKUserManageButton().click();

        System.out.println("vlidate user via web login... ");
        ApiTests test  = new ApiTests();
        Verification(Verify.isTrue(test.signIn(UNAME,PASSWORD,SERVER),"Sign In .Net New user via web","verify that user created  by .Net app and can login via web"));

        //System.out.println("Change Password... ");

       int index  = 0;
        Thread.sleep(2000);
        for (TableRow row: appModel.AdvantageShopAdministrator().UsersTypesGridTable().getRows()) {
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
        appModel.AdvantageShopAdministrator().UsersTypesGridTable().selectCell(index,"DELETE");
        appModel.DeleteUserConfirmationWindow().OKDeleteButton().click();










    }


    ///////////////////////////////////////////// END TESTS //////////////////////////////////


    public static void InitBeforeClass() throws IOException, GeneralLeanFtException {

        appModel = new DotNetAppModel();
        window  = new ProcessBuilder(applocation);

        window.start();
        SignIn();

    }


    public static void SignIn() throws GeneralLeanFtException {
        appModel.AdvantageShopAdministrator().UserNameEditField().setText("admin");
        appModel.AdvantageShopAdministrator().PasswordEditField().setText("adm1n");
        appModel.AdvantageShopAdministrator().ServerEditField().setText(SERVER);

        appModel.AdvantageShopAdministrator().SIGNINButton().click();
        appModel.AdvantageShopAdministrator().SIGNINButton().click();
        waitUntilElementExists(appModel.AdvantageShopAdministrator().PRODUCTSUiObject());


    }

    public void ChangePass() throws GeneralLeanFtException {
        appModel.ResetPasswordForDotNetUserWindow().NewPassEditField().setText("Password2");
        appModel.ResetPasswordForDotNetUserWindow().RePassEditField().setText("Password2");
        appModel.ResetPasswordForDotNetUserWindow().OKPasswordButton().click();
    }




    public static boolean waitUntilElementExists(UiObject appElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(appElem,new WaitUntilTestObjectState.WaitUntilEvaluator<UiObject>(){
            public boolean evaluate(UiObject we){
                try{
                    return we.exists();
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
}