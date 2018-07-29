package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.MenuType;
import page.BaseMenu;
import page.MenuPopup;
import page.SnapHome;

public class TestCheckProductImages extends BaseTestCase {
	
	@Test
	public void testCheckBreakfast()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Breakfast Menu exists.", menuPopup.breakfast.exists());
		
		assertTrue("Test Step:  Click on Breakfast Menu.", menuPopup.goToMenu(MenuType.BREAKFAST));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Breakfast Menu.", "breakfast", menu.getCategoryName());
		
		menu.checkProductImages();
		
	}
	
	@Test
	public void testCheckLunch()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Lunch & Dinner Menu exists.", menuPopup.lunchAndDinner.exists());
		
		assertTrue("Test Step:  Click on Lunch and Dinner Menu.", menuPopup.goToMenu(MenuType.LUNCH_AND_DINNER));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Lunch and Dinner Menu.", "lunch & dinner", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckSalads()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Salad Menu exists.", menuPopup.salads.exists());
		
		assertTrue("Test Step:  Click on Salad Menu.", menuPopup.goToMenu(MenuType.SALADS));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Salads Menu.", "salads", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckSoups()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Soups Menu exists.", menuPopup.soups.exists());
		
		assertTrue("Test Step:  Click on Soups Menu.", menuPopup.goToMenu(MenuType.SOUPS));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Soups Menu.", "soups", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckSmallBites()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Small Bites Menu exists.", menuPopup.smallBites.exists());
		
		assertTrue("Test Step:  Click on Small Bites Menu.", menuPopup.goToMenu(MenuType.SMALL_BITES));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Small Bites Menu.", "small bites", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	//
	@Test
	public void testCheckSides()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Sides Menu exists.", menuPopup.sides.exists());
		
		assertTrue("Test Step:  Click on Sides Menu.", menuPopup.goToMenu(MenuType.SIDES));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Sides Menu.", "sides", menu.getCategoryName());
		
		menu.checkProductImages();
		
	}
	
	@Test
	public void testCheckSnacks()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Snacks Menu exists.", menuPopup.snacks.exists());
		
		assertTrue("Test Step:  Click on Snacks Menu.", menuPopup.goToMenu(MenuType.SNACKS));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Snacks Menu.", "snacks", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckJuices()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Juices & Blends Menu exists.", menuPopup.juices.exists());
		
		assertTrue("Test Step:  Click on Juices & Blends Menu.", menuPopup.goToMenu(MenuType.JUICES));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Juices & Blends Menu.", "juices & blends", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckDrinks()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Drinks Menu exists.", menuPopup.drinks.exists());
		
		assertTrue("Test Step:  Click on Drinks Menu.", menuPopup.goToMenu(MenuType.DRINKS));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Drinks Menu.", "drinks", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}
	
	@Test
	public void testCheckSweets()
	{
		login(defaultUser);
		
		SnapHome snapHome = new SnapHome();
		assertTrue("Test Step:  Click on Menu in the top navigation.", snapHome.goToTopNavMenu());
		
		//BREAKFAST
		MenuPopup menuPopup = new MenuPopup();
		assertTrue("Test Step:  Sweets Menu exists.", menuPopup.sweets.exists());
		
		assertTrue("Test Step:  Click on Sweets Menu.", menuPopup.goToMenu(MenuType.SWEETS));
		
		BaseMenu menu = new BaseMenu();
		assertEquals("Verify that we are on the Sweets Menu.", "sweets", menu.getCategoryName());
		
		assertEquals("Success",menu.checkProductImages());

	}


}
