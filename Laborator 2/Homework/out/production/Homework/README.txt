Pentru clasele Location si Road am creat setters si getters pentru fiecare atribut in parte.
Am creat transformat clasa Location intr-una abstracta pentru a putea crea clasele Airport, City si GasStation care se extind din Location. In apelarea constructorului specific lor, se va folosi antetul super pentru apelarea constructorului de la Location.
Am creat constructorii si setters/getters necesari.
Am creat clasa RoadType de tip enum unde am inserat valori reprezentand tipul unui Road.
Am creat metoda toString careia i-am dat override pentru a putea apela o varianta specifica a metodei in functie de obiectul oferit, unde am creat un string pentru concatenarea atributelor din obiect.
Am creat clasa Problem care este formata dintr-un array de locatii si un array de roads, unde pe langa constructori, getters si setters, am creat medota isValid pentru a verifica daca distanta introdusa de noi este posibila sau nu folosind formula lui euclid pentru distanta intre 2 puncte.
Pe langa asta, am verificat si duplicatele din multime folosint 2 for-uri.
Metoda canGo consta in crearea unei liste de array-uri 'visited' pentru a putea verifica daca exista un drum intre cele 2 locatii date folosind un algoritm de tip dfs
Parcurgand fiecare drum din array, se verifica drumurile care pot duce la destinatia finala. Daca exista un drum intre 2 noduri, il vizitam si apoi parcurgem toate drumurile plecand de la el ca sa putem ajunge la destinatia finala.
Metoda equals verifica daca doua obiecte sunt identice.