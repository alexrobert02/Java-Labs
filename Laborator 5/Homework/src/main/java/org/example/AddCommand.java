package org.example;

public class AddCommand implements Command {
    private Catalog catalog;
    private Document document;

    public AddCommand(Catalog catalog, Document document) {
        this.catalog = catalog;
        this.document = document;
    }

    @Override
    public void execute() {
        catalog.addDocument(document);
    }
}
