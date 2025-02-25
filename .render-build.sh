#!/usr/bin/env bash
./gradlew build -x test

#!/usr/bin/env bash

# Install Java (OpenJDK 17)
apt-get update && apt-get install -y openjdk-17-jdk

# Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Proceed with the build
./gradlew build -x test
