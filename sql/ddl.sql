CREATE TABLE user
(
	  id          INTEGER       PRIMARY KEY
	, account     LONGVARCHAR   UNIQUE NOT NULL
	, name        LONGVARCHAR
	, email       LONGVARCHAR   UNIQUE
	, password    LONGVARCHAR   NOT NULL
	, description LONGVARCHAR
	, icon        LONGVARBINARY
	, insert_date TIMESTAMP     NOT NULL
	, update_date TIMESTAMP     NOT NULL
);

CREATE TABLE message
(
	  id          INTEGER     PRIMARY KEY
	, user_id     INTEGER     NOT NULL
	, text        LONGVARCHAR NOT NULL
	, insert_date TIMESTAMP   NOT NULL
	, update_date TIMESTAMP   NOT NULL
);

CREATE VIEW user_message AS
SELECT
	  account
	, name
	, message.id AS id
	, user_id
	, text
	, message.insert_date AS insert_date
FROM
	user, message
WHERE
	user.id = message.user_id;

-- CREATE SEQUENCE my_seq;

