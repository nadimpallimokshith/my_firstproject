#!/bin/bash
if curl -s http://localhost:8080/firstproject123-1.0-SNAPSHOT | grep 'UP'; then
  echo "Application is running."
  exit 0
else
  echo "Application is not running."
  exit 1
fi
