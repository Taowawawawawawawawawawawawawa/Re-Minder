-- Mock data for Costumes
INSERT INTO costume (costumeId, costumeType, costumeName, costumeDescription, costumeFiles, price) VALUES
(10001, 'HAT', 'Wizard Hat', 'A magical hat for spellcasters.', 'https://i.ibb.co/sgmB3Cd/Wizard.png', 200),
(10002, 'DRESS', 'Princess Dress', 'An elegant dress for royal occasions.', 'https://i.ibb.co/4m998XT/princess.png', 300);

-- Mock data for Rewards
INSERT INTO rewards (rewardId, rewardSpriteArts, rewardName, rewardDescription, rewardPrice) VALUES
(30001, '/rewards/sprites/coupon_sprite.png', 'Discount Coupon', 'Redeem for 10% off your next purchase.', 100),
(30002, '/rewards/sprites/keychain_sprite.png', 'Exclusive Keychain', 'A limited edition keychain.', 200);
