#!/bin/bash

javac src/*.java -d classes/
cd classes
java UI
