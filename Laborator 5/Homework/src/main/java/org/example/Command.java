package org.example;

import javax.xml.catalog.CatalogException;
import java.io.IOException;

public interface Command {
    void execute() throws CatalogException, IOException;
}
