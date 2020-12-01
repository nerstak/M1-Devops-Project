-- You need to be connected to postgres (the DB) to execute this part
drop database IF EXISTS st2eedb ; -- No need to put everything in capital, it is not case sensitive here
CREATE DATABASE st2eedb OWNER adm ENCODING DEFAULT CONNECTION LIMIT -1;


