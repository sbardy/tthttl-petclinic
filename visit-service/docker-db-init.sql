USE visitdb;

CREATE TABLE IF NOT EXISTS visits
(
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  date        TIMESTAMP,
  description VARCHAR(50),
  pet_id      BIGINT,
  INDEX (pet_id)
) engine = InnoDb;

INSERT INTO visits(id,date, description, pet_id) values (1, '2018-01-01', 'Good boy', 1);
INSERT INTO visits(id,date, description, pet_id) values (2, '2017-02-04', 'Rabies Shot', 2);
INSERT INTO visits(id,date, description, pet_id) values (3, '2011-07-15', 'Injured left foot', 6);
INSERT INTO visits(id,date, description, pet_id) values (4, '2013-05-30', 'Cutting balls off :(', 3);
INSERT INTO visits(id,date, description, pet_id) values (5, '2015-02-28', 'Check-up', 4);
INSERT INTO visits(id,date, description, pet_id) values (6, '2016-05-18', 'Food poisoning', 5);