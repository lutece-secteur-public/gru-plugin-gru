
--
-- Dumping data for table `unittree_unit`
--
DELETE FROM unittree_unit WHERE id_unit = 0;
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (0,-1,'Mairie de Paris','Mairie de Paris');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (1,0,'DICOM','Direction de la communication');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (2,1,'3975','Centre d\'appel du 3975');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (3,0,'Mairies d\'arrdt','Mairies d\'arrondissement');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (4,3,'Mairie du 2ème','Mairie du 2ème arrondissement');
INSERT INTO unittree_unit (id_unit, id_parent, label, description) VALUES (5,0,'Equipe FF','Equipe Facil Famille');

--
-- Dumping data for table `unittree_unit_user`
--
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (0,1);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (0,10);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (5,11);
INSERT INTO unittree_unit_user (id_unit, id_user) VALUES (5,12);
