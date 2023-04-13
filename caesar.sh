#!/usr/local/bin/bash
./gradlew build
java -jar build/libs/caesar-1.0-SNAPSHOT.jar $1 $2 $3
