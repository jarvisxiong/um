package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_destroyedItem"
 * @hibernate.joined-subclass-key
 * column = "warehouseItemId"
 * @author chase
 *
 */
public class DestroyedItem extends WarehouseItem {

}
