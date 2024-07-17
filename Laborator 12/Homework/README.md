## Homework

* The input may be a folder (containing .class files) or a .jar. You must explore it recursively. (solved) (Aplicatia verifica daca classPath este un fisier sau un fisier .jar. In cazul in care este un director, se pargurge recursiv pentru a gasi fisiere .class. Daca fisierul eeste .jar, se incarca clasele din acel fisier.)
* Create the complete prototype, in the same manner as javap tool. (solved) (Folosind *reflexion*, se analizeaza clasele si afiseaza informatii despre acestea utilizand metodele *getPackage()*, *getSimpleName()*, *getDeclaredMethods()*, si *getModifiers()*)
* Identify all public classes annotated with @Test and invoke the methods annotated with @Test, whether static or not. (solved) (Se verifica daca o metoda este adnotata cu *@Test@* folosind *isAnnotationPresent(Test.class)* si *Modifier.isPublic(modifiers)*. Daca sunt respectate conditiile, se invoca metoda.)
* If a method requires primitive (at least int) or String arguments, generate mock values for them. (solved) (Metoda *generateMockArguments()* insereaza argumente *mock* pentru metodele de testare. Verificand tipurile parametrilor, se insereaza valori corespunzatoare.)
* Print a statistics regarding the tests. (solved) (Cand o metoda test este invocata cu succes, se contorizeaza *passedTests*. La final, se afiseaza numarul total de teste, numarul de teste invocate cu succes si numarul de teste esuate.)