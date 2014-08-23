package test.com.goEuro.test.main;

import com.goEuro.test.main.EntryPoint;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.System;

import static org.junit.Assert.assertNotNull;

/**
 * EntryPoint Tester.
 *
 * @author Abhishek Ghosh
 * @version 1.0
 * @since <pre>Aug 23, 2014</pre>
 */
public class EntryPointTest {

    private String requestString;
    private EntryPoint entryPoint;

    @Before
    public void before() throws Exception {
        requestString = "Berlin";
        entryPoint = new EntryPoint();
    }

    @After
    public void after() throws Exception {
        requestString = "";
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        Assert.assertEquals(requestString, "Berlin");
        Assert.assertNotNull(requestString);
    }


    /**
     * Method: getPositionData(String arg)
     */
    @Test
    public void testGetPositionData() throws Exception {
        String output = entryPoint.getPositionData(requestString);
        Assert.assertNotNull(output);
    }

} 
