/**
 * 
 */
package com.hhz.ump.service;

import java.util.Collection;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;

/**
 * @author huangj
 * 2010-1-13
 */
public class PowerFilterInvocationImpl implements FilterInvocationDefinitionSource {

	/* (non-Javadoc)
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
	 */
	public ConfigAttributeDefinition getAttributes(Object arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
	 */
	public Collection getConfigAttributeDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
	 */
	public boolean supports(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
