USE vetdb;

CREATE TABLE IF NOT EXISTS vets
(
  id         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name  varchar(50) NOT NULL,
  index (last_name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS specialties
(
  id   BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) NOT NULL,
  index (name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS vets_specialties
(
  vets_id        BIGINT NOT NULL,
  specialties_id BIGINT NOT NULL,
  foreign key (vets_id) references vets (id),
  foreign key (specialties_id) references specialties (id),
  unique (vets_id, specialties_id)
) engine = InnoDB;

INSERT INTO vets (id, first_name, last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets (id, first_name, last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets (id, first_name, last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets (id, first_name, last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets (id, first_name, last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets (id, first_name, last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties (id, name) VALUES (1, 'radiology');
INSERT INTO specialties (id, name) VALUES (2, 'surgery');
INSERT INTO specialties (id, name) VALUES (3, 'dentistry');

INSERT INTO vets_specialties (vets_id, specialties_id) VALUES (2, 1);
INSERT INTO vets_specialties (vets_id, specialties_id) VALUES (3, 2);
INSERT INTO vets_specialties (vets_id, specialties_id) VALUES (3, 3);
INSERT INTO vets_specialties (vets_id, specialties_id) VALUES (4, 2);
INSERT INTO vets_specialties (vets_id, specialties_id) VALUES (5, 1);