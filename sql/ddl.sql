/*
 변경 내역 관리 가능
*/

drop table if exists member CASCADE;
create table member
(
id bigint generated by default as identity,
name varchar(255),
primary key (id)
);