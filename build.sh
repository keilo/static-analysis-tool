#!/bin/bash
if [ "$1" = "clean" ]
then
	rm *.java *.class
else
	rm *.java *.class
	javacc CParser.jj
	javac *.java 
fi

