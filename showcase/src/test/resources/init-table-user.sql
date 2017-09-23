CREATE TABLE IF NOT EXISTS t_user (
  id       INT NOT NULL  AUTO_INCREMENT,
  username VARCHAR(100),
  password VARCHAR(100),
  status   INT,
  team_id INT,
  PRIMARY KEY (`id`)
);

INSERT INTO t_user (username, password, status,team_id) VALUES ('aaa','aaa',1,1);
INSERT INTO t_user (username, password, status,team_id) VALUES ('bbb','aaa',1,1);
INSERT INTO t_user (username, password, status,team_id) VALUES ('ccc','aaa',1,2);
INSERT INTO t_user (username, password, status,team_id) VALUES ('ddd','aaa',1,2);

CREATE  TABLE t_team(
  id INT AUTO_INCREMENT,
  name VARCHAR(100),
  PRIMARY KEY (`id`)
);

INSERT INTO t_team (name) VALUES ('黑山老妖');
INSERT INTO t_team (name) VALUES ('天山童母');