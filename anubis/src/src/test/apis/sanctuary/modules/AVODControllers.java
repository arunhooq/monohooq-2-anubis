package sanctuary.modules;



import org.json.simple.JSONObject;


import com.automation.testengine.TestUtilities;


import io.restassured.response.Response;
import sanctuary.utils.ReusableMethods;
import sanctuary.utils.ApiVerifications;



public class AVODControllers {
	
	public static void validate_AVODbyCustomerType(Response accountMe) throws NullPointerException
	{
		try
		{
			
			int subscriptionDuration = 0;
			JSONObject evergentObject = (JSONObject) ReusableMethods.rawtoJsonObject(accountMe).get("evergent");			
			JSONObject userConfig 	  = (JSONObject) ReusableMethods.rawtoJsonObject(accountMe).get("userConfig");
			
			boolean prerollEnabled,adsEnabled,interstitialEnabled = false;				
			String customerType = null,bundleType = null,skuType = null,isRecurringSubscription = null;
			
			String userLevel = ReusableMethods.rawtoJsonObject(accountMe).get("userLevel").toString();
			
			
			if(userLevel.equals("10"))		
			{		
				
				prerollEnabled = (boolean) userConfig.get("prerollEnabled");
				adsEnabled = (boolean) userConfig.get("adsEnabled");
				interstitialEnabled = (boolean) userConfig.get("interstitialEnabled");
				
				ApiVerifications.verifyiftrue(prerollEnabled);
				ApiVerifications.verifyiftrue(adsEnabled);
				ApiVerifications.verifyiftrue(interstitialEnabled);				
			}			
			else
			{
								
				customerType = evergentObject.get("customerType").toString();
				bundleType 	= evergentObject.get("bundleType").toString();
				skuType 		= evergentObject.get("SKUType").toString();
				
				if(!skuType.equals("Forever"))
				{
					subscriptionDuration = Integer.parseInt(evergentObject.get("subscriptionDuration").toString());	
				}
					
				isRecurringSubscription = evergentObject.get("isRecurringSubscription").toString();										
			
			
			// The partnerType logic is not implmented. Need more information on this
			//String partnerType = evergentObject.get("partnerType").toString();
																	
				if (customerType.equalsIgnoreCase("OTT"))			
				{
					if (skuType.equalsIgnoreCase("onetime"))
					{
						if(subscriptionDuration > 7)
						{
							prerollEnabled = (boolean) userConfig.get("prerollEnabled");
							ApiVerifications.verifyiftrue(prerollEnabled);
						}
						else if (subscriptionDuration == 1 || subscriptionDuration == 7)
						{
							adsEnabled = (boolean) userConfig.get("adsEnabled");
							prerollEnabled = (boolean) userConfig.get("prerollEnabled");
							ApiVerifications.verifyiftrue(prerollEnabled);
							ApiVerifications.verifyiffalse(adsEnabled);
							ApiVerifications.verifyiffalse(interstitialEnabled);
							
						}					
					}
					else if(skuType.equalsIgnoreCase("Recurring"))
					{
						ApiVerifications.verifyiffalse((boolean) userConfig.get("prerollEnabled"));
						ApiVerifications.verifyiffalse((boolean) userConfig.get("adsEnabled"));
						ApiVerifications.verifyiffalse((boolean) userConfig.get("interstitialEnabled"));
					}
				}
				
				else if (customerType.equalsIgnoreCase("bundle"))
				{
					
					if(bundleType.equalsIgnoreCase("hard") || bundleType.equalsIgnoreCase("Hardbundle"))
					{
						prerollEnabled = (boolean) userConfig.get("prerollEnabled");
						adsEnabled = (boolean) userConfig.get("adsEnabled");
						interstitialEnabled = (boolean) userConfig.get("interstitialEnabled");
						
						ApiVerifications.verifyiffalse(prerollEnabled);
						ApiVerifications.verifyiffalse(adsEnabled);
						ApiVerifications.verifyiffalse(interstitialEnabled);
						
					}
					else if (bundleType.equalsIgnoreCase("soft") && isRecurringSubscription == "false")
					{
						prerollEnabled = (boolean) userConfig.get("prerollEnabled");
						adsEnabled = (boolean) userConfig.get("adsEnabled");
						interstitialEnabled = (boolean) userConfig.get("interstitialEnabled");
						
						ApiVerifications.verifyiftrue(prerollEnabled);
						ApiVerifications.verifyiftrue(adsEnabled);
						ApiVerifications.verifyiftrue(interstitialEnabled);
					}
						
				}
				else if (customerType.equalsIgnoreCase("promo"))
				{
					prerollEnabled = (boolean) userConfig.get("prerollEnabled");
					adsEnabled = (boolean) userConfig.get("adsEnabled");
					interstitialEnabled = (boolean) userConfig.get("interstitialEnabled");
					
					ApiVerifications.verifyiffalse(prerollEnabled);
					ApiVerifications.verifyiffalse(adsEnabled);
					ApiVerifications.verifyiffalse(interstitialEnabled);
					
				}
				else
				{
					prerollEnabled = (boolean) userConfig.get("prerollEnabled");
					adsEnabled = (boolean) userConfig.get("adsEnabled");
					interstitialEnabled = (boolean) userConfig.get("interstitialEnabled");
					
					ApiVerifications.verifyiftrue((boolean) userConfig.get("prerollEnabled"));
					ApiVerifications.verifyiftrue((boolean) userConfig.get("adsEnabled"));
					ApiVerifications.verifyiftrue((boolean) userConfig.get("interstitialEnabled"));
				}
				
			}
						
		}
		catch(Exception e) {
			TestUtilities.logReportFailure(e,"The AVOD for customer type is not working as expected ");
		}		
			
		}	
 
 	}
