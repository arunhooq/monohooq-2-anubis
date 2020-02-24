package ios.pages;

import org.openqa.selenium.By;

import com.automation.testengine.ActionEngine;
import com.automation.testengine.TestUtilities;


public class MyRentalsPage  extends ActionEngine {
	
	public static By navBackBtn = By.name("navbar back btn");
	public static By tabRent = By.xpath("//XCUIElementTypeOther[2]/XCUIElementTypeTabBar/XCUIElementTypeButton[5]");
	
	
	
	
	/***
	 * Function Name :- getMePage Developed By :- Pankaj Kumar Date :-
	 * 4-July-2019
	 */
	public MePage navigateBack()
	{
		try {
			
			click(navBackBtn, "Back From My Rentals page");
		} catch (Exception e) {TestUtilities.logReportFailure(e);}
		return new MePage();
	}
	
	public MyRentalsPage clickRentalTab() {
		try {
		click(tabRent, "Rent tab is entered");
		}catch(Exception e) {TestUtilities.logReportFailure(e);}
		return new MyRentalsPage();
	}
	
	public MyRentalsPage clickOnAnyRentalMovie() {
		try {
		
			
			
			
		}catch(Exception e) {TestUtilities.logReportFailure(e);}
		return new MyRentalsPage();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
