--
-- Data for table gru_customer
--

INSERT INTO gru_customer (id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes) VALUES 
(1,1,'Maurice','Dupont',1,'mdupont@domain.com','3121231','mdupont@domain.com',1,'0677889911',0,NULL);


--
-- Data for table gru_demand_type
--

INSERT INTO gru_demand_type ( id_demand_type, demand_type_id ,title ) VALUES
(1,100,'Carte de stationnement'),
(2,101,'Demande d\'information'),
(3,102,'Réclamation'),
(4,103,'RDV Urbanisme');


--
-- Data for table gru_demand_type_action
--

INSERT INTO gru_demand_type_action ( id_demand_type_action, id_demand_type, action_label, action_link ) VALUES
( 1 , 2 , 'Gérer la demande' , 'jsp/admin/plugins/ticketing/ManageTickets.jsp?view=modifyTicket&id={id}' ),
( 2 , 3 , 'Gérer la réclamation' , 'jsp/admin/plugins/ticketing/ManageTickets.jsp?view=modifyTicket&id={id}' ),
( 3 , 4 , 'Accéder au RDV' , 'jsp/admin/plugins/appointment/ManageAppointments.jsp?view=viewAppointment&id_appointment={id}' ),
( 4 , 1 , 'Faire une réclamation' , 'jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket&cat=DVDSSVP&fn={firstname}&ln={lastname}&guid={guid}&ph={phone}&em={email}');


--
-- Data for table gru_feature_category
--

INSERT INTO gru_feature_category (id_feature_category, name, description, id_order, category_icon, color ) VALUES 
(1,'Espace Public','Services numériques concernant l\'espace publique (voirie, parc et jardins, ...)',1,'street-view', 'purple'),
(2,'Sollicitations','Demandes d\'information ou réclamations',2,'comment', 'yellow'),
(3,'Voirie','Demandes concernant le stationnement',3,'car', 'lime'),
(4,'Urbanisme','Demandes d\'urbanisme',4,'building', 'olive');
--
-- Data for table gru_feature
--

INSERT INTO gru_feature ( id_feature, name, link, link_customer_params, target, id_category, id_order, display_level ) VALUES 
(1,'Enlèvement d\'encombrants','https://teleservices.paris.fr/sira/jsp/site/Portal.jsp?page=formengine&form=ramen','',0,1,1,1),
(2,'Signalement DansMaRue','https://teleservices.paris.fr/sira/jsp/site/Portal.jsp?page=formengine&form=signalement','',0,1,2,1),
(3,'Créer une sollicitation','jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket','&fn={firstname}&ln={lastname}&guid={guid}&ph={phone}&em={email}',1,2,1,1),
(4,'Prendre un RDV Urbanisme ','https://teleservices.paris.fr/rdvdu/','',0,4,1,1),
(5,'Réclamation Carte stationnement ','jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket&cat=DVDSSVP','&fn={firstname}&ln={lastname}&guid={guid}&ph={phone}&em={email}',0,3,2,1),
(6,'Prendre un RDV Stationnement','https://teleservices.paris.fr/rdvssvp/','',0,3,1,1);

