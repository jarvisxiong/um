/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ResourceDetailsService.java 493 2009-09-19 04:49:28Z calvinxiu $
 */
package org.springside.modules.security.springsecurity;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * RequestMap生成接口,由用户自行实现从数据库或其它地方搜索URL-授权关系定义.
 * 
 * @author calvin
 */
public interface ResourceDetailsService {

	/**
	 * 返回带顺序的URL-授权关系Map, key为受保护的URL, value为能访问的URL列表.
	 */
	public LinkedHashMap<String, List<String>> getRequestMap() throws Exception;
}
