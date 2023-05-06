# Laboratorul nr. 9

## Homework
* Create all entity classes and repositories. Implement properly the one-to-many and many-to-many relationships. (solved) (Am creat clasele *Artist*, *ArtistRepository*, *Album*, *AlbumRepository*, *Genre* si *GenreRepository*. Pentru fiecare entitate am creat relatii one-to-many si many-to-many cum ar fi @ManyToMany intre *Album* si *Genre*. Fiecare Repository implementeaza operatii cum ar fi *findByName* si *create*)
* Create a generic AbstractRepository using generics in order to simplify the creation of the repository classes. You may take a look at the CrudRepository interface from Spring Framework. (solved) (Clasa generica *AbstractRepository* defineste cateva operatii *CRUD* care sunt folosite de clasele care o extind.)