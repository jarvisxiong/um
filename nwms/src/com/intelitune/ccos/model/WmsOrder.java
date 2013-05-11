/**
 * WmsOrder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.intelitune.ccos.model;

public class WmsOrder  implements java.io.Serializable {
    private java.util.Date achievementDate;

    private java.lang.String alreadyInbound;

    private java.lang.String alreadyOutbound;

    private java.util.Date anticipateDate;

    private java.lang.String bkt;

    private com.intelitune.ccos.model.BasicOrder[] childrenOrders;

    private java.lang.String clientCode;

    private java.lang.Integer clientId;

    private java.lang.String clientName;

    private java.util.Date closeTime;

    private java.lang.Integer contactId;

    private java.lang.String contactMan;

    private java.lang.String contactPhone;

    private java.util.Date creationTime;

    private com.intelitune.ccos.model.InttUser creator;

    private java.lang.String crn;

    private java.lang.String hawb;

    private java.lang.Integer id;

    private com.intelitune.ccos.model.InttJobFieldCode inttJobFieldCode;

    private com.intelitune.ccos.model.InttJobMode inttJobMode;

    private java.lang.Integer isEmergency;

    private java.lang.String itemIdList;

    private java.lang.String jobId;

    private java.lang.Integer locked;

    private java.lang.String mawb;

    private com.intelitune.ccos.model.OrderStatus orderStatus;

    private java.lang.String orderType;

    private java.lang.String rn;

    private java.lang.String warehouseAlias;

    private java.lang.String warehouseId;

    private com.intelitune.ccos.model.WmsOrderStatus wmsOrderStatus;

    private java.lang.String wmsOrderType;
    
    private java.lang.String remark;
    
    

    public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public WmsOrder() {
    }

    public WmsOrder(
           java.util.Date achievementDate,
           java.lang.String alreadyInbound,
           java.lang.String alreadyOutbound,
           java.util.Date anticipateDate,
           java.lang.String bkt,
           com.intelitune.ccos.model.BasicOrder[] childrenOrders,
           java.lang.String clientCode,
           java.lang.Integer clientId,
           java.lang.String clientName,
           java.util.Date closeTime,
           java.lang.Integer contactId,
           java.lang.String contactMan,
           java.lang.String contactPhone,
           java.util.Date creationTime,
           com.intelitune.ccos.model.InttUser creator,
           java.lang.String crn,
           java.lang.String hawb,
           java.lang.Integer id,
           com.intelitune.ccos.model.InttJobFieldCode inttJobFieldCode,
           com.intelitune.ccos.model.InttJobMode inttJobMode,
           java.lang.Integer isEmergency,
           java.lang.String itemIdList,
           java.lang.String jobId,
           java.lang.Integer locked,
           java.lang.String mawb,
           com.intelitune.ccos.model.OrderStatus orderStatus,
           java.lang.String orderType,
           java.lang.String rn,
           java.lang.String warehouseAlias,
           java.lang.String warehouseId,
           com.intelitune.ccos.model.WmsOrderStatus wmsOrderStatus,
           java.lang.String wmsOrderType) {
           this.achievementDate = achievementDate;
           this.alreadyInbound = alreadyInbound;
           this.alreadyOutbound = alreadyOutbound;
           this.anticipateDate = anticipateDate;
           this.bkt = bkt;
           this.childrenOrders = childrenOrders;
           this.clientCode = clientCode;
           this.clientId = clientId;
           this.clientName = clientName;
           this.closeTime = closeTime;
           this.contactId = contactId;
           this.contactMan = contactMan;
           this.contactPhone = contactPhone;
           this.creationTime = creationTime;
           this.creator = creator;
           this.crn = crn;
           this.hawb = hawb;
           this.id = id;
           this.inttJobFieldCode = inttJobFieldCode;
           this.inttJobMode = inttJobMode;
           this.isEmergency = isEmergency;
           this.itemIdList = itemIdList;
           this.jobId = jobId;
           this.locked = locked;
           this.mawb = mawb;
           this.orderStatus = orderStatus;
           this.orderType = orderType;
           this.rn = rn;
           this.warehouseAlias = warehouseAlias;
           this.warehouseId = warehouseId;
           this.wmsOrderStatus = wmsOrderStatus;
           this.wmsOrderType = wmsOrderType;
    }


    /**
     * Gets the achievementDate value for this WmsOrder.
     * 
     * @return achievementDate
     */
    public java.util.Date getAchievementDate() {
        return achievementDate;
    }


    /**
     * Sets the achievementDate value for this WmsOrder.
     * 
     * @param achievementDate
     */
    public void setAchievementDate(java.util.Date achievementDate) {
        this.achievementDate = achievementDate;
    }


    /**
     * Gets the alreadyInbound value for this WmsOrder.
     * 
     * @return alreadyInbound
     */
    public java.lang.String getAlreadyInbound() {
        return alreadyInbound;
    }


    /**
     * Sets the alreadyInbound value for this WmsOrder.
     * 
     * @param alreadyInbound
     */
    public void setAlreadyInbound(java.lang.String alreadyInbound) {
        this.alreadyInbound = alreadyInbound;
    }


    /**
     * Gets the alreadyOutbound value for this WmsOrder.
     * 
     * @return alreadyOutbound
     */
    public java.lang.String getAlreadyOutbound() {
        return alreadyOutbound;
    }


    /**
     * Sets the alreadyOutbound value for this WmsOrder.
     * 
     * @param alreadyOutbound
     */
    public void setAlreadyOutbound(java.lang.String alreadyOutbound) {
        this.alreadyOutbound = alreadyOutbound;
    }


    /**
     * Gets the anticipateDate value for this WmsOrder.
     * 
     * @return anticipateDate
     */
    public java.util.Date getAnticipateDate() {
        return anticipateDate;
    }


    /**
     * Sets the anticipateDate value for this WmsOrder.
     * 
     * @param anticipateDate
     */
    public void setAnticipateDate(java.util.Date anticipateDate) {
        this.anticipateDate = anticipateDate;
    }


    /**
     * Gets the bkt value for this WmsOrder.
     * 
     * @return bkt
     */
    public java.lang.String getBkt() {
        return bkt;
    }


    /**
     * Sets the bkt value for this WmsOrder.
     * 
     * @param bkt
     */
    public void setBkt(java.lang.String bkt) {
        this.bkt = bkt;
    }


    /**
     * Gets the childrenOrders value for this WmsOrder.
     * 
     * @return childrenOrders
     */
    public com.intelitune.ccos.model.BasicOrder[] getChildrenOrders() {
        return childrenOrders;
    }


    /**
     * Sets the childrenOrders value for this WmsOrder.
     * 
     * @param childrenOrders
     */
    public void setChildrenOrders(com.intelitune.ccos.model.BasicOrder[] childrenOrders) {
        this.childrenOrders = childrenOrders;
    }


    /**
     * Gets the clientCode value for this WmsOrder.
     * 
     * @return clientCode
     */
    public java.lang.String getClientCode() {
        return clientCode;
    }


    /**
     * Sets the clientCode value for this WmsOrder.
     * 
     * @param clientCode
     */
    public void setClientCode(java.lang.String clientCode) {
        this.clientCode = clientCode;
    }


    /**
     * Gets the clientId value for this WmsOrder.
     * 
     * @return clientId
     */
    public java.lang.Integer getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this WmsOrder.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.Integer clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the clientName value for this WmsOrder.
     * 
     * @return clientName
     */
    public java.lang.String getClientName() {
        return clientName;
    }


    /**
     * Sets the clientName value for this WmsOrder.
     * 
     * @param clientName
     */
    public void setClientName(java.lang.String clientName) {
        this.clientName = clientName;
    }


    /**
     * Gets the closeTime value for this WmsOrder.
     * 
     * @return closeTime
     */
    public java.util.Date getCloseTime() {
        return closeTime;
    }


    /**
     * Sets the closeTime value for this WmsOrder.
     * 
     * @param closeTime
     */
    public void setCloseTime(java.util.Date closeTime) {
        this.closeTime = closeTime;
    }


    /**
     * Gets the contactId value for this WmsOrder.
     * 
     * @return contactId
     */
    public java.lang.Integer getContactId() {
        return contactId;
    }


    /**
     * Sets the contactId value for this WmsOrder.
     * 
     * @param contactId
     */
    public void setContactId(java.lang.Integer contactId) {
        this.contactId = contactId;
    }


    /**
     * Gets the contactMan value for this WmsOrder.
     * 
     * @return contactMan
     */
    public java.lang.String getContactMan() {
        return contactMan;
    }


    /**
     * Sets the contactMan value for this WmsOrder.
     * 
     * @param contactMan
     */
    public void setContactMan(java.lang.String contactMan) {
        this.contactMan = contactMan;
    }


    /**
     * Gets the contactPhone value for this WmsOrder.
     * 
     * @return contactPhone
     */
    public java.lang.String getContactPhone() {
        return contactPhone;
    }


    /**
     * Sets the contactPhone value for this WmsOrder.
     * 
     * @param contactPhone
     */
    public void setContactPhone(java.lang.String contactPhone) {
        this.contactPhone = contactPhone;
    }


    /**
     * Gets the creationTime value for this WmsOrder.
     * 
     * @return creationTime
     */
    public java.util.Date getCreationTime() {
        return creationTime;
    }


    /**
     * Sets the creationTime value for this WmsOrder.
     * 
     * @param creationTime
     */
    public void setCreationTime(java.util.Date creationTime) {
        this.creationTime = creationTime;
    }


    /**
     * Gets the creator value for this WmsOrder.
     * 
     * @return creator
     */
    public com.intelitune.ccos.model.InttUser getCreator() {
        return creator;
    }


    /**
     * Sets the creator value for this WmsOrder.
     * 
     * @param creator
     */
    public void setCreator(com.intelitune.ccos.model.InttUser creator) {
        this.creator = creator;
    }


    /**
     * Gets the crn value for this WmsOrder.
     * 
     * @return crn
     */
    public java.lang.String getCrn() {
        return crn;
    }


    /**
     * Sets the crn value for this WmsOrder.
     * 
     * @param crn
     */
    public void setCrn(java.lang.String crn) {
        this.crn = crn;
    }


    /**
     * Gets the hawb value for this WmsOrder.
     * 
     * @return hawb
     */
    public java.lang.String getHawb() {
        return hawb;
    }


    /**
     * Sets the hawb value for this WmsOrder.
     * 
     * @param hawb
     */
    public void setHawb(java.lang.String hawb) {
        this.hawb = hawb;
    }


    /**
     * Gets the id value for this WmsOrder.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this WmsOrder.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the inttJobFieldCode value for this WmsOrder.
     * 
     * @return inttJobFieldCode
     */
    public com.intelitune.ccos.model.InttJobFieldCode getInttJobFieldCode() {
        return inttJobFieldCode;
    }


    /**
     * Sets the inttJobFieldCode value for this WmsOrder.
     * 
     * @param inttJobFieldCode
     */
    public void setInttJobFieldCode(com.intelitune.ccos.model.InttJobFieldCode inttJobFieldCode) {
        this.inttJobFieldCode = inttJobFieldCode;
    }


    /**
     * Gets the inttJobMode value for this WmsOrder.
     * 
     * @return inttJobMode
     */
    public com.intelitune.ccos.model.InttJobMode getInttJobMode() {
        return inttJobMode;
    }


    /**
     * Sets the inttJobMode value for this WmsOrder.
     * 
     * @param inttJobMode
     */
    public void setInttJobMode(com.intelitune.ccos.model.InttJobMode inttJobMode) {
        this.inttJobMode = inttJobMode;
    }


    /**
     * Gets the isEmergency value for this WmsOrder.
     * 
     * @return isEmergency
     */
    public java.lang.Integer getIsEmergency() {
        return isEmergency;
    }


    /**
     * Sets the isEmergency value for this WmsOrder.
     * 
     * @param isEmergency
     */
    public void setIsEmergency(java.lang.Integer isEmergency) {
        this.isEmergency = isEmergency;
    }


    /**
     * Gets the itemIdList value for this WmsOrder.
     * 
     * @return itemIdList
     */
    public java.lang.String getItemIdList() {
        return itemIdList;
    }


    /**
     * Sets the itemIdList value for this WmsOrder.
     * 
     * @param itemIdList
     */
    public void setItemIdList(java.lang.String itemIdList) {
        this.itemIdList = itemIdList;
    }


    /**
     * Gets the jobId value for this WmsOrder.
     * 
     * @return jobId
     */
    public java.lang.String getJobId() {
        return jobId;
    }


    /**
     * Sets the jobId value for this WmsOrder.
     * 
     * @param jobId
     */
    public void setJobId(java.lang.String jobId) {
        this.jobId = jobId;
    }


    /**
     * Gets the locked value for this WmsOrder.
     * 
     * @return locked
     */
    public java.lang.Integer getLocked() {
        return locked;
    }


    /**
     * Sets the locked value for this WmsOrder.
     * 
     * @param locked
     */
    public void setLocked(java.lang.Integer locked) {
        this.locked = locked;
    }


    /**
     * Gets the mawb value for this WmsOrder.
     * 
     * @return mawb
     */
    public java.lang.String getMawb() {
        return mawb;
    }


    /**
     * Sets the mawb value for this WmsOrder.
     * 
     * @param mawb
     */
    public void setMawb(java.lang.String mawb) {
        this.mawb = mawb;
    }


    /**
     * Gets the orderStatus value for this WmsOrder.
     * 
     * @return orderStatus
     */
    public com.intelitune.ccos.model.OrderStatus getOrderStatus() {
        return orderStatus;
    }


    /**
     * Sets the orderStatus value for this WmsOrder.
     * 
     * @param orderStatus
     */
    public void setOrderStatus(com.intelitune.ccos.model.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    /**
     * Gets the orderType value for this WmsOrder.
     * 
     * @return orderType
     */
    public java.lang.String getOrderType() {
        return orderType;
    }


    /**
     * Sets the orderType value for this WmsOrder.
     * 
     * @param orderType
     */
    public void setOrderType(java.lang.String orderType) {
        this.orderType = orderType;
    }


    /**
     * Gets the rn value for this WmsOrder.
     * 
     * @return rn
     */
    public java.lang.String getRn() {
        return rn;
    }


    /**
     * Sets the rn value for this WmsOrder.
     * 
     * @param rn
     */
    public void setRn(java.lang.String rn) {
        this.rn = rn;
    }


    /**
     * Gets the warehouseAlias value for this WmsOrder.
     * 
     * @return warehouseAlias
     */
    public java.lang.String getWarehouseAlias() {
        return warehouseAlias;
    }


    /**
     * Sets the warehouseAlias value for this WmsOrder.
     * 
     * @param warehouseAlias
     */
    public void setWarehouseAlias(java.lang.String warehouseAlias) {
        this.warehouseAlias = warehouseAlias;
    }


    /**
     * Gets the warehouseId value for this WmsOrder.
     * 
     * @return warehouseId
     */
    public java.lang.String getWarehouseId() {
        return warehouseId;
    }


    /**
     * Sets the warehouseId value for this WmsOrder.
     * 
     * @param warehouseId
     */
    public void setWarehouseId(java.lang.String warehouseId) {
        this.warehouseId = warehouseId;
    }


    /**
     * Gets the wmsOrderStatus value for this WmsOrder.
     * 
     * @return wmsOrderStatus
     */
    public com.intelitune.ccos.model.WmsOrderStatus getWmsOrderStatus() {
        return wmsOrderStatus;
    }


    /**
     * Sets the wmsOrderStatus value for this WmsOrder.
     * 
     * @param wmsOrderStatus
     */
    public void setWmsOrderStatus(com.intelitune.ccos.model.WmsOrderStatus wmsOrderStatus) {
        this.wmsOrderStatus = wmsOrderStatus;
    }


    /**
     * Gets the wmsOrderType value for this WmsOrder.
     * 
     * @return wmsOrderType
     */
    public java.lang.String getWmsOrderType() {
        return wmsOrderType;
    }


    /**
     * Sets the wmsOrderType value for this WmsOrder.
     * 
     * @param wmsOrderType
     */
    public void setWmsOrderType(java.lang.String wmsOrderType) {
        this.wmsOrderType = wmsOrderType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WmsOrder)) return false;
        WmsOrder other = (WmsOrder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.achievementDate==null && other.getAchievementDate()==null) || 
             (this.achievementDate!=null &&
              this.achievementDate.equals(other.getAchievementDate()))) &&
            ((this.alreadyInbound==null && other.getAlreadyInbound()==null) || 
             (this.alreadyInbound!=null &&
              this.alreadyInbound.equals(other.getAlreadyInbound()))) &&
            ((this.alreadyOutbound==null && other.getAlreadyOutbound()==null) || 
             (this.alreadyOutbound!=null &&
              this.alreadyOutbound.equals(other.getAlreadyOutbound()))) &&
            ((this.anticipateDate==null && other.getAnticipateDate()==null) || 
             (this.anticipateDate!=null &&
              this.anticipateDate.equals(other.getAnticipateDate()))) &&
            ((this.bkt==null && other.getBkt()==null) || 
             (this.bkt!=null &&
              this.bkt.equals(other.getBkt()))) &&
            ((this.childrenOrders==null && other.getChildrenOrders()==null) || 
             (this.childrenOrders!=null &&
              java.util.Arrays.equals(this.childrenOrders, other.getChildrenOrders()))) &&
            ((this.clientCode==null && other.getClientCode()==null) || 
             (this.clientCode!=null &&
              this.clientCode.equals(other.getClientCode()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.clientName==null && other.getClientName()==null) || 
             (this.clientName!=null &&
              this.clientName.equals(other.getClientName()))) &&
            ((this.closeTime==null && other.getCloseTime()==null) || 
             (this.closeTime!=null &&
              this.closeTime.equals(other.getCloseTime()))) &&
            ((this.contactId==null && other.getContactId()==null) || 
             (this.contactId!=null &&
              this.contactId.equals(other.getContactId()))) &&
            ((this.contactMan==null && other.getContactMan()==null) || 
             (this.contactMan!=null &&
              this.contactMan.equals(other.getContactMan()))) &&
            ((this.contactPhone==null && other.getContactPhone()==null) || 
             (this.contactPhone!=null &&
              this.contactPhone.equals(other.getContactPhone()))) &&
            ((this.creationTime==null && other.getCreationTime()==null) || 
             (this.creationTime!=null &&
              this.creationTime.equals(other.getCreationTime()))) &&
            ((this.creator==null && other.getCreator()==null) || 
             (this.creator!=null &&
              this.creator.equals(other.getCreator()))) &&
            ((this.crn==null && other.getCrn()==null) || 
             (this.crn!=null &&
              this.crn.equals(other.getCrn()))) &&
            ((this.hawb==null && other.getHawb()==null) || 
             (this.hawb!=null &&
              this.hawb.equals(other.getHawb()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.inttJobFieldCode==null && other.getInttJobFieldCode()==null) || 
             (this.inttJobFieldCode!=null &&
              this.inttJobFieldCode.equals(other.getInttJobFieldCode()))) &&
            ((this.inttJobMode==null && other.getInttJobMode()==null) || 
             (this.inttJobMode!=null &&
              this.inttJobMode.equals(other.getInttJobMode()))) &&
            ((this.isEmergency==null && other.getIsEmergency()==null) || 
             (this.isEmergency!=null &&
              this.isEmergency.equals(other.getIsEmergency()))) &&
            ((this.itemIdList==null && other.getItemIdList()==null) || 
             (this.itemIdList!=null &&
              this.itemIdList.equals(other.getItemIdList()))) &&
            ((this.jobId==null && other.getJobId()==null) || 
             (this.jobId!=null &&
              this.jobId.equals(other.getJobId()))) &&
            ((this.locked==null && other.getLocked()==null) || 
             (this.locked!=null &&
              this.locked.equals(other.getLocked()))) &&
            ((this.mawb==null && other.getMawb()==null) || 
             (this.mawb!=null &&
              this.mawb.equals(other.getMawb()))) &&
            ((this.orderStatus==null && other.getOrderStatus()==null) || 
             (this.orderStatus!=null &&
              this.orderStatus.equals(other.getOrderStatus()))) &&
            ((this.orderType==null && other.getOrderType()==null) || 
             (this.orderType!=null &&
              this.orderType.equals(other.getOrderType()))) &&
            ((this.rn==null && other.getRn()==null) || 
             (this.rn!=null &&
              this.rn.equals(other.getRn()))) &&
            ((this.warehouseAlias==null && other.getWarehouseAlias()==null) || 
             (this.warehouseAlias!=null &&
              this.warehouseAlias.equals(other.getWarehouseAlias()))) &&
            ((this.warehouseId==null && other.getWarehouseId()==null) || 
             (this.warehouseId!=null &&
              this.warehouseId.equals(other.getWarehouseId()))) &&
            ((this.wmsOrderStatus==null && other.getWmsOrderStatus()==null) || 
             (this.wmsOrderStatus!=null &&
              this.wmsOrderStatus.equals(other.getWmsOrderStatus()))) &&
            ((this.wmsOrderType==null && other.getWmsOrderType()==null) || 
             (this.wmsOrderType!=null &&
              this.wmsOrderType.equals(other.getWmsOrderType())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAchievementDate() != null) {
            _hashCode += getAchievementDate().hashCode();
        }
        if (getAlreadyInbound() != null) {
            _hashCode += getAlreadyInbound().hashCode();
        }
        if (getAlreadyOutbound() != null) {
            _hashCode += getAlreadyOutbound().hashCode();
        }
        if (getAnticipateDate() != null) {
            _hashCode += getAnticipateDate().hashCode();
        }
        if (getBkt() != null) {
            _hashCode += getBkt().hashCode();
        }
        if (getChildrenOrders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChildrenOrders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChildrenOrders(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getClientCode() != null) {
            _hashCode += getClientCode().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getClientName() != null) {
            _hashCode += getClientName().hashCode();
        }
        if (getCloseTime() != null) {
            _hashCode += getCloseTime().hashCode();
        }
        if (getContactId() != null) {
            _hashCode += getContactId().hashCode();
        }
        if (getContactMan() != null) {
            _hashCode += getContactMan().hashCode();
        }
        if (getContactPhone() != null) {
            _hashCode += getContactPhone().hashCode();
        }
        if (getCreationTime() != null) {
            _hashCode += getCreationTime().hashCode();
        }
        if (getCreator() != null) {
            _hashCode += getCreator().hashCode();
        }
        if (getCrn() != null) {
            _hashCode += getCrn().hashCode();
        }
        if (getHawb() != null) {
            _hashCode += getHawb().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getInttJobFieldCode() != null) {
            _hashCode += getInttJobFieldCode().hashCode();
        }
        if (getInttJobMode() != null) {
            _hashCode += getInttJobMode().hashCode();
        }
        if (getIsEmergency() != null) {
            _hashCode += getIsEmergency().hashCode();
        }
        if (getItemIdList() != null) {
            _hashCode += getItemIdList().hashCode();
        }
        if (getJobId() != null) {
            _hashCode += getJobId().hashCode();
        }
        if (getLocked() != null) {
            _hashCode += getLocked().hashCode();
        }
        if (getMawb() != null) {
            _hashCode += getMawb().hashCode();
        }
        if (getOrderStatus() != null) {
            _hashCode += getOrderStatus().hashCode();
        }
        if (getOrderType() != null) {
            _hashCode += getOrderType().hashCode();
        }
        if (getRn() != null) {
            _hashCode += getRn().hashCode();
        }
        if (getWarehouseAlias() != null) {
            _hashCode += getWarehouseAlias().hashCode();
        }
        if (getWarehouseId() != null) {
            _hashCode += getWarehouseId().hashCode();
        }
        if (getWmsOrderStatus() != null) {
            _hashCode += getWmsOrderStatus().hashCode();
        }
        if (getWmsOrderType() != null) {
            _hashCode += getWmsOrderType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WmsOrder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "WmsOrder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("achievementDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "achievementDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alreadyInbound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "alreadyInbound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alreadyOutbound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "alreadyOutbound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anticipateDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "anticipateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bkt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "bkt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childrenOrders");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "childrenOrders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "BasicOrder"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "BasicOrder"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "clientCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "clientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "clientName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("closeTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "closeTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "contactId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactMan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "contactMan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "contactPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "creationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "creator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "InttUser"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "crn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hawb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "hawb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inttJobFieldCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "inttJobFieldCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "InttJobFieldCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inttJobMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "inttJobMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "InttJobMode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isEmergency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "isEmergency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemIdList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "itemIdList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "jobId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "locked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mawb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "mawb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "orderStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "OrderStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "orderType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "rn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warehouseAlias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "warehouseAlias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("warehouseId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "warehouseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wmsOrderStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "wmsOrderStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "WmsOrderStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wmsOrderType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.ccos.intelitune.com", "wmsOrderType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
