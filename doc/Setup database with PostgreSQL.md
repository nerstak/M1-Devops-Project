# Setup database with PostgreSQL

For the first time, you need to follow every step. After that, you'll only need to run scripts located in `database/scripts`. Note that if you have trouble running a script because of multiple connections, restart the database.

## By hand

### User setup

You need to create a user (`adm`) to interact with the database.  We assume that you know how to run script to PostgreSQL engine through the shell or the IDE.

Run the script `User_Setup.sql` while being connected to Postgres database.

### Database setup

The creation of the database is separated in two scripts, because they will not be executed in the same database.

### Database in itself

Run the script `DB_create.sql` while being connected to Postgres database.

### DDL

Run the script `JEE_db.sql` while being connected to st2eedb database.

### Populate

Run the script `Populate.sql` while being connected to st2eedb database.

## Using a shell script

If you are using an Unix system, or WSL, you can use the script `database/scripts/run.sh` every time you need to set up the database.

## Using a script with Docker

If you are using the official image of [PostgreSQL]([Docker Hub](https://hub.docker.com/_/postgres/)) and working on an Unix system, you can use the script `database/send.sh` to set up the database. 

Note that the name of the docker container is setup to `pg-docker` in this script.