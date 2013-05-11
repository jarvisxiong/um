/**
 * WMSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.intelitune.ccos.client;

import com.intelitune.nwms.service.PropertyService;

public class WMSServiceLocator extends org.apache.axis.client.Service implements com.intelitune.ccos.client.WMSService {
	public PropertyService ps=new PropertyService();
	
    public WMSServiceLocator() {
    }
	

    public WMSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WMSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WMSServiceHttpPort
    private java.lang.String WMSServiceHttpPort_address = ps.getPropertyValue("ccos_service");

    public java.lang.String getWMSServiceHttpPortAddress() {
        return WMSServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WMSServiceHttpPortWSDDServiceName = "WMSServiceHttpPort";

    public java.lang.String getWMSServiceHttpPortWSDDServiceName() {
        return WMSServiceHttpPortWSDDServiceName;
    }

    public void setWMSServiceHttpPortWSDDServiceName(java.lang.String name) {
        WMSServiceHttpPortWSDDServiceName = name;
    }

    public com.intelitune.ccos.client.WMSServicePortType getWMSServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WMSServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWMSServiceHttpPort(endpoint);
    }

    public com.intelitune.ccos.client.WMSServicePortType getWMSServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.intelitune.ccos.client.WMSServiceHttpBindingStub _stub = new com.intelitune.ccos.client.WMSServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getWMSServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWMSServiceHttpPortEndpointAddress(java.lang.String address) {
        WMSServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.intelitune.ccos.client.WMSServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.intelitune.ccos.client.WMSServiceHttpBindingStub _stub = new com.intelitune.ccos.client.WMSServiceHttpBindingStub(new java.net.URL(WMSServiceHttpPort_address), this);
                _stub.setPortName(getWMSServiceHttpPortWSDDServiceName());
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
        if ("WMSServiceHttpPort".equals(inputPortName)) {
            return getWMSServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://com/intelitune/ccos/client", "WMSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://com/intelitune/ccos/client", "WMSServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WMSServiceHttpPort".equals(portName)) {
            setWMSServiceHttpPortEndpointAddress(address);
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
