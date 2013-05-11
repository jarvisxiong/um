create table intt_itemType (intt_id varchar2(255 char) not null, intt_code varchar2(255 char), intt_alias varchar2(255 char), intt_itemTypeState varchar2(255 char), intt_remark varchar2(255 char), primary key (intt_id));
create table intt_itemTypeState (intt_id varchar2(255 char) not null, intt_description varchar2(255 char), intt_code varchar2(255 char), primary key (intt_id));

alter table INTT_ITEM add(intt_itemTypeId VARCHAR2(255 char));

alter table intt_itemType add constraint FK968FE987917B6BA0 foreign key (intt_itemTypeState) references intt_itemTypeState;
alter table intt_item add constraint FK368404AD8021D1FD foreign key (intt_itemTypeId) references intt_itemType;

--数据有用
Insert into INTT_ITEMTYPESTATE (Intt_ID,Intt_DESCRIPTION,intt_CODE) values ('402880021efc5fa0011efc6034560001','正常','0');
Insert into INTT_ITEMTYPESTATE (Intt_ID,intt_DESCRIPTION,intt_CODE) values ('402880021efc5fa0011efc6134560002','删除','1');
Insert into INTT_ITEMTYPESTATE (Intt_ID,intt_DESCRIPTION,intt_CODE) values ('402880021efc5fa0011efc6134560003','锁定','2');

--20090911补-michael
--unitstate 数据
insert into intt_unitstate(id,description,code) values('402880021efc5fa0011efc62dc40000a','正常',0);
insert into intt_unitstate(id,description,code) values ('402880021efc5fa0011efc62ef4b000b','删除',1);
insert into intt_unitstate(id,description,code) values ('402880021efc5fa0011efc630505000c','锁定',2);

--插入一条unit数据
insert into intt_unit(id,state,code,alias) values ('402880021efc5fa0011efc638eb7000d','402880021efc5fa0011efc62dc40000a','0','个');

--插入warehousestate
insert into intt_warehousestate(id,description,code) values('4028803c1efce638011efce6a5dc0001','正常',0);
insert into intt_warehousestate(id,description,code) values ('4028803c1efce638011efce6a5fb0002','删除',1);
insert into intt_warehousestate(id,description,code) values ('4028803c1efce638011efce6a5fb0003','锁定',2);
