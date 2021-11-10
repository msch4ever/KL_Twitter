INSERT INTO user (login, dateOfBirth, about)
VALUES
       ('@rip212', '1993-05-27', 'Hi, i''m Andrew. JS evangelista')
     , ('@msch4ever', '1991-01-07', 'Hi, i''m Kostia. I love Java')
     , ('@dardevil', '1993-06-13', 'Hi, i''m Olezhna. I love doing nothing and complain')
     , ('@mkbhd', '1995-11-11', 'Hi, i''m Marquess Brownlee. I love making tech videos')
     , ('@boo88', '1988-04-11', 'Hi, i''m Dima. I love selling things');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (1, '2001-09-11', null, 'We will never forget this moment! America will never settle!')
     , (1, null, null, 'I was always wondering why it is so cool to be awesome!');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (2, '2001-09-11', 1, 'Oh my god! What happened!?')
     , (2, null, null, 'Hi chat! Is Mayo an instrument?');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (3, '2001-09-11', 3, 'They attacked US!')
     , (3, null, null, 'I just love COCK! Cant imagine my life without it!');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (4, '2001-09-11', 1, 'Very Sad!')
     , (4, null, 6, 'That''s so terrible we can not edit tweets XD');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (5, null, null, 'Hi, That will be my first tweet!');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (1, null, 4, 'No Kostia, mayo isn''t and instrument!')
     , (1, null, 8, 'Ahahahahha LOL!');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES
       (4, null, 10, '@rip212 Why would you be so angry at mayo!');

--NEXT--

INSERT INTO followers (userId, followingUserId)
VALUES
       (1, 2)
    ,  (1, 3)
    ,  (1, 5)
    ,  (2, 1)
    ,  (2, 3)
    ,  (2, 4)
    ,  (2, 5)
    ,  (3, 1)
    ,  (3, 2)
    ,  (4, 2)
    ,  (5, 1)
    ,  (5, 2)
    ,  (5, 3);

--NEXT--

INSERT INTO likes (tweetId, likedUserId)
VALUES
       (1, 2)
     , (1, 3)
     , (1, 5)
     , (2, 2)
     , (4, 1)
     , (4, 3)
     , (4, 4)
     , (4, 5)
     , (6, 1)
     , (6, 2)
     , (6, 5)
     , (8, 1)
     , (8, 2)
     , (8, 5)
     , (11, 4)
     , (12, 2)
     , (12, 1)
     , (12, 5);
