/**
 * COSServiceCommonLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.intelitune.ccos.common;

import com.intelitune.nwms.service.PropertyService;

public class COSServiceCommonLocator extends org.apache.axis.client.Service implements com.intelitune.ccos.common.COSServiceCommon {
	
	public PropertyService ps=new PropertyService();

    public COSServiceCommonLocator() {
    }


    public COSServiceCommonLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public COSServiceCommonLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for COSServiceCommonHttpPort
//    private java.lang.String COSServiceCommonHttpPort_address = "http://192.168.0.56:6080/ccos/services/COSServiceCommon";
    private java.lang.String COSServiceCommonHttpPort_address = ps.getPropertyValue("ccoservice_common");

    public java.lang.String getCOSServiceCommonHttpPortAddress() {
        return COSServiceCommonHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String COSServiceCommonHttpPortWSDDServiceName = "COSServiceCommonHttpPort";

    public java.lang.String getCOSServiceCommonHttpPortWSDDServiceName() {
        return COSServiceCommonHttpPortWSDDServiceName;
    }

    public void setCOSServiceCommonHttpPortWSDDServiceName(java.lang.String name) {
        COSServiceCommonHttpPortWSDDServiceName = name;
    }

    public com.intelitune.ccos.common.COSServiceCommonPortType getCOSServiceCommonHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(COSServiceCommonHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCOSServiceCommonHttpPort(endpoint);
    }

    public com.intelitune.ccos.common.COSServiceCommonPortType getCOSServiceCommonHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.intelitune.ccos.common.COSServiceCommonHttpBindingStub _stub = new com.intelitune.ccos.common.COSServiceCommonHttpBindingStub(portAddress, this);
            _stub.setPortName(getCOSServiceCommonHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCOSServiceCommonHttpPortEndpointAddress(java.lang.String address) {
        COSServiceCommonHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.intelitune.ccos.common.COSServiceCommonPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.intelitune.ccos.common.COSServiceCommonHttpBindingStub _stub = new com.intelitune.ccos.common.COSServiceCommonHttpBindingStub(new java.net.URL(COSServiceCommonHttpPort_address), this);
                _stub.setPortName(getCOSServiceCommonHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("COSServiceCommonHttpPort".equals(inputPortName)) {
            return getCOSServiceCommonHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://com/intelitune/ccos/common", "COSServiceCommon");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://com/intelitune/ccos/common", "COSServiceCommonHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("COSServiceCommonHttpPort".equals(portName)) {
            setCOSServiceCommonHttpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
