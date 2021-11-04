CREATE TABLE user
(
      userId INTEGER PRIMARY KEY ASC
    , nickname VARCHAR(25)
    , login VARCHAR(25) NOT NULL
    , dateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , dateOfBirth DATE
    , about VARCHAR(250)
);

--NEXT--

CREATE TRIGGER default_user_nickname
    AFTER INSERT ON user
    FOR EACH ROW
    WHEN NEW.nickname IS NULL
BEGIN
    UPDATE user SET nickname = NEW.login WHERE rowid = NEW.rowid;
END;

--NEXT--

CREATE TABLE tweet
(
      tweetId INTEGER PRIMARY KEY ASC
    , userId INTEGER NOT NULL
    , referenceTweet INTEGER
    , datePosted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , content VARCHAR(250) NOT NULL
    , FOREIGN KEY(userId) REFERENCES user(userId)
);

--NEXT--

CREATE TABLE followers
(
      userId INTEGER NOT NULL
    , followingUserId INTEGER NOT NULL
    , since TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT UNIQUE_COMBINATION UNIQUE (userId, followingUserId)
);

--NEXT--

CREATE TABLE likes
(
      tweetId INTEGER NOT NULL
    , likedUserId INTEGER NOT NULL
    , likedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT UNIQUE_COMBINATION UNIQUE (tweetId, likedUserId)
);