# Laboratorul nr. 8

## Homework
* Create an object-oriented model of the data managed by the Java application. (solved) (Am creat interfata generica *DAO* care este implementata de toate clasele *DAO* pentru a putea da override la metode.)
* Implement all the DAO classes. (solved) (Clasele *AlbumDAO*, *ArtistDAO* si *GenreDAO* se ocupa de cautarea si inserarea in in baza de date a inregistrarilor.)
* Use a connection pool in order to manage database connections, such as C3PO, HikariCP or Apache Commons DBCP. (solved) (Am implementat un connection pool folosing biblioteca *Apache Commons DBCP*.)
* Create a tool to import data from a real dataset, such as Rolling Stone's 500 Greatest Albums of All Time (or other) (solved) (Folosind biblioteca *opencsv*, am creat clasa *CSVTool* cu care am putut parcurge un document csv ca sa pot introduce in baza de date inregistrarile sale.)