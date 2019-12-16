package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelDataProvider
{
	File src = null;
	XSSFWorkbook wb = null;
	FileInputStream fis = null;
	public ConfigDataProvider config = new ConfigDataProvider();
	
	public ExcelDataProvider()
	{
		src = new File(config.getDataFromConfig("dataPath"));

		try
		{
			fis = new FileInputStream(src);

			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Unable to read Excel File " + e.getMessage());
		}
	}

	public String getCellStringData(int sheetIndex, int row, int column)
	{
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}

	public String getCellStringData(String sheetName, int row, int column)
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}

	public double getCellNumericData(int sheetIndex, int row, int column)
	{
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getNumericCellValue();
	}

	public double getCellNumericData(String sheetName, int row, int column)
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

	public int getRowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum() + 1;
	}

	public int getRowCount(int sheetIndex)
	{
		return wb.getSheetAt(sheetIndex).getLastRowNum() + 1;
	}
	

	
	public  Object[][] getTableArray(String sheetName, int startRow, int lastRow, int startCol, int lastCol) throws Exception {   

		   String[][] tabArray = null;
		   int ci,cj;

		   tabArray=new String[lastRow - startRow + 1][lastCol - startCol + 1];
		   ci=0;

		   for (int i=startRow;i<=lastRow;i++, ci++) {           	   
			  cj=0;
			   for (int j=startCol;j<=lastCol;j++, cj++){

				   tabArray[ci][cj]=getCellValueAsString(sheetName,i,j);

					}
				}
			return(tabArray);
			}


	public  Object[][] getTableArray(int sheetIndex, int startRow, int lastRow, int startCol, int lastCol)  {   

		   String[][] tabArray = null;
		   int ci,cj;

		   tabArray=new String[lastRow - startRow + 1][lastCol - startCol + 1];
		   ci=0;

		   for (int i=startRow-1;i<=lastRow-1;i++, ci++) {           	   
			  cj=0;
			   for (int j=startCol-1;j<=lastCol-1;j++, cj++){
				   
				   tabArray[ci][cj]=getCellValueAsString(sheetIndex,i,j);
				   
					}

				}
			return(tabArray);
			}

		 /**
	     * This method for the type of data in the cell, extracts the data and
	     * returns it as a string.
	     */
	    public String getCellValueAsString(String sheetName, int row, int column) {
	    	XSSFCell cell = wb.getSheet(sheetName).getRow(row - 1).getCell(column - 1);
	    	
	        String strCellValue = null;
	        if (cell != null) {
	            switch (cell.getCellType()) {
	            case STRING:
	                strCellValue = cell.toString();
	                break;
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat(
	                            "dd/MM/yyyy");
	                    strCellValue = dateFormat.format(cell.getDateCellValue());
	                } else {
	                    Double value = cell.getNumericCellValue();
	                    Long longValue = value.longValue();
	                    strCellValue = new String(longValue.toString());
	                }
	                break;
	            case BOOLEAN:
	                strCellValue = new String(new Boolean(
	                        cell.getBooleanCellValue()).toString());
	                break;
	            case BLANK:
	                strCellValue = "";
	                break;
				case ERROR:
					strCellValue = "ERROR";
					break;
				case FORMULA:
					strCellValue = "FORMULA";
					break;
				case _NONE:
					strCellValue = "";
					break;
				default:
					break;
	            }
	        }
	        return strCellValue;
	    }
	    
	    /**
	     * This method for the type of data in the cell, extracts the data and
	     * returns it as a string.
	     */
	    public String getCellValueAsString(int sheetIndex, int row, int column) {
	    	XSSFCell cell = wb.getSheetAt(sheetIndex).getRow(row).getCell(column);
	    	
	        String strCellValue = null;
	        if (cell != null) {
	            switch (cell.getCellType()) {
	            case STRING:
	                strCellValue = cell.toString();
	                break;
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat(
	                            "dd/MM/yyyy");
	                    strCellValue = dateFormat.format(cell.getDateCellValue());
	                } else {
	                    Double value = cell.getNumericCellValue();
	                    Long longValue = value.longValue();
	                    strCellValue = new String(longValue.toString());
	                }
	                break;
	            case BOOLEAN:
	                strCellValue = new String(new Boolean(
	                        cell.getBooleanCellValue()).toString());
	                break;
	            case BLANK:
	                strCellValue = "";
	                break;
				case ERROR:
					strCellValue = "ERROR";
					break;
				case FORMULA:
					strCellValue = "FORMULA";
					break;
				case _NONE:
					strCellValue = "";
					break;
				default:
					break;
	            }
	        }
	        return strCellValue;
	    }

	    public void recordOrderDetails(String orderType, String email, String orderNumber, 
	    		String title, String firstName, String secondName, String contactNumber, String idType, String idNumber, String dateOfBirth, 
	    		String allowIonContact, String billingAddressEircode, String isDeliveryAddressSame, String deliveryAddressEircode) {	
	    	
	    	// This needs to be opened at the beginning of each recording as the excel file
	    	// initiated when the this class in each test class will not have the latest
	    	// entries made
	    	openExcelFile();
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sheet = null;
			
			if (orderType.equalsIgnoreCase("Singleline")) {
				sheet = wb.getSheet("completedSinglineOrders");
			} else {
				sheet = wb.getSheet("completedMultilineOrders");
			}

			int lastRow = sheet.getLastRowNum();
			
			// Styling border of cell.  
	        CellStyle style = wb.createCellStyle();  
	        style.setBorderBottom(BorderStyle.THIN);  
	        style.setBorderRight(BorderStyle.THIN);  
	        style.setBorderTop(BorderStyle.THIN);  
	        style.setBorderLeft(BorderStyle.THIN); 
	        
			// Styling border of cell.  
	        XSSFCellStyle style2 = wb.createCellStyle();  
	        style2.setBorderBottom(BorderStyle.THIN);  
	        style2.setBorderRight(BorderStyle.THIN);  
	        style2.setBorderTop(BorderStyle.THIN);  
	        style2.setBorderLeft(BorderStyle.THIN);
	        style2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
			// Styling border of cell.  
	        XSSFCellStyle style3 = wb.createCellStyle();  
	        style3.setBorderBottom(BorderStyle.THIN);  
	        style3.setBorderRight(BorderStyle.THIN);  
	        style3.setBorderTop(BorderStyle.THIN);  
	        style3.setBorderLeft(BorderStyle.THIN);
	        style3.setAlignment(HorizontalAlignment.CENTER);
	        style3.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
	        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			// Create First Row
			XSSFRow newRow = sheet.createRow(lastRow + 1);
			XSSFCell c1 = newRow.createCell(0);
			if (orderType.equalsIgnoreCase("Singleline")) {
				c1.setCellValue("Singleline");
			} else {
				c1.setCellValue("Multiline");
			}
			c1.setCellStyle(style2);
			XSSFCell c2 = newRow.createCell(1);
			c2.setCellValue(email);
			c2.setCellStyle(style);
			XSSFCell c3 = newRow.createCell(2);
//			c3.setCellValue(password);
			c3.setCellStyle(style);
			XSSFCell c4 = newRow.createCell(3);
			c4.setCellValue(orderNumber);
			c4.setCellStyle(style);
			XSSFCell c5 = newRow.createCell(4);
			c5.setCellValue(title);
			c5.setCellStyle(style);
			XSSFCell c6 = newRow.createCell(5);
			c6.setCellValue(firstName);
			c6.setCellStyle(style);
			XSSFCell c7 = newRow.createCell(6);
			c7.setCellValue(secondName);
			c7.setCellStyle(style);
			XSSFCell c8 = newRow.createCell(7);
			c8.setCellValue(contactNumber);
			c8.setCellStyle(style);
			XSSFCell c9 = newRow.createCell(8);
			c9.setCellValue(idType);
			c9.setCellStyle(style);
			XSSFCell c10 = newRow.createCell(9);
			c10.setCellValue(idNumber);
			c10.setCellStyle(style);
			XSSFCell c11 = newRow.createCell(10);
			c11.setCellValue(dateOfBirth);
			c11.setCellStyle(style);
			XSSFCell c12 = newRow.createCell(11);
			c12.setCellValue(allowIonContact);
			c12.setCellStyle(style);
			XSSFCell c13 = newRow.createCell(12);
			c13.setCellValue(billingAddressEircode);
			c13.setCellStyle(style);
			XSSFCell c14 = newRow.createCell(13);
			c14.setCellValue(isDeliveryAddressSame);
			c14.setCellStyle(style);
			XSSFCell c15 = newRow.createCell(14);
			c15.setCellValue(deliveryAddressEircode);
			c15.setCellStyle(style);
			XSSFCell c16 = newRow.createCell(15);
			c16.setCellValue("1");
			c16.setCellStyle(style3);
			XSSFCell c17 = newRow.createCell(16);
			c17.setCellValue(Helper.GetCurrentTimeStampMilliseconds());
			c17.setCellStyle(style);
			
			
			saveExcelFile();
	    }
	    
	    public void recordSetupComplete(String orderType, String password) {	
	    	// This needs to be opened at the beginning of each recording as the excel file
	    	// initiated when the this class in each test class will not have the latest
	    	// entries made
	    	openExcelFile();
	    	
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sheet = null;
			
			if (orderType.equalsIgnoreCase("Singleline")) {
				sheet = wb.getSheet("completedSinglineOrders");
			} else {
				sheet = wb.getSheet("completedMultilineOrders");
			}

			int lastRow = sheet.getLastRowNum();
			
			// Styling border of cell.  
	        XSSFCellStyle style = wb.createCellStyle();  
	        style.setBorderBottom(BorderStyle.THIN);  
	        style.setBorderRight(BorderStyle.THIN);  
	        style.setBorderTop(BorderStyle.THIN);  
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setAlignment(HorizontalAlignment.CENTER);
	        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			// Set Password
			XSSFCell c3 = sheet.getRow(lastRow).getCell(2);
			c3.setCellValue(password);

			// Update order stage
			XSSFCell c16 = sheet.getRow(lastRow).getCell(15);
			c16.setCellValue("2");
			c16.setCellStyle(style);
			
			saveExcelFile();
	    }
	    
	    public void recordProvisioningComplete(String orderType) {	
	    	// This needs to be opened at the beginning of each recording as the excel file
	    	// initiated when the this class in each test class will not have the latest
	    	// entries made
	    	openExcelFile();
	    	
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sheet = null;
			
			if (orderType.equalsIgnoreCase("Singleline")) {
				sheet = wb.getSheet("completedSinglineOrders");
			} else {
				sheet = wb.getSheet("completedMultilineOrders");
			}

			int lastRow = sheet.getLastRowNum();
			
			// Styling border of cell.  
	        XSSFCellStyle style = wb.createCellStyle();  
	        style.setBorderBottom(BorderStyle.THIN);  
	        style.setBorderRight(BorderStyle.THIN);  
	        style.setBorderTop(BorderStyle.THIN);  
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setAlignment(HorizontalAlignment.CENTER);
	        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			// Update order stage
			XSSFCell c16 = sheet.getRow(lastRow).getCell(15);
			c16.setCellValue("3");
			c16.setCellStyle(style);
			
			saveExcelFile();
	    }
	    
	    public void recordCrossSellOrder() {	
	    	// This needs to be opened at the beginning of each recording as the excel file
	    	// initiated when the this class in each test class will not have the latest
	    	// entries made
	    	openExcelFile();
	    	
			wb.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFSheet sheet = wb.getSheet("completedSinglineOrders");

			int lastRow = sheet.getLastRowNum();
			
			// Styling border of cell.  
	        XSSFCellStyle style = wb.createCellStyle();  
	        style.setBorderBottom(BorderStyle.THIN);  
	        style.setBorderRight(BorderStyle.THIN);  
	        style.setBorderTop(BorderStyle.THIN);  
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			// Update order stage
			XSSFCell c1 = sheet.getRow(lastRow).getCell(0);
			c1.setCellValue("CrossSell");
			c1.setCellStyle(style);
			
			saveExcelFile();
	    }
	    
	    
	    private void openExcelFile() {
	    	src = new File(config.getDataFromConfig("dataPath"));

			try
			{
				fis = new FileInputStream(src);

				wb = new XSSFWorkbook(fis);
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				System.out.println("Unable to read Excel File " + e.getMessage());
			}
	    	
		}
	    
	    private void saveExcelFile() {
	    	try {
				fis.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while (true) 
			{
				try 
				{
					FileOutputStream fos = new FileOutputStream(config.getDataFromConfig("dataPath"));
			        wb.write(fos);
			        fos.close();
			        System.out.println("Order details saved in " + config.getDataFromConfig("dataPath"));
			        break;
				} catch (FileNotFoundException e) {
					// TODO: handle exception
					
					JOptionPane.showMessageDialog(null, "Unable to edit Data.xlsx as it is open. Please close and click OK.",
							"Order Details Have Not Been Recorded.", 1);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage(),
							"Order Details Have Not Been Recorded.", 1);
					e.printStackTrace();
				}
				
			}
			
			src = new File(config.getDataFromConfig("dataPath"));

			try
			{
				fis = new FileInputStream(src);

				wb = new XSSFWorkbook(fis);
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				System.out.println("Unable to read Excel File " + e.getMessage());
			}
		}
		

}
