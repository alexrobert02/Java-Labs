# Laboratorul nr. 3

## Homework

1. Create the complete model: Person, Programmer, Designer, Company. All persons have a birth date. Each class must have at least one specific property, that others don't have (be creative). (solved) (Am creat clasele *Person* si *Company*, apoi am derivat din clasa *Person*, clasele *Programmer* si *Designer*.)
2. Each person will contain a Map defining the relathionships to other persons or companies. (solved) (Am creat o mapa cu cheia de tip string si values de tip lista de obiecte de tip *node*)
3. Create the Network class containing a List of identifiable nodes. (solved) (Am creat clasa *Network* ce contine o lista de noduri.)
4. Create a method that computes the importance of a node in the network, as the number of its connections to other nodes. (solved) (Am creat metoda *getNodeImportance* care intai calculeaza gradul extern al nodului dat ca parsmetru folosindu-ne de marimea hashmap-ului, apoi gradul intern iterand peste fiecare nod in parte din lista.)
5. Create a network object containing persons, companies and relationships and print it on the screen. When printing the network, the nodes must be ordered according to their importance. (solved) (Am creat obiecte de tip Person, Company si am adaugat relatii. Am creat un network in care am adaugat aceste noduri, pe care ulterior le-am afisat.)