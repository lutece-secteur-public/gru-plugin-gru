
--
-- Structure for table gru_customer
--

DROP TABLE IF EXISTS gru_customer;
CREATE TABLE gru_customer (
id_customer int(11) NOT NULL,
id_title int(11) NOT NULL default '0',
firstname varchar(50) NOT NULL default '',
lastname varchar(50) NOT NULL default '',
has_account SMALLINT NOT NULL default '0',
account_login varchar(50) NOT NULL default '',
account_guid varchar(50) NOT NULL default '',
email varchar(255) NOT NULL default '',
is_email_verified SMALLINT NOT NULL default '0',
mobile_phone varchar(50) NOT NULL default '',
is_mobile_phone_verified SMALLINT NOT NULL default '0',
extras_attributes long varchar NULL ,
PRIMARY KEY (id_customer)
);

--
-- Structure for table gru_customer_extras_attributes
--

DROP TABLE IF EXISTS gru_customer_extras_attributes;
CREATE TABLE gru_customer_extras_attributes (
id_extras_attributes int(6) NOT NULL,
attribute_key varchar(50) NOT NULL default '',
name varchar(50) NOT NULL default '',
description varchar(255) NOT NULL default '',
PRIMARY KEY (id_extras_attributes)
);


--
-- Structure for table gru_demand_type
--

DROP TABLE IF EXISTS gru_demand_type;
CREATE TABLE gru_demand_type (
id_demand_type int(6) NOT NULL,
demand_type_id int(11) NOT NULL default '0',
title varchar(255) NOT NULL default '',
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
