# SpringMVC-SpringSecurity-Hibernate-JasperReports

Small web application using Spring MVC, Spring Security, Hibernate and Jasper Reports.

# SQL

CREATE TABLE users ( 
username character varying(50) NOT NULL, 
password character varying(50) NOT NULL, 
email character varying(50) NOT NULL, 
birthday date, 
sex smallint, 
enabled boolean, 
CONSTRAINT users_pkey PRIMARY KEY (username) 
)

CREATE TABLE authorities ( 
username character varying(50) NOT NULL, 
authority character varying(50) NOT NULL, 
CONSTRAINT fk_authorities_users FOREIGN KEY (username) 
REFERENCES users (username) 
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION 
)
