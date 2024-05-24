#!/bin/bash

PGPASSWORD='MultiDBS' psql -h slon.felk.cvut.cz -d saframa9 -U saframa9 -W -c "\copy public.accessory(type, isbn) FROM '/home/glycerolveinz/Documents/Coding/Cvut/DBS/musicStoreSemestral/cp03/accessory_data.csv' DELIMITER ',' CSV HEADER;"
