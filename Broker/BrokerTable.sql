--drop table SHARES;
--drop TABLE STOCK;
--drop TABLE CUSTOMER;

CREATE TABLE CUSTOMER(SSN varCHAR2(15) NOT NULL,
		CUST_NAME varCHAR2(40) NOT NULL,
		ADDRESS varCHAR2(100) NOT NULL);
ALTER TABLE CUSTOMER ADD(CONSTRAINT PK_CUSTOMER PRIMARY KEY(SSN));
COMMIT;

CREATE TABLE STOCK(SYMBOL varCHAR2(8) NOT NULL,
		PRICE NUMBER(10,2) NOT NULL);	
ALTER TABLE STOCK ADD(CONSTRAINT PK_STOCK PRIMARY KEY(SYMBOL));
COMMIT;

CREATE TABLE SHARES(SSN varCHAR2(15) NOT NULL,
		SYMBOL varCHAR2(8) NOT NULL,
		QUANTITY NUMBER(10,0) NOT NULL);
ALTER TABLE SHARES ADD(CONSTRAINT PK_SHARES PRIMARY KEY(SSN,SYMBOL));
ALTER TABLE SHARES ADD(CONSTRAINT FK_SSN FOREIGN KEY(SSN) REFERENCES CUSTOMER);
ALTER TABLE SHARES ADD(CONSTRAINT FK_SYMBOL FOREIGN KEY(SYMBOL) REFERENCES STOCK);
COMMIT;		


insert into customer values( '111-111', 'Yufirst1', 'Seoul');
insert into customer values( '111-112', 'Yufirst2', 'Pusan');
insert into customer values( '111-113', 'Yufirst3', 'Daegu');
insert into customer values( '111-114', 'Yufirst4', 'Kwangju');
insert into customer values( '111-115', 'yufirst5', 'Mokpo');
insert into customer values( '111-116', 'Yufirst6', 'Ulsan');
insert into customer values( '111-117', 'Yufirst7', 'Suwon');
insert into customer values( '111-118', 'Yufirst8', 'Inchon');
insert into customer values( '111-119', 'Yufirst9', 'Seoul');

commit;

insert into stock ( symbol,price) values( 'SUNW', 68.75);
insert into stock ( symbol,price) values( 'CyAs', 22.675);
insert into stock ( symbol,price) values( 'DUKE', 6.25);
insert into stock ( symbol,price) values( 'ABStk', 18.5);
insert into stock ( symbol,price) values( 'JSVco', 9.125);
insert into stock ( symbol,price) values( 'TMAs', 82.375);
insert into stock ( symbol,price) values( 'BWInc', 11.375);
insert into stock ( symbol,price) values( 'GMEnt', 44.625);
insert into stock ( symbol,price) values( 'PMLtd', 203.375);
insert into stock ( symbol,price) values( 'JDK', 33.5);

commit;

insert into shares values('111-111','SUNW',400);
insert into shares values('111-111','JDK',150);
insert into shares values('111-112','JDK',300);
insert into shares values('111-113','TMAs',800);
insert into shares values('111-113','ABStk',50);
insert into shares values('111-113','JSVco',100);
insert into shares values('111-114','DUKE',200);
insert into shares values('111-115','GMEnt',200);
insert into shares values('111-116','SUNW',900);
insert into shares values('111-117','JDK',340);
insert into shares values('111-117','GMEnt',100);
insert into shares values('111-117','BWInc',50);
insert into shares values('111-118','TMAs',750);
insert into shares values('111-119','JSVco',930);

commit;