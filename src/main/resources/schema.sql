CREATE TABLE IF NOT EXISTS ADVERTISER
(
	ADVERTISER_ID	VARCHAR(100)		NOT NULL PRIMARY KEY,
	ADVERTISER_NAME		VARCHAR(255)		NOT NULL,
	ADVERTISER_CONTACT_NAME		VARCHAR(255)		NOT NULL,
	ADVERTISER_CREDIT_LIMIT		DECIMAL(10,2)
);