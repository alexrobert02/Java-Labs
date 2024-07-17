## Homework

* Create the object oriented model of the game. Consider implementing a retained mode for drawing the game board. (solved) (Folosind libraria *JavaFX*, elementele grafice sunt stocate in clase, astfel se poate actualiza scena cu usurinta.)
* Implement the logic of the game. Use a *mouse listener* in order to select the line which must be colored, either by selecting the dots or the line itself. Validate the moves, according to the game rules. Determine the winner of the game. (solved) (Metoda *startGame* implica un mouse listener care coloreaza o linie atunci cand se apasa pe ea. Daca aceasta linie exista in matricea de adiacenta creeata de butonul *Create game*, atunci aceasta se coloreaza in functie de jucatorul care este la rand. Cand un jucator a castigat desenand un triunghi, adica pentru fiecare iteratie linia desenata este cea care determina castigatorul prin verificarea matricii de culori a trei linii conectate, se afizeaza un obiect de tip *Alert* care contine un mesaj.)
* Export the current image of the game board into a PNG file. (solved) (Cand un jucator a castigat, se va afisa un *Alert* care contine doua butoane. Apasand pe butonul *Yes*, utilizatorul are acces la a alege locatia salvarii desenului in format PNG utilizand clasa *WritableImage*.)
* Use object serialization in order to save and restore the current status of the game. (solved) (Alegandu-se locatia salvarii si locatia de load utilizand clasa *FileChooser*, se transmit pe rand atributele clasei *DrawingPanel* intr-un output stream folosind outputStream.writeObject. Load presupune deserializarea obiectului, preluand informatiile din input stream utlizand inputStream.readObject().)