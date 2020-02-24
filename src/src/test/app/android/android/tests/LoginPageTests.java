package android.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.testengine.ConfigDetails;
import com.automation.testengine.TestUtilities;
import com.automation.utilities.GlobalConstant;
import com.automation.utilities.ReadTestData;

public class LoginPageTests extends TestConfiguration {

	@Test(priority = 1, enabled = false, groups = {
			GlobalConstant.GROUP_SANITY_Visitor,
	"verify_Login_SingtelCast" })
	public void verify_Login_SingtelCast() {

		ReadTestData.fnAddTestRailScriptID(1234);
		if(ConfigDetails.country.equalsIgnoreCase(GlobalConstant.COUNTRY_SG))
		{
			try {
				basePage.getLoginPage().performSingtelCastLogin(ReadTestData.SINGTEL_ID, ReadTestData.SINGTEL_PWD);

			} catch (Exception e) {
				TestUtilities.logReportFatal(e);
			}
		}
		else			
			throw new SkipException("Intentionaly Skiping this test as it is not applicable for UserType :"+ConfigDetails.userType+" , Country : "+ConfigDetails.country);
	}

}
