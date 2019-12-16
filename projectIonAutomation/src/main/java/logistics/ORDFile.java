package logistics;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project �on
 * 
 * This class represents an ORD file.
 * It consists of a name and a list of ORD line item objects
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ORDFile {

	private String ORDName;
	private ArrayList<ORDLineItem> lineItems;

	// Constructor - taking an ORD file directory as input
	@SuppressWarnings("deprecation")
	public ORDFile(String fileName) {

		// create the new list of line items
		lineItems = new ArrayList<ORDLineItem>();

		BufferedReader reader;

		try {
			// split the directory string by "\\"
			String[] dirParts = fileName.replace("\\", "/").split("/");
			
			// determine the ORD filename - i.e. the last portion of the location string
			ORDName = dirParts[dirParts.length - 1];
			
			// set up a new reader to read the ORD file
			reader = new BufferedReader(new FileReader(fileName));
			String text = null;

			// while there are lines in the ORD file
			while ((text = reader.readLine()) != null) {
				
				// create a new ORDLineItem object based on the line text
				ORDLineItem item = new ORDLineItem(text + " ");
				
				// add the ORD line item to the list
				lineItems.add(item);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ORD not found.", "Attention", 1);
			Thread.currentThread().stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// toString() method, returning the ORD file name and list of contents
	public String toString() {
		String ordFileString = ORDName + "\n";
		for (ORDLineItem item : lineItems) {
			ordFileString = ordFileString + item + "\n";
		}

		return ordFileString;
	}

	// return the count of ORD line items in a file
	public int getLineCount() {
		return lineItems.size();
	}
	
	/*
	 * Getter and Setter methods...
	 */
	public String getORDName() {
		return ORDName;
	}

	public ArrayList<ORDLineItem> getLineItems() {
		return lineItems;
	}

	public void setORDName(String oRDName) {
		ORDName = oRDName;
	}

	public void setLineItems(ArrayList<ORDLineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
