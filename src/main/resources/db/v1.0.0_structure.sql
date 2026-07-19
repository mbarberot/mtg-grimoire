CREATE TABLE IF NOT EXISTS cards
(
    multiverseId VARCHAR(20) PRIMARY KEY,
    `set`        VARCHAR(20),
    name         VARCHAR(50),
    type         VARCHAR(50),
    manaCost     VARCHAR(20),
    text         VARCHAR(1023),
    power        VARCHAR(20),
    toughness    VARCHAR(20),
    tags         VARCHAR(255)
);