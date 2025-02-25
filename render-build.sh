#!/bin/bash

# Set correct Java path
export JAVA_HOME=/opt/render/.jdk
export PATH=$JAVA_HOME/bin:$PATH

# Grant execute permission (just in case)
chmod +x ./gradlew

# Run the build command
./gradlew build -x test
