# Graph Test Code Makefile
# Christopher Blignaut
# 28 April 2023

JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) -sourcepath $(SRCDIR) $<
	
CLASSES=Edge.class Vertex.class Path.class \
	GraphException.class Graph.class GraphGenerator.class\

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)


default: $(CLASSES_FILES)

clean:
	rm $(BINDRIR)/*.class

dataset: $(CLASS_FILES)
	$(JAVA) -cp bin Data	
run: $(CLASS_FILES)
	$(JAVA) -cp bin Graph