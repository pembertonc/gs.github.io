INSERT INTO authorizationrole (id, description, rolename) VALUES ('-0000', '', 'Administrator')  ON CONFLICT DO NOTHING;
INSERT INTO authorizationrole (id, description, rolename) VALUES ('-0001', '', 'User')  ON CONFLICT DO NOTHING;

INSERT INTO subject (id, username, password) VALUES ('-0000', 'Administrator', 'Password')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('-0000', '-0000')  ON CONFLICT DO NOTHING;

INSERT INTO person (id, firstname, familyname, othername) VALUES ('PER002', 'Peter', 'Andrew', 'Shillingford') ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password, person_id) VALUES ('S000', 'User1', 'Password', 'PER002')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('0000', '-0001')  ON CONFLICT DO NOTHING;


INSERT INTO person (id, firstname, familyname, othername) VALUES ('PER001', 'James', 'Smith', 'Jamason') ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password, person_id) VALUES ('S001', 'jsmith', 'Password', 'PER001')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('S001', '-0001')  ON CONFLICT DO NOTHING;