#!/bin/bash
set -e

mysql -u root -p$MYSQL_ROOT_PASSWORD <<-EOSQL
    CREATE DATABASE IF NOT EXISTS keycloak CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EOSQL
