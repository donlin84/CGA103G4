package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_ChefSubscription {
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("chefid".equals(columnName) || "memid".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;

		
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
		map.put("chefid", new String[] { "" });
		map.put("memid", new String[] { "" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from chefSubscription "
				          + jdbcUtil_CompositeQuery_ChefSubscription.get_WhereCondition(map)
				          + "order by chefid";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
