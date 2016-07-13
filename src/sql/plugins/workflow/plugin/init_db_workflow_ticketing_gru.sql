DELETE FROM workflow_task WHERE id_action >= 300 AND id_action < 400 ;
DELETE FROM workflow_action WHERE id_workflow >= 300 AND id_workflow < 400;
DELETE FROM workflow_state WHERE id_workflow >= 300 AND id_workflow < 400;
DELETE FROM workflow_workflow WHERE id_workflow >= 300 AND id_workflow < 400; 
INSERT INTO workflow_workflow (id_workflow, name, description, creation_date, is_enabled, workgroup_key) 
	VALUES	(301,'Workflow GRU','Workflow GRU','2016-01-13 08:36:34',1,'all');

INSERT INTO workflow_state (id_state, name, description, id_workflow, is_initial_state, is_required_workgroup_assigned, id_icon, display_order) 
	VALUES	(301,'Nouveau','Nouveau',301,1,0,NULL,1),
			(303,'A traiter','A traiter',301,0,0,NULL,2),
			(304,'En attente de compléments par l''usager','En attente de compléments par l''usager',301,0,0,NULL,3),
			(305,'Escaladé niveau 3','Le ticket est escaladé vers un agent de niveau 3',301,0,0,NULL,4),
			(306,'Traité','Traité',301,0,0,NULL,5);

INSERT INTO workflow_action (id_action, name, description, id_workflow, id_state_before, id_state_after, id_icon, is_automatic, is_mass_action, display_order, is_automatic_reflexive_action) 
	VALUES 	(301,'Initialisation','Initialisation de la sollicitation',301,301,303,1,1,0,1,0),
			(303,'Requalifier','Requalification de la sollicitation',301,303,303,1,0,0,2,0),
            (304,'Escalader','Escalade vers un agent de niveau 2',301,303,303,1,0,0,6,0),
            (305,'Escalader','Escalade vers un agent de niveau 3',301,303,305,1,0,0,7,0),
			(306,'Assigner à une autre entité','Assignation de la sollicitation à une autre entité',301,303,303,1,0,0,5,0),
            (307,'Assigner à un autre agent','Assignation de la sollicitation à un autre agent',301,303,303,1,0,0,4,0),
			(308,'Me l''assigner','Prise en charge de la sollicitation',301,303,303,1,0,0,3,0),
			(309,'Demander compléments','Demande d''informations complémentaires à l''usager',301,303,304,1,0,0,8,0),
			(310,'Répondre pour l''usager','Réponse à la place de l''usager',301,304,303,1,0,0,9,0),
			(311,'Répondre (usager)','Réponse de l''usager à une demande d''informations',301,304,303,1,0,0,10,0),
            (312,'Répondre à l''usager','Réponse finale à l''usager',301,303,306,1,0,0,11,0),
            (313,'Répondre à l''escalade', 'Réponse à l''escalade', 301, 305, 303, 1, 0, 0, 12, 0),
            (314,'Assigner à un autre agent','Assignation de la sollicitation à un autre agent',301,305,305,1,0,0,4,0), -- assignation a autre agent pour tickets escaladés
			(315,'Me l''assigner','Prise en charge de la sollicitation',301,305,305,1,0,0,3,0) -- auto assignation pour tickets escaladés
;
		
INSERT INTO workflow_task (id_task, task_type_key, id_action, display_order) 
	VALUES 	(301,'taskTicketingGenerateTicketReference',301,1), -- Initialize
			(302,'taskTicketingAssignUnitLinkedToCategory',301,2),
			(303, 'taskAutomaticAssignment',301,3),
			(304,'taskTicketingCreateCustomer',301,4),
            (305,'taskNotifyGru',301,5),
            (306, 'taskTicketingIndexTicket',301,6),
            (307, 'taskTicketingRegisterChannel',301,7),
            (341,'taskTicketingQualifyTicket',303,1), -- Qualify
            (342,'taskTicketingModifyTicketCategory',303,2),
            (343, 'taskTypeComment', 303,3),
            (344,'taskNotifyGru',303,4),
            (345, 'taskTicketingIndexTicket',303,5),
            (346, 'taskTicketingMarkAsUnread',303,6),
            (351,'taskTicketingAssignTicketToUnit',306,1), -- Assign to unit
            (352, 'taskTypeComment', 306,2),
            (353,'taskNotifyGru',306,3),
            (354, 'taskTicketingMarkAsUnread',306,4),
            (361,'taskTicketingAssignTicketToUser',307,1), -- Assign to user
            (362, 'taskTypeComment', 307,2),
            (363,'taskNotifyGru',307,3),
            (364, 'taskTicketingMarkAsUnread',307,4),
            (371,'taskTicketingAssignTicketToMe',308,1), -- Assign to me
            (372, 'taskTypeComment', 308,2),
            (373,'taskNotifyGru',308,3),
			(381,'taskTicketingAssignUpTicket',304,1), -- Assign up
			(382, 'taskTypeComment', 304,2),
            (383,'taskNotifyGru',304,3),
            (384, 'taskTicketingMarkAsUnread',304,4),
            (386,'taskTicketingAssignUpTicket',305,1), -- Assign up to level 3
			(387, 'taskTypeComment', 305,2),
            (388,'taskNotifyGru',305,3),
            (389, 'taskTicketingMarkAsUnread',305,4),
            (390, 'taskTicketingEditTicket', 309,1), -- Ask for user information
            (392,'taskNotifyGru',309,2),
            (393, 'taskTicketingIndexTicket',309,3),
            (400, 'taskTicketingEditTicket', 310,1), -- Reply to info request from agent to agent
            (401, 'taskTicketingSelectChannel', 310,2),
            (402,'taskNotifyGru',310,3),
            (403, 'taskTicketingIndexTicket', 310,4),
            (404, 'taskTicketingMarkAsUnread',310,5),
            (410, 'taskTicketingEditTicket',311,1), -- Reply to info request from user to agent
            (411, 'taskTicketingSelectChannel',311,2),
            (412,'taskNotifyGru',311,3),
            (413, 'taskTicketingIndexTicket', 311,4),
            (414, 'taskTicketingMarkAsUnread',311,5),
			(420, 'taskTicketingReply', 312,1), -- Reply to user
            (422,'taskNotifyGru',312,2),
            (423, 'taskTicketingIndexTicket', 312,3),
            (441, 'taskTicketingReplyAssignUpTicket',313,1), -- Reply to assign up
            (442, 'taskTypeComment', 313,2),
            (443,'taskNotifyGru',313,3),
            (444, 'taskTicketingMarkAsUnread',313,4),
            (461,'taskTicketingAssignTicketToUser',314,1), -- Assign to user LEVEL3
            (462, 'taskTypeComment', 314,2),
            (463,'taskNotifyGru',314,3),
            (464, 'taskTicketingMarkAsUnread',314,4),
            (471,'taskTicketingAssignTicketToMe',315,1), -- Assign to me LEVEL3
            (472, 'taskTypeComment', 315,2),
            (473,'taskNotifyGru',315,3)
;

DELETE FROM workflow_task_comment_config WHERE id_task >= 300 AND id_task < 500;			
INSERT INTO workflow_task_comment_config (id_task, title, is_mandatory, is_richtext) 
	VALUES	(343, 'Commentaire', 0, 1),
			(352, 'Commentaire', 0, 1),
			(362, 'Commentaire', 0, 1),
			(372, 'Commentaire', 0, 1),
			(382, 'Commentaire', 0, 1),
			(387, 'Commentaire', 0, 1),
            (442, 'Commentaire', 0, 1),
            (462, 'Commentaire', 0, 1),
			(472, 'Commentaire', 0, 1)
;

DELETE FROM workflow_task_ticketing_edit_ticket_config;
INSERT INTO workflow_task_ticketing_edit_ticket_config (id_task, message_direction, id_user_edition_action) 
    VALUES  (390, 1, 311),  -- Ask for user information
            (400, 0, 310), -- Reply to agent from agent
            (410, 0, 311) -- Reply to agent from user
;
			
DELETE FROM workflow_task_ticketing_reply_config;
INSERT INTO workflow_task_ticketing_reply_config (id_task, message_direction) 
    VALUES  (420, 1) -- Reply to user
;
	
DELETE FROM workflow_task_notify_gru_cf WHERE id_task >= 300 AND id_task <= 473;
INSERT INTO `workflow_task_notify_gru_cf` (`id_task`, `id_spring_provider`, `demand_status`, `set_onglet`, `message_guichet`, `status_text_guichet`, `sender_name_guichet`, `subject_guichet`, `demand_max_step_guichet`, `demand_user_current_step_guichet`, `is_active_onglet_guichet`, `status_text_agent`, `message_agent`, `is_active_onglet_agent`, `subject_email`, `message_email`, `sender_name_email`, `recipients_cc_email`, `recipients_cci_email`, `is_active_onglet_email`, `message_sms`, `is_active_onglet_sms`, `id_mailing_list_broadcast`, `sender_name_broadcast`, `subject_broadcast`, `message_broadcast`, `recipients_cc_broadcast`, `recipients_cci_broadcast`, `is_active_onglet_broadcast`) VALUES
(445, 'notifygru-appointment.ProviderService.@.1', 0, 3, '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Pour des raisons ind&eacute;pendantes de notre volont&eacute;, nous sommes contraints d''annuler votre rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment}.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande <a title="Demande d erendez-vous" href="jsp/site/Portal.jsp?page=appointment&amp;view=getAppointmentFormFirstStep&amp;id_form=1">ici</a></p>\r\n<p>En vous remerciant de votre compr&eacute;hension.</p>\r\n<p>Maire de Paris.</p>', 'Rendez-vous ${reference} annulé', 'Mairie de Paris', 'Votre rendez-vous ${reference} est annulé', 1, 1, 1, 'Rendez-vous ${reference} annulé par l''usager', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Pour des raisons ind&eacute;pendantes de notre volont&eacute;, nous sommes contraints d''annuler votre rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment}.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande <a href="${url_cancel}">ici</a></p>\r\n<p>En vous remerciant de votre compr&eacute;hension.</p>\r\n<p>Maire de Paris.</p>', 1, 'Votre rendez-vous ${reference} a été annulé', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Pour des raisons ind&eacute;pendantes de notre volont&eacute;, nous sommes contraints d''annuler votre rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment}.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande <a href="${url_cancel}">ici</a></p>\r\n<p>En vous remerciant de votre compr&eacute;hension.</p>\r\n<p>Maire de Paris.</p>', 'Mairie de Paris', '', '', 1, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, 0),
(448, 'notifygru-appointment.ProviderService.@.1', 0, 3, '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Votre demande de&nbsp;rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment} a bien &eacute;t&eacute; annul&eacute;e.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande ici</p>\r\n<p>Maire de Paris.</p>', 'Rendez-vous annulé à votre demande', 'Mairie de Paris', 'Rendez-vous ${reference} annulé à votre demande', 1, 1, 1, 'Rendez-vous annulé par l''usager', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Votre demande de&nbsp;rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment} a bien &eacute;t&eacute; annul&eacute;e.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande ici</p>\r\n<p>Maire de Paris.</p>', 1, 'Annulation de votre demande de rendez-vous ${reference}', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Votre demande de&nbsp;rendez-vous ${reference} du ${date_appointment} &agrave; ${time_appointment} a bien &eacute;t&eacute; annul&eacute;e.</p>\r\n<p>Nous vous invitons &agrave; renouveller votre demande ici</p>\r\n<p>Maire de Paris.</p>', 'Mairie de Paris', '', '', 1, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, 0),
(443, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} escalad&eacute;e a &eacute;t&eacute; retourn&eacute;e &agrave; l''agent demandeur.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(444, 'notifygru-appointment.ProviderService.@.1', 0, 3, '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande de rendez-vous ${reference} le ${date_appointment} &agrave; ${time_appointment} avec les services de la Ville.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}.</p>\r\n<p>Voic les informations que vous nous avez communiqu&eacute;es</p>\r\n<p>${recap}</p>\r\n<p>Pour annuler le rendez-vous ${url_cancel}</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'Votre demande de rendez-vous ${reference}', 'Mairie de Paris', 'Votre demande de rendez-vous ${reference}', 1, 1, 1, 'Nouvelle demande de rendez-vous ${reference}', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande de rendez-vous ${reference} le ${date_appointment} &agrave; ${time_appointment} avec les services de la Ville.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}.</p>\r\n<p>Voic les informations que vous nous avez communiqu&eacute;es</p>\r\n<p>${recap}</p>\r\n<p>Pour annuler le rendez-vous ${url_cancel}</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>', 1, 'Votre demande de rendez-vous ${reference}', '<p>Bonjour ${firstName} ${lastName},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande de rendez-vous ${reference} le ${date_appointment} &agrave; ${time_appointment} avec les services de la Ville.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}.</p>\r\n<p>Voic les informations que vous nous avez communiqu&eacute;es</p>\r\n<p>${recap}</p>\r\n<p>Pour annuler le rendez-vous ${url_cancel}</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>', 'Mairie de Paris', '', '', 1, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, 0),
(422, 'notifygru-ticketing.ProviderService', 1, 0, '<p>${user_message}</p>', 'Traité', 'Mairie de Paris', 'Réponse à votre demande ${reference}', 4, 3, 1, 'Traité', '<p>${user_message}</p>', 1, 'Réponse à votre demande ${reference}', '<p>${user_message}</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, Nous avons répondu à votre demande ${reference}. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Réponse à votre demande ${reference}', '<p>${user_message}</p>', '', '', 1),
(402, 'notifygru-ticketing.ProviderService', 0, 0, '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Réception de vos information complémentaires, demande ${reference}', 4, 1, 1, 'A traiter', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 1, 'Réception de vos information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, Nous avons bien reçu vos informations complémentaires pour votre demande ${reference}. Nous allons traiter votre demande dans les plus brefs délais. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Réception de vos information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', '', '', 1),
(412, 'notifygru-ticketing.ProviderService', 0, 0, '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Réception de vos information complémentaires, demande ${reference}', 4, 1, 1, 'A traiter', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 1, 'Réception de vos information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, Nous avons bien reçu vos informations complémentaires pour votre demande ${reference}. Nous allons traiter votre demande dans les plus brefs délais. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Réception de vos information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u vos informations compl&eacute;mentaires pour votre demande ${reference} et nous vous en remercions.</p>\r\n<p>Nous allons traiter votre demande dans les plus brefs d&eacute;lais.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', '', '', 1),
(344, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; requalifi&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(353, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; assign&eacutee &agrave; une autre entit&eacute;.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(363, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; assign&eacutee &agrave; un autre agent.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(463, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; assign&eacutee &agrave; un autre agent.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(373, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} vous a &eacute;t&eacute; assign&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(473, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} vous a &eacute;t&eacute; assign&eacute;e.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(383, 'notifygru-ticketing.ProviderService', 0, 0, NULL, NULL, NULL, NULL, 4, 1, 0, 'A traiter', '<p>La sollicitation ${reference} a &eacute;t&eacute; transmise &agrave; l''entit&eacute; de support.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(388, 'notifygru-ticketing.ProviderService', 0, 0, 'En cours de traitement', NULL, NULL, NULL, 4, 1, 0, 'En attente retour niveau 3', '<p>La sollicitation ${reference} a &eacute;t&eacute; transmise &agrave; l''entit&eacute; de support.</p>', 1, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, 0),
(392, 'notifygru-ticketing.ProviderService', 0, 0, '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris</p>', 'En attente de compléments', 'Mairie de Paris', 'Demande d''information complémentaires, demande ${reference}', 4, 2, 1, 'En attente de compléments par l''usager', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris</p>', 1, 'Demande d''information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, votre demande ${reference} est en attente d''informations complémentaires de votre part. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Demande d''information complémentaires, demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Votre demande ${reference} est en attente d''informations compl&eacute;mentaires de votre part. <a href=''${url_completed}''>Cliquez ici</a> pour acc&eacute;der &agrave; votre demande.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris</p>', '', '', 1),
(305, 'notifygru-ticketing.ProviderService', 0, 0, '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d''avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'En cours de traitement', 'Mairie de Paris', 'Votre demande ${reference}', 4, 1, 1, 'Nouveau', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d''avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 1, 'Votre demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d''avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', 'Mairie de Paris', '', '', 1, 'Bonjour ${firstname} ${lastname}, votre demande ${reference} est bien reçue. Cordialement. Mairie de Paris', 1, 1, 'Mairie de Paris', 'Votre demande ${reference}', '<p>Bonjour ${firstname} ${lastname},</p>\r\n<p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>\r\n<p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l''adresse suivante ${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d''avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>\r\n<p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>\r\n<p>Cordialement,</p>\r\n<p>Mairie de Paris.</p>', '', '', 1);

DELETE FROM core_datastore WHERE entity_key LIKE 'ticketing.configuration.%';
INSERT INTO `core_datastore` (`entity_key`, `entity_value`) VALUES
('ticketing.configuration.workflow.id', '301'),
('ticketing.configuration.states.selected', '303'),
('ticketing.configuration.states.selected.for.role.gru_level_3', '305'),
('ticketing.configuration.state.id.closed', '306'),
('ticketing.configuration.actions.filtered.when.assigned.to.me', '308;315'),
('ticketing.configuration.adminUser.id.front', '5'),
('ticketing.configuration.channel.id.front', '99')
;
