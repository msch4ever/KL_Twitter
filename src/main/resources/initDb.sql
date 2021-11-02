SET SCHEMA TWITTER;

CREATE TABLE USER
(
      userId          BIGINT AUTO_INCREMENT
    , role            VARCHAR(15) DEFAULT 'USER'
    , firstName       VARCHAR(30) NOT NULL
    , lastName        VARCHAR(30) NOT NULL
    , dateOfBirth     DATE
    , lastUpdatedTime TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    , entryTime       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT USER_PK PRIMARY KEY (userId)
);

CREATE TABLE TWEET
(
      tweetId          BIGINT AUTO_INCREMENT
    , role            VARCHAR(15) DEFAULT 'USER'
    , firstName       VARCHAR(30) NOT NULL
    , lastName        VARCHAR(30) NOT NULL
    , dateOfBirth     DATE
    , lastUpdatedTime TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    , entryTime       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT TWEET_PK PRIMARY KEY (tweetId)
);
