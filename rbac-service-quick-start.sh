#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME."
echo "[Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH."
	
set MAVEN_OPTS=$MAVEN_OPTS -XX:MaxPermSize=128m

echo "[Step 1] Install all  modules to local maven repository."

mvn clean install -DskipTests


echo "[Step 2] Start rbac projects."
echo "[INFO] Please wait a moment then access below demo sites:"
echo "[INFO] http://localhost:8080/rbac-rest-service"

cd rbac-rest-service
mvn clean jetty:run -Dspring.profiles.active=functional -DskipTests
cd ..


