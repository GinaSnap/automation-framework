package test;

import java.io.IOException;

import org.junit.Test;

import common.SnapAPIUtilities;

public class TestProductImages extends BaseTestCase {
	
	@Test
	public void testProductJson()
	{
		SnapAPIUtilities snapAPI = new SnapAPIUtilities();
		try {
			snapAPI.getProductJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckKeyIngredientImages()
	{
		SnapAPIUtilities snapAPI = new SnapAPIUtilities();
		try {
			snapAPI.getProductDetailJson();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
