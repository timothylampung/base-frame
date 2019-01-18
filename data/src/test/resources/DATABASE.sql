-- postgres
DROP DATABASE IF EXISTS spotit;
DROP USER IF EXISTS spotit;
CREATE USER spotit WITH PASSWORD 'spotit';
CREATE DATABASE spotit WITH ENCODING 'UTF8' LC_COLLATE ='C' LC_CTYPE ='C' TEMPLATE =template0;
GRANT ALL PRIVILEGES ON DATABASE spotit to spotit;


