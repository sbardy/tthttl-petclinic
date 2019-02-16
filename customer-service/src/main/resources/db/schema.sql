# CREATE DATABASE IF NOT EXISTS petclinic;
# GRANT ALL PRIVILEGES ON petclinic.* TO 'root'@'%' IDENTIFIED BY '1234';

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