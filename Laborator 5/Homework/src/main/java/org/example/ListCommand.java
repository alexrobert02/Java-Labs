package org.example;

import javax.xml.catalog.CatalogException;
import java.util.List;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws CatalogException {
        List<Document> documents = catalog.getDocuments();
        if (documents.isEmpty()) {
            throw new CatalogException("Catalog is empty");
        }
        for (Document document : documents) {
            System.out.println(document);
        }
    }
}