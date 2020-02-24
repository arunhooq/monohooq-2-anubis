package sanctuary.utils;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import io.restassured.response.Response;
import partner.utils.Constants;
import com.automation.reports.ReporterLog;
import com.automation.testengine.TestUtilities;


public class ApiVerifications
{
	public static void verifyStatusCode(int actualStatusCode, int expectedStatusCode)
	{
		try
		{
			if(actualStatusCode != expectedStatusCode)
				throw new Exception();
		}
		catch (Exception e)
		{
			ReporterLog.softAssert.fail();
			TestUtilities.logReportFailure(e,"actualStatusCode: "+actualStatusCode,"expectedStatusCode: "+expectedStatusCode);
		}
	}

	public static void verifyStatusCode(int actualStatusCode, int expectedStatusCode,String message)
	{
		try
		{
			if(actualStatusCode != expectedStatusCode)
				throw new Exception(message);
		}
		catch (Exception e)
		{
			ReporterLog.softAssert.fail();
			TestUtilities.logReportFailure(e,"actualStatusCode: "+actualStatusCode,"expectedStatusCode: "+expectedStatusCode);
		}
	}

	public static void verifyRequestSucceed(Response res)
	{
		try
		{
			int responseCode = res.getStatusCode();
			if(responseCode >= 300)
			{
				throw new Exception("Error Details : " + ApiVerifications.getErrorcode(res).get(Constants.DETAIL).toString());
			}
		}
		catch (Exception e)
		{
			ReporterLog.softAssert.fail();
			TestUtilities.logReportFailure(e,"Error Details : " + ApiVerifications.getErrorcode(res).get(Constants.DETAIL).toString());
		}
	}

	public static void verifyStringMatching(String actualString, String expectedString)
	{
		try
		{
			if(actualString.equalsIgnoreCase(expectedString))
			{
				TestUtilities.logReportPass("actualString: "+ actualString +" is matching with the expectedString: "+expectedString);
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actualString,"expectedString: "+expectedString);
		}
	}

	public static void verifyboolMatching(Boolean actual, Boolean expected)
	{
		try
		{
			if(actual.equals(expected))
			{
				TestUtilities.logReportPass("actualString is matching with the expectedString");
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actual,"expectedString: "+expected);
		}
	}

	public static void verifyStringContains(String actualString, String partialString)
	{
		try
		{
			if(actualString.contains(partialString))
			{
				TestUtilities.logReportPass("actualString: "+ actualString +"is matching with the partialString: "+partialString);
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actualString,"partialString: "+partialString);
		}
	}

	public static void verifyStringdoesnotContain(String actualString, String partialString)
	{
		try
		{
			if(!actualString.contains(partialString))
			{
				TestUtilities.logReportPass("actualString: "+ actualString +"does not contain this partialString: "+partialString);
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actualString,"partialString: "+partialString);
		}
	}

	public static void verifyStringStartWith(String actualString, String partialString)
	{
		try
		{
			if(actualString.startsWith(partialString))
			{
				TestUtilities.logReportPass("actualString: "+ actualString +"start with this partialString: "+partialString);
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actualString,"partialString: "+partialString);
		}
	}

	public static void verifyStringEndWith(String actualString, String partialString)
	{
		try
		{
			if(actualString.endsWith(partialString))
			{
				TestUtilities.logReportPass("actualString: "+ actualString +"end with this partialString: "+partialString);
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"actualString: "+actualString,"partialString: "+partialString);
		}
	}

	public static void verifyNotNull(String text)
	{
		try
		{
			if(text == null || text == "")
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,text.toString());
		}
	}

	public static void verifyStringisNull(String text)
	{
		try
		{
			if(text == null || text.equals("") || text.isEmpty())
			{
				TestUtilities.logReportPass("The supplied string is null");
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e,"The supplied string is not null "+text.toString());
		}
	}

	public static void verifyiftrue(boolean flag)
	{
		try
		{
			if(flag == true)
			{
				TestUtilities.logReportPass("The flag status is true as expected");
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static void verifyiffalse(boolean flag)
	{
		try
		{
			if(flag == false)
			{
				TestUtilities.logReportPass("The flag status is false as expected");
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static void verifyStringhasdecimal(String text)
	{
		try
		{
			if(text.contains("."))
			{
				ReporterLog.fail("String contains decimal", "String contains decimal");
			}
			else
			{
				ReporterLog.pass("String does not contain decimal","String does not contain decimal");
			}
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static void verifyEmptyArray(JSONArray array)
	{
		try
		{
			if(array.size() == 0)
			{
				TestUtilities.logReportPass("JSONArray is empty");
			}
			else
				throw new Exception();
		}
		catch (Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
	}

	public static Map<String,String> getErrorcode(Response getcatalog)
	{
		Map<String,String> errorMap = new HashMap<String,String>();
		try
		{
			JSONArray error = (JSONArray) ReusableMethods.rawtoJsonObject(getcatalog).get("errors");
			JSONObject errorlist = (JSONObject) error.get(0);

			if(errorlist.containsKey("status"))
			{
				errorMap.put("status", errorlist.get("status").toString());
			}
			errorMap.put("code", errorlist.get("code").toString());
			errorMap.put("detail", errorlist.get("detail").toString());
		}
		catch(Exception e)
		{
			TestUtilities.logReportFailure(e);
		}
		return errorMap;
	}
}
