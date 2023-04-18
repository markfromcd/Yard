JAVAFILES=Yard.java TestYard.java

.PHONY: clean

compile:
	javac Yard.java TestYard.java RunYard.java

run:
	java -ea RunYard

clean:
	rm -rf *.class
