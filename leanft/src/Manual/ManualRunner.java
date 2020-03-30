package Manual;

import cucumber.api.CucumberOptions;
import org.junit.AfterClass;

import org.junit.BeforeClass;

import com.hpe.alm.octane.OctaneCucumber;
import org.junit.runner.RunWith;


import unittesting.*;
/*
* this class are empty - he call to 'Manual.OrderHistory_menual_to_auto' that implement an automation test
* */

@RunWith(OctaneCucumber.class)
@CucumberOptions(features="Check_order_history.feature")
public class ManualRunner extends UnitTestClassBase {

    public ManualRunner() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new ManualRunner();
        globalSetup(ManualRunner.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }



}