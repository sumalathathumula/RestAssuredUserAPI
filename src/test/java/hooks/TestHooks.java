package hooks;

import utils.ExcelReader;
import java.util.List;
import java.util.Map;

import io.cucumber.java.Before;

public class TestHooks {

	public static List<Map<String, String>> testData;  // Store test data globally

    @Before
    public void loadTestData() {
    	System.out.println("Loading test data...");
        if (testData == null) {  // Load only once
            testData = ExcelReader.getTestData("src/test/resources/testdata/TestData.xlsx", "Users");
        }
    }
	
}
