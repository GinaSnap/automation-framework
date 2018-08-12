package common;

import java.util.Random;

/**
 * For the time being this class will contain utilities common to the entire framework.
 * We can separate by function as needed.
 * @author GMitchell
 *
 */
public class Utilities {
	
	public String getUniqueString(int size)
	{
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int i=1;i<=size;i++)
		{
			sb.append(random.nextInt(10));
		}
		
		return sb.toString();
	}
	
	


}
