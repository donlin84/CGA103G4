package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_CreditCardInformation {
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("creditCardid".equals(columnName) || "memid".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("creditCardNumber".equals(columnName) || "creditCardName".equals(columnName) || "creditCardTime".equals(columnName)
				 || "cvcCode".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
//		else if ("memBirthday".equals(columnName))                          // 用於date
//			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
////		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("creditCardid", new String[] { "" });
		map.put("memid", new String[] { "" });
		map.put("creditCardNumber", new String[] { "" });
		map.put("creditCardName", new String[] { "" });
		map.put("creditCardTime", new String[] { "" });
		map.put("cvcCode", new String[] { "" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from creditcardinformation "
				          + jdbcUtil_CompositeQuery_CreditCardInformation.get_WhereCondition(map)
				          + "order by creditCardid";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
