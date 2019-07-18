package test;

import org.junit.Test;

import common.ExcelUtilities;

public class TestAccessSplitSheet extends BaseTestCase {
	
	@Test
	public void testAccessSplitSheet()
	{
		ExcelUtilities excelUtil = new ExcelUtilities();
		excelUtil.getExcelSpreadsheet();
	}

}
