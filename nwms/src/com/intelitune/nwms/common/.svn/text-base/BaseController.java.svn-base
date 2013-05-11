package com.intelitune.nwms.common;

import java.util.Date;
import javax.servlet.http.Cookie;
import net.sf.click.Page;

public class BaseController extends Page {
	/**
     * Get Current Time
     * @return Timestamp of current time
     */
    public static java.sql.Timestamp getCurrentTime() {
    	return new java.sql.Timestamp(System.currentTimeMillis());
    }


    /**
     * Get TimeStamp
     * @param date
     * @return Timestamp of given date
     */
    public static java.sql.Timestamp getTimeStamp(Date date) {
    	return new java.sql.Timestamp(date.getTime());
    }

    /**
     * Return the text of the request referer, if available, else null.
     * @param key - name of the cookie
     * @return java.lang.String
     */
    public Cookie getCookie(String key) {
    	Cookie ret = null;
    	Cookie cookArr[] = getContext().getRequest().getCookies();
    	if (cookArr != null) {
    		for (int i = 0; i < cookArr.length; i++) {
    			if (cookArr[i].getName().equals(key)) {
    				ret = cookArr[i];
    				break;
    			}
    		}
    	}
    	return ret;
    }

    /**
     * Check if user set the Cookies disable.
     * @return true for Cookies Available, false for Cookies disable.
     */
    public boolean isCookieAvailable() {
    	Cookie test = new Cookie("isCookieAvailable", "1");
    	getContext().getResponse().addCookie(test);
    	return getContext().getRequest().getCookies() != null;
    }

    /**
     * Add a Cookie
     * @param key 
     * @param value
     * @param maxAge
     */
    public void addCookie(String key, String value, int maxAge) {
    	Cookie cookie;
    	cookie = new Cookie(key,value);
    	cookie.setMaxAge(maxAge);
    	this.getContext().getResponse().addCookie(cookie);
    }
}