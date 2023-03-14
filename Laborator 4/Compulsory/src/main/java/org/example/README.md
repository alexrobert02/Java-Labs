# Laboratorul nr. 4

## Compulsory

1. Create a Maven project. (solved) (Am creat proiectul Maven *Compulsory*.)
2. Create an object-oriented model of the problem. Students and projects have names. Make sure the objects of these classes are comparable. (solved) (Am creat clasele *Student* and *Project* avand doar atributul *name* de tip String, implementand interfata *Comparable*.)
3. Create the students and the projects described in the example. Use streams in order to easily create the objects. (solved) (folosind metoda *IntStream.rangeClosed()* am generat numarul dorit de studenti si de proiecte in intervalul dorit.)
4. Put all the students in a LinkedList and print them sorted by their names. (solved) (Apeland metoda *Collections.sort()* am sortat elementele din lista.)
5. Put all the projects in a TreeSet and print them sorted by their names. (solved) (Folosit tipul *TreeSet*, elementele sunt deja ordonate automat cand sunt adaugate.)