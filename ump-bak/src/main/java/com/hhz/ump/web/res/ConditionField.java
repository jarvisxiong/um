/**
 * 
 */
package com.hhz.ump.web.res;

import com.hhz.ump.web.res.ResConditionParser.MatchType;

/**
 * @author huangj 2010-7-8
 */
public class ConditionField {
	private String fieldName;

	private MatchType matchType;

	private String value;

	public ConditionField(String fieldName, MatchType matchType, String value) {
		this.fieldName = fieldName;
		this.matchType = matchType;
		this.value = value;
	}
	public ConditionField(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public String getValue() {
		return value;
	}
}
