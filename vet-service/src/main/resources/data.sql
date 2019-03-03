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