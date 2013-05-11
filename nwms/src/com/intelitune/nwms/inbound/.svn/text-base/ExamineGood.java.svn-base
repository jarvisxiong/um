package com.intelitune.nwms.inbound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author Louis
 *对上传的验货文件进行校验，生成相应的集合
 */
public class ExamineGood {
	
	public Map getCollecton(String path) throws IOException{
		String content = null;
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		try {
			content = br.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		String pieces [];
		
		String productCode;
		String sn;
		
		String productLCode;
		String lSn;
		while(!content.trim().equals("")){
			pieces = content.split(",",0);
			String jobId = pieces[0];
			List list;
			//map结构key是业务编号是这个jobId下的所有的item
			if(map.containsKey(jobId)){
				list = (List)map.get(jobId);
				productCode = pieces[1];
				sn = pieces[2];
				boolean label = false;
				for(int i=0;i<list.size();i++){
					//这种情况存在，合并
					ExamineGoodModel rgmm = (ExamineGoodModel)list.get(i);
					productLCode = rgmm.getProductCode();
					lSn = rgmm.getSn();
					if(productLCode.equals(productCode)){
						if(lSn.equals(sn)){
							rgmm.setArriveQty(rgmm.getArriveQty()+Float.parseFloat(pieces[3]));
							rgmm.setBadQty(rgmm.getBadQty()+ Float.parseFloat(pieces[4].trim()));
							label = false;
							break;
						}
					}
					label = true;
				}
				//不存在则新建
				if(label==true){
					ExamineGoodModel rgm = new ExamineGoodModel();
					rgm.setJobId(jobId);
					rgm.setProductCode(pieces[1]);
					rgm.setSn(pieces[2]);
					rgm.setArriveQty(Float.parseFloat(pieces[3]));
					rgm.setBadQty(Float.parseFloat(pieces[4].trim()));
					list.add(rgm);
				}
				//不存在这个jobId
			}else{
				list = new ArrayList();
				ExamineGoodModel rgm = new ExamineGoodModel();
				rgm.setJobId(jobId);
				rgm.setProductCode(pieces[1]);
				rgm.setSn(pieces[2]);
				rgm.setArriveQty(Float.parseFloat(pieces[3]));
				rgm.setBadQty(Float.parseFloat(pieces[4].trim()));
				list.add(rgm);
				map.put(jobId, list);
			}
			content = br.readLine();
		}
		fis.close();
		isr.close();
		br.close();
		return map;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		ExamineGood eg = new ExamineGood();
		Map map = new HashMap();
		try {
			map = eg.getCollecton("c:"+File.separator+"123.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set keySet = map.keySet();
		Iterator iter = keySet.iterator();
		while(iter.hasNext()){
			String key = (String)iter.next();
			List list = (List)map.get(key);
			for(int i=0;i<list.size();i++){
				ExamineGoodModel rgm = (ExamineGoodModel)list.get(i);
				System.out.print(rgm.getJobId()+",");
				System.out.print(rgm.getProductCode()+",");
				System.out.print(rgm.getSn()+",");
				System.out.print(rgm.getArriveQty()+",");
				System.out.println(rgm.getBadQty());
			}
		}
	}
}
