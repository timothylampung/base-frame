#!/bin/bash
figlet "SPOT IT"

cd /root/projects/spotit/front
git pull
echo "DELETING DIST FOLDER"
rm -rf dist/
echo "BUILDING SPOTIT FRONTEND"
ng build -c dev --base-href  /spotit/ --deploy-url /spotit/
echo "PAYMENT FRONTEND COMPLETED!"

cd /root/projects/spotit/back
git pull
echo "BUILDING WAR FILE"
mvn -DskipTests=true clean package
echo "REMOVING OLD WEBAPPS"
rm -rf /opt/tomcat/webapps/spotit
echo "COPYING TARGET WAR TO TOMCAT"
cp /root/projects/spotit/back/target/spotit.war /opt/tomcat/webapps/
echo "COMPLETED!"
