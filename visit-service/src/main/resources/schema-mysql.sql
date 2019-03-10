USE visitdb;

CREATE TABLE IF NOT EXISTS visits
(
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  date        TIMESTAMP,
  description VARCHAR(50),
  pet_id      BIGINT,
  INDEX (pet_id)
) engine = InnoDb;