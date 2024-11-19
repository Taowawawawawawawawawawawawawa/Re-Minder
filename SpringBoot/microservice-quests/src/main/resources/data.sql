INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10001, 'How are you today?', 'Hello! How are you today? Let us know!', 1, 2, 1, 'text','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10001, '1'),
(10001, '2'),
(10001, '3');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10001, 'INFP'),
(10001, 'INFJ');

INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10002, 'Take a photo of sunrise', 'Wake up early and take a photo of the most beautiful sunrise you can see.', 1, 2, 1, 'picture','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10002, '1');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10002, 'ESFP'),
(10002, 'ISFP');

INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10003, 'Run 1 km', 'Spend time exercising by running 1 kilometer.', 2, 5, 1, 'text','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10003, '2');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10003, 'ENTJ'),
(10003, 'ESTJ');

INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10004, 'Cook a meal', 'Prepare your favorite meal and take a picture of it.', 2, 5, 1, 'picture','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10004, '1'),
(10004, '2');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10004, 'ISFP'),
(10004, 'ESFP');

INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10005, 'Write an article about happiness', 'Write an article expressing your perspective on happiness.', 3, 10, 1, 'text','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10005, '2'),
(10005, '3');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10005, 'INFJ'),
(10005, 'ENTP');

INSERT INTO Quest (questId, questName, questDescription, difficulty, berylReward, pointReward, questSubmitMethod, targetObject) 
VALUES 
(10006, 'Climb a mountain', 'Go mountain climbing and share the photos you took.', 3, 10, 1, 'picture','other');

INSERT INTO Quest_availableTime (Quest_questId, availableTime) VALUES
(10006, '1');

INSERT INTO Quest_suitableMBTI (Quest_questId, suitableMBTI) VALUES
(10006, 'ESTP'),
(10006, 'ISTP');
