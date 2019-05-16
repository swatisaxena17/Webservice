package com.testautomation.StepDef;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.Utility.ExcelHandler;
import com.testautomation.Utility.PropertiesFileReader;
import com.testautomation.Utility.TestDataHandler;
import com.testautomation.Utility.TestDataPropertiesFileReader;
import com.testautomation.Utility.TestUtil;

import cucumber.api.java.en.Given;
import io.restassured.http.Headers;

public class GetRequestStepDef extends ExtentReportListener {

	TestDataHandler testdata = new TestDataHandler();
	TestDataPropertiesFileReader obj = new TestDataPropertiesFileReader();
	PropertiesFileReader obj1 = new PropertiesFileReader();

	@Given("^I want to verify number of circuits in the year in testcase_(\\d+)$")
	public void i_Want_to_Verify_Number_Of_Circuits_In_The_Year_In_testcase_(int arg1) throws Throwable {
		ExtentTest logInfo = null;
		try {

			test = extent.createTest(Feature.class, "This feature is to validate Ergast API GET Request 1");

			test = test.createNode(Scenario.class,
					"Check if user is able to retrieve valid data for GET Request in Ergast API");

			logInfo = test.createNode(new GherkinKeyword("Given"),
					"i_Want_to_Verify_Number_Of_Circuits_In_The_Year_In_testcase");

			Properties properties = obj.getProperty();
			Properties properties1 = obj1.getProperty();
			Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(
					properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_001");
			System.out.println(properties1.getProperty("baseURL") + TestDataInMap.get("URI"));

			given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI")).then().assertThat()
					.statusCode(200).and().body(TestDataInMap.get("Attribute1"), hasSize(20)).and()
					.header(TestDataInMap.get("Attribute2"), "4551");

			logInfo.pass(TestDataInMap.get("Attribute1") + " and " + TestDataInMap.get("Attribute2") + " verified");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("^I want to verify Driver name in testcase_(\\d+)$")
	public void i_Want_to_Verify_Drivers_Name_In_testcase_(int arg1) throws Throwable {
		ExtentTest logInfo = null;
		try {

			test = extent.createTest(Feature.class, "This feature is to validate Ergast API GET Request 2");

			test = test.createNode(Scenario.class,
					"Check if user is able to retrieve valid data for GET Request in Ergast API");

			logInfo = test.createNode(new GherkinKeyword("Given"), "i_Want_to_Verify_Drivers_Name_In_testcase");

			Properties properties = obj.getProperty();
			Properties properties1 = obj1.getProperty();
			Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(
					properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_002");
			System.out.println(properties1.getProperty("baseURL") + TestDataInMap.get("URI"));
			System.out.println(given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString());

			given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI")).then().assertThat()
					.statusCode(200).and().header(TestDataInMap.get("Attribute2"), "5805");

			logInfo.pass(TestDataInMap.get("Attribute2") + " in the header is verified as: 5805");

			String bodyStringValue = given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString();

			Assert.assertTrue(bodyStringValue.contains("Fernando"));

			logInfo.pass("Driver's Given name in the body is verified as: Fernando");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("^I want to verify Json array data in testcase_(\\d+)$")
	public void i_Want_to_Verify_Jsonarray_Data_testcase_(int arg1) throws Throwable {
		ExtentTest logInfo = null;
		try {

			test = extent.createTest(Feature.class, "This feature is to validate Ergast API GET Request 3");

			test = test.createNode(Scenario.class,
					"Check if user is able to retrieve valid data for GET Request in Ergast API");

			logInfo = test.createNode(new GherkinKeyword("Given"), "i_Want_to_Verify_Jsonarray_Data_testcase");

			Properties properties = obj.getProperty();
			Properties properties1 = obj1.getProperty();
			Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(
					properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_003");
			System.out.println(properties1.getProperty("baseURL") + TestDataInMap.get("URI"));
			System.out.println(given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString());

			// Print Response Json
			String resposestring = given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString();

			JSONObject jsonresponse = new JSONObject(resposestring);
			System.out.println("JSON Response from API ---" + jsonresponse);

			// Total Value
			String totalvalue = TestUtil.getValueByJPath(jsonresponse, "/MRData/total");
			System.out.println("Total value in Response Json------" + totalvalue);
			Assert.assertEquals(totalvalue, "1", "Total value is not correct ");
			logInfo.pass("Total value in Response Json is verified as: " + totalvalue);

			// Get Value from Json Array
			String raceName = TestUtil.getValueByJPath(jsonresponse, "/MRData/RaceTable/Races[0]/raceName");

			System.out.println("raceName in Response Json------" + raceName);
			Assert.assertEquals(raceName, "Brazilian Grand Prix", "Race name is not correct ");

			logInfo.pass("Race name in Response Json is verified as: " + raceName);

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("^I want to verify the list of Constructors in testcase_(\\d+)$")
	public void i_Want_to_Verify_list_of_Constructors_testcase_(int arg1) throws Throwable {
		ExtentTest logInfo = null;
		try {

			test = extent.createTest(Feature.class, "This feature is to validate Ergast API GET Request 4");

			test = test.createNode(Scenario.class,
					"Check if user is able to retrieve valid data for GET Request in Ergast API");

			logInfo = test.createNode(new GherkinKeyword("Given"), "i_Want_to_Verify_list_of_Constructors_testcase");

			Properties properties = obj.getProperty();
			Properties properties1 = obj1.getProperty();
			Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(
					properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_004");
			System.out.println(properties1.getProperty("baseURL") + TestDataInMap.get("URI"));
			System.out.println(given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString());

			// Print Response Json
			String resposestring = given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString();

			JSONObject jsonresponse = new JSONObject(resposestring);
			System.out.println("JSON Response from API ---" + jsonresponse);

			// Constructor Name
			String consnname = TestUtil.getValueByJPath(jsonresponse, TestDataInMap.get("Attribute1"));
			System.out.println("Constructor name in List 2 is------" + consnname);
			Assert.assertEquals(consnname, "Brabham-Repco", "Constructor name is not correct ");
			logInfo.pass(TestDataInMap.get("Attribute1") + " in Response Json is verified as: " + consnname);

			Headers headerarray = given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();

			for (io.restassured.http.Header header : headerarray) {
				allHeaders.put(header.getName(), header.getName());

				// System.out.println("Headers Array--------"+ allHeaders);

			}
			System.out.println("Headers Array--------" + allHeaders);

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("^I want to verify the headers in testcase_(\\d+)$")
	public void i_Want_to_Verify_Headers_In_Testcase_(int arg1) throws Throwable {
		ExtentTest logInfo = null;
		try {

			test = extent.createTest(Feature.class, "This feature is to validate Ergast API GET Request 5");

			test = test.createNode(Scenario.class,
					"Check if user is able to retrieve valid data for GET Request in Ergast API");

			logInfo = test.createNode(new GherkinKeyword("Given"), "i_Want_to_Verify_Headers_In_Testcase");

			Properties properties = obj.getProperty();
			Properties properties1 = obj1.getProperty();
			Map<String, String> TestDataInMap = ExcelHandler.getTestDataInMap(
					properties.getProperty("testdatafilepath"), properties.getProperty("sheetname"), "TestCase_005");
			System.out.println(properties1.getProperty("baseURL") + TestDataInMap.get("URI"));
			System.out.println(given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.getBody().asString());

			// Verify Response Code
			given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI")).then().assertThat()
					.statusCode(Integer.parseInt(TestDataInMap.get("Status Code")));

			logInfo.pass("Status Code in Response is verified as: " + given().when()
					.get(properties1.getProperty("baseURL") + TestDataInMap.get("URI")).getStatusCode());

			// Verify Header Info

			Headers headerarray = given().when().get(properties1.getProperty("baseURL") + TestDataInMap.get("URI"))
					.headers();
			HashMap<String, String> allHeaders = new HashMap<String, String>();

			for (io.restassured.http.Header header : headerarray) {
				allHeaders.put(header.getName(), header.getName());

			}
			System.out.println("Headers Map--------" + allHeaders);

			if (allHeaders.containsValue("Connection")) {
				logInfo.pass("Header contains value Connection");
			}

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}
}
