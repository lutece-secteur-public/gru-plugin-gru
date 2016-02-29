DELETE FROM workflow_workflow WHERE id_workflow >= 300 AND id_workflow < 400; 
INSERT INTO workflow_workflow (id_workflow, name, description, creation_date, is_enabled, workgroup_key) 
	VALUES	(301,'Workflow GRU','Workflow GRU','2016-01-13 08:36:34',1,'all');

DELETE FROM workflow_state WHERE id_workflow >= 300 AND id_workflow < 400;
INSERT INTO workflow_state (id_state, name, description, id_workflow, is_initial_state, is_required_workgroup_assigned, id_icon, display_order) 
	VALUES	(301,'Nouveau','Nouveau',301,1,0,NULL,1),
			(302,'A qualifier','A qualifier',301,0,0,NULL,2),
			(303,'A traiter','A traiter',301,0,0,NULL,3),
			(304,'En attente d\'informations de l\'usager','En attente d\'informations de l\'usager',301,0,0,NULL,4),
			(305,'Clos','Clos',301,0,0,NULL,5);

DELETE FROM workflow_action WHERE id_workflow >= 300 AND id_workflow < 400;
INSERT INTO workflow_action (id_action, name, description, id_workflow, id_state_before, id_state_after, id_icon, is_automatic, is_mass_action, display_order, is_automatic_reflexive_action) 
	VALUES 	(301,'Initialisation','Initialisation de la sollicitation',301,301,302,1,1,0,1,0),
			(302,'Qualifier','Qualifier',301,302,303,1,0,0,2,0),
			(303,'Requalifier','Requalifier',301,303,303,1,0,0,3,0),
            (304,'Escalader','Escalader',301,303,303,1,0,0,7,0),
			(305,'Assigner à une autre entité','Assigner une sollicitation à une autre entité',301,303,303,1,0,0,6,0),
            (306,'Assigner à un autre agent','Assigner une sollicitation à un autre agent',301,303,303,1,0,0,5,0),
			(307,'Me l\'assigner','M\'assigner une sollicitation',301,303,303,1,0,0,4,0),
			(308,'Demander des informations complémentaires à l\'usager','Demander des informations complémentaires à l\'usager',301,303,304,1,0,0,8,0),
			(309,'Répondre','Action de répondre pour l\'usager',301,304,303,1,0,0,9,0),
            (310,'Répondre','Action de répondre pour l\'agent',301,303,305,1,0,0,10,0),            
			(311, 'Ré-ouvrir la sollicitation', 'Réouverture d\'une sollicitation close', 301, 305, 303, 1, 0, 0, 11, 0),
            (312, 'Répondre à l\'escalade', 'Répondre à l\'escalade', 301, 303, 303, 1, 0, 0, 12, 0);
		
DELETE FROM workflow_task WHERE id_action >= 300 AND id_action < 450 AND id_task != 303 ;
INSERT INTO workflow_task (id_task, task_type_key, id_action, display_order) 
	VALUES 	(301,'taskTicketingGenerateTicketReference',301,1),
			(302,'taskTicketingAssignUnitLinkedToCategory',301,2),
			-- 303 is reserved by module ticketing gru 	(303,'taskTicketingCreateCustomer',301,3)
            (304,'taskNotifyGru',301,3),
            (321,'taskTicketingQualifyTicket',302,1),
            (322,'taskTicketingAssignTicketToUnit',302,2),
			(323,'taskTicketingAssignTicketToUser',302,3),
            (324,'taskTicketingModifyTicketCategory',302,4),
			(325, 'taskTypeComment', 302,5), 
            (326,'taskNotifyGru',302,6),
            (341,'taskTicketingQualifyTicket',303,1),
            (342,'taskTicketingModifyTicketCategory',303,2),
            (343, 'taskTypeComment', 303,3),
            (344,'taskNotifyGru',303,4),
            (351,'taskTicketingAssignTicketToUnit',305,1),
            (352, 'taskTypeComment', 305,2),
            (353,'taskNotifyGru',305,3),
            (361,'taskTicketingAssignTicketToUser',306,1),
            (362, 'taskTypeComment', 306,2),
            (363,'taskNotifyGru',306,3),
            (371,'taskTicketingAssignTicketToMe',307,1),
            (372, 'taskTypeComment', 307,2),
            (373,'taskNotifyGru',307,3),
			(381,'taskTicketingAssignUpTicket',304,1),
			(382, 'taskTypeComment', 304,2),
            (383,'taskNotifyGru',304,3),
            (390, 'taskTicketingEditTicket', 308,1), -- Ask for user information
            (392,'taskNotifyGru',308,2),
            (400, 'taskTicketingEditTicket', 309,1), -- Reply to agent
            (402,'taskNotifyGru',309,2),
			(420, 'taskTicketingReply', 310,1), -- Reply to user
            (422,'taskNotifyGru',310,2),
			(431, 'taskTicketingQualifyTicket', 311, 1), 
			(432, 'taskTicketingAssignTicketToUnit', 311, 2), 
			(433, 'taskTicketingModifyTicketCategory', 311, 3), 
			(434, 'taskTypeComment', 311, 4),
            (435,'taskNotifyGru',311,5),
            (441, 'taskTicketingReplyAssignUpTicket',312,1),
            (442, 'taskTypeComment', 312,2),
            (443,'taskNotifyGru',312,3)
;

DELETE FROM workflow_task_comment_config WHERE id_task >= 300 AND id_task < 450;			
INSERT INTO workflow_task_comment_config (id_task, title, is_mandatory) 
	VALUES	(325, 'Commentaire', 0),
			(343, 'Commentaire', 0),
			(352, 'Commentaire', 0),
			(362, 'Commentaire', 0),
			(372, 'Commentaire', 0),
			(382, 'Commentaire', 0),
			(434, 'Motif de ré-ouverture', 1),
            (442, 'Commentaire', 0)
;

DELETE FROM workflow_task_ticketing_edit_ticket_config;
INSERT INTO workflow_task_ticketing_edit_ticket_config (id_task, message_direction, id_user_edition_action, default_message) 
    VALUES  (390, 1, 309, "Bonjour,
Merci de compléter les champs suivants.
En vous remerciant par avance,
Mairie de Paris"),  -- Ask for user information
            (400, 0, 309, "") -- Reply to agent
;
			
DELETE FROM workflow_task_ticketing_reply_config;
INSERT INTO workflow_task_ticketing_reply_config (id_task, message_direction) 
    VALUES  (420, 1) -- Reply to user
;
	
DELETE FROM workflow_task_notify_gru_cf;
INSERT INTO `workflow_task_notify_gru_cf` (`id_task`, `id_spring_provider`, `demand_status`, `set_onglet`, `message_guichet`, `status_text_guichet`, `sender_name_guichet`, `subject_guichet`, `demand_max_step_guichet`, `demand_user_current_step_guichet`, `is_active_onglet_guichet`, `status_text_agent`, `message_agent`, `is_active_onglet_agent`, `subject_email`, `message_email`, `sender_name_email`, `recipients_cc_email`, `recipients_cci_email`, `is_active_onglet_email`, `message_sms`, `is_active_onglet_sms`, `id_mailing_list_broadcast`, `sender_name_broadcast`, `subject_broadcast`, `message_broadcast`, `recipients_cc_broadcast`, `recipients_cci_broadcast`, `is_active_onglet_broadcast`) VALUES
(303, 'notifygru-ticketing.ProviderService', 1, 0, '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p> <p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d''avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p> <p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Votre demande', 4, 1, 1, 'Nouveau', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre sollicitation est bien re&ccedil;ue.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 1, 'Votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre sollicitation est bien re&ccedil;ue.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, votre demande est bien reçue. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre sollicitation est bien re&ccedil;ue.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', '', '', 1),
(325, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; qualifi&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(344, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; requalifi&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(353, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; assign&eacutee &agrave; une autre entit&eacute;.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(363, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; assign&eacutee &agrave; un autre agent.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(373, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} vous a &eacute;t&eacute; assign&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(383, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; transmise &agrave; l''entit&eacute; de support.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(392, 'notifygru-ticketing.ProviderService', 1, 0, '<p>Bonjour ${firstname} ${lastname},</p> <p>Une demande d''informations compl&eacute;mentaires vous a &eacute;t&eacute; envoy&eacute;e &agrave; l''adresse suivante ${email}. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'En attente de compléments', 'Mairie de Paris', 'Demande d''information complémentaires', 4, 2, 1, 'En attente de compléments par l''usager', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 1, 'Demande d''information complémentaires', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, votre demande ${reference} est en attente d''informations complémentaires de votre part. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Demande d''information complémentaires', '<p>Bonjour ${firstname} ${lastname},</p> <p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', '', '', 1),
(402, 'notifygru-ticketing.ProviderService', 1, 0, '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires et nous vous en remercions.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Réception de vos information complémentaires', 4, 1, 1, 'A traiter', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 1, 'Réception de vos information complémentaires', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, Nous avons bien reçu vos informations complémentaires pour votre demande ${reference}. Nous allons traiter votre demande dans les plus brefs délais. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Réception de vos information complémentaires', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', '', '', 1),
(422, 'notifygru-ticketing.ProviderService', 1, 0, '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons r&eacute;pondu &agrave; votre demande.</p> <p>Un e-mail vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}.</p> <p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'Traité', 'Mairie de Paris', 'Réponse à votre demande', 4, 3, 1, 'Traité', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons r&eacute;pondu &agrave; votre demande ${reference}. Cliquez ici pour voir la r&eacute;ponse.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 1, 'Réponse à votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons r&eacute;pondu &agrave; votre demande ${reference}. Cliquez ici pour voir la r&eacute;ponse.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, Nous avons répondu à votre demande ${reference}. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Réponse à votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons r&eacute;pondu &agrave; votre demande ${reference}. Cliquez ici pour voir la r&eacute;ponse.</p> <p>Cordialement,</p> <p>Mairie de Paris</p>', '', '', 1),
(435, 'notifygru-ticketing.ProviderService', 1, 0, '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u la r&eacute;-ouverture de votre demande.</p> <p>Un e-mail vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Ré-ouverture de votre demande', 4, 1, 1, 'A traiter', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u la r&eacute;-ouverture de votre demande ${reference}.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 1, 'Ré-ouverture de votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u la r&eacute;-ouverture de votre demande ${reference}.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, nous avons bien reçue la ré-ouverture de votre demande ${reference}. Nous allons traiter votre demande dans les plus brefs délais. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Ré-ouverture de votre demande', '<p>Bonjour ${firstname} ${lastname},</p> <p>Nous avons bien re&ccedil;u la r&eacute;-ouverture de votre demande ${reference}.</p> <p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p> <p>Cordialement,</p> <p>Mairie de Paris.</p>', '', '', 1),
(443, 'notifygru-ticketing.ProviderService', 1, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} escalad&eacute;e a &eacute;t&eacute; retourn&eacute;e &agrave; l''agent demandeur.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0)
;
