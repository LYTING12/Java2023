-- 1. 設立 level 預設值
insert into level (levelName) values ('分析人員'),('管理人員'),('主管');

-- 2. 設立 service 預設值
insert into service (serviceName, serviceUrl) 
values ('首頁', '/mvc/analyze/main'),
('Momo', '/mvc/analyze/ecWebsite/momo2'),
('商品', '/mvc/analyze/product/addProduct'),
('庫存', '/mvc/analyze/productQty'),
('權限管理', '/mvc/analyze/product/permissionSettings');

-- 3. 設立 levelRefService 預設值
insert into levelRefService (levelId, serviceId) values (1, 1),(1, 2),(2, 1),(2, 3),(2, 4),(3, 1),(3, 2),(3, 3),(3, 4),(3, 5);

-- 4. 設立 User 預設值
insert into user (username, password, levelId) values ('John', 'pass123', 1),('Sean', 'pass456', 2),('Amy', 'pass789', 3);

-- -------------------------------------------------------------------------------------------------------------------------------
-- 5. 設立 productType 預設值
insert into productType (name) values ('餐廚'), ('傢俱'), ('旅遊');

-- 6. 設立 productSubType 預設值
insert into productSubType (name) values ('鍋鏟'), ('平底鍋'), ('主廚刀');

-- 7. 設立 product 預設值
insert into product (productId, productName, productPrice, productBarcode, productBrand, productTypeId, productSubTypeId, productImg, productDesc, isLaunch, productQty) 
values ('A101', '好用鍋鏟', 550, '12345678', 'AAA', 1, 1, '','', 0, 50), ('B101', '好用平底鍋', 1200, '22345678', 'BBB', 1, 2, '','', 0, 100), ('C101', '好用平底鍋', 1200, '22345689', 'CCC', 1, 2, '','', 0, 80);

-- 8. 設立 ecommerce 預設值
insert into ecommerce (name, website) 
values ('Momo', 'https://www.momoshop.com.tw/main/Main.jsp'), 
('PChome', 'https://24h.pchome.com.tw/sign/ce?gclid=EAIaIQobChMI1NX3-N7UgwMVw80WBR0VnQ5PEAAYASAAEgLVPvD_BwE'), 
('蝦皮', 'https://shopee.tw/');

-- 9. 設立 stcok 預設值
insert into stock (productId, ecId, ecProductQty) values ('A101', 1, 10), ('B101', 1, 8);
insert into stock (productId, ecId, ecProductQty) values ('A101', 2, 30);

-- 10.設立 salesdata 預設值
insert into salesdata (ecId, productId, ecOrderNumber, ecProductCode, ecProductType, ecProductSubType, ecWarehouse, ecSalesQty, ecSalesPrice, ecSalesDate, ecSalesStatus) 
values (1, 'A101', 'BD-2023123012345-01', '12345-01', '刀具砧板配件', '鍋鏟', '轉單', 2, 550, 20231201, '銷售'),
(1, 'B101', 'BD-2023123012345-02', '12345-02', '鍋具', '平底鍋', '寄倉', 1, 1000, 20231202, '銷售');

-- 11.設立 productRefEcommerce 預設值
INSERT INTO `analyze`.`productRefEcommerce` (`productId`, `ecId`)
VALUES 
('A101', 1), -- Momo
('A101', 2); -- PChome

-- 依照平台，取得商品銷售資訊原始數據(待定)
-- select d.trxId,p.productId,p.productName,p.productBrand,pt.name,pst.name,p.productBarcode, s.ecProductQty, s.productQty, d.ecSalesQty,d.ecSalesPrice
-- from product p, salesdata d, ecommerce e , stock s, producttype pt, productsubtype pst
-- where p.productId = d.productId and d.ecId = e.id
-- and s.ecId = e.id and s.productId = p.productId
-- and pt.id = p.productTypeId
-- and pst.id = productSubTypeId
-- and e.name = 'Momo'; 