DROP SCHEMA IF EXISTS projet_dev_web_loiselle;
CREATE SCHEMA projet_dev_web_loiselle;

CREATE TABLE projet_dev_web_loiselle.Streamers(
    IdStreamer INTEGER(20) NOT NULL auto_increment,
    Description VARCHAR(500),
    CheminImage VARCHAR(100),
    Nom VARCHAR(50),
    PRIMARY KEY (IdStreamer)
);

CREATE TABLE projet_dev_web_loiselle.Sons(
	IdSon INTEGER(20) NOT NULL auto_increment,
    Chemin VARCHAR(100) NOT NULL,
    Description VARCHAR(50),
    Supprime bool,
    Date DATE,
    IdStreamer INTEGER(20) NOT NULL,
     FOREIGN KEY (IdStreamer) REFERENCES Streamers(IdStreamer),
    CONSTRAINT pk0_Sons PRIMARY KEY (IdSon)
);

INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (1,'Le streamer de feu','Images/Zerator.jpg','Zerator');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (2,'Le vietnamo-moldave','Images/MisterMV.jpg','MisterMV');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (3,'Le roi bisou','Images/LRB.jpg','LRB');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (4,'Le type du hardcorner','Images/Benzaie.jpg','Benzaie');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (5,'Le bromingo','Images/Domingo.jpg','Domingo');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (6,'AKA faker','Images/Xari.jpg','Xari');
INSERT INTO projet_dev_web_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (7,'Le Pyrobarbare','Images/Bob Lennon.jpg','Bob Lennon');
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (1, 'Sons/Zerator/Projet1.mp3', 'Projet', '2017-10-06', 1, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (2, 'Sons/MisterMV/Karim Zianiii2.mp3','Karim Zianiii','2014-10-26', 2, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (3, 'Sons/LRB/Poele3.mp3', 'Poele', '2017-10-06', 3, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (4, 'Sons/Benzaie/Ta mere laink4.mp3','Ta mere laink','2014-10-26', 4, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (5, 'Sons/Domingo/Putain5.mp3', 'Putain', '2017-10-06', 5, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (6, 'Sons/Xari/Noooon6.mp3','Noooon','2014-10-26', 6, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (7, 'Sons/Bob Lennon/Ah Gay7.mp3','Ah Gay','2014-10-26', 7, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (8, 'Sons/MisterMV/Clavier qui colle8.mp3','Clavier qui colle','2014-10-26', 2, 0);
INSERT INTO projet_dev_web_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (9, 'Sons/Benzaie/Un hareng9.mp3','Un hareng','2014-10-26', 4, 0);
DROP SCHEMA IF EXISTS projet_dev_web_test_loiselle;
CREATE SCHEMA projet_dev_web_test_loiselle;

CREATE TABLE projet_dev_web_test_loiselle.Streamers(
    IdStreamer INTEGER(20) NOT NULL auto_increment,
    Description VARCHAR(500),
    CheminImage VARCHAR(100),
    Nom VARCHAR(50),
    PRIMARY KEY (IdStreamer)
);

CREATE TABLE projet_dev_web_test_loiselle.Sons(
	IdSon INTEGER(20) NOT NULL auto_increment,
    Chemin VARCHAR(100) NOT NULL,
    Description VARCHAR(50),
    Supprime bool,
    Date DATE,
    IdStreamer INTEGER(20) NOT NULL,
     FOREIGN KEY (IdStreamer) REFERENCES Streamers(IdStreamer),
    CONSTRAINT pk0_Sons PRIMARY KEY (IdSon)
);

INSERT INTO projet_dev_web_test_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (1,'le streamer de feu','Images/Zerator.jpg','Zerator');
INSERT INTO projet_dev_web_test_loiselle.streamers(IdStreamer,Description,CheminImage,Nom) VALUES (2,'le vietnamo-moldave','Images/MisterMV.jpg','MisterMV');
INSERT INTO projet_dev_web_test_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (1, 'Sons/Zerator1.mp3', 'son 1', '2017-10-06', 1, 0);
INSERT INTO projet_dev_web_test_loiselle.sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) VALUES (2, 'Sons/MisterMV1.mp3','son 2','2014-10-26', 2, 0);
