package ru.javafiddle.web.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by atsanda on 17.05.16.
 */
@XmlRootElement
public class NewFileModel {

    private String projectHash;
    private String fileName;
    private String typeName;
    private String path;

    public NewFileModel() {
    }

    public NewFileModel(String projectHash, String fileName, String typeName, String path) {
        this.projectHash = projectHash;
        this.fileName = fileName;
        this.typeName = typeName;
        this.path = path;
    }

    public String getProjectHash() {
        return projectHash;
    }

    public void setProjectHash(String projectHash) {
        this.projectHash = projectHash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
