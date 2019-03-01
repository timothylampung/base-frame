#!/bin/bash
figlet "MPSP TASK"

cd /root/projects/spotit-web/front
git pull
echo "DELETING DIST FOLDER"
rm -rf dist/
echo "BUILDING TASK FRONTEND"
ng build -c dev --base-href  /spotit-web/ --deploy-url /spotit-web/
echo "TASK FRONTEND COMPLETED!"

cd /root/projects/spotit-web/back
git pull
echo "BUILDING WAR FILE"
mvn -DskipTests=true clean package
echo "REMOVING OLD WEBAPPS"
rm -rf /opt/tomcat/webapps/spotit-web
echo "COPYING TARGET WAR TO TOMCAT"
cp /root/projects/spotit-web/back/target/spotit-web.war /opt/tomcat/webapps/
echo "COMPLETED!"
