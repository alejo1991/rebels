DROP TABLE IF EXISTS sattelite_message_tracking;

DROP TABLE IF EXISTS sattelite_position_history;

DROP TABLE IF EXISTS user_model;

--table sattelite
CREATE TABLE sattelite (
	id int AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT UQ_SATTELITE_NAME UNIQUE(name)
);

--table sattelite_position_history
CREATE TABLE sattelite_position_history (
	id int AUTO_INCREMENT PRIMARY KEY,
	sattelite_id int NOT NULL,
	position_x FLOAT NOT NULL,
	position_y FLOAT NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE sattelite_position_history
	ADD FOREIGN KEY(sattelite_id)
	REFERENCES sattelite(id);


--table sattelite_message_tracking
CREATE TABLE sattelite_message_tracking (
	id int AUTO_INCREMENT PRIMARY KEY,
	sattelite_id int NOT NULL,
	sattelite_position_id int NOT NULL,
	message VARCHAR(250) NOT NULL,
	distance FLOAT NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE sattelite_message_tracking
	ADD FOREIGN KEY(sattelite_id)
	REFERENCES sattelite(id);	
	
ALTER TABLE sattelite_message_tracking
	ADD FOREIGN KEY(sattelite_position_id)
	REFERENCES sattelite_position_history(id);
	



----INSERTING PRE-EXISTING RESISTENCE REGISTERED SATTELITES
INSERT INTO sattelite (name) VALUES ('kenobi');
INSERT INTO sattelite (name) VALUES ('skywalker');
INSERT INTO sattelite (name) VALUES ('sato');
	

INSERT INTO sattelite_position_history (sattelite_id, position_x, position_y)
	VALUES
	((select id from sattelite where name = 'kenobi'), -500, -200),
	((select id from sattelite where name = 'skywalker'), 100, -100),
	((select id from sattelite where name = 'sato'), 500, 100);
