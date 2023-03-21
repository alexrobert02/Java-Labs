package org.example;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog catalog = new Catalog("Catalog");

        // Create some documents
        Document document1 = new Document(1, "My Article", "path/to/article", Map.of("Author", "John Doe", "Year", "2022"));
        Document document2 = new Document(2, "My Book", "path/to/book", Map.of("Author", "Jane Smith", "Year", "2021"));

        // Add the documents to the catalog
        catalog.add(document1);
        catalog.add(document2);

        // Save and load the catalog to/from a file
        CatalogUtil.save(catalog, "catalog.json");
        Catalog loadedCatalog = CatalogUtil.load("catalog.json");

        // Print the loaded catalog
        System.out.println("Loaded catalog: " + loadedCatalog);


    }
}