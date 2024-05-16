#!/bin/bash

# Replace these variables with your actual values
APP_NAME="your_application_name"
WAR_FILE="path_to_your_war_file"
TOMCAT_WEBAPPS_DIR="/var/lib/tomcat/webapps"

# Stop Tomcat
sudo service tomcat stop

# Remove existing deployment
sudo rm -rf $TOMCAT_WEBAPPS_DIR/$APP_NAME*

# Copy the new WAR file to Tomcat's webapps directory
sudo cp $WAR_FILE $TOMCAT_WEBAPPS_DIR/

# Start Tomcat
sudo service tomcat start

# Check Tomcat status
sudo service tomcat status
