package org.example;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    String name;
    private List<Document> documents;
    // default constructor for load
    public Catalog() {
    }

    public Catalog(String catalog) {
        this.name = catalog;
        documents = new ArrayList<>();
    }
    // method to add a document to the catalog
    public void add(Document document) {
        documents.add(document);
    }
    // method to remove a document from the catalog by id
    public void remove(int documentId) {
        documents.removeIf(document -> document.getId() == documentId);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Document document : documents) {
            result.append(document.toString()).append("\n");
        }
        result.append("}");
        return result.toString();
    }

    // getter and sette
    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}