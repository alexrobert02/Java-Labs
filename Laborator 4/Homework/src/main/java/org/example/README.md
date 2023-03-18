# Laboratorul nr. 4

## Homework

1. Create a class that describes the problem. (solved) (Am creat clasa *Problem* avand ca atribut un Map avand cheia *Student* si ca valoare un *Set* de *Project* pentru fiecare *Student*.)
2. Using Java Stream API, write a query that display all the students that have a number of preferences lower than the average number of preferences. (solved) (Am creat o functie care calculeaza numarul *avgPrefs* si alta functie care filtreaza mapa si afiseaza studentii care au numarul de preferinte mai mic decat *avgPrefs*.)
3. Use a third-party library in order to generate random fake names for students and projects. (solved) (Am folosit clasa *Faker* pentru a genera nume intregi pentru *Student* si culori pentru *Project* pentru a asigura repetarea valorilor intre fiecare *Set* de *Project* pentru motive de testare.)
4. Create a Greedy algorithm in order to solve the problem. (solved) (Am creat functia *greedy* care sorteaza lista dupa numarul de preferinte, astfel, parcurgand fiecare student in parte, aleg prima lui optiune disponibila.)