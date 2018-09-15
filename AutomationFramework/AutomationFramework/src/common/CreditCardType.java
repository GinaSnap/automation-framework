package common;

public class CreditCardType {
	
	String cardNumber;
	String expiration;
	String cvc;
	
	public CreditCardType(String cardNumber, String expiration, String cvc)
	{
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.cvc = cvc;
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
	
}

