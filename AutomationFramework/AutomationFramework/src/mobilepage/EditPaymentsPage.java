package mobilepage;

import org.openqa.selenium.NoSuchElementException;

import common.CreditCardType;
import element.BaseMobileElement;

public class EditPaymentsPage extends BasePage {
	
	private final BaseMobileElement addNewCard = new BaseMobileElement("Add New Card…");
	private final BaseMobileElement cancelEditPayment = new BaseMobileElement("Cancel");
	private final BaseMobileElement editPaymentMethods = new BaseMobileElement("Edit");
	private final BaseMobileElement doneEditPayment = new BaseMobileElement("Done");
	private final BaseMobileElement card_Number = new BaseMobileElement("card number");
	private final BaseMobileElement card_ExpireDate = new BaseMobileElement("expiration date");
	private final BaseMobileElement card_CVC = new BaseMobileElement("CVC");
	private final BaseMobileElement card_Cancel = new BaseMobileElement("Cancel");
	private final BaseMobileElement card_Done = new BaseMobileElement("Done");
	
	public String addCreditCardFromAccount(CreditCardType cardType)
	{
		try 
		{
			addNewCard.click();
			card_Number.setWebValue(cardType.getCardNumber());
			card_ExpireDate.setWebValue(cardType.getExpiration());
			card_CVC.setWebValue(cardType.getCVC());
			card_Done.click();
		}
		catch (NoSuchElementException e)
		{
			return "Error";
		}
	
		return "Success";
	}
	
	public boolean creditCardExists(CreditCardType cardType)
	{
		try 
		{
			BaseMobileElement editPayment = new BaseMobileElement(cardType.getDescription());
			return editPayment.exists();
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
	public String deleteCard(CreditCardType cardType)
	{
		try
		{
			editPaymentMethods.click();
			
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Edit link on Edit Payments Screen.";
		}
		
		try {
			//Format of the delete button is : Delete Visa Ending In 4242
			BaseMobileElement deletePayment = new BaseMobileElement("Delete " + cardType.getDescription());
			BaseMobileElement deleteConfirm = new BaseMobileElement("Delete");
			deletePayment.click(); // Click the delete button to the left of the credit card
			deleteConfirm.click(); // Then click delete again
			
		}
		catch (NoSuchElementException e)
		{
			return "Could not find delete icon for the specified payment method.";
		}
		
		try
		{
			doneEditPayment.click();
		}
		catch (NoSuchElementException e)
		{
			return "Could not find Done link on the Edit Payments Screen.";
		}
		return "Success";
	}

}
