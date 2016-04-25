--
-- Data for table core_portlet
--
DELETE FROM core_portlet WHERE id_portlet = 95;
INSERT INTO core_portlet VALUES (95,'HTML_PORTLET',1,'Liens','2016-03-10 17:05:55',0,1,1,100,0,'2016-02-16 16:11:11',1,'none',4369);


--
-- Data for table core_datastore
--

DELETE FROM core_datastore WHERE entity_key='portal.site.site_property.logo_url';
INSERT INTO core_datastore VALUES('portal.site.site_property.logo_url', 'images/admin/skin/nef_inverse.png');


--
-- Data for table core_feature_group
--

DELETE FROM core_feature_group WHERE id_feature_group LIKE "GRU%"; 
INSERT INTO core_feature_group VALUES ('GRU','gru.features.group.gru.description','gru.features.group.gru.label',0);
INSERT INTO core_feature_group VALUES ('GRU_ADMIN','gru.features.group.gru_admin.description','gru.features.group.gru_admin.label',0);


--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'GRU_ADMIN_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('GRU_ADMIN_MANAGEMENT','gru.adminFeature.ManageAdminGRU.name',1,'jsp/admin/plugins/gru/ManageFeatures.jsp','gru.adminFeature.ManageAdminGRU.description',0,'gru','GRU_ADMIN',NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'GRU_ADMIN_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('GRU_ADMIN_MANAGEMENT',1);


--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'GRU_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('GRU_MANAGEMENT','gru.adminFeature.ManageCustomers.name',1,'jsp/admin/plugins/gru/ManageCustomers.jsp','gru.adminFeature.ManageCustomers.description',0,'gru','GRU',NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'GRU_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('GRU_MANAGEMENT',1);

--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'GRU_DEMAND_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('GRU_DEMAND_MANAGEMENT','gru.adminFeature.ManageDemand.name',1,'jsp/admin/plugins/gru/ManageDemandTypes.jsp','gru.adminFeature.ManageDemand.description',0,'gru','GRU_ADMIN',NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'GRU_DEMAND_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('GRU_DEMAND_MANAGEMENT',1);


--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'GRU_DOMAIN_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('GRU_DOMAIN_MANAGEMENT','gru.adminFeature.ManageDomainGru.name',1,'jsp/admin/plugins/gru/ManageBusinessDomains.jsp','gru.adminFeature.ManageDomainGru.description',0,'gru','GRU_ADMIN',NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'GRU_DOMAIN_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('GRU_DOMAIN_MANAGEMENT',1);


--
-- Data for table core_admin_role
--
DELETE FROM core_admin_role WHERE role_key LIKE 'gru_%';
INSERT INTO core_admin_role ( role_key , role_description ) VALUES 
( 'gru_admin' ,'GRU - Administrateur' ),
( 'gru_level_1' ,'GRU - Agent niveau 1' ),
( 'gru_level_2' ,'GRU - Agent niveau 2' ),
( 'gru_level_3' ,'GRU - Agent niveau 3' ),
( 'gru_dom_recl_facilfamille' ,'GRU - Agents du domaine réclamations Facil Famille' ),
( 'gru_dom_info_mairie' ,'GRU - Agents du domaine informations mairie' ),
( 'gru_dom_info_stationnement' ,'GRU - Agents du domaine informations Stationnement' ),
( 'gru_dom_recl_autre' ,'GRU - Agents du domaine informations autre' ),
( 'gru_dom_info_autre' ,'GRU - Agents du domaine réclamations autre' )
;


--
-- Data for table core_admin_role_resource
--
DELETE FROM core_admin_role_resource WHERE rbac_id >= 400 AND rbac_id <= 412;
INSERT INTO core_admin_role_resource ( rbac_id , role_key, resource_type, resource_id, permission ) VALUES 
( 400 , 'gru_admin' , 'GRU_FEATURE' , '*' , '*' ),
( 401 , 'gru_admin' , 'GRU_DEMAND_ACTION' , '*' , '*' ),
( 402 , 'gru_admin' , 'GRU_DOMAIN' , '*' , '*' ),
( 403 , 'gru_level_1' ,'GRU_FEATURE' , '*' , '*' ),
( 404 , 'gru_level_1' ,'GRU_DEMAND_ACTION' , '*' , '*' ),
( 405 , 'gru_level_1' ,'GRU_DOMAIN' , '*' , '*' ),
( 406 , 'gru_level_2' ,'GRU_FEATURE' , '*' , '*' ),
( 407 , 'gru_level_2' ,'GRU_DEMAND_ACTION' , '*' , '*' ),
( 408 , 'gru_level_2' ,'GRU_DOMAIN' , '*' , '*' ),
( 409 , 'gru_level_3' ,'GRU_FEATURE' , '*' , '*' ),
( 410 , 'gru_level_3' ,'GRU_DEMAND_ACTION' , '*' , '*' ),
( 411 , 'gru_level_3' ,'GRU_DOMAIN' , '*' , '*' )
;



DELETE FROM core_admin_user WHERE id_user >= 10 AND id_user <= 50;
INSERT INTO core_admin_user VALUES 
(10,'gruadmin','GRU','Administrateur','gruadmin@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(20,'dom','Durant','Dominique','dom@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(21,'ale','Dupont','Alexia','ale@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(22,'gui','Martin','Guillaume','gui@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(23,'lau','Dubois','Laura','lau@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(24,'max','Petit','Maxime','max@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(25,'ber','Morel','Bernard','ber@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(26,'vic','Roussel','Victor','vic@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(27,'mar','Fontaine','Marie','mar@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(28,'ant','Chevalier','Antoine','ant@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(29,'jul','Robin','Jules','jul@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(30,'vla','Perrin','Vladimir','vla@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all');

UPDATE core_admin_user SET password = 'grudemo!' WHERE access_code = 'admin'; 

DELETE FROM core_user_role WHERE role_key LIKE 'gru_%';
INSERT INTO core_user_role ( role_key , id_user ) VALUES
( 'gru_admin' , 1 ),
( 'gru_admin' , 10 ),

( 'gru_dom_recl_facilfamille', 21), -- Alexia
( 'gru_level_1', 21),

( 'gru_dom_recl_facilfamille', 23), -- Laura
( 'gru_level_2', 23),

( 'gru_dom_recl_facilfamille', 24), -- Maxime
( 'gru_level_2', 24), 


( 'gru_dom_recl_facilfamille', 26), -- Victor
( 'gru_level_3' , 26 ),


( 'gru_dom_info_mairie' , 27), -- Marie
( 'gru_level_1' , 27 ),


( 'gru_dom_info_mairie', 20), -- Dominique 
( 'gru_level_1', 20), 

( 'gru_dom_info_mairie', 22), -- Guillaume
( 'gru_level_1' , 22 ),

( 'gru_dom_info_stationnement', 25), -- Bernard
( 'gru_level_2', 25),

( 'gru_dom_recl_facilfamille', 28), -- Antoine
( 'gru_level_3', 28),

( 'gru_dom_info_stationnement', 29), -- Jules
( 'gru_level_3', 29),

( 'gru_dom_recl_facilfamille', 30), -- Vladimir
( 'gru_level_2', 30)
;

DELETE FROM core_user_right WHERE id_user >= 10 AND id_user <= 50;
INSERT INTO core_user_right ( id_user , id_right ) VALUES
( 10 , 'GRU_ADMIN_MANAGEMENT' ),
( 10 , 'GRU_DEMAND_MANAGEMENT' ),
( 10 , 'GRU_MANAGEMENT' ),
( 10 , 'GRU_DOMAIN_MANAGEMENT' ),
( 14 , 'GRU_MANAGEMENT' ),
( 15 , 'GRU_MANAGEMENT' ),
( 16 , 'GRU_MANAGEMENT' ),
( 17 , 'GRU_MANAGEMENT' ),
( 18 , 'GRU_MANAGEMENT' ),
( 19 , 'GRU_MANAGEMENT' ),
( 20 , 'GRU_MANAGEMENT' ),
( 21 , 'GRU_MANAGEMENT' ),
( 22 , 'GRU_MANAGEMENT' ),
( 23 , 'GRU_MANAGEMENT' ),
( 24 , 'GRU_MANAGEMENT' ),
( 25 , 'GRU_MANAGEMENT' ),
( 26 , 'GRU_MANAGEMENT' ),
( 27 , 'GRU_MANAGEMENT' ),
( 28 , 'GRU_MANAGEMENT' ),
( 29 , 'GRU_MANAGEMENT' ),
( 30 , 'GRU_MANAGEMENT' ),
( 10 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 14 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 15 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 16 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 17 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 18 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 19 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 20 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 21 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 22 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 23 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 24 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 25 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 26 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 27 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 28 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 29 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 30 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 10 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 14 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 15 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 16 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 17 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 18 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 19 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 20 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 21 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 22 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 23 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 24 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 25 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 26 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 27 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 28 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 29 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 30 , 'TICKETING_USER_PREFERENCES_MANAGEMENT' ),
( 21 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 22 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 23 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 24 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 25 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 27 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 30 , 'CORE_LINK_SERVICE_MANAGEMENT' ),
( 20 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 21 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 22 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 23 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 24 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 25 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 26 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 27 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 28 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 29 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' ),
( 30 , 'TICKETING_INSTANT_RESPONSE_MANAGEMENT' )
;



DELETE FROM core_admin_role_resource WHERE rbac_id > 1100 AND rbac_id < 1500;

-- RBAC permissions
INSERT INTO core_admin_role_resource (rbac_id, role_key, resource_type, resource_id, permission) VALUES
-- full right on ticketing resources for gru_admin role 
(1101, 'gru_admin', 'ticket', '*', '*'), 
(1110, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '301', '*'), -- init 
(1112, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '303', '*'), -- requalify
(1113, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '304', '*'), -- assign up
(1114, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '305', '*'), -- assign up level 3
(1115, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '306', '*'), -- assign to an other entity
(1116, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '307', '*'), -- assign to an other user
(1117, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '308', '*'), -- self assign
(1118, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '309', '*'), -- ask new informations
(1119, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '310', '*'), -- answer from agent to user (info request)
(1120, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '311', '*'), -- answer from agent to user 
(1121, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '312', '*'), -- respond  to user and close ticket
(1122, 'gru_admin', 'WORKFLOW_ACTION_TYPE', '313', '*'), -- respond assign up

(1141, 'gru_admin', 'UNIT_TYPE', '*', '*'), 
(1151, 'gru_admin', 'TICKET_DOMAIN', '*', '*'), 
(1163, 'gru_admin', 'SUPPORT_ENTITY', '*', '*'), 
(1170, 'gru_admin', 'WORKFLOW_STATE_TYPE', '*', '*'),

-- level 1 : 
(1201, 'gru_level_1', 'ticket', '*', 'CREATE'),
(1202, 'gru_level_1', 'ticket', '*', 'VIEW'),

(1210, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '301', '*'), -- init 
(1212, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '303', '*'), -- requalify
(1213, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '304', '*'), -- assign up
(1214, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '306', '*'), -- assign to an other entity
(1215, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '307', '*'), -- assign to an other user
(1216, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '308', '*'), -- self assign
(1217, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '309', '*'), -- ask new informations
(1218, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '312', '*'), -- answer to user and close ticket
(1220, 'gru_level_1', 'WORKFLOW_ACTION_TYPE', '310', '*'), -- answer from agent to user (info request)
(1240, 'gru_level_1', 'WORKFLOW_STATE_TYPE', '*', '*'), -- all workflow states

-- support entity
(1251, 'gru_level_1', 'SUPPORT_ENTITY', '1', 'VIEW'), 
(1252, 'gru_level_1', 'SUPPORT_ENTITY', '2', 'VIEW'), 
(1253, 'gru_level_1', 'SUPPORT_ENTITY', '3', 'VIEW'), 
(1254, 'gru_level_1', 'SUPPORT_ENTITY', '5', 'VIEW'), 

-- Attached files
(1271, 'gru_level_1', 'INSERT_SERVICE', 'librarylinkservice', '*'), -- add attached file
(1272, 'gru_level_1', 'INSERT_SERVICE', 'libraryuploadlinkservice', '*'), -- upload attached file
(1273, 'gru_level_1', 'DOCUMENT_SPACE', '7', '*'), -- document space image
(1274, 'gru_level_1', 'DOCUMENT_SPACE', '8', '*'), -- document space PDF
(1275, 'gru_level_1', 'DOCUMENT_SPACE', '6', '*'), -- document space uploaded files
(1276, 'gru_level_1', 'DOCUMENT_TYPE', 'image', '*'),
(1277, 'gru_level_1', 'DOCUMENT_TYPE', 'pdf', '*'),


-- level 2
(1301, 'gru_level_2', 'ticket', '*', 'CREATE'),
(1302, 'gru_level_2', 'ticket', '*', 'VIEW'),

(1310, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '301', '*'), -- init 
(1312, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '303', '*'), -- requalify
(1313, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '305', '*'), -- assign up to level 3
(1314, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '306', '*'), -- assign to an other entity
(1315, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '307', '*'), -- assign to an other user
(1316, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '308', '*'), -- self assign
(1317, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '309', '*'), -- ask new informations
(1318, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '310', '*'), -- answer from agent to user (info request)
(1319, 'gru_level_2', 'WORKFLOW_ACTION_TYPE', '312', '*'), -- answer to user and close ticket

(1340, 'gru_level_2', 'WORKFLOW_STATE_TYPE', '*', '*'), -- all workflow states

-- support entity
(1351, 'gru_level_2', 'SUPPORT_ENTITY', '4', 'VIEW'),

-- Attached files
(1371, 'gru_level_2', 'INSERT_SERVICE', 'librarylinkservice', '*'), -- add attached file
(1372, 'gru_level_2', 'INSERT_SERVICE', 'libraryuploadlinkservice', '*'), -- upload attached file
(1373, 'gru_level_2', 'DOCUMENT_SPACE', '7', '*'), -- document space image
(1374, 'gru_level_2', 'DOCUMENT_SPACE', '8', '*'), -- document space PDF
(1375, 'gru_level_2', 'DOCUMENT_SPACE', '6', '*'), -- document space uploaded files
(1376, 'gru_level_2', 'DOCUMENT_TYPE', 'image', '*'),
(1377, 'gru_level_2', 'DOCUMENT_TYPE', 'pdf', '*'),


-- level 3
(1402, 'gru_level_3', 'ticket', '*', 'VIEW'),
(1414, 'gru_level_3', 'WORKFLOW_ACTION_TYPE', '314', '*'), -- assign to an other entity
(1415, 'gru_level_3', 'WORKFLOW_ACTION_TYPE', '315', '*'), -- assign to an other user
(1416, 'gru_level_3', 'WORKFLOW_ACTION_TYPE', '316', '*'), -- self assign
(1417, 'gru_level_3', 'WORKFLOW_ACTION_TYPE', '313', '*'), -- respond assign up

(1440, 'gru_level_3', 'WORKFLOW_STATE_TYPE', '*', '*'), -- all workflow states

-- domaines
(1451, 'gru_dom_info_mairie', 'TICKET_DOMAIN', '110','VIEW'),
(1452, 'gru_dom_info_stationnement', 'TICKET_DOMAIN', '120','VIEW'),
(1453, 'gru_dom_info_autre', 'TICKET_DOMAIN', '100','VIEW'),
(1454, 'gru_dom_recl_facilfamille', 'TICKET_DOMAIN', '210','VIEW'),
(1455, 'gru_dom_recl_autre', 'TICKET_DOMAIN', '200','VIEW'),

-- User front ticketing
(1456, 'ticketing_user_front', 'WORKFLOW_ACTION_TYPE', '301','*'), -- init 
(1457, 'ticketing_user_front', 'WORKFLOW_ACTION_TYPE', '311','*'), -- respond to info request
(1458, 'ticketing_user_front', 'WORKFLOW_STATE_TYPE', '*', '*') -- all workflow states
;


--
-- Init  table core_dashboard
--
DELETE FROM core_dashboard WHERE dashboard_name='GRU';
INSERT INTO core_dashboard(dashboard_name, dashboard_column, dashboard_order) VALUES('GRU', 1, 2);

