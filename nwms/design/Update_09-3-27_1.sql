create table intt_logPickedItem (warehouseItemId varchar2(255 char) not null, primary key (warehouseItemId));
create table intt_logStorageItem (warehouseItemId varchar2(255 char) not null, primary key (warehouseItemId));
alter table intt_logPickedItem add constraint FK64895BD6E6BACD9 foreign key (warehouseItemId) references intt_warehouseItem;
alter table intt_logStorageItem add constraint FKFDFA5F646E6BACD9 foreign key (warehouseItemId) references intt_warehouseItem;
