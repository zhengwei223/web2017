#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME."
echo "[Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH."
	
set MAVEN_OPTS=$MAVEN_OPTS -XX:MaxPermSize=128m

echo "安装commons jar包到仓库"

cd commons
mvn clean install -DskipTests
cd ..


