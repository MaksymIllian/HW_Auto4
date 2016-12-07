package practice4.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Admin on 07.12.2016.
 */
public class ExampleTest {

    @Test
    public void loginTest(){
        Assert.assertEquals(1,0);
    }

    @Test
    public void loadDataTest(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void logoutTest(){
        Assert.assertEquals(1,1);
    }

}
