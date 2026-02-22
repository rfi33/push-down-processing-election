CREATE USER election_db_manager with ENCRYPTED password '4567';

CREATE DATABASE election_db ;

GRANT CONNECT on database election_db to election_db_manager ;

\c election_db;

grant create on schema public to election_db_manager;

alter default privileges in schema public
grant select , insert ,update ,delete on tables to election_db_manager;

alter default privileges in schema public
grant usage , select ,update on sequences to election_db_manager;