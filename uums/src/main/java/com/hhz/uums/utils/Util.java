package com.hhz.uums.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.hhz.org.pd.service.WSPdService;
import com.hhz.uums.service.WSPlasService;
import com.hhz.uums.service.WebServiceClient;

@Service
public class Util {

	/**
	 * 应用根据初始化的uaapService客户端服务,获取代理.
	 * 
	 * @return
	 */
	public static WSPlasService getPlasService() {
		return WebServiceClient.getInstance().getPlasService();
	}
	
	public static WSPdService getPdService() {
		return WebServiceClient.getInstance().getPdService();
	}
 
  
	/**
	 * 将文本框内的换行符转为特殊符号,以免在脚本区引用时出现编译错误.
	 * 
	 * @param content
	 * @return
	 */
	public static String transTextAreaEnter(String content) {
		return content;
	}
 

	/**
	 * [邮箱]是否开通
	 * @param emailFlag
	 * @return
	 */
	public static boolean emailOpen(String emailFlag) {
		if (DictContants.PLAS_EMAIL_FLG_ENABLE.equals(emailFlag) || DictContants.PLAS_EMAIL_FLG_DISABLE.equals(emailFlag))
			return true;
		else
			return false;
	}

	/**
	 * [邮箱]是否生效
	 * @param emailFlag
	 * @return
	 */
	public static boolean emailEnable(String emailFlag) {
		if (DictContants.PLAS_EMAIL_FLG_ENABLE.equals(emailFlag))
			return true;
		return false;
	}

	/**
	 * [邮箱]是否失效
	 * @param emailFlag
	 * @return
	 */
	public static boolean emailDisable(String emailFlag) {
		if (DictContants.PLAS_EMAIL_FLG_DISABLE.equals(emailFlag))
			return true;
		return false;
	}

	/**
	 * [EAS]是否开通
	 * @param emailFlag
	 * @return
	 */
	public static boolean easOpen(String easFlag) {
		if (DictContants.PLAS_EAS_FLG_ENABLE.equals(easFlag) || DictContants.PLAS_EAS_FLG_DISABLE.equals(easFlag))
			return true;
		else
			return false;
	}

	/**
	 * [EAS]是否生效
	 * @param easFlag
	 * @return
	 */
	public static boolean easEnable(String easFlag) {
		if (DictContants.PLAS_EAS_FLG_ENABLE.equals(easFlag))
			return true;
		return false;
	}

	/**
	 * [EAS]是否失效
	 * @param easFlag
	 * @return
	 */
	public static boolean easDisable(String easFlag) {
		if (DictContants.PLAS_EAS_FLG_DISABLE.equals(easFlag))
			return true;
		return false;
	}

	/**
	 * [明源]是否开通
	 * @param mysoftFlg
	 * @return
	 */
	public static boolean mysoftOpen(String mysoftFlg) {
		if (DictContants.PLAS_MYSOFT_FLG_ENABLE.equals(mysoftFlg) || DictContants.PLAS_MYSOFT_FLG_DISABLE.equals(mysoftFlg))
			return true;
		else
			return false;
	}

	/**
	 * [明源]是否生效
	 * @param mysoftFlg
	 * @return
	 */
	public static boolean mysoftEnable(String mysoftFlg) {
		if (DictContants.PLAS_MYSOFT_FLG_ENABLE.equals(mysoftFlg))
			return true;
		return false;
	}

	/**
	 * [明源]是否失效
	 * @param mysoftFlg
	 * @return
	 */
	public static boolean mysoftDisable(String mysoftFlg) {
		if (DictContants.PLAS_MYSOFT_FLG_DISABLE.equals(mysoftFlg))
			return true;
		return false;
	}


	//[cmail]是否开通
	public static boolean cmailOpen(String cmailFlg) {
		if (DictContants.CMAIL_FLG_ENABLE.equals(cmailFlg)
				|| DictContants.CMAIL_FLG_DISABLE.equals(cmailFlg))
			return true;
		else
			return false;
	}
	
	//[cmail]是否生效
	public static boolean cmailEnable(String cmailFlg) {
		if (DictContants.CMAIL_FLG_ENABLE.equals(cmailFlg))
			return true;
		return false;
	}
	//[cmail]是否失效
	public static boolean cmailDisable(String cmailFlg) {
		if (DictContants.CMAIL_FLG_DISABLE.equals(cmailFlg))
			return true;
		return false;
	}

	public static String bSubstring(String s, int length) 
	{
		
		try{
			byte[] bytes = s.getBytes("Unicode");
			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始
			for (; i < bytes.length && n < length; i++)
			{
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1)
				{
					n++; // 在UCS2第二个字节时n加1
				}
				else
				{
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0)
					{
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1)
			{
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0) {
					i = i - 1;
				} else {
					i = i + 1;
				}
			}
			return new String(bytes, 0, i, "Unicode");
		}catch(Exception e){
			return "error";
		}
	} 
	public static String makeRandom(int length)
    {
        // author : herrapfel
        String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();

        for(int i=0;i<length;i++)
        {
            int randNum = rand.nextInt(36);
            generateRandStr.append(radStr.substring(randNum,randNum+1));
        }
        return generateRandStr+"";
    } 
	public static String transferListToS(int size,String param){
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i<size;i++){
			if(i==0) {
				sb.append(":").append(param);
			} else{
				sb.append(",:").append(param);
			}
		}
		return sb.toString();
	}
	/**
	 * 是否含有汉字
	 * return true  含有  ；false 不含
	 */
	public static boolean hasChinese(String str){
		 String regEx = "[\\u4e00-\\u9fa5]";
		 Pattern p = Pattern.compile(regEx);
		  Matcher m = p.matcher(str);
		  if(m.find())
			return true;
		else return false;
	}
}