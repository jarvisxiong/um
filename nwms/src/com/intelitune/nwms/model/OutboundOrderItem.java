package com.intelitune.nwms.model;
/**
 * @hibernate.joined-subclass
 * table = "intt_outboundOrderItem"
 * @hibernate.joined-subclass-key
 * column = "orderItemId"
 * @author chase
 *
 */
public class OutboundOrderItem extends OrderItem {

}
