create table twei_user(
	group_id varchar2(10),
	userid varchar2(20),
	user_name varchar2(20),
	password varchar2(20),
	email varchar2(50),
	create_at date,
	last_login_at date,
	update_at date
)

create table twei_role(
	role_id varchar2(10),
	role_name varchar2(30),
	create_at date,
	create_by varchar2(20),
	role_parent_id varchar2(10),
	role_level number,
	role_desc varchar2(500)
)

create table twei_auth(
	auth_id varchar2(10),
	auth_name varchar2(50),
	auth_parent_id varchar2(10),
	auth_desc varchar2(200),
	auth_type number,
	create_at date,
	create_by varchar2(20)
)

create table twei_organization{
	organization_id varchar2(10),
	organization_name varchar2(50),
	organization_desc varchar2(200),
	organization_parent_id varchar2(10),
	organization_level number,
	create_at date,
	create_by varchar2(20)
)

create table twei_user_role(
	ur_id number,
	userid varchar2(20),
	role_id varchar2(10)
)

create table twei_organization_role(
	or_id number,
	organization_id varchar2(10),
	role_id varchar2(10)
)

create table twei_role_auth(
	ra_id number,
	role_id varchar2(10),
	auth_id varchar2(10)
)

create table twei_app_log(
	log_id number,
	log_type number,
	log_content varchar2(4000),
	log_at date,
	log_by varchar2(20)
)