#!/bin/bash

docker run --name data-java-avanzado \
  -e MYSQL_ROOT_PASSWORD=my-secret-pw \
  -d \
  -p 3306:3306 \
  -v /home/josmiguelmm/DataMySQL:/var/lib/mysql \
  mysql:tag
