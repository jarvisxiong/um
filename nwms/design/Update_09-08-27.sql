--有用
alter table intt_orderitem add unit_price number;
alter table intt_orderitem add unit_weight number;
alter table intt_orderitem add gross_price number;
alter table intt_orderitem add gross_weight number;
alter table intt_orderitem add currency varchar(10);

--item 表增加字段
alter table intt_item add unit_price number;
alter table intt_item add unit_weight number;
alter table intt_item add gross_price number;
alter table intt_item add gross_weight number;
alter table intt_item add currency varchar(10);