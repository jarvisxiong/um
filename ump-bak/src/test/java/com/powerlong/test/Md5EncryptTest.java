/**
 * 
 */
package com.powerlong.test;

import org.junit.Test;

import com.hhz.ump.util.Md5Encrypt;

/**
 * @author huangjian
 *
 */
public class Md5EncryptTest {
	@Test
	public void test(){
		String pwd=Md5Encrypt.md5("123");
		System.out.println(pwd);
	}
}
