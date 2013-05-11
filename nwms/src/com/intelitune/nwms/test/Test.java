package com.intelitune.nwms.test;

import java.util.List;

import com.axis2.services.MCSserviceStub;
import com.axis2.services.MCSserviceStub.InttClientDetailWS;

public class Test {

	public static void main(java.lang.String args[]) {
		try {

			MCSserviceStub stub = new MCSserviceStub("http://www.hmgtech.com:7080/MCS_NEW/services/MCSservice");
			
//			MCSserviceStub stub = new MCSserviceStub("http://localhost:8080/MCS_NEW/services/MCSservice");

//			getHello(stub);

//			System.err.println(add(stub, 1, 2));

//			canMCSserviceRunTest(stub);

			findClientById(stub, 1);
			
//			getClientListDDL(stub);
			
//			getAllClient(stub);

//			getClientList(stub);
			
//			getAllClientId(stub);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}

	public static void getHello(MCSserviceStub stub) {
		try {
			MCSserviceStub.GetHelloResponse res = stub.getHello();
			System.err.println(res.get_return());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}

	public static int add(MCSserviceStub stub, int a, int b) {
		try {
			MCSserviceStub.Add req = new MCSserviceStub.Add();
			req.setA(a);
			req.setB(b);
			MCSserviceStub.AddResponse res = stub.add(req);
			return res.get_return();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
			return -1;
		}
	}

	public static void canMCSserviceRunTest(MCSserviceStub stub) {
		try {
			MCSserviceStub.CanMCSserviceRunTestResponse res = stub.canMCSserviceRunTest();
			System.err.println(res.get_return());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}

	public static void findClientById(MCSserviceStub stub, int id) {
		try {
			MCSserviceStub.FindClientById req_FindClientById = new MCSserviceStub.FindClientById();
			req_FindClientById.setId(id);
			MCSserviceStub.FindClientByIdResponse res_FindClientById = stub.findClientById(req_FindClientById);
			MCSserviceStub.InttClientDetailWS icd = res_FindClientById.get_return();
			System.out.println("id:" + icd.getId());
			System.out.println("cnName:" + icd.getCnName());
			System.out.println("enName:" + icd.getEnName());
			System.out.println("companyCode:" + icd.getCompanyCode());
			System.out.println("enterpriseType:" + icd.getEnterpriseType());
			String[] address = icd.getAddress();
			if(address != null && address.length > 0) {
				for (int i = 0; i < address.length; i++) {
					System.err.println("Address" + i + ":" + address[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}
	
	public static void getClientListDDL(MCSserviceStub stub) {
		try {
			MCSserviceStub.FindAllClientListDDLResponse res = stub.findAllClientListDDL();
			String[] result = res.get_return();
			for(int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}
	
	public static void getAllClient(MCSserviceStub stub) {
		try {
			MCSserviceStub.GetAllClientResponse res = stub.getAllClient();
			MCSserviceStub.InttClientDetailWS[] result = res.get_return();
			for (int i = 0; i < result.length; i++) {
				System.out.println("id:" + result[i].getId());
				System.out.println("cnName:" + result[i].getCnName());
				System.out.println("enName:" + result[i].getEnName());
				System.out.println("companyCode:" + result[i].getCompanyCode());
				System.out.println("enterpriseType:" + result[i].getEnterpriseType());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}

	public static void getClientList(MCSserviceStub stub) {
		try {
			MCSserviceStub.FindAllClientListResponse res_FindAllClientList = stub.findAllClientList();
			List<MCSserviceStub.InttClientDetailWS> list = (List<MCSserviceStub.InttClientDetailWS>) res_FindAllClientList.get_return();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("id:" + list.get(i).getId());
				System.out.println("cnName:" + list.get(i).getCnName());
				System.out.println("enName:" + list.get(i).getEnName());
				System.out.println("companyCode:" + list.get(i).getCompanyCode());
				System.out.println("enterpriseType:" + list.get(i).getEnterpriseType());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}
	
	public static void getAllClientId(MCSserviceStub stub) {
		try {
			MCSserviceStub.GetAllClientIdResponse res = stub.getAllClientId();
			int[] result = res.get_return();
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\n\n\n");
		}
	}

}
