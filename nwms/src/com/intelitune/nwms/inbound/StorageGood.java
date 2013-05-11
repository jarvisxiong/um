package com.intelitune.nwms.inbound;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Louis
 *解析上传的上架文件，生成对应的集合
 */
public class StorageGood {

	public List<StorageGoodModel> getCollecton(String path) throws IOException{
		String content = null;
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		try {
			content = br.readLine();
		}catch (IOException e) {
			e.printStackTrace();
		}
//		Map map = new HashMap();
		List<StorageGoodModel> list = new ArrayList<StorageGoodModel>();
		String pieces [];
		
//		String productCode;
//		String sn;
//		String binCode;
		
//		String productLCode;
//		String lSn;
//		String lbinCode;
		while(!content.trim().equals("")){
			pieces = content.split(",",0);
			String jobId = pieces[0];
			String productCode = pieces[1];
			String sn = pieces[2];
			float qty = Float.parseFloat(pieces[3]);
			String binCode = pieces[4].toUpperCase();
			boolean label = false;
			if(list.size()!=0){
				for(StorageGoodModel stor:list){
					label = false;
					if(stor.getJobId().equals(jobId)){
						if(stor.getProductCode().equals(productCode)){
							if(stor.getSn().equals(sn)){
								if(stor.getBinCode().equals(binCode)){
									label = true;
									stor.setQty(stor.getQty()+qty);
									break;
								}
							}
						}
					}
				}
				if(label==false){
					StorageGoodModel sgm = new StorageGoodModel();
					sgm.setJobId(jobId);
					sgm.setProductCode(productCode);
					sgm.setSn(sn);
					sgm.setBinCode(binCode);
					sgm.setQty(qty);
					list.add(sgm);
				}
			}else{
				StorageGoodModel sgm = new StorageGoodModel();
				sgm.setJobId(jobId);
				sgm.setProductCode(productCode);
				sgm.setSn(sn);
				sgm.setBinCode(binCode);
				sgm.setQty(qty);
				list.add(sgm);
			}
			
			content = br.readLine();
		}
		fis.close();
		isr.close();
		br.close();
		return list;
//			pieces = content.split(",",0);
//			String jobId = pieces[0];
//			List list;
//			//map结构key是业务编号是这个jobId下的所有的item
//			if(map.containsKey(jobId)){
//				list = (List)map.get(jobId);
//				productCode = pieces[1];
//				sn = pieces[2];
//				binCode = pieces[3];
//				boolean label = false;
//				for(int i=0;i<list.size();i++){
//					//这种情况存在，合并
//					StorageGoodModel sgm = (StorageGoodModel)list.get(i);
//					productLCode = sgm.getProductCode();
//					lSn = sgm.getSn();
//					lbinCode = sgm.getBinCode();
//					if(productLCode.equals(productCode)){
//						if(lSn.equals(sn)){
//							if(lbinCode.trim().equals(binCode.trim())){
//								sgm.setQty(sgm.getQty()+Float.parseFloat(pieces[3]));
//								sgm.setBinCode(pieces[4].trim());
//								label = false;
//								break;
//							}
//						}
//					}
//					label = true;
//				}
//				//不存在则新建
//				if(label==true){
//					StorageGoodModel sgm = new StorageGoodModel();
//					sgm.setJobId(jobId);
//					sgm.setProductCode(pieces[1]);
//					sgm.setSn(pieces[2]);
//					sgm.setQty(Float.parseFloat(pieces[3]));
//					sgm.setBinCode(pieces[4].trim());
//					list.add(sgm);
//				}
//				//不存在这个jobId
//			}else{
//				list = new ArrayList();
//				StorageGoodModel sgm = new StorageGoodModel();
//				sgm.setJobId(jobId);
//				sgm.setProductCode(pieces[1]);
//				sgm.setSn(pieces[2]);
//				sgm.setQty(Float.parseFloat(pieces[3]));
//				sgm.setBinCode(pieces[4].trim());
//				list.add(sgm);
//				map.put(jobId, list);
//			}
//			content = br.readLine();
//		}
//		return map;
	}
	
//	public static void main(String[] args){
//		StorageGood sg = new StorageGood();
//		Map map = null;
//		try {
//			map = sg.getCollecton("c:"+File.separator+"123.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Set keySet = map.keySet();
//		Iterator iter = keySet.iterator();
//		while(iter.hasNext()){
//			String key = (String)iter.next();
//			List list = (List)map.get(key);
//			for(int i=0;i<list.size();i++){
//				StorageGoodModel sgm = (StorageGoodModel)list.get(i);
//				System.out.print(sgm.getJobId()+",");
//				System.out.print(sgm.getProductCode()+",");
//				System.out.print(sgm.getSn()+",");
//				System.out.print(sgm.getQty()+",");
//				System.out.println(sgm.getBinCode());
//			}
//		}
//	}
}
