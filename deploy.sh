#!/bin/bash

# Copy application artifact to Tomcat webapps directory
cp /home/ec2-user/server/target/firstproject123-1.0-SNAPSHOT.war /home/ec2-user/tomcat/webapps/firstproject

# Change directory to Tomcat bin folder
cd /home/ec2-user/tomcat/bin

# Stop Tomcat
./shutdown.sh

# Wait for 20 seconds
sleep 20

# Start Tomcat
./startup.sh
