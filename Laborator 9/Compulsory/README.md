## Compulsory

* Create a *persistence unit* (use EclipseLink or Hibernate or other JPA implementation).

    Verify the presence of the *persistence.xml* file in your project. Make sure that the driver for EclipseLink or Hibernate was added to your project classpath (or add it yourself). (solved) (Am creat un proiect *Maven* unde am creat persistence unit-ul numit *default*. Am verificat existenta in fisierul *persistence.xml*.)
* Define the entity classes for your model (at least one) and put them in a dedicated package. You may use the IDE support in order to generate entity classes from database tables. (solved) (Am creat clasele entity *Album*, *Artist* si *Genre* structurate in functie de baza de date.)
* Create a *singleton* responsible with the management of an *EntityManagerFactory* object. (solved) (Am creat clasa PersistenceManagement care se ocupa de management-ul entitatilor si conexiunea cu baza de date.)
* Define *repository* classes for your entities (at least one). They must contain the following methods:
  * *create* - receives an entity and saves it into the database;
  * *findById* - returns an entity based on its primary key;
  * *findByName* - returns a list of entities that match a given name pattern. Use a *named query* in order to implement this method. (solved) (Am creat pentru fiecare entity *NamedQueries* pentru *create*, *findByName*, *findById*.)
* Test your application. (solved)