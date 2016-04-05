
-- core attribute initialisation already done by init_core_profile.sql script
-- INSERT INTO core_attribute (id_attribute, type_class_name, title, help_message, is_mandatory, is_shown_in_search, is_shown_in_result_list, is_field_in_line, attribute_position, plugin_name, anonymize) 
-- SELECT * FROM (SELECT IFNULL(MAX(a.id_attribute)+1,1) AS id, 'fr.paris.lutece.portal.business.user.attribute.AttributeComboBox' AS type_class_name, 'Profil' AS title, '' AS help, 0 as mandatory, 0 as search, 0 as result, 0 as field, 0 as pos, 'profiles' as plugin, NULL as anonym from core_attribute a) AS tmp
-- WHERE NOT EXISTS (
    -- SELECT type_class_name, title, plugin_name FROM core_attribute WHERE type_class_name='fr.paris.lutece.portal.business.user.attribute.AttributeComboBox' AND title = 'Profil' AND plugin_name='profiles'
-- ) LIMIT 1;

DELETE FROM core_attribute_field WHERE title like 'PROF_GRU_%';
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,2)  as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_ADMIN' AS title, 'Administrateur GRU' as defaut, IFNULL(IFNULL(MAX(a.field_position)+1,1),2) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,3) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL1_FacilFam' AS title, 'Agent de Niveau 1 - domaine Facil Famille' as defaut, IFNULL(IFNULL(MAX(a.field_position)+1,1),3) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,4) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL2_FacilFam' AS title, 'Agent de Niveau 2 - domaine Facil Famille' as defaut, IFNULL(IFNULL(MAX(a.field_position)+1,1),4) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,5) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL3_FacilFam' AS title, 'Agent de Niveau 3 - domaine Facil Famille' as defaut, IFNULL(MAX(a.field_position)+1,5) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,6) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL1_Station' AS title, 'Agent de Niveau 1 - domaine Stationnement' as defaut, IFNULL(MAX(a.field_position)+1,6) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,7) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL2_Station' AS title, 'Agent de Niveau 2 - domaine Stationnement' as defaut, IFNULL(MAX(a.field_position)+1,7) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,8) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL3_Station' AS title, 'Agent de Niveau 3 - domaine Stationnement' as defaut, IFNULL(MAX(a.field_position)+1,8) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,9) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL1_Mairie' AS title, 'Agent de Niveau 1 - domaine Mairie' as defaut, IFNULL(MAX(a.field_position)+1,9) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,10) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL2_Mairie' AS title, 'Agent de Niveau 2 - domaine Mairie' as defaut, IFNULL(MAX(a.field_position)+1,10) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+1,11) as id_field, IFNULL(b.id_attribute,1) AS id_attr, 'PROF_GRU_LEVEL3_Mairie' AS title, 'Agent de Niveau 3 - domaine Mairie' as defaut, IFNULL(MAX(a.field_position)+1,11) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;

DELETE FROM core_admin_user_field WHERE user_field_value like 'PROF_GRU_%';
-- gruadmin 10
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 10, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_ADMIN';
-- Dominique 20
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 20, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL1_Mairie';
-- Alexia 21
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 21, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL1_FacilFam';
-- Guillaume 22
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 22, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL1_Mairie';
-- Laura 23
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 23, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL2_FacilFam';
-- Maxime 24
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 24, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL2_FacilFam';
-- Bernard 25
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 25, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL2_Station';
-- Victor 26
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 26, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL3_FacilFam';
-- Marie 27
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 27, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL1_Mairie';
-- Antoine 28
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 28, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL3_FacilFam';
-- Jules 29
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 29, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL3_Station';
-- Vladimir 30
INSERT INTO core_admin_user_field (id_user_field, id_user, id_attribute, id_field, id_file, user_field_value) 
SELECT IFNULL(MAX(a.id_user_field)+1,1)  as id_user_field, 30, b.id_attribute AS id_attribute, b.id_field as id_field, NULL, b.title FROM core_admin_user_field a, core_attribute_field b WHERE b.title='PROF_GRU_LEVEL2_FacilFam';

