package com.intelitune.nwms.inbound;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseDirectInbound {

	public List<DirectInboundModel> getCollecton(String path) throws IOException{
		String content = null;
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		try {
			content = br.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}

		List<DirectInboundModel> list = new ArrayList<DirectInboundModel>();
		String pieces [];
		
		while(!content.trim().equals("")){
			pieces = content.split(",",0);
			String productCode = pieces[0];
			String sn = pieces[1];
			float qty = Float.parseFloat(pieces[2]);
			String binCode = pieces[3];
			
			DirectInboundModel sm = new DirectInboundModel();
			sm.setProductCode(productCode);
			sm.setSn(sn);
			sm.setQty(qty);
			sm.setBinCode(binCode);
			
			list.add(sm);		
			content = br.readLine();
		}
		fis.close();
		isr.close();
		br.close();
		return list;
	}
}
