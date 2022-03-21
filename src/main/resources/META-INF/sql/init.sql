INSERT INTO authorizationrole (id, description, rolename) VALUES ('-0000', '', 'Administrator')  ON CONFLICT DO NOTHING;
INSERT INTO authorizationrole (id, description, rolename) VALUES ('-0001', '', 'User')  ON CONFLICT DO NOTHING;

INSERT INTO contact (id, email, telephone1, telephone2) VALUES ('CT000', 'cshillingford@email.com', '767-245-2222', '256-222-6959') ON CONFLICT DO NOTHING;
INSERT INTO person (id, firstname, familyname, othername, contact_id) VALUES ('PER000', 'Carol', 'Jannet', 'Shillingford', 'CT000') ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password, person_id) VALUES ('-0000', 'Administrator', 'Password', 'PER000')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('-0000', '-0000')  ON CONFLICT DO NOTHING;

INSERT INTO contact (id, email, telephone1, telephone2) VALUES ('CT001', 'pshillingford@email.com', '787-265-2333', '256-333-6959') ON CONFLICT DO NOTHING;
INSERT INTO person (id, firstname, familyname, othername, contact_id) VALUES ('PER002', 'Peter', 'Andrew', 'Shillingford', 'CT001') ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password, person_id) VALUES ('S000', 'User1', 'Password', 'PER002')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('S000', '-0001')  ON CONFLICT DO NOTHING;

INSERT INTO contact (id, email, telephone1, telephone2) VALUES ('CT002', 'jjamason@email.com', '555-265-5553', '555-222-6959') ON CONFLICT DO NOTHING;
INSERT INTO person (id, firstname, familyname, othername, contact_id) VALUES ('PER001', 'James', 'Smith', 'Jamason', 'CT002') ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password, person_id) VALUES ('S001', 'jsmith', 'Password', 'PER001')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('S001', '-0001')  ON CONFLICT DO NOTHING;