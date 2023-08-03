## Compulsory

* Create an object oriented model of the problem. (solved) (Am creat clasele: *SharedMemory*, *ExplorationMap*, *Exploration*, *Robot* si *Token* care se ocupa de gestionarea problemei.)
* Each robot will have a name and they must perform in a concurrent manner, moving randomly around the map and extracting tokens from the shared memory when reaching an unvisited cell.

    A message will be displayed on the screen every time a robot visits a new cell. (solved) (Folosind thread-uri, asociez fiecarui thread un obiect de tip *Exploration* care contine si un obiect de tip *Robot*. Fiecare robot are acces la *sharedMemory* cu ajutorul keyword-ului *synchronized*. Se parcurge fiecare cell, se extrag n tokens si se marcheaza cell-ul respectiv ca vizitat, apoi se adauga in matrix cell. De fiecare data cand se visiteaza un cell, se afiseaza un mesaj.)
* **Simulate the exploration using a thread for each robot.**

    Pay attention to the *synchronization* of the threads when extracting tokens and when visiting cells. (solved) (Atasand keyword-ul *synchronized*, fiecare robot poate apela functile care contin acest keyword, astfel nu se poate ca un robot sa extraga acelasi token ca si alt robot sau sa visiteze in acelasi timp cu alt robot un cell.)