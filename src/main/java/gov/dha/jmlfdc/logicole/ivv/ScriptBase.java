package gov.dha.jmlfdc.logicole.ivv;


import org.apache.log4j.Logger;
import gov.dha.jmlfdc.logicole.ivv.pages.ABiSearchPage;
import gov.dha.jmlfdc.logicole.ivv.pages.EquipmentRecordPage;
import gov.dha.jmlfdc.logicole.ivv.pages.EquipmentRequestFuzzPage;
import gov.dha.jmlfdc.logicole.ivv.pages.EquipmentRequestPage;
import gov.dha.jmlfdc.logicole.ivv.pages.HomePage;
import gov.dha.jmlfdc.logicole.ivv.pages.UserManagementPage;

public class ScriptBase {

	private static Logger log = Logger.getLogger(ScriptBase.class.getName());
	
	public static HomePage homepage = new HomePage();
	public ABiSearchPage abisearchpage = new ABiSearchPage();
	public EquipmentRecordPage equipmentrecordpage = new EquipmentRecordPage();
	public EquipmentRequestPage equipmentrequestpage = new EquipmentRequestPage();
	public EquipmentRequestFuzzPage equipmenteqeustfuzzpage = new EquipmentRequestFuzzPage();
	public UserManagementPage usermanagement = new UserManagementPage();
	
	public String randomName(int count, String type){

		String ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String NUM = "0123456789";

		int intRndChar = 0;
		String strRandomName = "";
		try {
			String strChosenType = null;
			if (type.contentEquals("ALPHA_NUM")){
				strChosenType = ALPHA_NUM;
			}else if (type.contentEquals("ALPHA")){
				strChosenType = ALPHA;
			}else if (type.contentEquals("NUM")){
				strChosenType = NUM;
			}
			int intLength = strChosenType.length();
			while (count != 0){
				char [] charPosition = new char[intLength];
				intRndChar = (int)(Math.random()*intLength);
				charPosition[intRndChar] = strChosenType.charAt(intRndChar);
				StringBuffer sb = new StringBuffer();
				sb.append(charPosition[intRndChar]);
				strRandomName = strRandomName.concat(sb.toString());
				count--;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return strRandomName;
	}
	
}
