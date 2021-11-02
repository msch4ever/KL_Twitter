CREATE TABLE user
(
      userId INTEGER PRIMARY KEY ASC
    , nickname VARCHAR(25) NOT NULL DEFAULT login
    , login VARCHAR(25) NOT NULL
    , dateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , dateOfBirth DATE
    , about VARCHAR(250)
);

----------------------------------------------

CREATE TABLE tweet
(
      tweetId INTEGER PRIMARY KEY ASC
    , userId INTEGER NOT NULL
    , referenceTweet INTEGER
    , datePosted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , content VARCHAR(250) NOT NULL
    , FOREIGN KEY(userId) REFERENCES user(userId)
);

----------------------------------------------

CREATE TABLE followers
(
      userId INTEGER NOT NULL
    , followingUserId INTEGER NOT NULL
    , since TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT UNIQUE_COMBINATION UNIQUE (userId, followingUserId)
);


----------------------------------------------

CREATE TABLE likes
(
      tweetId INTEGER NOT NULL
    , likedUserId INTEGER NOT NULL
    , likedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT UNIQUE_COMBINATION UNIQUE (tweetId, likedUserId)
);