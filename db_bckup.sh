#!/usr/bin/bash

#there should exist a seqno file with "1" in it initially in the folder where the backups will be stored.
#run this file as a cron job for every hour

typeset -i seqno=$(cat /srshome/pdict_db_backup/seqno)

chmod 777 -R /home/pdict/postgres_data
cp -r /home/pdict/postgres_data /srshome/pdict_db_backup/postgres_data$seqno

echo $((seqno+1)) > /srshome/pdict_db_backup/seqno