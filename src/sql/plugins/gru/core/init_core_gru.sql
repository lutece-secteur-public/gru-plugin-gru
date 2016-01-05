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
-- Data for table core_admin_role
--
DELETE FROM core_admin_role WHERE role_key LIKE 'gru_%';
INSERT INTO core_admin_role ( role_key , role_description ) VALUES 
( 'gru_admin' ,'GRU - Administrateur' ),
( 'gru_level_1' ,'GRU - Agent niveau 1' ),
( 'gru_level_2' ,'GRU - Agent niveau 2' );

--
-- Data for table core_admin_role_resource
--
DELETE FROM core_admin_role_resource WHERE rbac_id >= 400 AND rbac_id <= 404;
INSERT INTO core_admin_role_resource ( rbac_id , role_key, resource_type, resource_id, permission ) VALUES 
( 400 , 'gru_admin' , 'GRU_FEATURE' , '*' , '*' ),
( 401 , 'gru_admin' , 'GRU_DEMAND_ACTION' , '*' , '*' ),
( 402 , 'gru_level_1' ,'GRU_FEATURE' , '*' , '*' ),
( 403 , 'gru_level_2' , 'GRU_FEATURE' , '*' , '*' ),
( 404 , 'gru_level_2' , 'GRU_DEMAND_ACTION' , '*' , '*' );

DELETE FROM core_admin_user WHERE id_user >= 10 AND id_user <= 12;
INSERT INTO core_admin_user VALUES 
(10,'gruadmin','GRU','Administrateur','gruadmin@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(11,'gru1','GRU niveau 1','Agent','gru1@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all'),
(12,'gru2','GRU niveau 2','Agent','gru2@lutece.fr',0,'grudemo','fr',0,0,0,null,null,0,'1980-01-01 00:00:00','all');

DELETE FROM core_user_role WHERE role_key LIKE 'gru_%';
INSERT INTO core_user_role ( role_key , id_user ) VALUES
( 'gru_admin' , 1 ),
( 'gru_admin' , 10 ),
( 'gru_level_1' , 11 ),
( 'gru_level_2' , 12 );

DELETE FROM core_user_right WHERE id_user >= 10 AND id_user <= 12;
INSERT INTO core_user_right ( id_user , id_right ) VALUES
( 10 , 'GRU_ADMIN_MANAGEMENT' ),
( 10 , 'GRU_DEMAND_MANAGEMENT' ),
( 10 , 'GRU_MANAGEMENT' ),
( 11 , 'GRU_MANAGEMENT' ),
( 12 , 'GRU_MANAGEMENT' ),

( 10 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 11 , 'TICKETING_TICKETS_MANAGEMENT' ),
( 12 , 'TICKETING_TICKETS_MANAGEMENT' );