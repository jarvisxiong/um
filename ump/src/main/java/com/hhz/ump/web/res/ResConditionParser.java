/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.List;

/**
 * 网批公式解析类
 * 
 * @author huangj 2010-7-8
 */
public class ResConditionParser {

	public enum MatchType {
		EQ, NEQ, LIKE, NLIKE, LT, GT, LE, GE, IN;
	}

	public static final String AND = "_AND_";
	private static final String or = "_or_";// 高优先级
	private static final String OR = "_OR_";// 低优先级
	// 预算内
	public static final String IN = "in";
	// 预算外
	public static final String OUT = "out";
	public static final String TRUE = "true";
	// 员工工资
	public static final String SALARY = "salary";
	// 预算
	public static final String[] IN_OUT = { IN, OUT, SALARY };
	public static final String[] IN_OUT_ONLY = { IN, OUT };

	/**
	 * 将指定字符串转化为条件类
	 * 
	 * @param conditionValue
	 * @return
	 */
	public static List<List<List<ConditionField>>> parse(String conditionValue) {
		List<List<List<ConditionField>>> listOr = new ArrayList<List<List<ConditionField>>>();
		if (conditionValue != null && !conditionValue.equals("无")) {
			String[] conditionsOr = conditionValue.split(OR);
			for (String conTmp : conditionsOr) {
				List<List<ConditionField>> lstAnd = new ArrayList<List<ConditionField>>();
				String[] conditionsAnd = conTmp.split(AND);
				for (String conAnd : conditionsAnd) {
					List<ConditionField> lstItem = new ArrayList<ConditionField>();
					String[] conditions_or = conAnd.split(or);
					for (String conItem : conditions_or) {
						String[] condition = conItem.split("_");
						if (condition.length == 3) {
							MatchType matchType = Enum.valueOf(MatchType.class, condition[1]);
							lstItem.add(new ConditionField(condition[0], matchType, condition[2]));
						} else if (condition.length == 1) {
							lstItem.add(new ConditionField(condition[0], MatchType.EQ, TRUE));
						}
					}
					lstAnd.add(lstItem);
				}
				listOr.add(lstAnd);
			}
		}
		return listOr;
	}
}
