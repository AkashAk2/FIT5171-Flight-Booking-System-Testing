#!/bin/bash
set -e

echo "Building the project..."
mvn clean install

echo "Running tests..."
mvn test
