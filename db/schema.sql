CREATE TABLE users
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username varchar(200) NOT NULL,
  password varchar(200) NOT NULL,
  name varchar(200) NOT NULL
);
CREATE UNIQUE INDEX users_username_uindex ON users (username);

CREATE TABLE notes
(
  id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  description varchar(600) NOT NULL,
  avatar blob,
  user_id int(11) NOT NULL,
  created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT notes_users_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE INDEX notes_users_user_id_fk ON notes (user_id);