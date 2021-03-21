#!/bin/bash

#run this file in a screen
#1) screen -S <screenname> ...for creating the screen
#2) ./deploy_loop.sh       ...for running the script
#3) ctrl+a+d               ...for detaching from the screen

function block_for_change {
  inotifywait --event modify,create /home/pdict/deploy/iplpredict.jar
  sleep 3
}

exec -a pdictprocess java -jar iplpredict.jar localhost 8080 &
while block_for_change; do
  echo "----JAR CHANGED----"
  pgrep -f pdictprocess | xargs kill -SIGTERM
  echo "----KILLED JAVA PROCESS----"
  exec -a pdictprocess java -jar iplpredict.jar 0.0.0.0 58080 &
done
