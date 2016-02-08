
-- core attribute initialisation already done by init_core_profile.sql script
-- INSERT INTO core_attribute (id_attribute, type_class_name, title, help_message, is_mandatory, is_shown_in_search, is_shown_in_result_list, is_field_in_line, attribute_position, plugin_name, anonymize) 
-- SELECT * FROM (SELECT IFNULL(MAX(a.id_attribute)+1,1) AS id, 'fr.paris.lutece.portal.business.user.attribute.AttributeComboBox' AS type_class_name, 'Profil' AS title, '' AS help, 0 as mandatory, 0 as search, 0 as result, 0 as field, 0 as pos, 'profiles' as plugin, NULL as anonym from core_attribute a) AS tmp
-- WHERE NOT EXISTS (
    -- SELECT type_class_name, title, plugin_name FROM core_attribute WHERE type_class_name='fr.paris.lutece.portal.business.user.attribute.AttributeComboBox' AND title = 'Profil' AND plugin_name='profiles'
-- ) LIMIT 1;

DELETE FROM core_attribute_field WHERE title like 'PROF_GRU_%';
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT IFNULL(MAX(a.id_field)+2,2)  as id_field, b.id_attribute AS id_attr, 'PROF_GRU_ADMIN' AS title, 'Administrateur GRU' as defaut, IFNULL(MAX(a.field_position)+1,1) as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL1_FacilFam' AS title, 'Agent de Niveau 1 - domaine Facil Famille' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL2_FacilFam' AS title, 'Agent de Niveau 2 - domaine Facil Famille' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL3_FacilFam' AS title, 'Agent de Niveau 3 - domaine Facil Famille' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL1_Station' AS title, 'Agent de Niveau 1 - domaine Stationnement' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL2_Station' AS title, 'Agent de Niveau 2 - domaine Stationnement' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL3_Station' AS title, 'Agent de Niveau 3 - domaine Stationnement' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL1_Mairie' AS title, 'Agent de Niveau 1 - domaine Mairie' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL2_Mairie' AS title, 'Agent de Niveau 2 - domaine Mairie' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;
INSERT INTO core_attribute_field (id_field, id_attribute, title, default_value, field_position) 
SELECT MAX(a.id_field)+1 as id_field, b.id_attribute AS id_attr, 'PROF_GRU_LEVEL3_Mairie' AS title, 'Agent de Niveau 3 - domaine Mairie' as defaut, MAX(a.field_position)+1 as pos FROM core_attribute_field as a, core_attribute as b where b.title='Profil' AND b.plugin_name='profiles' ;

