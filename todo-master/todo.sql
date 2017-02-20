mysql -u root -p

create database rest;
use rest;
create table user (u_id int not null auto_increment,name varchar(20) not null,user_name varchar(20) not null,password varchar(20) not null,primary key(u_id));
create table todo(todo_id int not null auto_increment,user_id int not null,message varchar(100) not null,primary key(todo_id), foreign key(user_id) references user(u_id)on delete cascade);
create table grp(id int not null auto_increment ,name varchar(20) not null,primary key(id))
create table grp_users(u_id int not null ,g_id int not null,admin_role bool not null)