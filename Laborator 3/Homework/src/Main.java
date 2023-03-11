import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Persons, Programmers, Designers and Companies
        Person Andra = new Programmer("Andra", LocalDate.of(2002, 2, 24), "Python");
        Person Alex = new Designer("Alex", LocalDate.of(2002, 4, 24), "CSS");
        Person Sebi = new Person("Sebi", LocalDate.of(2002, 11, 28));
        Person Teo = new Person("Teo", LocalDate.of(2002, 10, 24));
        Person Alexandra = new Person("Alexandra", LocalDate.of(2002, 5, 12));
        Person Ana = new Person("Ana", LocalDate.of(2001, 7, 13));
        Company Amazon = new Company("Amazon");
        Company Bitdefender = new Company("Bitdefender");
        //adding relationships
        Andra.addRelationships("brother", Alex);
        Alex.addRelationships("sister", Andra);
        Andra.addRelationships("friend", Teo);
        Teo.addRelationships("friend", Andra);
        Teo.addRelationships("friend", Sebi);
        Sebi.addRelationships("friend", Teo);
        Teo.addRelationships("friend", Alexandra);
        Alexandra.addRelationships("friend", Teo);
        Teo.addRelationships("friend", Ana);
        Amazon.addRelationships("CEO", Alex);
        Bitdefender.addRelationships("worker", Andra);
        Ana.addRelationships("friend", Teo);
        //creating the network
        Network network = new Network(Arrays.asList(Alex, Andra, Teo, Sebi, Alexandra, Ana, Amazon, Bitdefender));
        System.out.println(network);
    }
}

