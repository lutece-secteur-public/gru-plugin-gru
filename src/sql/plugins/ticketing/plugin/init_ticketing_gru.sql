DELETE FROM ticketing_ticket_form ;
INSERT INTO ticketing_ticket_form (id_form, title, description) VALUES
(1, "Facil'Familles - Formulaire problème tarifaire petite enfance", "Formulaire du domaine Facil'Familles - Formulaire de la problématique 'problème tarifaire petite enfance'"), 
(2, "Facil'Familles - Problèmes tarifaires périscolaire", "Formulaire du domaine Facil'Familles - Formulaire de la problématique  'Problèmes tarifaires périscolaire'"), 
(3, "Facil'Familles - Autre", "Formulaire du domaine Facil'Familles - problématique 'Autre'");

DELETE FROM ticketing_ticket_type ;
INSERT INTO ticketing_ticket_type (id_ticket_type, label, reference_prefix) VALUES
(1, "Demande d'information", "INF"),
(2, "Réclamation", "RCL");

DELETE FROM ticketing_ticket_domain ;
INSERT INTO ticketing_ticket_domain (id_ticket_domain, id_ticket_type, label) VALUES
(100, 1, "Autre" ),
(110, 1, "Mairie" ),
(120, 1, "Stationnement" ),
(200, 2, "Autre" ),
(210, 2, "Facil'familles" );

DELETE FROM ticketing_ticket_category ;
INSERT INTO ticketing_ticket_category (id_ticket_category, id_ticket_domain, label, category_code, id_ticket_form, id_workflow, id_unit ) VALUES
 -- affecte a MIB
(1, 100, "Autre" , NULL, 0, 301, 11 ),
-- affecte a MIB
(2, 110, "Réservation de salle" , NULL, 0, 301, 11 ), 
-- affecte a MIB
(3, 110, "Autre" , NULL, 0, 301, 11 ), 
-- affecte a MIB
(4, 120, "Horaires de stationnement" , "DVDSSVP", 0, 301, 11 ),
-- affecte a 3975
(5, 120, "Autre" , NULL, 0, 301, 10 ), 
-- affecte a MIB
(6, 200, "Autre" , NULL, 0, 301, 11 ), 
-- affecte a section tarifaire peri
(7, 210, "Problème tarifaire périscolaire" , "FFTARIFPERISCO", 2, 301, 20 ), 
 -- affecte a section tarifaire peri
(8, 210, "Problème tarifaire petite enfance" , "FFTARIFPE", 1, 301, 21 ),
-- affecte a MIB
(9, 210, "Autre" , NULL, 3, 301, 11 ); 

DELETE FROM ticketing_user_title ;
INSERT INTO ticketing_user_title (id_user_title, label) VALUES
(1, "M."),
(2, "Mme");

DELETE FROM ticketing_contact_mode ;
INSERT INTO ticketing_contact_mode (id_contact_mode, label, confirmation_msg) VALUES
(1, "E-mail", "<p>Bonjour&nbsp;${userTitle} ${lastName},</p>
 <p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>
 <p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l'adresse suivante&nbsp;${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d'avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>
 <p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>
 <p>Cordialement,</p>
 <p>Mairie de Paris.</p>"),
(2, "Téléphone fixe", "<p>Bonjour&nbsp;${userTitle} ${lastName},</p>
 <p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>
 <p>Un e-mail de confirmation vous a &eacute;t&eacute; envoy&eacute; &agrave; l'adresse suivante&nbsp;${email}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d'avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>
 <p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>
 <p>Cordialement,</p>
 <p>Mairie de Paris.</p>"),
(3, "Téléphone portable", "<p>Bonjour&nbsp;${userTitle} ${lastName},</p>
 <p>Nous avons bien re&ccedil;u votre demande et nous vous remercions de votre confiance.</p>
 <p>Un sms de confirmation vous a &eacute;t&eacute; envoy&eacute; au num&eacute;ro suivant&nbsp;${mobilePhoneNumber}. Il contient un num&eacute;ro de suivi qui vous sera demand&eacute; au 3975 pour suivre son &eacute;tat d'avancement. Il est &eacute;galement disponible dans votre espace Compte Parisien.</p>
 <p>Nous restons &agrave; votre enti&egrave;re disposition pour toute information compl&eacute;mentaire.</p>
 <p>Cordialement,</p>
 <p>Mairie de Paris.</p>");
 
DELETE FROM ticketing_support_entity ;
INSERT INTO ticketing_support_entity (id_support_entity, name, level, id_unit, id_admin_user, id_domain) VALUES
(1, 'SN1 Facil Famille ', 1, 2, -1, 210), 
(2, 'SN2 Facil Famille tarif. Petite Enfance. ', 2, 21, -1, 210), 
(3, 'SN2 Facil Famille tarif. périscolaire', 3, 20, -1, 210),
(4, 'SN3 DFPE ', 3, 4, -1, 210);