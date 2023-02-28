Pentru clasele Location si Road am creat setters si getters pentru fiecare atribut in parte.
Pentru Location am creat 2 constructori, unul cu instantiere folosindu-ma de setters si unul fara.
Pentru Road am creat constructorul folosindu-ma de setters.
Setter-ul pentru length a fost creat folosind formula matematica a lui Euclid pentru distanta intre doua puncte intr-un plan bidimensional: d = √[(x2 – x1)^2 + (y2– y1)^2].
Am creat clasa RoadType de tip enum unde am inserat valori reprezentand tipul unui Road.
Am creat metoda toString careia i-am dat override pentru a putea apela o varianta specifica a metodei in functie de obiectul oferit, unde am creat un string pentru concatenarea atributelor din obiect.
