--
-- Data for table gru_customer
--
DELETE FROM gru_customer ;
INSERT INTO gru_customer (id_customer, id_title, firstname, lastname, has_account, account_login, account_guid, email, is_email_verified, mobile_phone, is_mobile_phone_verified, extras_attributes) VALUES 
(1,1,'Maurice','Dupont',1,'mdupont@domain.com','3121231','mdupont@domain.com',1,'0677889911',0,NULL);


--
-- Data for table gru_demand_type
--
DELETE FROM gru_demand_type ;
INSERT INTO gru_demand_type ( id_demand_type, demand_type_id ,title, id_business_domain ) VALUES
(1,100,'Carte de stationnement', 2 ),
(2,101,'Demande d\'information', 5 ),
(3,102,'Réclamation', 5 ),
(4,103,'RDV Urbanisme', 4 );


--
-- Data for table gru_demand_type_action
--
DELETE FROM gru_demand_type_action  ;
INSERT INTO gru_demand_type_action ( id_demand_type_action, id_demand_type, action_label, action_link ) VALUES
( 1 , 2 , 'Gérer la demande' , 'jsp/admin/plugins/ticketing/TicketView.jsp?id={id}' ),
( 2 , 3 , 'Gérer la réclamation' , 'jsp/admin/plugins/ticketing/TicketView.jsp?id={id}' ),
( 3 , 4 , 'Accéder au RDV' , 'jsp/admin/plugins/appointment/ManageAppointments.jsp?view=viewAppointment&id_appointment={id}' ),
( 4 , 1 , 'Faire une réclamation' , 'jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket&cat=DVDSSVP&fn={firstname}&ut={user_title}&cid={cid}&ln={lastname}&guid={guid}&ph={phone}&em={email}');


--
-- Data for table gru_feature_category
--
DELETE FROM gru_feature_category  ;
INSERT INTO gru_feature_category (id_feature_category, name, description, id_order, category_icon, color ) VALUES 
(1,'Espace Public','Services numériques concernant l\'espace publique (voirie, parc et jardins, ...)',1,'street-view', 'purple'),
(2,'Sollicitations','Demandes d\'information ou réclamations',2,'comment', 'aqua'),
(3,'Voirie','Demandes concernant le stationnement',3,'car', 'blue'),
(4,'Urbanisme','Demandes d\'urbanisme',4,'building', 'teal'),
(5,'Famille','Demandes relatives à la famille',5,'child', 'navy');
--
-- Data for table gru_feature
--
DELETE FROM gru_feature  ;
INSERT INTO gru_feature ( id_feature, name, link, link_customer_params, target, id_category, id_order, display_level ) VALUES 
(1,'Enlèvement d\'encombrants','https://teleservices.paris.fr/sira/jsp/site/Portal.jsp?page=formengine&form=ramen','',0,1,1,1),
(2,'Signalement DansMaRue','https://teleservices.paris.fr/sira/jsp/site/Portal.jsp?page=formengine&form=signalement','',0,1,2,1),
(3,'Créer une sollicitation','jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket','&ut={user_title}&cid={cid}&fn={firstname}&ln={lastname}&guid={guid}&ph={phone}&em={email}&cid={cid}',1,2,1,1),
(4,'Prendre un RDV Urbanisme ','https://teleservices.paris.fr/rdvdu/','',0,4,1,1),
(5,'Réclamation Carte stationnement ','jsp/admin/plugins/ticketing/ManageTickets.jsp?view=createTicket&cat=DVDSSVP','&ut={user_title}&cid={cid}&fn={firstname}&ln={lastname}&guid={guid}&ph={phone}&em={email}&cid={cid}',0,3,2,1),
(6,'Prendre un RDV Stationnement','https://teleservices.paris.fr/rdvssvp/','',0,3,1,1),
(7,'Motif d\'appel ou de visite','jsp/admin/plugins/ticketing/ManageInstantResponses.jsp?view=createInstantResponse','',1,2,2,1)
;


DELETE FROM gru_business_sector;
INSERT INTO gru_business_sector ( id_business_sector , name, description ) VALUES
( 1 , 'Fiscalité' , 'Taxe ou redevance d\'enlèvement des ordures ménagères, taxe de séjour, etc.'),
( 2, 'Travail et social' , 'Gestion des aides sociales (demande, attribution et suivi) dans les domaines suivants :
allocation personnalisée d\'autonomie, revenu de solidarité active, etc.'),
( 3, 'Santé' ,'Protection maternelle et infantile, plan d\'alerte et sauvegarde de la population, etc.' ),
( 4, 'Transports' ,'Inscription, suivi et paiement en ligne des prestations, scolaires ou municipales, de transports
individuels ou en commun (vélo, voiture, autobus, etc.) ; informations sur les conditions de circulation, etc.'),
( 5, 'Etat civil et citoyenneté' , 'Demande d\'extraits ou de copies d\'actes de l\'état civil ; inscription sur les listes
électorales ; etc.' ),
( 6, 'Relations avec les élus' , 'Communication municipale ; relations des usagers avec les élus ; etc.' ),
( 7, 'Scolaire, périscolaire, sport et culture', 'Gestion des dossiers (inscription,
suivi et paiement en ligne) dans les domaines suivants : prestations touristiques ; centre de vacances, école,
bibliothèque, etc.'),
( 8, 'Economie et urbanisme', 'Demande de locaux professionnels ; gestion des dossiers (demande, attribution, suivi
et paiement en ligne) dans les domaines suivants : eau-assainissement, permis de construire, etc.'),
( 9 , 'Polices spéciales et voirie' , 'Autorisation temporaire de débit de boissons ; accès aux voies piétonnes ; etc.'),
( 10 , 'Relations avec les usagers' , 'Relation des usagers avec les services ; exercice des droits " Informatique et
Libertés " (demande d\'information, d\'accès, de rectification, suppression, etc.).');

INSERT INTO gru_business_domain ( id_business_domain, id_business_sector , name, description ) VALUES
( 1 , 7 , 'Facil\'Familles', 'Facil\'Familles' ),
( 2 , 9 , 'Stationnement' , 'Stationnement' ),
( 3 , 5 , 'Demandes d\'actes d\'état civil', 'Demandes d\actes d\'état civil : naissance, mariage, décès.' ),
( 4 , 8 , 'Demande d\'urbanisme' ,'Permis de construire, autorisation de travaux, enseignes' ),
( 5 , 10 ,  'Demande Usager', 'Relation usager : demande d\'information ou réclamation');
