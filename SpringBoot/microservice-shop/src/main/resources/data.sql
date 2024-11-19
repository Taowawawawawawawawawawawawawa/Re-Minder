-- Mock data for Costumes
INSERT INTO costume (costumeId, costumeType, costumeName, costumeDescription, costumeFiles, price) VALUES
(10001, 'HAT', 'Cowboy Hat', 'A magical hat for cowboy.', 'https://drive.google.com/uc?id=1AgSjNDYF8o961aRBM4VFxduQ49EWQKLo', 200),
(10002, 'DRESS', 'Princess Dress', 'An elegant dress for royal occasions.', 'https://drive.google.com/uc?id=1c7jGx_gY00uUsVzcoIysMOauwZ270pN7', 300);

-- Mock data for Themes
INSERT INTO themes (themeId, frameSpriteArts, backGround, bgm, price) VALUES
(20001, '/themes/frames/forest_frame.png', '/themes/backgrounds/forest_background.png', '/themes/bgm/forest_theme.mp3', 150),
(20002, '/themes/frames/space_frame.png', '/themes/backgrounds/space_background.png', '/themes/bgm/space_theme.mp3', 250);

-- Mock data for Rewards
INSERT INTO rewards (rewardId, rewardSpriteArts, rewardName, rewardDescription, rewardPrice) VALUES
(30001, '/rewards/sprites/coupon_sprite.png', 'Discount Coupon', 'Redeem for 10% off your next purchase.', 100),
(30002, '/rewards/sprites/keychain_sprite.png', 'Exclusive Keychain', 'A limited edition keychain.', 200);
