-- Mock data for Diaries
INSERT INTO diary (diaryId, userId) VALUES
(10001, 20001), -- User ID: 20001
(10002, 20002), -- User ID: 20002
(10003, 20003); -- User ID: 20003

-- Mock data for Pages
INSERT INTO pages (pageId, diaryId, createDate, details) VALUES
-- Pages for Diary 10001
(20001, 10001, '2024-11-15', 'Today was a great day! I finally finished my project.'),
(20002, 10001, '2024-11-16', 'Went out for a walk in the park. The weather was amazing!'),

-- Pages for Diary 10002
(20003, 10002, '2024-11-14', 'Feeling a bit under the weather today. Took the day off to rest.'),
(20004, 10002, '2024-11-15', 'Caught up with an old friend. It was so refreshing.'),

-- Pages for Diary 10003
(20005, 10003, '2024-11-13', 'Started learning a new programming language. It is so exciting!'),
(20006, 10003, '2024-11-14', 'Worked on a coding problem all day. Finally solved it in the evening.'),
(20007, 10003, '2024-11-15', 'Treated myself to a nice meal after a long week.');
