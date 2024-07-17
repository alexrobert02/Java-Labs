## Homework

* Implement the commands that start/pause the robots (all of them or only a specific one). A robot can be paused for a specific time or indefinitely, requiring a start command.

  The commands must be given using the keyboard. (solved) (Am implementat functiile *start*, *pause*, *resume* si *quit* care incep thread-uri concurente pentru fiecare robot in parte. *Quit* intrerupe thread-urile si opreste tot programul din a rula. Comenzile se dau la tastatura cu ajutorul unui scanner.)
* Design an algorithm such that each robots will try to explore the map in a systematic fashion, ensuring the termination of the exploration process. (solved) (Am implementat o cautare *DFS*. Se incepe cu o pozitie la intamplare pentru fiecare robot in parte, apoi se exploreaza intr-o maniera *DFS* si se marcheaza intr-un *stack* urmatoarele celule care trebuie vizitate. Algoritmul se opreste atunci cand nu mai exista celule de vizitat.)
* Implement a *timekeeper* thread that runs concurrently with the player threads, as a *daemon*. This thread will display the running time of the exploration and it will stop it exceeds a certain time limit. (solved) (Dupa ce depaseste timpul de 1 minut, acest thread se opreste.)
* At the end of the exploration, determine how many tokens each robot has placed in the matrix. (solved) (Am numarat cati tokens stocheaza fiecare robot in parte, apoi i-am afisat folosind metoda *robot.getNumTokens()*)