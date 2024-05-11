#!/bin/bash

sudo rm -rf /home/ec2-user/tomcat8/webapps/*

# Copy application artifact to Tomcat webapps directory
sudo cp /home/ec2-user/server/target/firstproject123-1.0-SNAPSHOT.war /home/ec2-user/tomcat8/webapps/

# Change directory to Tomcat bin folder
cd /home/ec2-user/tomcat8/bin

# Stop Tomcat
sudo ./shutdown.sh

# Wait for 20 seconds
sleep 20

# Start Tomcat
sudo ./startup.sh
