package practice4;

import org.testng.annotations.*;

/**
 * Created by Serhii on 30-Nov-16.
 */
public class BeforeAfterExample {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @Test
    public void test1() {
        System.out.println("TEST1");
    }

    @Test
    public void test2() {
        System.out.println("TEST2");
    }

    @Test
    public void test3() {
        System.out.println("TEST3");
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }


    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }


}
