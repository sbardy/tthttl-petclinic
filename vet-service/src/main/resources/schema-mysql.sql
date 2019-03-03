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