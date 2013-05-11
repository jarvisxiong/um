package com.intelitune.ccos.client;

public class WMSServicePortTypeProxy implements com.intelitune.ccos.client.WMSServicePortType {
  private String _endpoint = null;
  private com.intelitune.ccos.client.WMSServicePortType wMSServicePortType = null;
  
  public WMSServicePortTypeProxy() {
    _initWMSServicePortTypeProxy();
  }
  
  public WMSServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWMSServicePortTypeProxy();
  }
  
  private void _initWMSServicePortTypeProxy() {
    try {
      wMSServicePortType = (new com.intelitune.ccos.client.WMSServiceLocator()).getWMSServiceHttpPort();
      if (wMSServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wMSServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wMSServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wMSServicePortType != null)
      ((javax.xml.rpc.Stub)wMSServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.intelitune.ccos.client.WMSServicePortType getWMSServicePortType() {
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType;
  }
  
  public boolean closeSession(java.lang.Object in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.closeSession(in0);
  }
  
  public com.intelitune.ccos.model.WmsOrder[] findAll() throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.findAll();
  }
  
  public com.intelitune.ccos.model.WmsOrder findByJobId(java.lang.String in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.findByJobId(in0);
  }
  
  public boolean refWmsOrder(com.intelitune.ccos.model.WmsOrder in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.refWmsOrder(in0, in1);
  }
  
  public boolean save(java.lang.Object in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.save(in0);
  }
  
  public java.lang.Object[] query(java.lang.String in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.query(in0);
  }
  
  public boolean update(java.lang.String in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.update(in0);
  }
  
  public com.intelitune.ccos.model.WmsOrder findById(int in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.findById(in0);
  }
  
  public boolean deRefWmsOrder(com.intelitune.ccos.model.WmsOrder in0, java.lang.String in1) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.deRefWmsOrder(in0, in1);
  }
  
  public com.intelitune.ccos.model.WmsOrder[] queryWmsOrder(java.lang.String in0) throws java.rmi.RemoteException{
    if (wMSServicePortType == null)
      _initWMSServicePortTypeProxy();
    return wMSServicePortType.queryWmsOrder(in0);
  }
  
  
}