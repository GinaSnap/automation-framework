package common;

public class CreditCardType {
	
	String cardNumber;
	String expiration;
	String cvc;
	String description;
	
	public CreditCardType(String cardNumber, String expiration, String cvc, String description)
	{
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.cvc = cvc;
		this.description = description;
	}
	
	public String getCardNumber()
	{
		return cardNumber;
	}
	
	public String getExpiration()
	{
		return expiration;
	}
	
	public String getCVC()
	{
		return cvc;
	}
	
	public String getDescription()
	{
		return description;
	}
	
}

