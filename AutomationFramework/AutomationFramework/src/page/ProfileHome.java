package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import common.UserType;
import element.BaseWebElement;
import framework.FindMethod;
import framework.WWWDriver;

public class ProfileHome extends BasePage {
	
	//Profile Information
	private BaseWebElement welcomeMessage = new BaseWebElement(FindMethod.CLASSNAME, "welcome-message-contents");
	private BaseWebElement pointsMessage = new BaseWebElement(FindMethod.CLASSNAME, "sub-message");
	private BaseWebElement referButton = new BaseWebElement(FindMethod.CLASSNAME, "refer-button-wrapper");
	public BaseWebElement firstName = new BaseWebElement(FindMethod.NAME, "firstName");
	public BaseWebElement lastName = new BaseWebElement(FindMethod.NAME, "lastName");
	public BaseWebElement email = new BaseWebElement(FindMethod.NAME, "email");
	public BaseWebElement phone = new BaseWebElement(FindMethod.NAME, "phone");
	private BaseWebElement showPasswordToggle = new BaseWebElement(FindMethod.CLASSNAME, "snap-input-icon--password");
	
	//Manage Meal Plan Screen
	private BaseWebElement noSubscriptionMessage = new BaseWebElement(FindMethod.CLASSNAME, "no-subscription-title");
	private BaseWebElement noSubscriptionButton = new BaseWebElement(FindMethod.XPATH, "//a[text()='Subscribe to a new plan']");
	
	//Orders Screen -- I now have an Orders Page.  Need to remove this.
	private BaseWebElement noOrdersSection = new BaseWebElement(FindMethod.CLASSNAME, "no-orders");
	private BaseWebElement viewOurMenuBtn = new BaseWebElement(FindMethod.XPATH, "//a[text()='view our menu']");
	private BaseWebElement pastOrders = new BaseWebElement(FindMethod.XPATH, "//div[@title='Past orders']");
	private BaseWebElement upcomingOrders = new BaseWebElement(FindMethod.XPATH, "//div[@title='Upcoming orders']");
	
	//Payments Screen
	private BaseWebElement paymentsStatus = new BaseWebElement(FindMethod.CLASSNAME, "explanation-text");
	private BaseWebElement addNewCard = new BaseWebElement(FindMethod.CLASSNAME, "add-new-card-button");
	
	//Favorites Screen
	private BaseWebElement noFavoritesMsg = new BaseWebElement(FindMethod.CLASSNAME, "no-favorites-message");
	
	//Customer Care Screen
	private BaseWebElement customerCareMsg = new BaseWebElement(FindMethod.CLASSNAME, "component-customer-care");
	private BaseWebElement customerCareTextBox = new BaseWebElement(FindMethod.CLASSNAME, "snap-textarea");
	private BaseWebElement customerCareSubmit = new BaseWebElement(FindMethod.XPATH, "//button[@type='submit']");
	private BaseWebElement customerCareSendAnother = new BaseWebElement(FindMethod.CLASSNAME, "send-another-message-link");
	
	
	private WebElement getProfileSaveButton()
	{
		List<WebElement> listSubmitButtons = WWWDriver.instance.findElements(By.xpath("//button[@type='submit']"));
		return listSubmitButtons.get(0);
	}
	
	private WebElement getPasswordSaveButton()
	{
		List<WebElement> listSubmitButtons = WWWDriver.instance.findElements(By.xpath("//button[@type='submit']"));
		return listSubmitButtons.get(0);
	}
	
	/**
	 * This function returns true if the message "You are not currently subscribed" and the "subscribe to 
	 * a new plan" button exists.
	 * @return Boolean
	 */
	public boolean verifyDefaultManageMealPlanScreen()
	{
		return noSubscriptionMessage.exists() && noSubscriptionButton.exists();
	}
	
	/**
	 * This function returns true if the status message of "You do not have orders" and the
	 * View Our Menu button exists.
	 * @return
	 */
	public boolean verifyDefaultOrdersScreen()
	{
		boolean statusMessage, statusViewMenuBtn;
		
		try
		{
			statusMessage = noOrdersSection.getText().contains("You do not have any active or scheduled orders.");
			statusViewMenuBtn = viewOurMenuBtn.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
		catch (NullPointerException e)
		{
			return false;
		}
		
		return statusMessage && statusViewMenuBtn;
	}
	
	public boolean goToPastOrders()
	{
		return pastOrders.click();
	}
	
	public boolean goToUpcomingOrders()
	{
		return upcomingOrders.click();
	}
	
	public boolean isPastOrdersActive()
	{
		try 
		{
			return !pastOrders.getWebElement().getAttribute("class").contains("inactive");
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean isUpcomingOrdersActive()
	{
		try 
		{
			return !upcomingOrders.getWebElement().getAttribute("class").contains("inactive");
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public boolean pastOrdersExists()
	{
		return pastOrders.exists();
	}
	
	public boolean upcomingOrdersExists()
	{
		return upcomingOrders.exists();
	}
	
	public String getPaymentStatusMessage()
	{
		try
		{
			return paymentsStatus.getText();
		}
		catch (NoSuchElementException e)
		{
			return "";
		}
	}
	
	public boolean addNewCardExists()
	{
		return addNewCard.exists();
	}
	
	public String getCustomerCareMessage()
	{
		try 
		{
			return customerCareMsg.getText();
		}
		catch (NullPointerException e)
		{
			return "";
		}
	}
	
	public String sendCustomerCareComment(String comment)
	{
		BaseWebElement customerCareMsg = new BaseWebElement(FindMethod.CLASSNAME, "component-customer-care");
		
		try
		{
			customerCareTextBox.setWebValue(comment);
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Customer Care Comment text box.";
		}
		
		if (!customerCareSubmit.click())
		{
			return "Could not find or click the submit button.";
		}
		
		//customerCareMsg.waitUntilStale();
		WWWDriver.pause(3000);  //TODO:  Figure out the waitForStale thing.
		
		return "Success";
	}
	
	public boolean sendAnotherMsgLinkExists()
	{
		return customerCareSendAnother.exists();
	}
	
	/**
	 * This will return all of the text on the Default favorites page.  There aren't 
	 * individual elements for the text items, so just use contains to see if the proper text
	 * exists.
	 * @return
	 */
	public String getNoFavoritesMessage()
	{
		return noFavoritesMsg.getText();
	}
	
	public boolean viewOurMenuExists()
	{
		return viewOurMenuBtn.exists();
	}
	
	/**
	 * To be Developed
	 * @param updateUser
	 */
	public void updateProfile(UserType updateUser)
	{
		
	}
	
	/**
	 * To be Developed
	 * @param updateUser
	 */
	public void updatePassword(UserType updateUser)
	{
		
	}
	
	/**
	 * To Be Developed
	 */
	public void showPassword()
	{
		
	}
	
	/**
	 * To Be Developed
	 */
	public void hidePassword()
	{
		
	}
}
