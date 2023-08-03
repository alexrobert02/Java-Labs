## Compulsory

* Create an object-oriented model of the problem. You should have at least the following classes: *Catalog*, *Document*. Create a class responsible with external operations regarding a catalog. (solved) (Am creat clasa *Document* avand ca atribute id, name, pathOrUrl and tags, clasa *Catalog* care contine o lista de documente si clasa *CatalogUtil* pentru a efectua operatii peste clasa *Catalog*.)
* Implement the following methods representing *commands* that will manage the content of the catalog:
   * *add*: adds a new entry into the catalog; (solved) (Am creat metoda *add* care adauga un document la catalog.)
   * *toString*: a textual representation of the catalog; (solved) (Am creat metoda *toString* care afiseaza continutul catalogului.)
   * *save*: saves the catalog to an external file using JSON format; you may use Jackson or other library; (Am creat metoda *save* care salveaza obiectul catalog intr-un fisier de tip JSON.)
   * *load*: loads the catalog from an external file. (solved) (Am creat metoda load care citeste dintr-un fisier JSON catalogul salvat anterior.)