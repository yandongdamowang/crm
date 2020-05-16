#!/bin/bash
mvn clean install sonar:sonar \
  -Dsonar.projectKey=fitmgr-recycle \
  -Dsonar.host.url=http://sonarqube.ls.com \
  -Dsonar.login=6075b2894c104a5e5fc2cb2db1c6ca7f1f9fd343