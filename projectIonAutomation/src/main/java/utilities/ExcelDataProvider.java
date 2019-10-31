package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider
{
	XSSFWorkbook wb;
	
	public ExcelDataProvider()
	{
		File src = new File("./TestData/Data.xlsx");

		try
		{
			FileInputStream fis = new FileInputStream(src);

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

			
		

}
