INSERT INTO subject (id, username, password, role) VALUES ('-0000', 'Administrator', 'Password', 'Administrator')  ON CONFLICT DO NOTHING;
