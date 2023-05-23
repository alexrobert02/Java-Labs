# Laboratorul nr. 12

## Compulsory
* The input will be a .class file, located anywhere in the file system. (solved) (Inputul pentru aplicatie este *TestClass.class* generat dupa compilarea acestuia)
* Load the specified class in memory, identifying dynamically its package. (solved) (Identificarea se face folosind *clazz.getPackage().getName()*)
* Using reflection, extract as many information about the class (at least its methods). (solved) (Afisez informatii despre toate metodele clasei parcurgandu-le cu *clazz.getDeclaredMethods()*)
* Using reflection, invoke the static methods, with no arguments, annotated with @Test. (solved) (Intai verific daca metoda are adnotarea *@Test* iar apoi verificand prin *Modifier.isStatic(modifiers) && method.getParameterCount() == 0)* invoc metoda.)