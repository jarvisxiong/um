/**
 * COSServiceCommonPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.intelitune.ccos.common;

public interface COSServiceCommonPortType extends java.rmi.Remote {
    public boolean alterStatusToDeleted1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException;
    public boolean alterStatusToConfirmed1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException;
    public boolean alterStatusToCompleted1(java.lang.String in0, java.lang.String in1, java.util.Calendar in2) throws java.rmi.RemoteException;
    public boolean alterStatusToDeleted(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean alterStatusToCompleted(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean alterStatusToConfirmed(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean appendToRemark(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
}
