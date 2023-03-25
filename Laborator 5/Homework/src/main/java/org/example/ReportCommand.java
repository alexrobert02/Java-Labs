package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private static final String TEMPLATE_DIRECTORY = "/templates";
    private static final String REPORT_TEMPLATE_NAME = "report.ftl";
    private static final String REPORT_FILE_EXTENSION = ".html";

    private final Catalog catalog;
    private final String outputDirectory;

    public ReportCommand(Catalog catalog, String outputDirectory) {
        this.catalog = catalog;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public void execute() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), TEMPLATE_DIRECTORY);

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("documents", catalog.getDocuments());

        Template template = configuration.getTemplate(REPORT_TEMPLATE_NAME);

        String reportFileName = catalog.getName() + REPORT_FILE_EXTENSION;
        String reportFilePath = Paths.get(outputDirectory, reportFileName).toString();

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(reportFilePath), StandardCharsets.UTF_8))) {
            template.process(dataModel, writer);
        } catch (TemplateException e) {
            throw new IOException("Failed to process report template", e);
        }

        System.out.println("Report generated successfully: " + reportFilePath);
    }
}