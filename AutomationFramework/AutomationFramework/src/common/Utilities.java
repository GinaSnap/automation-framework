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
	
	//select * from subscription order by created_at desc and grab the subscription_id
	//select * from subscription_week where subscription_id=<>
	
	//UPDATE SUBSCRIPTION WEEK
	//There will be multiple, run this query for both
	//UPDATE subscription_week set start_date='2018-02-22 13:00' where id=1575
	
	//UPDATE TIME SLOT INFORMATION
	//update subscription_fulfillment_time_slot set start_time='2018-02-15 22:00' where id=920


}
