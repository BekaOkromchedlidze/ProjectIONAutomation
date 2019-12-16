package logistics;

/*
 * Author: Stephen Hall
 * Created 17/08/2018 for Project �on
 * 
 * This class represents a line item in an ORD file.
 * Its variables mirror the list of fields present in an ORD line item.
 */
public class ORDLineItem {

	private String caseID;
	private String orderNumber;
	private String accountNumber;
	private String entity;
	private String customerName;
	private String deliveryAddress1;
	private String deliveryAddress2;
	private String deliveryAddress3;
	private String deliveryAddress4;
	private String deliveryAddress5;
	private String deliveryAddress6;
	private String deliveryAddress7;
	private String deliveryNotes;
	private String contactDeliveryPerson1;
	private String contactPhone1;
	private String contactEmail1;
	private String contactDeliveryPerson2;
	private String contactPhone2;
	private String contactEmail2;
	private String orderDate;
	private String itemNumber;
	private String equipmentType;
	private String spcCode;
	private String equipmentMake;
	private String equipmentModel;
	private String productId;
	private String portInNumber;
	private String condition;

	// Constructor - takes an ORD line item text string as input
	public ORDLineItem(String ordText) {
		//ordText = ordText.replaceAll("\"", "");

		// split the text string by comma
		String[] textFields = ordText.split("\",\"");

		
		for(int i = 0; i < textFields.length;i++) {
			System.out.println(i + ": " + textFields[i]);
		}
		
		// assign the values and build the ORDLineItem object
		caseID = textFields[0].replaceAll("\"", "");
		orderNumber = textFields[1].replaceAll("\"", "");
		accountNumber = textFields[2].replaceAll("\"", "");
		entity = textFields[3].replaceAll("\"", "");
		customerName = textFields[4].replaceAll("\"", "");
		deliveryAddress1 = textFields[5].replaceAll("\"", "");
		deliveryAddress2 = textFields[6].replaceAll("\"", "");
		deliveryAddress3 = textFields[7].replaceAll("\"", "");
		deliveryAddress4 = textFields[8].replaceAll("\"", "");
		deliveryAddress5 = textFields[9].replaceAll("\"", "");
		deliveryAddress6 = textFields[10].replaceAll("\"", "");
		deliveryAddress7 = textFields[11].replaceAll("\"", "");
		deliveryNotes = textFields[12].replaceAll("\"", "");
		contactDeliveryPerson1 = textFields[13].replaceAll("\"", "");
		contactPhone1 = textFields[14].replaceAll("\"", "");
		contactEmail1 = textFields[15].replaceAll("\"", "");
		contactDeliveryPerson2 = textFields[16].replaceAll("\"", "");
		contactPhone2 = textFields[17].replaceAll("\"", "");
		contactEmail2 = textFields[18].replaceAll("\"", "");
		orderDate = textFields[19].replaceAll("\"", "");
		itemNumber = textFields[20].replaceAll("\"", "");
		equipmentType = textFields[21].replaceAll("\"", "");

		// if the SPC is NOT included in the field, set SPC to blank
		if (textFields.length == 27) {
			spcCode="";
			equipmentMake = textFields[22].replaceAll("\"", "");
			equipmentModel = textFields[23].replaceAll("\"", "");
			productId = textFields[24].replaceAll("\"", "");
			portInNumber = textFields[25].replaceAll("\"", "");
			condition = textFields[26].replaceAll("\"", "");
		}
		// else if the SPC is included in the field - note that this will be added by MediationZone
		else if (textFields.length == 28) {
			spcCode = textFields[22].replaceAll("\"", "");
			equipmentMake = textFields[23].replaceAll("\"", "");
			equipmentModel = textFields[24].replaceAll("\"", "");
			productId = textFields[25].replaceAll("\"", "");
			portInNumber = textFields[26].replaceAll("\"", "");
			condition = textFields[27].replaceAll("\"", "");
		}
		else{
			System.err.println("Error: Malformed ORD Line Item:" + textFields.length + " fields found!");
		}
	}
	
	// toString() method
	public String toString() {
		String s = "";
		s = s + caseID + ",";
		s = s + orderNumber + ",";
		s = s + accountNumber + ",";
		s = s + entity + ",";
		s = s + customerName + ",";
		s = s + deliveryAddress1 + ",";
		s = s + deliveryAddress2 + ",";
		s = s + deliveryAddress3 + ",";
		s = s + deliveryAddress4 + ",";
		s = s + deliveryAddress5 + ",";
		s = s + deliveryAddress6 + ",";
		s = s + deliveryAddress7 + ",";
		s = s + deliveryNotes + ",";
		s = s + contactDeliveryPerson1 + ",";
		s = s + contactPhone1 + ",";
		s = s + contactEmail1 + ",";
		s = s + contactDeliveryPerson2 + ",";
		s = s + contactPhone2 + ",";
		s = s + contactEmail2 + ",";
		s = s + orderDate + ",";
		s = s + itemNumber + ",";
		s = s + equipmentType + ",";
		s = s + spcCode + ",";
		s = s + equipmentMake + ",";
		s = s + equipmentModel + ",";
		s = s + productId + ",";
		s = s + portInNumber + ",";
		s = s + condition;

		return s;
	}

	/*
	 * Getter and Setter methods
	 */
	public String getCaseId() {
		return caseID;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getEntity() {
		return entity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getDeliveryAddress1() {
		return deliveryAddress1;
	}

	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	public String getDeliveryAddress3() {
		return deliveryAddress3;
	}

	public String getDeliveryAddress4() {
		return deliveryAddress4;
	}

	public String getDeliveryAddress5() {
		return deliveryAddress5;
	}

	public String getDeliveryAddress6() {
		return deliveryAddress6;
	}

	public String getDeliveryAddress7() {
		return deliveryAddress7;
	}

	public String getDeliveryNotes() {
		return deliveryNotes;
	}

	public String getContactDeliveryPerson1() {
		return contactDeliveryPerson1;
	}

	public String getContactPhone1() {
		return contactPhone1;
	}

	public String getContactEmail1() {
		return contactEmail1;
	}

	public String getContactDeliveryPerson2() {
		return contactDeliveryPerson2;
	}

	public String getContactPhone2() {
		return contactPhone2;
	}

	public String getContactEmail2() {
		return contactEmail2;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public String getSpcCode() {
		return spcCode;
	}

	public String getEquipmentMake() {
		return equipmentMake;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public String getProductId() {
		return productId;
	}

	public String getPortInNumber() {
		return portInNumber;
	}

	public String getCondition() {
		return condition;
	}

	public void setBucketId(String bucketId) {
		this.caseID = bucketId;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setDeliveryAddress1(String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	public void setDeliveryAddress3(String deliveryAddress3) {
		this.deliveryAddress3 = deliveryAddress3;
	}

	public void setDeliveryAddress4(String deliveryAddress4) {
		this.deliveryAddress4 = deliveryAddress4;
	}

	public void setDeliveryAddress5(String deliveryAddress5) {
		this.deliveryAddress5 = deliveryAddress5;
	}

	public void setDeliveryAddress6(String deliveryAddress6) {
		this.deliveryAddress6 = deliveryAddress6;
	}

	public void setDeliveryAddress7(String deliveryAddress7) {
		this.deliveryAddress7 = deliveryAddress7;
	}

	public void setDeliveryNotes(String deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}

	public void setContactDeliveryPerson1(String contactDeliveryPerson1) {
		this.contactDeliveryPerson1 = contactDeliveryPerson1;
	}

	public void setContactPhone1(String contactPhone1) {
		this.contactPhone1 = contactPhone1;
	}

	public void setContactEmail1(String contactEmail1) {
		this.contactEmail1 = contactEmail1;
	}

	public void setContactDeliveryPerson2(String contactDeliveryPerson2) {
		this.contactDeliveryPerson2 = contactDeliveryPerson2;
	}

	public void setContactPhone2(String contactPhone2) {
		this.contactPhone2 = contactPhone2;
	}

	public void setContactEmail2(String contactEmail2) {
		this.contactEmail2 = contactEmail2;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public void setSpcCode(String spcCode) {
		this.spcCode = spcCode;
	}

	public void setEquipmentMake(String equipmentMake) {
		this.equipmentMake = equipmentMake;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setPortInNumber(String portInNumber) {
		this.portInNumber = portInNumber;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	public static void main(String [] args){
		ORDLineItem item = new ORDLineItem("112,USCZLXUW,22,ION,James Doe,Delivery,secret line 2,secret line 3,A town,CO_GALWAY,,,,James Doe,02345123456,beka1432@test.com,,,,11012019,3,SIM,Oberthur,3 in 1,0SIMONLINA,, ");
		System.out.println(item);
	}
}
