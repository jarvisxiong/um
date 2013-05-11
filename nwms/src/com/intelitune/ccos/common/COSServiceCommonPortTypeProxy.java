package com.intelitune.ccos.common;

public class COSServiceCommonPortTypeProxy implements com.intelitune.ccos.common.COSServiceCommonPortType {
  private String _endpoint = null;
  private com.intelitune.ccos.common.COSServiceCommonPortType cOSServiceCommonPortType = null;
  
  public COSServiceCommonPortTypeProxy() {
    _initCOSServiceCommonPortTypeProxy();
  }
  
  public COSServiceCommonPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCOSServiceCommonPortTypeProxy();
  }
  
  private void _initCOSServiceCommonPortTypeProxy() {
    try {
      cOSServiceCommonPortType = (new com.intelitune.ccos.common.COSServiceCommonLocator()).getCOSServiceCommonHttpPort();
      if (cOSServiceCommonPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cOSServiceCommonPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cOSServiceCommonPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cOSServiceCommonPortType != null)
      ((javax.xml.rpc.Stub)cOSServiceCommonPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.intelitune.ccos.common.COSServiceCommonPortType getCOSServiceCommonPortType() {
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType;
  }
  
  public boolean alterStatusToDeleted1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToDeleted1(in0, in1, in2);
  }
  
  public boolean alterStatusToConfirmed1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToConfirmed1(in0, in1, in2);
  }
  
  public boolean alterStatusToCompleted1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToCompleted1(in0, in1, in2);
  }
  
  public boolean alterStatusToDeleted(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToDeleted(in0, in1);
  }
  
  public boolean alterStatusToCompleted(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToCompleted(in0, in1);
  }
  
  public boolean alterStatusToConfirmed(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.alterStatusToConfirmed(in0, in1);
  }
  
  public boolean appendToRemark(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (cOSServiceCommonPortType == null)
      _initCOSServiceCommonPortTypeProxy();
    return cOSServiceCommonPortType.appendToRemark(in0, in1);
  }
  
  
}