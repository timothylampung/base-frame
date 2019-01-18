#!/bin/bash
echo "building webapp"
mvn -DskipTests=true clean package
