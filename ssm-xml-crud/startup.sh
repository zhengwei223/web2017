#!/bin/bash
cd ../
mvn install -DskipTests
cd ssm-xml-crud
mvn clean jetty:run -DskipTests -Dspring.profiles.active=development