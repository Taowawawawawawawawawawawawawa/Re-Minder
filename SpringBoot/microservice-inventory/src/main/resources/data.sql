-- Mock data for Inventory
INSERT INTO inventory (inventoryId, userId) VALUES
(40001, 10001),
(40002, 10002),
(40003, 10003),
(40004, 10004),
(40005, 10005);

-- Mock data for costumeList (relation to costumes)
INSERT INTO inventory_costumeList (inventory_inventoryId, costumeList) VALUES
(40002, 10001), -- User 10002 owns "Wizard Hat"
(40003, 10002); -- User 10003 owns "Princess Dress"

-- Mock data for rewardList (relation to rewards)
INSERT INTO inventory_rewardList (inventory_inventoryId, rewardList) VALUES
(40001, 30001), -- User 10001 owns "Discount Coupon"
(40002, 30001), -- User 10002 owns "Discount Coupon"
(40002, 30002), -- User 10002 owns "Exclusive Keychain"
(40003, 30002); -- User 10003 owns "Exclusive Keychain"
