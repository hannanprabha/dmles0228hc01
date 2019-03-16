package gov.dha.jmlfdc.logicole.ivv;

public class IResourceConstants {

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
