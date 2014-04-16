# Tasks schema
 
# --- !Ups

CREATE SEQUENCE item_id_seq;
CREATE TABLE item (
    id integer NOT NULL DEFAULT nextval('item_id_seq'),
    label varchar(255),
    vote integer  ,
    item_group integer 
);
 
# --- !Downs
 
DROP TABLE item;
DROP SEQUENCE item_id_seq;