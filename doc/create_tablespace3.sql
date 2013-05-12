sqlplus/nolog

drop user ump cascade;
drop user uums cascade;

------

CREATE TABLESPACE POWERDESK DATAFILE 
  'E:\app\huangjian\oradata\tms\POWERDESK01.DBF' SIZE 50M AUTOEXTEND ON NEXT 10M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;


--DROP TABLESPACE UMP INCLUDING CONTENTS AND DATAFILES;

CREATE TABLESPACE UMP DATAFILE 
  'E:\app\huangjian\oradata\tms\UMP01.DBF' SIZE 100M AUTOEXTEND ON NEXT 200M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;

--DROP TABLESPACE UUMS INCLUDING CONTENTS AND DATAFILES;

CREATE TABLESPACE UUMS DATAFILE 
  'E:\app\huangjian\oradata\tms\UUMS01.DBF' SIZE 100M AUTOEXTEND ON NEXT 50M MAXSIZE UNLIMITED
LOGGING
ONLINE
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;

-----users-------------
CREATE USER ump
  IDENTIFIED BY ump
  DEFAULT TABLESPACE UMP
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles for ump 
  GRANT RESOURCE TO ump;
  GRANT CONNECT TO ump;
  GRANT DBA TO ump;
  GRANT SELECT any table TO ump;
  ALTER USER ump DEFAULT ROLE ALL;
  -- 2 System Privileges for ump 
  --GRANT SELECT ANY DICTIONARY TO ump;
  GRANT UNLIMITED TABLESPACE TO ump;

  CREATE USER uums
  IDENTIFIED BY uums
  DEFAULT TABLESPACE UUMS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 3 Roles for uums 
  GRANT RESOURCE TO uums;
  GRANT CONNECT TO uums;
  --GRANT DBA TO uums;
  ALTER USER uums DEFAULT ROLE ALL;
  -- 2 System Privileges for uums 
  --GRANT SELECT ANY DICTIONARY TO uums;
  GRANT UNLIMITED TABLESPACE TO uums;
  
  ----------------imp----------
  
  imp ump/ump file=D:\work\self\cmd\cmd\bakup\tms_130331_1214.dmp log=D:\work\self\cmd\cmd\bakup\ump%date:~0,4%%date:~5,2%%date:~8,2%_imp.log fromuser=(powerdesk,plas) touser=(ump,uums) buffer=1024000
  
  imp ump/ump file=D:\work\self\cmd\cmd\bakup\PowerDesk_12-07-10_2330.dmp log=D:\work\self\cmd\cmd\bakup\ump%date:~0,4%%date:~5,2%%date:~8,2%_imp.log fromuser=(powerdesk) touser=(ump) tables=(bis_fact_store) IGNORE=Y buffer=1024000
  
  imp ump/ump file=D:\work\self\cmd\cmd\bakup\tms_130326_2008.dmp log=D:\work\self\cmd\cmd\bakup\ump%date:~0,4%%date:~5,2%%date:~8,2%_imp.log fromuser=(powerdesk) touser=(ump) tables=(JBPM_TASK,JBPM_TASK_CANDIDATE) IGNORE=Y buffer=1024000
  
  exp ump/ump file=tms_%date:~2,2%%date:~5,2%%date:~8,2%_%time:~0,2%%time:~3,2%.dmp log=ump_%date:~2,2%%date:~5,2%%date:~8,2%_0%time:~0,2%%time:~3,2%.log owner=(ump,uums) buffer=1024000 consistent=y feedback=100000