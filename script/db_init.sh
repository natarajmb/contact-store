#!/bin/bash

# Common configuration properties
psqluser=contact_store
psqlpasswd=contact_store
psqldb=contact_store
psqlschema=contact_store

# Create user
createuser $psqluser && echo "CREATE ROLE"

# Create database and user as owner
createdb $psqldb -O $psqluser && echo "CREATE DATABASE"

# Alter user to add a password
psql -d "$psqldb" -c "ALTER USER $psqluser WITH PASSWORD '$psqlpasswd';"

# Create schema under the database
psql -d "$psqldb" -c "CREATE SCHEMA $psqlschema AUTHORIZATION $psqluser;"

# Grant all privileges for user on the database
psql -d "$psqldb" -c "GRANT ALL PRIVILEGES ON DATABASE $psqldb TO $psqluser;"

# Create extension uuid-ossp
psql -d "$psqldb" -c "CREATE EXTENSION IF NOT EXISTS \"pgcrypto\" with SCHEMA $psqlschema;"