/**
 * 
 */
package com.hhz.ump.aop;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhz.core.utils.PowerUtils;

/**
 * @author huangjian
 * 
 *         2011-5-27
 */
public class ClusterRequest {
	protected static Logger logger = LoggerFactory.getLogger(ClusterRequest.class);
	public static void setAllRequest() {
		try {
			HttpRequester request = new HttpRequester();
			InputStream is = PowerUtils.class.getClassLoader()
					.getResourceAsStream("clusterNode.properties");
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			String nodeUrl1 = (String) bundle.handleGetObject("node1");
			request.sendSimple(nodeUrl1);
			String nodeUrl2 = (String) bundle.handleGetObject("node2");
			request.sendSimple(nodeUrl2);
		} catch (IOException e) {
			// e.printStackTrace();
			logger.error("请求失败:", e);
		}
	}

	public static int getQuartzNode() {
		int port = -1;
		try {
			InputStream is = PowerUtils.class.getClassLoader()
					.getResourceAsStream("clusterNode.properties");
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			port = Integer.valueOf((String) bundle
					.handleGetObject("quartzNode"));
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return port;
	}
}
