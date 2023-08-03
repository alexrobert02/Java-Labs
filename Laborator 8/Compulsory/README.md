## Compulsory

* Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.). (solved) (Am creat un Database Connection in sqldeveloper numit l8 avand ca username si password *java*.)
* Write an SQL script that will create the following tables: (solved) (Am creat un script SQL care creaza tabelele urmatoare.)
   * *albums*: id, release year, title, artist, genre(s)
   * *artists*: id, name (for example: Beatles)
   * *genres*: id, name (for example: Rock)
   * an associative (junction) table in order to store each album genres
* Update *pom.xml*, in order to add the *database driver* to the project libraries. (Am adaugat dependinta pentru Oracle.)
* Create a *singleton* class in order to manage a connection to the database. (Clasa *Database* creeaza conexiunea catre basa de date.)
* Create *DAO* classes that offer methods for managing artists, genres and albums (at least one). (Am creat clasele *AlbumDAO*, *ArtistDAO* si *GenreDAO*, cate un constructor pentru fiecare clasa in parte si 2 methode pentru cautarea in functie de nume si id.)
* Implement a simple test using your classes. (Am populat tabelele in main si am afisat informatiile din tabela *albums*.)