package org.example;

import javax.xml.catalog.CatalogException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    private Document document;

    public ViewCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() throws CatalogException {
        if (!Desktop.isDesktopSupported()) {
            throw new CatalogException("Desktop is not supported");
        }
        try {
            Desktop.getDesktop().open(new File(document.getPathOrUrl()));
        } catch (IOException e) {
            throw new CatalogException("Could not open document", e);
        }
    }
}