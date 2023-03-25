package org.example;

import freemarker.template.TemplateException;

import javax.xml.catalog.CatalogException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog("My Documents");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (add, list, view, report, save, load, exit):");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            String commandName = tokens[0];

            try {
                if ("add".equalsIgnoreCase(commandName)) {
                    if (tokens.length < 2) {
                        throw new InvalidCommandException("Invalid add command. Usage: add <document id>");
                    }
                    String documentId = tokens[1];
                    //Document document = createDocumentFromInput(documentType, scanner);
                    System.out.println("Enter document name:");
                    String documentName = scanner.nextLine();
                    System.out.print("Enter document path or URL: ");
                    String documentPathOrUrl = scanner.nextLine();
                    System.out.print("Enter document tags (comma-separated key-value pairs, e.g. 'title=My Article,author=John Doe,year=2022'): ");
                    String documentTags = scanner.nextLine();
                    String[] tagsArray = documentTags.split(",");
                    Map<String, String> tags = new HashMap<>();
                    for (String tag : tagsArray) {
                        String[] pair = tag.trim().split("=");
                        tags.put(pair[0], pair[1]);
                    }
                    Document document = new Document(Integer.parseInt(documentId), documentName, documentPathOrUrl, tags);
                    catalog.addDocument(document);
                    System.out.println("Document added.");
                    System.out.println(catalog);
                } else if ("list".equalsIgnoreCase(commandName)) {
                    System.out.println(catalog.getDocuments());
                } else if ("view".equalsIgnoreCase(commandName)) {
                    if (tokens.length < 2) {
                        throw new InvalidCommandException("Invalid view command. Usage: view <document ID>");
                    }
                    String documentId = tokens[1];
                    Document document = catalog.findDocumentById(documentId);
                    if (document == null) {
                        throw new CatalogException("Document not found");
                    }
                    ViewCommand viewCommand = new ViewCommand(document);
                    viewCommand.execute();
                } else if ("report".equalsIgnoreCase(commandName)) {
                    if (tokens.length < 2) {
                        throw new InvalidCommandException("Invalid report command. Usage: report <HTML report file>");
                    }
                    String htmlReportFile = tokens[1];
                    ReportCommand reportCommand = new ReportCommand(catalog, htmlReportFile);
                    reportCommand.execute();
                    System.out.println("Report generated.");
                } else if ("save".equalsIgnoreCase(commandName)) {
                    if (tokens.length < 2) {
                        throw new InvalidCommandException("Invalid save command. Usage: save <catalog file>");
                    }
                    String catalogFile = tokens[1];
                    CatalogUtil.save(catalog, catalogFile);
                    System.out.println("Catalog saved.");
                } else if ("load".equalsIgnoreCase(commandName)) {
                    if (tokens.length < 2) {
                        throw new InvalidCommandException("Invalid load command. Usage: load <catalog file>");
                    }
                    String catalogFile = tokens[1];
                    catalog = CatalogUtil.load(catalogFile);
                    System.out.println("Catalog loaded.");
                } else if ("exit".equalsIgnoreCase(commandName)) {
                    break;
                } else {
                    throw new InvalidCommandException("Invalid command.");
                }
            } catch (CatalogException | IOException | InvalidCommandException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }

}