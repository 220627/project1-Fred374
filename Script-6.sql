CREATE TABLE ers_reimbursements (
	reimb_id			serial PRIMARY KEY,
	reimb_amount		int,
	reimb_submitted		timestamp,
	reimb_resolved		timestamp,
	reimb_description	TEXT,
	users_author_fk		int REFERENCES ers_users(users_id),
	users_resolver_fk	int REFERENCES ers_users(users_id),
	reimb_status_fk		int REFERENCES ers_reimbursement_statuses(reimb_status_id),
	reimb_type_fk		int	REFERENCES ers_reimbursement_types(reimb_type_id),
	reimb_resolution	int REFERENCES ers_reimbursement_resolutions(reimb_res_id),
	reimb_receipt		bytea
);

CREATE TABLE ers_reimbursement_resolutions (
	reimb_res_id		serial PRIMARY KEY,
	reimb_res			TEXT
);

CREATE TABLE ers_reimbursement_statuses (
	reimb_status_id		serial PRIMARY KEY,
	reimb_status		TEXT
);

CREATE TABLE ers_reimbursement_types (
	reimb_type_id		serial PRIMARY KEY,
	reimb_type			TEXT
);

CREATE TABLE ers_users (
	users_id			serial PRIMARY KEY,
	users_username		TEXT UNIQUE,
	users_password		TEXT,
	users_first_name	TEXT,
	users_last_name		TEXT,
	users_email			TEXT UNIQUE,
	users_role_fk		int REFERENCES ers_user_roles(user_role_id)
);

CREATE TABLE ers_user_roles (
	user_role_id		serial PRIMARY KEY,
	user_role			TEXT
);

INSERT INTO ers_reimbursement_statuses(reimb_status) VALUES ('status test 1'),(' status test 2');
INSERT INTO ers_reimbursement_types (reimb_type) VALUES ('type test 1'), ('type test 2');
INSERT INTO ers_user_roles (user_role) VALUES ('ADMIN'), ('USER');
INSERT INTO ers_users (users_username, users_password, users_first_name, users_last_name, users_email, users_role_fk) VALUES ('ADMIN', '123robot', 'admin', 'admin', 'admin@reimb.db', 1), ('user test 1', 'password', 'user', 'test 1', 'user@reimb.db', 2);
INSERT INTO ers_reimbursement_resolutions(reimb_res) VALUES ('ACCEPTED'), ('DENIED');
INSERT INTO ers_reimbursements (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, users_author_fk, users_resolver_fk, reimb_status_fk, reimb_type_fk, reimb_resolution, reimb_receipt) VALUES (100, current_timestamp, NULL, 'test reimb 1', 2, NULL, 1, 2, null, pg_read_binary_file('K:\Work Files\project_1\project1-Fred374\imgs\receipt.jpg'));

SELECT * FROM ers_reimbursements;

SELECT * FROM ers_reimbursement_resolutions;

select * from ers_users where users_username = 'ADMIN';

select * from pg_catalog.pg_user;

alter user postgres with superuser;

UPDATE ers_reimbursements SET reimb_resolved = current_timestamp, users_resolver_fk = 1, reimb_resolution = 1 WHERE reimb_id = 1
