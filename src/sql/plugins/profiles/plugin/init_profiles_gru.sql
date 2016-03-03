DELETE FROM profile_profile WHERE profile_key like 'PROF_GRU%';
INSERT INTO profile_profile (profile_key, profile_description) VALUES
('PROF_GRU_ADMIN', 'Administrateur GRU'),
('PROF_GRU_LEVEL1_FacilFam', 'Agent de Niveau 1 - domaine Facil Famille '),
('PROF_GRU_LEVEL2_FacilFam', 'Agent de Niveau 2 - domaine Facil Famille '),
('PROF_GRU_LEVEL3_FacilFam', 'Agent de Niveau 3 - domaine Facil Famille '),
('PROF_GRU_LEVEL1_Station', 'Agent de Niveau 1 - domaine Stationnement'),
('PROF_GRU_LEVEL2_Station', 'Agent de Niveau 2 - domaine Stationnement'),
('PROF_GRU_LEVEL3_Station', 'Agent de Niveau 3 - domaine Stationnement'),
('PROF_GRU_LEVEL1_Mairie', 'Agent Mairie de Niveau 1'),
('PROF_GRU_LEVEL2_Mairie', 'Agent Mairie de Niveau 2'),
('PROF_GRU_LEVEL3_Mairie', 'Agent Mairie de Niveau 3')
;

DELETE FROM profile_right WHERE profile_key like 'PROF_GRU%';
INSERT INTO profile_right(profile_key, id_right) VALUES
-- admin
('PROF_GRU_ADMIN', 'GRU_ADMIN_MANAGEMENT'),
('PROF_GRU_ADMIN', 'GRU_DEMAND_MANAGEMENT'),
('PROF_GRU_ADMIN', 'GRU_MANAGEMENT'),
('PROF_GRU_ADMIN', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_ADMIN', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),

-- dom facil famille
('PROF_GRU_LEVEL1_FacilFam', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL1_FacilFam', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL1_FacilFam', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL2_FacilFam', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL2_FacilFam', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL2_FacilFam', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL3_FacilFam', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL3_FacilFam', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL3_FacilFam', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),

-- dom stationnement
('PROF_GRU_LEVEL1_Station', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL1_Station', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL1_Station', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL2_Station', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL2_Station', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL2_Station', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL3_Station', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL3_Station', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL3_Station', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),

-- dom mairie
('PROF_GRU_LEVEL1_Mairie', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL1_Mairie', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL1_Mairie', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL2_Mairie', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL2_Mairie', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL2_Mairie', 'TICKETING_USER_PREFERENCES_MANAGEMENT'),
('PROF_GRU_LEVEL3_Mairie', 'GRU_MANAGEMENT'),
('PROF_GRU_LEVEL3_Mairie', 'TICKETING_TICKETS_MANAGEMENT'),
('PROF_GRU_LEVEL3_Mairie', 'TICKETING_USER_PREFERENCES_MANAGEMENT');

DELETE FROM profile_role WHERE profile_key like 'PROF_GRU%';
INSERT INTO profile_role (profile_key, role_key) VALUES
-- admin
('PROF_GRU_ADMIN', 'gru_admin'),
-- dom facil famille
('PROF_GRU_LEVEL1_FacilFam', 'gru_level_1'),
('PROF_GRU_LEVEL1_FacilFam', 'gru_dom_recl_facilfamille'),
('PROF_GRU_LEVEL2_FacilFam', 'gru_level_2'),
('PROF_GRU_LEVEL2_FacilFam', 'gru_dom_recl_facilfamille'),
('PROF_GRU_LEVEL3_FacilFam', 'gru_level_3'),
('PROF_GRU_LEVEL3_FacilFam', 'gru_dom_recl_facilfamille'),

-- dom stationnement
('PROF_GRU_LEVEL1_Station', 'gru_level_1'),
('PROF_GRU_LEVEL1_Station', 'gru_dom_info_stationnement'),
('PROF_GRU_LEVEL2_Station', 'gru_level_2'),
('PROF_GRU_LEVEL2_Station', 'gru_dom_info_stationnement'),
('PROF_GRU_LEVEL3_Station', 'gru_level_3'),
('PROF_GRU_LEVEL3_Station', 'gru_dom_info_stationnement'),

-- dom mairie
('PROF_GRU_LEVEL1_Mairie', 'gru_level_1'),
('PROF_GRU_LEVEL1_Mairie', 'gru_dom_info_mairie'),
('PROF_GRU_LEVEL2_Mairie', 'gru_level_2'),
('PROF_GRU_LEVEL2_Mairie', 'gru_dom_info_mairie'),
('PROF_GRU_LEVEL3_Mairie', 'gru_level_3'),
('PROF_GRU_LEVEL3_Mairie', 'gru_dom_info_mairie')
;


DELETE FROM profile_user WHERE profile_key like 'PROF_GRU%';
INSERT INTO profile_user (profile_key, id_user) VALUES
('PROF_GRU_ADMIN', 1 ),
('PROF_GRU_ADMIN', 10 ),
('PROF_GRU_LEVEL1_FacilFam',  21), -- Alexia
('PROF_GRU_LEVEL2_FacilFam',  23), -- Laura
('PROF_GRU_LEVEL2_FacilFam',  24), -- Maxime
('PROF_GRU_LEVEL3_FacilFam', 26), -- Victor
('PROF_GRU_LEVEL1_Mairie' , 27), -- Marie
('PROF_GRU_LEVEL1_Mairie', 20), -- Dominique 
('PROF_GRU_LEVEL1_Mairie', 22), -- Guillaume
('PROF_GRU_LEVEL2_Station', 25), -- Bernard
('PROF_GRU_LEVEL3_FacilFam', 28), -- Antoine
('PROF_GRU_LEVEL3_Station', 29), -- Jules
('PROF_GRU_LEVEL2_FacilFam', 30) -- Vladimir
;

