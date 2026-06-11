-- DRS-Enhanced MySQL setup script
-- COIT20258 Assessment 3 - Group 2
-- Creates database tables and seed data for backend testing.

DROP DATABASE IF EXISTS drs_enhanced;
CREATE DATABASE drs_enhanced;
USE drs_enhanced;

CREATE TABLE users (
    user_id VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    account_status VARCHAR(20) NOT NULL,
    created_time DATETIME NOT NULL
);

CREATE TABLE disaster_reports (
    report_id VARCHAR(20) PRIMARY KEY,
    reporter_name VARCHAR(100) NOT NULL,
    disaster_type VARCHAR(50) NOT NULL,
    location VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    initial_severity VARCHAR(30) NOT NULL,
    report_status VARCHAR(30) NOT NULL,
    reported_time DATETIME NOT NULL
);

CREATE TABLE incidents (
    incident_id VARCHAR(20) PRIMARY KEY,
    report_id VARCHAR(20) NOT NULL,
    affected_people INT NOT NULL,
    affected_area VARCHAR(150) NOT NULL,
    severity VARCHAR(50) NOT NULL,
    priority VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    created_time DATETIME NOT NULL,
    FOREIGN KEY (report_id) REFERENCES disaster_reports(report_id)
);

CREATE TABLE incident_updates (
    update_id VARCHAR(20) PRIMARY KEY,
    incident_id VARCHAR(20) NOT NULL,
    update_notes TEXT NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    updated_status VARCHAR(50) NOT NULL,
    update_time DATETIME NOT NULL,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id)
);

CREATE TABLE emergency_resources (
    resource_id VARCHAR(20) PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    available_quantity INT NOT NULL,
    assigned_quantity INT NOT NULL,
    unavailable_quantity INT NOT NULL,
    maintenance_quantity INT NOT NULL,
    resource_status VARCHAR(30) NOT NULL
);

CREATE TABLE response_agencies (
    agency_id VARCHAR(20) PRIMARY KEY,
    agency_name VARCHAR(100) NOT NULL,
    agency_type VARCHAR(50) NOT NULL,
    contact_number VARCHAR(50) NOT NULL
);

CREATE TABLE emergency_responses (
    response_id VARCHAR(20) PRIMARY KEY,
    incident_id VARCHAR(20) NOT NULL,
    agency_id VARCHAR(20) NOT NULL,
    resource_id VARCHAR(20) NOT NULL,
    dispatch_status VARCHAR(50) NOT NULL,
    dispatch_notes TEXT,
    dispatch_time DATETIME NOT NULL,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id),
    FOREIGN KEY (agency_id) REFERENCES response_agencies(agency_id),
    FOREIGN KEY (resource_id) REFERENCES emergency_resources(resource_id)
);

CREATE TABLE evacuation_shelters (
    shelter_id VARCHAR(20) PRIMARY KEY,
    shelter_name VARCHAR(100) NOT NULL,
    location VARCHAR(150) NOT NULL,
    total_capacity INT NOT NULL,
    current_occupants INT NOT NULL,
    available_spaces INT NOT NULL,
    shelter_status VARCHAR(30) NOT NULL,
    last_updated DATETIME NOT NULL
);

CREATE TABLE public_alerts (
    alert_id VARCHAR(20) PRIMARY KEY,
    incident_id VARCHAR(20),
    alert_type VARCHAR(50) NOT NULL,
    affected_area VARCHAR(150) NOT NULL,
    severity_level VARCHAR(30) NOT NULL,
    alert_message TEXT NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    created_time DATETIME NOT NULL,
    alert_status VARCHAR(30) NOT NULL,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id)
);

CREATE TABLE audit_logs (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    action_type VARCHAR(100) NOT NULL,
    action_details TEXT NOT NULL,
    action_time DATETIME NOT NULL
);

INSERT INTO users VALUES
('USR001','System Administrator','admin@drs.local','admin','admin123','SYSTEM_ADMINISTRATOR','ACTIVE',NOW()),
('USR002','Emergency Control Centre','ecc@drs.local','ecc','ecc123','EMERGENCY_CONTROL_CENTRE','ACTIVE',NOW()),
('USR003','Public User','public@drs.local','public','public123','PUBLIC_USER','ACTIVE',NOW());

INSERT INTO emergency_resources VALUES
('RES001','Ambulance Unit','MEDICAL',5,5,0,0,0,'AVAILABLE'),
('RES002','Fire Truck','FIRE',4,4,0,0,0,'AVAILABLE'),
('RES003','Rescue Boat','RESCUE',2,2,0,0,0,'AVAILABLE'),
('RES004','Relief Food Kit','SUPPLY',200,200,0,0,0,'AVAILABLE');

INSERT INTO response_agencies VALUES
('AG001','Fire and Emergency Services','FIRE_RESPONSE','000'),
('AG002','Queensland Ambulance Service','MEDICAL_RESPONSE','000'),
('AG003','Queensland Police Service','SECURITY_RESPONSE','000');

INSERT INTO evacuation_shelters VALUES
('SH001','Brisbane Community Hall','Brisbane CBD',200,45,155,'AVAILABLE',NOW()),
('SH002','North Side Relief Centre','Chermside',150,130,20,'NEAR_CAPACITY',NOW()),
('SH003','Southbank Safety Centre','South Brisbane',100,100,0,'FULL',NOW()),
('SH004','Mango Hill Community Centre','Mango Hill',120,30,90,'AVAILABLE',NOW()),
('SH005','Redcliffe Emergency Shelter','Redcliffe',180,170,10,'NEAR_CAPACITY',NOW()),
('SH006','Logan Relief Centre','Logan Central',160,0,160,'AVAILABLE',NOW());

INSERT INTO disaster_reports VALUES
('R001','Test Public User','FIRE','Brisbane CBD','Fire reported near a public building.','HIGH','REPORTED',NOW()),
('R002','Public User','FLOOD','South Bank','Flooding reported near river walkway.','MEDIUM','REPORTED',NOW()),
('R003','Maria Santos','STORM','Mango Hill','Strong winds damaged trees and blocked a local road.','MEDIUM','REPORTED',NOW()),
('R004','John Miller','FIRE','Fortitude Valley','Smoke seen from a commercial building near Brunswick Street.','CRITICAL','REPORTED',NOW()),
('R005','Aileen Cruz','FLOOD','Chermside','Water level rising near residential streets after heavy rain.','HIGH','REPORTED',NOW()),
('R006','Michael Tan','EARTHQUAKE','Logan Central','Minor building cracks reported after ground movement.','LOW','REPORTED',NOW()),
('R007','Sarah Wilson','LANDSLIDE','Mount Coot-tha','Soil movement reported near walking track access road.','HIGH','REPORTED',NOW()),
('R008','David Lee','HURRICANE','Redcliffe','Severe wind warning reported near coastal properties.','CRITICAL','REPORTED',NOW());

INSERT INTO incidents VALUES
('INC001','R001',25,'Brisbane CBD','HIGH','HIGH','REGISTERED',NOW()),
('INC002','R002',12,'South Bank','MEDIUM','MEDIUM','ASSESSED',NOW()),
('INC003','R003',8,'Mango Hill','MEDIUM','LOW','REGISTERED',NOW()),
('INC004','R004',75,'Fortitude Valley','CRITICAL','EMERGENCY','DISPATCHED',NOW()),
('INC005','R005',40,'Chermside','HIGH','HIGH','ONGOING',NOW()),
('INC006','R006',3,'Logan Central','LOW','LOW','RESOLVED',NOW()),
('INC007','R007',18,'Mount Coot-tha','HIGH','HIGH','REGISTERED',NOW()),
('INC008','R008',95,'Redcliffe','CRITICAL','EMERGENCY','DISPATCHED',NOW());

INSERT INTO incident_updates VALUES
('U001','INC001','Initial incident registered from disaster report.','Emergency Control Centre','REGISTERED',NOW()),
('U002','INC002','Flood incident assessed and priority confirmed.','Emergency Control Centre','ASSESSED',NOW()),
('U003','INC004','Fire response team dispatched to the affected building.','Emergency Control Centre','DISPATCHED',NOW()),
('U004','INC005','Flood response is ongoing and road access is being monitored.','Emergency Control Centre','ONGOING',NOW()),
('U005','INC006','Minor earthquake report resolved after inspection.','Emergency Control Centre','RESOLVED',NOW()),
('U006','INC008','Emergency coastal response dispatched to Redcliffe.','Emergency Control Centre','DISPATCHED',NOW());

INSERT INTO emergency_responses VALUES
('ER001','INC001','AG001','RES002','DISPATCHED','Fire response team dispatched to Brisbane CBD.',NOW()),
('ER002','INC004','AG001','RES002','DISPATCHED','Fire truck dispatched to Fortitude Valley commercial building.',NOW()),
('ER003','INC005','AG003','RES003','DISPATCHED','Rescue boat placed on standby for flood response in Chermside.',NOW()),
('ER004','INC008','AG002','RES001','DISPATCHED','Ambulance unit dispatched to Redcliffe evacuation point.',NOW());

INSERT INTO public_alerts VALUES
('AL001','INC001','Fire Alert','Brisbane CBD','HIGH','Avoid the affected area and follow emergency service instructions.','Emergency Control Centre',NOW(),'PUBLISHED'),
('AL002','INC002','Flood Warning','South Bank','MEDIUM','Use alternate routes near South Bank due to flooding.','Emergency Control Centre',NOW(),'PUBLISHED'),
('AL003','INC004','Fire Alert','Fortitude Valley','CRITICAL','Emergency services are responding to a building fire. Avoid Brunswick Street.','Emergency Control Centre',NOW(),'PUBLISHED'),
('AL004','INC005','Flood Warning','Chermside','HIGH','Residents near low-lying streets should monitor updates and prepare to move if advised.','Emergency Control Centre',NOW(),'DRAFT'),
('AL005','INC008','Evacuation Warning','Redcliffe','CRITICAL','Coastal residents should move to higher ground and follow official evacuation advice.','Emergency Control Centre',NOW(),'PUBLISHED'),
('AL006','INC006','Shelter Update','Logan Central','LOW','The Logan Relief Centre remains available for residents needing support.','Emergency Control Centre',NOW(),'EXPIRED');

INSERT INTO audit_logs(username, action_type, action_details, action_time) VALUES
('system','DATABASE_INITIALISED','Default DRS-Enhanced seed data inserted.',NOW()),
('admin','LOGIN_SUCCESS','System administrator logged in during testing.',NOW()),
('ecc','REGISTER_INCIDENT','Incident INC001 registered from disaster report R001.',NOW()),
('ecc','CREATE_PUBLIC_ALERT','Public alert AL001 created for incident INC001.',NOW()),
('ecc','DISPATCH_RESPONSE','Emergency response ER001 dispatched for incident INC001.',NOW()),
('public','SUBMIT_DISASTER_REPORT','Public user submitted disaster report R002.',NOW());