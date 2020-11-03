CREATE TABLE User (
    userId int not null,
    username varchar(255),
    createDate DATE,
    createdBy varchar(255),
    lastChangeDate DATE,
    lastChangedBy varchar(255)
);

CREATE TABLE Stock (
    stockId int not null,
    stockName varchar(255),
    currentPrice number,
    highestPriceInLast5Min number,
    lowestPriceInLast5Min number,
    volume varchar(255),
    createDate DATE,
    createdBy varchar(255),
    lastChangeDate DATE,
    lastChangedBy varchar(255)
);

CREATE TABLE UserStock (
    userStockId int not null,
    userId int,
    stockId int,
    createDate DATE,
    createdBy varchar(255),
    lastChangeDate DATE,
    lastChangedBy varchar(255),
    activeIndicator varchar(1)
--     activeIndicator varchar(1)
);

CREATE SEQUENCE userstock_seq
  MINVALUE 1
  MAXVALUE 999999999
  START WITH 2
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE user_seq
  MINVALUE 1
  MAXVALUE 999999999
  START WITH 2
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE stock_seq
  MINVALUE 1
  MAXVALUE 999999999
  START WITH 3
  INCREMENT BY 1
  CACHE 20;