CREATE TABLE IF NOT EXISTS users (
  id       INT NOT NULL  AUTO_INCREMENT,
  username VARCHAR(100),
  password VARCHAR(100),
  status   INT,
  PRIMARY KEY (`id`)
);

INSERT INTO users (username, password, status) VALUES ('aaa','aaa',1);
INSERT INTO users (username, password, status) VALUES ('bbb','aaa',1);
INSERT INTO users (username, password, status) VALUES ('ccc','aaa',1);
INSERT INTO users (username, password, status) VALUES ('ddd','aaa',1);
