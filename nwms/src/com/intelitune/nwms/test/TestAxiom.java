package com.intelitune.nwms.test;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class TestAxiom {

	private static EndpointReference targetEPR = new EndpointReference("http://192.168.0.56:9080/MCS_NEW/services/MCSservice");

	public static OMElement getHelloWorld() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://model.mcs.intelitune.com/xsd", "tns");
		OMElement value = fac.createOMElement("getHello", omNs);
		return value;
	}

	public static OMElement getClientById(int id) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://model.mcs.intelitune.com/xsd", "tns");
		OMElement method = fac.createOMElement("findClientById", omNs);
		OMElement value1 = fac.createOMElement("id", omNs);
		value1.addChild(fac.createOMText(value1, String.valueOf(id)));
		method.addChild(value1);
		return method;
	}

	/*
	 * public static OMElement getAdd(String a, String b) { OMFactory fac =
	 * OMAbstractFactory.getOMFactory(); OMNamespace omNs =
	 * fac.createOMNamespace("http://model.mcs.intelitune.com/xsd", "tns");
	 * OMElement method = fac.createOMElement("add", omNs); OMElement value1 =
	 * fac.createOMElement("a", omNs); value1.addChild(fac.createOMText(value1,
	 * a)); OMElement value2 = fac.createOMElement("b", omNs);
	 * value2.addChild(fac.createOMText(value2, b)); return value; }
	 */

	public static void main(String[] args) {
		try {
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			// OMElement result = sender.sendReceive(getHelloWorld());

			OMElement result = sender.sendReceive(getClientById(1));

			String response = result.getFirstElement().getText();
			System.err.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
