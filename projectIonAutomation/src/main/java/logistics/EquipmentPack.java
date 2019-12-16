package logistics;

public class EquipmentPack {
	
	private String msisdn;
	private String packID;
	private String iccid;
	private String imsi;
	
	public EquipmentPack(String msisdn, String packID, String iccid,String imsi){
		this.msisdn=msisdn;
		this.packID=packID;
		this.iccid=iccid;
		this.imsi=imsi;
	}
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMsisdn() {
		return msisdn;
	}
	public String getPackID() {
		return packID;
	}

	public String getIccid() {
		return iccid;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public void setPackID(String packID) {
		this.packID = packID;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
}
