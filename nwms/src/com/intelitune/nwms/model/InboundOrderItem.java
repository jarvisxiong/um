package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_inboundOrderItem"
 * @hibernate.joined-subclass-key
 * column = "orderItemId"
 * @author chase
 *
 */
public class InboundOrderItem extends OrderItem {

}
