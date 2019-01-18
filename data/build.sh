#!/bin/bash
echo "rebuilding db"
mvn sql:execute@db-drop sql:execute@db-seed
