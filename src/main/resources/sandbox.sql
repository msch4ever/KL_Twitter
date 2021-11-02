
CREATE TABLE USER
(
      userId          BIGINT         AUTO_INCREMENT
    , login           VARCHAR(30)    NOT NULL
    , nickname        VARCHAR(30)    NOT NULL
    , dateRegistered  DATE
    , dateOfBirth     DATE
    , about           VARCHAR(250)   NOT NULL
    , lastUpdatedTime TIMESTAMP      ON UPDATE CURRENT_TIMESTAMP
    , entryTime       TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
    , CONSTRAINT      USERS_PK       PRIMARY KEY (userId)
);

INSERT INTO USER (login
                , nickname
                , dateRegistered
                , dateOfBirth
                , about)
VALUES  ('lolkek', 'lolkek', '2001-2-1', '2001-2-1', 'im first')

SELECT MAX(userId) FROM USER

SELECT * FROM USER

TRUNCATE TABLE USER