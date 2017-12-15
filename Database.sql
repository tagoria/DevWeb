DROP SCHEMA IF EXISTS projet_dev_web;
CREATE SCHEMA projet_dev_web;

CREATE TABLE projet_dev_web.Streamers(
    IdStreamer INTEGER(20) NOT NULL auto_increment,
    Description VARCHAR(500),
    CheminImage VARCHAR(100),
    Nom VARCHAR(50),
    PRIMARY KEY (IdStreamer)
);

CREATE TABLE projet_dev_web.Sons(
	IdSon INTEGER(20) NOT NULL auto_increment,
    Chemin VARCHAR(100) NOT NULL,
    Description VARCHAR(50),
    Supprime bool,
    Date DATE,
    IdStreamer INTEGER(20) NOT NULL,
     FOREIGN KEY (IdStreamer) REFERENCES Streamers(IdStreamer),
    CONSTRAINT pk0_Sons PRIMARY KEY (IdSon)
);

INSERT INTO projet_dev_web.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (1,'le streamer de feu','Images/Zerator.jpg','Zerator');
INSERT INTO projet_dev_web.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (2,'le vietnamo-moldave','Images/MisterMV.jpg','MisterMV');
INSERT INTO projet_dev_web.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (1, 'Sons/Zerator1.mp3', 'son 1', '2017-10-06', 1, 0);
INSERT INTO projet_dev_web.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (2, 'Sons/MisterMV1.mp3','son 2','2014-10-26', 2, 0);

DROP SCHEMA IF EXISTS projet_dev_web_test;
CREATE SCHEMA projet_dev_web_test;

CREATE TABLE projet_dev_web_test.Streamers(
    IdStreamer INTEGER(20) NOT NULL auto_increment,
    Description VARCHAR(500),
    CheminImage VARCHAR(100),
    Nom VARCHAR(50),
    PRIMARY KEY (IdStreamer)
);

CREATE TABLE projet_dev_web_test.Sons(
	IdSon INTEGER(20) NOT NULL auto_increment,
    Chemin VARCHAR(100) NOT NULL,
    Description VARCHAR(50),
    Supprime bool,
    Date DATE,
    IdStreamer INTEGER(20) NOT NULL,
     FOREIGN KEY (IdStreamer) REFERENCES Streamers(IdStreamer),
    CONSTRAINT pk0_Sons PRIMARY KEY (IdSon)
);

INSERT INTO projet_dev_web_test.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (1,'le streamer de feu','Images/Zerator.jpg','Zerator');
INSERT INTO projet_dev_web_test.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (2,'le vietnamo-moldave','Images/MisterMV.jpg','MisterMV');
INSERT INTO projet_dev_web_test.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (1, 'Sons/Zerator1.mp3', 'son 1', '2017-10-06', 1, 0);
INSERT INTO projet_dev_web_test.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (2, 'Sons/MisterMV1.mp3','son 2','2014-10-26', 2, 0);
