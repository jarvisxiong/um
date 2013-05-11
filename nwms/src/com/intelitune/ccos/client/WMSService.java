/**
 * WMSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.intelitune.ccos.client;

public interface WMSService extends javax.xml.rpc.Service {
    public java.lang.String getWMSServiceHttpPortAddress();

    public com.intelitune.ccos.client.WMSServicePortType getWMSServiceHttpPort() throws javax.xml.rpc.ServiceException;

    public com.intelitune.ccos.client.WMSServicePortType getWMSServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
