CREATE TABLE IF NOT EXISTS sets
(
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE if NOT EXISTS tags
(
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS cards
(
    multiverseId VARCHAR(20) PRIMARY KEY,
    setCode      VARCHAR(20),
    name         VARCHAR(50),
    type         VARCHAR(50),
    manaCost     VARCHAR(20),
    text         VARCHAR(1023),
    power        VARCHAR(20),
    toughness    VARCHAR(20),

    FOREIGN KEY (setCode) REFERENCES sets (code)
);

CREATE TABLE IF NOT EXISTS cardTags
(
    cardId  VARCHAR(20),
    tagName VARCHAR(255),

    PRIMARY KEY (cardId, tagName),
    FOREIGN KEY (cardId) REFERENCES cards (multiverseId),
    FOREIGN KEY (tagName) REFERENCES tags (name)
)
