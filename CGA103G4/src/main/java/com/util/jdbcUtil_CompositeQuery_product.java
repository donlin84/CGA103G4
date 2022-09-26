/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *     �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */


package com.util;

import java.util.*;

public class jdbcUtil_CompositeQuery_product {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		//join優惠方案
		if ("pdid".equals(columnName) || "pdsid".equals(columnName) || "pdPrice".equals(columnName) || "pdStatus".equals(columnName)) // �Ω��L
			aCondition = columnName + "=" + value;
		else if ("pdName".equals(columnName)) // �Ω�varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("pdUpdate".equals(columnName))                          // �Ω�date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for �䥦DB  �� date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle �� date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action4".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

}
