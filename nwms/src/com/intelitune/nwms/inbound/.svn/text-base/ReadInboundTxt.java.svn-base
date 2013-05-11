package com.intelitune.nwms.inbound;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ReadInboundTxt {
	public List<TxtData> getCollecton(String path) throws IOException{
		List<TxtData> list=new ArrayList<TxtData>();
		String content = null;
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		
		try {
			content = br.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
		String pieces [];
		while(content!=null){
			pieces = content.split(",");
			TxtData td=new TxtData();
		    if(pieces.length>=5){
			    if(pieces[0]!="N/A"&&pieces[0]!=null&&pieces[0]!=""){
			    	td.setJob_id(pieces[0]);
			    }
			    if(pieces[1]!="N/A"&&pieces[1]!=null&&pieces[1]!=""){
			    	td.setProduct_code(pieces[1]);
			    }
			    if(pieces[2]!="N/A"&&pieces[2]!=null&&pieces[2]!=""){
			    	td.setSn(pieces[2]);
			    }
			    if(pieces[3]!="N/A"&&pieces[3]!=null&&pieces[3]!=""){
			    	try{
			    		td.setQty(Float.parseFloat(pieces[3]));
			    	}catch(Exception e){
			    		e.printStackTrace();
			    	}			    
			    }else{
			    	td.setQty(1.0f);
			    }
			    if(pieces[4]!="N/A"&&pieces[4]!=null&&pieces[4]!=""){
			    	td.setBin_code(pieces[4]);
			    }
			   
			    int i;			    
			    for(i=0;i<list.size();i++){
			    	TxtData td1=list.get(i);
			    	if(td.getJob_id().equals(td1.getJob_id())&&"N/A".equals(td.getSn())&&"N/A".equals(td1.getSn())&&td.getBin_code().equals(td1.getBin_code())&&td.getProduct_code().equals(td1.getProduct_code())){
			    		td1.setQty(td.getQty()+td1.getQty());
			    		break;
			    	}
			    }
			    if(i==list.size()){
			    	list.add(td);
			    }
		    }
		    content = br.readLine();
		}
		return list;
	}
}
