



--User Table
insert into User (userId, username, createDate, createdBy, lastChangeDate, lastChangedBy) values (1, 'user1', sysdate, 'system_user', sysdate, 'system_user');
insert into User (userId, username, createDate, createdBy, lastChangeDate, lastChangedBy) values (2, 'user2', sysdate, 'system_user', sysdate, 'system_user');

insert into Stock (stockId, stockName, currentPrice, highestPriceInLast5Min, lowestPriceInLast5Min, volume, createDate, createdBy, lastChangeDate, lastChangedBy)
values (1, 'IBM', 108.15, 108.3, 108.15, 398, sysdate, 'system_user', sysdate, 'system_user');
insert into Stock (stockId, stockName, currentPrice, highestPriceInLast5Min, lowestPriceInLast5Min, volume, createDate, createdBy, lastChangeDate, lastChangedBy)
values (2, 'AMZN', 109.15, 109.3, 106.15, 1398, sysdate, 'system_user', sysdate, 'system_user');
insert into Stock (stockId, stockName, currentPrice, highestPriceInLast5Min, lowestPriceInLast5Min, volume, createDate, createdBy, lastChangeDate, lastChangedBy)
values (3, 'INTC', 110.15, 109.3, 106.15, 1398, sysdate, 'system_user', sysdate, 'system_user');
insert into Stock (stockId, stockName, currentPrice, highestPriceInLast5Min, lowestPriceInLast5Min, volume, createDate, createdBy, lastChangeDate, lastChangedBy)
values (4, 'MSFT', 111.15, 109.3, 106.15, 1398, sysdate, 'system_user', sysdate, 'system_user');

--
insert into UserStock (userStockId, userId, stockId, activeIndicator, createDate, createdBy, lastChangeDate, lastChangedBy) values (1, 1, 1, 'Y', sysdate, 'system_user', sysdate, 'system_user');
insert into UserStock (userStockId, userId, stockId, activeIndicator, createDate, createdBy, lastChangeDate, lastChangedBy) values (2, 1, 2, 'Y', sysdate, 'system_user', sysdate, 'system_user');

insert into UserStock (userStockId, userId, stockId, activeIndicator, createDate, createdBy, lastChangeDate, lastChangedBy) values (3, 2, 3, 'Y', sysdate, 'system_user', sysdate, 'system_user');
insert into UserStock (userStockId, userId, stockId, activeIndicator, createDate, createdBy, lastChangeDate, lastChangedBy) values (4, 2, 4, 'Y', sysdate, 'system_user', sysdate, 'system_user');
--
