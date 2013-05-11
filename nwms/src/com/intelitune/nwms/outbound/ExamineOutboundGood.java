package com.intelitune.nwms.outbound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExamineOutboundGood {

	public List<OutboundGoodModel> getCollecton(String path) throws IOException{
		List<OutboundGoodModel> list=new ArrayList<OutboundGoodModel>();
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
		    OutboundGoodModel ogm=new OutboundGoodModel();
		    if(pieces.length>=5){
			    if(pieces[0]!="N/A"&&pieces[0]!=null&&pieces[0]!=""){
			    	ogm.setJob_id(pieces[0]);
			    }
			    if(pieces[1]!="N/A"&&pieces[1]!=null&&pieces[1]!=""){
			    	ogm.setProduct_code(pieces[1]);
			    }
			    if(pieces[2]!="N/A"&&pieces[2]!=null&&pieces[2]!=""){
			    	ogm.setSn(pieces[2]);
			    }
			    if(pieces[3]!="N/A"&&pieces[3]!=null&&pieces[3]!=""){
			    	try{
			    		ogm.setQty(Float.parseFloat(pieces[3]));
			    	}catch(Exception e){
			    		e.printStackTrace();
			    	}			    
			    }else{
			    	ogm.setQty(1.0f);
			    }
			    if(pieces[4]!="N/A"&&pieces[4]!=null&&pieces[4]!=""){
			    	ogm.setBin_code(pieces[4].toUpperCase());
			    }
			   
			    int i;			    
			    for(i=0;i<list.size();i++){
			    	OutboundGoodModel ogm1=list.get(i);
			    	if(ogm.getJob_id().equals(ogm1.getJob_id())&&"N/A".equals(ogm.getSn())&&"N/A".equals(ogm1.getSn())&&ogm.getBin_code().equals(ogm1.getBin_code())&&ogm.getProduct_code().equals(ogm1.getProduct_code())){
			    		ogm1.setQty(ogm.getQty()+ogm1.getQty());
			    		break;
			    	}
			    }
			    if(i==list.size()){
			    	list.add(ogm);
			    }
		    }
		    content = br.readLine();
		}
		return list;
	}
	
	
	public static void main(String[] arg) throws FileNotFoundException{
//		List<OutboundGoodModel> list=new ArrayList<OutboundGoodModel>();
//		ExamineOutboundGood eog=new ExamineOutboundGood();
//		try{
//			list=eog.getCollecton("c:"+File.separator+"text.txt");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		for(int i=0;i<list.size();i++){
//			OutboundGoodModel ogm=(OutboundGoodModel)list.get(i);
//			System.out.print(ogm.getJob_id()+"     ");
//			System.out.print(ogm.getBin_code()+"     ");
//			System.out.print(ogm.getProduct_code()+"     ");
//			System.out.print(ogm.getSn()+"     ");
//			System.out.println(ogm.getQty()+"     ");
//		}
		System.out.print("inbound123456".substring(8));
	}
}
