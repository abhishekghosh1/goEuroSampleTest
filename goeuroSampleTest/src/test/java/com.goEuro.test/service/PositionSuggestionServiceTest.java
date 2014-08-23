package test.com.goEuro.test.service; 

import com.goEuro.test.dto.PositionSuggestion;
import com.goEuro.test.service.PositionSuggestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** 
* PositionSuggestionService Tester. 
* 
* @author Abhishek Ghosh
* @since <pre>Aug 23, 2014</pre> 
* @version 1.0 
*/ 
public class PositionSuggestionServiceTest {

    private String entryPoint;
    private PositionSuggestionService positionSuggestionService;
    Collection<PositionSuggestion> positionSuggestions;
    PositionSuggestion positionSuggestion;

@Before
public void before() throws Exception {
    entryPoint = "http://api.goeuro.com/api/v2/position/suggest/en/";
    positionSuggestionService = new PositionSuggestionService();
    positionSuggestionService.setEndpoint(entryPoint);
    positionSuggestions = new ArrayList<PositionSuggestion>();;
    positionSuggestion = new PositionSuggestion();
} 

@After
public void after() throws Exception {
    positionSuggestionService.shutdown();

}

/** 
* 
* Method: getPositionSuggestions(String requestString) 
* 
*/ 
@Test(expected = IllegalArgumentException.class)
public void testGetPositionSuggestions() throws Exception {
    List<PositionSuggestion> positionSuggestionList = positionSuggestionService.getPositionSuggestions(null);
}

/** 
* 
* Method: exportAsCSV(Collection<PositionSuggestion> positionSuggestions) 
* 
*/ 
@Test
public void testExportAsCSV() throws Exception {
    long id = 123;
    positionSuggestion.set_id(id);
    positionSuggestion.setName("Berlin Tegel");
    positionSuggestion.setType("Distance");
    positionSuggestions.add(positionSuggestion);
    String exportAsCSV = positionSuggestionService.exportAsCSV(positionSuggestions);
    Assert.assertNotNull(exportAsCSV);
} 

/** 
* 
* Method: shutdown() 
* 
*/ 
@Test
public void testShutdown() throws Exception {
    Assert.assertNotNull(positionSuggestionService);
} 

/** 
* 
* Method: setEndpoint(String endpoint) 
* 
*/ 
@Test
public void testSetEndpoint() throws Exception {
    Assert.assertEquals(entryPoint, "http://api.goeuro.com/api/v2/position/suggest/en/");
} 


} 
