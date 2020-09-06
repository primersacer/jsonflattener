September 06, 2020

## 1. Overview
This is a program written in Java that takes a JSON object as
input and outputs a flattened version of the JSON object,
with keys as the path to every terminal value in the JSON
structure.

## 2. Contents
JsonFlattener.java - The java program for JSON flattening

README             - This file

ValidJson/         - A directory containing a set of valid
                     JSON files used for testing
                     
InvalidJson/       - A directory containing a set of invalid
                     JSON files used for testing

## 3. Tool Versions
Java - openjdk 14.0.2 2020-07-14
Gson - gson-2.8.6.jar

## 4. Setup
Tool setup:  Download and install java and gson

Command to compile JsonFlattener.java:
  > javac JsonFlattener.java -cp gson-2.8.6.jar

Command to run JsonFlattener with a json.  Replace json_path
with path to your json:
  > cat json_path | java -cp .:gson-2.8.6.jar JsonFlattener

Note: Providing an invalid json will cause program to exit
with an error

## 5. Contact Information
Please contact Xavier Cambou with any questions

Email: xcambou@gmail.com

Phone: 512-767-8707
