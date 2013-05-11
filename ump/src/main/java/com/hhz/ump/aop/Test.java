package com.hhz.ump.aop;


public class Test {
	public static void main(String[] args) {
		try {
			HttpRequester request = new HttpRequester();
			HttpRespons respons=request.sendGet("http://mail1.powerlong.com/coremail/demo/message/listMessages.jsp?fid=1&sid=BAtSMaPPypZYTlwmRJPPTZXPrbvaGOJW&page_no=2&itemPerPage=10");
			System.out.println(respons.getContent());
//			request.sendSimple("http://localhost:8080/PowerDesk/sortMacAddr");

//			System.out.println(hr.getUrlString());
//			System.out.println(hr.getProtocol());
//			System.out.println(hr.getHost());
//			System.out.println(hr.getPort());
//			System.out.println(hr.getContentEncoding());
//			System.out.println(hr.getMethod());
//
//			System.out.println(hr.getContent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
