DROP TABLE IF EXISTS satellite_message_tracking;

DROP TABLE IF EXISTS satellite_position_history;

DROP TABLE IF EXISTS satellite;

--table satellite
CREATE TABLE satellite (
	id int AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT UQ_SATTELITE_NAME UNIQUE(name)
);

--table satellite_position_history
CREATE TABLE satellite_position_history (
	id int AUTO_INCREMENT PRIMARY KEY,
	satellite_id int NOT NULL,
	position_x FLOAT NOT NULL,
	position_y FLOAT NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE satellite_position_history
	ADD FOREIGN KEY(satellite_id)
	REFERENCES satellite(id);


--table satellite_message_tracking
CREATE TABLE satellite_message_tracking (
	id int AUTO_INCREMENT PRIMARY KEY,
	satellite_id int NOT NULL,
	satellite_position_id int NOT NULL,
	message VARCHAR(250) NOT NULL,
	distance FLOAT NOT NULL,
	date_insert DATE DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE satellite_message_tracking
	ADD FOREIGN KEY(satellite_id)
	REFERENCES satellite(id);	
	
ALTER TABLE satellite_message_tracking
	ADD FOREIGN KEY(satellite_position_id)
	REFERENCES satellite_position_history(id);
	



----INSERTING PRE-EXISTING RESISTENCE REGISTERED SATTELITES
INSERT INTO satellite (name) VALUES ('kenobi');
INSERT INTO satellite (name) VALUES ('skywalker');
INSERT INTO satellite (name) VALUES ('sato');
	

INSERT INTO satellite_position_history (satellite_id, position_x, position_y)
	VALUES
	((select id from satellite where name = 'kenobi'), -500, -200),
	((select id from satellite where name = 'skywalker'), 100, -100),
	((select id from satellite where name = 'sato'), 500, 100);
