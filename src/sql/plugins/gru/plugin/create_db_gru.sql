

--
-- Structure for table gru_demand_type
--

DROP TABLE IF EXISTS gru_demand_type;
CREATE TABLE gru_demand_type (
id_demand_type int(6) NOT NULL,
demand_type_id int(11) NOT NULL default '0',
title varchar(255) NOT NULL default '',
id_business_domain int(6) NOT NULL,
PRIMARY KEY (id_demand_type)
);

--
-- Structure for table gru_demand_type_action
--

DROP TABLE IF EXISTS gru_demand_type_action;
CREATE TABLE gru_demand_type_action (
id_demand_type_action int(6) NOT NULL,
action_link varchar(255) NOT NULL default '',
action_label varchar(50) NOT NULL default '',
id_demand_type int(11) NOT NULL default '0',
PRIMARY KEY (id_demand_type_action)
);


--
-- Structure for table gru_business_sector
--

DROP TABLE IF EXISTS gru_business_sector;
CREATE TABLE gru_business_sector (
id_business_sector int(6) NOT NULL,
name varchar(50) NOT NULL default '',
description varchar(255) NOT NULL default '',
PRIMARY KEY (id_business_sector)
);

--
-- Structure for table gru_business_domain
--

DROP TABLE IF EXISTS gru_business_domain;
CREATE TABLE gru_business_domain (
id_business_domain int(6) NOT NULL,
id_business_sector int(11) NOT NULL default '0',
name varchar(50) NOT NULL default '',
description varchar(255) NOT NULL default '',
PRIMARY KEY (id_business_domain)
);


--
-- Structure for table gru_feature_category
--

DROP TABLE IF EXISTS gru_feature_category;
CREATE TABLE gru_feature_category (
id_feature_category int(6) NOT NULL,
name varchar(50) NOT NULL default '',
description varchar(255) NOT NULL default '',
id_order int(11) NOT NULL default '0',
category_icon varchar(255) NOT NULL default '',
color varchar(50) NOT NULL default '',
PRIMARY KEY (id_feature_category)
);

--
-- Structure for table gru_feature
--

DROP TABLE IF EXISTS gru_feature;
CREATE TABLE gru_feature (
id_feature int(6) NOT NULL,
name varchar(50) NOT NULL default '',
link varchar(255) NOT NULL default '',
link_customer_params varchar(255) NOT NULL default '',
target int(11) NOT NULL default '0',
id_category int(11) NOT NULL default '0',
id_order int(11) NOT NULL default '0',
display_level int(11) NOT NULL default '0',
PRIMARY KEY (id_feature)
);
