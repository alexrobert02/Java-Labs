package org.example;

import java.io.Serializable;
import java.util.Map;


public class Document implements Serializable {
    private int id;
    private String name;
    private String pathOrUrl;
    private Map<String, String> tags;
    // default constructor for load
    public Document() {
    }

    public Document(int id, String name, String pathOrUrl, Map<String, String> tags) {
        this.id = id;
        this.name = name;
        this.pathOrUrl = pathOrUrl;
        this.tags = tags;
    }
    // getters and setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPathOrUrl() {
        return pathOrUrl;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void addTag(String tagName, String tagValue) {
        tags.put(tagName, tagValue);
    }

    public void removeTag(String tagName) {
        tags.remove(tagName);
    }


    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", name='" + name + '\'' + ", pathOrUrl='" + pathOrUrl + '\'' + ", tags=" + tags + '}';
    }
}