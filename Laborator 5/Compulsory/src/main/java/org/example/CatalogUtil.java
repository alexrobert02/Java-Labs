package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    // default constructor for load
    public CatalogUtil() {
    }
    // save the document with a .json extension
    public static void save(Catalog catalog, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), catalog);
    }
    // load the document
    public static Catalog load(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //documents = objectMapper.readValue(new File(filePath), new TypeReference<List<Document>>(){});
        Catalog catalog = objectMapper.readValue(new File(filePath), Catalog.class);
        return catalog;
    }
}
