USE petclinic;

CREATE TABLE IF NOT EXISTS owners
(
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(50)  NOT NULL,
  address    VARCHAR(255) NOT NULL,
  city       VARCHAR(50)  NOT NULL,
  telephone  VARCHAR(20)  NOT NULL,
  INDEX (last_name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS pet_types
(
  id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  INDEX (name)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS pets
(
  id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(30),
  birth_date  DATE,
  pet_type_id BIGINT NOT NULL,
  owner_id    BIGINT NOT NULL,
  INDEX (name),
  FOREIGN KEY (owner_id) REFERENCES owners (id),
  FOREIGN KEY (pet_type_id) REFERENCES pet_types (id)
) engine = InnoDb;

INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (1, 'jack', 'russel', 'bark street', 'dogville', '0620134567');
INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (2, 'richard', 'lionheart', 'kings cross', 'sherwood', '0620133267');
INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (3, 'old', 'lady', 'silenthill', 'california', '0620224567');
INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (4, 'john', 'only', 'baker street', 'london', '0620137767');
INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (5, 'mr', 'x', 'classified', 'area 51', '0620135267');
INSERT INTO owners (id, first_name, last_name, address, city, telephone)
values (6, 'john', 'doe', 'murder scene', 'miami', '0620137767');

INSERT INTO pet_types (id, name)
VALUES (1, 'cat');
INSERT INTO pet_types (id, name)
VALUES (2, 'dog');
INSERT INTO pet_types (id, name)
VALUES (3, 'lizard');
INSERT INTO pet_types (id, name)
VALUES (4, 'snake');
INSERT INTO pet_types (id, name)
VALUES (5, 'bird');
INSERT INTO pet_types (id, name)
VALUES (6, 'hamster');

INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (1, 'Leo', '2000-09-07', 1, 1);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (2, 'Basil', '2002-08-06', 6, 2);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (3, 'Rosy', '2001-04-17', 2, 3);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (4, 'Jewel', '2000-03-07', 2, 3);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (5, 'Iggy', '2000-11-30', 3, 4);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (6, 'George', '2000-01-20', 4, 5);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (7, 'Samantha', '1995-09-04', 1, 6);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (8, 'Max', '1995-09-04', 1, 6);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (9, 'Lucky', '1999-08-06', 5, 1);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (10, 'Mulligan', '1997-02-24', 2, 2);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (11, 'Freddy', '2000-03-09', 5, 3);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (12, 'Lucky', '2000-06-24', 2, 4);
INSERT  INTO pets (id, name, birth_date, pet_type_id, owner_id)
VALUES (13, 'Sly', '2002-06-08', 1, 5);