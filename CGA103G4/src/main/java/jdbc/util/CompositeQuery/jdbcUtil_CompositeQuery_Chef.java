package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Chef {
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("chefid".equals(columnName) || "chefStatus".equals(columnName) || "chefPrice".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("chefAccount".equals(columnName) || "chefPassword".equals(columnName) || "chefName".equals(columnName)
				 || "chefNickname".equals(columnName) || "chefIntroduction".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
//		else if ("hiredate".equals(columnName))                          // 用於date
//			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
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
		map.put("chefid", new String[] { "201" });
		map.put("chefAccount", new String[] { "" });
		map.put("chefPassword", new String[] { "" });
		map.put("chefStatus", new String[] { "0" });
		map.put("chefName", new String[] { "" });
		map.put("chefNickname", new String[] { "" });
		map.put("chefPrice", new String[] { "" });
		map.put("chefIntroduction", new String[] { "" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from chef "
				          + jdbcUtil_CompositeQuery_Chef.get_WhereCondition(map)
				          + "order by chefid";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
