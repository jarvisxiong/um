package com.intelitune.nwms.model;

import java.sql.Timestamp;
import java.util.Set;
/**
 * @hibernate.class
 * table = "intt_item"
 * @author chase
 *
 */
public class Item implements java.io.Serializable{

	
	public String sn;
	
	public Invoice invoice;
	
	public String referenceBin;
	
	public ItemType itemType;
	
	public Float f_unit_price;
	public Float f_unit_weight;
	public Float f_gross_price;
	public Float f_gross_weight;
	public String f_currency;
	
	
	
	
	/*
 * (non-javadoc)
 */
public ItemState state;
 
/**
 * Getter of the property <tt>state</tt>
 *@hibernate.many-to-one
 * @return Returns the state.
 * 
 */

public ItemState getState()
{
	return state;
}

/**
 * Setter of the property <tt>state</tt>
 *
 * @param state The state to set.
 *
 */
public void setState(ItemState state ){
	this.state = state;
}

/*
 * (non-javadoc)
 */
public String remark;
 
/**
 * Getter of the property <tt>remark</tt>
 *@hibernate.property
 * @return Returns the remark.
 * 
 */

public String getRemark()
{
	return remark;
}

/**
 * Setter of the property <tt>remark</tt>
 *
 * @param remark The remark to set.
 *
 */
public void setRemark(String remark ){
	this.remark = remark;
}

/*
 * (non-javadoc)
 */
public Timestamp LastModifiedTime;
 
/**
 * Getter of the property <tt>lastModifyTime</tt>
 *@hibernate.property
 * @return Returns the lastModifyTime.
 * 
 */

public Timestamp getLastModifiedTime()
{
	return LastModifiedTime;
}

/**
 * Setter of the property <tt>lastModifyTime</tt>
 *
 * @param lastModifyTime The lastModifyTime to set.
 *
 */
public void setLastModifiedTime(Timestamp lastModifyTime ){
	this.LastModifiedTime = lastModifyTime;
}

/*
 * (non-javadoc)
 */
public Timestamp creationTime;
 
/**
 * Getter of the property <tt>createTime</tt>
 *@hibernate.property
 * @return Returns the createTime.
 * 
 */

public Timestamp getCreationTime()
{
	return creationTime;
}

/**
 * Setter of the property <tt>createTime</tt>
 *
 * @param createTime The createTime to set.
 *
 */
public void setCreationTime(Timestamp createTime ){
	this.creationTime = createTime;
}

/*
 * (non-javadoc)
 */
public Set<Item> nextItems;
 
/**
 * Getter of the property <tt>nextItems</tt>
 *@hibernate.set lazy = "true"
 *table = "intt_nextItems"
 *@hibernate.collection-key
 *   column = "mainItemId"
 *   @hibernate.collection-many-to-many
 *   column = "nextItemId"
 *   class = "com.intelitune.nwms.model.Item"
 * @return Returns the nextItems.
 * 
 */

public Set<Item> getNextItems()
{
	return nextItems;
}

/**
 * Setter of the property <tt>nextItems</tt>
 *
 * @param nextItems The nextItems to set.
 *
 */
public void setNextItems(Set<Item> nextItems ){
	this.nextItems = nextItems;
}

/*
 * (non-javadoc)
 */
public Set<Item> lastItems;
 
/**
 * Getter of the property <tt>sourceItems</tt>
 *@hibernate.set lazy = "true"
 *table = "intt_lastItems"
 *@hibernate.collection-key
 *   column = "mainItemId"
 *@hibernate.collection-many-to-many
 *   column = "lastItemId"
 *   class = "com.intelitune.nwms.model.Item"
 * @return Returns the sourceItems.
 * 
 */

public Set<Item> getLastItems()
{
	return lastItems;
}

/**
 * Setter of the property <tt>sourceItems</tt>
 *
 * @param sourceItems The sourceItems to set.
 *
 */
public void setLastItems(Set<Item> sourceItems ){
	this.lastItems = sourceItems;
}

/*
 * (non-javadoc)
 */
public Float qty;
 
/**
 * Getter of the property <tt>qty</tt>
 *@hibernate.property
 * @return Returns the qty.
 * 
 */

public Float getQty()
{
	return qty;
}

/**
 * Setter of the property <tt>qty</tt>
 *
 * @param qty The qty to set.
 *
 */
public void setQty(Float qty ){
	this.qty = qty;
}

/*
 * (non-javadoc)
 */
public UnitPackage unitPackage;
 
/**
 * Getter of the property <tt>unitPackage</tt>
 *@hibernate.many-to-one
 * @return Returns the unitPackage.
 * 
 */

public UnitPackage getUnitPackage()
{
	return unitPackage;
}

/**
 * Setter of the property <tt>unitPackage</tt>
 *
 * @param unitPackage The unitPackage to set.
 *
 */
public void setUnitPackage(UnitPackage unitPackage ){
	this.unitPackage = unitPackage;
}

/*
 * (non-javadoc)
 */
public Material material;
 
/**
 * Getter of the property <tt>material</tt>
 *@hibernate.many-to-one
 * @return Returns the material.
 * 
 */

public Material getMaterial()
{
	return material;
}

/**
 * Setter of the property <tt>material</tt>
 *
 * @param material The material to set.
 *
 */
public void setMaterial(Material material ){
	this.material = material;
}

/*
 * (non-javadoc)
 */
public String id;
 
/**
 * Getter of the property <tt>id</tt>
 *@hibernate.id
 *   generator-class = "uuid"
 * @return Returns the id.
 * 
 */

public String getId()
{
	return id;
}

/**
 * Setter of the property <tt>id</tt>
 *
 * @param id The id to set.
 *
 */
public void setId(String id ){
	this.id = id;
}
/**
 * @hibernate.property
 * column = "intt_sn"
 * @return
 */
public String getSn() {
	return sn;
}

public void setSn(String sn) {
	this.sn = sn;
}
/**
 * @hibernate.many-to-one
 * column = "invoice_id"
 * @return
 */
public Invoice getInvoice() {
	return invoice;
}

public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
}

public String getReferenceBin() {
	return referenceBin;
}

public void setReferenceBin(String referenceBin) {
	this.referenceBin = referenceBin;
}

/**
 * @hibernate.many-to-one
 * column = "intt_itemTypeId"
 * @return
 */
public ItemType getItemType() {
	return itemType;
}

public void setItemType(ItemType itemType) {
	this.itemType = itemType;
}

public Invoice outboundInvoice;

/**
 * @hibernate.many-to-one
 * column = "intt_outbound_invoice_id"
 * @return
 */
public Invoice getOutboundInvoice() {
	return outboundInvoice;
}

public void setOutboundInvoice(Invoice outboundInvoice) {
	this.outboundInvoice = outboundInvoice;
}

public Float getF_unit_price() {
	return f_unit_price;
}

public void setF_unit_price(Float f_unit_price) {
	this.f_unit_price = f_unit_price;
}

public Float getF_unit_weight() {
	return f_unit_weight;
}

public void setF_unit_weight(Float f_unit_weight) {
	this.f_unit_weight = f_unit_weight;
}

public Float getF_gross_price() {
	return f_gross_price;
}

public void setF_gross_price(Float f_gross_price) {
	this.f_gross_price = f_gross_price;
}

public Float getF_gross_weight() {
	return f_gross_weight;
}

public void setF_gross_weight(Float f_gross_weight) {
	this.f_gross_weight = f_gross_weight;
}

public String getF_currency() {
	return f_currency;
}

public void setF_currency(String f_currency) {
	this.f_currency = f_currency;
}
}