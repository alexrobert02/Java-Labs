# Laboratorul nr. 5

## Homework

1. Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command.
   
   Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).
   * list: prints the list of documents on the screen; (solved) (Am creat clasa *ListCommand* care afiseaza fiecare document al catalogului.)
   * view: opens a document using the native operating system application (see the Desktop class); (solved) (Am creat clasa *ViewCommand* care deschide documentul.)
   * report: creates (and opens) an HTML report representing the content of the catalog. (solved) (Utilizand clasa *ReportCommand*, am creat un report HTML care contine continutul cataloglui.)
   
      Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.
2. The application will signal invalid data or the commands that are not valid using custom exceptions. (solved) (Am creat expectii specifice, cum ar fi *InvalidCommand* care semnaleaza un warning atunci cand este introdusa e comanda eronata.)
3. The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR. (solved)