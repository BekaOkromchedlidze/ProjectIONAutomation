package logistics;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project �on
 * 
 * This class represents a CNF file.
 * It consists of a name and a list of CNF line item objects
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logistics.Logistics;

public class CNFFile {
	
	private static String cnfName;
	private ArrayList<CNFLineItem> lineItems;
	
	public static String getCnfName(){
		return cnfName;
	}
	
	// constructor for the CNF file
	public CNFFile(ORDFile ordFile,ArrayList<EquipmentPack> packs,ArrayList<EquipmentPack> handsets){

		lineItems = new ArrayList<CNFLineItem>();
		
		// determine the name of the CNF file
		//DateFormat df1 = new SimpleDateFormat("yyMMdd");
		//DateFormat df2 = new SimpleDateFormat("HHmmss");
		
		cnfName = "ION_CNF_" + Logistics.getOrdName().substring(8, Logistics.getOrdName().length() - 4);
		
		//cnfName = "ION_CNF_" + df1.format(new Date())+ordFile.getORDName().replace("ION_ORD_", "");
		
		// for each line in the ord file, create a corresponding CNF line-item object
		for(int i = 0; i < ordFile.getLineCount(); i++){
			
			EquipmentPack pack=null;
			
			ORDLineItem ordItem = ordFile.getLineItems().get(i);
			System.out.println("Equipment Type = " + ordItem.getEquipmentType());
			if(ordItem.getEquipmentType().equals("SIM")){
				pack = packs.get(0);
				packs.remove(0);
			}
			
			
			/*
			 * For handset sales only - (i.e. future phases)
			 * 
			else if(ordItem.getEquipmentType().equals("SIM/Handset")){
				pack = packs.get(0);
				String imsi = packs.get(0).getImsi();
				pack.setImsi(imsi);
				packs.remove(0);
				handsets.remove(0);
			}
			else if(ordItem.getEquipmentType().equals("Handset")){
				pack = handsets.get(0);
				handsets.remove(0);
			}
			*/
			
			CNFLineItem cnfItem = new CNFLineItem(ordItem,pack);			
			
			// add the CNF line item to the list
			lineItems.add(cnfItem);
		}
	}
	
	// string method returns the contents of the CNF file
	public String toString(){
		
		// start the string with the CNF file header
		String cnfContents="<NUMBER_OF_RECORDS>" + lineItems.size() + "\n";
		
		// add each CNF line-item from the list
		for(CNFLineItem item:lineItems){
			cnfContents = cnfContents + item.toString().trim() + "\n";
		}
		
		// trim the last new-line character as it is not required
		if(cnfContents.endsWith("\n")){
			cnfContents =cnfContents.trim();
		}
		
		return cnfContents;
	}
	
	// write the CNF contents to a file
	public void writeCNFFile() throws IOException{
		BufferedWriter writer = new BufferedWriter(new 
				FileWriter(System.getProperty("user.dir") + "\\generated_Logistic_Files" + "\\"+ cnfName));
		writer.write(toString());
	    writer.close();
	}
}
