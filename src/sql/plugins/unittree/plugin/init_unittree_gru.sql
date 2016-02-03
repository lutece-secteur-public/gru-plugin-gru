
--
-- Dumping data for table `unittree_unit`
--
DELETE FROM unittree_unit; 

INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (0,-1,'Mairie de Paris','Mairie de Paris');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (1,0,'DICOM','Direction de la communication');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (10,1,'3975','Centre d\'appel du 3975');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (11,1,'MIB','Message in the bottle');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (2,0,'Equipe FF','Equipe Facil Famille');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (20,2,'Fact. périscolaire','Section facturation périscolaire');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (21,2,'Fact. petite enfance','Section facturation petite enfance');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (3,0,'DVD','Direction de la Voirie et des Déplacements');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (30,3,'Section voirie','Section voirie');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (4,0,'DFPE','Direction des Familles et de la Petite Enfance');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (40,4,'BEF','Bureau enfance et famille');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (5,0,'DASCO','Direction des Affaires Scolaires');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (6,0,'Mairies d\'arrdt','Mairies d\'arrondissement');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (60,6,'Mairie du 12ème','Guichet mairie du 12ème arrondissement');

--
-- Dumping data for table `unittree_unit_user`
--
DELETE FROM unittree_unit_user ;

INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (0,1);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (0,10);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (11,11);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (10,20);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (10,21);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (11,22);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (20,24);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (21,23);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (30,25);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (40,26);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (60,27);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (5,28);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (4,29);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (2,30);