--
-- Data for table core_feature_group
--
UPDATE core_feature_group SET feature_group_order = feature_group_order + 1 WHERE id_feature_group <> "GRU";
DELETE FROM core_feature_group WHERE id_feature_group = "GRU"; 
INSERT INTO core_feature_group VALUES ('GRU','gru.features.group.gru.description','gru.features.group.gru.label',1);


--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'GRU_ADMIN_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('GRU_ADMIN_MANAGEMENT','gru.adminFeature.ManageAdminGRU.name',1,'jsp/admin/plugins/gru/ManageFeatures.jsp','gru.adminFeature.ManageAdminGRU.description',0,'gru','APPLICATIONS',NULL,NULL,4);


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
('GRU_MANAGEMENT','gru.adminFeature.ManageCustomers.name',3,'jsp/admin/plugins/gru/ManageCustomers.jsp','gru.adminFeature.ManageCustomers.description',0,'gru','GRU',NULL,NULL,4);


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
('GRU_DEMAND_MANAGEMENT','gru.adminFeature.ManageDemand.name',1,'jsp/admin/plugins/gru/ManageDemandTypes.jsp','gru.adminFeature.ManageDemand.description',0,'gru','APPLICATIONS',NULL,NULL,4);


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
('GRU_DOMAIN_MANAGEMENT','gru.adminFeature.ManageDomainGru.name',1,'jsp/admin/plugins/gru/ManageBusinessDomains.jsp','gru.adminFeature.ManageDomainGru.description',0,'gru','APPLICATIONS',NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'GRU_DOMAIN_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('GRU_DOMAIN_MANAGEMENT',1);

--
-- Init  table core_dashboard
--
DELETE FROM core_dashboard WHERE dashboard_name='GRU';
INSERT INTO core_dashboard(dashboard_name, dashboard_column, dashboard_order) VALUES('GRU', 1, 2);


--
-- Init RBAC permissions
--
INSERT INTO core_admin_role_resource (rbac_id,role_key,resource_type,resource_id,permission) (SELECT MAX(rbac_id) +1, 'super_admin','GRU_DOMAIN','*','*' FROM core_admin_role_resource);
