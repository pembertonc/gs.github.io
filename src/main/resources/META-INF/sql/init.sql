INSERT INTO authorizationrole (id, description, rolename) VALUES ('-0000', '', 'Administrator')  ON CONFLICT DO NOTHING;
INSERT INTO subject (id, username, password) VALUES ('-0000', 'Administrator', 'Password')  ON CONFLICT DO NOTHING;
INSERT INTO subject_authorizationrole (subject_id, authorizationroles_id) VALUES ('-0000', '-0000')  ON CONFLICT DO NOTHING;
