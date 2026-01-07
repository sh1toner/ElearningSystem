-- Додаємо адміністратора
INSERT INTO humans (username, password, email, role) VALUES
  ('admin', '{bcrypt}$2a$10$Cfzn0y5b0F2xS6kIhM6b7u3YJkCkZy7y6B3n2E9PjRlc0R5I3p6mO', 'admin@example.com', 'ADMIN');

